package org.uts.powercoil.user;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.uts.powercoil.R;
import org.uts.powercoil.SqliteHelper;
import org.uts.powercoil.adapter.Adapter;
import org.uts.powercoil.model.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserActivity extends AppCompatActivity {

    ListView listView;
    AlertDialog.Builder dialog;
    List<Data> itemList = new ArrayList<Data>();
    Adapter adapter;
    SqliteHelper SQLite = new SqliteHelper(this);

    EditText txtPut;
    String ambil;

    public static final String TAG_ID = "id";
    public static final String TAG_NAME = "username";
    public static final String TAG_EMAIL = "email";
    public static final String TAG_ROLES = "roles";
    public static final String TAG_PASSWORD = "password";
    public static final String TAG_NOHP = "nohp";
    public static final String TAG_PERUSAHAAN = "perusahaan";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        ActionBar actionBar = getSupportActionBar();
        setTitle("List User");
        actionBar.setDisplayHomeAsUpEnabled(true);

        txtPut = (EditText) findViewById(R.id.putExtra);


        Intent intent = getIntent();
        txtPut.setText(intent.getStringExtra("put"));

        ambil = txtPut.getText().toString();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        SQLite = new SqliteHelper(getApplicationContext());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        listView = (ListView) findViewById(R.id.list_view);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ambil.equals("konsultan")) {
                    Intent intent = new Intent(UserActivity.this, AddEdit.class);
                    intent.putExtra("put", "konsultan");
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(UserActivity.this, AddEdit.class);
                    startActivity(intent);
                }
            }
        });


        adapter = new org.uts.powercoil.adapter.Adapter(UserActivity.this, itemList);
        listView.setAdapter(adapter);

        // long press listview to show edit and delete
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, View view,
                                           final int position, long id) {
                // TODO Auto-generated method stub
                final String idx = itemList.get(position).getId();
                final String name = itemList.get(position).getName();
                final String email = itemList.get(position).getEmail();
                final String roles = itemList.get(position).getRoles();
                final String password = itemList.get(position).getPassword();
                final String nohp = itemList.get(position).getNoHp();
                final String perusahaan = itemList.get(position).getPerusahaan();

                final CharSequence[] dialogitem = {"Edit", "Delete"};
                dialog = new AlertDialog.Builder(UserActivity.this);
                dialog.setCancelable(true);
                dialog.setItems(dialogitem, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        switch (which) {
                            case 0:
                                if(ambil.equals("konsultan")) {
                                    Intent intent = new Intent(UserActivity.this, AddEdit.class);
                                    intent.putExtra("put", "konsultan");
                                    intent.putExtra(TAG_ID, idx);
                                    intent.putExtra(TAG_NAME, name);
                                    intent.putExtra(TAG_EMAIL, email);
                                    intent.putExtra(TAG_ROLES, roles);
                                    intent.putExtra(TAG_PASSWORD, password);
                                    intent.putExtra(TAG_NOHP, nohp);
                                    intent.putExtra(TAG_PERUSAHAAN, perusahaan);
                                    startActivity(intent);
                                }else {
                                    Intent intent = new Intent(UserActivity.this, AddEdit.class);
                                    intent.putExtra(TAG_ID, idx);
                                    intent.putExtra(TAG_NAME, name);
                                    intent.putExtra(TAG_EMAIL, email);
                                    intent.putExtra(TAG_ROLES, roles);
                                    intent.putExtra(TAG_PASSWORD, password);
                                    intent.putExtra(TAG_NOHP, nohp);
                                    intent.putExtra(TAG_PERUSAHAAN, perusahaan);
                                    startActivity(intent);
                                }
                                break;
                            case 1:
                                SQLite.delete(Integer.parseInt(idx));
                                itemList.clear();
                                if (ambil.equals("konsultan")) {
                                    getKonsultanData();
                                }else {
                                    getAllData();
                                }
                                break;
                        }
                    }
                }).show();
                return false;
            }
        });

        if (ambil.equals("konsultan")) {
            getKonsultanData();

        }else {
            getAllData();

        }

    }

    private void getAllData() {
        ArrayList<HashMap<String, String>> row = SQLite.getAllData();

        for (int i = 0; i < row.size(); i++) {
            String id = row.get(i).get(TAG_ID);
            String nama = row.get(i).get(TAG_NAME);
            String email = row.get(i).get(TAG_EMAIL);
            String roles = row.get(i).get(TAG_ROLES);
            String password = row.get(i).get(TAG_PASSWORD);
            String nohp = row.get(i).get(TAG_NOHP);
            String perusahaan = row.get(i).get(TAG_PERUSAHAAN);

            Data data = new Data();

            data.setId(id);
            data.setName(nama);
            data.setEmail(email);
            data.setRoles(roles);
            data.setPassword(password);
            data.setNoHp(nohp);
            data.setPerusahaan(perusahaan);

            itemList.add(data);
        }

        adapter.notifyDataSetChanged();
    }

    private void getKonsultanData() {
        ArrayList<HashMap<String, String>> row = SQLite.getDataKonsultan();

        for (int i = 0; i < row.size(); i++) {
            String id = row.get(i).get(TAG_ID);
            String nama = row.get(i).get(TAG_NAME);
            String email = row.get(i).get(TAG_EMAIL);
            String roles = row.get(i).get(TAG_ROLES);
            String password = row.get(i).get(TAG_PASSWORD);
            String nohp = row.get(i).get(TAG_NOHP);
            String perusahaan = row.get(i).get(TAG_PERUSAHAAN);

            Data data = new Data();

            data.setId(id);
            data.setName(nama);
            data.setEmail(email);
            data.setRoles(roles);
            data.setPassword(password);
            data.setNoHp(nohp);
            data.setPerusahaan(perusahaan);

            itemList.add(data);
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        itemList.clear();
        if (ambil.equals("konsultan")) {
            getKonsultanData();
        }else {
            getAllData();
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}



