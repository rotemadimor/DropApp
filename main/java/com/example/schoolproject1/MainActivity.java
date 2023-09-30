package com.example.schoolproject1;

import android.app.Dialog;
import android.app.Notification;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.schoolproject1.App.stChannelId;


public class MainActivity extends AppCompatActivity {
    private NotificationManagerCompat notificationManager;
    Dialog dialog1;
    Dialog signup;
    private FirebaseAuth mAuth;
    DatabaseReference usersref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitymain);





        notificationManager= NotificationManagerCompat.from(this);

        mAuth = FirebaseAuth.getInstance();
        TextView tx = findViewById(R.id.tvWelcome);
        usersref = FirebaseDatabase.getInstance().getReference();
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/KeeponTruckin.ttf");
        tx.setTypeface(custom_font);
        final Button btnSign = findViewById(R.id.btnSign);
        final Button btnLog = findViewById(R.id.btnLog);

        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    CreateDialogLogIn();
                    startMyService(v);
                    dialog1.show();

            }
        });

        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    CreateDialogSignUp();
                    signup.show();
            }
        });

    }


    // creating dialog Log In
    public void CreateDialogLogIn() {
        dialog1 = new Dialog(this);
        dialog1.setContentView(R.layout.dialog1);
        final Button btnOK = dialog1.findViewById(R.id.btnDial);



        final EditText etEmailDial = dialog1.findViewById(R.id.etEmailDial);
        final EditText etPswDial=dialog1.findViewById(R.id.etPswDial);

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
           btnOK.setOnClickListener(new View.OnClickListener() {

               @Override
               public void onClick(View view) {
                   final String stEmailDial=etEmailDial.getText().toString();
                   final String stPswDial=etPswDial.getText().toString();
                   if (stEmailDial.trim().length()>0 && stPswDial.trim().length()>0) {
                       mAuth.signInWithEmailAndPassword(stEmailDial, stPswDial)
                               .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                   @Override
                                   public void onComplete(@NonNull Task<AuthResult> task) {

                                       if (task.isSuccessful()) {
                                           // Sign in success, update UI with the signed-in user's information
                                           Log.d("success", "signInWithEmail:success");
                                           updateUIin(user);
                                           OpenCurrentSystem();
                                       }
                                       else {
                                           // If sign in fails, display a message to the user.
                                           Log.w("failure", "signInWithEmail:failure", task.getException());
                                           Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                           updateUIin(null);
                                       }

                                       // ...
                                   }
                               });
                   }
//
                   else{
                       Toast.makeText(MainActivity.this, "something is wrong, please try again", Toast.LENGTH_SHORT).show();
                       stopService();
                   }

               }
           });
       }


        // Create Dialog Sign Up
        public void CreateDialogSignUp () {
            signup = new Dialog(this);
            signup.setContentView(R.layout.signup);
            final EditText etPassword = signup.findViewById(R.id.etPassword);
            final EditText etEmail = signup.findViewById(R.id.etEmail);
            final RadioGroup rgGender = signup.findViewById(R.id.rgGender);
            final EditText etFNameDial = signup.findViewById(R.id.etName);
            final EditText etLNameDial = signup.findViewById(R.id.etLastName);
            final EditText etFamSignCode = signup.findViewById(R.id.etFamSignCode);

            Button btnSubmitSign = signup.findViewById(R.id.btnSubmitSign);
            btnSubmitSign.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final RadioButton rbGenderDial = signup.findViewById(rgGender.getCheckedRadioButtonId());
                    final String email = etEmail.getText().toString();
                    final String password = etPassword.getText().toString();
                    final String stFNameDial = etFNameDial.getText().toString();
                    final String stLNameDial = etLNameDial.getText().toString();
                    final String stGenderDial = rbGenderDial.getText().toString();
                    final String stFamSignCode = etFamSignCode.getText().toString();

                    final RadioButton rbNewFamily = signup.findViewById(R.id.rbNewFamily);

                    if (!stFNameDial.matches("") && !stLNameDial.matches("")  && !stGenderDial.matches("")  && !email.matches("")  && !password.matches("") ) {
                        mAuth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            // Sign in success, update UI with the signed-in user's information
                                            Log.d("success", "createUserWithEmail:success");
                                            final FirebaseUser firebaseUser = mAuth.getInstance().getCurrentUser();
                                            if (rbNewFamily.isChecked()) {
                                                User user = new User(stFNameDial, stLNameDial, stGenderDial, email, password, firebaseUser.getUid(), firebaseUser.getUid());
                                                user.saveUserData();
                                                Family family = new Family();
                                                family.setFamilyId(user.getUID());
                                                family.familyUsers.add(user.getUID());
                                                family.saveFamilyData();
                                                sendOnServiceChannel();
                                                updateUIup(firebaseUser, user.getfName());
                                            } else if (!stFamSignCode.matches("") ) {

                                                //Add a listener to get the friend's nickname (and check if the friend actually exists);
                                                usersref.child("Family").child(stFamSignCode).addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(DataSnapshot dataSnapshot) {

                                                        //Check if someone with the provided userID exists and if not exist the method:
                                                        if (!dataSnapshot.exists()) {
                                                            //Stop the progress dialog and inform the user that his friend does'nt exist:
                                                            Toast.makeText(MainActivity.this, "Family doesn't exist.", Toast.LENGTH_SHORT).show();
                                                            return;
                                                        }

                                                        // TODO: comment
                                                        Family family = dataSnapshot.getValue(Family.class);
                                                        User user = new User(stFNameDial, stLNameDial, stGenderDial, email, password, firebaseUser.getUid(), stFamSignCode);
                                                        user.saveUserData();
                                                        family.familyUsers.add(user.getUID());
                                                        family.saveFamilyData();


                                                        //Add the user to the added friend's friends list:
                                                        //Stop the progress dialog:
                                                        Toast.makeText(MainActivity.this, "Friend added successfully!", Toast.LENGTH_SHORT).show();
                                                    }

                                                    @Override
                                                    public void onCancelled(DatabaseError databaseError) {
                                                        Toast.makeText(MainActivity.this, "Failed to load information!", Toast.LENGTH_SHORT).show();
                                                    }
                                                });


                                            } else {
                                                Log.w("failure", "createUserWithEmailAndPassword:failure", task.getException());
                                                Toast.makeText(MainActivity.this, "Please fill family code.", Toast.LENGTH_SHORT).show();
                                            }

                                        } else {
                                            // If sign in fails, display a message to the user.
                                            Log.w("failure", "createUserWithEmail:failure", task.getException());
                                            Toast.makeText(MainActivity.this, "failed.", Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                });
                    }
                    else {
                        Log.w("failure", "createUserWithEmailAndPassword:failure");
                        Toast.makeText(MainActivity.this, "Please fill all the fields.", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
        final public void updateUIup (FirebaseUser user, String name){
        if (user != null) {
            Log.d("success", "createUserWithEmail:success");
            stopService();
            Toast.makeText(this, "Hello " + name, Toast.LENGTH_SHORT).show();
            signup.dismiss();
            OpenFamilyUsers();

        }
         }

        final public void updateUIin (FirebaseUser user){
        if (user != null) {
            Log.d("success", "createUserWithEmail:success");
            stopService();
            Toast.makeText(this, "Hello " + user.getDisplayName(), Toast.LENGTH_SHORT).show();
            dialog1.dismiss();
            OpenCurrentSystem();

        }
        }

        public void IsFamilyExistCheck(View v){
        final TextView tvFamSignCode = signup.findViewById(R.id.tvFamSignCode);
        final TextView etFamSignCode = signup.findViewById(R.id.etFamSignCode);
        final RadioGroup radioGroup = signup.findViewById(R.id.rgIsFamilyExist);
        int check =radioGroup.getCheckedRadioButtonId();
        if (check==R.id.rbFamilySigned){
            tvFamSignCode.setVisibility(View.VISIBLE);
            etFamSignCode.setVisibility(View.VISIBLE);
        }
        else{
            tvFamSignCode.setVisibility(View.INVISIBLE);
            etFamSignCode.setVisibility(View.INVISIBLE);
        }
        }


    public void sendOnServiceChannel(){
        String title= "Hey there!";
        String meesage = "Welcome to WateringApp :)" ;
        Notification notification= new NotificationCompat.Builder(this,stChannelId)
                .setSmallIcon(R.drawable.ic_cloud)
                .setContentTitle(title)
                .setContentText(meesage)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        notificationManager.notify(1,notification);

    }

    public void stopService(){
        Intent intent= new Intent(this,MyService.class);
        stopService(intent);
    }

    public void startMyService(View view){
        Intent intent = new Intent(this,MyService.class);
        startService(intent);

    }

    // screens switching
    public void OpenFamilyUsers(){
        Intent i = new Intent(this, FamilyUsers.class);
        startActivity(i);
    }

    public void OpenMain(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void OpenCurrentSystem(){
        Intent i = new Intent(this, CurrentSystem.class);
        startActivity(i);
    }




}












