package gov.cap.ohwg.es.alertroster.view;

import gov.cap.ohwg.es.alertroster.model.pojo.ErrorInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ckovacs on 5/12/16.
 */
@ControllerAdvice
public class DefaultErrorHandler {
    private static Logger LOG = LoggerFactory.getLogger(DefaultErrorHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorInfo resolveException(HttpServletRequest req, Exception ex) {
        LOG.error("View Error in " + req.getRequestURI(), ex);
        ex.printStackTrace();
        return new ErrorInfo(req.getRequestURL().toString(), ex);
    }
}