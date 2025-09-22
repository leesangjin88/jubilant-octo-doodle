package kr.or.ddit.validate.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MimeTypeCheckValidator.class)
public @interface MimeTypeCheck {
  String mainType(); // 이러면 mainType을 안줘도 됨 
                                //  default가 없으면 필수 인자로 설정됨
  String message() default "파일 Mime의 메인 타입 확인";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
