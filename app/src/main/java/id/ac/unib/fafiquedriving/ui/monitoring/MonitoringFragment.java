package id.ac.unib.fafiquedriving.ui.monitoring;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import id.ac.unib.fafiquedriving.R;
import id.ac.unib.fafiquedriving.databinding.FragmentMonitoringBinding;
import id.ac.unib.fafiquedriving.databinding.FragmentShowScoreBinding;
import id.ac.unib.fafiquedriving.ui.gallery.GalleryViewModel;
import id.ac.unib.fafiquedriving.ui.gallery.ShowScoreFragment;
import android.graphics.Color;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.firebase.database.ValueEventListener;

public class MonitoringFragment extends Fragment {

    private FragmentMonitoringBinding binding;
    private SharedPreferences sharedPreferences;

    public static ShowScoreFragment newInstance() {
        return new ShowScoreFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMonitoringBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Button btnShow = binding.datePickerButton;
        //try {
        //    showData();

        //} catch (ParseException e) {
        //    throw new RuntimeException(e);
        //}
        //showDataFirebase();
        coba();
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });
        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    // Fungsi untuk menampilkan DatePickerDialog
    public void showDatePickerDialog() {
        // Mendapatkan tanggal saat ini
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        EditText eTanggal = binding.tanggalShow;
        // Membuat DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                requireActivity(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Menampilkan tanggal yang dipilih
                        String selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                        Toast.makeText(requireContext(), "Tanggal dipilih: " + selectedDate, Toast.LENGTH_SHORT).show();
                        eTanggal.setText(selectedDate);
                    }
                },
                year, month, dayOfMonth);

        // Menampilkan dialog
        datePickerDialog.show();
    }
    public void coba(){
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference().child("data_percepatan");
        LineChart lineChart = binding.lineChart;
        ArrayList<Integer> waktuList = new ArrayList<>();
        ArrayList<Float> percepatanList = new ArrayList<>();
        ArrayList<Entry> entries = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        databaseRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                String waktu = dataSnapshot.child("waktu").getValue(String.class);
                Log.d("waktu", waktu);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                // Implementasi ketika child dihapus
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

            // ... (method lain dari ChildEventListener)
        });

        for (int i = 0; i < waktuList.size(); i++) {
            Log.d("waktu", waktuList.get(i).toString());
            Log.d("percepatan", percepatanList.get(i).toString());

            entries.add(new Entry(waktuList.get(i), percepatanList.get(i)));
        }

        LineDataSet dataSet = new LineDataSet(entries, "Percepatan Pengemudi");
        dataSet.setColor(Color.BLUE);
        dataSet.setValueTextColor(Color.RED);

        LineData lineData = new LineData(dataSet);
        lineChart.setData(lineData);

        Legend legend = lineChart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP); // Menentukan alignment vertikal
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT); // Menentukan alignment horizontal
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL); // Menentukan orientasi
        legend.setDrawInside(false); // Apakah legenda akan digambar di dalam area grafik

        Description description = new Description();
        description.setText("");
        lineChart.setDescription(description);

        // Menambahkan label pada sumbu x dan sumbu y
        XAxis xAxis = lineChart.getXAxis();
        YAxis yAxis = lineChart.getAxisLeft(); // Menggunakan sumbu kiri
        YAxis yAxis1 = lineChart.getAxisRight();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); // Atur posisi label sumbu x
        //xAxis.setAxisMinimum(0f); // Nilai minimum sumbu x
        //xAxis.setAxisMaximum(10f); // Nilai maksimum sumbu x
        //xAxis.setCenterAxisLabels(true);

        //yAxis.setAxisMinimum(0f); // Nilai minimum sumbu y
        //yAxis.setAxisMaximum(10f); // Nilai maksimum sumbu y
        //yAxis.setCenterAxisLabels(true);
        yAxis1.setEnabled(false);


        xAxis.setDrawGridLines(true); // Nonaktifkan garis grid sumbu x jika tidak diperlukan
        yAxis.setDrawGridLines(false); // Nonaktifkan garis grid sumbu y jika tidak diperlukan
        yAxis1.setDrawGridLines(false);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                // Ubah nilai waktu dari milidetik ke format waktu yang mudah dibaca (jam:menit:detik)
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss"); // Format waktu yang diinginkan
                Date date = new Date(Math.round(value));

                // Format ulang nilai waktu ke dalam string sesuai dengan format yang Anda tentukan sebelumnya
                String formattedTime = dateFormat.format(date);
                return formattedTime; // Tampilkan nilai sebagai bilangan bulat
            }
        });

        // Mengatur interval minimum dan maksimum sumbu X (sesuaikan dengan data Anda)
        //long minMil = 8 * 3600000L; // 8 jam dalam milidetik
        //long maxMil = 10 * 3600000L; // 8 jam dalam milidetik
        //xAxis.setAxisMinimum(minMil); // Nilai minimum sumbu X (waktu mulai)
        //xAxis.setAxisMaximum(maxMil); // Nilai maksimum sumbu X (waktu akhir)
        lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                float xValue = e.getX(); // Mendapatkan nilai sumbu X dari entry yang di-tap
                // Lakukan apa pun yang Anda inginkan dengan nilai x, seperti menampilkan dalam info window atau dialog
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss"); // Format waktu yang diinginkan
                Date date = new Date(Math.round(xValue));

                // Format ulang nilai waktu ke dalam string sesuai dengan format yang Anda tentukan sebelumnya
                String formattedTime = dateFormat.format(date);
                Toast.makeText(requireContext(), "Nilai sumbu X: " + formattedTime, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {
                // Ketika tidak ada yang di-tap
            }
        });
        lineChart.invalidate();
    }
    public void showDataFirebase(){
        // Referensi database Firebase
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference().child("data_percepatan").child("random_code");

        // Mendapatkan data dari Firebase
        databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                LineChart lineChart = binding.lineChart;
                ArrayList<Integer> waktuList = new ArrayList<>();
                ArrayList<Float> percepatanList = new ArrayList<>();
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Log.d("status_firebase", "Ada");
                    // Mendapatkan nilai waktu dan percepatan dari Firebase
                    String waktu = snapshot.child("waktu").getValue(String.class);
                    float percepatan = snapshot.child("percepatan_x").getValue(Float.class);

                    try {
                        Date date = dateFormat.parse(waktu);
                        long timeInMillis = date.getTime();
                        waktuList.add(Math.round(timeInMillis));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    percepatanList.add(percepatan);
                }

                // Setelah mendapatkan data, Anda dapat menggunakan ArrayList tersebut untuk memplot grafik
                // Misalnya menggunakan MPAndroidChart, Anda dapat membuat Entries dari ArrayList tersebut
                ArrayList<Entry> entries = new ArrayList<>();
                for (int i = 0; i < waktuList.size(); i++) {
                    Log.d("waktu", waktuList.get(i).toString());
                    Log.d("percepatan", percepatanList.get(i).toString());

                    entries.add(new Entry(waktuList.get(i), percepatanList.get(i)));
                }

                LineDataSet dataSet = new LineDataSet(entries, "Percepatan Pengemudi");
                dataSet.setColor(Color.BLUE);
                dataSet.setValueTextColor(Color.RED);

                LineData lineData = new LineData(dataSet);
                lineChart.setData(lineData);

                Legend legend = lineChart.getLegend();
                legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP); // Menentukan alignment vertikal
                legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT); // Menentukan alignment horizontal
                legend.setOrientation(Legend.LegendOrientation.HORIZONTAL); // Menentukan orientasi
                legend.setDrawInside(false); // Apakah legenda akan digambar di dalam area grafik

                Description description = new Description();
                description.setText("");
                lineChart.setDescription(description);

                // Menambahkan label pada sumbu x dan sumbu y
                XAxis xAxis = lineChart.getXAxis();
                YAxis yAxis = lineChart.getAxisLeft(); // Menggunakan sumbu kiri
                YAxis yAxis1 = lineChart.getAxisRight();
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); // Atur posisi label sumbu x
                //xAxis.setAxisMinimum(0f); // Nilai minimum sumbu x
                //xAxis.setAxisMaximum(10f); // Nilai maksimum sumbu x
                //xAxis.setCenterAxisLabels(true);

                //yAxis.setAxisMinimum(0f); // Nilai minimum sumbu y
                //yAxis.setAxisMaximum(10f); // Nilai maksimum sumbu y
                //yAxis.setCenterAxisLabels(true);
                yAxis1.setEnabled(false);


                xAxis.setDrawGridLines(true); // Nonaktifkan garis grid sumbu x jika tidak diperlukan
                yAxis.setDrawGridLines(false); // Nonaktifkan garis grid sumbu y jika tidak diperlukan
                yAxis1.setDrawGridLines(false);
                xAxis.setValueFormatter(new ValueFormatter() {
                    @Override
                    public String getFormattedValue(float value) {
                        // Ubah nilai waktu dari milidetik ke format waktu yang mudah dibaca (jam:menit:detik)
                        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss"); // Format waktu yang diinginkan
                        Date date = new Date(Math.round(value));

                        // Format ulang nilai waktu ke dalam string sesuai dengan format yang Anda tentukan sebelumnya
                        String formattedTime = dateFormat.format(date);
                        return formattedTime; // Tampilkan nilai sebagai bilangan bulat
                    }
                });

                // Mengatur interval minimum dan maksimum sumbu X (sesuaikan dengan data Anda)
                //long minMil = 8 * 3600000L; // 8 jam dalam milidetik
                //long maxMil = 10 * 3600000L; // 8 jam dalam milidetik
                //xAxis.setAxisMinimum(minMil); // Nilai minimum sumbu X (waktu mulai)
                //xAxis.setAxisMaximum(maxMil); // Nilai maksimum sumbu X (waktu akhir)
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
                    @Override
                    public void onValueSelected(Entry e, Highlight h) {
                        float xValue = e.getX(); // Mendapatkan nilai sumbu X dari entry yang di-tap
                        // Lakukan apa pun yang Anda inginkan dengan nilai x, seperti menampilkan dalam info window atau dialog
                        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss"); // Format waktu yang diinginkan
                        Date date = new Date(Math.round(xValue));

                        // Format ulang nilai waktu ke dalam string sesuai dengan format yang Anda tentukan sebelumnya
                        String formattedTime = dateFormat.format(date);
                        Toast.makeText(requireContext(), "Nilai sumbu X: " + formattedTime, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected() {
                        // Ketika tidak ada yang di-tap
                    }
                });
                lineChart.invalidate();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle jika terjadi error
            }
        });

    }
    public void showData() throws ParseException {
        LineChart lineChart = binding.lineChart;
        // Misalkan Anda memiliki 5 waktu dalam format tanggal
        String[] waktuStrings = {
                "18:00:00",
                "18:30:00",
                "19:00:00",
                "19:30:00",
                "20:00:00"
        };
        List<Entry> entries = new ArrayList<>();

// Buat list untuk menyimpan nilai waktu dalam bentuk milidetik
        List<Long> waktuMilis = new ArrayList<>();

// Ubah setiap waktu menjadi nilai waktu dalam bentuk milidetik
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

        for (String waktuString : waktuStrings) {
            try {
                Date date = dateFormat.parse(waktuString);
                long timeInMillis = date.getTime();
                waktuMilis.add(timeInMillis);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

// Tampilkan nilai waktu dalam bentuk milidetik
        int i = 0;
        for (Long timeInMillis : waktuMilis) {
            entries.add(new Entry(timeInMillis, i));
            i += 2;
        }// Contoh konversi tanggal ke Unix Timestamp

        //Nilai x adalah nilai waktu
        //Nilai y adalah nilai percepatan



        LineDataSet dataSet = new LineDataSet(entries, "Percepatan Pengemudi");
        dataSet.setColor(Color.BLUE);
        dataSet.setValueTextColor(Color.RED);

        LineData lineData = new LineData(dataSet);
        lineChart.setData(lineData);

        Legend legend = lineChart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP); // Menentukan alignment vertikal
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT); // Menentukan alignment horizontal
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL); // Menentukan orientasi
        legend.setDrawInside(false); // Apakah legenda akan digambar di dalam area grafik

        Description description = new Description();
        description.setText("");
        lineChart.setDescription(description);

        // Menambahkan label pada sumbu x dan sumbu y
        XAxis xAxis = lineChart.getXAxis();
        YAxis yAxis = lineChart.getAxisLeft(); // Menggunakan sumbu kiri
        YAxis yAxis1 = lineChart.getAxisRight();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); // Atur posisi label sumbu x
        //xAxis.setAxisMinimum(0f); // Nilai minimum sumbu x
        //xAxis.setAxisMaximum(10f); // Nilai maksimum sumbu x
        //xAxis.setCenterAxisLabels(true);

        //yAxis.setAxisMinimum(0f); // Nilai minimum sumbu y
        //yAxis.setAxisMaximum(10f); // Nilai maksimum sumbu y
        //yAxis.setCenterAxisLabels(true);
        yAxis1.setEnabled(false);


        xAxis.setDrawGridLines(true); // Nonaktifkan garis grid sumbu x jika tidak diperlukan
        yAxis.setDrawGridLines(false); // Nonaktifkan garis grid sumbu y jika tidak diperlukan
        yAxis1.setDrawGridLines(false);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                // Ubah nilai waktu dari milidetik ke format waktu yang mudah dibaca (jam:menit:detik)
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss"); // Format waktu yang diinginkan
                Date date = new Date(Math.round(value));

                // Format ulang nilai waktu ke dalam string sesuai dengan format yang Anda tentukan sebelumnya
                String formattedTime = dateFormat.format(date);
                return formattedTime; // Tampilkan nilai sebagai bilangan bulat
            }
        });

        // Mengatur interval minimum dan maksimum sumbu X (sesuaikan dengan data Anda)
        //long minMil = 8 * 3600000L; // 8 jam dalam milidetik
        //long maxMil = 10 * 3600000L; // 8 jam dalam milidetik
        //xAxis.setAxisMinimum(minMil); // Nilai minimum sumbu X (waktu mulai)
        //xAxis.setAxisMaximum(maxMil); // Nilai maksimum sumbu X (waktu akhir)
        lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                float xValue = e.getX(); // Mendapatkan nilai sumbu X dari entry yang di-tap
                // Lakukan apa pun yang Anda inginkan dengan nilai x, seperti menampilkan dalam info window atau dialog
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss"); // Format waktu yang diinginkan
                Date date = new Date(Math.round(xValue));

                // Format ulang nilai waktu ke dalam string sesuai dengan format yang Anda tentukan sebelumnya
                String formattedTime = dateFormat.format(date);
                Toast.makeText(requireContext(), "Nilai sumbu X: " + formattedTime, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {
                // Ketika tidak ada yang di-tap
            }
        });
        lineChart.invalidate();
    }
}