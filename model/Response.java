package com.example.TestApp.model;

public class Response {
    private int questionId;
    private String response;


    public Response() {}

    public Response(int questionId, String response) {
        this.questionId = questionId;
        this.response = response;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
