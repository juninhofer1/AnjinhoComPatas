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
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import br.com.edu.ifsc.anjinhocompatas.R;

import static android.Manifest.permission.READ_CONTACTS;

public class LoginActivity extends AppCompatActivity {

    /**
     * Id que identifica READ_CONTACTS permissão de contatos.
     */
    private static final int REQUEST_READ_CONTACTS = 0;
    private AutoCompleteTextView mEmailView;
    private EditText mSenhaView;
    private TextView textViewCadastrese;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(this.toolbar);
//        getSupportActionBar().setLogo(R.drawable.icon_app_s);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textViewCadastrese = (TextView) findViewById(R.id.textViewCadastrese);
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        pupularAutoComplete();

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

    private void pupularAutoComplete() {
        if (!meusContatosSolicitar()) {
            return;
        }
    }

    private boolean meusContatosSolicitar() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(mEmailView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    });
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                pupularAutoComplete();
            }
        }
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
}

