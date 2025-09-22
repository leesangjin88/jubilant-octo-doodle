package kr.or.ddit.validate.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = FileNotEmptyValidator.class) // ConstraintValidator 클래스와 관련된 제약조건을 따름
@Target(ElementType.FIELD) // 전역 변수로 사용하기 위해 사용
@Retention(RetentionPolicy.RUNTIME) // 실행 시간 동안 유지되게 함
public @interface FileNotEmpty {
  String message() default "파일이 비어있음";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
