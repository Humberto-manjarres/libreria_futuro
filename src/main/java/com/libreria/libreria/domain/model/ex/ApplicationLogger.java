package com.libreria.libreria.domain.model.ex;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ApplicationLogger {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private static final String LOG_SEPARATOR = " -> id: ";
    private static final String ID_ERROR_SUFFIX = " ";
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHmmss");

    public String logError(String message) {
        String id = getErrorId();
        String msg = message != null ? message : "";
        String errorMessage = msg.concat(LOG_SEPARATOR).concat(id);
        logger.error(errorMessage);
        return id;
    }

    public String logError(String message, Throwable throwable) {
        String id = getErrorId();
        String msg = message != null ? message : "";
        String errorMessage = msg.concat(LOG_SEPARATOR).concat(id);
        logger.error(errorMessage, throwable);
        return id;
    }

    private String getErrorId() {
        return dateFormat.format(new Date()).concat(ID_ERROR_SUFFIX);
    }

}
