package uzi.utm.hellofirebase.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import uzi.utm.hellofirebase.R;
import uzi.utm.hellofirebase.adapter.DataAdapter;
import uzi.utm.hellofirebase.model.Data;

public class HomeActivity extends AppCompatActivity {
    private ImageView ivLogout;
    private RecyclerView rvList;
    private FirebaseAuth mAuth;
    private DataAdapter adapter;
    private ArrayList<Data> listData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ivLogout = findViewById(R.id.ivLogout);
        rvList = findViewById(R.id.rvList);

        mAuth = FirebaseAuth.getInstance();

        adapter = new DataAdapter(this);
        rvList.setLayoutManager(new LinearLayoutManager(this));

        //dummy data


        listData = new ArrayList<Data>() {
            {
                add(new Data("10111", "Fauzisho"));
                add(new Data("10111", "Fauzisho"));
                add(new Data("10111", "Fauzisho"));
            }
        };
        adapter.addAll(listData);
        rvList.setAdapter(adapter);

        ivLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                UILogin();
            }
        });
    }

    public void UILogin() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }


}
