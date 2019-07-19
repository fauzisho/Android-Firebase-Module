package uzi.utm.hellofirebase.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import uzi.utm.hellofirebase.R;

public class HomeActivity extends AppCompatActivity {
    private ImageView ivLogout;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ivLogout = findViewById(R.id.ivLogout);
        mAuth = FirebaseAuth.getInstance();


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
