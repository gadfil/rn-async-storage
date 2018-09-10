package org.gamega;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableArray;

import java.util.ArrayList;
import java.util.Map;

public class RNAsyncStorageModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public RNAsyncStorageModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "RNAsyncStorage";
    }

    /**
     * Write string value by key
     *
     * @param key
     * @param value
     */
    @ReactMethod
    public void setItem(String key, String value, Promise promise) {
        try {
            SharedPreferences sharedPref = getCurrentActivity().getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(key, value);
            editor.commit();
            promise.resolve(null);
        } catch (Exception e) {
            promise.reject(e);
            e.printStackTrace();
        }
    }

    @ReactMethod
    public void multiSet(ReadableArray keyValueArray, Promise promise) {
        if (keyValueArray == null) {
            promise.reject(new Throwable("Argument of type 'null' is not assignable to parameter of type 'string[][]'."));
        }

        try {
            SharedPreferences sharedPref = getCurrentActivity().getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            for (int i = 0; i < keyValueArray.size(); i++) {
                if (keyValueArray.getArray(i).size() != 2) {
                    promise.reject(new Throwable("Siz of array don't equal 2 " + keyValueArray.getArray(i)));

                    return;
                }
                if (keyValueArray.getArray(i).getString(0) == null) {
                    promise.reject(new Throwable("Null Pointer  " + keyValueArray.getArray(i)));
                    return;
                }
                if (keyValueArray.getArray(i).getString(1) == null) {
                    promise.reject(new Throwable("Null Pointer  " + keyValueArray.getArray(i)));
                    return;
                }

                editor.putString(keyValueArray.getArray(i).getString(0), keyValueArray.getArray(i).getString(1));

            }
            editor.commit();
            promise.resolve(null);
        } catch (Exception e) {
            promise.reject(e);
            e.printStackTrace();
        }
    }

    /**
     * Remove value by key
     *
     * @param key
     */
    @ReactMethod
    public void removeItem(String key, Promise promise) {

        try {
            SharedPreferences sharedPref = getCurrentActivity().getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.remove(key);
            editor.commit();
            promise.resolve(null);
        } catch (Exception e) {
            promise.reject(e);
            e.printStackTrace();
        }
    }

    /**
     * Get string value by key
     *
     * @param key
     * @return string value or null
     */
    @ReactMethod
    public void getItem(String key, Promise promise) {
        try {
            SharedPreferences sharedPref = getCurrentActivity().getPreferences(Context.MODE_PRIVATE);
            String value = sharedPref.getString(key, null);
            promise.resolve(value);
        } catch (Exception e) {
            promise.reject(e);
            e.printStackTrace();
        }
    }

    @ReactMethod
    public void multiGet(ReadableArray keys, Promise promise) {
        if (keys == null) {
            promise.reject(new Throwable("Argument of type 'null' is not assignable to parameter of type 'string[]'."));
        }
        try {
            SharedPreferences sharedPref = getCurrentActivity().getPreferences(Context.MODE_PRIVATE);
            WritableArray data = Arguments.createArray();
            for (int i = 0; i < keys.size(); i++) {
                WritableArray row = Arguments.createArray();
                row.pushString(keys.getString(i));
                row.pushString(sharedPref.getString(keys.getString(i), null));
                data.pushArray(row);
            }
            promise.resolve(data);
        } catch (Exception e) {
            promise.reject(e);
            e.printStackTrace();
        }
    }

    /**
     * Clear storage
     */
    @ReactMethod
    public void clear(Promise promise) {
        try {
            SharedPreferences sharedPref = getCurrentActivity().getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.clear();
            editor.commit();
            promise.resolve(null);
        } catch (Exception e) {
            promise.reject(e);
            e.printStackTrace();
        }
    }

    /**
     * Get all keys
     *
     * @param promise
     */
    @ReactMethod
    public void getAllKeys(Promise promise) {
        try {
            SharedPreferences sharedPref = getCurrentActivity().getPreferences(Context.MODE_PRIVATE);
            Map<String, ?> map = sharedPref.getAll();
            ArrayList<String> arrayList = new ArrayList(map.size());
            for (Map.Entry<String, ?> entry : map.entrySet()) {
                arrayList.add(entry.getKey());
            }

            String[] returnArray = new String[arrayList.size()];
            returnArray = arrayList.toArray(returnArray);
            WritableArray promiseArray = Arguments.createArray();
            for (int i = 0; i < returnArray.length; i++) {
                promiseArray.pushString(returnArray[i]);
            }
            promise.resolve(promiseArray);

        } catch (Exception e) {
            promise.reject(e);
            e.printStackTrace();

        }

    }
}
