package com.lastmin.ichor;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.lastmin.ichor.databinding.ActivityDonorUserBinding;

public class DonorUserActivity extends AppCompatActivity {

    private ActivityDonorUserBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDonorUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //Bundle bundle = getIntent().getExtras().getBundle("userdata");
       DonorProfileFragment donorProfileFragment = new DonorProfileFragment();
       donorProfileFragment.setDonorUserActivity(DonorUserActivity.this);
      // donorProfileFragment.setArguments(bundle);


        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.donorframe,donorProfileFragment)
                .commit();
    }

}