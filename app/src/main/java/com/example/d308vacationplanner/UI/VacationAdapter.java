package com.example.d308vacationplanner.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.d308vacationplanner.R;
import com.example.d308vacationplanner.entities.Vacation;

import java.util.List;

public class VacationAdapter extends RecyclerView.Adapter<VacationAdapter.VacationViewHolder> {

    private List<Vacation> mVacations;
    private final Context context;
    private final LayoutInflater mInflater;

    public VacationAdapter(Context context) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
    }

    // Sets up the RecyclerView list
    public class VacationViewHolder extends RecyclerView.ViewHolder {
        private final TextView vacationItemView;

        public VacationViewHolder(@NonNull View itemView) {
            super(itemView);
            vacationItemView = itemView.findViewById(R.id.textView2); // Vacation list item
            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION && mVacations != null) {
                    Vacation current = mVacations.get(position);
                    Intent intent = new Intent(context, VacationDetails.class);
                    intent.putExtra("id", current.getVacationId());
                    intent.putExtra("title", current.getVacationTitle());
                    intent.putExtra("hotel", current.getVacationHotel());
                    intent.putExtra("startdate", current.getStartDate());
                    intent.putExtra("enddate", current.getEndDate());
                    context.startActivity(intent);
                }
            });
        }
    }

    @NonNull
    @Override
    public VacationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.vacation_list_item, parent, false);
        return new VacationViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VacationViewHolder holder, int position) {
        if (mVacations != null && position < mVacations.size()) {
            Vacation current = mVacations.get(position);
            holder.vacationItemView.setText(current.getVacationTitle());
        } else {
            holder.vacationItemView.setText("No vacation title");
        }
    }

    @Override
    public int getItemCount() {
        return (mVacations != null) ? mVacations.size() : 0;
    }

    public void setVacations(List<Vacation> vacations) {
        this.mVacations = vacations;
        notifyDataSetChanged();
    }
}

