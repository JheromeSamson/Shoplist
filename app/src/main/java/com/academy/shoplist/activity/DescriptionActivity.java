package com.academy.shoplist.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.academy.shoplist.intentConstant.Constant;
import com.academy.shoplist.interfac.FragmentListener;
import com.jherome.linx.shoplist.R;

import fragment.EditDettaglioFragment;
import fragment.ViewDettaglioFragment;

public class DescriptionActivity extends AppCompatActivity implements FragmentListener {

    private EditDettaglioFragment editFragment;
    private ViewDettaglioFragment viewFragment;
    private Button bottone_ritorno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        Bundle extra = getIntent().getExtras();
        int codice = extra.getInt("codiceFragment");
        int position = extra.getInt("position");

        Bundle args = new Bundle();
        args.putInt("position", position);

        if (codice == Constant.VIEWITEMREQUESTCODE) {
            viewFragment = new ViewDettaglioFragment();
            viewFragment.setArguments(args);

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, viewFragment);
            fragmentTransaction.commit();
        }

        if (codice == Constant.EDITITEMREQUESTCODE) {
            editFragment = new EditDettaglioFragment();
            editFragment.setArguments(args);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, editFragment)
                    .commit();
        }

        bottone_ritorno = (Button) findViewById(R.id.button_return);

        bottone_ritorno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void onItemClicked(boolean ritorno) {
        if (ritorno == true) {
            finish();
        }
    }
}
