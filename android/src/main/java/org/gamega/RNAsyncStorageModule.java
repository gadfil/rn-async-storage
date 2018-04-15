
package org.gamega;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

public class RNAsyncStorageModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  public RNReactNativeAsyncStorageModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNAsyncStorage";
  }

   /**
      * Write string value by key
      * @param key
      * @param value
      */
     @ReactMethod
     public void setItem(String key, String value, Promise promise){
         try {
             SharedPreferences sharedPref = getCurrentActivity().getPreferences(Context.MODE_PRIVATE);
             SharedPreferences.Editor editor = sharedPref.edit();
             editor.putString(key, value);
             editor.commit();
             promise.resolve(null);
         }catch (Exception e){

         }
     }

     /**
      * Get string value by key
      * @param key
      * @return string value or null
      */
     @ReactMethod
     public void getItem(String key, Promise promise){
         try {
             SharedPreferences sharedPref = getCurrentActivity().getPreferences(Context.MODE_PRIVATE);
             String value = sharedPref.getString(key, null);
             Log.d("storage/getItem", key);
             Log.d("storage/getItem", value);
             promise.resolve(value);
         }catch (Exception e){
             promise.reject(e);
             e.printStackTrace();
         }
     }

     /**
      * Clear storage
      */
     @ReactMethod
     public void clear(Promise promise){
         try {
             SharedPreferences sharedPref = getCurrentActivity().getPreferences(Context.MODE_PRIVATE);
             SharedPreferences.Editor editor = sharedPref.edit();
             editor.clear();
             editor.commit();
             promise.resolve(null);
         }catch (Exception e){
             e.printStackTrace();
         }
     }

     /**
      * Get all keys
      * @param promise
      */
     @ReactMethod
     public void getAllKeys(Promise promise){
         try {
             SharedPreferences sharedPref = getCurrentActivity().getPreferences(Context.MODE_PRIVATE);
             Map<String, ?> map = sharedPref.getAll();
             ArrayList<String> arrayList = new ArrayList(map.size());
             for (  Map.Entry<String, ?> entry :      map.entrySet()){
                 arrayList.add(entry.getKey());
             }

             String[] returnArray = new String[arrayList.size()];
             returnArray = arrayList.toArray(returnArray);
             WritableArray promiseArray= Arguments.createArray();
             for(int i=0;i<returnArray.length;i++){
                 promiseArray.pushString(returnArray[i]);
             }
             promise.resolve(promiseArray);

         }catch (Exception e){
             promise.reject(e);
             e.printStackTrace();

         }

     }
}