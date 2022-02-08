package org.uts.powercoil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import org.uts.powercoil.fragment.NotificationFragment;
import org.uts.powercoil.fragment.HomeFragment;

public class MainActivity extends FragmentActivity implements NavigationView.OnNavigationItemSelectedListener  {
    BottomNavigationView bottomNavigation;

    FusedLocationProviderClient mFusedLocationClient;
    SupportMapFragment mapFrag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        //Menampilkan halaman Fragment yang pertama kali muncul
        getFragmentPage(new NotificationFragment());


//
//        TextView textView=(TextView) findViewById(R.id.txt_bundle);
//
//        Intent intent = getIntent();
//        String str = intent.getStringExtra("data");
//        textView.setText(str);
//

        /*Inisialisasi BottomNavigationView beserta listenernya untuk
         *menangkap setiap kejadian saat salah satu menu item diklik
         */
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
                        startActivity(newAct);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
                        break;
                    case R.id.navigation_notifications:
                        Intent newAct2 = new Intent(getApplicationContext(), MapsActivity.class);
                        startActivity(newAct2);
                        overridePendingTransition(R.anim.slide_in_right,android.R.anim.slide_out_right);

                }
                return getFragmentPage(fragment);
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        findViewById(R.id.floatingActionButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.openDrawer(GravityCompat.START);
            }
        });
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_notif) {
            Intent newNotif = new Intent(getApplicationContext(), NotifikasiActivity.class);
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


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}