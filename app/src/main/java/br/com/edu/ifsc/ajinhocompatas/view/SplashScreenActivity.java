package br.com.edu.ifsc.ajinhocompatas.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.edu.ifsc.ajinhocompatas.MainActivity;
import br.com.edu.ifsc.ajinhocompatas.R;

public class SplashScreenActivity extends Activity {

    // Splash screen timer
    private static int SPLASH_TEMPO_FIM = 2500;
    private static int SPLASH_TEMPO_INICIO = 1000;
    private static int DURACAO_ANIMACAO = 1500;

    private ImageView imageViewLogo;
    private TextView bemVindo;

    private Animation animation01;
    private Animation animation02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        imageViewLogo = (ImageView) findViewById(R.id.imageViewLogo);
        bemVindo = (TextView) findViewById(R.id.bemVindo);

        animation01 = AnimationUtils.loadAnimation(this, R.anim.animation01);
        animation01.setDuration(SPLASH_TEMPO_FIM);

        animation02 = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        animation02.setDuration(DURACAO_ANIMACAO);

        bemVindo.startAnimation(animation01);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                start();
            }
        }, SPLASH_TEMPO_INICIO);
    }

    private void start() {
        imageViewLogo.setVisibility(View.VISIBLE);
        imageViewLogo.startAnimation(animation02);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        }, SPLASH_TEMPO_FIM);
    }
}
