package uzi.utm.hellofirebase.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import uzi.utm.hellofirebase.R;
import uzi.utm.hellofirebase.model.Data;

public class AddActivity extends AppCompatActivity {
    private EditText etId;
    private EditText etFullname;
    private ImageView ivAdd;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etId = findViewById(R.id.etId);
        etFullname = findViewById(R.id.etFullname);
        ivAdd = findViewById(R.id.ivAdd);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Data data = new Data("" + etId.getText().toString() + "", etFullname.getText().toString());

                databaseReference.child("data").push().setValue(data, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                        Toast.makeText(AddActivity.this, "Data added.", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
            }
        });
    }
}
