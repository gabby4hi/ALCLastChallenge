package com.gabrieljames85gmail.lastchallenge;

/**
 * Created by OSAJI GABRIEL on 22/08/2017.
 */

public class Lads {
    private String mPics;
    private String mFirstName;
    private String mLastName;
    private String mGitProfile;

    public Lads(String Pics, String FirstName, String GitProfile){
        mPics = Pics;
        mFirstName = FirstName;
        mGitProfile = GitProfile;

    }

    public String getmPics(){
        return mPics;
    }
    public String getmFirstName(){
        return mFirstName;
    }
    public String getmGitProfile(){
        return mGitProfile;
    }
}
