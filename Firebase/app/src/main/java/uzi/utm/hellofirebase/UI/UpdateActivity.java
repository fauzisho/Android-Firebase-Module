package uzi.utm.hellofirebase.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import uzi.utm.hellofirebase.R;
import uzi.utm.hellofirebase.model.Data;

public class UpdateActivity extends AppCompatActivity {

    private Data data;

    private EditText etId;
    private EditText etFullname;
    private ImageView ivAdd;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        data = (Data) getIntent().getSerializableExtra("data");

        etId = findViewById(R.id.etId);
        etFullname = findViewById(R.id.etFullname);
        ivAdd = findViewById(R.id.ivAdd);

        etId.setText(data.getId());
        etFullname.setText(data.getName());
        databaseReference = FirebaseDatabase.getInstance().getReference();

        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query query = databaseReference.child("data").orderByChild("id").equalTo(data.id);

                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            snapshot.child("id").getRef().setValue(etId.getText().toString());
                            snapshot.child("name").getRef().setValue(etFullname.getText().toString());
                        }
                        finish();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
