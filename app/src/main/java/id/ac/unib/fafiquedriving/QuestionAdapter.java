package id.ac.unib.fafiquedriving;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import android.util.Log;


public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder> {

    private List<String> questions;

    public QuestionAdapter(List<String> questions) {
        this.questions = questions;
    }
    int idCounter = 0;
    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question, parent, false);
        itemView.setId(idCounter++); // Gunakan counter atau posisi sebagai ID
        return new QuestionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        String question = questions.get(holder.getBindingAdapterPosition());
        int max = getItemCount();
        holder.questionText.setText(question);
        if (holder.getBindingAdapterPosition() == max-1){
            holder.btn_submit.setVisibility(View.VISIBLE);
            holder.btn_submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("Mine",holder.getBindingAdapterPosition()+" posisi");
                    btn_click(holder);
                }
            });
        } else {
            holder.btn_submit.setVisibility(View.GONE);
            holder.score.setVisibility(View.GONE);
            holder.score2.setVisibility(View.GONE);
            holder.score3.setVisibility(View.GONE);
        }
        // Set radio button values for the options
        holder.opsi.setTag(holder.getBindingAdapterPosition());
        holder.opsi1.setTag(holder.getBindingAdapterPosition());
        holder.opsi2.setTag(holder.getBindingAdapterPosition());
        holder.opsi3.setTag(holder.getBindingAdapterPosition());
        holder.opsi4.setTag(holder.getBindingAdapterPosition());
        holder.opsi5.setTag(holder.getBindingAdapterPosition());
        holder.opsi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!view.isSelected()){
                    holder.opsi1.setChecked(true);
                    holder.opsi2.setChecked(false);
                    holder.opsi3.setChecked(false);
                    holder.opsi4.setChecked(false);
                    holder.opsi5.setChecked(false);
                }
            }
        });
        holder.opsi.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                Log.d("Mine",holder.getBindingAdapterPosition()+" posisi");
                Log.d("Id_saya","Id : " + holder.opsi.getId());
                Log.d("Id_saya","Id1 : " + holder.opsi1.getId());
                Log.d("Id_saya","Id2 : " + holder.opsi2.getId());
                Log.d("Id_saya","Id3 : " + holder.opsi3.getId());
                Log.d("Id_saya","Id4 : " + holder.opsi4.getId());

            }
        });
        //holder.score2.setText("posisi" + position);
    }

    private Double getSelectedValue(QuestionViewHolder holder, int position) {
        RadioButton selectedOption = null;
        Double selectedValue = 0.0;

        int checkedId = holder.opsi.getCheckedRadioButtonId();
        if (checkedId == R.id.radio_option1) {
            //selectedOption = holder.opsi1;
            selectedValue = 0.0;
        } else if (checkedId == R.id.radio_option2) {
            //selectedOption = holder.opsi2;
            selectedValue = 0.25;
        } else if (checkedId == R.id.radio_option3) {
            //selectedOption = holder.opsi3;
            selectedValue = 0.5;
        } else if (checkedId == R.id.radio_option4) {
            //selectedOption = holder.opsi4;
            selectedValue = 0.75;
        } else if (checkedId == R.id.radio_option5) {
            //selectedOption = holder.opsi5;
            selectedValue = 1.0;
        }

        //if (selectedOption != null) {
        //    int tag = (int) selectedOption.getTag();
        //    if (tag == position) {
        //        selectedValue = Integer.parseInt(selectedOption.getText().toString());
        //    }
        //}

        return selectedValue;
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }
    private void btn_click(QuestionViewHolder holder){
        List<Double> _bobotList = new ArrayList<>();
        List<Double> _bobotList2 = new ArrayList<>();
        List<Double> _bobotList3 = new ArrayList<>();
        final Double[] totalScore = {0.0};
        final Double[] totalScore2 = {0.0};
        final Double[] totalScore3 = {0.0};
        // Mendapatkan referensi ke tabel Firebase
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("basis_pengetahuan")
                .orderByChild("parameter")
                .equalTo("F1")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                Double bobot = snapshot.child("bobot").getValue(Double.class);
                                _bobotList.add(bobot);

                            }
                            for (int i = 0; i < _bobotList.size(); i++) {
                                Double selectedValue = getSelectedValue(holder, i);
                                Double Hasil = selectedValue * _bobotList.get(i);
                                totalScore[0] += Hasil;
                            }
                            DecimalFormat df = new DecimalFormat("#.##");
                            String hasilBulat = df.format(totalScore[0]);
                            holder.score.setVisibility(View.VISIBLE);
                            holder.score.setText("Score 1 "+ hasilBulat);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        // Penanganan kesalahan jika terjadi
                    }
                });
        DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference();
        reference2.child("basis_pengetahuan")
                .orderByChild("parameter")
                .equalTo("F2")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                Double bobot = snapshot.child("bobot").getValue(Double.class);
                                _bobotList2.add(bobot);

                            }
                            for (int i = 0; i < _bobotList2.size(); i++) {
                                Double selectedValue = getSelectedValue(holder, i);
                                Double Hasil = selectedValue * _bobotList2.get(i);
                                totalScore2[0] += Hasil;
                            }
                            DecimalFormat df = new DecimalFormat("#.##");
                            String hasilBulat = df.format(totalScore2[0]);
                            holder.score2.setVisibility(View.VISIBLE);
                            holder.score2.setText("Score 2 "+ hasilBulat);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        // Penanganan kesalahan jika terjadi
                    }
                });
        DatabaseReference reference3 = FirebaseDatabase.getInstance().getReference();
        reference3.child("basis_pengetahuan")
                .orderByChild("parameter")
                .equalTo("F3")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                Double bobot = snapshot.child("bobot").getValue(Double.class);
                                _bobotList3.add(bobot);

                            }
                            for (int i = 0; i < _bobotList3.size(); i++) {
                                Double selectedValue = getSelectedValue(holder, i);
                                Double Hasil = selectedValue * _bobotList3.get(i);

                                totalScore3[0] += Hasil;
                            }
                            DecimalFormat df = new DecimalFormat("#.##");
                            String hasilBulat = df.format(totalScore3[0]);
                            holder.score3.setVisibility(View.VISIBLE);
                            holder.score3.setText("Score 3 "+ hasilBulat);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        // Penanganan kesalahan jika terjadi
                    }
                });
        // Hitung total nilai dari semua opsi yang terpilih
        //int totalScore = 0;
        //for (int i = 0; i < max - 1; i++) {
        //    int selectedValue = getSelectedValue(holder, i);
        //    totalScore += selectedValue;
        //}
        //holder.score.setVisibility(View.VISIBLE);
        //holder.score.setText("Score "+totalScore);
        // Lakukan sesuatu dengan totalScore, misalnya tampilkan di Toast
        // atau simpan ke dalam database atau Firebase

    }
    static class QuestionViewHolder extends RecyclerView.ViewHolder {
        TextView questionText, score, score2, score3;
        RadioButton opsi1;
        RadioButton opsi2;
        RadioButton opsi3;
        RadioButton opsi4;
        RadioButton opsi5;
        final RadioGroup opsi;
        Button btn_submit;
        public QuestionViewHolder(@NonNull View itemView) {

            super(itemView);
            questionText = itemView.findViewById(R.id.questionText);
            score = itemView.findViewById(R.id.score_parameter);
            score2 = itemView.findViewById(R.id.score_parameter2);
            score3 = itemView.findViewById(R.id.score_parameter3);
            opsi = (RadioGroup) itemView.findViewById(R.id.radioGroup);
            opsi1 = itemView.findViewById(R.id.radio_option1);
            opsi2 = itemView.findViewById(R.id.radio_option2);
            opsi3 = itemView.findViewById(R.id.radio_option3);
            opsi4 = itemView.findViewById(R.id.radio_option4);
            opsi5 = itemView.findViewById(R.id.radio_option5);
            btn_submit = itemView.findViewById(R.id.btn_submit);
            //answerButton = itemView.findViewById(R.id.answerButton);
        }
    }
}
