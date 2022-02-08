package org.uts.powercoil.lapangan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import org.uts.powercoil.FaqActivity;
import org.uts.powercoil.ListrikActivity;
import org.uts.powercoil.LoginActivity;
import org.uts.powercoil.NotifikasiActivity;
import org.uts.powercoil.PanduanActivity;
import org.uts.powercoil.R;
import org.uts.powercoil.konsultan.HomeKonsultan;
import org.uts.powercoil.konsultan.PengkajianActivity;
import org.uts.powercoil.user.UserActivity;

public class HomeLapangan extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    EditText txtPut;
    String ambil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_lapangan);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view1);
        navigationView.setNavigationItemSelectedListener(this);

        txtPut = (EditText) findViewById(R.id.putExtra);


        Intent intent = getIntent();
        txtPut.setText(intent.getStringExtra("put"));

        ambil = txtPut.getText().toString();

        CardView BtnListrik = (CardView) findViewById(R.id.cvListrik);

        BtnListrik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeLapangan.this, ListrikActivity.class);
                startActivity(intent);
            }
        });

        CardView BtnPengkajian = (CardView) findViewById(R.id.cvPengkajian);

        BtnPengkajian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeLapangan.this, PengkajianActivity.class);
                intent.putExtra("put", "lapangan");
                startActivity(intent);
            }
        });


        findViewById(R.id.floatingActionButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerhome_lapangan);
                drawer.openDrawer(GravityCompat.START);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.nav_notif) {

            Toast.makeText(getApplicationContext(), "Hak Akses Tidak di izinkan", Toast.LENGTH_SHORT).show();

        }else if(id == R.id.nav_panduan) {
            Intent newNotif = new Intent(getApplicationContext(), PanduanActivity.class);
            startActivity(newNotif);
        }else if(id == R.id.nav_faq) {
            Intent newNotif = new Intent(getApplicationContext(), FaqActivity.class);
            startActivity(newNotif);
        } else if (id == R.id.nav_logout) {

            Intent logout = new Intent(getApplicationContext(), LoginActivity.class);
            logout.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(logout);
            finish();
            Toast.makeText(getApplicationContext(), "Log Out Successful", Toast.LENGTH_SHORT).show();
//            MainActivity.this.finish();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerhome_lapangan);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}