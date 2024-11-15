package com.adrdev.starters.commons.logging.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

@Component
public class QueryTypeUtil {

    public String determineQueryType(ProceedingJoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        if (methodName.startsWith("find")) {
            return "SELECT";
        } else if (methodName.startsWith("save") || methodName.startsWith("insert")) {
            return "INSERT";
        } else if (methodName.startsWith("update")) {
            return "UPDATE";
        } else if (methodName.startsWith("delete")) {
            return "DELETE";
        }
        return "UNKNOWN";
    }
}
