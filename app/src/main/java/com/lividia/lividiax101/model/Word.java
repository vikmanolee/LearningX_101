package com.lividia.lividiax101.model;

import java.util.ArrayList;

/**
 * Created by user2 on 18/08/2015.
 */
public class Word extends Phoneme{

    public ArrayList<Letter> letters;
    public int visualRepresentation;

    public Word(String verbalRepresentation, int oralRepresentation, String existsInLanguage,
                                                int visualRepresentation) {
        super(verbalRepresentation, oralRepresentation, existsInLanguage);
        this.visualRepresentation = visualRepresentation;
        letters = new ArrayList<>();
    }

    public void populateWithLetters(Language language){
        Letter l;
        char[] lettersString = verbalRepresentation.toCharArray();
        for (char c : lettersString) {
            l = language.getLetter(c);
            letters.add(l);
        }
    }

//    public void populateWithLetters(ArrayList<Letter> letters){
//        this.letters = letters;
//    }

    public void populateWithLetters(Letter ... ls){
        for(Letter l : ls)
            letters.add(l);
    }
}
