package com.academy.shoplist.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.academy.shoplist.intentConstant.Constant;
import com.academy.shoplist.interfac.FragmentListener;
import com.jherome.linx.shoplist.R;
import fragment.EditDettaglioFragment;
import fragment.ViewDettaglioFragment;

public class DescriptionActivity extends AppCompatActivity implements FragmentListener {

    private EditDettaglioFragment editFragment;
    private ViewDettaglioFragment viewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        Bundle extra = getIntent().getExtras();
        int codice = extra.getInt("codiceFragment");
        String nome = extra.getString("nome prodotto");

        Bundle args = new Bundle();
        args.putString("nome prodotto", nome);

        if (codice == Constant.VIEWITEMREQUESTCODE){
            viewFragment = new ViewDettaglioFragment();
            viewFragment.setArguments(args);

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container,viewFragment);
            fragmentTransaction.commit();
        }

        if (codice == Constant.EDITITEMREQUESTCODE) {
            editFragment = new EditDettaglioFragment();
            editFragment.setArguments(args);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, editFragment)
                    .commit();
        }


  /*      //FragmentManager fragmentManager = getSupportFragmentManager();
        //FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Bundle extra= getIntent().getExtras();

        int codice = extra.getInt("codiceFragment");
            TextView descriptionClickedToShow = (TextView) findViewById(R.id.descriptionClicked);

        if (codice == Constant.EDITITEMREQUESTCODE){
            editFragment = new EditDettaglioFragment();

            //.add(R.id.view, viewFragment);
            //fragmentTransaction.commit();
        }

        if (codice == Constant.VIEWITEMREQUESTCODE) {



            int position=extra.getInt("position");

            Prodotto p =SingletonShopList.getInstance().getProdottoByPosition(position);

            TextView nameClickedToShow = (TextView) findViewById(R.id.nameClicked);
            TextView descriptionClickedToShow = (TextView) findViewById(R.id.descriptionClicked);

            nameClickedToShow.setText(p.getNome());
            descriptionClickedToShow.setText(p.getDescrizione());*/
        }

    @Override
    public int onItemClicked(int position) {
        return 0;
    }
}
