package id.ac.unib.fafiquedriving.ui.gallery;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.DecimalFormat;

import id.ac.unib.fafiquedriving.R;
import id.ac.unib.fafiquedriving.databinding.FragmentFatigueBinding;
import id.ac.unib.fafiquedriving.databinding.FragmentShowScoreBinding;


public class ShowScoreFragment extends Fragment {

    private FragmentShowScoreBinding binding;
    private SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public static ShowScoreFragment newInstance() {
        return new ShowScoreFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        binding = FragmentShowScoreBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sharedPreferences = getActivity().getSharedPreferences("scoreData", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        String AGs = sharedPreferences.getString("AG", "0.0"); // Mendapatkan string
        double AG = Double.parseDouble(AGs); // Mengonversi string ke double
        String ALs = sharedPreferences.getString("AL", "0.0"); // Mendapatkan string
        double AL = Double.parseDouble(ALs); // Mengonversi string ke double
        String Ls = sharedPreferences.getString("L", "0.0"); // Mendapatkan string
        double L = Double.parseDouble(Ls); // Mengonversi string ke double
        TextView scoreTot = binding.showScore;
        scoreTot.setText("Score AG : "+AG+" \nScore AL : "+AL+" \nScore L : "+L);

        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}