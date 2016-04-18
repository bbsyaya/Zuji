package com.fly.footprint.Finds;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fly.footprint.R;

import java.util.ArrayList;


public class FindFragment extends Fragment implements WaterfallAdapter.MyItemClickListener {

    RecyclerView recyclerView;
    WaterfallAdapter adapter;
    ArrayList<Find> finds = new ArrayList<>();
    public FindFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.RecyclerView);
        //StaggeredGridLayoutManager设置为2列，方向为垂直
        recyclerView.setLayoutManager(
                new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        initData();
        adapter = new WaterfallAdapter(finds);
        recyclerView.setAdapter(adapter);
        //设置item之间的间隔
        SpacesItemDecoration decoration = new SpacesItemDecoration(16);
        recyclerView.addItemDecoration(decoration);
        adapter.setOnItemClickListener(this);
        return view;
    }
    private void initData(){
        finds.clear();
        int[] images = {
                R.drawable.item_bg,
                R.drawable.f1,R.drawable.f2,R.drawable.f3,
                R.drawable.f4,R.drawable.f5,R.drawable.f6,
                R.drawable.f1,R.drawable.f2,R.drawable.f6,
                R.drawable.f4,R.drawable.f2,R.drawable.f5};

        for (int i = 0 ; i< images.length ; i++){
            Find find = new Find(images[i],"标题"+i);
            finds.add(find);
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        Find find = finds.get(position);
        if (find != null){
            Toast.makeText(getActivity(), find.getTitle(), Toast.LENGTH_SHORT).show();
        }
    }
}
