package wyl.hikers.exception.handle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import wyl.hikers.exception.HikersException;
import wyl.hikers.model.RespBody;

@ControllerAdvice
@Slf4j
public class ExceptionHandle {
    @ExceptionHandler(HikersException.class)
    @ResponseBody
    public RespBody handle(HikersException e) {
        log.error(e.getInfo());
        return RespBody.error(e.getMessage());
    }
}
