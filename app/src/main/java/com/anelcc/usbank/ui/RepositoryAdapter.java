package com.anelcc.usbank.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.ViewHolder>{
private List<Repo> repositories;

public RepositoryAdapter(List<Repo> repositories){
        this.repositories=repositories;
        }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(android.R.id.text1);
        }

        public void bind(Repo repo) {
            textView.setText(repo.getName());
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        // this is the part for the simple xml you have in you code
        View view = layoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(repositories.get(position));
    }

    @Override
    public int getItemCount() {
        return repositories.size();
    }


    public void setRepositories(List<Repo> repositories) {
        this.repositories = repositories;
    }

}
