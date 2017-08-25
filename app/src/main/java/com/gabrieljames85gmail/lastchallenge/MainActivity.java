package com.gabrieljames85gmail.lastchallenge;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class MainActivity extends AppCompatActivity {
    public static final String LOG_TAG = MainActivity.class.getName();
    public static final String Lagos_Java_Url = "https://api.github.com/search/users?q=location:%22lagos%22+language:%22java%22";
    public static  final String Avatar_Url = " Avatar_Url";
    public static final String FirstName = " FirstName";
    public static final String Profile = "Profile";

    public JavaLagosAdapter javalag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-RobotoRegular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );


        LagosJavaTask javaFriends = new LagosJavaTask();
        javaFriends.execute(Lagos_Java_Url);


        javalag = new JavaLagosAdapter(this, new ArrayList<Lads>());
        ListView mylist = (ListView)findViewById(R.id.list_name);
        mylist.setAdapter(javalag);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Lads lad = javalag.getItem(position);
                String imaged = lad.getmPics();
                String firstName = lad.getmFirstName();
                String git_profile = lad.getmGitProfile();

                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                intent.putExtra(FirstName, firstName);
                intent.putExtra(Avatar_Url, imaged);
                intent.putExtra(Profile, git_profile);

                startActivity(intent);


            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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



    private class LagosJavaTask extends AsyncTask<String, Void, List<Lads>> {

        @Override
        protected List<Lads> doInBackground(String... urls) {
            // Don't perform the request if there are no URLs, or the first URL is null.
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            List<Lads> result = QueryUtils.fetchJavaDevLag(urls[0]);
            return result;


        }

        @Override
        protected void onPostExecute(List<Lads> data) {
            // Clear the adapter of previous earthquake data
            javalag.clear();

            // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
            // data set. This will trigger the ListView to update.
            if (data != null && !data.isEmpty()) {
                javalag.addAll(data);
            }
        }
    }



}
