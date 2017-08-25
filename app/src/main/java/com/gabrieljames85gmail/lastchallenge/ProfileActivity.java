package com.gabrieljames85gmail.lastchallenge;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        TextView userName = (TextView)findViewById(R.id.loginname);
        TextView gitLink = (TextView)findViewById(R.id.git_url);
        ImageView gitImage = (ImageView)findViewById(R.id.gitPic);

        Intent intent = getIntent();
        String name = intent.getExtras().getString(MainActivity.FirstName);
        String profile = intent.getExtras().getString(MainActivity.Profile);

        Picasso.with(this).load(intent.getExtras().getString(MainActivity.Avatar_Url)).into(gitImage);


        userName.setText(name);
        gitLink.setText(profile);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
