package com.anelcc.usbank;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.anelcc.usbank.ui.RepositoriesServiceManager;
import com.anelcc.usbank.ui.RepositoryAdapter;
import com.anelcc.usbank.ui.ViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private static final int WAIT_TIME = 3000;


    private RepositoryAdapter repoAdapter;
    private RecyclerView recyclerView;

    private ViewModel viewModel;
    private RepositoriesServiceManager serviceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        serviceManager = new RepositoriesServiceManager();
        viewModel = new ViewModel(serviceManager);

        recyclerView = findViewById(R.id.recycler_view);
        repoAdapter = new RepositoryAdapter(viewModel.getResult());

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(repoAdapter);

        updateUI();
    }

    private void updateUI() {
        final Handler handler = new Handler(Looper.getMainLooper());
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                viewModel.getCurrentData(serviceManager);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        repoAdapter = new RepositoryAdapter(viewModel.getResult());
                        recyclerView.setAdapter(repoAdapter);
                        repoAdapter.notifyDataSetChanged();
                    }
                }, WAIT_TIME);
            }
        });
    }


    //Only the original thread that created a view hierarchy can touch its views.
    private void refreshThread() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                viewModel.getCurrentData(serviceManager);
                repoAdapter.setRepositories(viewModel.getResult());
                repoAdapter.notifyDataSetChanged();
            }
        };
        thread.start();
    }

}