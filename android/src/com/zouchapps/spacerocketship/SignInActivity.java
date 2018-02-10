package com.zouchapps.spacerocketship;


import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;
import com.google.example.games.basegameutils.BaseGameActivity;
import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.GameHelper;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class SignInActivity extends Activity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {


DatabaseReference mRoot = FirebaseDatabase.getInstance().getReference();
DatabaseReference mScore = mRoot.child("Score");

    private GoogleApiClient mGoogleApiClient;
    TextView textV ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        textV = (TextView) findViewById(R.id.textView4);







        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Create the Google Api Client with access to the Play Games services
        mGoogleApiClient = new GoogleApiClient.Builder(this)//Instantiate w/ builder
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API)
                .addScope(Plus.SCOPE_PLUS_LOGIN)
                .addApi(Games.API)
                .addScope(Games.SCOPE_GAMES)

                .build();








        Log.i("fuck", "come onnnnnn ");
        mGoogleApiClient.connect();
        if (mGoogleApiClient.isConnected())

        {
            Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_LONG);
            Log.i("fuck", "dude ");
        } else Toast.makeText(getApplicationContext(), "problemmm", Toast.LENGTH_LONG);






    }


    @Override
    protected void onStart() {
        super.onStart();

        mScore.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                textV.setText(text);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
       // mGoogleApiClient.connect();

       // if(mGoogleApiClient.isConnected()) Toast.makeText(getApplicationContext() , "Connected" , Toast.LENGTH_LONG);
    }

    @Override
    protected void onStop() {
        super.onStop();
       mGoogleApiClient.disconnect();
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {


     Intent intent = new Intent(this, AndroidLauncher.class );
      startActivity(intent);


    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


}
