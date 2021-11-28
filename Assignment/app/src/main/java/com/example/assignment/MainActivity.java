package com.example.assignment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.assignment.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    ActivityMainBinding activityMainBinding;
    MainActivityViewModel mainActivityViewModel;
    FirebaseUser firebaseUser;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        sharedPreferences = getSharedPreferences("com.example.assignment", Context.MODE_PRIVATE);
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        activityMainBinding.setLifecycleOwner(this);
        activityMainBinding.setViewmodel(mainActivityViewModel);
        activityMainBinding.executePendingBindings();
        activityMainBinding.setClickHandler(new ClickHandler());
        init();
    }
    public void init(){
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
    }

    @Override
    protected void onResume() {
        super.onResume();
        init();
    }

    public class ClickHandler{

        public void submitData(View view, String username, String password) {
            if (firebaseUser != null) {
                if (firebaseUser.getEmail().equals(username)) {
                    activityMainBinding.setIsLoggedIn(true);
                }
                else {
                    Snackbar.make(view,"Only one mail id can be used to log in. Please use the mail id which was used previously used in this device",Snackbar.LENGTH_SHORT).show();
                }
            } else {
                Snackbar.make(view,"Please create an account to access this feature",Snackbar.LENGTH_SHORT).show();
            }
            activityMainBinding.executePendingBindings();
        }
        public void onAlertDialog(View view,String username,String password) {
            if(firebaseUser==null){
                startActivity(new Intent(MainActivity.this,CreateNewUserActivity.class));
            }
            else{
                Snackbar.make(view,"You already have an account on this device",Snackbar.LENGTH_SHORT).show();
            }
        }
    }
}