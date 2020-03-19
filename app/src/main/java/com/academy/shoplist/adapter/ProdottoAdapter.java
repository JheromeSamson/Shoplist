package com.academy.shoplist.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.academy.shoplist.bean.Prodotto;
import com.academy.shoplist.interfac.ItemClickListener;


import java.util.ArrayList;

import com.jherome.linx.shoplist.R;

public class ProdottoAdapter extends RecyclerView.Adapter<ProdottoAdapter.ProdottoViewHolder> {

     private ArrayList<Prodotto>prodotti= new ArrayList<>();
     private ItemClickListener mListener;

    public static class ProdottoViewHolder extends RecyclerView.ViewHolder{
       public ImageView img_immagine_prodotto;
       public TextView textView_nomeProdotto;
       public TextView textView_descrizioneProdotto;
       public ImageButton imageButton_deleteProdotto;
       public ImageButton imageButton_editProdotto;

        public ProdottoViewHolder(@NonNull final View itemView, final ItemClickListener listener) {
            super(itemView);
            img_immagine_prodotto=itemView.findViewById(R.id.immagine_prodotto);
            textView_nomeProdotto=itemView.findViewById(R.id.nome_prodotto);
            textView_descrizioneProdotto=itemView.findViewById(R.id.descrizione_prodotto);
            imageButton_deleteProdotto = itemView.findViewById(R.id.delete_item);
            imageButton_editProdotto = itemView.findViewById(R.id.edit_item);

            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v){
                    if (listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });

            imageButton_deleteProdotto.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v){
                    if (listener!= null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemDelete(position);
                        }
                    }
                }
            });

            imageButton_editProdotto.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v){
                    if (listener!= null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemEdit(position);
                        }
                    }
                }
            });

        }
    }

    public ProdottoAdapter(ArrayList<Prodotto> listaProdotti){
        this.prodotti=listaProdotti;
    }


    @NonNull
    @Override
    public ProdottoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.prodotto_item,parent,false);
        ProdottoViewHolder pvh= new ProdottoViewHolder(v, mListener);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull ProdottoViewHolder holder, int position) {
        Prodotto prodottoCorrente=prodotti.get(position);
        holder.img_immagine_prodotto.setImageResource(prodottoCorrente.getImmagine());
        holder.textView_nomeProdotto.setText(prodottoCorrente.getNome());
        holder.textView_descrizioneProdotto.setText(prodottoCorrente.getDescrizione());
    }

    @Override
    public int getItemCount() {
        return prodotti.size();
    }

    public void setOnItemClickListener(ItemClickListener listener){
        mListener = listener;
    }




}
