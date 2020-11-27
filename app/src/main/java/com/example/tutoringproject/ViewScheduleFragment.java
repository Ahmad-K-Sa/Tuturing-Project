package com.example.tutoringproject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.tutoringproject.R;

import java.util.ArrayList;

public class ViewScheduleFragment extends Fragment {
    int TUTOR_ID;
    SQLiteDatabase db;
    Cursor cursor;
    View v;

    public ViewScheduleFragment(int TUTOR_ID) {
        this.TUTOR_ID = TUTOR_ID;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        db.close();
        cursor.close();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_view_scheduel, container, false);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        ListView lv = v.findViewById(R.id.ListViewSched);
        SQLiteOpenHelper helper = new DatabaseSQLiteOpenHelper(getContext());
        db = helper.getWritableDatabase();
        cursor = db.query("SCHEDULES", new String[]{"_id", "TIME", "SUBJECT"}, null, null, null, null, null);
        ArrayList<String> SchedItems = new ArrayList<>();
        if (cursor.moveToFirst()) {
            while (!cursor.isLast()) {
                SchedItems.add(cursor.getString(1) + "\n" + cursor.getString(2));
                cursor.moveToNext();
            }
            SchedItems.add(cursor.getString(1) + "\n" + cursor.getString(2));
            ArrayAdapter ItemsToDisplay = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, SchedItems);

            lv.setAdapter(ItemsToDisplay);
        }
    }
}