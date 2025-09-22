package kr.or.ddit.pfcp.staff.reservationTimestamp.facility.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.pfcp.common.vo.FacilityVO;
import kr.or.ddit.pfcp.common.vo.ReservationTimestampVO;
import kr.or.ddit.pfcp.staff.facility.service.FacilityService;
import kr.or.ddit.pfcp.staff.reservationTimestamp.facility.service.FacilityRTService;
import kr.or.ddit.validate.utils.ErrorsUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author YSM
 * @since 250703
 */

@Slf4j
@Controller
@RequestMapping("/staff/reservationTimestamp")
public class FacilityRTController {
	
	
	@Autowired
	private FacilityService facilityService;
	
	@Autowired
	private FacilityRTService facilityRTService;
	
	static final String MODELNAME = "facilityReservationTimestamp";

	@Autowired
	private ErrorsUtils errorsUtils;
	
	@GetMapping("facility/facility.do")
	public String facilityReservationTimestamp(
		@RequestParam String what,
		Model model
	) {
		FacilityVO facility = facilityService.readFacility(what);
		model.addAttribute("facility", facility);
//		log.info("facility" + facility);

		List<ReservationTimestampVO> facilityRT = facilityRTService.readFacilityRT(what);
		model.addAttribute("facilityRT", facilityRT);
		log.info("facilityRT" + facilityRT);
		
		return "pfcp/staff/reservationTimestamp/facility/facilityRT";
	}
	
	@GetMapping("facility/facilityInsert.do")
	public String studentUpdateUI(
		String what,
		Model model
	) {
		List<ReservationTimestampVO> facilityRT = facilityRTService.readFacilityRT(what);
		
		FacilityVO facility = facilityService.readFacility(what);
		model.addAttribute("facility", facility);
		log.info("facility!!!" + facility);
		
		model.addAttribute("facilityRT", facilityRT);
		model.addAttribute("mode", "edit");
		return "pfcp/staff/reservationTimestamp/facility/facilityRT";
	}
	

	@PostMapping("facility/facilityUpdateProcess.do")
	@ResponseBody
	public Map<String, Object> facilityReservationTimestampUpdateProcess(
	        @RequestParam String what,
	        @RequestParam String timeSlots,
	        Model model
	) {
	    Map<String, Object> result = new HashMap<>();
	    
	    
	    
	    try {
	        // 1. 해당 시설의 모든 예약 시간표를 삭제
	        facilityRTService.deleteFacilityRT(what);
	        
	        // 2. 클릭된 모든 시간표를 다시 추가
	        if (timeSlots != null && !timeSlots.isEmpty()) {
	            ObjectMapper objectMapper = new ObjectMapper();
	            List<Map<String, Object>> activeSlotsData = objectMapper.readValue(timeSlots, List.class);
	            
	            for (Map<String, Object> slotData : activeSlotsData) {
	                String day = (String) slotData.get("day");
	                int hour = (Integer) slotData.get("hour");
	                
	                ReservationTimestampVO vo = new ReservationTimestampVO();
	                vo.setFacilityNo(what);
	                vo.setReservationDay(day);
	                vo.setStartHour(hour);
	                
	                facilityRTService.insertFacilityRT(vo);
	            }
	        }
	        
	        result.put("success", true);
	        result.put("message", "예약 시간이 성공적으로 업데이트되었습니다.");
	        
	    } catch (Exception e) {
	        log.error("예약 시간 업데이트 중 오류 발생: ", e);
	        result.put("success", false);
	        result.put("message", "업데이트 중 오류가 발생했습니다.");
	    }
	    
	    return result;
	}
}
