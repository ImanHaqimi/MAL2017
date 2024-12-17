package com.example.bookingrestaurantcw2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private final List<Reservation> historyItems;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Reservation historyItem);
    }

    public HistoryAdapter(@NonNull List<Reservation> historyItems, @NonNull OnItemClickListener listener) {
        this.historyItems = historyItems != null ? historyItems : List.of(); // Avoid null list
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Reservation historyItem = historyItems.get(position);
        if (historyItem != null) {  // Safeguard against null data
            holder.bind(historyItem);
        }
    }

    @Override
    public int getItemCount() {
        return historyItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView customerNameTextView;
        private final TextView seatingAreaTextView;
        private final TextView dateTextView;
        private final TextView mealTimeTextView;
        private final TextView tableSizeTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            customerNameTextView = itemView.findViewById(R.id.customerNameEditText);
            seatingAreaTextView = itemView.findViewById(R.id.areaEditText);
            dateTextView = itemView.findViewById(R.id.dateEditText);
            mealTimeTextView = itemView.findViewById(R.id.mealtimeEditText);
            tableSizeTextView = itemView.findViewById(R.id.tablesizeEditText);

            // Set click listener for the item
            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION && listener != null) {
                    listener.onItemClick(historyItems.get(position));
                }
            });
        }

        public void bind(@NonNull Reservation historyItem) {
            customerNameTextView.setText(String.format("Name: %s", historyItem.getCustomerName()));
            seatingAreaTextView.setText(String.format("Seating Area: %s", historyItem.getSeatingArea()));
            dateTextView.setText(String.format("Date: %s", historyItem.getDate()));
            mealTimeTextView.setText(String.format("Meal Time: %s", historyItem.getMeal()));
            tableSizeTextView.setText(String.format("Table Size: %s", historyItem.getTableSize()));
        }
    }
}
