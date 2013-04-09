package com.Trigger;

import org.apache.cordova.api.CallbackContext;


import org.apache.cordova.api.CordovaPlugin;
import org.apache.cordova.api.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;
import android.app.Activity;
import android.media.*;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.PictureCallback;

import java.io.*;
import java.lang.*;
public class CameraPlugin extends CordovaPlugin {
	public CameraPlugin() {
	    super();
	}
    private String name;
    private Camera myCamera;
    @Override
    public boolean execute(String action, JSONArray args,
			   final CallbackContext callbackContext) throws JSONException {
        if (action.equals("mainCapture")){
        myCamera=Camera.open();
        Activity ctx = this.cordova.getActivity();
        if(myCamera!=null){
          try{
            //set camera parameters if you want to
            //...

            // here, the unused surface view and holder
            SurfaceView dummy=new SurfaceView(ctx);
            myCamera.setPreviewDisplay(dummy.getHolder());    
            myCamera.startPreview(); 
            name = args.getString(0);
            myCamera.takePicture(null, null, getJpegCallback());
            callbackContext.success("CameraPlugin picture taken");
            
            return true;
          } catch (IOException ex) {Log.d("CameraPlugin","Failed");}  

        }else{
          Log.d("CameraPlugin","Picture failed");
        }
        } else if (action.equals("frontCapture")) {
         Log.d("CameraPlugin","Not implemented"); 
        }
		return false;
    }
    

    private PictureCallback getJpegCallback(){
        try{
        PictureCallback jpeg=new PictureCallback() {   
          @Override
          public void onPictureTaken(byte[] data, Camera camera) {
            FileOutputStream fos;
            try {
              fos = new FileOutputStream(name);
              fos.write(data);
              fos.close();
            }  catch (IOException e) {
              Log.d("CameraPlugin","Image Write Failed "+ e);
            }
          }
        };
        return jpeg;
      } finally { 
          myCamera.release();
          myCamera.stopPreview();}
    }
}
