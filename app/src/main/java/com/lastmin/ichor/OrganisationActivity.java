package com.lastmin.ichor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.lastmin.ichor.databinding.ActivityOrganisationBinding;

public class OrganisationActivity extends AppCompatActivity {


    private ActivityOrganisationBinding binding;
    Toolbar navBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityOrganisationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //Bundle bundle = getIntent().getExtras().getBundle("userdata");
        OrgProfileFragment orgProfileFragment = new OrgProfileFragment();
        //orgProfileFragment.setOrgUserActivity(OrgUserActivity.this);
        OrgHomeFragment orgHomeFragment = new OrgHomeFragment();
        OrgRequestFragment orgRequestFragment = new OrgRequestFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.organisationframe, orgHomeFragment)
                .commit();
        navBar =binding.navOrganisation;
        navBar.setOnMenuItemClickListener(task->{
            switch (task.getItemId()){
                case R.id.profileorg:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.organisationframe,orgProfileFragment)
                            .commit();
                    break;
                case R.id.homeorg:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.organisationframe,orgHomeFragment)
                            .commit();
                    break;
                case R.id.requestorg:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.organisationframe,orgRequestFragment)
                            .commit();
                    break;
                case R.id.logoutorg:
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    finish();
                    break;

            }
            return true;
        });
    }
}