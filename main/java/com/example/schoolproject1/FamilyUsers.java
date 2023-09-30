package com.example.schoolproject1;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FamilyUsers extends AppCompatActivity {

    ListView lv;
    ArrayList<FamUser> FamUsersList;
    FamUsersAdapter familyUsersAdapter;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_users);

        TextView tx = findViewById(R.id.tvUsersTitle);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/KeeponTruckin.ttf");
        tx.setTypeface(custom_font);

        user = FirebaseAuth.getInstance().getCurrentUser();

        FamUsersList = new ArrayList<>();
        familyUsersAdapter = new FamUsersAdapter(this,0,0,FamUsersList);

        final DatabaseReference usersref = FirebaseDatabase.getInstance().getReference();
        usersref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String familyID = dataSnapshot.child("Users").child(user.getUid()).child("fid").getValue(String.class);
                Toast.makeText(FamilyUsers.this,familyID, Toast.LENGTH_SHORT);
//                GenericTypeIndicator<ArrayList> t = new GenericTypeIndicator<ArrayList>() {};
                ArrayList<String> mList =(ArrayList<String>) dataSnapshot.child("Family").child(familyID).child("familyUsers").getValue();

                for (int i = 0;
                     i< mList.size();
                     i++ )
                {
                    String pose = String.valueOf(i);
                    FamUser userInList= new FamUser(familyID,pose,dataSnapshot);
                    FamUsersList.add(userInList);

                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(FamilyUsers.this,"failed", Toast.LENGTH_SHORT);

            }
        });

        lv=findViewById(R.id.lv);
        lv.setAdapter(familyUsersAdapter);

    }
}
