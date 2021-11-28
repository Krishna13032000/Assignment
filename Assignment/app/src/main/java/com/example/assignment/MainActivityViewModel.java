package com.example.assignment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class MainActivityViewModel extends AndroidViewModel {

    private MutableLiveData<String> username;

    private MutableLiveData<String> password;

    public MainActivityViewModel(Application application) {
        super(application);
        init();
    }
    public void init(){

        username = new MutableLiveData<>("");
        password = new MutableLiveData<>("");

    }
    public MutableLiveData<String> getUsername() {
        return username;
    }

    public void setUsername(MutableLiveData<String> username) {
        this.username = username;
    }

    public MutableLiveData<String> getPassword() {
        return password;
    }

    public void setPassword(MutableLiveData<String> password) {
        this.password = password;
    }
}
