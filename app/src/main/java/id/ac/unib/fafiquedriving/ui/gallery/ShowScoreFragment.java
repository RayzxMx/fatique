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

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

import id.ac.unib.fafiquedriving.R;
import id.ac.unib.fafiquedriving.databinding.FragmentFatigueBinding;
import id.ac.unib.fafiquedriving.databinding.FragmentShowScoreBinding;
import id.ac.unib.fafiquedriving.login;


public class ShowScoreFragment extends Fragment {

    private FragmentShowScoreBinding binding;
    private SharedPreferences sharedPreferences;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("data_percepatan");
    public static ShowScoreFragment newInstance() {
        return new ShowScoreFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentShowScoreBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sharedPreferences = getActivity().getSharedPreferences("scoreData", Context.MODE_PRIVATE);
        boolean AG = false;
        boolean AL = false;
        boolean L = false;

        String AGs = sharedPreferences.getString("AG", "0.0"); // Mendapatkan string
        double AGd = Double.parseDouble(AGs); // Mengonversi string ke double
        String ALs = sharedPreferences.getString("AL", "0.0"); // Mendapatkan string
        double ALd = Double.parseDouble(ALs); // Mengonversi string ke double
        String Ls = sharedPreferences.getString("L", "0.0"); // Mendapatkan string
        double Ld = Double.parseDouble(Ls); // Mengonversi string ke double
        sharedPreferences = getActivity().getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE);
        int pengemudiId = sharedPreferences.getInt("pengemudi_id",0);

        TextView tAG = binding.AG;
        TextView tAL = binding.AL;
        TextView tL = binding.L;
        TextView recomText = binding.textRecomendasi;

        tAG.setText(AGs);
        tAL.setText(ALs);
        tL.setText(Ls);
        //forward chaining
        AG = AGd >= 0.55;
        AL = ALd >= 0.55;
        L = Ld >= 0.55;

        if(AG && AL && L){
            recomText.setText(R.string.AG_AL_L);
        } else if (AG && AL) {
            recomText.setText(R.string.AG_AL);
        } else if (AG && L) {
            recomText.setText(R.string.AG_L);
        } else if (AL && L) {
            recomText.setText(R.string.AL_L);
        } else if (AG) {
            recomText.setText(R.string.AG);
        } else if (AL) {
            recomText.setText(R.string.AL);
        } else if (L) {
            recomText.setText(R.string.L);
        }
        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void save_data(int pengemudiId, double AGd, double ALd, double Ld){
        Boolean AG = AGd >= 0.55;
        Boolean AL = ALd >= 0.55;
        Boolean L = Ld >= 0.55;

    }
}