package id.ac.unib.fafiquedriving.ui.gallery;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import id.ac.unib.fafiquedriving.R;
import id.ac.unib.fafiquedriving.databinding.FragmentHomeBinding;
import id.ac.unib.fafiquedriving.databinding.FragmentTransisiFragmentBinding;
import id.ac.unib.fafiquedriving.ui.home.HomeViewModel;

public class transisi_fragment extends Fragment {

    private FragmentTransisiFragmentBinding binding;

    public static transisi_fragment newInstance() {
        return new transisi_fragment();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentTransisiFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        switch_fragment();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    public void switch_fragment(){
        // Mendapatkan instance dari FragmentManager
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

// Membuat transaksi fragment
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

// Membuat instance dari fragment yang akan ditampilkan
        Fragment fragment = new GalleryFragment(); // Gantilah TargetFragment dengan fragment yang ingin Anda tampilkan

// Mengganti fragment yang saat ini ditampilkan dengan fragment yang baru
        fragmentTransaction.replace(R.id.fragment_container, fragment);

// Menambahkan transaksi ke back stack (jika diperlukan, agar pengguna dapat kembali)
        fragmentTransaction.addToBackStack(null);
// Melakukan commit transaksi
        fragmentTransaction.commit();

    }

}