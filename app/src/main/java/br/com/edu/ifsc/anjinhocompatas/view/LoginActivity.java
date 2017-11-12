package br.com.edu.ifsc.anjinhocompatas.view;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.Arrays;

import br.com.edu.ifsc.anjinhocompatas.R;
import br.com.edu.ifsc.anjinhocompatas.vo.Usuario;

import static android.Manifest.permission.READ_CONTACTS;

public class LoginActivity extends AppCompatActivity {

    public static final int LOGIN_ID = 10;
    public static final String LOGIN_EXTRA_USUARIO = "usuario_logado";

    private AutoCompleteTextView mEmailView;
    private EditText mSenhaView;
    private TextView textViewCadastrese;
    private Toolbar toolbar;
    private LoginButton mLoginButtonFacebook;
    private CallbackManager mCallbackManager;
    private Usuario mUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textViewCadastrese = (TextView) findViewById(R.id.textViewCadastrese);
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mCallbackManager = CallbackManager.Factory.create();
        mLoginButtonFacebook = (LoginButton) findViewById(R.id.loginButton);
        mLoginButtonFacebook.setReadPermissions(Arrays.asList(
                "public_profile", "email", "user_birthday"));
        mLoginButtonFacebook.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                mUsuario = new Usuario();
                                try {
                                    String email = object.getString("email");
                                    mUsuario.setEmail(email);
                                    String nome = object.getString("name");
                                    mUsuario.setNome(nome);
                                    String birthday = object.getString("birthday");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                Intent intent = new Intent();
                                intent.putExtra(LOGIN_EXTRA_USUARIO, mUsuario);
                                setResult(LOGIN_ID, intent);
                                finish();
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender, picture,birthday"); // id,first_name,last_name,email,gender,birthday,cover,picture.type(large)
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        mSenhaView = (EditText) findViewById(R.id.password);
        mSenhaView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    logar();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                logar();
            }
        });

        textViewCadastrese.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, DesenvolvimentoActivity.class));
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
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
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isSenhaValido(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            AccessToken accessToken = loginResult.getAccessToken();
            Profile profile = Profile.getCurrentProfile();

            // Facebook Email address
            GraphRequest request = GraphRequest.newMeRequest(
                    loginResult.getAccessToken(),
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(
                                JSONObject object,
                                GraphResponse response) {
//                            Log.v("LoginActivity Response ", response.toString());

                            try {
                                String Name = object.getString("name");

                                String FEmail = object.getString("email");
//                                Log.v("Email = ", " " + FEmail);
                                Toast.makeText(getApplicationContext(), "Name " + Name, Toast.LENGTH_LONG).show();


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,name,email,gender, birthday");
            request.setParameters(parameters);
            request.executeAsync();
        }

        @Override
        public void onCancel() {
            LoginManager.getInstance().logOut();

        }

        @Override
        public void onError(FacebookException e) {

        }
    };
}

