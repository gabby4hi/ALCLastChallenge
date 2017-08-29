package com.gabrieljames85gmail.lastchallenge;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by OSAJI GABRIEL on 29/08/2017.
 */

public class JavaLagosLoader extends AsyncTaskLoader<List<Lads>>{

    private String mUrl;

    public JavaLagosLoader(Context context, String url){
        super(context);
        mUrl = url;

    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Lads> loadInBackground() {
        if (mUrl == null){
            return  null;
        }

        List<Lads> javaLagList = QueryUtils.fetchJavaDevLag(mUrl);
        return javaLagList;
    }
}

