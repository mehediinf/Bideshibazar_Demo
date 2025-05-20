//package com.mtach.bideshibazar.product;
//
//import android.content.Context;
//import android.graphics.Color;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.core.content.ContextCompat;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.mtach.bideshibazar.R;
//
//import java.util.List;
//
//public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.ViewHolder> {
//
//    private final List<String> subcategories;
//    private final Context context;
//    private int selectedIndex;
//    private final OnCategorySelectedListener listener;
//
//    public interface OnCategorySelectedListener {
//        void onCategorySelected(int index);
//    }
//
//    public SubCategoryAdapter(List<String> subcategories, int selectedIndex, OnCategorySelectedListener listener) {
//        this.subcategories = subcategories;
//        this.selectedIndex = selectedIndex;
//        this.listener = listener;
//        this.context = null; // optional if not passing from constructor
//    }
//
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        Context ctx = parent.getContext();
//        View view = LayoutInflater.from(ctx).inflate(R.layout.item_subcategory, parent, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//        holder.textView.setText(subcategories.get(position));
//
//        if (position == selectedIndex) {
//            holder.textView.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.selected_color));
//            holder.textView.setTextColor(Color.WHITE);
//        } else {
//            holder.textView.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.default_background));
//            holder.textView.setTextColor(Color.BLACK);
//        }
//
//        holder.textView.setOnClickListener(v -> {
//            selectedIndex = position;
//            listener.onCategorySelected(position);
//            notifyDataSetChanged();
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return subcategories.size();
//    }
//
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//        TextView textView;
//        public ViewHolder(View itemView) {
//            super(itemView);
//            textView = itemView.findViewById(R.id.subcategoryText);
//        }
//    }
//}
