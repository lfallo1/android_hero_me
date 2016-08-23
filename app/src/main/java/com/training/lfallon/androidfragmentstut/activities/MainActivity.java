package com.training.lfallon.androidfragmentstut.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.training.lfallon.androidfragmentstut.R;
import com.training.lfallon.androidfragmentstut.fragments.ChooseCauseFragment;
import com.training.lfallon.androidfragmentstut.fragments.ChoosePowerFragment;
import com.training.lfallon.androidfragmentstut.interfaces.OnFragmentInteractionListener;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener {

    public enum Cause {
        ACCIDENT, MUTATION, BIRTH
    };
    public static String EXTRA_SELECTED_CAUSE = "EXTRA_SELECTED_CAUSE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.fragmentHomepagePlaceholder);
        if (fragment == null) {
            fragment = new ChooseCauseFragment();
            manager.beginTransaction().add(R.id.fragmentHomepagePlaceholder, fragment).commit();
        }
    }

    private void loadPickPowerScreen(int val){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentHomepagePlaceholder, new ChoosePowerFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onFragmentInteraction(int val) {
        loadPickPowerScreen(val);
    }
}
