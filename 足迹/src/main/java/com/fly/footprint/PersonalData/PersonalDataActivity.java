package com.fly.footprint.PersonalData;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fly.footprint.R;

public class PersonalDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        setAction(R.layout.actionbar_personaldate,this);
        return true;
    }
    private void setAction(int layoutId, Context context){
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayShowHomeEnabled(false);
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            LayoutInflater inflater = (LayoutInflater) this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(layoutId, new LinearLayout(context), false);
            ActionBar.LayoutParams layout = new ActionBar.LayoutParams(
                    ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
            actionBar.setCustomView(v, layout);

            TextView tv_back = (TextView) v.findViewById(R.id.personal_back);
            TextView tv_more = (TextView) v.findViewById(R.id.personal_more);

            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v.getId() == R.id.personal_back){
                        onBackPressed();
                    }else {
                        Toast.makeText(PersonalDataActivity.this, "更多", Toast.LENGTH_SHORT).show();
                    }
                }
            };
            tv_back.setOnClickListener(listener);
            tv_more.setOnClickListener(listener);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
