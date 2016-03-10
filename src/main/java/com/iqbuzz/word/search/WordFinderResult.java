package com.iqbuzz.word.search;

import java.util.ArrayList;

public class WordFinderResult extends ArrayList<WordFinderItemResult>{

    public void build(){
        this.forEach(WordFinderItemResult::build);
    }


    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        result.append("[\n");
        for(WordFinderItemResult item : this){
            result.append(item.toString());
            result.append("\n");
        }
        result.append("]");
        return result.toString();
    }

}
