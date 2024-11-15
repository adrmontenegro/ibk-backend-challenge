package com.adrdev.starters.commons.logging.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.UUID;

@Component
public class RequestIdUtil {
    public String getRequestIdFromContext() {
        String requestId = MDC.get("request-id");
        if (requestId == null) {
            requestId = extractRequestIdFromHeader();
            MDC.put("request-id", requestId);
        }
        return requestId;
    }

    private String extractRequestIdFromHeader() {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getHeader("request-id") != null ? request.getHeader("request-id") : UUID.randomUUID().toString();
    }
}
