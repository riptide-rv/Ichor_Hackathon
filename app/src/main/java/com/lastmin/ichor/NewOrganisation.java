package com.lastmin.ichor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lastmin.ichor.databinding.ActivityNewOrganisationBinding;
import com.lastmin.ichor.domains.OrganisationUser;
import com.lastmin.ichor.domains.User;

public class NewOrganisation extends AppCompatActivity {
    ActivityNewOrganisationBinding binding;
    EditText etNameOrg,etEmailOrg,etPhoneOrg,etAddressOrg;
    FirebaseAuth mauth;
    FirebaseDatabase db;
    DatabaseReference myref;
    DatabaseReference orgref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewOrganisationBinding.inflate(getLayoutInflater());
        etNameOrg = binding.etNameOrg;
        etEmailOrg = binding.etEmailOrg;
        etPhoneOrg = binding.etPhoneOrg;
        etAddressOrg = binding.etAddressOrg;
        String email = getIntent().getStringArrayListExtra("info").get(0);
        String pass = getIntent().getStringArrayListExtra("info").get(1);
        mauth = FirebaseAuth.getInstance();
        myref = FirebaseDatabase.getInstance().getReference().child("Users");
        orgref = FirebaseDatabase.getInstance().getReference().child("Orgs");
        binding.buCreateOrg.setOnClickListener(view->{
            mauth.createUserWithEmailAndPassword(email,pass).addOnSuccessListener(
                    task->{
                       String id =  task.getUser().getUid();
                       myref.child("Users").setValue(new User(email,1));
                        OrganisationUser organisationUser = new OrganisationUser(
                                String.valueOf(etNameOrg.getText()),
                                String.valueOf(etEmailOrg.getText()),
                                String.valueOf(etPhoneOrg.getText()),
                                String.valueOf(etAddressOrg.getText())

                        );
                        orgref.child(id).setValue(organisationUser);
                        Intent intent = new Intent(NewOrganisation.this,OrganisationActivity.class)
                                .putExtra("id",id);
                        startActivity(intent);
                        finish();

                    }
            );
        });



        setContentView(binding.getRoot());


    }
}