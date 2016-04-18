package com.fly.footprint;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


public class ContactsFragment extends Fragment {

    ListView listView_frontiers;
    PublicAdapter adapter;
    ArrayList<String> list = new ArrayList<>();
    public ContactsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts,container,false);
        listView_frontiers = (ListView) view.findViewById(R.id.listView_frontiers);
        MainActivity contacts = (MainActivity) getActivity();
        list.clear();
        for (int i = 0 ; i < 15 ; i++){
            list.add("测试"+i);
        }
        adapter = new PublicAdapter(contacts,list);
        listView_frontiers.setAdapter(adapter);
        return view;
    }
}
