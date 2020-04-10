package com.example.trending;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView allUsersList;
    private UsersAdapter usersAdapter;
    private List<Items> usersList = new ArrayList<>();

    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        allUsersList = (RecyclerView) findViewById(R.id.all_users_list);

        for(int i=0; i<=10; i++){
            Items item = new Items(
                    "Name " + i+1,
                    "Project Name",
                    "Up branch to easily missed by do. Admiration considered acceptance too led one melancholy expression.",
                    "3994",
                    "C++",
                    "3994",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSYc0JKEV33KR7KNAUFIoLqfqCK0qpRLblxeR7cbpLCZd9BqbLi&usqp=CAU"
            );
            usersList.add(item);
        }

        usersAdapter = new UsersAdapter(usersList,this);
        linearLayoutManager = new LinearLayoutManager(this);
        allUsersList.setHasFixedSize(true);
        allUsersList.setLayoutManager(linearLayoutManager);
        allUsersList.setAdapter(usersAdapter);


    }
}
