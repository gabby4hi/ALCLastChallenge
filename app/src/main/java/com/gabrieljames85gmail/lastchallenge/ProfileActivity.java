package com.gabrieljames85gmail.lastchallenge;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ShareActionProvider;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {
    protected ShareActionProvider mShareActionProvider;
    public static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        TextView userName = (TextView) findViewById(R.id.loginname);
        TextView gitLink = (TextView) findViewById(R.id.git_url);
        ImageView gitImage = (ImageView) findViewById(R.id.gitPic);

        Intent intent = getIntent();
        final String name = intent.getExtras().getString(MainActivity.FirstName);
        final String profileUrl = intent.getExtras().getString(MainActivity.Profile);

        Picasso.with(this).load(intent.getExtras().getString(MainActivity.Avatar_Url)).transform(new RoundedTransformation(50, 4)).fit().centerCrop().into(gitImage);


        userName.setText(name);
        gitLink.setText(profileUrl);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ImageButton shareButton = (ImageButton) findViewById(R.id.shareButton);
        ImageButton gitConnect = (ImageButton)findViewById(R.id.gitConnect);

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                String shareBody = " Check out this Awesome Developer   " + "\n " + "Name: " + name +  "\n" + "@gitHub  " + profileUrl;
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Meet This world Class Awesome Java Developer....");
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(shareIntent);
            }
        });

        gitConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(profileUrl));
                startActivity(intent);
            }
        });

        gitLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(profileUrl));
                startActivity(intent);
            }
        });
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
