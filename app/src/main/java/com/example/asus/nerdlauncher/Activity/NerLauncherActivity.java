package com.example.asus.nerdlauncher.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;

import com.example.asus.nerdlauncher.Fragment.NerdLauncherFragment;

public class NerLauncherActivity extends SingleFragmentActivity {

   @Override
    public ListFragment createFragment()
   {
       return  new NerdLauncherFragment();
   }
}
