using UnityEngine;
using System.Collections;

public class GDTControl : MonoBehaviour {

	public const string UnityActivityClassName = "com.unity3d.player.UnityPlayer";
	public const string DexFileHelperClassName = "com.example.demo0918.DexFileHelper";
	
	private AndroidJavaObject dexHelper;

	// Use this for initialization
	void Start () {
		#if UNITY_ANDROID			
			AndroidJavaClass playerClass = new AndroidJavaClass(UnityActivityClassName);
			AndroidJavaObject activity =
                    playerClass.GetStatic<AndroidJavaObject>("currentActivity");
					
			dexHelper = new AndroidJavaObject(
                    DexFileHelperClassName, activity);
			dexHelper.Call("init");			
		#endif
	}
	
	public void showInterstitial(){
		#if UNITY_ANDROID
			dexHelper.Call("showInterstitial");
		#endif
	}
	
	public void showBanner(){
		#if UNITY_ANDROID
			dexHelper.Call("showBanner");
		#endif
	}
}
