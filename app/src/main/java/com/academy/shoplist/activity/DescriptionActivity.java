package com.academy.shoplist.activity;

import android.net.Uri;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.academy.shoplist.bean.Prodotto;
import com.academy.shoplist.data.SingletonShopList;
import com.academy.shoplist.intentConstant.Constant;
import com.jherome.linx.shoplist.R;
import fragment.EditDettaglioFragment;
import fragment.ViewDettaglioFragment;

public class DescriptionActivity extends AppCompatActivity implements EditDettaglioFragment.OnFragmentInteractionListener{

    private EditDettaglioFragment editFragment;
    private ViewDettaglioFragment viewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        Bundle extra= getIntent().getExtras();

        int codice = extra.getInt("codiceFragment");

        if (codice == Constant.VIEWITEMREQUESTCODE){

            viewFragment = new ViewDettaglioFragment();

        }

        if (codice == Constant.EDITITEMREQUESTCODE) {

            editFragment = new EditDettaglioFragment();

            getSupportFragmentManager().beginTransaction()
                  //  .replace(R.id.fragment_edit, editFragment, false)
                    .commit();


            int position=extra.getInt("position");

            Prodotto p =SingletonShopList.getInstance().getProdottoByPosition(position);

            TextView nameClickedToShow = (TextView) findViewById(R.id.nameClicked);
            TextView descriptionClickedToShow = (TextView) findViewById(R.id.descriptionClicked);

            nameClickedToShow.setText(p.getNome());
            descriptionClickedToShow.setText(p.getDescrizione());
        }





    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
