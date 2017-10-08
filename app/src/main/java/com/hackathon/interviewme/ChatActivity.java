package com.hackathon.interviewme;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hackathon.interviewme.Fragment.Data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by yernar on 08/10/2017.
 */

public class ChatActivity extends AppCompatActivity {


    private RecyclerView.Adapter mAdapter;
    private ArrayList<Chat> mArrayList = new ArrayList<>();
    private RecyclerView recyclerView;
    Data dt;

    String cot;
    int position;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Bundle b = getIntent().getExtras();
        cot = b.getString("cot");
        position = b.getInt("pos");
        setdata();
        init();
    }

    void setdata() {
        try {
            JSONArray json = dt.data.getJSONArray(cot);
            JSONObject gson = (JSONObject) json.get(position);
            setTitle(gson.getString("person"));
            Chat ch = new Chat(gson.getString("message"), true);
            mArrayList.add(ch);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    void init() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        initview();
    }
    void initview() {
        setAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    public void setAdapter() {
        mAdapter = new ChatAdapter(ChatActivity.this, mArrayList);
        recyclerView.setAdapter(mAdapter);
    }
}
