package com.example.taxitest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class AfterActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnRevoke, btnLogout;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);

        btnLogout = (Button)findViewById(R.id.btn_logout);
        btnRevoke = (Button)findViewById(R.id.btn_revoke);



        mAuth = FirebaseAuth.getInstance();

        btnLogout.setOnClickListener(this);
        btnRevoke.setOnClickListener(this);
    }

    private void signOut() {
        FirebaseAuth.getInstance().signOut();
    }

    private void revokeAccess() { mAuth.getCurrentUser().delete(); }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_logout:
                signOut();
                finishAffinity();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_revoke:
                revokeAccess();
                //파이어베이스에 등록된 회원정보 삭제 코드
                finishAffinity();
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}