package br.com.edu.ifsc.anjinhocompatas.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import br.com.edu.ifsc.anjinhocompatas.R;
import br.com.edu.ifsc.anjinhocompatas.utilitarios.SharedPreferencesUtil;
import br.com.edu.ifsc.anjinhocompatas.vo.Usuario;

public class LoginActivity extends AppCompatActivity {

    public static final int LOGIN_ID = 10;
    private AutoCompleteTextView mEmailView;
    private EditText mSenhaView;
    private TextView mTextViewCadastrese;
    private Toolbar mToolbar;
    private LoginButton mLoginButtonFacebook;
    private CallbackManager mCallbackManager;
    private Usuario mUsuario;
    private Button mEmailSignInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(this.mToolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.mTextViewCadastrese = (TextView) findViewById(R.id.textViewCadastrese);
        this.mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        this.mSenhaView = (EditText) findViewById(R.id.password);
        this.mSenhaView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    logar();
                    return true;
                }
                return false;
            }
        });

        this.mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        this.mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                logar();
            }
        });

        this.mTextViewCadastrese.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, CadastroUsuarioActivity.class));
            }
        });
        eventoLoginFacebook();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    private void logar() {
        mEmailView.setError(null);
        mSenhaView.setError(null);

        // Pega os valores dos componentes
        String lEmail = mEmailView.getText().toString();
        String lSenha = mSenhaView.getText().toString();

        boolean lErroCampo = false;
        View focusView = null;

        // Verifica senha
        if (!TextUtils.isEmpty(lSenha) && !isSenhaValido(lSenha)) {
            mSenhaView.setError(getString(R.string.error_invalid_password));
            focusView = mSenhaView;
            lErroCampo = true;
        }

        // Verifica se o email é valido
        if (TextUtils.isEmpty(lEmail)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            lErroCampo = true;
        } else if (!isEmailValid(lEmail)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            lErroCampo = true;
        }

        if (lErroCampo) {
            focusView.requestFocus();
        } else {
            //Loga o usuário
        }
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isSenhaValido(String password) {
        return password.length() > 5;
    }

    private void loginComFacebook() {
        Intent intent = new Intent();
        intent.putExtra(getString(R.string.key_usuriao_logado), mUsuario);
        setResult(LOGIN_ID, intent);
        finish();
    }

    private void eventoLoginFacebook() {
        LoginManager.getInstance().logOut();
        this.mCallbackManager = CallbackManager.Factory.create();
        this.mLoginButtonFacebook = (LoginButton) findViewById(R.id.loginButton);
        this.mLoginButtonFacebook.setReadPermissions(Arrays.asList("public_profile", "email", "user_birthday"));
        this.mLoginButtonFacebook.registerCallback(this.mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject aObject, GraphResponse aResponse) {
                                mUsuario = new Usuario();
                                try {
                                    mUsuario = Usuario.converterObjetoJsonFacebook(aObject);
                                    Usuario.savarUsuarioBaseDados(LoginActivity.this, mUsuario);
                                    SharedPreferencesUtil.criarPreferenciaString(LoginActivity.this, mUsuario.getEmail(), R.string.key_usuriao_logado);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                loginComFacebook();
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender, picture.width(150).height(150),birthday"); // id,first_name,last_name,email,gender,birthday,cover,picture.type(large)
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                LoginManager.getInstance().logOut();
                Snackbar.make(getCurrentFocus(), getString(R.string.fb_operacao_cancelada), Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException error) {
                Snackbar.make(getCurrentFocus(), getString(R.string.erro_comunicacao), Snackbar.LENGTH_LONG).show();
            }
        });
    }
}

