package ru.hand_build.android.githubsearcher;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;

import org.eclipse.egit.github.core.SearchRepository;
import org.eclipse.egit.github.core.service.RepositoryService;
import java.io.IOException;
import java.util.List;

import ru.hand_build.android.githubsearcher.fragments.ListFragment;
import ru.hand_build.android.githubsearcher.fragments.SearcherFragment;

/**
 * Created by vladimir on 22.06.15.
 */
public class DataGetterTask extends AsyncTask<String, Void, List<SearchRepository>> {

    List<SearchRepository> repos;
    SearcherFragment context;
    String query;

    public DataGetterTask(SearcherFragment context, String query){
        super();
        this.query = query;
        this.context = context;

    }

    protected List<SearchRepository> doInBackground(String... urls) {
        RepositoryService service = new RepositoryService();

        try {
            repos = service.searchRepositories(query);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return repos;
    }

    /** The system calls this to perform work in the UI thread and delivers
     * the result from doInBackground() */
    protected void onPostExecute(List<SearchRepository> result) {

        for (int i = 0; i < result.size(); i++){
            context.myDataset.add(i, new Repo(result.get(i).getName(),
                    result.get(i).getDescription(),
                    result.get(i).getWatchers(),
                    result.get(i).getForks(),
                    result.get(i).getOwner(),
                    result.get(i).getHomepage()));
        }

        Bundle args = new Bundle();
        args.putSerializable(ListFragment.QUERY, context.myDataset);
        ListFragment listFragment = new ListFragment();
        listFragment.setArguments(args);

        context.getFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .setCustomAnimations(R.animator.enter_anim, R.animator.exit_anim, R.animator.enter_anim, R.animator.exit_anim)
                .replace(R.id.fragment_container, listFragment)
                .commit();

    }
}