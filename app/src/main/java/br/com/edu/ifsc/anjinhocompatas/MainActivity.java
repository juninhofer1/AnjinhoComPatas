package br.com.edu.ifsc.anjinhocompatas;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;

import com.facebook.login.LoginManager;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import br.com.edu.ifsc.anjinhocompatas.constantes.MenuLateralOpcoesProps;
import br.com.edu.ifsc.anjinhocompatas.constantes.MenuLateralProps;
import br.com.edu.ifsc.anjinhocompatas.utilitarios.CoresUtil;
import br.com.edu.ifsc.anjinhocompatas.utilitarios.DialogoUtil;
import br.com.edu.ifsc.anjinhocompatas.utilitarios.ImagemUtil;
import br.com.edu.ifsc.anjinhocompatas.utilitarios.SharedPreferencesUtil;
import br.com.edu.ifsc.anjinhocompatas.telas.DesenvolvimentoActivity;
import br.com.edu.ifsc.anjinhocompatas.telas.LoginActivity;
import br.com.edu.ifsc.anjinhocompatas.telas.adapter.ViewPagerAdapter;

import br.com.edu.ifsc.anjinhocompatas.telas.fragment.FragmentAnimais;
import br.com.edu.ifsc.anjinhocompatas.modelos.Usuario;


public class MainActivity extends AppCompatActivity {

