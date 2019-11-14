package com.anelcc.usbank.ui;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.VisibleForTesting;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewModel {

    private List<Repo> result = new ArrayList<>();

    public ViewModel(RepositoriesServiceManager serviceManager) {
        getCurrentData(serviceManager);
    }

    //This method should be in the activity
    public void getCurrentData(RepositoriesServiceManager serviceManager) {
        Call<List<Repo>> repos = serviceManager.getRepositories("anelcc");
        repos.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                result = response.body();
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
            }
        });
    }

    public List<Repo> getResult() {
        return result;
    }

    @VisibleForTesting
    List<Integer> getLargeSum(int[] numberList){
        int listSize = numberList.length ;
        int currentSum = 0;
        int largeSum = numberList != null ? numberList[0] : 0;
        int indexLargeSum = 0;
        ArrayList<List<Integer>> subArrayListSums = new ArrayList<>();

        for (int i = 0; i < listSize-1 ; i++){
            int nextIndice = i+1;
            currentSum = numberList[i];
            List<Integer> arrayList = new ArrayList<>();
            arrayList.add(numberList[i]);
            while (nextIndice <= listSize-1){
                currentSum = currentSum + numberList[nextIndice];
                arrayList.add(numberList[nextIndice]);
                List<Integer> saveSubArrayList = new ArrayList<>();
                saveSubArrayList.addAll(arrayList);
                nextIndice++;
                if (largeSum < currentSum){
                    largeSum = currentSum;
                    indexLargeSum = subArrayListSums.size();
                }
                subArrayListSums.add(saveSubArrayList);
            }
        }
        return subArrayListSums.get(indexLargeSum);
    }
}
