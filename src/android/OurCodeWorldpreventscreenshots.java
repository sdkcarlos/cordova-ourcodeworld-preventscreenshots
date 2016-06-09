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
        final CallbackContext callbacks = callbackContext;

        if (ACTION_ENABLE.equals(action)) {
            cordova.getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    try{
                        // Allow to make screenshots removing the FLAG_SECURE
                        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
                            cordova.getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_SECURE);
                        }
                        
                        PluginResult result = new PluginResult(PluginResult.Status.OK, "");
                        result.setKeepCallback(true);
                        callbacks.sendPluginResult(result);
                    }catch(Exception e){
                        PluginResult result = new PluginResult(PluginResult.Status.ERROR, e.getMessage());
                        result.setKeepCallback(true);
                        callbacks.sendPluginResult(result);
                    }
                }
            });
        }else if(ACTION_DISABLE.equals(action)){
            cordova.getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    try{
                        // Disable the creation of screenshots adding the FLAG_SECURE to the window
                        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
                            cordova.getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                                                                       WindowManager.LayoutParams.FLAG_SECURE);
                        }

                        PluginResult result = new PluginResult(PluginResult.Status.OK, "");
                        result.setKeepCallback(true);
                        callbacks.sendPluginResult(result);
                    }catch(Exception e){
                        PluginResult result = new PluginResult(PluginResult.Status.ERROR, e.getMessage());
                        result.setKeepCallback(true);
                        callbacks.sendPluginResult(result);
                    }
                }
            });
        }

        PluginResult pluginResult = new  PluginResult(PluginResult.Status.NO_RESULT);
        pluginResult.setKeepCallback(true); // Keep callback

        return true;
    }
}
