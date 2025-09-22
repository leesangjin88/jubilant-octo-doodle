package kr.or.ddit.validate.utils;

import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

@Component
public class ErrorsUtils {
  public MultiValueMap<String, String> errorsToMap(BindingResult errors) {
    MultiValueMap<String, String> customErrors = new LinkedMultiValueMap<String, String>();
    List<ObjectError> allErrors = errors.getAllErrors();
    for (ObjectError single : allErrors) {
      if (single instanceof FieldError) {
        FieldError fe = (FieldError) single;
        String fieldName = fe.getField();
        String message = fe.getDefaultMessage();
        customErrors.add(fieldName, message);
      }
    }
    return customErrors;
  }
}
