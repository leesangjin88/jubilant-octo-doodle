package kr.or.ddit.member.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.common.exception.PKDuplicatedException;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.vo.MemberVO;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("${myapp.register-url}")
@RequiredArgsConstructor
public class MemberInsertController {
  
  static final String MODELNAME = "member";
  
  @ModelAttribute(MODELNAME)
  public MemberVO member() {
    MemberVO member = new MemberVO();

    return member;
  }
  
  private final MemberService service;
  
  @GetMapping
  public String memberForm(
        Authentication authentication,
        RedirectAttributes redirectAttributes
      ) {
    
    if(authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
      redirectAttributes.addFlashAttribute("message", "이미 회원가입 된 회원입니다.");
      return "redirect:/";
    }
    return "member/memberForm";
  }
  
  @PostMapping
  public String memberProcess(
        @Validated(InsertGroup.class) @ModelAttribute(MODELNAME) MemberVO member,
        BindingResult errors,
        RedirectAttributes redirectAttributes
      ) {
    String lvn;
    if(errors.hasErrors()) {
      redirectAttributes.addFlashAttribute(MODELNAME, member);
      String errorNames = BindingResult.MODEL_KEY_PREFIX + MODELNAME;
      redirectAttributes.addFlashAttribute(errorNames, errors);
      lvn = "redirect:/member/memberInsert.do";
    }else {
      try {
        service.createMember(member);
        lvn = "redirect:/";
//        if (lastException != null) {
//          String registrationId = lastException.getClientRegistration().getRegistrationId();
//          lvn = "redirect:"
//              + OAuth2AuthorizationRequestRedirectFilter.DEFAULT_AUTHORIZATION_REQUEST_BASE_URI
//              + "/" + registrationId;
//        }
      } catch (PKDuplicatedException e) {
        // 아이디 중복
        redirectAttributes.addFlashAttribute(MODELNAME, member);
        redirectAttributes.addFlashAttribute("message", "아이디 중복");
        lvn = "redirect:/member/memberInsert.do";
      }
    }
    return lvn;
  }
}
