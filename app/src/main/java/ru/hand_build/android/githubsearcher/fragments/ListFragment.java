package ru.hand_build.android.githubsearcher.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.eclipse.egit.github.core.SearchRepository;

import java.util.ArrayList;
import java.util.List;
import ru.hand_build.android.githubsearcher.DataGetterTask;
import ru.hand_build.android.githubsearcher.GitAdapter;
import ru.hand_build.android.githubsearcher.R;
import ru.hand_build.android.githubsearcher.Repo;


public class ListFragment extends Fragment{

    public RecyclerView mRecyclerView;
    public RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public ArrayList<Repo> myDataset = new ArrayList<Repo>();
    public static final String QUERY = "query";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        Bundle args = getArguments();

        myDataset = (ArrayList<Repo>) args.getSerializable(QUERY);

       // toolbar.setTitle(query);

        mAdapter = new GitAdapter(this);
        mRecyclerView.setAdapter(mAdapter);


        return v;
    }

}
