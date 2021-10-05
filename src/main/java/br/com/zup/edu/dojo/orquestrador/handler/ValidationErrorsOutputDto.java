package br.com.zup.edu.dojo.orquestrador.handler;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorsOutputDto {
    private List<String> globalErrorMessages = new ArrayList<>();
    private List<Error> fieldErrors = new ArrayList<>();

    public ValidationErrorsOutputDto() {
    }

    public ValidationErrorsOutputDto(String message) {
        this.globalErrorMessages.add(message);
    }

    public void addError(String message) {
        globalErrorMessages.add(message);
    }

    public void addFieldError(String field, String message) {
        Error fieldError = new Error(field, message);
        fieldErrors.add(fieldError);
    }

    public List<String> getGlobalErrorMessages() {
        return globalErrorMessages;
    }

    public List<Error> getErrors() {
        return fieldErrors;
    }

    public int getNumberOfErrors() {
        return globalErrorMessages.size() + fieldErrors.size();
    }
}
