package br.com.edu.ifsc.anjinhocompatas.telas;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

import br.com.edu.ifsc.anjinhocompatas.CadastroAnimalActivity;
import br.com.edu.ifsc.anjinhocompatas.MainActivity;
import br.com.edu.ifsc.anjinhocompatas.R;
import br.com.edu.ifsc.anjinhocompatas.bancodedados.implementacao.UsuarioDao;
import br.com.edu.ifsc.anjinhocompatas.modelos.Usuario;
import br.com.edu.ifsc.anjinhocompatas.utilitarios.DialogoUtil;
import br.com.edu.ifsc.anjinhocompatas.utilitarios.ImagemUtil;
import br.com.edu.ifsc.anjinhocompatas.utilitarios.SharedPreferencesUtil;

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
    private Usuario mUsuario;
    private Bitmap mImagemBitmap;
    UsuarioDao usuarioDao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        usuarioDao = new UsuarioDao(CadastroUsuarioActivity.this);
        usuarioDao.open();

        mUsuario = new Usuario();

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
//                Evento para pegar a imagem da galeria ou tirar foto camera...
                eventoEscolherFormaDePegarImagemPerfil();
            }
        });

        mImageViewPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventoEscolherFormaDePegarImagemPerfil();
            }
        });
    }

//    Método que espera um resultado de outra activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        BitMap mapa de bits, utilizado para imagens
        if (requestCode == 2 && resultCode == Activity.RESULT_OK) {
//            Pega a informação retornada da camera
            mImagemBitmap = (Bitmap) data.getExtras().get("data");
//            seta a imagem(Bitmap) em um ImageView
            mImageViewPerfil.setImageBitmap(mImagemBitmap);
//            Transforma o bitmap em uma String base64 para poder salvar no usuario e depois no banco;
            mUsuario.setFoto(ImagemUtil.converter(mImagemBitmap));
        } else if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            try {
//                Cria um objeto uri que faz o gerenciamento da imagem retornada da galeria
                Uri imageUri = data.getData();
//                Converte nosso objeto uri para um objeto InputStrem que será usado para tranformar nossa imagem em um BITmap
                InputStream imageStream;
                imageStream = getContentResolver().openInputStream(imageUri);
//                Decodefica nosso imput stream para uma bitmap
                mImagemBitmap = BitmapFactory.decodeStream(imageStream);
//                seta o bitmap no image view
                mImageViewPerfil.setImageBitmap(mImagemBitmap);
//                Transforma o bitmap em uma String base64 para poder salvar no usuario e depois no banco;
                mUsuario.setFoto(ImagemUtil.converter(mImagemBitmap));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
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
            String lEmail = mEditTextEmail.getText().toString();
            if (usuarioDao.carregarPorEmail(lEmail) == null) {
                mUsuario.setEmail(lEmail);
            } else {
                mEditTextEmail.setError(getString(R.string.error_email_cadastado));
                lViewFocar = mEditTextEmail;
                return lViewFocar;
            }
        } else {
            mEditTextEmail.setError(getString(R.string.error_preencha_campo));
            lViewFocar = mEditTextEmail;
            return lViewFocar;
        }

        if(!mEditTextNome.getText().toString().isEmpty()) {
            mUsuario.setNome(mEditTextNome.getText().toString());
        } else {
            mEditTextNome.setError(getString(R.string.error_preencha_campo));
            lViewFocar = mEditTextNome;
            return lViewFocar;
        }

        if(!mEditTextEndereco.getText().toString().isEmpty()) {
            mUsuario.setEndereco(mEditTextEndereco.getText().toString());
        } else {
            mEditTextEndereco.setError(getString(R.string.error_preencha_campo));
            lViewFocar = mEditTextEndereco;
            return lViewFocar;
        }

        if(!mEditTextDataNascimento.getText().toString().isEmpty()) {
            mUsuario.setDataNascimento(mEditTextEndereco.getText().toString());
        } else {
            mEditTextDataNascimento.setError(getString(R.string.error_preencha_campo));
            lViewFocar = mEditTextDataNascimento;
            return lViewFocar;
        }

        if(!mEditTextSenha.getText().toString().isEmpty()) {
            mUsuario.setSenha(mEditTextSenha.getText().toString());
        } else {
            mEditTextSenha.setError(getString(R.string.error_preencha_campo));
            lViewFocar = mEditTextSenha;
            return lViewFocar;
        }

        if(!mEditTextConfirmacaoSenha.getText().toString().isEmpty()) {
            if(!mEditTextConfirmacaoSenha.getText().toString().equals(mUsuario.getSenha())) {
                mEditTextConfirmacaoSenha.setError(getString(R.string.error_senha_diferente));
                lViewFocar = mEditTextConfirmacaoSenha;
                return lViewFocar;
            }
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
            if(Usuario.savarUsuarioBaseDados(CadastroUsuarioActivity.this ,mUsuario)){
                Toast.makeText(CadastroUsuarioActivity.this, getString(R.string.msm_cadastro_sucesso), Toast.LENGTH_LONG).show();
                SharedPreferencesUtil.criarPreferenciaString(CadastroUsuarioActivity.this, mUsuario.getEmail(), R.string.key_usuriao_logado);
                Intent intent = new Intent(CadastroUsuarioActivity.this, MainActivity.class);
                intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(CadastroUsuarioActivity.this, getString(R.string.msm_cadastro_erro), Toast.LENGTH_LONG).show();
            }
        }
    }

    private void eventoEscolherFormaDePegarImagemPerfil() {
//        Métodos de clcik do botão
        DialogInterface.OnClickListener onClickListenerCamera = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Chama a tela padrão de tirar foto do dispositivo.
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                Inicia a camera passando uma chave de identificação para utilizar no onActivityResult
                startActivityForResult(cameraIntent, 2);
            }
        };

        DialogInterface.OnClickListener onClickListenerGaleria = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Chama a tela da galeria do dispositivo
                Intent intent = new Intent(Intent.ACTION_PICK,  android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                Inicia a camera passando uma chave de identificação para utilizar no onActivityResult
                startActivityForResult(intent, 1);
            }
        };

//        Dialogo com duas opções GALERIA OU CAMERA
        DialogoUtil.dialogCamera(CadastroUsuarioActivity.this, "Escolha uma opção para carregar a foto de perfil", onClickListenerGaleria , onClickListenerCamera).show();
    }
}
