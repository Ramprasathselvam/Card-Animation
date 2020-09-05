package com.example.helloworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loginView extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_view);

        //Setup Firebase
        FirebaseApp.initializeApp(this);

        //Check Login
        //isUserLogin();


    }

    public void createAccount (View view){
        //Check Email and Password
        String email = "ramprasath@gmail.com";
        String psw = "1234qwer";
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, psw)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            //Nav to Next Page
                            Log.d("create","success");
                        }else if (task.isCanceled()){
                            //Handle Canceled...
                        }

                    }
                });
    }

    public void loginBtnTapped(View view){
        String email = "ramprasath@gmail.com";
        String psw = "1234qwer";
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, psw)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Login", "signInWithEmail:success");

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Error", "signInWithEmail:failure", task.getException());
                            Toast.makeText(loginView.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }



    public void isUserLogin(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            //Navigation
            startActivity(new Intent(getApplicationContext(), ProfileDetails.class));
            finish();
            return;
        }
    }
}