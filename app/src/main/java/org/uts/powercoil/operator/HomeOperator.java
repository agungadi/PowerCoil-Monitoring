package org.uts.powercoil.operator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import org.uts.powercoil.AlertActivity;
import org.uts.powercoil.FaqActivity;
import org.uts.powercoil.ListrikActivity;
import org.uts.powercoil.LoginActivity;
import org.uts.powercoil.MapsActivity;
import org.uts.powercoil.NotifikasiActivity;
import org.uts.powercoil.PanduanActivity;
import org.uts.powercoil.R;
import org.uts.powercoil.fragment.NotificationFragment;
import org.uts.powercoil.lapangan.HomeLapangan;

public class HomeOperator extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_operator);


        CardView BtnListrik = (CardView) findViewById(R.id.cvListrik);

        BtnListrik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeOperator.this, ListrikActivity.class);
//                intent.putExtra("put", "operator");
                startActivity(intent);
            }
        });

        CardView BtnNotif = (CardView) findViewById(R.id.cvNotif);

        BtnNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeOperator.this, AlertActivity.class);
//                intent.putExtra("put", "operator");
                startActivity(intent);
            }
        });
        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {


            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment = null;
                //Menantukan halaman Fragment yang akan tampil
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        fragment = new NotificationFragment();
                        break;
                    case R.id.nav_pemberitahuan:
                        Intent newAct = new Intent(getApplicationContext(), NotifikasiActivity.class);
                        newAct.putExtra("put", "operator");
                        startActivity(newAct);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
                        break;
                    case R.id.navigation_notifications:
                        Intent newAct2 = new Intent(getApplicationContext(), MapsActivity.class);
                        newAct2.putExtra("put", "operator");
                        startActivity(newAct2);
                        overridePendingTransition(R.anim.slide_in_right,android.R.anim.slide_out_right);

                }
                return getFragmentPage(fragment);
            }
        });



        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view1);
        navigationView.setNavigationItemSelectedListener(this);

        findViewById(R.id.floatingActionButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerhome_operator);
                drawer.openDrawer(GravityCompat.START);
            }
        });


    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.nav_notif) {
            Intent newNotif = new Intent(getApplicationContext(), NotifikasiActivity.class);
            newNotif.putExtra("put", "operator");
            startActivity(newNotif);;
        }else if(id == R.id.nav_panduan) {
            Intent newNotif = new Intent(getApplicationContext(), PanduanActivity.class);
            startActivity(newNotif);
        }else if(id == R.id.nav_faq) {
            Intent newNotif = new Intent(getApplicationContext(), FaqActivity.class);
            startActivity(newNotif);
        }else if (id == R.id.nav_logout) {

            Intent logout = new Intent(getApplicationContext(), LoginActivity.class);
            logout.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(logout);
            finish();
            Toast.makeText(getApplicationContext(), "Log Out Successful", Toast.LENGTH_SHORT).show();
//            MainActivity.this.finish();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerhome_operator);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //Menampilkan halaman Fragment
    private boolean getFragmentPage(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}