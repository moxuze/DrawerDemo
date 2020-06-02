package com.moxtar_1s.android.demo.drawer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private NavController navController;
    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 设置新的 toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // navController 根据控制行为替换掉 HostFragment 中的 Fragment
        // navController 由于拿到了 R.id.host_fragment，故也拿到了其中包含的 navigation.xml
        // navigation.xml 拿到了三个 fragment 布局，并映射到对应的 Fragment
        navController = Navigation.findNavController(this, R.id.hostFragment);
        // drawerLayout 包含背景 HostFragment 以及导航 navigationView
        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        // appBarConfiguration 自动获取 toolbar 并将各个 Fragment 页面注册到其中
        appBarConfiguration = new AppBarConfiguration
//                .Builder(navController.getGraph())
                .Builder(R.id.textFragment, R.id.listFragment, R.id.pagerFragment)
                .setDrawerLayout(drawerLayout)
                .build();
        // 将 toolbar 和 navController 关联
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        // 将 navigationView 和 navController 关联
        // navigationView 中的 menu 将会自动根据 navigation.xml 中的各 id 来绑定对应的 Fragment
        NavigationView navigationView = findViewById(R.id.navigationView);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    // 汉堡图绑定拉出导航的事件
    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}