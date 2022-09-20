package com.example.testproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<RecylerModel> recyclerModelList=new ArrayList<>();
    RecyclerAdapter adapter;
    LinearLayout l1list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRecyclerView();
    }

    private void setRecyclerView(){
        recyclerView=findViewById(R.id.recycler_view);
        l1list=findViewById(R.id.l1list);
        LinearLayoutManager layoutManager=new LinearLayoutManager(MainActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

        recyclerModelList.add(new RecylerModel(R.drawable.one));
        recyclerModelList.add(new RecylerModel(R.drawable.two));
        recyclerModelList.add(new RecylerModel(R.drawable.three));
        recyclerModelList.add(new RecylerModel(R.drawable.four));

        adapter=new RecyclerAdapter(MainActivity.this,recyclerModelList);
        recyclerView.setAdapter(adapter);
       // l1list.addView(recyclerView);
        initBottomDots(adapter.getItemCount(),recyclerView);
    }

    private void initBottomDots(int itemCount, RecyclerView recyclerView) {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && itemCount>0) {
            LinearLayout l1=getBottomDotsLayout(itemCount);
            recyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                    int firstVisible=((LinearLayoutManager)recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                    int lastVisible=((LinearLayoutManager)recyclerView.getLayoutManager()).findLastVisibleItemPosition();

                    int total=0;
                    for(int j=firstVisible;j<lastVisible;j++){
                        total++;

                    }
                    transitionDots(l1,lastVisible,total);
                }
            });
        }

    }

    private void transitionDots(LinearLayout l1, int lastVisible, int total) {

    for(int i=0;i<l1.getChildCount();i++){
        if(l1.getChildAt(i) instanceof TextView){
            l1.getChildAt(i).setBackgroundResource(R.drawable.indicator0);

        }
    }
    for(int j=0;j<total;j++){
        if(lastVisible >=0){
            l1.getChildAt(lastVisible).setBackgroundResource(R.drawable.indicator1);
            lastVisible--;
        }
    }

    }

    private LinearLayout getBottomDotsLayout(int itemCount) {
        LinearLayout layout=new LinearLayout(MainActivity.this);

        layout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setGravity(Gravity.CENTER);
        for(int i=0;i<itemCount;i++){
            TextView textView=new TextView(MainActivity.this);
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(20,20);
            params.setMargins(3,2,3,2);
            textView.setLayoutParams(params);
            textView.setBackgroundResource(R.drawable.indicator0);
            layout.addView(textView);
        }
        l1list.addView(layout);
        return layout;
    }


}