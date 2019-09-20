package com.example.testunitytwo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class UnityContainerFragment extends Fragment {

    private View playerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        playerView = UnityConstants.unityPlayer.getView();
        if(playerView.getParent() != null){
            ((ViewGroup)playerView.getParent()).removeAllViews();
        }
        playerView.getViewTreeObserver().addOnWindowFocusChangeListener(new ViewTreeObserver.OnWindowFocusChangeListener() {
            @Override
            public void onWindowFocusChanged(boolean hasFocus) {
                UnityConstants.unityPlayer.windowFocusChanged(hasFocus);
            }
        });
        return playerView;
    }

    @Override
    public void onResume() {
        UnityConstants.unityPlayer.resume();
        super.onResume();
    }

    @Override
    public void onPause() {
        UnityConstants.unityPlayer.pause();
        super.onPause();
    }

}
