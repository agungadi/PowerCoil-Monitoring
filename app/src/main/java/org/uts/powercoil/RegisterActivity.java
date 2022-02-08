package org.uts.powercoil;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

public class RegisterActivity extends AppCompatActivity {

    private EditText etname, etemail, etpassword, etroles;
    private CircularProgressButton btnregister;
    private TextView tvlogin;

    private TextView onclick3;
    private ImageView onclick4;
    SqliteHelper sqliteHelper;

    //Declaration TextInputLayout
    TextInputLayout textInputLayoutUserName;
    TextInputLayout textInputLayoutEmail;
    TextInputLayout textInputLayoutPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        sqliteHelper = new SqliteHelper(this);

        getSupportActionBar().hide();


        etname = (EditText) findViewById(R.id.etname);
        etemail = (EditText) findViewById(R.id.etemail);
        etpassword = (EditText) findViewById(R.id.etpassword);
        etroles = (EditText) findViewById(R.id.etroles);

        onclick3 = findViewById(R.id.onLoginClick3);
        onclick4 = findViewById(R.id.onLoginClick4);
        btnregister = (CircularProgressButton) findViewById(R.id.btnRegister);

        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputPassword);
        textInputLayoutUserName = (TextInputLayout) findViewById(R.id.textInputName);

        onclick3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);
            }
        });

        onclick4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);
            }
        });

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                registerMe();
            }
        });


    }
//    private void registerMe() {
//        if (validate()) {
//            String UserName = etname.getText().toString();
//            String Email = etemail.getText().toString();
//            String Roles = etroles.getText().toString();
//            String Password = etpassword.getText().toString();
//
//            //Check in the database is there any user associated with  this email
//            if (!sqliteHelper.isEmailExists(Email)) {
//
//                //Email does not exist now add new user to database
//                sqliteHelper.addUser(new User(null, UserName, Email,Roles, Password, ));
//                Snackbar.make(btnregister, "User created successfully! Please Login ", Snackbar.LENGTH_LONG).show();
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        login();
//                    }
//                }, Snackbar.LENGTH_LONG);
//            }else {
//
//                //Email exists with email input provided so show error user already exist
//                Snackbar.make(btnregister, "User already exists with same email ", Snackbar.LENGTH_LONG).show();
//            }
//        }
//    }
//
//    //This method is used to validate input given by user
//    public boolean validate() {
//        boolean valid = false;
//
//        //Get values from EditText fields
//        String UserName = etname.getText().toString();
//        String Email = etemail.getText().toString();
//        String Password = etpassword.getText().toString();
//
//
//
//        //Handling validation for UserName field
//        if (UserName.isEmpty()) {
//            valid = false;
//            textInputLayoutUserName.setError("Please enter valid username!");
//        } else {
//            if (UserName.length() > 5) {
//                valid = true;
//                textInputLayoutUserName.setError(null);
//            } else {
//                valid = false;
//                textInputLayoutUserName.setError("Username is to short!");
//            }
//        }
//
//        //Handling validation for Email field
//        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
//            valid = false;
//            textInputLayoutEmail.setError("Please enter valid email!");
//        } else {
//            valid = true;
//            textInputLayoutEmail.setError(null);
//        }
//
//        //Handling validation for Password field
//        if (Password.isEmpty()) {
//            valid = false;
//            textInputLayoutPassword.setError("Please enter valid password!");
//        } else {
//            if (Password.length() > 5) {
//                valid = true;
//                textInputLayoutPassword.setError(null);
//            } else {
//                valid = false;
//                textInputLayoutPassword.setError("Password is to short!");
//            }
//        }
//
//
//        return valid;
//    }
//
//    public void login(){
//        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
//        startActivity(intent);
//    }

}