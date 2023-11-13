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
import id.ac.unib.fafiquedriving.databinding.FragmentGalleryBinding;

public class AltruismeFragment extends Fragment {

    private FragmentAltruismeBinding binding;

    public static AltruismeFragment newInstance() {
        return new AltruismeFragment();
    }
    private SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        binding = FragmentAltruismeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        double[] score = new double[11];
        sharedPreferences = getActivity().getSharedPreferences("scoreData", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        RadioGroup pertanyaan28 = binding.radioGroup28;
        RadioGroup pertanyaan29 = binding.radioGroup29;
        RadioGroup pertanyaan30 = binding.radioGroup30;
        RadioGroup pertanyaan31 = binding.radioGroup31;
        RadioGroup pertanyaan32 = binding.radioGroup32;
        RadioGroup pertanyaan33 = binding.radioGroup33;
        RadioGroup pertanyaan34 = binding.radioGroup34;
        RadioGroup pertanyaan35 = binding.radioGroup35;
        RadioGroup pertanyaan36 = binding.radioGroup36;
        RadioGroup pertanyaan37 = binding.radioGroup37;

        Button submit = binding.btnSubmit;
        TextView s = binding.scoreParameter;
        Resources resources = getResources();

        submit.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                score[0] = calScore(pertanyaan28.getCheckedRadioButtonId(), R.id.radio_option1_28, R.id.radio_option2_28, R.id.radio_option3_28, R.id.radio_option4_28,R.id.radio_option5_28);
                score[1] = calScore(pertanyaan29.getCheckedRadioButtonId(), R.id.radio_option1_29, R.id.radio_option2_29, R.id.radio_option3_29, R.id.radio_option4_29,R.id.radio_option5_29);
                score[2] = calScore(pertanyaan30.getCheckedRadioButtonId(), R.id.radio_option1_30, R.id.radio_option2_30, R.id.radio_option3_30, R.id.radio_option4_30,R.id.radio_option5_30);
                score[3] = calScore(pertanyaan31.getCheckedRadioButtonId(), R.id.radio_option1_31, R.id.radio_option2_31, R.id.radio_option3_31, R.id.radio_option4_31,R.id.radio_option5_31);
                score[4] = calScore(pertanyaan32.getCheckedRadioButtonId(), R.id.radio_option1_32, R.id.radio_option2_32, R.id.radio_option3_32, R.id.radio_option4_32,R.id.radio_option5_32);
                score[5] = calScore(pertanyaan33.getCheckedRadioButtonId(), R.id.radio_option1_33, R.id.radio_option2_33, R.id.radio_option3_33, R.id.radio_option4_33,R.id.radio_option5_33);
                score[6] = calScore(pertanyaan34.getCheckedRadioButtonId(), R.id.radio_option1_34, R.id.radio_option2_34, R.id.radio_option3_34, R.id.radio_option4_34,R.id.radio_option5_34);
                score[7] = calScore(pertanyaan35.getCheckedRadioButtonId(), R.id.radio_option1_35, R.id.radio_option2_35, R.id.radio_option3_35, R.id.radio_option4_35,R.id.radio_option5_35);
                score[8] = calScore(pertanyaan36.getCheckedRadioButtonId(), R.id.radio_option1_36, R.id.radio_option2_36, R.id.radio_option3_36, R.id.radio_option4_36,R.id.radio_option5_36);
                score[9] = calScore(pertanyaan37.getCheckedRadioButtonId(), R.id.radio_option1_37, R.id.radio_option2_37, R.id.radio_option3_37, R.id.radio_option4_37,R.id.radio_option5_37);

                double s1 = 0.0;
                TypedArray typedArray = resources.obtainTypedArray(R.array.bobot);
                Double[] bobot = new Double[11];
                for (int i = 0; i < 10; i++) {
                    bobot[i] = (double) typedArray.getFloat(i+27, 0.0f); // Ambil nilai dan konversi ke double
                }

                typedArray.recycle(); // Penting untuk mendaur ulang TypedArray setelah digunakan
                for (int i = 0; i< 10;i++){
                    s1 += (score[i] * bobot[i]);
                }
                DecimalFormat decimalFormat = new DecimalFormat("#.#####");
                String roundedValue = decimalFormat.format(s1);
                s.setText("Score :"+ roundedValue);
                editor.putString("AL", String.valueOf(roundedValue)); // Mengonversi double menjadi string
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
        Fragment fragment = new FatigueFragment(); // Gantilah TargetFragment dengan fragment yang ingin Anda tampilkan

        // Mengganti fragment yang saat ini ditampilkan dengan fragment yang baru
        fragmentTransaction.replace(R.id.fragment_container, fragment);

        // Menambahkan transaksi ke back stack (jika diperlukan, agar pengguna dapat kembali)
        fragmentTransaction.addToBackStack(null);
        // Melakukan commit transaksi
        fragmentTransaction.commit();

    }


}