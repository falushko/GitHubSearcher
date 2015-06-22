package ru.hand_build.android.githubsearcher;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by vladimir on 15.06.15.
 */
public class GitAdapter extends RecyclerView.Adapter<GitAdapter.ViewHolder> {
    private ArrayList<Repo> mDataset;
    private ListActivity context;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        public View v;
        public TextView name;
        public Button like;
        public TextView description;
        public Button watchers;
        public Button forks;
        public Button owner;
        public TextView website;

        public ViewHolder(View v) {
            super(v);
            this.v = v;
            name = (TextView) v.findViewById(R.id.name);
            description = (TextView) v.findViewById(R.id.description);
            watchers = (Button) v.findViewById(R.id.watchers);
            forks = (Button) v.findViewById(R.id.forks);
            owner = (Button) v.findViewById(R.id.owner);
            website = (TextView) v.findViewById(R.id.website);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public GitAdapter(ListActivity context) {
        this.context = context;
        mDataset = context.myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public GitAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.name.setText((CharSequence) mDataset.get(position).name);
        holder.description.setText((CharSequence) mDataset.get(position).description);
        holder.watchers.setText("Watchers: " + mDataset.get(position).watchers);
        holder.forks.setText("Forks: " + mDataset.get(position).forks);
        holder.owner.setText("Owner: " + mDataset.get(position).owner);

        //shit happens here, i'll fix it later

        if(mDataset.get(position).website == null){
            holder.website.setVisibility(View.INVISIBLE);
            return;
        }
        holder.website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = mDataset.get(position).website;

                //TODO chek if any activity to handle intent
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                context.startActivity(i);
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}