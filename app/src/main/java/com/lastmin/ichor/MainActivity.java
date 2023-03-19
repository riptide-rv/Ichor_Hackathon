package com.lastmin.ichor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lastmin.ichor.databinding.ActivityMainBinding;
import com.lastmin.ichor.domains.User;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    EditText etEmailMain,etPassMain;
    Button buLoginMain;
    TextView linkNewUser;
    DatabaseReference userref;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());FirebaseAuth.getInstance();
        mAuth = FirebaseAuth.getInstance();
        userref = FirebaseDatabase.getInstance().getReference("Users");
        currentUser = mAuth.getCurrentUser();
        etEmailMain = binding.etEmailMain;
        etPassMain = binding.etPassMain;
        linkNewUser = binding.linkSignUp;
        linkNewUser.setOnClickListener(view->{
            startNewUserActivity();
        });
        buLoginMain= (Button) binding.buLoginMain;
        buLoginMain.setOnClickListener(view -> {
            if(TextUtils.isEmpty(etEmailMain.getText())){
                Toast.makeText(getApplicationContext(),"enter a mail id",Toast.LENGTH_SHORT).show();
                return;
            }else if(TextUtils.isEmpty(etPassMain.getText())){
                Toast.makeText(getApplicationContext(),"enter password",Toast.LENGTH_SHORT).show();
                return;
            }
            String email = String.valueOf(etEmailMain.getText());
            String pass = String.valueOf(etPassMain.getText());
            mAuth = FirebaseAuth.getInstance();

            mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    currentUser  = mAuth.getCurrentUser();
                    userref = FirebaseDatabase.getInstance().getReference("Users")
                            .child(currentUser.getUid());
                    userref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            User user3 = snapshot.getValue(User.class);
                           // assert user3 != null;
                            if(user3.getMode()==0){
                                startUserActivity();
                            }else{
                                startOrgActivity();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                }else{
                    String msg = "invalid credentials";
                    if(pass.length()<6){
                        msg = "password should be min 6";
                    }

                    Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
                    Log.d("exception in Login",task.getException().getMessage());
                }
            });
        });


    }

    private void startNewUserActivity() {
        startActivity(new Intent(MainActivity.this,NewUser.class));
        finish();
    }

    @Override
    protected void onStart(){
        super.onStart();
        currentUser = mAuth.getCurrentUser();


        if(currentUser!=null){

            userref.child(currentUser.getUid()).get().addOnSuccessListener(
                    task->{

                        User user1 = task.getValue(User.class);

                        if(user1.getMode()==0){
                            startUserActivity();
                        }else {
                            startOrgActivity();
                        }

                    }
            );


        }

    }

    private void startOrgActivity() {
        startActivity(new Intent(getApplicationContext(),OrganisationActivity.class)
                .putExtra("id",currentUser.getUid()));
        finish();
    }

    public  void startUserActivity(){
        startActivity(new Intent(getApplicationContext(),DonorUserActivity.class)
                .putExtra("id",currentUser.getUid()));
        finish();
    }
}