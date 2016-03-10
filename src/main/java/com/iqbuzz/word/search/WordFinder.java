package com.iqbuzz.word.search;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.logging.Logger;

public class WordFinder {

    private final static Logger log = Logger.getLogger(WordFinder.class.getName());
    private char[] searchingChars;

    public WordFinderResult find(String word, String fileName) {

        WordFinderResult result = new WordFinderResult();
        WordFinderItemResult item = new WordFinderItemResult();
        WordFinderItemResult itemPrev = new WordFinderItemResult();

        searchingChars = word.toCharArray();

        int charInCurrentWord = 0;
        int pos = 0;
        boolean saveNextWord = false;

        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(fileName));
            int c;


            while ((c = bufferedReader.read()) != -1) {
                char currentChar = (char) c;

                //следующее слово
                if (currentChar == 32) {
                    saveNextWord = false;

                    if (checkValidWord(item)) {
                        item.setCurrentWordStart(pos - charInCurrentWord + 1);
                        result.add(item);
                        itemPrev = item;
                        item = new WordFinderItemResult();
                        saveNextWord = true;
                    } else {
                        item.setPreviousWordStart(pos - charInCurrentWord + 1);
                        item.setPreviousWord(item.getCurrentWord());
                        item.setCurrentWordStart(pos);
                        item.setCurrentWord(CharBuffer.allocate(WordGenerator.MAX_CHAR_IN_WORD));
                    }
                    charInCurrentWord = 0;
                } else {
                    item.getCurrentWord().put(charInCurrentWord, currentChar);
                    if (saveNextWord) {
                        if (charInCurrentWord == 0) {
                            itemPrev.setNextWordStart(pos + 1);
                        }
                        itemPrev.getNextWord().put(charInCurrentWord, currentChar);
                    }
                    charInCurrentWord++;
                }
                pos++;
            }

            if (checkValidWord(item)) {
                item.setCurrentWordStart(pos - charInCurrentWord + 1);
                result.add(item);
            }

        } catch (IOException e) {
            log.throwing("WordFinder", "find", e);
        } finally {
            try {
                assert bufferedReader != null;
                bufferedReader.close();
            } catch (IOException e) {
                log.throwing("WordFinder", "find", e);
            }
        }
        return result;
    }


    private boolean checkValidWord(WordFinderItemResult item) {
        int charCount = 0;
        boolean validWord = true;
        for (; charCount < WordGenerator.MAX_CHAR_IN_WORD; charCount++) {
            if (searchingChars.length > charCount) {
                validWord = item.getCurrentWord().charAt(charCount) == searchingChars[charCount];
            } else {
                if (charCount < WordGenerator.MAX_CHAR_IN_WORD) {
                    validWord = item.getCurrentWord().charAt(charCount) == 0;
                }
                break;
            }
            if (!validWord) {
                break;
            }
        }

        if (validWord && charCount < WordGenerator.MAX_CHAR_IN_WORD - 1) {
            validWord = item.getCurrentWord().charAt(charCount + 1) == 0;
        }
        return validWord;
    }

}
