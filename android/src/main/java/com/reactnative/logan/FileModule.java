package com.reactnative.logan;

import android.support.v4.util.LruCache;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;

import java.io.File;

@ReactModule(name = FileModule.MODULE_NAME)
public class FileModule extends ReactContextBaseJavaModule {

    public static final String MODULE_NAME = "RFile";

    private static final LruCache<String, File> FILE_CACHE = new LruCache<>(5);

    public FileModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return MODULE_NAME;
    }

    private File getFile(String filePath) {
        File file = FILE_CACHE.get(filePath);
        if(file == null) {
            file = new File(filePath);
        }
        return file;
    }

    @ReactMethod
    public void getFileName(String filePath, Promise promise) {
        File file = getFile(filePath);
        promise.resolve(file.getName());
    }

    @ReactMethod
    public void exists(String filePath, Promise promise) {
        File file = getFile(filePath);
        promise.resolve(file.exists());
    }

    @ReactMethod
    public void delete(String filePath, Promise promise) {
        File file = getFile(filePath);
        promise.resolve(file.delete());
    }

    @ReactMethod
    public void canRead(String filePath, Promise promise) {
        File file = getFile(filePath);
        promise.resolve(file.canRead());
    }

    @ReactMethod
    public void canWrite(String filePath, Promise promise) {
        File file = getFile(filePath);
        promise.resolve(file.canWrite());
    }

    @ReactMethod
    public void lastModified(String filePath, Promise promise) {
        File file = getFile(filePath);
        promise.resolve(file.lastModified());
    }

    @ReactMethod
    public void setLastModified(String filePath, double time, Promise promise) {
        File file = getFile(filePath);
        promise.resolve(file.setLastModified((long) time));
    }

    @ReactMethod
    public void length(String filePath, Promise promise) {
        File file = getFile(filePath);
        promise.resolve(file.length());
    }

}
