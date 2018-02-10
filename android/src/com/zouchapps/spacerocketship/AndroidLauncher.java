package com.zouchapps.spacerocketship;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class AndroidLauncher extends AndroidApplication {

	protected AdView adView ;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		RelativeLayout layout = new RelativeLayout(this) ;
		View gameView = initializeForView(new SpaceRocket(), config);
		layout.addView(gameView);


		adView = new AdView(this) ;
		adView.setAdListener(new AdListener() {
			@Override
			public void onAdLoaded() {
				super.onAdLoaded();

				Log.i("ad" , "addLoaded") ;
			}
		});

		adView.setAdSize(AdSize.BANNER);
		adView.setAdUnitId("ca-app-pub-9903872758129733/6205521806");

		AdRequest.Builder builder = new AdRequest.Builder() ;
		//builder.addTestDevice("035812DA07AF5BCA869ABF56E70A1049");
		RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT


		) ;
		adParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);


		layout.addView(adView , adParams);
		adView.loadAd(builder.build());
		setContentView(layout);

		//AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		//initialize(new SpaceRocket(), config);
	}
}
