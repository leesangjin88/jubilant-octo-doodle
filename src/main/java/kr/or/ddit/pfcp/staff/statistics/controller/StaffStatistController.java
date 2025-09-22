package kr.or.ddit.pfcp.staff.statistics.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 */
@Controller
@RequestMapping("/staff/statistics")
public class StaffStatistController {

	/**
	 *  시각화 통계 대시보드 진입 화면
	 * @return
	 */
    @GetMapping("/dashboard")
    public String statisticsDashboard() {
        return "pfcp/staff/statistics/chartDashboard";
    }

    /**
     * 강의 통계 - 교수별 개설 수		
     * @param year
     * @return
     */
    @GetMapping("/lecture/by-professor")
    @ResponseBody
    public Map<String, Object> getLectureByProfessor(@RequestParam int year) {
        Map<String, Object> result = new HashMap<>();
        result.put("labels", Arrays.asList("김교수", "이교수", "박교수"));
        result.put("data", Arrays.asList(10, 7, 12));
        return result;
    }

    /**
     * 강의 규모 분석
     * @return
     */
    @GetMapping("/lecture/size-analysis")
    @ResponseBody
    public Map<String, Object> getLectureSizeAnalysis() {
        Map<String, Object> result = new HashMap<>();
        result.put("labels", Arrays.asList("소규모", "중규모", "대규모"));
        result.put("data", Arrays.asList(30, 45, 25));
        return result;
    }

    /**
     *  전체 강의 개설 수
     * @return
     */
    @GetMapping("/lecture/total-opened")
    @ResponseBody
    public Map<String, Object> getTotalLectures() {
        Map<String, Object> result = new HashMap<>();
        result.put("year", 2024);
        result.put("total", 105);
        return result;
    }

    /**
     * 학생 수강 현황
     * @return
     */
    @GetMapping("/lecture/student-status")
    @ResponseBody
    public Map<String, Object> getStudentLectureStatus() {
        Map<String, Object> result = new HashMap<>();
        result.put("labels", Arrays.asList("1학년", "2학년", "3학년", "4학년"));
        result.put("data", Arrays.asList(100, 120, 110, 90));
        return result;
    }

    /**
     *  장학금 - 총 지급액
     * @return
     */
    @GetMapping("/scholarship/total-amount")
    @ResponseBody
    public Map<String, Object> getTotalScholarshipAmount() {
        Map<String, Object> result = new HashMap<>();
        result.put("year", 2024);
        result.put("amount", 95000000);
        return result;
    }

    /**
     * 장학금 - 수혜 유형별
     * @return
     */
    @GetMapping("/scholarship/benefit-status")
    @ResponseBody
    public Map<String, Object> getScholarshipBenefitStatus() {
        Map<String, Object> result = new HashMap<>();
        result.put("labels", Arrays.asList("성적우수", "저소득층", "교외장학"));
        result.put("data", Arrays.asList(30, 20, 15));
        return result;
    }

    /**
     *  장학금 - 학생별 수혜
     * @return
     */
    @GetMapping("/scholarship/by-student")
    @ResponseBody
    public Map<String, Object> getScholarshipByStudent() {
        Map<String, Object> result = new HashMap<>();
        result.put("labels", Arrays.asList("홍길동", "이몽룡", "성춘향"));
        result.put("data", Arrays.asList(3000000, 2500000, 4000000));
        return result;
    }

    /**
     *  장학금 지급 추이
     * @return
     */
    @GetMapping("/scholarship/trend")
    @ResponseBody
    public Map<String, Object> getScholarshipTrend() {
        Map<String, Object> result = new HashMap<>();
        result.put("labels", Arrays.asList("1학기", "2학기"));
        result.put("data", Arrays.asList(45000000, 50000000));
        return result;
    }
}
