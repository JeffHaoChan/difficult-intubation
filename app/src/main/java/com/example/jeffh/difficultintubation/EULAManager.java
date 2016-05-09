package com.example.jeffh.difficultintubation;

/**
 * Created by jeffh on 2016-05-08.
 */
public class EULAManager {

    private static boolean hasUserAgreed = false;

    public static boolean getHasUserAgreed() {
        return hasUserAgreed;
    }

    public static void setHasUserAgreed(boolean hasAgreed) {
        hasUserAgreed = hasAgreed;
    }
}
