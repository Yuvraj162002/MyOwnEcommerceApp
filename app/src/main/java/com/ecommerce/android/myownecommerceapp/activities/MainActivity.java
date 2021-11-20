package com.ecommerce.android.myownecommerceapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.ecommerce.android.myownecommerceapp.Fragment.HomeFragment;
import com.ecommerce.android.myownecommerceapp.R;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;


    Fragment homeFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
        homeFragment = new HomeFragment();
        loadFragment(homeFragment);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);

        // first parameter is the file for icon and second one is menu
        return super.onCreateOptionsMenu(menu);
    }

    private void loadFragment(Fragment homeFragment) {

        FragmentTransaction transaction  = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.home_container , homeFragment);
        transaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.cart_Button:

                Intent intent = new Intent(MainActivity.this , CartActivity.class);
                startActivity(intent);
                break;

            case R.id.log_out_button:

//                SharedPreferences myPrefs = getSharedPreferences("SelfTrip", MODE_PRIVATE);
//                SharedPreferences.Editor editor = myPrefs.edit();
//                editor.clear();
//                editor.commit();
//                Intent loginPageIntent = new Intent(getApplicationContext(), LoginActivity.class);
//                loginPageIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(loginPageIntent);
//                break;
                firebaseAuth.signOut();
                startActivity(new Intent(MainActivity.this , LoginActivity.class));
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}