package com.lastmin.ichor;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lastmin.ichor.domains.OrganisationUser;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrgProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrgProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OrgProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrgProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrgProfileFragment newInstance(String param1, String param2) {
        OrgProfileFragment fragment = new OrgProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_org_profile, container, false);
        // Inflate the layout for this fragment
        FirebaseAuth mauth = FirebaseAuth.getInstance();
        DatabaseReference dref = FirebaseDatabase.getInstance().getReference("Orgs")
                .child(mauth.getCurrentUser().getUid());
        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                OrganisationUser organisationUser = snapshot.getValue(OrganisationUser.class);

                TextView tvName = view.findViewById(R.id.tv_name_of_org);
                TextView tvEmail = view.findViewById(R.id.tv_age_of_org);
                tvName.setText(String.valueOf(organisationUser.getName()));
                tvEmail.setText(String.valueOf(organisationUser.getEmail()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return view;
    }
}