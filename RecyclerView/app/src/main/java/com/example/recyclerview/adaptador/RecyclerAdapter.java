package com.example.recyclerview.adaptador;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.R;
import com.example.recyclerview.activity.DetailActivity;
import com.example.recyclerview.model.ItemList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder> {
    private List<ItemList> items;
    private List<ItemList> originalItems;
    private RecyclerItemClick itemClick;

    private Context context;
    private ViewGroup parent;
    private AlertDialog pdEdit;
    private EditText etTitle, etContent;

    public RecyclerAdapter(List<ItemList> items, RecyclerItemClick itemClick) {
        this.items = items;
        this.itemClick = itemClick;
        this.originalItems = new ArrayList<>();
        originalItems.addAll(items);
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (context == null)
            context = parent.getContext();
        if (this.parent == null)
            this.parent = parent;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_view, parent, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerHolder holder, final int position) {
        final ItemList item = items.get(position);
        holder.imgItem.setImageResource(item.getImgResource());
        holder.tvTitulo.setText(item.getTitulo());
        holder.tvDescripcion.setText(item.getDescripcion());


        holder.itemView.setOnClickListener(v ->
                itemClick.itemClick(item)
        );

        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
                intent.putExtra("itemDetail", item);
                holder.itemView.getContext().startActivity(intent);
            }
        });*/

        holder.itemView.setOnLongClickListener(v -> {
            showPopUpEdit(item, position);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void filter(final String strSearch) {
        if (strSearch.length() == 0) {
            items.clear();
            items.addAll(originalItems);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                items.clear();
                List<ItemList> collect = originalItems.stream()
                        .filter(i -> i.getTitulo().toLowerCase().contains(strSearch))
                        .collect(Collectors.toList());

                items.addAll(collect);
            } else {
                items.clear();
                for (ItemList i : originalItems) {
                    if (i.getTitulo().toLowerCase().contains(strSearch)) {
                        items.add(i);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    private void editItem(int position, String title, String content) {
        Toast.makeText(context, "Actualizando...", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(() -> {
            ItemList item = items.get(position);
            item.setTitulo(title);
            item.setDescripcion(content);
            items.set(position, item);
            notifyItemChanged(position);
            Toast.makeText(context, "Hecho !!", Toast.LENGTH_SHORT).show();
        }, 2000);
    }

    private void deleteItem(int position) {
        Toast.makeText(context, "Eliminando...", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(() -> {
            originalItems.remove(items.get(position));
            items.remove(position);
            notifyItemRemoved(position);
            Toast.makeText(context, "Hecho !!", Toast.LENGTH_SHORT).show();
        }, 2000);
    }

    public void addItem(ItemList itemList) {
        Toast.makeText(context, "Agregando...", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(() -> {
            originalItems.add(itemList);
            items.add(itemList);
            notifyItemInserted(getItemCount() - 1);
            Toast.makeText(context, "Hecho !!", Toast.LENGTH_SHORT).show();
        }, 2000);
    }

    private void showPopUpEdit(ItemList item, int position) {
        View viewPopUp = LayoutInflater.from(context).inflate(R.layout.popup_edit_item, null);
        etTitle = viewPopUp.findViewById(R.id.et_title);
        etContent = viewPopUp.findViewById(R.id.et_content);
        etTitle.setText(item.getTitulo());
        etContent.setText(item.getDescripcion());

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(viewPopUp)
                .setPositiveButton("Guardar", (dialogInterface, i) ->
                        editItem(position, etTitle.getText().toString(), etContent.getText().toString())
                )
                .setNegativeButton("Eliminar", (dialogInterface, i) ->
                        deleteItem(position)
                )
                .setCancelable(true);

        pdEdit = builder.create();
        pdEdit.show();
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder {
        private ImageView imgItem;
        private TextView tvTitulo;
        private TextView tvDescripcion;

        public RecyclerHolder(@NonNull View itemView_1) {
            super(itemView_1);

            imgItem = itemView.findViewById(R.id.imgItem);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
        }
    }

    public interface RecyclerItemClick {
        void itemClick(ItemList item);
    }
}
