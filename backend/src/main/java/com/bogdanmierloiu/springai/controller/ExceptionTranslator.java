package com.bogdanmierloiu.springai.controller;

import org.springframework.ai.openai.api.common.OpenAiApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionTranslator extends ResponseEntityExceptionHandler {
    public static final String OPEN_AI_CLIENT_RAISED_EXCEPTION = "Open AI client raised exception";

    @ExceptionHandler(OpenAiApiException.class)
    ProblemDetail handleOpenAiHttpException(OpenAiApiException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        problemDetail.setTitle(OPEN_AI_CLIENT_RAISED_EXCEPTION);
        return problemDetail;
    }
}