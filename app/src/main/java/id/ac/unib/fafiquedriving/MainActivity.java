package id.ac.unib.fafiquedriving;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getSharedPreferences("MyAppPreferences", MODE_PRIVATE);
        // Cek apakah ini instalasi pertama atau bukan
        boolean isFirstInstall = sharedPreferences.getBoolean("isFirstInstall", true);
        if (isFirstInstall) {
            // Jika instalasi pertama, tampilkan tampilan sambutan
            setContentView(R.layout.start_layout);

            // Setelah tampilan sambutan ditampilkan, tandai bahwa ini bukan instalasi pertama
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isFirstInstall", false);
            editor.apply();
        } else {
            // Mendapatkan nilai isLoggedIn
            boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
            // Pengecekan status login
            if (isLoggedIn) {
                // Jika sudah login, arahkan ke halaman beranda
                startActivity(new Intent(this, Main.class));
                finish();
            } else {
                Intent intent = new Intent(this, login.class);
                startActivity(intent);
                finish();
            }
        }
    }
    public void btn_lanjut_click(View view) {
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
        finish();
    }
}