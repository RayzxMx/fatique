package id.ac.unib.fafiquedriving.ui.daftarPengemudi;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.ac.unib.fafiquedriving.R;

public class DaftarPengemudiFragment extends Fragment {

    private DaftarPengemudiViewModel mViewModel;

    public static DaftarPengemudiFragment newInstance() {
        return new DaftarPengemudiFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_daftar_pengemudi, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DaftarPengemudiViewModel.class);
        // TODO: Use the ViewModel
    }

}