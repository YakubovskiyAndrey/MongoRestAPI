package ua.yakubovskiy.MongoRestAPI.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.EXPECTATION_FAILED)
public class FailedDownloadException extends RuntimeException {
    public FailedDownloadException(String message) {
        super(message);
    }
}
