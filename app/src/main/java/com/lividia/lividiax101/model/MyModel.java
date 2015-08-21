package com.lividia.lividiax101.model;

import com.lividia.lividiax101.R;

import java.util.ArrayList;

/**
 * Created by user2 on 18/08/2015.
 */
public class MyModel {

    public Language englishLang;

    public MyModel(){
        super();
        englishLang = new Language("english", "en-GB");
    }

    public void create(){

        Letter letterA = new Letter("a", 0, "english", R.drawable.letter_a);
        Letter letterB = new Letter("b", 0, "english", R.drawable.letter_b);
        Letter letterC = new Letter("c", 0, "english", R.drawable.letter_c);
        Letter letterD = new Letter("d", 0, "english", R.drawable.letter_d);
        Letter letterE = new Letter("e", 0, "english", R.drawable.letter_e);
        Letter letterF = new Letter("f", 0, "english", R.drawable.letter_f);
        Letter letterG = new Letter("g", 0, "english", R.drawable.letter_g);
        Letter letterH = new Letter("h", 0, "english", R.drawable.letter_h);
        Letter letterI = new Letter("i", 0, "english", R.drawable.letter_i);
        Letter letterJ = new Letter("j", 0, "english", R.drawable.letter_j);
        Letter letterK = new Letter("k", 0, "english", R.drawable.letter_k);
        Letter letterL = new Letter("l", 0, "english", R.drawable.letter_l);
        Letter letterM = new Letter("m", 0, "english", R.drawable.letter_m);
        Letter letterN = new Letter("n", 0, "english", R.drawable.letter_n);
        Letter letterO = new Letter("o", 0, "english", R.drawable.letter_o);
        Letter letterP = new Letter("p", 0, "english", R.drawable.letter_p);
        Letter letterQ = new Letter("q", 0, "english", R.drawable.letter_q);
        Letter letterR = new Letter("r", 0, "english", R.drawable.letter_r);
        Letter letterS = new Letter("s", 0, "english", R.drawable.letter_s);
        Letter letterT = new Letter("t", 0, "english", R.drawable.letter_t);
        Letter letterU = new Letter("u", 0, "english", R.drawable.letter_u);
        Letter letterV = new Letter("v", 0, "english", R.drawable.letter_v);
        Letter letterW = new Letter("w", 0, "english", R.drawable.letter_w);
        Letter letterX = new Letter("x", 0, "english", R.drawable.letter_x);
        Letter letterY = new Letter("y", 0, "english", R.drawable.letter_y);
        Letter letterZ = new Letter("z", 0, "english", R.drawable.letter_z);
        englishLang.populateWithLetters(letterA, letterB, letterC, letterD, letterE, letterF,
                letterG, letterH, letterI, letterJ, letterK, letterL, letterM, letterN, letterO,
                letterP, letterQ, letterR, letterS, letterT, letterU, letterV, letterW, letterX,
                letterY, letterZ);

        // make words and append
        Word badger = new Word("badger", R.raw.en_us_badger, "english", R.drawable.badger);
        Word bear = new Word("bear", R.raw.en_us_bear, "english", R.drawable.bear);
        Word cow = new Word("cow", R.raw.en_us_cow, "english", R.drawable.cow);
        Word deer = new Word("deer", R.raw.en_us_deer, "english", R.drawable.deer);
        Word fox = new Word("fox", R.raw.en_us_fox, "english", R.drawable.fox);
        Word giraffe = new Word("giraffe", R.raw.en_us_giraffe, "english", R.drawable.giraffe);
        Word hedghog = new Word("hedgehog", R.raw.en_uk_hedgehog, "english", R.drawable.hedghog);
        Word lion = new Word("lion", R.raw.en_us_lion, "english", R.drawable.lion);
        Word owl = new Word("owl", R.raw.en_us_owl, "english", R.drawable.owl);
        Word sheep = new Word("sheep", R.raw.en_us_sheep, "english", R.drawable.sheep);
        Word turtle = new Word("turtle", R.raw.en_us_turtle, "english", R.drawable.turtle);
        Word whale = new Word("whale", R.raw.en_us_whale, "english", R.drawable.whale);

        //englishLang.populateWithWords(badger, bear, cow, deer, fox, giraffe,
        //        hedghog, lion, owl, sheep, turtle, whale);
        englishLang.populateWithWords(cow, deer, fox, lion, owl, sheep);

        for (Word word : englishLang.words) {
            word.populateWithLetters(englishLang);
        }


    }
}
