package com.lividia.lividiax101.model;

import java.util.ArrayList;

/**
 * Created by user2 on 18/08/2015.
 */
public class Language {

    public ArrayList<Word> words;
    public ArrayList<Letter> letters;
    public String langName;
    public String langAlias;

    String alphabet = "abcdefghijklmnopqrstuvwxyz";

    public Language(String langName, String langAlias) {
        this.langName = langName;
        this.langAlias = langAlias;
        words = new ArrayList<>();
        letters = new ArrayList<>();
    }

    //public void populateWithWords(ArrayList<Word> words){
    //    this.words = words;
    //}

    public void populateWithLetters(Letter ... ls){
        for (Letter l : ls)
            letters.add(l);
    }
    public void populateWithWords(Word ... ws){
        for(Word w : ws)
            words.add(w);
    }

    public Letter getLetter(char character){
        int letterIndex = alphabet.indexOf(character);
        return letters.get(letterIndex);
    }
}
