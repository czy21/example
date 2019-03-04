package com.team.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    // 捕捉shiro的异常
    @ExceptionHandler(UnauthenticatedException.class)
    public ErrorModel handle401() {
        return new ErrorModel(ErrorCode.NO_AUTH.toString(), "您没有权限访问");
    }

    // 捕捉其他所有异常
    @ExceptionHandler({Exception.class})
    public ErrorModel globalException(HttpServletRequest request, Throwable ex) {
        return new ErrorModel(getStatus(request).toString(), ex.getMessage());
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }

}
