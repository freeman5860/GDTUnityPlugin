package com.example.demo0918;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
	private LinearLayout adContainer; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		adContainer = (LinearLayout)findViewById(R.id.banner_container);
		
		DexFileHelper dexFileHelper = new DexFileHelper(this);
		dexFileHelper.init();
		dexFileHelper.showBanner();
		//dexFileHelper.showInterstitial();
	}
	
	/*private boolean loadJarFile(String fileName){
		final File dexDir = getFilesDir();
		File dexFile = new File(dexDir.getAbsolutePath() + "/" + fileName);
		if(!dexFile.exists()){
			return false;
		}
		DexClassLoader classLoader = new DexClassLoader(
				dexFile.getAbsolutePath(),
				dexDir.getAbsolutePath(), null, getClassLoader());
		try {
			Class AdSizeClss = classLoader.loadClass("com.qq.e.ads.banner.ADSize");
			Field bannerField = AdSizeClss.getDeclaredField("BANNER");
			Object bannerSize = bannerField.get(null);
			
			Class mLoadClass = classLoader.loadClass("com.qq.e.ads.banner.BannerView");
			Constructor constructor = mLoadClass.getConstructor(new Class[] {Activity.class, AdSizeClss, String.class, String.class});
			Object banner = constructor.newInstance(new Object[] {MainActivity.this, bannerSize, "1101152570","9079537218417626401"});
			adContainer.addView((View) banner);
			return true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	private void copyFile(String apkPath, String fileName) throws IOException {
		InputStream input;
		OutputStream output = openFileOutput(fileName, Context.MODE_PRIVATE);
		input = this.getAssets().open(apkPath);
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

	private boolean loadDexFile(String fileName) {

		final File dexDir = getFilesDir();
		File dexFile = new File(dexDir.getAbsolutePath() + "/" + fileName);
		if(!dexFile.exists()){
			return false;
		}
		DexClassLoader classLoader = new DexClassLoader(
				dexFile.getAbsolutePath(),
				dexDir.getAbsolutePath(), null, getClassLoader());
		try {
			Class mLoadClass = classLoader.loadClass("com.iappteam.gdt.GDTHelper");
			Constructor constructor = mLoadClass.getConstructor(new Class[] {});
			Object gdtHelper = constructor.newInstance(new Object[] {});

			Method initMethod = mLoadClass.getDeclaredMethod("initialize", null);
			initMethod.invoke(gdtHelper, null);
			
			Method createBannerMethod = mLoadClass.getMethod("createBannerView", new Class[] { Activity.class });
			createBannerMethod.setAccessible(true);
			Object banner = createBannerMethod.invoke(gdtHelper, new Object[] {MainActivity.this});
			
			//Method interstitialMethod = mLoadClass.getMethod("showInterstitial", new Class[] { Activity.class });
			//interstitialMethod.invoke(gdtHelper, new Object[] {MainActivity.this});
			return true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return false;

	}*/
}
