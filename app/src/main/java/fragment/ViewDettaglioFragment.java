package fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.academy.shoplist.bean.Prodotto;
import com.academy.shoplist.data.SingletonShopList;
import com.academy.shoplist.interfac.FragmentListener;
import com.jherome.linx.shoplist.R;


public class ViewDettaglioFragment extends Fragment {

    private TextView name;
    private TextView descrizione;
    private int position;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_view, container, false);

        name = v.findViewById(R.id.nome_stampa);
        descrizione = v.findViewById(R.id.descrizione_stampa);

        if(getArguments() != null){
            position = getArguments().getInt("position");
        }

        Prodotto p = SingletonShopList.getInstance().getProdottoByPosition(position);

        name.setText(p.getNome());
        descrizione.setText(p.getDescrizione());

        return v;
}

    public void updateEditText(int position){


    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if (context instanceof FragmentListener){
            //listener = (FragmentListener) context;
        }else{
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