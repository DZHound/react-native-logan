package com.example;

import android.app.Application;

import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;
import com.reactnative.logan.LoganPackage;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class MainApplication extends Application implements ReactApplication {

    private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
        @Override
        public boolean getUseDeveloperSupport() {
            return BuildConfig.DEBUG;
        }

        @Override
        protected List<ReactPackage> getPackages() {
            return Arrays.<ReactPackage>asList(
                    new MainReactPackage(),
                    new LoganPackage()
            );
        }

        @Override
        protected String getJSMainModuleName() {
            return "example/index";
        }
    };

    @Override
    public ReactNativeHost getReactNativeHost() {
        return mReactNativeHost;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("---------" + getApplicationContext().getFilesDir().getAbsolutePath());
        System.out.println("---------" + getApplicationContext().getExternalFilesDir(null).getAbsolutePath()
                + File.separator + "rnlogan");
        SoLoader.init(this, /* native exopackage */ false);
    }
}
