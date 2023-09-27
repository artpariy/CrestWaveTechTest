package ru.pariy.webmodule.service;

public class NegativeResponse {
    private final Integer step;
    private final String errorMessage;

    public NegativeResponse(Integer step, String errorMessage) {
        this.step = step;
        this.errorMessage = errorMessage;
    }

    public Integer getStep() {
        return step;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
