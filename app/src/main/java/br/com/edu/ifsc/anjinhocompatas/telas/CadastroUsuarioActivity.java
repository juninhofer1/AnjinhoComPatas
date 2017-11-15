package br.com.edu.ifsc.anjinhocompatas.telas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import br.com.edu.ifsc.anjinhocompatas.R;

/**
 * Created by Wilson on 13/11/2017.
 */

public class CadastroUsuarioActivity extends AppCompatActivity {

    private EditText mEditTextEmail;
    private EditText mEditTextNome;
    private EditText mEditTextEndereco;
    private EditText mEditTextDataNascimento;
    private EditText mEditTextSenha;
    private EditText mEditTextConfirmacaoSenha;

    private ImageView mImageViewPerfil;
    private Button mButtonCadastro;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

//        Referenciando componentes
        mEditTextEmail = (EditText) findViewById(R.id.edittext_email);
        mEditTextNome = (EditText) findViewById(R.id.edittext_nome);
        mEditTextEndereco = (EditText) findViewById(R.id.edittext_endereco);
        mEditTextDataNascimento = (EditText) findViewById(R.id.edittext_nascimento);
        mEditTextSenha = (EditText) findViewById(R.id.edittext_senha);
        mEditTextConfirmacaoSenha = (EditText) findViewById(R.id.edittext_confirmacao_senha);

        mImageViewPerfil = (ImageView) findViewById(R.id.imageview_perfil);
        mButtonCadastro = (Button) findViewById(R.id.button_cadastrar);


//        Eventos dos componentes
        mButtonCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Validação dos campos de textos
                eventoCadastrar(eventoValidarComponentes());
            }
        });

        mImageViewPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Evento para pegar a imagem da galeria ou tirar goto camera...
                eventoEscolherFormaDePegarImagemPerfil();
            }
        });
    }

    private View eventoValidarComponentes(){
        View lViewFocar = null;
        mEditTextEmail.setError(null);
        mEditTextNome.setError(null);
        mEditTextEndereco.setError(null);
        mEditTextDataNascimento.setError(null);
        mEditTextSenha.setError(null);
        mEditTextConfirmacaoSenha.setError(null);

        if(!mEditTextEmail.getText().toString().isEmpty()) {

        } else {
            mEditTextEmail.setError(getString(R.string.error_preencha_campo));
            lViewFocar = mEditTextEmail;
            return lViewFocar;
        }

        if(!mEditTextNome.getText().toString().isEmpty()) {

        } else {
            mEditTextNome.setError(getString(R.string.error_preencha_campo));
            lViewFocar = mEditTextNome;
            return lViewFocar;
        }

        if(!mEditTextEndereco.getText().toString().isEmpty()) {

        } else {
            mEditTextEndereco.setError(getString(R.string.error_preencha_campo));
            lViewFocar = mEditTextEndereco;
            return lViewFocar;
        }

        if(!mEditTextDataNascimento.getText().toString().isEmpty()) {

        } else {
            mEditTextDataNascimento.setError(getString(R.string.error_preencha_campo));
            lViewFocar = mEditTextDataNascimento;
            return lViewFocar;
        }

        if(!mEditTextSenha.getText().toString().isEmpty()) {

        } else {
            mEditTextSenha.setError(getString(R.string.error_preencha_campo));
            lViewFocar = mEditTextSenha;
            return lViewFocar;
        }

        if(!mEditTextConfirmacaoSenha.getText().toString().isEmpty()) {

        } else {
            mEditTextConfirmacaoSenha.setError(getString(R.string.error_preencha_campo));
            lViewFocar = mEditTextConfirmacaoSenha;
            return lViewFocar;
        }
        return lViewFocar;
    }

    private void eventoCadastrar(View aView){
        if (aView != null) {
            aView.requestFocus();
        } else {
//            cadastrar
        }
    }

    private void eventoEscolherFormaDePegarImagemPerfil() {

    }
}
