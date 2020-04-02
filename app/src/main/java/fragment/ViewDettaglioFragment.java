package fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.academy.shoplist.activity.DescriptionActivity;
import com.academy.shoplist.activity.MainActivity;
import com.academy.shoplist.bean.Prodotto;
import com.academy.shoplist.data.ShoplistDatabaseManager;
import com.academy.shoplist.data.SingletonShopList;
import com.academy.shoplist.interfac.FragmentListener;
import com.academy.shoplist.util.DbBitMapUtility;
import com.jherome.linx.shoplist.R;


public class ViewDettaglioFragment extends Fragment {

    private TextView name;
    private TextView descrizione;
    private ImageView immagine;
    private int position;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_view, container, false);

        name = v.findViewById(R.id.nome_stampa);
        descrizione = v.findViewById(R.id.descrizione_stampa);
        immagine = v.findViewById(R.id.img_dettaglio);
        if (getArguments() != null) {
            position = getArguments().getInt("position");
        }

        Prodotto prodotto = ShoplistDatabaseManager.getInstance(getActivity()).getProdottiByCursor(ShoplistDatabaseManager.getInstance(getActivity()).getAllProdotti()).get(position);

        byte[] rowImage;

        if (ShoplistDatabaseManager.getInstance(getActivity()).selectImg(prodotto.getImmagine()) != null) {

            rowImage = ShoplistDatabaseManager.getInstance(getActivity()).selectImg(prodotto.getImmagine());
            immagine.setImageBitmap(DbBitMapUtility.getImage(rowImage));

        } else {
            immagine.setImageResource(R.drawable.ic_add);
        }

        name.setText(prodotto.getNome());
        descrizione.setText(prodotto.getDescrizione());


        return v;
    }

    public void updateEditText(int position) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentListener) {
            //listener = (FragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + "MUST IMPLEMENT FRAGMENTVIEWLISTENER");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //listener = null;
    }
}
