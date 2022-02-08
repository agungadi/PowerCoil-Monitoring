package org.uts.powercoil.konsultan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import org.uts.powercoil.FaqActivity;
import org.uts.powercoil.LoginActivity;
import org.uts.powercoil.NotifikasiActivity;
import org.uts.powercoil.PanduanActivity;
import org.uts.powercoil.R;
import org.uts.powercoil.user.UserActivity;

public class HomeKonsultan extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_home);


        CardView BtnPengkajian = (CardView) findViewById(R.id.cvPengkajian);

        BtnPengkajian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeKonsultan.this, PengkajianActivity.class);
                intent.putExtra("put", "konsultan");
                startActivity(intent);
            }
        });

        CardView BtnUser = (CardView) findViewById(R.id.cvUser);

        BtnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeKonsultan.this, UserActivity.class);
                intent.putExtra("put", "konsultan");
                startActivity(intent);
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view1);
        navigationView.setNavigationItemSelectedListener(this);

        findViewById(R.id.floatingActionButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerhome_layout);
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


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerhome_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}