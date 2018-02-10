package com.zouchapps.spacerocketship;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends Activity {


    // Splash screen timer
    private static int SPLASH_TIME_OUT = 2000;
    // Animation

    ImageView imgLogo ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        imgLogo = (ImageView) findViewById(R.id.imgLogo);



        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        imgLogo.startAnimation(hyperspaceJumpAnimation);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */







            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashScreen.this, Test.class);
                 startActivity(i);

                //new SignInActivity(getApplicationContext()) ;

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
