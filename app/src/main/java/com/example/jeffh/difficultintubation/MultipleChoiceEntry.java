package com.example.jeffh.difficultintubation;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jeffh on 2016-05-01.
 */
public class MultipleChoiceEntry implements EntryEncodingTransformer {

    String question;
    String[] answers;
    Map<String, String> encodingToAnswersMap;

    public MultipleChoiceEntry(String multipleChoiceQuestion, String[] multipleChoiceAnswers) {
        question = multipleChoiceQuestion;
        answers = multipleChoiceAnswers;
        encodingToAnswersMap = new HashMap<>(multipleChoiceAnswers.length + 1);
        encodingToAnswersMap.put("-1", "Not Available");
        for (int i = 0; i < multipleChoiceAnswers.length; i++) {
            encodingToAnswersMap.put(String.valueOf(i), multipleChoiceAnswers[i]);
        }
    }

    public boolean isEncodingValid(String encoding) {
        return (Integer.parseInt(encoding) >= -1) && (Integer.parseInt(encoding) < answers.length);
    }

    public String getReadableAnswer(String encoding) throws InvalidEncodingException {
        if (!isEncodingValid(encoding)) {
            throw new InvalidEncodingException();
        }
        return question + " : " + encodingToAnswersMap.get(encoding);
    }

    public String getQuestion() {
        return question;
    }
}