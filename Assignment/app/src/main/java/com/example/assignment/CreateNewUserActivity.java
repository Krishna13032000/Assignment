package com.example.assignment;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.assignment.databinding.ActivityCreateNewUserBinding;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CreateNewUserActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    ActivityCreateNewUserBinding activityCreateNewUserBinding;
    CreateNewUserViewModel createNewUserViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCreateNewUserBinding = DataBindingUtil.setContentView(this,R.layout.activity_create_new_user);
        activityCreateNewUserBinding.setLifecycleOwner(this);
        firebaseAuth = FirebaseAuth.getInstance();
        getSupportActionBar().hide();
        createNewUserViewModel = new ViewModelProvider(this).get(CreateNewUserViewModel.class);
        activityCreateNewUserBinding.setViewmodel(createNewUserViewModel);
        activityCreateNewUserBinding.setClickHandler(new ClickHandler());
    }
    public class ClickHandler{
        public void createUser(View view, String username, String password){
            firebaseAuth.createUserWithEmailAndPassword(username, password)
                    .addOnCompleteListener(CreateNewUserActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getApplicationContext(), "Account created successfully", Toast.LENGTH_LONG).show();
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"Some error has occured please try again after sometime",Toast.LENGTH_LONG).show();
                            }
                        }
                    }).addOnFailureListener(CreateNewUserActivity.this, new OnFailureListener() {
                @Override
                public void onFailure( Exception e) {
                    e.printStackTrace();
                }
            })
            .addOnCanceledListener(CreateNewUserActivity.this, new OnCanceledListener() {
                @Override
                public void onCanceled() {
                }
            });
            finish();
        }
    public void cancel(){
        finish();
        }
    }
}