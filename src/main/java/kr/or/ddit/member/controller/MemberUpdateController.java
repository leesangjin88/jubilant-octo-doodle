package kr.or.ddit.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.vo.MemberVO;

@Controller
@RequestMapping("/member/memberUpdate.do")
public class MemberUpdateController {
  
  static final String MODELNAME = "member";
  
  @Autowired
  private MemberService service;
  
  @ModelAttribute(MODELNAME)
  public MemberVO member() {
    MemberVO member = new MemberVO();
    return member;
  }
  
  // 수정 성공 후 -> mypage로 이동
  
  @GetMapping
  public String updateForm(
        Authentication authentication,
        Model model
      ) {
        String username = authentication.getName();
        MemberVO member = service.readMember(username);
        model.addAttribute(MODELNAME, member);
        return "member/memberUpdateForm";
 }
  
  @PostMapping
  public String updateProcess(
        @Validated(UpdateGroup.class) @ModelAttribute(MODELNAME) MemberVO member,
        BindingResult errors,
        RedirectAttributes redirectAttributes
      ) {
    String lvn;
    if(errors.hasErrors()) {
      redirectAttributes.addFlashAttribute(MODELNAME, member);
      String errorNames = BindingResult.MODEL_KEY_PREFIX + MODELNAME;
      redirectAttributes.addFlashAttribute(errorNames, errors);
      lvn = "redirect:/member/memberUpdate.do";
    } else {
      try {
        service.modifyMember(member);
        lvn = "redirect:/mypage";
      } catch (AuthenticationException e) {
        redirectAttributes.addFlashAttribute(MODELNAME, member);
        redirectAttributes.addFlashAttribute("message", "비밀번호가 일치하지 않음");
        lvn = "redirect:/member/memberUpdate.do";
      }
    }
    return lvn;
  }
  
  // 인증 실패 -> 다시 업데이트폼 양식으로 redirect, 비번 오류 메시지, 기존 입력 데이터 
  
  // 검증 실패 -> 다시 업데이트폼 양식으로 redirect, 기존 입력 데이터, 검증 에러 메시지
}
