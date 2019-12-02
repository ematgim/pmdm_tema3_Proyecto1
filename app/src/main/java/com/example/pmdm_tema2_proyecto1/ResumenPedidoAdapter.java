package com.example.pmdm_tema2_proyecto1;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ResumenPedidoAdapter extends RecyclerView.Adapter<ResumenPedidoAdapter.MiViewHolder>{

    private ArrayList<Menu> lista;

    public ResumenPedidoAdapter(ArrayList<Menu> lista) {

        this.lista = lista;
    }

    @NonNull
    @Override
    public ResumenPedidoAdapter.MiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_elementoslista, parent, false);

        MiViewHolder miViewHolder = new MiViewHolder(view);

        return miViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ResumenPedidoAdapter.MiViewHolder holder, int position) {

    String pizza = lista.get(position).getPizza().getName();
        holder.txtVComida.setText(pizza);

    String bebida = lista.get(position).getDrink().getName();
    holder.txtVBebida.setText(bebida);

    String precio = String.valueOf(lista.get(position).getTotalPrice());
    holder.txtVprecioMenu.setText(precio);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class MiViewHolder extends RecyclerView.ViewHolder {
        public TextView txtVComida;
        public TextView txtVBebida;
        public TextView txtVprecioMenu;

        public MiViewHolder(View view) {
            super(view);
            Log.v("ADAPTER", "entra");
            txtVComida = itemView.findViewById(R.id.txtVComida);
            txtVBebida = itemView.findViewById(R.id.txtVBebida);
            txtVprecioMenu = itemView.findViewById(R.id.txtVPrecioMenu);
        }
    }
}
