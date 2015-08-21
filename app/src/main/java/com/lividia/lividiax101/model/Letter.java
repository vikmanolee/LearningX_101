package com.lividia.lividiax101.model;

import android.util.Log;

/**
 * Created by user2 on 18/08/2015.
 */
public class Letter extends Phoneme{
    public int visualRepresentation;

    public Letter(String verbalRepresentation, int oralRepresentation,
                  String existsInLanguage, int visualRepresentation) {
        super(verbalRepresentation, oralRepresentation, existsInLanguage);
        this.visualRepresentation = visualRepresentation;
    }
}
