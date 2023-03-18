package com.lastmin.ichor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.lastmin.ichor.databinding.ActivityRegActivtityUserBinding;

public class RegActivtityUser extends AppCompatActivity {
    ActivityRegActivtityUserBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegActivtityUserBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        String email = getIntent().getStringArrayListExtra("info").get(0);
        String pass = getIntent().getStringArrayListExtra("info").get(1);

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,pass);
    }
}