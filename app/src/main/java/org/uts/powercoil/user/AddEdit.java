package org.uts.powercoil.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.uts.powercoil.R;
import org.uts.powercoil.SqliteHelper;

public class AddEdit extends AppCompatActivity {

    EditText txt_id, txt_name, txt_email, txt_roles, txt_password, txt_nohp, txt_perusahaan, txtPut;
    Button btn_submit, btn_cancel;
    SqliteHelper SQLite = new SqliteHelper(this);
    String id, name, email, roles, password, nohp, perusahaan,ambil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);


        txtPut = (EditText) findViewById(R.id.putExtra);


        Intent intent = getIntent();
        txtPut.setText(intent.getStringExtra("put"));

        ambil = txtPut.getText().toString();


        txt_id = (EditText) findViewById(R.id.txt_id);
        txt_name = (EditText) findViewById(R.id.txt_name);
        txt_email = (EditText) findViewById(R.id.txt_email);
        txt_roles= (EditText) findViewById(R.id.txt_roles);
        txt_password= (EditText) findViewById(R.id.txt_password);
        txt_nohp= (EditText) findViewById(R.id.txt_nohp);
        txt_perusahaan= (EditText) findViewById(R.id.txt_perusahaan);

        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);

        id = getIntent().getStringExtra(UserActivity.TAG_ID);
        name = getIntent().getStringExtra(UserActivity.TAG_NAME);
        email = getIntent().getStringExtra(UserActivity.TAG_EMAIL);
        roles = getIntent().getStringExtra(UserActivity.TAG_ROLES);
        password = getIntent().getStringExtra(UserActivity.TAG_PASSWORD);
        nohp = getIntent().getStringExtra(UserActivity.TAG_NOHP);
        perusahaan = getIntent().getStringExtra(UserActivity.TAG_PERUSAHAAN);

        if (ambil.equals("konsultan") && id == null || id == "") {
            setTitle("Add Data");
            txt_roles.setText("Pekerja Lapangan");
            txt_roles.setVisibility(View.INVISIBLE);
        }else if(id == null || id == ""){
            setTitle("Add Data");
        }else if(ambil.equals("konsultan")) {
            setTitle("Edit Data Pekerja Lapangan");
            txt_id.setText(id);
            txt_name.setText(name);
            txt_email.setText(email);
            txt_roles.setText(roles);
            txt_roles.setVisibility(View.INVISIBLE);
            txt_password.setText(password);
            txt_nohp.setText(nohp);
            txt_perusahaan.setText(perusahaan);
        }
        else{
            setTitle("Edit Data");
            txt_id.setText(id);
            txt_name.setText(name);
            txt_email.setText(email);
            txt_roles.setText(roles);
            txt_password.setText(password);
            txt_nohp.setText(nohp);
            txt_perusahaan.setText(perusahaan);

        }

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (txt_id.getText().toString().equals("")) {
                        save();
                    } else {
                        edit();
                    }
                } catch (Exception e){
                    Log.e("Submit", e.toString());
                }
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blank();
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                blank();
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Make blank all Edit Text
    private void blank() {
        txt_name.requestFocus();
        txt_id.setText(null);
        txt_name.setText(null);
        txt_email.setText(null);
        txt_roles.setText(null);
        txt_password.setText(null);
        txt_nohp.setText(null);
        txt_perusahaan.setText(null);

    }

    // Save data to SQLite database
    private void save() {
        if (String.valueOf(txt_name.getText()).equals(null) || String.valueOf(txt_name.getText()).equals("") ||
                String.valueOf(txt_email.getText()).equals(null) || String.valueOf(txt_email.getText()).equals("")) {
            Toast.makeText(getApplicationContext(),
                    "Please input name or address ...", Toast.LENGTH_SHORT).show();
        } else {
            SQLite.insert(txt_name.getText().toString().trim(), txt_email.getText().toString().trim(), txt_roles.getText().toString().trim(),  txt_password.getText().toString().trim(),txt_nohp.getText().toString().trim(), txt_perusahaan.getText().toString().trim());
            blank();
            finish();
        }
    }

    // Update data in SQLite database
    private void edit() {
        if (String.valueOf(txt_name.getText()).equals(null) || String.valueOf(txt_name.getText()).equals("") ||
                String.valueOf(txt_email.getText()).equals(null) || String.valueOf(txt_email.getText()).equals("")) {
            Toast.makeText(getApplicationContext(),
                    "Please input name or address ...", Toast.LENGTH_SHORT).show();
        } else {
            SQLite.update(Integer.parseInt(txt_id.getText().toString().trim()), txt_name.getText().toString().trim(),
                    txt_email.getText().toString().trim(), txt_roles.getText().toString().trim(),  txt_password.getText().toString().trim(), txt_nohp.getText().toString().trim(), txt_perusahaan.getText().toString().trim());
            blank();
            finish();
        }
    }

}
