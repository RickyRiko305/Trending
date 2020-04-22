package com.example.trending;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;

import com.example.trending.api.ApiClient;
import com.example.trending.api.ApiInterface;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.Collections;
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

    private ShimmerFrameLayout shimmerFrameLayout;
    private RelativeLayout noInternetLayout;
    private Button retryButton;
    private ImageView menuImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menuImage = findViewById(R.id.menu_button);

        retryButton = (Button) findViewById(R.id.retry_button);

        noInternetLayout = (RelativeLayout) findViewById(R.id.no_internet_layout);

        shimmerFrameLayout = (ShimmerFrameLayout) findViewById(R.id.shimmer_view);

        allUsersList = (RecyclerView) findViewById(R.id.all_users_list);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);

        usersAdapter = new UsersAdapter(usersList,this);
        linearLayoutManager = new LinearLayoutManager(this);
        allUsersList.setHasFixedSize(true);
        allUsersList.setLayoutManager(linearLayoutManager);
        getUsers();
        //allUsersList.setAdapter(usersAdapter);

        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noInternetLayout.setVisibility(View.GONE);
                shimmerFrameLayout.startShimmer();
                shimmerFrameLayout.setVisibility(View.VISIBLE);
                getUsers();
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                allUsersList.setVisibility(View.GONE);
                noInternetLayout.setVisibility(View.GONE);
                shimmerFrameLayout.setVisibility(View.VISIBLE);
                shimmerFrameLayout.startShimmer();
                getUsers();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        menuImage.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                //Context context = new ContextThemeWrapper(MainActivity.this, view);
                final PopupMenu popupMenu = new PopupMenu(MainActivity.this,view);
                popupMenu.getMenuInflater().inflate(R.menu.main_menu, popupMenu.getMenu());
                popupMenu.setGravity(Gravity.START);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        //Toast.makeText(MainActivity.this,"" + menuItem.getTitle(), Toast.LENGTH_LONG).show();
                        switch (menuItem.getItemId()){
                            case R.id.sort_by_stars:
                                sortByStar();
                                return true;
                            case R.id.sort_by_name:
                                sortByName();
                                return true;
                        }
                        return true;
                    }
                });

                popupMenu.show();
            }
        });
    }

    private void sortByStar() {
        Collections.sort(usersList, Items.starComparator);
        usersAdapter = new UsersAdapter(usersList, this);
        allUsersList.setAdapter(usersAdapter);
    }

    private void sortByName() {
        Collections.sort(usersList, Items.nameComparator);
        usersAdapter = new UsersAdapter(usersList, this);
        allUsersList.setAdapter(usersAdapter);
    }

    public void getUsers(){
        //shimmerLayout.startShimmerAnimation();
        Call<List<Items>> call = ApiClient.getApiClient().create(ApiInterface.class).getUserInfo();

        call.enqueue(new Callback<List<Items>>() {
            @Override
            public void onResponse(Call<List<Items>> call, Response<List<Items>> response) {
                List<Items> Item = response.body();
                usersList = response.body();
                usersAdapter = new UsersAdapter(usersList,MainActivity.this);
                shimmerFrameLayout.stopShimmer();
                shimmerFrameLayout.setVisibility(View.GONE);
                allUsersList.setVisibility(View.VISIBLE);
                allUsersList.setAdapter(usersAdapter);
                noInternetLayout.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<List<Items>> call, Throwable t) {
                allUsersList.setVisibility(View.GONE);
                shimmerFrameLayout.stopShimmer();
                shimmerFrameLayout.setVisibility(View.GONE);
                noInternetLayout.setVisibility(View.VISIBLE);

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        shimmerFrameLayout.startShimmer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        shimmerFrameLayout.stopShimmer();
    }

}
