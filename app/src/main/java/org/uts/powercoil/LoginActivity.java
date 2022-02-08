package org.uts.powercoil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import org.uts.powercoil.konsultan.HomeKonsultan;
import org.uts.powercoil.lapangan.HomeLapangan;
import org.uts.powercoil.operator.HomeOperator;
import org.uts.powercoil.user.UserActivity;

import java.util.ArrayList;
import java.util.List;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPass, etRoles;
    private TextView onclick1;
    private ImageView onclick2;
    private CircularProgressButton btnlogin;
    SqliteHelper sqliteHelper;
    TextInputLayout textInputLayoutEmail;
    TextInputLayout textInputLayoutPassword;

//    Spinner spNamen;
//    List<String> li;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sqliteHelper = new SqliteHelper(this);

        etEmail = (EditText) findViewById(R.id.etemail);
        etPass = (EditText) findViewById(R.id.etpassword);
        etRoles = (EditText) findViewById(R.id.etroles);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputPassword);
//        spNamen = (Spinner) findViewById(R.id.listItem);


//        li=new ArrayList<String>();
//
//        li.add("Admin");
//        li.add("Konsultan");
//        li.add("Operator");
//        li.add("Pekerja Lapangan");

//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, li);
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spNamen.setAdapter(dataAdapter);

//        onclick1 = findViewById(R.id.onLoginClick1);
//        onclick2 = findViewById(R.id.onLoginClick2);
        btnlogin = (CircularProgressButton) findViewById(R.id.btnLogin);

//        onclick1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
//
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
//                overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
//            }
//        });
//
//        onclick2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
//                overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
//            }
//        });

        btnlogin = (CircularProgressButton) findViewById(R.id.btnLogin);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginUser();
            }
        });

    }

    private void loginUser() {
        if (validate()) {

            //Get values from EditText fields
            String Email = etEmail.getText().toString();
            String Password = etPass.getText().toString();
//            String Roles = spNamen.getSelectedItem().toString();
            String nohp = etRoles.getText().toString();
            String Perusahaan = etRoles.getText().toString();

            //Authenticate user
            User currentUser = sqliteHelper.Authenticate(new User(null, null, Email, null, Password, nohp, Perusahaan));
            //Check Authentication is successful or not
            if (currentUser != null && currentUser.roles.equals("Admin")) {
                Snackbar.make(btnlogin, "Successfully Logged in!", Snackbar.LENGTH_LONG).show();
                //User Logged in Successfully Launch You home screen activity
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
            else if (currentUser != null && currentUser.roles.equals("Konsultan")) {
                    Snackbar.make(btnlogin, "Successfully Logged in!", Snackbar.LENGTH_LONG).show();
                    //User Logged in Successfully Launch You home screen activity
                    Intent intent=new Intent(LoginActivity.this, HomeKonsultan.class);
                    intent.putExtra("data", Email);

                    startActivity(intent);
                    finish();
            } else if (currentUser != null && currentUser.roles.equals("Pekerja Lapangan")) {
                Snackbar.make(btnlogin, "Successfully Logged in!", Snackbar.LENGTH_LONG).show();
                //User Logged in Successfully Launch You home screen activity
                Intent intent = new Intent(LoginActivity.this, HomeLapangan.class);
                intent.putExtra("data", Email);
                startActivity(intent);
                finish();
            }else if (currentUser != null && currentUser.roles.equals("Operator")) {
                Snackbar.make(btnlogin, "Successfully Logged in!", Snackbar.LENGTH_LONG).show();
                //User Logged in Successfully Launch You home screen activity
                Intent intent = new Intent(LoginActivity.this, HomeOperator.class);
                intent.putExtra("data", Email);
                startActivity(intent);
                finish();
            } else {

                //User Logged in Failed
                Snackbar.make(btnlogin, "Failed to log in , please try again", Snackbar.LENGTH_LONG).show();
            }
        }
    }

    //This method is used to validate input given by user
    public boolean validate() {
        boolean valid = false;

        //Get values from EditText fields
        String Email = etEmail.getText().toString();
        String Password = etPass.getText().toString();

        //Handling validation for Email field
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            valid = false;
            textInputLayoutEmail.setError("Please enter valid email!");
        } else {
            valid = true;
            textInputLayoutEmail.setError(null);
        }

        //Handling validation for Password field
        if (Password.isEmpty()) {
            valid = false;
            textInputLayoutPassword.setError("Please enter valid password!");
        } else {
            if (Password.length() > 5) {
                valid = true;
                textInputLayoutPassword.setError(null);
            } else {
                valid = false;
                textInputLayoutPassword.setError("Password is to short!");
            }
        }

        return valid;
    }




}