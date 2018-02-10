package com.zouchapps.spacerocketship;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;

import com.google.example.games.basegameutils.BaseGameActivity;
import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.BaseGameActivity;
public class Test extends BaseGameActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


        findViewById(R.id.sign_in_buttons).setOnClickListener(this);
        findViewById(R.id.sign_out_button).setOnClickListener(this);
        findViewById(R.id.achievement).setOnClickListener(this);

        beginUserInitiatedSignIn();



    }



    @Override
    public void onSignInFailed() {

    }

    @Override
    public void onSignInSucceeded() {






    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.sign_in_buttons) {
            //beginUserInitiatedSignIn();

            Intent i = new Intent(getApplicationContext(), AndroidLauncher.class);
            startActivity(i);



        }
        else if (view.getId() == R.id.sign_out_button) {
            signOut();
            findViewById(R.id.sign_in_buttons).setVisibility(View.VISIBLE);
            findViewById(R.id.sign_out_button).setVisibility(View.GONE);
        }
     if (view.getId() == R.id.achievement) {
                   startActivityForResult(Games.Achievements.getAchievementsIntent(
                   getApiClient()), 1);


        }
    }
}
