package com.example.schoolproject1;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class User {
    String fName;
    String lName;
    String gender;
    String email;
    String password;
    String UID;
    String FID;


    public User(){
        fName="";
        lName="";
        gender="";
        email="";
        password="";
        UID = "";
        FID = "";
    }

    public User(String fN, String lN, String gn, String em, String psw, String uid,String fid ) {
        fName = fN;
        lName = lN;
        gender = gn;
        email = em;
        password= psw;
        UID = uid;
        FID = fid;
    }


    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getUID() {
        return  FirebaseAuth.getInstance().getCurrentUser().getUid();
    }
    public String getFID() {
        return FID;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void setUID(String UID) {
        this.UID = UID;
    }
    public void setFID(String FID) {
        this.FID = FID;
    }


    public void saveUserData()
    {
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference usersRef = rootRef.child("Users");
//        User user = new User(fName, lName, gender, email,password, UID,FID);
        usersRef.child(UID).setValue(this);
    }


}

