package com.gabrieljames85gmail.lastchallenge;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
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
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Lads>>{
    public static final String LOG_TAG = MainActivity.class.getName();
    public static final String LOG_TAGS = QueryUtils.class.getName();
    public static final String Lagos_Java_Url = "https://api.github.com/search/users?q=location:%22lagos%22+language:%22java%22";
    public static final String Avatar_Url = " Avatar_Url";
    public static final String FirstName = " FirstName";
    public static final String Profile = "Profile";

    public static final int JAVALAG = 1;

    public JavaLagosAdapter javalag;

    protected TextView mEmptyStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /**
         Initializing an Async-downloader ...
         LagosJavaTask javaFriends = new LagosJavaTask();
         javaFriends.execute(Lagos_Java_Url); */


        LoaderManager loaderManager = getLoaderManager();

        loaderManager.initLoader(JAVALAG, null, this);


        //Initializing Adapter Class for javaLagos...
        javalag = new JavaLagosAdapter(this, new ArrayList<Lads>());
        ListView mylist = (ListView) findViewById(R.id.list_name);
        mylist.setAdapter(javalag);


        //Initializing an Empty View of text to show on list when network fails
        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        mylist.setEmptyView(mEmptyStateTextView);

        // Setting up the OnItemClickListener for each item that will be cliked..
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
    public Loader<List<Lads>> onCreateLoader(int i, Bundle bundle) {

        return  new JavaLagosLoader(this, Lagos_Java_Url);
    }

    @Override
    public void onLoadFinished(Loader<List<Lads>> loader, List<Lads> lags) {

        // Adding up a Progress Spin for this
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        //Indicating A text to be shown when Network Connection Fails
        mEmptyStateTextView.setText(R.string.erro_text);


        // When there is a good network checks to to see if values is empty
        if (lags != null && !lags.isEmpty()){
            javalag.addAll(lags);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Lads>> loader) {
        javalag.clear();
    }


    /**
     //A nested Class that extends the AsyncTask to run a background thread...
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

    //Updating the UI with this method below
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

     **/


}
