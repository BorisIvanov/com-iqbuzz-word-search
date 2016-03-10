package com.iqbuzz.word.search;

import sun.util.logging.PlatformLogger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Logger;

public class WordGenerator {

    private final static Logger log = Logger.getLogger(WordGenerator.class.getName());


    public static final int MAX_CHAR_IN_WORD = 16;
    public static final int MIN_CHAR_IN_WORD = 2;

    public static String generate(String fileName) {
        int startChar = 65;
        int deltaChar = 90 - startChar;
        int wordInBuffer = 2000;
        int wordCounterForBuffer = 0;
        int wordMaxCount = 1_000_000;

        String result = "";

        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(fileName));

            Random random = new Random();
            for (int i = 0; i < wordMaxCount; i++) {
                int wordLength = MIN_CHAR_IN_WORD + random.nextInt(MAX_CHAR_IN_WORD - MIN_CHAR_IN_WORD);

                char[] word;
                if (i < wordMaxCount - 1) {
                    word = new char[wordLength + 1];
                    word[wordLength] = 32;
                } else {
                    word = new char[wordLength];
                }
                for (int c = 0; c < wordLength; c++) {
                    word[c] = (char) (startChar + random.nextInt(deltaChar));
                }

                bufferedWriter.write(word, 0, word.length);
                wordCounterForBuffer++;
                if (wordCounterForBuffer > wordInBuffer) {
                    bufferedWriter.flush();
                    wordCounterForBuffer = 0;
                }
                /*
                if (i == 0 || i == 500000 || i == wordMaxCount - 1) {
                    System.out.println(new String(word));
                    result = new String(word);
                }*/
                if (i == 500_000) {
                    result = new String(word).trim();
                }
            }
            bufferedWriter.flush();
        } catch (IOException e) {
            log.throwing("WordGenerator", "generate", e);
        } finally {
            try {
                assert bufferedWriter != null;
                bufferedWriter.close();
            } catch (IOException e) {
                log.throwing("WordGenerator", "generate", e);
            }
        }
        return result;
    }

}
