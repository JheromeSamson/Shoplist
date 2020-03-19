package fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
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


public class EditDettaglioFragment extends Fragment {

    private TextView editname;
    private TextView editDescrizione;

    private int position;
    private Button confirm_Edit_Button;
    private FragmentListener listener;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_edit, container, false);

        if(getArguments() != null){
            position = getArguments().getInt("position");
        }

        Prodotto p = SingletonShopList.getInstance().getProdottoByPosition(position);

        ((TextView) v.findViewById(R.id.editName)).setText(p.getNome());
        ((TextView) v.findViewById(R.id.editDescription)).setText(p.getDescrizione());

        editname = (TextView) v.findViewById(R.id.editName);
        editDescrizione = (TextView) v.findViewById(R.id.editDescription);

        confirm_Edit_Button = (Button) v.findViewById(R.id.confirm_edit);

        confirm_Edit_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SingletonShopList.getInstance().setEdit(editname.getText().toString(),editDescrizione.getText().toString(),position);

            }
        });


        return v;
    }

    public void updateEditText(String oldName,String oldDescription){
        //editName.setText(oldName);
        //editDescription.setText(oldDescription);
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if (context instanceof FragmentListener){
            listener = (FragmentListener) context;
        }else{
            throw new RuntimeException(context.toString()
            + "MUST IMPLEMENT FRAGMENTEDITLISTENER");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }






    /*
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public EditDettaglioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditDettaglioFragment.
     *
    // TODO: Rename and change types and number of parameters
    public static EditDettaglioFragment newInstance(String param1, String param2) {
        EditDettaglioFragment fragment = new EditDettaglioFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_dettaglio, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     *
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    } */
}
