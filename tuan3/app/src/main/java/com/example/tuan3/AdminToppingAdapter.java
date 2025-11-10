package com.example.tuan3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.Locale;

public class AdminToppingAdapter extends RecyclerView.Adapter<AdminToppingAdapter.ToppingViewHolder> {

    private final List<AdminToppingModel> toppingList;
    private final OnToppingActionListener listener;
    private final Context context;

    public AdminToppingAdapter(Context context, List<AdminToppingModel> toppingList, OnToppingActionListener listener) {
        this.context = context;
        this.toppingList = toppingList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ToppingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_admin_topping, parent, false);
        return new ToppingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ToppingViewHolder holder, int position) {
        AdminToppingModel topping = toppingList.get(position);

        holder.tvName.setText(topping.getName());
        holder.tvPrice.setText(String.format(Locale.getDefault(), "Giá: %,d VNĐ", topping.getPrice()));

        // Cập nhật trạng thái hiển thị
        if (topping.isAvailable()) {
            holder.tvStatus.setText("Trạng thái: Còn hàng");
            holder.tvStatus.setTextColor(context.getResources().getColor(android.R.color.holo_green_dark));
        } else {
            holder.tvStatus.setText("Trạng thái: Hết hàng");
            holder.tvStatus.setTextColor(context.getResources().getColor(android.R.color.holo_red_dark));
        }

        holder.btnEdit.setOnClickListener(v -> listener.onEditClick(topping));
        holder.btnDelete.setOnClickListener(v -> listener.onDeleteClick(topping));
    }

    @Override
    public int getItemCount() {
        return toppingList.size();
    }

    // ViewHolder Class
    public static class ToppingViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPrice, tvStatus;
        ImageButton btnEdit, btnDelete;

        public ToppingViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvToppingNameAdmin);
            tvPrice = itemView.findViewById(R.id.tvToppingPriceAdmin);
            tvStatus = itemView.findViewById(R.id.tvToppingStatus);
            btnEdit = itemView.findViewById(R.id.btnEditTopping);
            btnDelete = itemView.findViewById(R.id.btnDeleteTopping);
        }
    }
}