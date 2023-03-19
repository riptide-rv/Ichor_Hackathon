package com.lastmin.ichor.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.lastmin.ichor.R;
import com.lastmin.ichor.domains.Request;

import java.util.List;

public class RequestUserAdapter extends RecyclerView.Adapter<RequestUserAdapter.RequestViewHolder> {

    private List<Request> requestList;
    private Context context;

    public RequestUserAdapter(List<Request> requestList, Context context) {
        this.requestList = requestList;
        this.context = context;
    }


    @Override
    public RequestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.request_item_layout, parent, false);
        return new RequestViewHolder(view);
    }

    @Override
    public void onBindViewHolder( RequestViewHolder holder, int position) {
        Request request = requestList.get(position);

        holder.tvName.setText(request.getOrgName());
        holder.tvDescription.setText(request.getDescription());
        holder.tvBg.setText(request.getBloodGroup());

        holder.btn.setOnClickListener(view -> {
            // Handle button click here
        });
    }

    @Override
    public int getItemCount() {
        return requestList.size();
    }

    public static class RequestViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvDescription, tvBg;
        Button btn;

        public RequestViewHolder( View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvnameorgreq);
            tvDescription = itemView.findViewById(R.id.tv_description);
            tvBg = itemView.findViewById(R.id.tv_bg);
            btn = itemView.findViewById(R.id.btn);
        }
    }
}