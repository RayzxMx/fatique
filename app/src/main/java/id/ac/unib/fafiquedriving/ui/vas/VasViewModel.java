package id.ac.unib.fafiquedriving.ui.vas;

import android.Manifest;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
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
    private NotificationManagerCompat notificationManager;
    private NotificationCompat.Builder builder;
    private Context context;

    public VasViewModel() {
        mText = new MutableLiveData<>();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //mText.setValue("This is vas fragment");
    }

    public void setNotificationManager(NotificationManagerCompat n) {
        this.notificationManager = n;
    }

    public void setNotificationCompat(NotificationCompat.Builder b) {
        this.builder = b;
    }

    public void setContext(Context c){
        this.context = c;
    }
    public void setup(NotificationManagerCompat n, NotificationCompat.Builder b, Context c){
        this.context = c;
        this.builder = b;
        this.notificationManager = n;
    }
    public String nilai_vas(Integer pengemudi_id) {
        String vas = "";
        mDatabase.child("detail_perjalanan_vas")
                .orderByChild("pengemudi_id")
                .equalTo(pengemudi_id)
                .limitToLast(1)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                                Integer vasValue = childSnapshot.child("nilai_vas").getValue(Integer.class);
                                if (vasValue >= 0 && vasValue < 4) {
                                    mText.setValue("Mild");
                                } else if (vasValue >= 4 && vasValue < 7) {
                                    mText.setValue("Moderate");
                                } else if (vasValue >= 7 && vasValue < 10) {
                                    mText.setValue("Severe");
                                    builder.setContentText("Severe");
                                    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                                        // TODO: Consider calling
                                        //    ActivityCompat#requestPermissions
                                        // here to request the missing permissions, and then overriding
                                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                        //                                          int[] grantResults)
                                        // to handle the case where the user grants the permission. See the documentation
                                        // for ActivityCompat#requestPermissions for more details.
                                        //return;
                                    }
                                    notificationManager.notify(1, builder.build());
                                }
                                //mText.setValue(vasValue.toString());
                            }
                        } else {
                            mText.setValue("null");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        // Penanganan kesalahan jika terjadi
                    }
                });
        return mText.getValue();
    }

    public LiveData<String> getText() {
        return mText;
    }
}