// TextRequest.java
package com.example.gomaa.Dto;

public class TextRequest {
    private String text;

    public TextRequest() {}

    public TextRequest(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
