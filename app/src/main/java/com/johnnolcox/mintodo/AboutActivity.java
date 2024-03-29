package com.johnnolcox.mintodo;

import android.content.pm.PackageInfo;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {
    private TextView mVersionTextView;
    private String appVersion = "1.0";
    private Toolbar toolbar;
    private TextView contactMe;
    String theme;
    private AnalyticsApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        app = (AnalyticsApplication)getApplication();
        app.send(this);

        theme = getSharedPreferences(MainActivity.THEME_PREFERENCES, MODE_PRIVATE).getString(MainActivity.THEME_SAVED, MainActivity.LIGHTTHEME);
        if(theme.equals(MainActivity.DARKTHEME)){
            Log.d("DarkTheme", "One");
            setTheme(com.johnnolcox.mintodo.R.style.CustomStyle_DarkTheme);
        }
        else{
            Log.d("LightTheme", "One");
            setTheme(com.johnnolcox.mintodo.R.style.CustomStyle_LightTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(com.johnnolcox.mintodo.R.layout.about_layout);
        
//        Intent i = getIntent();
//        mId = (UUID)i.getSerializableExtra(TodoNotificationService.TODOUUID);

        final Drawable backArrow = getResources().getDrawable(com.johnnolcox.mintodo.R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        if(backArrow!=null){
            backArrow.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);

        }
        try{
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), 0);
            appVersion = info.versionName;
        }
        catch (Exception e){
            e.printStackTrace();
        }


        mVersionTextView = (TextView)findViewById(com.johnnolcox.mintodo.R.id.aboutVersionTextView);
        mVersionTextView.setText(String.format(getResources().getString(com.johnnolcox.mintodo.R.string.app_version), appVersion));
        toolbar = (Toolbar)findViewById(com.johnnolcox.mintodo.R.id.toolbar);
        contactMe = (TextView)findViewById(com.johnnolcox.mintodo.R.id.aboutContactMe);

        contactMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                app.send(this, "Action", "Feedback");
            }
        });

        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(backArrow);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                if(NavUtils.getParentActivityName(this)!=null){
                    NavUtils.navigateUpFromSameTask(this);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
