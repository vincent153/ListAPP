package com.example.listapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "ListAPP";
    private ListView listView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CustomAdapter adapter = new CustomAdapter(getApplicationContext(),this,getPackages());


        listView1 = findViewById(R.id.menuList);
        listView1.setAdapter(adapter);
    }



    private ArrayList<PInfo> getPackages() {
        ArrayList<PInfo> apps = getInstalledApps(false); /* false = no system packages */
        final int max = apps.size();
        for (int i=0; i<max; i++) {
            Log.d(TAG,apps.get(i).toString());

        }
        return apps;
    }

    private ArrayList<PInfo> getInstalledApps(boolean getSysPackages) {
        ArrayList<PInfo> res = new ArrayList<PInfo>();
        PackageManager pm = getPackageManager();
        List<PackageInfo> packs = pm.getInstalledPackages(0);
        for(int i=0;i<packs.size();i++) {
            PackageInfo p = packs.get(i);
            if ((!getSysPackages) && (p.versionName == null)) {
                continue ;
            }
            PInfo newInfo = new PInfo();
            Intent intent = pm.getLaunchIntentForPackage(p.packageName);
            if(intent!=null){
                Set<String> ss = intent.getCategories();
                Log.d(TAG,"package:"+p.packageName);
                Log.d(TAG,"getCategories:");
                for(String s:ss){
                    Log.d(TAG,s);
                    if(s.equals("android.intent.category.LAUNCHER")){
                        newInfo.appname = p.applicationInfo.loadLabel(getPackageManager()).toString();
                        newInfo.pname = p.packageName;
                        newInfo.versionName = p.versionName;
                        newInfo.versionCode = p.versionCode;
                        newInfo.icon = p.applicationInfo.loadIcon(getPackageManager());
                        res.add(newInfo);
                    }
                }
            }
        }
        return res;
    }

    public class PInfo {
        public String appname = "";
        public String pname = "";
        public String versionName = "";
        public int versionCode = 0;
        public Drawable icon;

        @Override
        public String toString() {
            return "PInfo{" +
                    "appname='" + appname + '\'' +
                    ", pname='" + pname + '\'' +
                    ", versionName='" + versionName + '\'' +
                    ", versionCode=" + versionCode +
                    '}';
        }
    }



}