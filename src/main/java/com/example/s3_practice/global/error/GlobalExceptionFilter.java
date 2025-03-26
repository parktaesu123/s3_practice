package com.example.s3_practice.global.error;


import com.example.s3_practice.global.error.exception.DayException;
import com.example.s3_practice.global.error.exception.ErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class GlobalExceptionFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    )throws IOException {

        try {
            filterChain.doFilter(request,response);
        } catch (DayException e){
            ErrorCode errorCode = e.getErrorCode();
            writerErrorResponse(response, errorCode.getStatusCode(), ErrorResponse.of(errorCode, errorCode.getMessage()));
        } catch (Exception e){
            e.printStackTrace();
            writerErrorResponse(response, response.getStatus(), ErrorResponse.of(response.getStatus(),e.getMessage()));
        }
    }
    private void writerErrorResponse(HttpServletResponse response, int statusCode, ErrorResponse errorResponse) throws IOException{
        response.setStatus(statusCode);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        objectMapper.writeValue(response.getWriter(), errorResponse);
    }
}
