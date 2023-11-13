package id.ac.unib.fafiquedriving.ui.gallery;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.DecimalFormat;

import id.ac.unib.fafiquedriving.R;
import id.ac.unib.fafiquedriving.databinding.FragmentAltruismeBinding;
import id.ac.unib.fafiquedriving.databinding.FragmentFatigueBinding;

public class FatigueFragment extends Fragment {

    private FragmentFatigueBinding binding;
    private SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public static FatigueFragment newInstance() {
        return new FatigueFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        binding = FragmentFatigueBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        double[] score = new double[11];
        sharedPreferences = getActivity().getSharedPreferences("scoreData", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        RadioGroup pertanyaan38 = binding.radioGroup38;
        RadioGroup pertanyaan39 = binding.radioGroup39;
        RadioGroup pertanyaan40 = binding.radioGroup40;
        RadioGroup pertanyaan41 = binding.radioGroup41;
        RadioGroup pertanyaan42 = binding.radioGroup42;
        RadioGroup pertanyaan43 = binding.radioGroup43;
        RadioGroup pertanyaan44 = binding.radioGroup44;
        RadioGroup pertanyaan45 = binding.radioGroup45;
        RadioGroup pertanyaan46 = binding.radioGroup46;
        RadioGroup pertanyaan47 = binding.radioGroup47;

        Button submit = binding.btnSubmit;
        TextView s = binding.scoreParameter;
        Resources resources = getResources();

        submit.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                score[0] = calScore(pertanyaan38.getCheckedRadioButtonId(), R.id.radio_option1_38, R.id.radio_option2_38, R.id.radio_option3_38, R.id.radio_option4_38,R.id.radio_option5_38);
                score[1] = calScore(pertanyaan39.getCheckedRadioButtonId(), R.id.radio_option1_39, R.id.radio_option2_39, R.id.radio_option3_39, R.id.radio_option4_39,R.id.radio_option5_39);
                score[2] = calScore(pertanyaan40.getCheckedRadioButtonId(), R.id.radio_option1_40, R.id.radio_option2_40, R.id.radio_option3_40, R.id.radio_option4_40,R.id.radio_option5_40);
                score[3] = calScore(pertanyaan41.getCheckedRadioButtonId(), R.id.radio_option1_41, R.id.radio_option2_41, R.id.radio_option3_41, R.id.radio_option4_41,R.id.radio_option5_41);
                score[4] = calScore(pertanyaan42.getCheckedRadioButtonId(), R.id.radio_option1_42, R.id.radio_option2_42, R.id.radio_option3_42, R.id.radio_option4_42,R.id.radio_option5_42);
                score[5] = calScore(pertanyaan43.getCheckedRadioButtonId(), R.id.radio_option1_43, R.id.radio_option2_43, R.id.radio_option3_43, R.id.radio_option4_43,R.id.radio_option5_43);
                score[6] = calScore(pertanyaan44.getCheckedRadioButtonId(), R.id.radio_option1_44, R.id.radio_option2_44, R.id.radio_option3_44, R.id.radio_option4_44,R.id.radio_option5_44);
                score[7] = calScore(pertanyaan45.getCheckedRadioButtonId(), R.id.radio_option1_45, R.id.radio_option2_45, R.id.radio_option3_45, R.id.radio_option4_45,R.id.radio_option5_45);
                score[8] = calScore(pertanyaan46.getCheckedRadioButtonId(), R.id.radio_option1_46, R.id.radio_option2_46, R.id.radio_option3_46, R.id.radio_option4_46,R.id.radio_option5_46);
                score[9] = calScore(pertanyaan47.getCheckedRadioButtonId(), R.id.radio_option1_47, R.id.radio_option2_47, R.id.radio_option3_47, R.id.radio_option4_47,R.id.radio_option5_47);
                double s1 = 0.0;
                TypedArray typedArray = resources.obtainTypedArray(R.array.bobot);
                Double[] bobot = new Double[11];
                for (int i = 0; i < 10; i++) {
                    bobot[i] = (double) typedArray.getFloat(i+37, 0.00f); // Ambil nilai dan konversi ke double
                }

                typedArray.recycle(); // Penting untuk mendaur ulang TypedArray setelah digunakan
                for (int i = 0; i<10;i++){
                    s1 += (score[i] * bobot[i]);
                }
                DecimalFormat decimalFormat = new DecimalFormat("#.#####");
                String roundedValue = decimalFormat.format(s1);
                s.setText("Score :"+ roundedValue);
                editor.putString("L", String.valueOf(roundedValue)); // Mengonversi double menjadi string
                editor.apply();
                switch_fragment();
            }
        });
        return root;
    }
    public double calScore(int cID, int rID1, int rID2, int rID3, int rID4, int rID5){
        double score = 0.0;
        if (cID == rID1){
            score = 0.0;
        } else if (cID == rID2) {
            score = 0.25;
        } else if (cID == rID3) {
            score = 0.5;
        } else if (cID == rID4) {
            score = 0.75;
        } else if (cID == rID5) {
            score = 1.0;
        }
        return score;
    }
    public void switch_fragment(){
        // Mendapatkan instance dari FragmentManager
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        // Membuat transaksi fragment
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Membuat instance dari fragment yang akan ditampilkan
        Fragment fragment = new ShowScoreFragment(); // Gantilah TargetFragment dengan fragment yang ingin Anda tampilkan

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