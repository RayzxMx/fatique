package id.ac.unib.fafiquedriving.ui.gallery;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

import id.ac.unib.fafiquedriving.R;
import id.ac.unib.fafiquedriving.databinding.FragmentGalleryBinding;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    private SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        double[] score = new double[28];
        sharedPreferences = getActivity().getSharedPreferences("scoreData", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        RadioGroup pertanyaan1 = binding.radioGroup1;
        RadioGroup pertanyaan2 = binding.radioGroup2;
        RadioGroup pertanyaan3 = binding.radioGroup3;
        RadioGroup pertanyaan4 = binding.radioGroup4;
        RadioGroup pertanyaan5 = binding.radioGroup5;
        RadioGroup pertanyaan6 = binding.radioGroup6;
        RadioGroup pertanyaan7 = binding.radioGroup7;
        RadioGroup pertanyaan8 = binding.radioGroup8;
        RadioGroup pertanyaan9 = binding.radioGroup9;
        RadioGroup pertanyaan10 = binding.radioGroup10;
        RadioGroup pertanyaan11 = binding.radioGroup11;
        RadioGroup pertanyaan12 = binding.radioGroup12;
        RadioGroup pertanyaan13 = binding.radioGroup13;
        RadioGroup pertanyaan14 = binding.radioGroup14;
        RadioGroup pertanyaan15 = binding.radioGroup15;
        RadioGroup pertanyaan16 = binding.radioGroup16;
        RadioGroup pertanyaan17 = binding.radioGroup17;
        RadioGroup pertanyaan18 = binding.radioGroup18;
        RadioGroup pertanyaan19 = binding.radioGroup19;
        RadioGroup pertanyaan20 = binding.radioGroup20;
        RadioGroup pertanyaan21 = binding.radioGroup21;
        RadioGroup pertanyaan22 = binding.radioGroup22;
        RadioGroup pertanyaan23 = binding.radioGroup23;
        RadioGroup pertanyaan24 = binding.radioGroup24;
        RadioGroup pertanyaan25 = binding.radioGroup25;
        RadioGroup pertanyaan26 = binding.radioGroup26;
        RadioGroup pertanyaan27 = binding.radioGroup27;

        Button submit = binding.btnSubmit;
        TextView s = binding.scoreParameter;
        Resources resources = getResources();


        submit.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                score[0] = calScore(pertanyaan1.getCheckedRadioButtonId(), R.id.radio_option1_1, R.id.radio_option2_1, R.id.radio_option3_1, R.id.radio_option4_1,R.id.radio_option5_1);
                score[1] = calScore(pertanyaan2.getCheckedRadioButtonId(), R.id.radio_option1_2, R.id.radio_option2_2, R.id.radio_option3_2, R.id.radio_option4_2,R.id.radio_option5_2);
                score[2] = calScore(pertanyaan3.getCheckedRadioButtonId(), R.id.radio_option1_3, R.id.radio_option2_3, R.id.radio_option3_3, R.id.radio_option4_3,R.id.radio_option5_3);
                score[3] = calScore(pertanyaan4.getCheckedRadioButtonId(), R.id.radio_option1_4, R.id.radio_option2_4, R.id.radio_option3_4, R.id.radio_option4_4,R.id.radio_option5_4);
                score[4] = calScore(pertanyaan5.getCheckedRadioButtonId(), R.id.radio_option1_5, R.id.radio_option2_5, R.id.radio_option3_5, R.id.radio_option4_5,R.id.radio_option5_5);
                score[5] = calScore(pertanyaan6.getCheckedRadioButtonId(), R.id.radio_option1_6, R.id.radio_option2_6, R.id.radio_option3_6, R.id.radio_option4_6,R.id.radio_option5_6);
                score[6] = calScore(pertanyaan7.getCheckedRadioButtonId(), R.id.radio_option1_7, R.id.radio_option2_7, R.id.radio_option3_7, R.id.radio_option4_7,R.id.radio_option5_7);
                score[7] = calScore(pertanyaan8.getCheckedRadioButtonId(), R.id.radio_option1_8, R.id.radio_option2_8, R.id.radio_option3_8, R.id.radio_option4_8,R.id.radio_option5_8);
                score[8] = calScore(pertanyaan9.getCheckedRadioButtonId(), R.id.radio_option1_9, R.id.radio_option2_9, R.id.radio_option3_9, R.id.radio_option4_9,R.id.radio_option5_9);
                score[9] = calScore(pertanyaan10.getCheckedRadioButtonId(), R.id.radio_option1_10, R.id.radio_option2_10, R.id.radio_option3_10, R.id.radio_option4_10,R.id.radio_option5_10);
                score[10] = calScore(pertanyaan11.getCheckedRadioButtonId(), R.id.radio_option1_11, R.id.radio_option2_11, R.id.radio_option3_11, R.id.radio_option4_11,R.id.radio_option5_11);
                score[11] = calScore(pertanyaan12.getCheckedRadioButtonId(), R.id.radio_option1_12, R.id.radio_option2_12, R.id.radio_option3_12, R.id.radio_option4_12,R.id.radio_option5_12);
                score[12] = calScore(pertanyaan13.getCheckedRadioButtonId(), R.id.radio_option1_13, R.id.radio_option2_13, R.id.radio_option3_13, R.id.radio_option4_13,R.id.radio_option5_13);
                score[13] = calScore(pertanyaan14.getCheckedRadioButtonId(), R.id.radio_option1_14, R.id.radio_option2_14, R.id.radio_option3_14, R.id.radio_option4_14,R.id.radio_option5_14);
                score[14] = calScore(pertanyaan15.getCheckedRadioButtonId(), R.id.radio_option1_15, R.id.radio_option2_15, R.id.radio_option3_15, R.id.radio_option4_15,R.id.radio_option5_15);
                score[15] = calScore(pertanyaan16.getCheckedRadioButtonId(), R.id.radio_option1_16, R.id.radio_option2_16, R.id.radio_option3_16, R.id.radio_option4_16,R.id.radio_option5_16);
                score[16] = calScore(pertanyaan17.getCheckedRadioButtonId(), R.id.radio_option1_17, R.id.radio_option2_17, R.id.radio_option3_17, R.id.radio_option4_17,R.id.radio_option5_17);
                score[17] = calScore(pertanyaan18.getCheckedRadioButtonId(), R.id.radio_option1_18, R.id.radio_option2_18, R.id.radio_option3_18, R.id.radio_option4_18,R.id.radio_option5_18);
                score[18] = calScore(pertanyaan19.getCheckedRadioButtonId(), R.id.radio_option1_19, R.id.radio_option2_19, R.id.radio_option3_19, R.id.radio_option4_19,R.id.radio_option5_19);
                score[19] = calScore(pertanyaan20.getCheckedRadioButtonId(), R.id.radio_option1_20, R.id.radio_option2_20, R.id.radio_option3_20, R.id.radio_option4_20,R.id.radio_option5_20);
                score[20] = calScore(pertanyaan21.getCheckedRadioButtonId(), R.id.radio_option1_21, R.id.radio_option2_21, R.id.radio_option3_21, R.id.radio_option4_21,R.id.radio_option5_21);
                score[21] = calScore(pertanyaan22.getCheckedRadioButtonId(), R.id.radio_option1_22, R.id.radio_option2_22, R.id.radio_option3_22, R.id.radio_option4_22,R.id.radio_option5_22);
                score[22] = calScore(pertanyaan23.getCheckedRadioButtonId(), R.id.radio_option1_23, R.id.radio_option2_23, R.id.radio_option3_23, R.id.radio_option4_23,R.id.radio_option5_23);
                score[23] = calScore(pertanyaan24.getCheckedRadioButtonId(), R.id.radio_option1_24, R.id.radio_option2_24, R.id.radio_option3_24, R.id.radio_option4_24,R.id.radio_option5_24);
                score[24] = calScore(pertanyaan25.getCheckedRadioButtonId(), R.id.radio_option1_25, R.id.radio_option2_25, R.id.radio_option3_25, R.id.radio_option4_25,R.id.radio_option5_25);
                score[25] = calScore(pertanyaan26.getCheckedRadioButtonId(), R.id.radio_option1_26, R.id.radio_option2_26, R.id.radio_option3_26, R.id.radio_option4_26,R.id.radio_option5_26);
                score[26] = calScore(pertanyaan27.getCheckedRadioButtonId(), R.id.radio_option1_27, R.id.radio_option2_27, R.id.radio_option3_27, R.id.radio_option4_27,R.id.radio_option5_27);

                double s1 = 0.0;
                TypedArray typedArray = resources.obtainTypedArray(R.array.bobot);
                Double[] bobot = new Double[typedArray.length()];
                for (int i = 0; i < 27; i++) {
                    bobot[i] = (double) typedArray.getFloat(i, 0.000000000f); // Ambil nilai dan konversi ke double
                }

                typedArray.recycle(); // Penting untuk mendaur ulang TypedArray setelah digunakan
                for (int i = 0; i<27;i++){
                    s1 += (score[i] * bobot[i]);
                }
                DecimalFormat decimalFormat = new DecimalFormat("#.#####");
                String roundedValue = decimalFormat.format(s1);
                s.setText("Score :"+ roundedValue);
                editor.putString("AG", String.valueOf(roundedValue)); // Mengonversi double menjadi string
                editor.apply();
                switch_fragment();
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
        Fragment fragment = new AltruismeFragment(); // Gantilah TargetFragment dengan fragment yang ingin Anda tampilkan

        // Mengganti fragment yang saat ini ditampilkan dengan fragment yang baru
        fragmentTransaction.replace(R.id.fragment_container, fragment);

        // Menambahkan transaksi ke back stack (jika diperlukan, agar pengguna dapat kembali)
        fragmentTransaction.addToBackStack(null);
        // Melakukan commit transaksi
        fragmentTransaction.commit();

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
}