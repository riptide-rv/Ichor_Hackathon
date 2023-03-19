package com.lastmin.ichor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.text.TextUtils;
import android.widget.CheckBox;
import android.widget.EditText;

import com.lastmin.ichor.databinding.ActivityNewUserBinding;

import java.util.ArrayList;

public class NewUser extends AppCompatActivity {
    ActivityNewUserBinding activityNewUserBinding;
    EditText etEmailNewUser,etPassNewUser;
    CheckBox switchcheck;
    int check=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityNewUserBinding = ActivityNewUserBinding.inflate(getLayoutInflater());
        setContentView(activityNewUserBinding.getRoot());
        etEmailNewUser =activityNewUserBinding.etEmailNewUser;
        etPassNewUser = activityNewUserBinding.etPassNewUser;
        switchcheck = findViewById(R.id.switchcheck);

        activityNewUserBinding.linkMain.setOnClickListener(view2->{
            startManActivity();
        });

        activityNewUserBinding.buSignUp.setOnClickListener(view ->{

            startRegActivity();

        });
    }

    private void startManActivity() {
        startActivity(
                new Intent(NewUser.this,MainActivity.class));


        finish();

    }

    public void startRegActivity(){


        ArrayList<String> info = new ArrayList<>(2);
        info.add(String.valueOf(etEmailNewUser.getText()));
        info.add(String.valueOf(etPassNewUser.getText()));
        switchcheck = findViewById(R.id.switchcheck);
        if(switchcheck.isChecked()){
            startActivity(new Intent(NewUser.this,NewOrganisation.class)
                    .putStringArrayListExtra("info",info));
            finish();

        }else{

            startActivity(
                    new Intent(NewUser.this,RegActivtityUser.class)
                    .putStringArrayListExtra("info",info));

            finish();
        }
    }
}