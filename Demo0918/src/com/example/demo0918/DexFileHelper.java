package com.example.demo0918;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

public class DexFileHelper {
	
	private WeakReference<Activity> mActivity;
	
	private static final String ASSET_FILE_PATH = "gdt.dd";
	private static final String DEX_FILE_NAME = "gdt.apk";
	private static String APP_ID = "1101152570";
	private static String BANNER_POS_ID = "9079537218417626401";
	private static String INTERSTITIAL_POS_ID = "8575134060152130849";

	private static final String TAG = "DexFileHelper";
	
	private DexClassLoader mClassLoader;
	private Object mGDTHelper;
	private Class<?> mLoadClass;
	
	public DexFileHelper(Activity a){
		mActivity = new WeakReference<Activity>(a);
	}
	
	public void setAppID(String appid){
		APP_ID = appid;
	}
	
	public void setBannerPosId(String posId){
		BANNER_POS_ID = posId;
	}
	
	public void setInterstitialPosId(String posId){
		INTERSTITIAL_POS_ID = posId;
	}
	
	public void initWithId(String appId, String bannerId, String interstitialId){
		setAppID(appId);
		setBannerPosId(bannerId);
		setInterstitialPosId(interstitialId);
		init();
	}
	
	public void init(){
		try {
			copyFile(ASSET_FILE_PATH, DEX_FILE_NAME);
			loadDexFile();
		} catch (IOException e) {
			Log.e(TAG,e.toString());
		}		
	}
	
	public void showInterstitial(){
		if(mGDTHelper == null){
			Log.e(TAG,"gdthelper is null");
			return;
		}
		
		try {			
			Method interstitialMethod = mLoadClass.getMethod("showInterstitial", new Class[] { Activity.class, String.class, String.class });
			interstitialMethod.invoke(mGDTHelper, new Object[] {mActivity.get(), APP_ID, INTERSTITIAL_POS_ID}); 
		} catch (SecurityException e) {
			Log.e(TAG,e.toString());
		} catch (NoSuchMethodException e) {
			Log.e(TAG,e.toString());
		} catch (IllegalArgumentException e) {
			Log.e(TAG,e.toString());
		} catch (IllegalAccessException e) {
			Log.e(TAG,e.toString());
		} catch (InvocationTargetException e) {
			Log.e(TAG,e.toString());
		}
	}
	
	public void showBanner(){
		if(mGDTHelper == null){
			Log.e(TAG,"gdthelper is null");
			return;
		}
		
		try {			
			Method createBannerMethod = mLoadClass.getMethod("createBannerView", new Class[] { Activity.class, String.class, String.class });
			createBannerMethod.setAccessible(true);
			createBannerMethod.invoke(mGDTHelper, new Object[] {mActivity.get(), APP_ID, BANNER_POS_ID});
		} catch (SecurityException e) {
			Log.e(TAG,e.toString());
		} catch (NoSuchMethodException e) {
			Log.e(TAG,e.toString());
		} catch (IllegalArgumentException e) {
			Log.e(TAG,e.toString());
		} catch (IllegalAccessException e) {
			Log.e(TAG,e.toString());
		} catch (InvocationTargetException e) {
			Log.e(TAG,e.toString());
		}
	}
	
	private void copyFile(String apkPath, String fileName) throws IOException {
		InputStream input;
		OutputStream output = mActivity.get().openFileOutput(fileName, Context.MODE_PRIVATE);
		input = mActivity.get().getAssets().open(apkPath);
		byte[] buffer = new byte[1024];
		int length = input.read(buffer);
		while (length > 0) {
			output.write(buffer, 0, length);
			length = input.read(buffer);
		}

		output.flush();
		input.close();
		output.close();
	}
	
	private boolean loadDexFile() {

		final File dexDir = mActivity.get().getFilesDir();
		File dexFile = new File(dexDir.getAbsolutePath() + "/" + DEX_FILE_NAME);
		if(!dexFile.exists()){
			return false;
		}
		mClassLoader = new DexClassLoader(
				dexFile.getAbsolutePath(),
				dexDir.getAbsolutePath(), null, mActivity.get().getClassLoader());
		try {
			mLoadClass = mClassLoader.loadClass("com.iappteam.gdt.GDTHelper");
			Constructor<?> constructor = mLoadClass.getConstructor(new Class[] {});
			mGDTHelper = constructor.newInstance(new Object[] {});

			Method initMethod = mLoadClass.getDeclaredMethod("initialize", new Class[]{});
			initMethod.invoke(mGDTHelper, new Object[]{});
		} catch (ClassNotFoundException e) {
			Log.e(TAG,e.toString());
		} catch (SecurityException e) {
			Log.e(TAG,e.toString());
		} catch (NoSuchMethodException e) {
			Log.e(TAG,e.toString());
		} catch (IllegalArgumentException e) {
			Log.e(TAG,e.toString());
		} catch (InstantiationException e) {
			Log.e(TAG,e.toString());
		} catch (IllegalAccessException e) {
			Log.e(TAG,e.toString());
		} catch (InvocationTargetException e) {
			Log.e(TAG,e.toString());
		}
		
		return true;
	}
}
