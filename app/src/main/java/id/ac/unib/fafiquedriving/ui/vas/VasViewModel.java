package id.ac.unib.fafiquedriving.ui.vas;

import android.content.SharedPreferences;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import id.ac.unib.fafiquedriving.R;
import id.ac.unib.fafiquedriving.login;


public class VasViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private DatabaseReference mDatabase;
    private final MutableLiveData<String> mText;

    public VasViewModel() {
        mText = new MutableLiveData<>();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("data_vas_harian").orderByKey().limitToLast(1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                        // Akses data yang ditemukan di sini
                        Integer vasValue = childSnapshot.child("nilai_vas").getValue(Integer.class);
                        // Lakukan operasi dengan nilai VAS yang ditemukan
                        // Misalnya, tampilkan nilai VAS menggunakan Snackbar
                        mText.setValue(vasValue.toString());
                    }
                } else {
                    // Data tidak ditemukan
                    mText.setValue("Data Tidak ditemukan");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Penanganan kesalahan jika terjadi
            }
        });

        //mText.setValue("This is vas fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}