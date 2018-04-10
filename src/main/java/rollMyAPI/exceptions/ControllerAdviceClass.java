package rollMyAPI.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import rollMyAPI.model.CustomExceptionPojo;

@ControllerAdvice
public class ControllerAdviceClass {

    @ExceptionHandler(UnauthorizedException.class)
    public @ResponseBody CustomExceptionPojo handleUnauthorized(UnauthorizedException ex) {
        CustomExceptionPojo error = new CustomExceptionPojo();
        error.setMessage(ex.getMessage());
        error.setStatus(403);
        return error;
    }


}
