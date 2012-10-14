package com.gngrwzrd.android;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;

public class Utilities {
	
	public static int pixelsToDPixels(Context context, int pixels) {
		final float scale = ((Activity)context).getResources().getDisplayMetrics().density;
		return (int) (pixels * scale + 0.5f);
	}
	
	public static String writeableDirectory(Context context) {
		File filesDir = null;
		String filesDirStr = "NONE";
		
		//first try external storage like SD cards etc.
		boolean mExternalStorageWriteable = false;
		String state = Environment.getExternalStorageState();
		
		//check if external storage is writable.
		if(Environment.MEDIA_MOUNTED.equals(state)) {
			mExternalStorageWriteable = true;
		} else if(Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
			mExternalStorageWriteable = false;
		} else {
			mExternalStorageWriteable = false;
		}
		
		//external storage is writable so use that
		if(mExternalStorageWriteable) {
			filesDir = new File(context.getExternalCacheDir().getAbsolutePath() + "/UGuide");
			filesDir.mkdirs();
			filesDirStr = filesDir.getAbsolutePath() + "/";
			return filesDirStr;
		}
		
		//secondly try internal storage.
		filesDir = context.getDir("UGuide",Context.MODE_PRIVATE);
		filesDir.mkdirs();
		filesDirStr = filesDir.getAbsolutePath();
		
		return filesDirStr + "/";
	}
	
	public static Boolean isUsingExternalStorage() {
		//first try external storage like SD cards etc.
		boolean mExternalStorageWriteable = false;
		String state = Environment.getExternalStorageState();
		
		//check if external storage is writable.
		if(Environment.MEDIA_MOUNTED.equals(state)) {
			mExternalStorageWriteable = true;
		} else if(Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
			mExternalStorageWriteable = false;
		} else {
			mExternalStorageWriteable = false;
		}
		
		//external storage is writable so use that
		if(mExternalStorageWriteable) {
			return true;
		}
		
		return false;
	}
}
