package com.example.jiashunz.mobilecalllog;

import android.support.v7.widget.RecyclerView;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by zhoujiashun on 1/31/18.
 */

public class CallListAdapter extends RecyclerView.Adapter {
    private List<Call> data;

    public CallListAdapter(@NonNull List<Call> data) {
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.call_info_item, parent, false);
        return new CallListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Call call = data.get(position);
        ((CallListViewHolder) holder).phoneNumberTextView.setText(call.phoneNumber);
        ((CallListViewHolder) holder).timeStampTextView.setText(call.timeStamp);
        ((CallListViewHolder) holder).callDirectionTextView.setText(call.callDirection);
    }

    /**
     * This method is used to return data size.
     * @return data size
     */
    public int getItemCount() {
        return data.size();
    }
}
