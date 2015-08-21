package com.lividia.lividiax101;

import android.app.Application;
import android.content.res.Configuration;

import com.lividia.lividiax101.model.MyModel;

/**
 * Created by user2 on 18/08/2015.
 */
public class MyApplication extends Application {

    public MyModel myModel;
    private static MyApplication singleton;

    public MyApplication getInstance() {
        return singleton;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
        myModel = new MyModel();
        myModel.create();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

}
