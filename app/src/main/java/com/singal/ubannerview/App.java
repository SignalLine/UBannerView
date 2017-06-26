package com.singal.ubannerview;

import android.app.Application;

/**
 * App
 *
 * Created by li on 2017/6/14.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //ANR检测
        // new ANRWatchDog().start();
    }
}
