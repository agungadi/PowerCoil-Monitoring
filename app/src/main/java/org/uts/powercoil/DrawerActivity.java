package org.uts.powercoil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class DrawerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);


        TextView txtPut = (TextView) findViewById(R.id.putExtra);

        Intent intent = getIntent();
        txtPut.setText(intent.getStringExtra("put"));

        String ambil = txtPut.getText().toString();

    }
}