package ru.hand_build.android.githubsearcher.fragments;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import ru.hand_build.android.githubsearcher.DataGetterTask;
import ru.hand_build.android.githubsearcher.R;
import ru.hand_build.android.githubsearcher.Repo;


public class SearcherFragment extends Fragment {

    private Button mSearchButton;
    private EditText mEditText;
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
        mSearchButton = (Button) v.findViewById(R.id.search_button);

        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = mEditText.getText().toString();
                if (message.isEmpty()) return;

                new DataGetterTask(SearcherFragment.this, message).execute(message);


            }
        });

        return v;
    }



}
