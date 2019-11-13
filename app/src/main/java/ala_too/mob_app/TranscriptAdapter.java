package ala_too.mob_app;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ala_too.mob_app.model.Mark;

public class TranscriptAdapter extends RecyclerView.Adapter<TranscriptAdapter.ViewHolder> {

    private List<Mark> marks = new ArrayList<>();

    public void setItems(Collection<Mark> marks) {
        this.marks.addAll(marks);
        notifyDataSetChanged();
    }

    public void clearItems() {
        marks.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapter_view_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bind(marks.get(i));
    }

    @Override
    public int getItemCount() {
        return marks.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView subject;
        private TextView midterm;
        private TextView final_;

        ViewHolder(View itemView) {
            super(itemView);
            subject = itemView.findViewById(R.id.subjectName);
            midterm = itemView.findViewById(R.id.midtermPoint);
            final_ = itemView.findViewById(R.id.finalPoint);
        }

        void bind(Mark mark) {
            subject.setText(mark.getSubject());
            midterm.setText("Midterm " + mark.getMidterm());
            final_.setText("Final " + mark.getFinal());
        }
    }
}