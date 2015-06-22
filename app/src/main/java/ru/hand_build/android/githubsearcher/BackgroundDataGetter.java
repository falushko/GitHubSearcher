package ru.hand_build.android.githubsearcher;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.eclipse.egit.github.core.SearchRepository;
import org.eclipse.egit.github.core.service.RepositoryService;

import java.io.IOException;
import java.util.List;

/**
 * Created by vladimir on 22.06.15.
 */
class BackgroundDataGetter extends AsyncTask<String, Void, List<SearchRepository>> {

    List<SearchRepository> repos;
    ListActivity context;

    public BackgroundDataGetter(ListActivity context){
        super();
        this.context = context;
    }

    protected List<SearchRepository> doInBackground(String... urls) {
        RepositoryService service = new RepositoryService();

        try {
            repos = service.searchRepositories(urls[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return repos;
    }

    /** The system calls this to perform work in the UI thread and delivers
     * the result from doInBackground() */
    protected void onPostExecute(List<SearchRepository> result) {
        context.rawData = result;


        for (int i = 0; i < context.rawData.size(); i++){
            context.myDataset.add(i, new Repo(context.rawData.get(i).getName(),
                    context.rawData.get(i).getDescription(),
                    context.rawData.get(i).getWatchers(),
                    context.rawData.get(i).getForks(),
                    context.rawData.get(i).getOwner(),
                    context.rawData.get(i).getHomepage()));
        }

        // specify an adapter (see also next example)
        context.mAdapter = new GitAdapter(context.myDataset);
        context.mRecyclerView.setAdapter(context.mAdapter);


    }
}