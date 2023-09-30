package com.example.schoolproject1;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyService extends Service {

    MediaPlayer player;
    String TAG = "BackgroundSoundService";
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        player.start();
        return START_STICKY;
    }



    @Override
    public void onCreate() {
        player = MediaPlayer.create(this,R.raw.elevator_music);
        player.setLooping(true); // Set looping
        player.setVolume(100,100);
        Log.i(TAG, "onCreate() , service started");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        player.stop();
        Log.i(TAG, "onCreate() , service stopped");
        super.onDestroy();
    }
}
