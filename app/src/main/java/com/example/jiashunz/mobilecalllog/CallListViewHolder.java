package com.example.jiashunz.mobilecalllog;

/**
 * Created by zhoujiashun on 1/31/18.
 */

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class CallListViewHolder extends RecyclerView.ViewHolder{
    TextView phoneNumberTextView;
    TextView timeStampTextView;
    TextView callDirectionTextView;

    public CallListViewHolder(@NonNull View itemView) {
        super(itemView);

        phoneNumberTextView = (TextView) itemView.findViewById(R.id.phone_number);
        timeStampTextView = (TextView) itemView.findViewById(R.id.time_stamp);
        callDirectionTextView = (TextView) itemView.findViewById(R.id.call_direction);
    }

}
