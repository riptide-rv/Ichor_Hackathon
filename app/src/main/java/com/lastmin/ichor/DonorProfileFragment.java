package com.lastmin.ichor;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.lastmin.ichor.domains.DonorUser;


public class DonorProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String name,email,address,phone,bloodgroup;
    private int age;
    FirebaseAuth mauth;
    private String id;
    DonorUser donorUser;


    public String getuid() {
        return id;
    }

    public void setuid(String id) {
        this.id = id;
    }

    public DonorProfileFragment() {
        // Required empty public constructor
    }
    private DonorUserActivity donorUserActivity;

    public DonorUserActivity getDonorUserActivity() {
        return donorUserActivity;
    }

    public void setDonorUserActivity(DonorUserActivity donorUserActivity) {
        this.donorUserActivity = donorUserActivity;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment DonorProfileFragment.
     */
    // TODO: Rename and change types and number of parameters


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_donor_profile, container, false);
        // Inflate the layout for this fragment
        TextView tvName = view.findViewById(R.id.tvName);

        view.findViewById(R.id.buLogOut).setOnClickListener(view2->{
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(donorUserActivity,MainActivity.class));
            donorUserActivity.finish();
        });
        return view ;
    }
}