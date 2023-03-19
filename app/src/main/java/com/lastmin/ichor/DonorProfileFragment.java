package com.lastmin.ichor;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lastmin.ichor.domains.DonorUser;
import com.lastmin.ichor.domains.User;


public class DonorProfileFragment extends Fragment {

    DatabaseReference dbref;
    FirebaseAuth mauth;
    String name;









    public DonorProfileFragment() {
        // Required empty public constructor
    }






    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);










    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_donor_profile, container, false);
        mauth = FirebaseAuth.getInstance();
        dbref =FirebaseDatabase.getInstance().getReference("Donors")
                .child(mauth.getCurrentUser().getUid().toString());
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get the user object from the snapshot
                DonorUser donorUser= dataSnapshot.getValue(DonorUser.class);
                if(donorUser==null){
                    System.out.println("null");
                }
                System.out.println(donorUser.toString());
                System.out.println(donorUser.getName()+ " nameee");
               // EditText tvName = (EditText)view.findViewById(R.id.etDonorName);
                //tvName.setText(name);
                name = String.valueOf( donorUser.getName());
                TextView tvCal = view.findViewById(R.id.tv_age_of_donor);
                TextView tvName = view.findViewById(R.id.tv_name_of_donor);
                TextView tvBg = view.findViewById(R.id.tv_bg_of_donor);
                tvBg.setText(String.valueOf(donorUser.getBloodgroup()));
                tvName.setText(name);
                tvCal.setText(String.valueOf(donorUser.getAge()));



                // Do something with the user object, such as displaying it in a UI element
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors heres
                System.out.println("Error");
            }
        });






        return view ;
    }
}