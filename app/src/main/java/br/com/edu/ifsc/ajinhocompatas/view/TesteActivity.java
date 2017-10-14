package br.com.edu.ifsc.ajinhocompatas.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import br.com.edu.ifsc.ajinhocompatas.R;

/**
 * Created by Wilson on 07/10/2017.
 */

public class TesteActivity extends AppCompatActivity {
    private Toolbar toolbar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desenvolvimento);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(this.toolbar);
//        getSupportActionBar().setLogo(R.drawable.icon_app_s);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
