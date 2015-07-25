package ru.hand_build.android.githubsearcher.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.pnikosis.materialishprogress.ProgressWheel;

import java.util.ArrayList;

import ru.hand_build.android.githubsearcher.DataGetterTask;
import ru.hand_build.android.githubsearcher.R;
import ru.hand_build.android.githubsearcher.Repo;


public class SearcherFragment extends Fragment {

    private Button mSearchButton;
    private EditText mEditText;
    ProgressWheel mWheel;
    private int mShortAnimationDuration;

    public ArrayList<Repo> myDataset = new ArrayList<Repo>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_searcher, container, false);


        mEditText = (EditText) v.findViewById(R.id.search_query);
        mEditText.setTextColor(getResources().getColor(R.color.primaryText));
        mEditText.setLinkTextColor(getResources().getColor(R.color.primaryText));

        mWheel = (ProgressWheel) v.findViewById(R.id.progress_wheel);
        mWheel.setVisibility(View.GONE);

        mShortAnimationDuration = getResources().getInteger(android.R.integer.config_shortAnimTime);
        mSearchButton = (Button) v.findViewById(R.id.search_button);
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { goToListFragment(); }
        });

        return v;
    }

    private void goToListFragment(){
        String message = mEditText.getText().toString();
        if (message.isEmpty()) return;
        mWheel.setVisibility(View.VISIBLE);
        mWheel.bringToFront();
        new DataGetterTask(SearcherFragment.this, message).execute(message);
    }


}