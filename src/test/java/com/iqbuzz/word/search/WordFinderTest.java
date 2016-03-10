package com.iqbuzz.word.search;


import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class WordFinderTest {

    @Test
    public void findReal(){
        String fileName = "test-real.txt";
        String findWord = WordGenerator.generate(fileName);
        System.out.println(findWord);
        WordFinderResult result = new WordFinder().find(findWord, fileName);
        result.build();
        System.out.println(result.toString());
    }

    @Test
    public void find() {
        WordFinderResult result = new WordFinder().find("A",
                getClass().getClassLoader().getResource("test-1.txt").getFile());
        result.build();
        WordFinderItemResult result0 = result.get(0);
        WordFinderItemResult result1 = result.get(1);
        WordFinderItemResult result2 = result.get(2);

        assertTrue(result0.getPreviousWordStart() == -1);
        assertTrue(result0.getCurrentWordStart() == 1);
        assertTrue(result0.getCurrentWord().toString().equals("A"));
        assertTrue(result0.getNextWordStart() == 3);
        assertTrue(result0.getNextWord().toString().equals("B"));

        assertTrue(result1.getPreviousWordStart() == 3);
        assertTrue(result1.getPreviousWord().toString().equals("B"));
        assertTrue(result1.getCurrentWordStart() == 5);
        assertTrue(result1.getCurrentWord().toString().equals("A"));
        assertTrue(result1.getNextWordStart() == 7);
        assertTrue(result1.getNextWord().toString().equals("B"));

        assertTrue(result2.getPreviousWordStart() == 7);
        assertTrue(result2.getPreviousWord().toString().equals("B"));
        assertTrue(result2.getCurrentWordStart() == 9);
        assertTrue(result2.getCurrentWord().toString().equals("A"));
        assertTrue(result2.getNextWordStart() == -1);

        //System.out.println(result.toString());
    }

    @Test
    public void find2() {
        WordFinderResult result = new WordFinder().find("AA",
                getClass().getClassLoader().getResource("test-2.txt").getFile());
        result.build();

        //System.out.println(result.toString());

        WordFinderItemResult result0 = result.get(0);
        WordFinderItemResult result1 = result.get(1);
        WordFinderItemResult result2 = result.get(2);

        assertTrue(result0.getPreviousWordStart() == -1);
        assertTrue(result0.getCurrentWordStart() == 1);
        assertTrue(result0.getCurrentWord().toString().equals("AA"));
        assertTrue(result0.getNextWordStart() == 4);
        assertTrue(result0.getNextWord().toString().equals("BB"));

        assertTrue(result1.getPreviousWordStart() == 12);
        assertTrue(result1.getPreviousWord().toString().equals("A"));
        assertTrue(result1.getCurrentWordStart() == 14);
        assertTrue(result1.getCurrentWord().toString().equals("AA"));
        assertTrue(result1.getNextWordStart() == 17);
        assertTrue(result1.getNextWord().toString().equals("AAA"));

        assertTrue(result2.getPreviousWordStart() == 21);
        assertTrue(result2.getPreviousWord().toString().equals("BB"));
        assertTrue(result2.getCurrentWordStart() == 24);
        assertTrue(result2.getCurrentWord().toString().equals("AA"));
        assertTrue(result2.getNextWordStart() == -1);
    }

}
