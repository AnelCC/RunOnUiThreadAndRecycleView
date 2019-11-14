package com.anelcc.usbank.ui;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RepositoriesServiceManager {
    private static final String TAG = "RepoServiceManager";
    private static final String BASE_URL = "https://api.github.com/";

    private GitHubService gitHubService;

    public Call<List<Repo>> getRepositories(String user){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        gitHubService = retrofit.create(GitHubService.class);
        return  gitHubService.listRepos(user);
    }

}
