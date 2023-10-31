package id.ac.unib.fafiquedriving;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.ui.AppBarConfiguration;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import id.ac.unib.fafiquedriving.databinding.ActivityMainBinding;

public class login extends AppCompatActivity {
    Intent intent;
    private SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        intent = new Intent(this, Main.class);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // Mendapatkan objek SharedPreferences
        sharedPreferences = getSharedPreferences("MyAppPreferences", MODE_PRIVATE);
        // Membuat objek Editor untuk mengedit SharedPreferences
        editor = sharedPreferences.edit();
    }
    public void btn_login_click(View view) {
        EditText eUsername = findViewById(R.id.eUsername);
        EditText ePassword = findViewById(R.id.ePassword);
        String username = eUsername.getText().toString();
        String password = ePassword.getText().toString();
        mDatabase.child("user")
                .orderByChild("id")
                .equalTo(username)
                .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    DataSnapshot childSnapshot = dataSnapshot.getChildren().iterator().next();
                    // Akses data yang ditemukan di sini
                    String _password = childSnapshot.child("password").getValue(String.class);
                    String role = childSnapshot.child("role").getValue(String.class);

                    // Lakukan operasi dengan data yang ditemukan
                    if (password.equals(_password)) {
                        Snackbar.make(view, "Login Success", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        // Menyimpan data session login
                        editor.putString("username", username);
                        editor.putString("password", password);
                        editor.putString("role", role);
                        editor.putBoolean("isLoggedIn", true);
                        getName(username, new NameCallback() {
                            @Override
                            public void onNameReceived(String name, Integer pengemudi_id) {
                                if (!name.isEmpty()) {
                                    editor.putString("name", name);
                                    editor.putInt("pengemudi_id", pengemudi_id);
                                } else {
                                    editor.putString("name", username);
                                    editor.putInt("pengemudi_id",0);
                                }
                                // Menyimpan perubahan
                                editor.apply();
                                Integer p = sharedPreferences.getInt("pengemudi_id", 0);
                                startActivity(intent);
                                finish();
                            }
                        });
                    } else {
                        Snackbar.make(view, "Login Failed, Username or Password is Wrong", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        ePassword.setText("");
                    }
                } else {
                    // Data tidak ditemukan
                    Snackbar.make(view, "Login Failed, Username or Password is Wrong", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    ePassword.setText("");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Penanganan kesalahan jika terjadi
            }
        });
    }
    public interface NameCallback {
        void onNameReceived(String name, Integer pengemudi_id);
    }

    public void getName(String username, NameCallback callback) {
        mDatabase.child("pengemudi").orderByChild("user_id").equalTo(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                        String name = childSnapshot.child("nama").getValue(String.class);
                        Integer pengemudi_id = childSnapshot.child("id").getValue(Integer.class);
                        if (name != null && !name.isEmpty()) {
                            callback.onNameReceived(name,pengemudi_id);
                            return; // Menghentikan iterasi setelah nama ditemukan
                        }
                    }
                }
                callback.onNameReceived("",0); // Mengirimkan string kosong jika nama tidak ditemukan
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Penanganan kesalahan jika terjadi
            }
        });
    }

}