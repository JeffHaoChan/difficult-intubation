package com.example.jeffh.difficultintubation;

/**
 * Created by jeffh on 2016-05-01.
 */
public interface EntryEncodingTransformer {
    boolean isEncodingValid(String encoding);
    String getReadableAnswer(String encoding) throws InvalidEncodingException;
    String getQuestion();
}
