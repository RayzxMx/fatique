package id.ac.unib.fafiquedriving;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import id.ac.unib.fafiquedriving.databinding.ActivityMainBinding;

public class Main extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = new Intent(this, login.class);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Mendapatkan objek SharedPreferences
        sharedPreferences = getSharedPreferences("MyAppPreferences", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        // Mendapatkan data session login
        String username = sharedPreferences.getString("username", "null");
        String _nama = sharedPreferences.getString("name", "null");
        String role = sharedPreferences.getString("role","null");
        setSupportActionBar(binding.appBarMain.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        View nav_header = navigationView.getHeaderView(0);
        TextView nama = nav_header.findViewById(R.id.username_nav);
        nama.setText(_nama);
        Menu navigationMenu = navigationView.getMenu();
        MenuItem vasMenuItem = navigationMenu.findItem(R.id.nav_vas);
        MenuItem slideshowMenuItem = navigationMenu.findItem(R.id.nav_slideshow);
        MenuItem monitoringMenuItem = navigationMenu.findItem(R.id.nav_monitoring);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_vas ,R.id.nav_slideshow, R.id.nav_monitoring)
                .setOpenableLayout(drawer)
                .build();
        if (role.equals("admin")){
            vasMenuItem.setVisible(false);
            slideshowMenuItem.setVisible(false);
        } else {
            vasMenuItem.setVisible(true);
            slideshowMenuItem.setVisible(true);
        }


        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void logout(MenuItem item) {
        // Lakukan logout dan arahkan pengguna ke halaman login
        editor.putString("username", "");
        editor.putString("name", "");
        editor.putBoolean("isLoggedIn", false);

        // Menyimpan perubahan
        editor.apply();

        startActivity(intent);
        finish();
    }
}