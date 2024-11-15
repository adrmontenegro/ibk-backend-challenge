package com.adrdev.starters.commons.logging;

import com.adrdev.starters.commons.logging.model.constants.LayerType;
import com.adrdev.starters.commons.logging.utils.LoggingService;
import com.adrdev.starters.commons.logging.utils.QueryTypeUtil;
import com.adrdev.starters.commons.logging.utils.RequestIdUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LoggingAspect {

    private final RequestIdUtil requestIdUtil;
    private final LoggingService loggingService;
    private final QueryTypeUtil queryTypeUtil;

    public LoggingAspect(RequestIdUtil requestIdUtil, LoggingService loggingService, QueryTypeUtil queryTypeUtil) {
        this.requestIdUtil = requestIdUtil;
        this.loggingService = loggingService;
        this.queryTypeUtil = queryTypeUtil;
    }
    @Around("@within(org.springframework.web.bind.annotation.RestController)")
    public Object logRestController(ProceedingJoinPoint joinPoint) throws Throwable {
        return logRequestFlow(joinPoint, LayerType.REST_CONTROLLER);
    }

    @Around("@within(org.springframework.stereotype.Service)")
    public Object logServiceLayer(ProceedingJoinPoint joinPoint) throws Throwable {
        return logRequestFlow(joinPoint, LayerType.SERVICE);
    }

    @Around("@within(org.springframework.stereotype.Repository)")
    public Object logRepositoryLayer(ProceedingJoinPoint joinPoint) throws Throwable {
        return logRequestFlow(joinPoint, LayerType.REPOSITORY);
    }


    private Object logRequestFlow(ProceedingJoinPoint joinPoint, LayerType layer) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String className = signature.getDeclaringType().getSimpleName();
        String methodName = signature.getName();

        String requestId = requestIdUtil.getRequestIdFromContext();
        long startTime = System.currentTimeMillis();

        if (LayerType.REST_CONTROLLER.equals(layer)) {
            loggingService.logRequestReceived(requestId, className, methodName, joinPoint.getArgs());
        }

        if (LayerType.SERVICE.equals(layer)) {
            loggingService.logServiceProcessing(requestId, className, methodName, joinPoint.getArgs());
        }

        if (LayerType.REPOSITORY.equals(layer)) {
            String queryType = queryTypeUtil.determineQueryType(joinPoint);
            loggingService.logRepositoryQueryStart(requestId, className, methodName, queryType);
        }

        Object result = joinPoint.proceed();

        if (LayerType.REPOSITORY.equals(layer)) {
            long duration = System.currentTimeMillis() - startTime;
            loggingService.logRepositoryQueryEnd(requestId, className, methodName, result, duration);
        }

        if (LayerType.SERVICE.equals(layer)) {
            long duration = System.currentTimeMillis() - startTime;
            loggingService.logServiceEnd(requestId, className, methodName, duration);
        }

        if (LayerType.SERVICE.equals(layer)) {
            long duration = System.currentTimeMillis() - startTime;
            loggingService.logControllerReturn(requestId, className, methodName, 200, duration);
        }

        MDC.clear();
        return result;
    }
}


