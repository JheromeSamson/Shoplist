package com.academy.shoplist.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.academy.shoplist.adapter.ProdottoAdapter;
import com.academy.shoplist.bean.Prodotto;
import com.academy.shoplist.data.ShoplistDatabaseManager;
import com.academy.shoplist.data.SingletonShopList;
import com.academy.shoplist.interfac.ItemClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.academy.shoplist.intentConstant.*;

import java.util.ArrayList;

import com.jherome.linx.shoplist.R;


public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ProdottoAdapter mAdapter;
    private LinearLayoutManager mLayout;
    ArrayList<Prodotto> prodotti = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        attivaListener();
        //ShoplistDatabaseManager.getInstance(MainActivity.this).addProdotto(new Prodotto(R.drawable.caffe,"nometest","descrizionetest"));
        setUp();


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
                    descriptionIntent.putExtra("position", position);
                    descriptionIntent.putExtra("codiceFragment", Constant.VIEWITEMREQUESTCODE);
                    startActivityForResult(descriptionIntent,Constant.VIEWITEMREQUESTCODE);

                }

                @Override
                public void onItemDelete(int position) {
                    ShoplistDatabaseManager.getInstance(MainActivity.this).deleteProdotto(ShoplistDatabaseManager.getInstance(MainActivity.this).getProdottiByCursor(ShoplistDatabaseManager.getInstance(MainActivity.this).getAllProdotti()).get(position));

                    SingletonShopList.getInstance().removeProdotto(position);
                    attivaListener();
                }

                @Override
                public void onItemEdit(int position) {
                    Intent descriptionIntent = new Intent(MainActivity.this, DescriptionActivity.class);
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
        mAdapter = new ProdottoAdapter(SingletonShopList.getInstance().prodotto);
        mAdapter = new ProdottoAdapter(ShoplistDatabaseManager.getInstance(MainActivity.this).getProdottiByCursor(ShoplistDatabaseManager.getInstance(MainActivity.this).getAllProdotti()));
        mRecyclerView.setLayoutManager(mLayout);
        mRecyclerView.setAdapter(mAdapter);


    }

}
