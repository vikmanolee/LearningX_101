package com.lividia.lividiax101.model;

import android.util.Log;

/**
 * Created by vicky on 18/08/2015.
 */
public abstract class Phoneme {

    public String verbalRepresentation;
    public int oralRepresentation;
    public String existsInLanguage;

    public Phoneme(String verbalRepresentation, int oralRepresentation, String existsInLanguage) {
        this.verbalRepresentation = verbalRepresentation;
        this.oralRepresentation = oralRepresentation;
        this.existsInLanguage = existsInLanguage;
        Log.v("CREATIONS", "Phoneme created: " + this.verbalRepresentation);
    }
}
