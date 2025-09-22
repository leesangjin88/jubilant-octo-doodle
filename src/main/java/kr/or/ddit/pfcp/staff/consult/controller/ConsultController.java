package kr.or.ddit.pfcp.staff.consult.controller; // 실제 패키지 구조에 맞게 변경

import kr.or.ddit.pfcp.common.vo.ProfessorVO;
import kr.or.ddit.pfcp.common.vo.ReservationTimestampVO; // ReservationTimestampVO 임포트
import kr.or.ddit.pfcp.staff.professormanage.service.StaffProfessorManageService; // 교수 관리 서비스
import lombok.extern.slf4j.Slf4j;
import kr.or.ddit.pfcp.staff.consult.service.ConsultService; // 새로 정의된 ConsultService 임포트

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author YSM
 * @since 2025. 7. 7.
 *
 * << 개정이력(Modification Information) >>
 * 수정일		|	수정자	|	수정 내용
 * -----------------------------------------------
 * 2025. 7. 7.	|	양수민	|	최초 생성
 * 2025. 7. 9.  |   양수민    |   수정
 */
@Slf4j
@Controller
@RequestMapping("/staff/consult") // "/staff/consult" 경로로 통일
public class ConsultController { // ConsultController로 통일

    @Autowired
    private StaffProfessorManageService professorService; // 교수 목록을 가져올 서비스

    @Autowired
    private ConsultService consultService; // 새로 정의된 상담 시간표 서비스

    // ErrorsUtils는 이 시나리오에선 직접 사용되지 않을 수 있습니다. 필요시 추가하세요.
    // @Autowired
    // private ErrorsUtils errorsUtils;

    /**
     * 초기 페이지 로드: 교수 목록만 전달하여 consultScheduleList.jsp를 표시합니다.
     * URL: /staff/consult/consultScheduleList.do
     */
    @GetMapping("consultScheduleList.do")
    public String consultScheduleListUI(Model model) {
        List<ProfessorVO> professorList = professorService.readProfessorList();
        model.addAttribute("professorList", professorList);
        model.addAttribute("count", professorList.size());
        return "pfcp/staff/consult/consultScheduleList"; // 메인 JSP 경로
    }

    /**
     * AJAX 요청을 처리하여 특정 교수의 상담 시간표 HTML 조각을 반환합니다.
     * 클라이언트의 `selectProfessor` 함수가 이 엔드포인트를 호출합니다.
     * URL: /staff/consult/getConsultScheduleFragment.do?userNo={userNo}&mode={mode}
     */
    @GetMapping("/getConsultScheduleFragment.do")
    @ResponseBody // HTML 문자열을 직접 응답하기 위해 사용
    public String getConsultScheduleFragment(
            @RequestParam("userNo") String userNo,
            @RequestParam(value = "mode", required = false, defaultValue = "view") String mode,
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

    	log.info("userNo"+userNo);
    	
        List<ReservationTimestampVO> consultRT = consultService.readConsultSchedule(userNo);
        request.setAttribute("consultRT", consultRT); // Fragment JSP에서 사용할 수 있도록 request에 추가

        request.setAttribute("mode", mode); // "edit" 또는 "view"

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/pfcp/staff/consult/consultScheduleTableFragment.jsp");
        StringWriter writer = new StringWriter();
        PrintWriter out = new PrintWriter(writer);

        HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(response) {
            @Override
            public PrintWriter getWriter() throws IOException {
                return out;
            }
            @Override
            public ServletOutputStream getOutputStream() throws IOException {
                return new ServletOutputStream() {
                    @Override public boolean isReady() { return true; }
                    @Override public void setWriteListener(WriteListener writeListener) { /* do nothing */ }
                    @Override public void write(int b) throws IOException { /* do nothing */ }
                };
            }
        };

        dispatcher.include(request, responseWrapper); 
        return writer.toString(); 
    }

    /**
     * 상담 시간표를 저장하는 AJAX 요청을 처리합니다.
     * URL: /staff/consult/consultUpdateProcess.do
     */
    @PostMapping("/consultUpdateProcess.do")
    @ResponseBody // JSON 응답을 위해 사용
    public Map<String, Object> consultUpdateProcess(
            @RequestParam("userNo") String userNo,
            @RequestParam("consultList") String consultListJson) {
        Map<String, Object> response = new HashMap<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            // JSON 문자열을 ReservationTimestampVO 리스트로 변환
            List<ReservationTimestampVO> consultList = objectMapper.readValue(consultListJson, new TypeReference<List<ReservationTimestampVO>>() {});

            consultService.updateConsultSchedule(userNo, consultList); // 서비스 호출

            response.put("success", true);
            response.put("message", "예약 시간 설정이 성공적으로 저장되었습니다.");
        } catch (Exception e) {
            e.printStackTrace(); // 콘솔에 스택 트레이스 출력
            response.put("success", false);
            response.put("message", "예약 시간 설정 저장 중 오류가 발생했습니다: " + e.getMessage());
        }
        return response;
    }
}