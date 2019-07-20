package uzi.utm.hellofirebase.ui;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import uzi.utm.hellofirebase.App;
import uzi.utm.hellofirebase.BuildConfig;
import uzi.utm.hellofirebase.R;
import uzi.utm.hellofirebase.adapter.DataAdapter;
import uzi.utm.hellofirebase.adapter.DataInterface;
import uzi.utm.hellofirebase.model.Data;
import uzi.utm.hellofirebase.model.MsgNotification;

public class HomeActivity extends AppCompatActivity implements DataInterface {
    private ImageView ivLogout;
    private RecyclerView rvList;
    private FirebaseAuth mAuth;
    private DataAdapter adapter;
    private Button btnAdd;
    private DatabaseReference databaseReference;
    private int position;
    private ImageView ivNotification;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ivLogout = findViewById(R.id.ivLogout);
        rvList = findViewById(R.id.rvList);
        btnAdd = findViewById(R.id.btnAdd);
        ivNotification = findViewById(R.id.ivNotification);

        mAuth = FirebaseAuth.getInstance();

        adapter = new DataAdapter(this, this);
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

        ivNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MsgNotification notif = new MsgNotification("Body","Title");
                String nChannelID = BuildConfig.APPLICATION_ID + ".notification.channel";

                NotificationManager nManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                if (Build.VERSION.SDK_INT >= 26) {
                    NotificationChannel nChannel;
                    nChannel = new NotificationChannel(nChannelID, "TentorQ Notification", NotificationManager.IMPORTANCE_HIGH);
                    if (nManager != null) {
                        nManager.createNotificationChannel(nChannel);
                    }
                }

//                String notiftext = remoteMessage.getData().get("notification");
//                if (!notiftext.isEmpty()) {
//                    notif = new Gson().fromJson(notiftext, MsgNotification.class);
                    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(App.getInstance(), nChannelID)
                            .setAutoCancel(true)
//                            .setContentIntent(resultIntent)
                            .setSmallIcon(R.mipmap.ic_launcher_round)
                            .setDefaults(NotificationCompat.DEFAULT_ALL)
                            .setContentText(notif.body)
                            .setContentTitle(notif.title)
                            .setStyle(new NotificationCompat.BigTextStyle().bigText(notif.body));

                    if (Build.VERSION.SDK_INT >= 24) {
                        mBuilder.setPriority(NotificationManager.IMPORTANCE_HIGH);
                        mBuilder.setPriority(NotificationManager.IMPORTANCE_MAX);
                    }

                    if (nManager != null) nManager.notify(11011, mBuilder.build());
//                }
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
                Gson gson = new Gson();
                Data data = gson.fromJson(dataSnapshot.getValue().toString(), Data.class);
                adapter.updateItemAt(position, data);
                adapter.notifyDataSetChanged();
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
    public void onDeleteData(final Data data) {
        Toast.makeText(HomeActivity.this, "onDeleteData", Toast.LENGTH_SHORT).show();
        Query query = databaseReference.child("data").orderByChild("id").equalTo(data.id);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    snapshot.getRef().removeValue();
                }
                adapter.remove(data);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onUpdateData(Data data, int position) {
        this.position = position;
        Intent intent = new Intent(this, UpdateActivity.class);
        intent.putExtra("data", data);
        startActivity(intent);
    }
}
