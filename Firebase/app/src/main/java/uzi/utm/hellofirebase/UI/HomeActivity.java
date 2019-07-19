package uzi.utm.hellofirebase.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import uzi.utm.hellofirebase.R;
import uzi.utm.hellofirebase.adapter.DataAdapter;
import uzi.utm.hellofirebase.adapter.DataInterface;
import uzi.utm.hellofirebase.model.Data;

public class HomeActivity extends AppCompatActivity implements DataInterface {
    private ImageView ivLogout;
    private RecyclerView rvList;
    private FirebaseAuth mAuth;
    private DataAdapter adapter;
    private Button btnAdd;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ivLogout = findViewById(R.id.ivLogout);
        rvList = findViewById(R.id.rvList);
        btnAdd = findViewById(R.id.btnAdd);

        mAuth = FirebaseAuth.getInstance();

        adapter = new DataAdapter(this,this);
        rvList.setLayoutManager(new LinearLayoutManager(this));

        //dummy data

        databaseReference = FirebaseDatabase.getInstance().getReference();


        rvList.setAdapter(adapter);

        ivLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, AddActivity.class));
            }
        });

        databaseReference.child("data").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Gson gson = new Gson();
                Data data = gson.fromJson(dataSnapshot.getValue().toString(), Data.class);
                adapter.add(data);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Toast.makeText(HomeActivity.this, "onChildChanged.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                Toast.makeText(HomeActivity.this, "onChildRemoved.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Toast.makeText(HomeActivity.this, "onChildMoved", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(HomeActivity.this, "onCancelled", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDeleteData(Data data) {
        Toast.makeText(HomeActivity.this, "onDeleteData", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpdateData(Data data) {
        Toast.makeText(HomeActivity.this, "onUpdateData", Toast.LENGTH_SHORT).show();
    }
}
