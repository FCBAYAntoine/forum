package com.antoine.forum.exception;

import com.antoine.forum.common.AppResult;

public class ApplicationException extends RuntimeException{

    protected AppResult errorResult;

    public AppResult getErrorResult() {
        return errorResult;
    }

    public  ApplicationException(AppResult errorResult){
        super(errorResult.getMessage());
        this.errorResult = errorResult;
    }

    public ApplicationException(String message) {
        super();
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationException(Throwable cause) {
        super(cause);
    }
}
