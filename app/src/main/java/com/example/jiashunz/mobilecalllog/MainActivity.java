package com.example.jiashunz.mobilecalllog;

import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.os.Bundle;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.util.Log;
import android.Manifest;


import com.example.jiashunz.mobilecalllog.Call;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;


public class MainActivity extends AppCompatActivity {
    final int PERMISSIONS_REQUEST_READ_CALL_LOG = 1;
    final String PERMISSIONS_READ_CALL_LOG = "android.permission.READ_CALL_LOG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUI();
    }

    private void setupUI() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.call_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Call> calls = new ArrayList<>();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CALL_LOG}, PERMISSIONS_REQUEST_READ_CALL_LOG);
        } else {
           calls = getData();
        }

        recyclerView.setAdapter(new CallListAdapter(calls));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        for (int index = 0; index < permissions.length; index++) {
            if (permissions[index].equals(PERMISSIONS_READ_CALL_LOG) && grantResults[index] == 0) {
                setupUI();
                break;
            }
        }
    }


    @NonNull
    private List<Call> getData() {

        List<Call> calls = new ArrayList<>();

        //Log.i("breakpoint", "here");

        //cursor = getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, null);
        Cursor cursor = managedQuery(CallLog.Calls.CONTENT_URI, null, null, null, null);

        int numberIndex = cursor.getColumnIndex(CallLog.Calls.NUMBER);
        int dateIndex = cursor.getColumnIndex(CallLog.Calls.DATE);
        int directionIndex = cursor.getColumnIndex(CallLog.Calls.TYPE);
        int total = 0;

        while (cursor.moveToNext() && total < 50) {
            total++;
            String phoneNumber = cursor.getString(numberIndex);
            String date = cursor.getString(dateIndex);
            String callDirection = cursor.getString(directionIndex);
            Date timeStamp = new Date(Long.valueOf(date));
            int direction = Integer.parseInt(callDirection);
            String dir = null;

            switch (direction) {
                case CallLog.Calls.OUTGOING_TYPE:
                    dir = "OUTGOING";
                    break;

                case CallLog.Calls.INCOMING_TYPE:
                    dir = "INCOMING";
                    break;

                case CallLog.Calls.MISSED_TYPE:
                    dir = "MISSED";
                    break;
            }
            calls.add(new Call(phoneNumber, String.valueOf(timeStamp), dir));

        }
        return calls;
    }
}
