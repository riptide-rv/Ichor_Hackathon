package com.lastmin.ichor;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.lastmin.ichor.databinding.ActivityDonorUserBinding;
import com.lastmin.ichor.domains.DonorUser;
import com.lastmin.ichor.domains.Request;
import com.lastmin.ichor.ui.home.HomeFragment;

import java.util.ArrayList;
import java.util.List;

public class DonorUserActivity extends AppCompatActivity {

    private ActivityDonorUserBinding binding;
    Toolbar navBar;
    DonorUser donorUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDonorUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        DonorProfileFragment donorProfileFragment = new DonorProfileFragment();




       DonorHomeFragment donorHomeFragment = new DonorHomeFragment();
       DonorRequestFragment donorRequestFragment = new DonorRequestFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.donorframe, donorProfileFragment)
                .commit();
       navBar =binding.navDonor;
       navBar.setOnMenuItemClickListener(task->{
           switch (task.getItemId()){
               case R.id.profile:
                   getSupportFragmentManager().beginTransaction()
                           .replace(R.id.donorframe,donorProfileFragment)
                           .commit();
                   break;
              /* case R.id.home:
                   getSupportFragmentManager().beginTransaction()
                           .replace(R.id.donorframe,donorHomeFragment)
                           .commit();
                   break;*/
               case R.id.donate:
                   getSupportFragmentManager().beginTransaction()
                           .replace(R.id.donorframe,donorRequestFragment)
                           .commit();
                   break;

               case R.id.userlogout:
                   FirebaseAuth.getInstance().signOut();
                   startActivity(new Intent(getApplicationContext(),MainActivity.class));
                   finish();
                   break;



           }
           return true;
       });
      // donorProfileFragment.setArguments(bundle);



    }



}