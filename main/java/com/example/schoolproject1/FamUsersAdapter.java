package com.example.schoolproject1;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class FamUsersAdapter  extends ArrayAdapter<FamUser> {

        Context context;
        List<FamUser> objects;
        public FamUsersAdapter(Context context, int resource, int textViewResourceId, List<FamUser> objects) {
            super(context, resource, textViewResourceId, objects);
            this.context=context;
            this.objects=objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
            View view = layoutInflater.inflate(R.layout.arraylistcotumelayout,parent,false);

            TextView tvName = view.findViewById(R.id.tvName);
            TextView tvUserStatus = view.findViewById(R.id.tvUserStatus);
            ImageView ivProfilePic=view.findViewById(R.id.ivProfilePic);
            FamUser temp = objects.get(position);

            ivProfilePic.setImageBitmap(temp.getProfile());
            tvName.setText(temp.getUsername());
            tvUserStatus.setText(temp.getUserStatus());

            return view;
        }
}
