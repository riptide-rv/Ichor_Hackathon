package com.lastmin.ichor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.lastmin.ichor.databinding.ActivityDonorBinding;

public class DonorActivity extends AppCompatActivity {
    ActivityDonorBinding activityDonorBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDonorBinding = ActivityDonorBinding.inflate(getLayoutInflater());


        setContentView(activityDonorBinding.getRoot());


    }

    private void startLoginActivity() {
        startActivity(new Intent(DonorActivity.this,MainActivity.class));
        finish();
    }


}