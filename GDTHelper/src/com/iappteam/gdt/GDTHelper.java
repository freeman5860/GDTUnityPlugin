package com.iappteam.gdt;

import android.app.Activity;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;

import com.qq.e.ads.banner.ADSize;
import com.qq.e.ads.banner.AbstractBannerADListener;
import com.qq.e.ads.banner.BannerView;
import com.qq.e.ads.interstitial.AbstractInterstitialADListener;
import com.qq.e.ads.interstitial.InterstitialAD;

public class GDTHelper {
	public static final String TAG = "GDTHelper";

	public void initialize() {
		try {
			Class.forName(BannerView.class.getName());
		} catch (ClassNotFoundException e) {
			Log.e(TAG, e.toString());
		}
	}

	public void showInterstitial(final Activity a, final String appid, final String posId) {
		a.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				final InterstitialAD iad = new InterstitialAD(a, appid,
						posId);
				iad.setADListener(new AbstractInterstitialADListener() {

					@Override
					public void onNoAD(int arg0) {
						Log.i(TAG, "LoadInterstitialAd Fail:" + arg0);
					}

					@Override
					public void onADReceive() {
						Log.i(TAG,"onADReceive");
						iad.show();
					}
				});
				iad.loadAD();
			}
		});
		
	}

	public void createBannerView(final Activity a, final String appid, final String posid) {
		a.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				BannerView banner = new BannerView(a, ADSize.BANNER, appid,
						posid);
				banner.setRefresh(30);
				banner.setADListener(new AbstractBannerADListener() {

					@Override
					public void onNoAD(int arg0) {
						Log.i(TAG, "BannerNoAD Code=" + arg0);
					}

					@Override
					public void onADReceiv() {
						Log.i(TAG, "ONBannerReceive");
					}
				});
				RelativeLayout container = new RelativeLayout(a);
				RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
				params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
				container.addView(banner, params);
				
				banner.loadAD();
				
				LayoutParams rParam = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

				a.addContentView(container, rParam);
			}
		});
	}
}
