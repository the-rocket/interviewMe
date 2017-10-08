package com.hackathon.interviewme.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.hackathon.interviewme.ChatActivity;
import com.hackathon.interviewme.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by yernar on 07/10/2017.
 */

public class JobFragment extends Fragment {

    Data data = new Data();
    ListView listview;
    customAdapter listAdapter;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_job, container, false);
        init();
        return view;
    }

    void init() {
        listview = view.findViewById(R.id.listView);
        listAdapter = new customAdapter(getContext(), getDATA());
        listview.setAdapter(listAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), ChatActivity.class);
                intent.putExtra("pos",i);
                intent.putExtra("cot","jobs");
                startActivity(intent);
            }
        });
        initview();
    }

    void initview() {

    }

    ArrayList<String> getDATA() {
        ArrayList<String> ddd = new ArrayList<>();
        for (String s : data.company)
            ddd.add(s);
        return ddd;
    }

    private class customAdapter extends ArrayAdapter<String> {

        private customAdapter(Context context, ArrayList<String> len) {
            super(context, R.layout.row_jobs, len);

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            JSONArray jsonArray = null;
            try {
                jsonArray = data.data.getJSONArray("jobs");
            } catch (Exception e) {
                e.getMessage();
            }
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_jobs, parent, false);
            ImageView img = convertView.findViewById(R.id.row_image);
            TextView company = convertView.findViewById(R.id.company_name);
            TextView person = convertView.findViewById(R.id.person_name);
            TextView last_message = convertView.findViewById(R.id.last_message);
            try {
                JSONObject jg = jsonArray.getJSONObject(position);
                company.setText(jg.getString("company"));
                person.setText(jg.getString("person"));
                last_message.setText(jg.getString("message"));
                RoundedBitmapDrawable circularBitmapDrawable =
                    RoundedBitmapDrawableFactory.create(getContext().getResources(), findIMG());
                circularBitmapDrawable.setCircular(true);
                img.setImageDrawable(circularBitmapDrawable);
            } catch (Exception e) {
                e.getMessage();
            }
            return convertView;
        }
        Bitmap findIMG() {
//            switch(comp_name) {
//                case "": return null;
//                case "Google": return ContextCompat.getDrawable(getContext(), R.drawable.compgoogle);
//                case "Facebook": return ContextCompat.getDrawable(getContext(), R.drawable.compfacebook);
//                case "Twitter": return ContextCompat.getDrawable(getContext(), R.drawable.comptwitter);
//                case "Dell": return ContextCompat.getDrawable(getContext(), R.drawable.compdell);
//                case "Microsoft": return ContextCompat.getDrawable(getContext(), R.drawable.compmicrosoft);
            //}
            Random r = new Random();
            int red=r.nextInt(255);
            int green=r.nextInt(255);

            int blue=r.nextInt(255);
            Bitmap image = Bitmap.createBitmap(30, 30, Bitmap.Config.ARGB_8888);
            image.eraseColor(Color.BLUE);
            return image;
        }
    }

}
