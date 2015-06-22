package ru.hand_build.android.githubsearcher;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.SearchRepository;
import org.eclipse.egit.github.core.service.RepositoryService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ListActivity extends Activity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Repo> myDataset = new ArrayList<Repo>();
    private List<SearchRepository> myDataset2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // This should be in AssynkTask



        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        for (int i = 0; i < 10; i++){
            myDataset.add(i, new Repo("Repo 1", "Lorem ipsum dolor sit amet, " +
                    "consectetur adipiscing elit, sed do eiusmod " +
                    "tempor incididunt ut labore et dolore magna aliqua. " +
                    "Ut enim ad minim veniam, quis nostrud exercitation " +
                    "ullamco laboris nisi ut aliquip ex ea commodo consequat. " +
                    "Duis aute irure dolor in reprehenderit in voluptate " +
                    "velit esse cillum dolore eu fugiat nulla pariatur.",
                    354, 672, "Uasya", "http://yiiframework.com"));
        }

        // specify an adapter (see also next example)
        mAdapter = new GitAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);
        new BackgroundTask().execute();
    }

    private class BackgroundTask extends AsyncTask<String, Void, List<SearchRepository>> {
        /** The system calls this to perform work in a worker thread and
         * delivers it the parameters given to AsyncTask.execute() */
        List<SearchRepository> repos;

        protected List<SearchRepository> doInBackground(String... urls) {
            RepositoryService service = new RepositoryService();

            try {
                repos = service.searchRepositories("yii");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return repos;
        }

        /** The system calls this to perform work in the UI thread and delivers
         * the result from doInBackground() */
        protected void onPostExecute(List<SearchRepository> result) {
            myDataset2 = result;
            Log.d("tag", myDataset2.get(0).getName());
        }
    }

}