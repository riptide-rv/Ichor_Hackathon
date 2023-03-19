package com.lastmin.ichor;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.lastmin.ichor.databinding.ActivityDonorUserBinding;
import com.lastmin.ichor.ui.home.HomeFragment;

public class DonorUserActivity extends AppCompatActivity {

    private ActivityDonorUserBinding binding;
    Toolbar navBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDonorUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //Bundle bundle = getIntent().getExtras().getBundle("userdata");
       DonorProfileFragment donorProfileFragment = new DonorProfileFragment();
       donorProfileFragment.setDonorUserActivity(DonorUserActivity.this);
       DonorHomeFragment donorHomeFragment = new DonorHomeFragment();
       DonorRequestFragment donorRequestFragment = new DonorRequestFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.donorframe, donorHomeFragment)
                .commit();
       navBar =binding.navDonor;
       navBar.setOnMenuItemClickListener(task->{
           switch (task.getItemId()){
               case R.id.profile:
                   getSupportFragmentManager().beginTransaction()
                           .replace(R.id.donorframe,donorProfileFragment)
                           .commit();
                   break;
               case R.id.home:
                   getSupportFragmentManager().beginTransaction()
                           .replace(R.id.donorframe,donorHomeFragment)
                           .commit();
                   break;
               case R.id.donate:
                   getSupportFragmentManager().beginTransaction()
                           .replace(R.id.donorframe,donorRequestFragment)
                           .commit();
                   break;



           }
           return true;
       });
      // donorProfileFragment.setArguments(bundle);



    }

}