package com.academy.shoplist.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.academy.shoplist.adapter.ProdottoAdapter;
import com.academy.shoplist.bean.Prodotto;
import com.academy.shoplist.data.ShoplistDatabaseManager;
import com.academy.shoplist.interfac.ItemClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.academy.shoplist.intentConstant.*;

import java.util.ArrayList;

import com.jherome.linx.shoplist.R;

import fragment.EditDettaglioFragment;


public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ProdottoAdapter mAdapter;
    private LinearLayoutManager mLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        attivaListener();
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Shoplist");
        setSupportActionBar(toolbar);

        FloatingActionButton aggiungi_prodotto = findViewById(R.id.add_prodotto);

        aggiungi_prodotto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activity_add = new Intent(MainActivity.this, AddProdottoActivity.class);
                startActivityForResult(activity_add, Constant.REQUESTCODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        attivaListener();

    }

    private void attivaListener() {
        setUp();
        mAdapter.setOnItemClickListener(new ItemClickListener() {

            @Override
            public void onItemClick(int position) {
                Intent descriptionIntent = new Intent(MainActivity.this, DescriptionActivity.class);
               // String nome_prodotto= mAdapter.prodotti.get(position).getNome();
                    descriptionIntent.putExtra("position", position);
                    descriptionIntent.putExtra("codiceFragment", Constant.VIEWITEMREQUESTCODE);
                    startActivityForResult(descriptionIntent,Constant.VIEWITEMREQUESTCODE);
                }

                @Override
                public void onItemDelete(int position) {
                    //String nome_prodotto= mAdapter.prodotti.get(position).getNome();
                    showAlertDialog(position);
                }

                @Override
                public void onItemEdit(int position) {
                Intent descriptionIntent = new Intent(MainActivity.this, DescriptionActivity.class);
                    String nome_prodotto= mAdapter.prodotti.get(position).getNome();

                    descriptionIntent.putExtra("position", position);
                    descriptionIntent.putExtra("codiceFragment", Constant.EDITITEMREQUESTCODE);
                    startActivityForResult(descriptionIntent,Constant.EDITITEMREQUESTCODE);

                     attivaListener();

                }


        });

    }

    public void setUp(){

        mRecyclerView = findViewById(R.id.RecycleView);
        mRecyclerView.setHasFixedSize(true);
        mLayout = new LinearLayoutManager(this);
        mAdapter = new ProdottoAdapter(ShoplistDatabaseManager.getInstance(MainActivity.this).getProdottiByCursor(ShoplistDatabaseManager.getInstance(MainActivity.this).getAllProdotti()));
        mRecyclerView.setLayoutManager(mLayout);
        mRecyclerView.setAdapter(mAdapter);


    }
    public void showAlertDialog(final int position){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Conferma eliminazione");
        alert.setMessage("Sei sicuro di voler eliminare il prodotto:" + mAdapter.prodotti.get(position).getNome() + "?");
        alert.setPositiveButton("Sì", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                String id = mAdapter.prodotti.get(position).getId();
                ShoplistDatabaseManager.getInstance(MainActivity.this).deleteProdottoById(id);
                attivaListener();
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                Toast.makeText(MainActivity.this, mAdapter.prodotti.get(position).getNome()+ "non è stato eliminato.", Toast.LENGTH_SHORT).show();
            }
        });
        alert.create().show();
    }
}