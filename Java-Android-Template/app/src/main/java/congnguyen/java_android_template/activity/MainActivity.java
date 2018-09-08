package congnguyen.java_android_template.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import congnguyen.java_android_template.R;
import congnguyen.java_android_template.common.Constants;
import congnguyen.java_android_template.presentation.home.HomeFragment;

public class MainActivity extends BaseActivity {
    private FragmentManager fragmentManager;
    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_main;
    }
    public static void start(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        HomeFragment fragment =
                HomeFragment.getInstance();
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.flContainer, fragment, Constants.CONTACT_TAG);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.addToBackStack(fragment.getClass().getName());
        fragmentTransaction.commit();
    }
}