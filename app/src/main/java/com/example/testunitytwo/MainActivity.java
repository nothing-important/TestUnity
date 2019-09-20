package com.example.testunitytwo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.Xtm.Demo.UnityPlayerActivity;
import com.unity3d.player.UnityPlayer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.mBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this , UnityPlayerActivity.class));
            }
        });
        UnityContainerFragment fragment = new UnityContainerFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.container , fragment);
        transaction.commit();
        initUnity();
    }

    private void initUnity() {
        UnityConstants.unityPlayer = new UnityPlayer(this);
        int glesMode = UnityConstants.unityPlayer.getSettings().getInt("gles_mode", 1);
        UnityConstants.unityPlayer.init(glesMode, false);
    }

    @Override
    protected void onDestroy() {
        UnityConstants.unityPlayer.quit();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        UnityConstants.unityPlayer.resume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        UnityConstants.unityPlayer.pause();
        super.onPause();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        UnityConstants.unityPlayer.configurationChanged(newConfig);
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        UnityConstants.unityPlayer.windowFocusChanged(hasFocus);
        super.onWindowFocusChanged(hasFocus);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_MULTIPLE)
            return UnityConstants.unityPlayer.onKeyMultiple(event.getKeyCode(), event.getRepeatCount(), event);
        return super.dispatchKeyEvent(event);
    }
}
