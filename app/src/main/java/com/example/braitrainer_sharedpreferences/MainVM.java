package com.example.braitrainer_sharedpreferences;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class MainVM extends AndroidViewModel {
    private static final String TAG = "MainVM";


    private MutableLiveData<Riddle> riddle = new MutableLiveData<>();


    public MainVM(@NonNull Application application) {
        super(application);
        riddle.setValue(new Riddle());
    }

    public MutableLiveData<Riddle> getRiddle() {
        return riddle;
    }

    public void nextRiddle(){
        riddle.setValue(new Riddle());
    }





}
