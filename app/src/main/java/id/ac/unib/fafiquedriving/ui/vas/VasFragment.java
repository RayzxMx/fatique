package id.ac.unib.fafiquedriving.ui.vas;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import id.ac.unib.fafiquedriving.R;
import id.ac.unib.fafiquedriving.databinding.FragmentSlideshowBinding;
import id.ac.unib.fafiquedriving.databinding.FragmentVasBinding;
import id.ac.unib.fafiquedriving.ui.slideshow.SlideshowViewModel;

public class VasFragment extends Fragment {

    private FragmentVasBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        VasViewModel vasViewModel =
                new ViewModelProvider(this).get(VasViewModel.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Channel Name";
            String description = "Deskripsi Channel";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("channel_id", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = requireContext().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
        // Membuat builder notifikasi
        NotificationCompat.Builder builder = new NotificationCompat.Builder(requireContext(), "channel_id")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Nilai Vas")
                .setContentText("Isi Notifikasi")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        int notificationId = 1; // Notifikasi pertama
        // Munculkan notifikasi
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(requireContext());
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {

        }
        //notificationManager.notify(notificationId, builder.build());
        binding = FragmentVasBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final TextView textView = binding.textVas;
        vasViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        // Mendapatkan objek SharedPreferences
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE);
        // Mendapatkan data session login
        Integer pengemudi_id = sharedPreferences.getInt("pengemudi_id", 0);
        vasViewModel.setup(notificationManager, builder, requireContext());
        //builder.setContentText(vasViewModel.nilai_vas(pengemudi_id));
        vasViewModel.nilai_vas(pengemudi_id);
        //notificationManager.notify(notificationId, builder.build());
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}