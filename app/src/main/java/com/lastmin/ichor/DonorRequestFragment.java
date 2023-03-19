package com.lastmin.ichor;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.lastmin.ichor.adapters.RequestAdapter;
import com.lastmin.ichor.adapters.RequestUserAdapter;
import com.lastmin.ichor.domains.Request;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DonorRequestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DonorRequestFragment extends Fragment {
    private List<Request> datarreq;
    RequestUserAdapter userAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DonorRequestFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DonorRequestFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DonorRequestFragment newInstance(String param1, String param2) {
        DonorRequestFragment fragment = new DonorRequestFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        datarreq  = new ArrayList<>();


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_donor_request, container, false);
        RecyclerView rv = view.findViewById(R.id.userReqrv);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        getData();
        userAdapter = new RequestUserAdapter(datarreq,getContext());
        rv.setAdapter(userAdapter);
        userAdapter.notifyDataSetChanged();
        view.findViewById(R.id.checkbutton).setOnClickListener(viewtest->{
            getData();
            userAdapter.notifyDataSetChanged();
        });

        // Inflate the layout for this fragment
        return view;
    }

    private void getData() {
        FirebaseDatabase.getInstance().getReference("Requests").get()
                .addOnSuccessListener(dataSnapshot -> {
                    for(DataSnapshot dorg:dataSnapshot.getChildren()){
                        for(DataSnapshot d: dorg.getChildren()){
                            Request request = d.getValue(Request.class);
                            System.out.println(request.toString());
                            datarreq.add(request);
                        }
                    }
                });
    }
}