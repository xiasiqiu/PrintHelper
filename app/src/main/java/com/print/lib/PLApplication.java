package com.print.lib;

import android.app.Application;

import com.print.printlib.PrintPlan;

/**
 * Created by Administrator on 2018/3/24.
 */

public class PLApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

    }

    public void onLowMemory() {
        System.gc();
        System.runFinalization();
        System.gc();
        super.onLowMemory();
    }

}
