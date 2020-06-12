package com.example.tanush.sih_20;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SymptomsAdapter extends RecyclerView.Adapter<SymptomsAdapter.SymViewHolder> {
    private ArrayList<SymptomsRecyclerItem> mSymList;
    private onItemClickListener1 mSymListener;

    public interface onItemClickListener1 {
        void onItemClick(int position);

    }

    public void setOnItemClickListener(onItemClickListener1 listener) {
        mSymListener = listener;

    }


    public SymptomsAdapter(ArrayList<SymptomsRecyclerItem> symList) {
        mSymList = symList;
    }

    @NonNull
    @Override
    public SymViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        SymViewHolder symV = new SymViewHolder(v, mSymListener);
        return symV;
    }

    @Override
    public void onBindViewHolder(@NonNull SymViewHolder holder, int position) {
        SymptomsRecyclerItem currentSymItem = mSymList.get(position);
        holder.symText.setText(currentSymItem.getSymptom());
        holder.head.setText(currentSymItem.getHead());
    }

    @Override
    public int getItemCount() {
        return mSymList.size();
    }


    public static class SymViewHolder extends RecyclerView.ViewHolder {
        public TextView symText;
        public TextView head;

        public SymViewHolder(@NonNull View itemView, final onItemClickListener1 listener1) {
            super(itemView);
            symText = itemView.findViewById(R.id.recyclerTextView);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener1 != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener1.onItemClick(position);
                        }
                    }
                }
            });
        }

    }


}
