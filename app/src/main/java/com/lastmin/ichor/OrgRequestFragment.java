package com.lastmin.ichor;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lastmin.ichor.adapters.RequestAdapter;
import com.lastmin.ichor.domains.OrganisationUser;
import com.lastmin.ichor.domains.Request;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrgRequestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrgRequestFragment extends Fragment {
    OrganisationUser organisationUser;
    List<Request> datareqs;
    RequestAdapter requestAdapter;

    public OrganisationUser getOrganisationUser() {
        return organisationUser;
    }

    public void setOrganisationUser(OrganisationUser organisationUser) {
        this.organisationUser = organisationUser;
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OrgRequestFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrgRequestFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrgRequestFragment newInstance(String param1, String param2) {
        OrgRequestFragment fragment = new OrgRequestFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        datareqs = new ArrayList<>();
        FirebaseDatabase.getInstance()
                .getReference("Requests/"+FirebaseAuth.getInstance()
                        .getCurrentUser().getUid()).get()
                .addOnSuccessListener(dataSnapshot -> {
                    for(DataSnapshot d:dataSnapshot.getChildren()){
                        Request request = d.getValue(Request.class);
                        datareqs.add(request);
                    }
                });
        requestAdapter = new RequestAdapter(datareqs,getContext());


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_org_request, container, false);
        Button buNewReqOrg = view.findViewById(R.id.buNewReq);
        EditText bg = view.findViewById(R.id.etNewRegBG);
        EditText desc = view.findViewById(R.id.etNewReqDesc);
        RecyclerView rv =view.findViewById(R.id.orgReqrv);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(requestAdapter);
        requestAdapter.notifyDataSetChanged();
        buNewReqOrg.setOnClickListener(v->{
           Request newReq = new Request("dummy",String.valueOf(bg.getText()),String.valueOf(desc.getText()));
           datareqs.add(newReq);
            requestAdapter.notifyItemChanged(datareqs.size()-1);


           FirebaseDatabase.getInstance()
                    .getReference("Requests/"+FirebaseAuth.getInstance()
                            .getCurrentUser().getUid())

                    .push()
                    .setValue(newReq);
        });
        // Inflate the layout for this fragment
        return view;
    }
}