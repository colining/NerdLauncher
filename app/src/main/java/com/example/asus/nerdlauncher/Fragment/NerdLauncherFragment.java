package com.example.asus.nerdlauncher.Fragment;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewGroupCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by asus on 2016/9/23.
 */
public class NerdLauncherFragment extends ListFragment{
    private  static final  String TAG = "NerdLauncherFragment";

    @Override
    public  void onCreate(Bundle s)
    {
        super.onCreate(s);
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);

        PackageManager pm = getActivity().getPackageManager();
        List<ResolveInfo> activities = pm.queryIntentActivities(intent,0);

        Log.i(TAG,"I have found"+activities.size()+"activites.");
        Collections.sort(activities, new Comparator<ResolveInfo>() {
            @Override
            public int compare(ResolveInfo resolveInfo, ResolveInfo t1) {
                PackageManager pm = getActivity().getPackageManager();
                return String.CASE_INSENSITIVE_ORDER.compare(
                        resolveInfo.loadLabel(pm).toString(),
                        t1.loadLabel(pm).toString());

            }
        });
        ArrayAdapter <ResolveInfo> adapter = new ArrayAdapter<ResolveInfo>(
                getActivity(),android.R.layout.simple_list_item_1,activities)
        {
            public View getView(int pos , View convertView , ViewGroup parent)
            {
                PackageManager pm = getActivity().getPackageManager();
                View v = super.getView(pos, convertView,parent);
                TextView textView = (TextView)v;

                ResolveInfo ri = getItem(pos);
               

               textView.setText(ri.loadLabel(pm));
                return v;
            }
        };
        setListAdapter(adapter);


    }
    @Override
    public  void onListItemClick(ListView l, View v,int postion,long id)
    {
        ResolveInfo resolveInfo =(ResolveInfo)l.getAdapter().getItem(postion);
        ActivityInfo activityInfo = resolveInfo.activityInfo;

        if(activityInfo == null)
            return;
        Intent intent = new Intent(Intent.ACTION_MAIN);

        intent.setClassName(activityInfo.applicationInfo.packageName,activityInfo.name);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
