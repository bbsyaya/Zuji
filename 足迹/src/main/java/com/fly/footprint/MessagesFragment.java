package com.fly.footprint;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessagesFragment extends Fragment {

    ListView listView_food;
    PublicAdapter adapter;
    ArrayList<String> list = new ArrayList<>();
    public MessagesFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_messages,container,false);
        listView_food = (ListView) view.findViewById(R.id.listView_food);
        MainActivity messages = (MainActivity) getActivity(); //获取活动
        list.clear();
        for (int i = 0 ; i< 15; i++){
            list.add("测试"+i);
        }
        adapter = new PublicAdapter(messages,list);
        listView_food.setAdapter(adapter);

        return view;
    }

}
