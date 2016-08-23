package com.training.lfallon.androidfragmentstut.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.training.lfallon.androidfragmentstut.R;
import com.training.lfallon.androidfragmentstut.activities.MainActivity;
import com.training.lfallon.androidfragmentstut.interfaces.OnFragmentInteractionListener;

public class ChooseCauseFragment extends Fragment implements View.OnClickListener {

    private OnFragmentInteractionListener mListener;

    private MainActivity.Cause selectedCause;
    private Button accidentButton;
    private Button bornButton;
    private Button mutationButton;
    private Button chooseButton;


    public ChooseCauseFragment() { }

    public static ChooseCauseFragment newInstance() {
        ChooseCauseFragment fragment = new ChooseCauseFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        accidentButton = (Button)view.findViewById(R.id.accidentButton);
        bornButton = (Button)view.findViewById(R.id.birthButton);
        mutationButton = (Button)view.findViewById(R.id.mutationButton);
        chooseButton = (Button)view.findViewById(R.id.chooseButton);

        accidentButton.setOnClickListener(this);
        mutationButton.setOnClickListener(this);
        bornButton.setOnClickListener(this);
        chooseButton.setOnClickListener(this);

        chooseButton.setEnabled(false);
        chooseButton.getBackground().setAlpha(128);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.accidentButton || v.getId() == R.id.mutationButton || v.getId() == R.id.birthButton){
            setButtonSelected((Button)v);
        } else if(v.getId() == R.id.chooseButton){
            mListener.onFragmentInteraction(selectedCause.ordinal());
        }
    }

    public void setButtonSelected(Button button) {
        clearSelectedButtons();
        int leftDrawable = 0;
        if(button.getId() == R.id.accidentButton){
            leftDrawable = R.drawable.lightning;
            selectedCause = MainActivity.Cause.ACCIDENT;
        } else if(button.getId() == R.id.mutationButton){
            leftDrawable = R.drawable.atomic;
            selectedCause = MainActivity.Cause.MUTATION;
        } else if(button.getId() == R.id.birthButton){
            leftDrawable = R.drawable.rocket;
            selectedCause = MainActivity.Cause.BIRTH;
        }
        chooseButton.setEnabled(true);
        chooseButton.getBackground().setAlpha(255);
        button.setCompoundDrawablesWithIntrinsicBounds(leftDrawable, 0, R.drawable.itemselected, 0);
    }

    public void clearSelectedButtons(){
        accidentButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.lightning, 0, 0, 0);
        mutationButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.atomic, 0, 0, 0);
        bornButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.rocket, 0, 0, 0);
    }
}
