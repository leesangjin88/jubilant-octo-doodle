package kr.or.ddit.login;

import java.util.Enumeration;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {

  @GetMapping("${myapp.login-url}")
  public String loginForm(
      @SessionAttribute(name = WebAttributes.AUTHENTICATION_EXCEPTION, required = false) Exception lastException,
      @RequestParam(value = "from", required = false) String from,
      @RequestParam(value = "redirect_url", required = false) String redirectUrl,
      HttpSession session,
      Model model,
      Authentication authentication,
      RedirectAttributes redirectAttributes
      ) {
    Enumeration<String> names = session.getAttributeNames();
    while(names.hasMoreElements()) {
      String key = names.nextElement();
      log.info("{} : {}", key, session.getAttribute(key));
    }
    
    if(authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
   // React에서 온 요청인지 확인
      if("react".equals(from)) {
          // redirect_url이 있으면 그곳으로, 없으면 React 앱 메인으로
          if(redirectUrl != null && !redirectUrl.isEmpty()) {
              return "redirect:" + redirectUrl;
          }
          return "redirect:http://localhost:6060/"; // 또는 React 앱의 기본 경로
      }
      redirectAttributes.addFlashAttribute("message", "이미 로그인 되어있습니다.");
      return "redirect:/";
    }
    
    if(lastException != null) {
      model.addAttribute("message", lastException.getMessage());
      session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
    
    return "login/loginForm";
    
  }

  @GetMapping("${myapp.logout-url}")
  public String logout() {
    return "login/logout";
  }
}
