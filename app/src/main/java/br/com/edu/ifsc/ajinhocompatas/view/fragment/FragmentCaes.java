package br.com.edu.ifsc.ajinhocompatas.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.com.edu.ifsc.ajinhocompatas.R;

/**
 * Created by Wilson on 13/10/2017.
 */

public class FragmentCaes extends Fragment {

    private View mView;

    private EditText mCaixaTexto;
    private Button mBotaoIdade;
    private TextView mResultadoIdade;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mView = inflater.inflate(R.layout.fragment_caes, container, false);


        this.mCaixaTexto = (EditText) this.mView.findViewById(R.id.caixaTextoId);
        this.mBotaoIdade = (Button) this.mView.findViewById(R.id.botaoIdadeId);
        this.mResultadoIdade = (TextView) this.mView.findViewById(R.id.resultadoIdadeId);

        mBotaoIdade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //recuperar o que foi digitado
                //toString converte para string
                String textoDigitado = mCaixaTexto.getText().toString();

                //isEmpty verifica se essa String está vazia
                if( textoDigitado.isEmpty() ){
                    //string vazia
                    Snackbar.make(view, getActivity().getString(R.string.numero_nao_digitado), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }else{
                    //converte a string com a idade do cachorro para int
                    int valorDigitado = Integer.parseInt(textoDigitado);
                    int resultadoFinal= valorDigitado * 7;

                    mResultadoIdade.setText("A idade do cachorro em anos humanos é: " + resultadoFinal + " anos");
                }
            }
        });

        return this.mView;
    }
}
