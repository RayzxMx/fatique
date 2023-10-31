package id.ac.unib.fafiquedriving;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class QuestionAdapter_new extends RecyclerView.Adapter<QuestionAdapter_new.QuestionViewHolder> {

    private List<String> questions;

    public QuestionAdapter_new(List<String> questions) {
        this.questions = questions;
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question, parent, false);
        return new QuestionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        String question = questions.get(position);
        holder.questionText.setText(question);

        // Set listener for RadioButton selection
        //holder.radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            //int selectedValue = getSelectedValue(checkedId);
            // Do something with the selectedValue (e.g., store in a list)
        //});
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    static class QuestionViewHolder extends RecyclerView.ViewHolder {
        TextView questionText;
        //RadioGroup radioGroup;

        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);
            questionText = itemView.findViewById(R.id.questionText);
            //radioGroup = itemView.findViewById(R.id.radioGroup);
        }
    }


}
