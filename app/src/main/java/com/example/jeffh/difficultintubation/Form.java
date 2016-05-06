package com.example.jeffh.difficultintubation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by jeffh on 2016-05-01.
 */
public class Form {

    private static int ENTRY_COUNT = 27;
    private ArrayList<EntryEncodingTransformer> entryEncodingTransformers;
    private LinkedList<EntryDivider> dividers;

    public Form() {
        entryEncodingTransformers = new ArrayList<>(ENTRY_COUNT);
        entryEncodingTransformers.add(new WrittenEntry("Date"));
        entryEncodingTransformers.add(new WrittenEntry("Institution"));
        entryEncodingTransformers.add(new MultipleChoiceEntry("Department",
                new String[] {"Anaesthesia", "Emergency", "Intensive", "Other"}));
        entryEncodingTransformers.add(new WrittenEntry("Name"));
        entryEncodingTransformers.add(new WrittenEntry("Telephone Number"));
        entryEncodingTransformers.add(new WrittenEntry("Email"));
        entryEncodingTransformers.add(new WrittenEntry("Indication for Airway Management"));
        entryEncodingTransformers.add(new MultipleChoiceEntry("Mallampati Score",
                new String[] {"I,", "II", "III", "IV"}));
        entryEncodingTransformers.add(new YesNoEntry("Thyromental Distance < 6 cm"));
        entryEncodingTransformers.add(new YesNoEntry("Mouth Opening < 3 Fingerbreadths?"));
        entryEncodingTransformers.add(new YesNoEntry("Large Tongue?"));
        entryEncodingTransformers.add(new YesNoEntry("Neck Mobility Reduced?"));
        entryEncodingTransformers.add(new YesNoEntry("Radiation/Surgery to Head or Neck?"));
        entryEncodingTransformers.add(new YesNoEntry("Congenital Abnormality?"));
        entryEncodingTransformers.add(new YesNoEntry("Increased Aspiration Risk?"));
        entryEncodingTransformers.add(new YesNoEntry("Raised BMI?"));
        entryEncodingTransformers.add(new MultipleChoiceEntry("Bag Mask Ventilation",
                new String[]{"Easy", "With Oropharyngeal Airway", "With Nasal Airway",
                "Two Person Technique", "Unsuccessful"}));
        entryEncodingTransformers.add(new WrittenEntry("Device Used"));
        entryEncodingTransformers.add(new WrittenEntry("Comments"));
        entryEncodingTransformers.add(new MultipleChoiceEntry("Successful?", new String[] {"Success", "Unsuccessful"}));
        entryEncodingTransformers.add(new WrittenEntry("Device Used"));
        entryEncodingTransformers.add(new WrittenEntry("Comments"));
        entryEncodingTransformers.add(new MultipleChoiceEntry("Successful?", new String[] {"Success", "Unsuccessful"}));
        entryEncodingTransformers.add(new WrittenEntry("Device Used"));
        entryEncodingTransformers.add(new WrittenEntry("Comments"));
        entryEncodingTransformers.add(new MultipleChoiceEntry("Successful?", new String[] {"Success", "Unsuccessful"}));
        entryEncodingTransformers.add(new WrittenEntry("Further Comments"));

        dividers = new LinkedList<>();
        dividers.add(new EntryDivider("Difficult Intubation Information\n", 0));
        dividers.add(new EntryDivider("\nContact\n", 3));
        dividers.add(new EntryDivider("\nPre-OP Physical Characteristics\n", 7));
        dividers.add(new EntryDivider("\nSequence of events\n\nAttempt 1\n", 17));
        dividers.add(new EntryDivider("\nAttempt 2\n", 20));
        dividers.add(new EntryDivider("\nAttempt 3\n", 23));
        dividers.add(new EntryDivider("\n", 26));
    }

    public String getReadableString(List<String> entryEncodings) throws InvalidEncodingCountException, InvalidEncodingException{
        if (entryEncodings.size() != ENTRY_COUNT) {
            throw new InvalidEncodingCountException();
        }
        String readableString = "";
        for (int i = 0; i < entryEncodingTransformers.size(); i++) {
            if (dividers.peek() != null) {
                if (dividers.peek().getPosition() == i) {
                    readableString += dividers.poll().getText() + "\n";
                }
            }
            String readableAnswer = "";
            EntryEncodingTransformer transformation = entryEncodingTransformers.get(i);
            try {
                readableAnswer = transformation.getReadableAnswer(entryEncodings.get(i));
            } catch (InvalidEncodingException exception) {
                throw exception;
            } finally {
                readableString += readableAnswer + "\n";
            }
        }
        return readableString;
    }
}
