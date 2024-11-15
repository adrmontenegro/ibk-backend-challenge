package com.adrdev.starters.commons.logging.utils;


import com.adrdev.starters.commons.logging.LoggingAspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;

@Component
public class LoggingService {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);


    public void logRequestReceived(String requestId, String className, String methodName, Object[] args) {
        logger.info("{} | {} | {}: Request received for {} - Arguments: {}",
                    LocalDateTime.now(), requestId, className, methodName, Arrays.toString(args));
    }

    public void logServiceProcessing(String requestId, String className, String methodName, Object[] args) {
        logger.info("{} | {} | {}: Processing request in {} - Arguments: {}",
                    LocalDateTime.now(), requestId, className, methodName, Arrays.toString(args));
    }

    public void logRepositoryQueryStart(String requestId, String className, String methodName, String queryType) {
        logger.info("{} | {} | {}: Querying database with {} - Query Type: {}",
                    LocalDateTime.now(), requestId, className, methodName, queryType);
    }

    public void logRepositoryQueryEnd(String requestId, String className, String methodName, Object result, long duration) {
        String resultLog = result != null ? result.toString() : "null";
        String resultSize = result instanceof Collection<?> ? "Size: " + ((Collection<?>) result).size() : "N/A";
        logger.info("{} | {} | {}: Query finished for {} - Result: {} - Duration: {} ms - {}",
                    LocalDateTime.now(), requestId, className, methodName, resultLog, duration, resultSize);
    }

    public void logServiceEnd(String requestId, String className, String methodName, long duration) {
        logger.info("{} | {} | {}: Finished processing {} - Duration: {} ms",
                    LocalDateTime.now(), requestId, className, methodName, duration);
    }

    public void logControllerReturn(String requestId, String className, String methodName, int statusCode, long duration) {
        logger.info("{} | {} | {}: Returning response from {} - HTTP Status: {} - Duration: {} ms",
                    LocalDateTime.now(), requestId, className, methodName, statusCode, duration);
    }
}
