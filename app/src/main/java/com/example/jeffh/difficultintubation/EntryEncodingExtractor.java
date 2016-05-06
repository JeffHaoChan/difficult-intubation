package com.example.jeffh.difficultintubation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by jeffh on 2016-04-28.
 */
public class EntryEncodingExtractor {
    public static List<String> getEntryEncodings(String barcodeString, char entryEncodingsDelimiter) {
        char[] barcodeCharArray = barcodeString.toCharArray();
        StringBuffer entryEncodingStringBuffer = new StringBuffer();
        List<String> entryEncodings = new LinkedList<>();
        for (int i = 0; i < barcodeCharArray.length; i++) {
            if (barcodeCharArray[i] == entryEncodingsDelimiter) {
                entryEncodings.add(new String(entryEncodingStringBuffer));
                entryEncodingStringBuffer.delete(0, entryEncodingStringBuffer.length());
            } else {
                entryEncodingStringBuffer.append(barcodeCharArray[i]);
            }
            if (i == barcodeCharArray.length - 1) {
                entryEncodings.add(new String(entryEncodingStringBuffer));
            }
        }
        return entryEncodings;
    }
}
