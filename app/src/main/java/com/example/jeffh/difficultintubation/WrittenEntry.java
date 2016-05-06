package com.example.jeffh.difficultintubation;

import android.util.Log;

/**
 * Created by jeffh on 2016-05-01.
 */
public class WrittenEntry implements EntryEncodingTransformer {
    String question;
    public WrittenEntry(String entryQuestion) {
        question = entryQuestion;
    }
    public boolean isEncodingValid(String encoding) {
        return encoding != null;
    }
    public String getReadableAnswer(String encoding) throws InvalidEncodingException {
        if (!isEncodingValid(encoding)) {
            throw new InvalidEncodingException();
        } else {
            return question + " : " + encoding;
        }
    }
    public String getQuestion() {
        return question;
    }
}
