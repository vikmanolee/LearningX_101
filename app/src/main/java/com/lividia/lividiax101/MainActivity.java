package com.lividia.lividiax101;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

import android.annotation.TargetApi;
import android.location.GpsStatus;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lividia.lividiax101.model.Letter;
import com.lividia.lividiax101.model.MyModel;
import com.lividia.lividiax101.model.Word;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    /**
     * The model world
     */
    MyModel mModel;

    /**
     * Sound
     */
    private SoundPool soundPool;
    private int soundID;
    boolean plays = false, loaded = false;
    float actVolume, maxVolume, volume;
    AudioManager audioManager;
    private static int KEY_WORD_LETTER = 123456789;
    private static int KEY_SHUFFLED_LETTER = 1234567890;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyApplication mApp = (MyApplication)getApplicationContext();
        mModel = mApp.myModel;


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // AudioManager audio settings for adjusting the volume
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        actVolume = (float) audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        maxVolume = (float) audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        volume = actVolume / maxVolume;

        //Hardware buttons setting to adjust the media sound
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);

        // Load the sounds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            createSoundPoolWithBuilder();
        } else{
            createSoundPoolWithConstructor();
        }

        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                loaded = true;
                Log.d("SOUND", "Soundpool Loaded");
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            String title = mModel.englishLang.words.get(position).verbalRepresentation;
            int pictureId = mModel.englishLang.words.get(position).visualRepresentation;
            int soundId = mModel.englishLang.words.get(position).oralRepresentation;
            return PlaceholderFragment.newInstance(position, title, pictureId, soundId);
        }

        @Override
        public int getCount() {
            // Show total pages.
            return mModel.englishLang.words.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            Word w = mModel.englishLang.words.get(position);
            return w.verbalRepresentation.toUpperCase(l);
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_WORD_INDEX = "word_index";
        private static final String ARG_SECTION_TITLE = "section_title";
        private static final String ARG_SECTION_PICTURE_ID = "picture_id";
        private static final String ARG_SECTION_SOUND_ID = "sound_id";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int wordIndex, String title,
                                                      int pictureId, int soundId) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_WORD_INDEX, wordIndex);
            args.putString(ARG_SECTION_TITLE, title);
            args.putInt(ARG_SECTION_PICTURE_ID, pictureId);
            args.putInt(ARG_SECTION_SOUND_ID, soundId);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            ImageView imgView = (ImageView) rootView.findViewById(R.id.section_picture);
            imgView.setBackgroundResource(getArguments().getInt(ARG_SECTION_PICTURE_ID));

            Word activeWord = ((MainActivity) getActivity()).mModel.englishLang.words
                    .get(getArguments().getInt(ARG_WORD_INDEX));

            final LinearLayout letterSection = (LinearLayout) rootView.findViewById(R.id.section_word);
            for (Letter letter : activeWord.letters) {
                ImageView oneLetterImg = new ImageView(getActivity());
                oneLetterImg.setBackgroundResource(letter.visualRepresentation);
                oneLetterImg.setTag(letter.verbalRepresentation);
                oneLetterImg.setVisibility(View.INVISIBLE);
                letterSection.addView(oneLetterImg);
            }

            LinearLayout dashes = (LinearLayout) rootView.findViewById(R.id.section_dashes);
            for (int i = 0; i < activeWord.letters.size(); i++) {
                ImageView dashImgView = new ImageView(getActivity());
                dashImgView.setBackgroundResource(R.drawable.dash_);
                dashes.addView(dashImgView);
            }

            LinearLayout scrambleSection = (LinearLayout) rootView.findViewById(R.id.section_scramble);
            ArrayList<Letter> randomLetters = activeWord.letters;
            Collections.shuffle(randomLetters);

            for (Letter letter : randomLetters) {
                ImageView oneLetterImg = new ImageView(getActivity());
                oneLetterImg.setBackgroundResource(letter.visualRepresentation);
                oneLetterImg.setTag(letter.verbalRepresentation);
                oneLetterImg.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        ImageView ltr = (ImageView) rootView.findViewWithTag(view.getTag());
                        Log.v("IMAGE", view.getTag().toString());
                        ltr.setVisibility(View.VISIBLE);
                        view.setVisibility(View.INVISIBLE);
                    }
                });
                //oneLetterImg.setTag(KEY_SHUFFLED_LETTER, letter.verbalRepresentation);
                scrambleSection.addView(oneLetterImg);
            }
            MainActivity mActivity = (MainActivity) getActivity();
            mActivity.soundID = mActivity.soundPool.load(mActivity, activeWord.oralRepresentation, 1);
            return rootView;
        }

        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

        }
    }

    public void letterClicked(View v){

        ImageView letter = (ImageView)v.findViewWithTag(v.getTag());
        letter.setVisibility(View.VISIBLE);
        v.setVisibility(View.INVISIBLE);
    }

    public void playSound(View v) {

        //Log.d("SOUND", (String) v.getTag());
        //Word thisWord = mModel.englishLang.words.get((Integer) v.getTag());

//        PlaceholderFragment fragment = (PlaceholderFragment) mSectionsPagerAdapter.getItem(mViewPager.getCurrentItem());
//        int sID = fragment.getArguments().getInt(PlaceholderFragment.ARG_SECTION_SOUND_ID);
//        soundID = soundPool.load(this, sID, 1);

        soundPool.play(soundID, volume, volume, 1, 0, 1f);


    }

    public void playLoop(View v) {
        // Is the sound loaded does it already play?
        if (loaded && !plays) {

            // the sound will play for ever if we put the loop parameter -1
            soundPool.play(soundID, volume, volume, 1, -1, 1f);
            //counter = counter++;

            plays = true;
        }
    }

    public void pauseSound(View v) {
        if (plays) {
            soundPool.pause(soundID);
            //soundID = soundPool.load(this, R.raw.beep, counter);

            plays = false;
        }
    }

    public void stopSound(View v) {
        if (plays) {
            soundPool.stop(soundID);
            //soundID = soundPool.load(this, R.raw.beep, counter);

            plays = false;
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    protected void createSoundPoolWithBuilder(){
        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        soundPool = new SoundPool.Builder().setAudioAttributes(attributes).setMaxStreams(6).build();
    }

    @SuppressWarnings("deprecation")
    protected void createSoundPoolWithConstructor(){
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
    }


}
