package com.gabrieljames85gmail.lastchallenge;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.gabrieljames85gmail.lastchallenge.MainActivity.LOG_TAG;

/**
 * Created by OSAJI GABRIEL on 23/08/2017.
 */

public class JavaLagosAdapter extends ArrayAdapter<Lads> {

   private Context context;

    public JavaLagosAdapter(Context context, List<Lads> object) {

        super(context, 0, object);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Lads lad = getItem(position);

        View listingview = convertView;
        if (listingview == null){
            listingview = LayoutInflater.from(getContext()).inflate(R.layout.java_lag, parent, false);
        }

       ImageView imageView = (ImageView)listingview.findViewById(R.id.profile_pic);
        Picasso.with(getContext()).load(lad.getmPics()).transform(new RoundedTransformation(50, 50)).fit().centerCrop().into(imageView);

        TextView firstName =(TextView)listingview.findViewById(R.id.first_name);
        firstName.setText(lad.getmFirstName());





        return listingview;

        //return super.getView(position, convertView, parent);
    }



}
