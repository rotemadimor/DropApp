package com.example.schoolproject1;

import android.content.res.Resources;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import com.google.firebase.database.DataSnapshot;

public class FamUser implements ListAdapter {
    String familyId;
    String pose;
DataSnapshot dataSnapshot;
    private String username ;
    private String userStatus;
    private Bitmap profile;

    public FamUser(String familyId, String pose, DataSnapshot dataSnapshot) {}
    {
        DataSnapshot ds = dataSnapshot.child("Family");
        DataSnapshot fID = ds.child(familyId);
        DataSnapshot fU = fID.child("familyUsers");
        DataSnapshot gfdh = fU.child(pose);
        String uid =gfdh.getValue(String.class);

        String username = dataSnapshot.child("Users").child(uid).child("fName").getValue(String.class);

        String userStatus;

        if(uid.equals(familyId)){
            userStatus ="Manager";
        }
        else{
            userStatus ="Family Member";
        }
        String gender = dataSnapshot.child(familyId).child(uid).getValue(User.class).getGender();
        Bitmap profile;
        if (gender.equals("Female")) {
            profile = BitmapFactory.decodeResource(Resources.getSystem() ,R.drawable.femaleavatar);
        }
        else{
            profile = BitmapFactory.decodeResource(Resources.getSystem() ,R.drawable.maleavatar);
        }
        this.username= username;
        this.userStatus= userStatus;
        this.profile= profile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public Bitmap getProfile() {
        return profile;
    }

    public void setProfile(Bitmap profile) {
        this.profile = profile;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int i) {
        return false;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public int getItemViewType(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}


