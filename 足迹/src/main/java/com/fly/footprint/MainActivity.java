package com.fly.footprint;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fly.footprint.Finds.FindFragment;
import com.fly.footprint.PersonalData.PersonalDataActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;

    TextView tv_messages;
    TextView tv_contacts;
    TextView tv_find;
    FootprintFragmentPagerAdapter pagerAdapter;
    ArrayList<Fragment> fragments = new ArrayList<>();
    ViewPager pager;
    MessagesFragment messages; //
    ContactsFragment contacts; //
    FindFragment find; //
    ChangeColor changeColor;
    /*
    * 我的好友
    * 我的相册
    * 我的评论
    * 我的赞
    *
    * 我的分享
    * 我的收藏
    * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        changeColor = new ChangeColor();
        ViewPager();
        setDrawer();
    }

    public void setDrawer() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setTitle("发现");
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.addHeaderView(setHeader());
        navigationView.setNavigationItemSelectedListener(this);

        /*StatusBarCompat.compat(this,getResources().getColor(R.color.colorPrimaryDark));
        StatusBarCompat.compat(this);*/
    }

    public View setHeader() {
        View view = LayoutInflater.from(this).inflate(R.layout.nav_header_main, null);
        ImageView head = (ImageView) view.findViewById(R.id.head);
        TextView nickname = (TextView) view.findViewById(R.id.nickname);
        TextView autograph = (TextView) view.findViewById(R.id.autograph);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.head) {
                    System.out.println("登录");
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }else if (v.getId() == R.id.nickname){
                    System.out.println("个人信息");
                    Intent intent = new Intent(MainActivity.this, PersonalDataActivity.class);
                    startActivity(intent);
                }
            }
        };
        head.setOnClickListener(listener);
        nickname.setOnClickListener(listener);
        return view;
    }

    public void ViewPager() {
        tv_messages = (TextView) findViewById(R.id.tv_messages);
        tv_contacts = (TextView) findViewById(R.id.tv_contacts);
        tv_find = (TextView) findViewById(R.id.tv_find);

        messages = new MessagesFragment();
        contacts = new ContactsFragment();
        find = new FindFragment();
//        EaseConversationListFragment Conversation = new EaseConversationListFragment();
//        EaseContactListFragment Contact = new EaseContactListFragment();
        fragments.add(messages);
        fragments.add(contacts);
        fragments.add(find);

        pager = (ViewPager) findViewById(R.id.viewpager);
        pagerAdapter = new FootprintFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        pager.setAdapter(pagerAdapter);

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    changeColor.Messages();
                    changeColor.NotContacts();
                    changeColor.NotFinds();
                    tv_messages.setTextColor(Color.parseColor("#FF9d55b8"));
                    tv_contacts.setTextColor(Color.parseColor("#000000"));
                    tv_find.setTextColor(Color.parseColor("#000000"));
                } else if (position == 1) {
                    changeColor.Contacts();
                    changeColor.NotMessages();
                    changeColor.NotFinds();
                    tv_contacts.setTextColor(Color.parseColor("#FF9d55b8"));
                    tv_messages.setTextColor(Color.parseColor("#000000"));
                    tv_find.setTextColor(Color.parseColor("#000000"));
                } else {
                    changeColor.Finds();
                    changeColor.NotMessages();
                    changeColor.NotContacts();
                    tv_find.setTextColor(Color.parseColor("#FF9d55b8"));
                    tv_messages.setTextColor(Color.parseColor("#000000"));
                    tv_contacts.setTextColor(Color.parseColor("#000000"));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 菜单栏
        getMenuInflater().inflate(R.menu.main, menu);
        //setActionBarLayout(R.layout.actionbar_login,this);
        return true;
    }

    @SuppressLint("NewApi")
    public void setActionBarLayout(int layoutId, Context context) {
        ActionBar actionBar = getSupportActionBar();
        if (null != actionBar) {
            actionBar.setDisplayShowHomeEnabled(false);
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

            LayoutInflater inflater = (LayoutInflater) this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(layoutId, new LinearLayout(context), false);

            ActionBar.LayoutParams layout = new ActionBar.LayoutParams(
                    ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
            actionBar.setCustomView(v, layout);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // 处理动作栏项目点击这里。动作栏将自动处理家庭/上下按钮的点击次数，
        // 只要你指定一个父活动 AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // 处理导航视图项目
        int id = item.getItemId();
        System.out.println("抽屉Item："+id);

        if (id == R.id.nav_friends) {
            //好友
        } else if (id == R.id.nav_albums) {
            //相册
        } else if (id == R.id.nav_comment) {
            //评论
        } else if (id == R.id.nav_praise) {
            //赞
        } else if (id == R.id.nav_setting) {
            //设置
        } else if (id == R.id.nav_share) {
            //分享
        }else if (id == R.id.nav_collection){
            //收藏
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    class ChangeColor{

        private void Messages(){
            Drawable Messages = getResources().getDrawable(R.drawable.messages_selected);
            Messages.setBounds(0, 0, Messages.getMinimumWidth(), Messages.getMinimumHeight());
            tv_messages.setCompoundDrawables(null, Messages, null, null);
        }
        private void NotMessages(){
            Drawable Messages_not = getResources().getDrawable(R.drawable.messages_not_selected);
            Messages_not.setBounds(0, 0, Messages_not.getMinimumWidth(), Messages_not.getMinimumHeight());
            tv_messages.setCompoundDrawables(null, Messages_not, null, null);
        }
        private void Contacts(){
            Drawable Contacts = getResources().getDrawable(R.drawable.contacts_selected);
            Contacts.setBounds(0, 0, Contacts.getMinimumWidth(), Contacts.getMinimumHeight());
            tv_contacts.setCompoundDrawables(null, Contacts, null, null);
        }
        private void NotContacts(){
            Drawable Contacts_not = getResources().getDrawable(R.drawable.contacts_not_selected);
            Contacts_not.setBounds(0, 0, Contacts_not.getMinimumWidth(), Contacts_not.getMinimumHeight());
            tv_contacts.setCompoundDrawables(null, Contacts_not, null, null);
        }
        private void Finds(){
            Drawable Finds = getResources().getDrawable(R.drawable.finds_selected);
            Finds.setBounds(0, 0, Finds.getMinimumWidth(), Finds.getMinimumHeight());
            tv_find.setCompoundDrawables(null, Finds, null, null);
        }
        private void NotFinds(){
            Drawable Finds_not = getResources().getDrawable(R.drawable.finds_not_selected);
            Finds_not.setBounds(0, 0, Finds_not.getMinimumWidth(), Finds_not.getMinimumHeight());
            tv_find.setCompoundDrawables(null, Finds_not, null, null);
        }
    }

    public void change(View v) {

        if (v.getId() == R.id.tv_messages) {
            pager.setCurrentItem(0);
        } else if (v.getId() == R.id.tv_contacts) {
            pager.setCurrentItem(1);
        } else {
            pager.setCurrentItem(2);
        }
    }
}
