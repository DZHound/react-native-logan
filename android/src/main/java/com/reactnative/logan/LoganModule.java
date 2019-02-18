package com.reactnative.logan;

import com.dianping.logan.Logan;
import com.dianping.logan.LoganConfig;
import com.dianping.logan.OnLoganProtocolStatus;
import com.dianping.logan.SendLogRunnable;
import com.facebook.react.bridge.*;
import com.facebook.react.module.annotations.ReactModule;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ReactModule(name = LoganModule.MODULE_NAME)
public class LoganModule extends ReactContextBaseJavaModule {

    public static final String MODULE_NAME = "RLogan";

    public LoganModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return MODULE_NAME;
    }

    /**
     * {
     *     cachePath: string, //mmap缓存路径
     *     path: string, //file文件路径
     *     maxFile: number, //删除文件最大值
     *     day: number, //删除天数
     *     encryptKey16: string, //128位ase加密Key
     *     encryptIv16: string, //128位aes加密IV
     *     minSDCard: numer
     * }
     * @param config
     */
    @ReactMethod
    public void init(ReadableMap config) {
        LoganConfig.Builder builder = new LoganConfig.Builder();
        ReadableMapKeySetIterator iterator = config.keySetIterator();
        while (iterator.hasNextKey()) {
            String configName = iterator.nextKey();
            switch (configName) {
                case "cachePath":
                    builder.setCachePath(config.getString(configName));
                    break;
                case "path":
                    builder.setPath(config.getString(configName));
                    break;
                case "maxFile":
                    builder.setMaxFile((long) config.getDouble(configName));
                    break;
                case "day":
                    builder.setDay((long) config.getDouble(configName));
                    break;
                case "encryptKey16":
                    builder.setEncryptKey16(config.getString(configName).getBytes());
                    break;
                case "encryptIv16":
                    builder.setEncryptIV16(config.getString(configName).getBytes());
                    break;
                case "minSDCard":
                    builder.setMinSDCard((long) config.getDouble(configName));
                    break;
            }
        }
        Logan.init(builder.build());
    }

    @ReactMethod
    public void write(String log, int type) {
        Logan.w(log, type);
    }

    @ReactMethod
    public void send(ReadableArray dates, final Callback callback) {
        List<String> dateList = new ArrayList<>(dates.size());
        for(int i = 0, len = dates.size(); i < len; i++) {
            if(!dates.isNull(i)) {
                dateList.add(dates.getString(i));
            }
        }
        String[] dateArr = new String[dateList.size()];
        dateList.toArray(dateArr);
        Logan.s(dateArr, new SendLogRunnable() {
            @Override
            public void sendLog(File logFile) {
                callback.invoke(logFile.getAbsolutePath());
                this.finish();
            }
        });
    }

    @ReactMethod
    public void getAllFilesInfo(Promise promise) {
        Map<String, Long> allFilesInfo = Logan.getAllFilesInfo();
        WritableMap map = Arguments.createMap();
        if(allFilesInfo != null) {
            for (Map.Entry<String, Long> fileInfo : allFilesInfo.entrySet()) {
                map.putDouble(fileInfo.getKey(), fileInfo.getValue());
            }
        }
        promise.resolve(map);
    }

    @ReactMethod
    public void flush() {
        Logan.f();
    }

    @ReactMethod
    public void debugEnable(boolean enable) {
        Logan.setDebug(enable);
    }

    @ReactMethod
    public void setOnLoganProtocolStatus(final Callback callback) {
        Logan.setOnLoganProtocolStatus(new OnLoganProtocolStatus() {
            @Override
            public void loganProtocolStatus(String cmd, int code) {
                callback.invoke(cmd, code);
            }
        });
    }

}
