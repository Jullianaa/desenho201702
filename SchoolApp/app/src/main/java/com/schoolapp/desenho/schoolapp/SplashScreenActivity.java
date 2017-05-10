package com.schoolapp.desenho.schoolapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.schoolapp.desenho.schoolapp.activities.MainActivity;

public class SplashScreenActivity extends AppCompatActivity {

    final long WAIT_TIME = 2000;
    private JSONParser jsonParser = new JSONParser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inserts all courses in the database if this is not already done
        Log.d("SplashScreen", "inserting disciplines");
        jsonParser.insertDisciplines(this);

        Log.d("SplashScreen", "Disciplines populated!!");
        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {


                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(SplashScreenActivity.this,MainActivity.class);
                SplashScreenActivity.this.startActivity(mainIntent);
                SplashScreenActivity.this.finish();
            }
        }, WAIT_TIME);

    }
}
