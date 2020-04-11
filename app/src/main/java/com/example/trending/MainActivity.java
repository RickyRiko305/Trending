package com.example.trending;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.trending.api.ApiClient;
import com.example.trending.api.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView allUsersList;
    private UsersAdapter usersAdapter;
    private List<Items> usersList = new ArrayList<>();

    private LinearLayoutManager linearLayoutManager;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        allUsersList = (RecyclerView) findViewById(R.id.all_users_list);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);

        usersAdapter = new UsersAdapter(usersList,this);
        linearLayoutManager = new LinearLayoutManager(this);
        allUsersList.setHasFixedSize(true);
        allUsersList.setLayoutManager(linearLayoutManager);
        getUsers();
        //allUsersList.setAdapter(usersAdapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getUsers();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    public void getUsers(){
        //shimmerLayout.startShimmerAnimation();
        Call<List<Items>> call = ApiClient.getApiClient().create(ApiInterface.class).getUserInfo();

        call.enqueue(new Callback<List<Items>>() {
            @Override
            public void onResponse(Call<List<Items>> call, Response<List<Items>> response) {
                List<Items> Item = response.body();
                usersAdapter = new UsersAdapter(Item,MainActivity.this);
                //allUsersList.setVisibility(View.VISIBLE);
                allUsersList.setAdapter(usersAdapter);

            }

            @Override
            public void onFailure(Call<List<Items>> call, Throwable t) {

            }
        });
    }
}
