package uzi.utm.hellofirebase.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import uzi.utm.hellofirebase.R;

public class AddActivity extends AppCompatActivity {
    private EditText etId;
    private EditText etFullname;
    private ImageView ivAdd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etId = findViewById(R.id.etId);
        etFullname = findViewById(R.id.etFullname);
        ivAdd = findViewById(R.id.ivAdd);

        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
