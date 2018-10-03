package proteam.com.bai_5_fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity implements OneFragment.OnFragmentInteractionListener{
    private OneFragment mFragment1;
    private Fragment mFragment2;
    private FrameLayout mFrContain2;
    OnActivityToFragmentInteractionListener onActivityToFragmentInteractionListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFrContain2 = (FrameLayout) findViewById(R.id.frContain2);
        (findViewById(R.id.btnFragment1)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragment1 = new OneFragment();
                switchFragment(mFragment1, true, R.id.frContain1);
                mFrContain2.setVisibility(View.GONE);
            }
        });

        (findViewById(R.id.btnFragment2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragment2 = new TwoFragment();
                switchFragment(mFragment2, true, R.id.frContain1);
                mFrContain2.setVisibility(View.GONE);
            }
        });
        (findViewById(R.id.btnFragment3)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragment1 = new OneFragment();
                mFragment2 = new TwoFragment();
                mFragment1.setCallBack(MainActivity.this);
                mFrContain2.setVisibility(View.VISIBLE);
                switchFragment(mFragment1, true, R.id.frContain1);
                switchFragment(mFragment2, true, R.id.frContain2);
            }
        });
    }

    private void switchFragment(Fragment fragment, boolean addToBackStack, int id) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        if (fragment.getTag() == null) {
            ft.replace(id, fragment, fragment.toString());
        } else {
            ft.replace(id, fragment);
        }

        //backPress with fragment
        if (addToBackStack) {
            ft.addToBackStack(null);
        }
        ft.commit();

//        //find fragment current
//        Fragment currentFragment = fm.findFragmentById(R.id.frContain1);
//
//        if (currentFragment != fragment) {
//            if (fragment.getTag() == null) {
//                ft.replace(id, fragment, fragment.toString());
//            } else {
//                ft.replace(id, fragment);
//            }
//
//            //back Press with fragment
//            if (addToBackStack) {
//                ft.addToBackStack(null);
//            }
//            ft.commit();
//        }

    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment currentFragment = fm.findFragmentById(R.id.frContain1);
        if (currentFragment instanceof OneFragment) {
           finish();
        } else {
            fm.popBackStack();
        }
    }

    public void setCallBackFromActivity(OnActivityToFragmentInteractionListener onActivityToFragmentInteractionListener) {
        this.onActivityToFragmentInteractionListener = onActivityToFragmentInteractionListener;

    }

    @Override
    public void messageFromFragmentToActivity(String myString) {
        Log.d("TAGGGG", myString);
        onActivityToFragmentInteractionListener.messageFromActivityToFragment(myString);
    }

    public interface OnActivityToFragmentInteractionListener {
        void messageFromActivityToFragment(String myString);
    }
}