    private Drawer.Result navegationDrawerLeft;
    private AccountHeader.Result hearderNavegationLeft;
    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ProfileDrawerItem mProfileDrawerItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Chama o método de inicialização dos componentes
        //dentro desse método pode ter varios outros ou só as referencias dos componentes.
        this.inicializarComponentes();
    }

    //Inicializando os componetes e adicionando algumas caracteristicas.
    private void inicializarComponentes() {
        //Pega Referencia dos componetes do xml - layout
        this.mToolbar = (Toolbar) findViewById(R.id.toolbar);
        this.mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        this.mViewPager = (ViewPager) findViewById(R.id.viewPager);

        //Adicionando propriedades na toolbar.
        this.setSupportActionBar(this.mToolbar);
//        this.getSupportActionBar().setLogo(R.drawable.icon_app_s);
        this.getSupportActionBar().setTitle(null);

        //Cria o menu lateral e seus itens.
        //Hedear - hearderNavegationLeft
        //Opções de menu - navegationDrawerLeft
        this.criarMenuLateral();

        //Cria a tabLayout que possui dois intes Cachorros e gatos
        //É necessário uma ViewPages para adicionar os fragments que são partes da tela.
        /*Fragments são semelhantes a activites, mas não são telas como as activities são pequenas fragmentos de telas que
        serão chamados em uma activity.*/
        this.criarViewPager(this.mViewPager);
    }

    private void criarViewPager(ViewPager aViewPager) {
        //false cão;
        Bundle bundleDog = new Bundle();
        bundleDog.putBoolean("animalzinho", false);

        //true gato;
        Bundle bundleCat = new Bundle();
        bundleCat.putBoolean("animalzinho", true);

        FragmentAnimais fragmentCao = new FragmentAnimais();
        fragmentCao.setArguments(bundleDog);

        FragmentAnimais fragmentGato = new FragmentAnimais();
        fragmentGato.setArguments(bundleCat);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction();

        ViewPagerAdapter lViewPagerAdapter = new ViewPagerAdapter(fragmentManager);
        lViewPagerAdapter.addFragment(fragmentCao, "Cães");
        lViewPagerAdapter.addFragment(fragmentGato, "Gatos");
        aViewPager.setAdapter(lViewPagerAdapter);
        this.mTabLayout.setupWithViewPager(aViewPager);
    }

    private ProfileDrawerItem getUsuarioPerfil() {
        String lEmail = SharedPreferencesUtil.lerPreferenciaString(MainActivity.this, R.string.key_usuriao_logado);
        this.mProfileDrawerItem = new ProfileDrawerItem();
        mProfileDrawerItem.withName("Usuário").withEmail(getResources().getString(R.string.app_name));
        if(lEmail != null) {
            Usuario lUsuario = Usuario.carregarUsuarioPorEmailBD(MainActivity.this, lEmail);
            Drawable lDrawable = ImagemUtil.converterBase64(getResources(), lUsuario.getFoto());
            mProfileDrawerItem.withName(lUsuario.getNome()).withIcon(lDrawable).withEmail(lUsuario.getEmail());
        }
        return mProfileDrawerItem;
    }

    private void criarMenuLateral() {
        this.hearderNavegationLeft = new AccountHeader()
                .withActivity(this)
                .withCompactStyle(false)
                .withHeaderBackground(R.color.primary)
                .withThreeSmallProfileImages(false)
                .withSelectionListEnabledForSingleProfile(false)
                .addProfiles(getUsuarioPerfil())
                .build();

        this.navegationDrawerLeft = new Drawer()
                .withActivity(this)
                .withToolbar(this.mToolbar)
                .withDisplayBelowToolbar(false)
                .withActionBarDrawerToggle(true)
                .withDrawerGravity(Gravity.LEFT)
                .withTranslucentStatusBar(false)
                .withSelectedItem(-1)
                .withAccountHeader(hearderNavegationLeft)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                        evetoClickItensMenuLateral(drawerItem.getIdentifier());
                    }
                })
                .withOnDrawerItemLongClickListener(new Drawer.OnDrawerItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                        return false;
                    }
                })
                .build();

        this.navegationDrawerLeft.addItem(new PrimaryDrawerItem()
                .withIdentifier(MenuLateralProps.INICIAL.getmId())
                .withName(MenuLateralProps.INICIAL.getmNomeTela())
                .withIcon(CoresUtil.alterarCorDrawableMenuItem(getApplication(), R.drawable.icon_home))
                .withTextColor(getResources().getColor(R.color.colorPrimary)));

        this.navegationDrawerLeft.addItem(new PrimaryDrawerItem()
                .withIdentifier(MenuLateralProps.FAVORITOS.getmId())
                .withName(MenuLateralProps.FAVORITOS.getmNomeTela())
                .withIcon(CoresUtil.alterarCorDrawableMenuItem(getApplication(), R.drawable.ic_favorite))
                .withTextColor(getResources().getColor(R.color.colorPrimary)));

        this.navegationDrawerLeft.addItem(new PrimaryDrawerItem()
                .withIdentifier(MenuLateralProps.DOAR_UM_AMIGO.getmId())
                .withName(MenuLateralProps.DOAR_UM_AMIGO.getmNomeTela())
                .withIcon(CoresUtil.alterarCorDrawableMenuItem(getApplication(), R.drawable.icon_search))
                .withTextColor(getResources().getColor(R.color.colorPrimary)));

        this.navegationDrawerLeft.addItem(new DividerDrawerItem());

        if(SharedPreferencesUtil.lerPreferenciaString(MainActivity.this, R.string.key_usuriao_logado) == null){
            this.navegationDrawerLeft.addItem(new PrimaryDrawerItem()
                    .withIdentifier(MenuLateralProps.Entrar.getmId())
                    .withName(MenuLateralProps.Entrar.getmNomeTela())
                    .withIcon(CoresUtil.alterarCorDrawableMenuItem(getApplication(), R.drawable.ic_login))
                    .withTextColor(getResources().getColor(R.color.colorPrimary)));

        }

        this.navegationDrawerLeft.addItem(new PrimaryDrawerItem()
                .withIdentifier(MenuLateralProps.AJUDA.getmId())
                .withName(MenuLateralProps.AJUDA.getmNomeTela())
                .withIcon(CoresUtil.alterarCorDrawableMenuItem(getApplication(), R.drawable.ic_help))
                .withTextColor(getResources().getColor(R.color.colorPrimary)));

        this.navegationDrawerLeft.addItem(new PrimaryDrawerItem()
                .withIdentifier(MenuLateralProps.SAIR.getmId())
                .withName(MenuLateralProps.SAIR.getmNomeTela())
                .withIcon(CoresUtil.alterarCorDrawableMenuItem(getApplication(), R.drawable.icon_exit))
                .withTextColor(getResources().getColor(R.color.colorPrimary)));
        this.navegationDrawerLeft.setSelection(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void evetoClickItensMenuLateral(int identificador){
        switch (identificador) {
            case MenuLateralOpcoesProps.INICIAL:
                break;
            case MenuLateralOpcoesProps.DOAR_UM_AMIGO:
                startActivity(new Intent(MainActivity.this, CadastroAnimalActivity.class));
                break;
            case MenuLateralOpcoesProps.FAVORITOS:
                startActivity(new Intent(MainActivity.this, DesenvolvimentoActivity.class));
                break;
            case MenuLateralOpcoesProps.AJUDA:
                startActivity(new Intent(MainActivity.this, DesenvolvimentoActivity.class));
                break;
            case MenuLateralOpcoesProps.SAIR:
                showDialogoSair();
                break;
            case MenuLateralOpcoesProps.ENTRAR:
                startActivityForResult(new Intent(MainActivity.this, LoginActivity.class), LoginActivity.LOGIN_ID);
                break;
        }

    }

    private void showDialogoSair(){
        DialogInterface.OnClickListener lYesClick = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                LoginManager.getInstance().logOut();
                SharedPreferencesUtil.removerPreferencia(MainActivity.this, R.string.key_usuriao_logado);
                finish();
            }
        };

        DialogInterface.OnClickListener lNoClick = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        };
        DialogoUtil.dialogYesNo(MainActivity.this, "Deseja sair do aplicativo?", lYesClick, lNoClick).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case LoginActivity.LOGIN_ID:
                Usuario lUsuario = (Usuario) data.getExtras().get(getString(R.string.key_usuriao_logado));
                Drawable lDrawable = ImagemUtil.converterBase64(getResources(), lUsuario.getFoto());
                mProfileDrawerItem = new ProfileDrawerItem()
                        .withName(lUsuario.getNome())
                        .withIcon(lDrawable)
                        .withEmail(lUsuario.getEmail());
                int lPosicao = this.navegationDrawerLeft.getPositionFromIdentifier(MenuLateralOpcoesProps.ENTRAR);
                this.navegationDrawerLeft.removeItem(lPosicao);
                this.hearderNavegationLeft.removeProfile(0);
                this.hearderNavegationLeft.addProfiles(mProfileDrawerItem);
                this.navegationDrawerLeft.setSelection(0);
                break;
        }
    }
}
