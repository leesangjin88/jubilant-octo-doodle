package kr.or.ddit.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpSession;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberDeleteController {
  
  @Autowired
  private MemberService service;
  
  @PostMapping("/member/memberDelete.do")
  public String deleteMember(
      HttpSession session,
      @RequestParam String password,
      @AuthenticationPrincipal(expression = "realUser") MemberVO realUser,
      Authentication authentication,
      RedirectAttributes redirectAttributes
  ) {
    try {
      service.removeMember(realUser.getMemId(), password);
      
      SecurityContextHolder.clearContext();
      
      session.invalidate();
      return "redirect:/";
    } catch (AuthenticationException e) {
      redirectAttributes.addFlashAttribute("message", "비밀번호가 일치하지 않음");
      return "redirect:/mypage";
    }
  }
  
  
}
