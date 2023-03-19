package com.lastmin.ichor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.google.android.gms.tasks.SuccessContinuation;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lastmin.ichor.databinding.ActivityRegActivtityUserBinding;
import com.lastmin.ichor.domains.DonorUser;
import com.lastmin.ichor.domains.User;

import java.util.ArrayList;

public class RegActivtityUser extends AppCompatActivity {
    ActivityRegActivtityUserBinding binding;
    FirebaseDatabase db;
    DatabaseReference userref,donorref;
    EditText etName,etAge,etEmail,etPhone,etAddress,etBloodGroup;
    FirebaseAuth mauth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegActivtityUserBinding.inflate(getLayoutInflater());
        mauth = FirebaseAuth.getInstance();
        etName = binding.etName;
        etAddress = binding.etAddress;
        etAge = binding.etAge;
        etBloodGroup = binding.etBloodGroup;
        etPhone  = binding.etPhone;
        etEmail = binding.etEmail;

        db = FirebaseDatabase.getInstance();
        userref = db.getReference().child("Users");
        donorref = db.getReference().child("Donors");


        setContentView(binding.getRoot());



        binding.buNewUser.setOnClickListener(view->{
            createNewUser();



        });
    }

    private void createNewUser() {
        ArrayList<String> info = getIntent().getStringArrayListExtra("info");
        String email = info.get(0);

        String pass = info.get(1);
        System.out.println(email+ "  "+pass);



       mauth.createUserWithEmailAndPassword(email,pass).addOnSuccessListener(task->{
            mauth.signInWithEmailAndPassword(email,pass).addOnSuccessListener(
                   task2->{
                       userref.child(mauth.getCurrentUser().getUid()).setValue(new User(email,0));
                        DonorUser donorUser= new DonorUser(String.valueOf(etName.getText()),Integer.parseInt(String.valueOf(etAge.getText())),String.valueOf( etEmail.getText()),
                               String.valueOf(etPhone.getText()),String.valueOf( etAddress.getText()),String.valueOf( etBloodGroup.getText()));
                        donorref.child(mauth.getCurrentUser().getUid()).setValue(donorUser);
                       Bundle bundle = new Bundle();
                       bundle.putString("name",donorUser.getName());
                       bundle.putString("email",donorUser.getEmail());
                       bundle.putInt("age",donorUser.getAge());
                       bundle.putString("phone",donorUser.getPhone());
                       bundle.putString("bg",donorUser.getPhone());
                       bundle.putString("address",donorUser.getAddress());

                       Intent intent = new Intent(RegActivtityUser.this,DonorUserActivity.class).putExtra("userdata",bundle);
                       startActivity(intent);
                       finish();
                   }
            );
        });










    }
}