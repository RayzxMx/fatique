package id.ac.unib.fafiquedriving.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.DialogPreference;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import id.ac.unib.fafiquedriving.QuestionAdapter;
import id.ac.unib.fafiquedriving.QuestionAdapter_new;
import id.ac.unib.fafiquedriving.R;
import id.ac.unib.fafiquedriving.databinding.FragmentHomeBinding;
import id.ac.unib.fafiquedriving.ui.daftarPengemudi.DaftarPengemudiFragment;
import id.ac.unib.fafiquedriving.ui.gallery.GalleryFragment;
import id.ac.unib.fafiquedriving.ui.gallery.transisi_fragment;
import id.ac.unib.fafiquedriving.ui.vas.VasFragment;
//import id.ac.unib.fafiquedriving.QuestionAdapter;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final TextView textView = binding.textHome;
        Button btn_mulai = binding.btnMulai;
        btn_mulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch_fragment();
                btn_mulai.setVisibility(View.INVISIBLE);
            }
        });
        return root;
    }

    public void switch_fragment(){
        // Mendapatkan instance dari FragmentManager
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

// Membuat transaksi fragment
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

// Membuat instance dari fragment yang akan ditampilkan
        Fragment fragment = new transisi_fragment(); // Gantilah TargetFragment dengan fragment yang ingin Anda tampilkan

// Mengganti fragment yang saat ini ditampilkan dengan fragment yang baru
        fragmentTransaction.replace(R.id.fragment_container, fragment);

// Menambahkan transaksi ke back stack (jika diperlukan, agar pengguna dapat kembali)
        fragmentTransaction.addToBackStack(null);
// Melakukan commit transaksi
        fragmentTransaction.commit();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}