package com.yourzeromax.test_bottomradiogroup;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, BaseFragment.OnFragmentInteractionListener {
    private static final String TAG = "zzzzzz";
    RadioGroup rgMain;
    RadioButton rb_0, rb_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rgMain = findViewById(R.id.rg_main);
        rb_0 = findViewById(R.id.rb_0);
        rb_1 = findViewById(R.id.rb_1);
        rb_0.setOnCheckedChangeListener(this);
        rb_1.setOnCheckedChangeListener(this);
        rb_0.setChecked(true);
    }

    void setVisibility(int position, boolean show) {
        Fragment fragment = findFragmentByTag(position);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (show) {
            if (!fragment.isAdded()) {
                fragmentTransaction.add(R.id.content, fragment, position + "");
            }
            fragmentTransaction.show(fragment);
        } else {
            if (!fragment.isAdded()) {
                return;
            }
            fragmentTransaction.hide(fragment);
        }
        fragmentTransaction.commitNowAllowingStateLoss();
    }

    Fragment findFragmentByTag(int position) {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(position + "");
        if (fragment == null) {
            switch (position) {
                case 0:
                    fragment = BaseFragment.newInstance("1", "1");
                    break;
                case 1:
                    fragment = BaseFragment.newInstance("2", "2");
                    break;
                case 2:
                    fragment = BaseFragment.newInstance("3", "3");
                    break;
                case 3:
                    fragment = BaseFragment.newInstance("4", "4");
                    break;
            }
        }
        return fragment;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Log.d(TAG, "onCheckedChanged: " + isChecked);
        switch (buttonView.getId()) {
            case R.id.rb_0:
                setVisibility(0, isChecked);
                break;
            case R.id.rb_1:
                setVisibility(1, isChecked);
                break;
        }
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
