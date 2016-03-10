package com.iqbuzz.word.search;

import java.nio.CharBuffer;
import java.util.Arrays;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordFinderItemResult {
    private CharBuffer previousWord = CharBuffer.allocate(WordGenerator.MAX_CHAR_IN_WORD);
    private int previousWordStart = -1;
    private CharBuffer currentWord = CharBuffer.allocate(WordGenerator.MAX_CHAR_IN_WORD);
    private int currentWordStart = -1;
    private CharBuffer nextWord = CharBuffer.allocate(WordGenerator.MAX_CHAR_IN_WORD);
    private int nextWordStart = -1;

    public CharBuffer getPreviousWord() {
        return previousWord;
    }

    public void setPreviousWord(CharBuffer previousWord) {
        this.previousWord = previousWord;
    }

    public int getPreviousWordStart() {
        return previousWordStart;
    }

    public void setPreviousWordStart(int previousWordStart) {
        this.previousWordStart = previousWordStart;
    }

    public CharBuffer getCurrentWord() {
        return currentWord;
    }

    public void setCurrentWord(CharBuffer currentWord) {
        this.currentWord = currentWord;
    }

    public int getCurrentWordStart() {
        return currentWordStart;
    }

    public void setCurrentWordStart(int currentWordStart) {
        this.currentWordStart = currentWordStart;
    }

    public CharBuffer getNextWord() {
        return nextWord;
    }

    public void setNextWord(CharBuffer nextWord) {
        this.nextWord = nextWord;
    }

    public int getNextWordStart() {
        return nextWordStart;
    }

    public void setNextWordStart(int nextWordStart) {
        this.nextWordStart = nextWordStart;
    }

    private CharBuffer build(CharBuffer charBuffer){
        return CharBuffer.wrap(
                charBuffer.chars().filter(i -> i > 0).mapToObj(i -> (char) i)
                        .collect(Collector.of(StringBuilder::new,
                                (stringBuilder, str) -> stringBuilder.append(str),
                                StringBuilder::append,
                                StringBuilder::toString))
        );
    }

    public void build() {
        setPreviousWord(build(getPreviousWord()));
        setCurrentWord(build(getCurrentWord()));
        setNextWord(build(getNextWord()));
    }


    @Override
    public String toString() {
        return "{ " +
                " [ PreviousWord ] : [ " +
                this.getPreviousWord().toString() +
                " ] " +
                "[ PreviousWord Pos ] = [ " +
                this.getPreviousWordStart() +
                "]"+
                " [ CurrentWord ] : [ " +
                this.getCurrentWord().toString() +
                " ] " +
                "[ CurrentWord Pos ] = [ " +
                this.getCurrentWordStart() +
                "]"+
                " [ NextWord ] : [ " +
                this.getNextWord().toString() +
                " ] " +
                "[ NextWord Pos ] = [ " +
                this.getNextWordStart() +
                "]"+
                " } ";
    }
}
