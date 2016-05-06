package com.example.jeffh.difficultintubation;

/**
 * Created by jeffh on 2016-05-01.
 */
public class EntryDivider {
    private String dividerText;
    private int dividerPosition;
    public EntryDivider(String text, int position) {
        dividerText = text;
        dividerPosition = position;
    }
    public String getText() {
        return dividerText;
    }
    public int getPosition() {
        return dividerPosition;
    }
}
