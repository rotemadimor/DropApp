package com.example.schoolproject1;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Family {
    List<String> familyUsers;
    private String familyId;

    public Family(){
        familyUsers = new ArrayList<>();
        familyId="";
    }

    public Family(String famId){
        familyUsers = new ArrayList<>();
        familyId=famId;
    }

    public Family(List<String> famUsers, String famId){
        familyUsers = famUsers;
        familyId = famId;
    }

    public List<String> getFamilyUsers() {
        return familyUsers;
    }

    public String getFamilyId() {
        return familyId;
    }

    public void setFamilyUsers(List<String> familyUsers) {
        this.familyUsers = familyUsers;
    }

    public void setFamilyId(String famId) {
        this.familyId = famId;
    }
    public void saveFamilyData()
    {
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference famRef = rootRef.child("Family");
        famRef.child(this.familyId).setValue(this);
    }
}
