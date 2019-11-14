package com.anelcc.usbank.ui;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ViewModelTest {

    @Mock
    RepositoriesServiceManager serviceManager;
    @Mock
    Call<List<Repo>> repos;
    private ViewModel model;

    @Before
    public void setUp() {
        when(serviceManager.getRepositories("anelcc")).thenReturn(repos);
        serviceManager.getRepositories("anelcc");
        model = new ViewModel(serviceManager);
    }

    @After
    public void tearDown() {
       model = null;
    }

    @Test
    public void shouldReturnCorrectArrayByLargeSum2() {
        List<String> listResult = new ArrayList<>();
        verify(repos).enqueue((Callback<List<Repo>>) any());

        assertEquals(listResult, model.getResult());
    }

    @Test
    public void shouldReturnCorrectArrayByLargeSum() {
        List<Integer> largeSum = new ArrayList<>();
        largeSum.add(1);
        largeSum.add(-3);
        largeSum.add(4);
        assertEquals(largeSum, model.getLargeSum(new int[] {-2,1, -3,4}));
    }


    @Test
    public void shouldReturnCorrectArrayByLargeSumPlus() {
        List<Integer> largeSum = new ArrayList<>();
        largeSum.add(3);
        largeSum.add(1);
        assertEquals(largeSum, model.getLargeSum(new int[] {3,1, -3,-5,4}));
    }
}