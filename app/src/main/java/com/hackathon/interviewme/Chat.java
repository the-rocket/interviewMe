package com.hackathon.interviewme;

/**
 * Created by yernar on 08/10/2017.
 */

public class Chat {
    private String text;
    private boolean button_flag = false;
    Chat(String text, boolean flag) {
        this.text = text;
        this.button_flag = flag;
    }

    public String getText() {
        return text;
    }
    public boolean getButtonFlag() {
        return button_flag;
    }
}
