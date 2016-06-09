package com.ourcodeworld.plugins.preventscreenshots;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.view.WindowManager;

public class OurCodeWorldpreventscreenshots extends CordovaPlugin {
    private static final String ACTION_ENABLE = "enable";
    private static final String ACTION_DISABLE = "disable";

    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {
        if (ACTION_ENABLE.equals(action)) {
            cordova.getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    cordova.getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                                                               WindowManager.LayoutParams.FLAG_SECURE);
                }
            });
        }else if(ACTION_DISABLE.equals(action)){
            cordova.getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    cordova.getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_SECURE,
                                                                 WindowManager.LayoutParams.FLAG_SECURE);
                }
            });
        }

        PluginResult pluginResult = new  PluginResult(PluginResult.Status.NO_RESULT);
        pluginResult.setKeepCallback(true); // Keep callback

        return true;
    }
}
