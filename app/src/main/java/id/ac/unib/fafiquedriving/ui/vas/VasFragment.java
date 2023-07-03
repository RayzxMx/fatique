package id.ac.unib.fafiquedriving.ui.vas;

import androidx.lifecycle.ViewModelProvider;

import android.content.SharedPreferences;
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

        binding = FragmentVasBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textVas;
        vasViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}