package com.example.schoolproject1;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CurrentSystem extends AppCompatActivity {

    Menu menu1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.currentsystem);
        TextView tx = findViewById(R.id.tvCurr);
        final Button btnMenu= findViewById(R.id.btnMenu);
        registerForContextMenu(btnMenu);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/KeeponTruckin.ttf");
        tx.setTypeface(custom_font);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCreateOptionsMenu(menu1);
            }
        });


    }

    @Override
       public void onCreateContextMenu (ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
           super.onCreateContextMenu(menu,v,menuInfo);
           MenuInflater inflater = getMenuInflater();
          inflater.inflate(R.menu.menu1 , menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        super.onContextItemSelected(item);
        int id = item.getItemId();
        if (id == R.id.btnmenu1) {
            OpenMain();
            return true;
        }
        if (id == R.id.btnmenu2) {
            OpenFamilyUsers();
            return true;
        }
        if (id == R.id.btnmenu3) {
            OpenCurrentSystem();
            return true;
        }
        return false;
    }

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
