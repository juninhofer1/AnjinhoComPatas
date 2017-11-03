package br.com.edu.ifsc.ajinhocompatas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Toast;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.List;

import br.com.edu.ifsc.ajinhocompatas.dao.implementacao.UsuarioDao;
import br.com.edu.ifsc.ajinhocompatas.props.MenuLateralOpcoesProps;
import br.com.edu.ifsc.ajinhocompatas.props.MenuLateralProps;
import br.com.edu.ifsc.ajinhocompatas.utilitarios.ColorUtil;
import br.com.edu.ifsc.ajinhocompatas.utilitarios.DialogUtil;
import br.com.edu.ifsc.ajinhocompatas.view.Animal_detalhes;
import br.com.edu.ifsc.ajinhocompatas.view.DesenvolvimentoActivity;
import br.com.edu.ifsc.ajinhocompatas.view.LoginActivity;
import br.com.edu.ifsc.ajinhocompatas.view.adapter.ViewPagerAdapter;
import br.com.edu.ifsc.ajinhocompatas.view.fragment.FragmentAnimais;
import br.com.edu.ifsc.ajinhocompatas.vo.Usuario;

public class MainActivity extends AppCompatActivity {

    private Drawer.Result navegationDrawerLeft;
    private AccountHeader.Result hearderNavegationLeft;
    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Chama o método de inicialização dos componentes
        //dentro desse método pode ter varios outros ou só as referencias dos componentes.
        this.inicializarComponentes();

        //Inserindo o primeiro registro no banco, só descomentar :D
//        utilizandoBancoDeDados();


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

        Bundle bundleDog = new Bundle();
        //false cão;
        bundleDog.putBoolean("animalzinho", false);

        Bundle bundleCat = new Bundle();
        //true gato;
        bundleCat.putBoolean("animalzinho", true);

        FragmentAnimais fragmentCao = new FragmentAnimais();
        fragmentCao.setArguments(bundleDog);

        FragmentAnimais fragmentGato = new FragmentAnimais();
        fragmentGato.setArguments(bundleCat);

        ViewPagerAdapter lViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        lViewPagerAdapter.addFragment(fragmentCao, "Cães");
        lViewPagerAdapter.addFragment(fragmentGato, "Gatos");
        aViewPager.setAdapter(lViewPagerAdapter);
        this.mTabLayout.setupWithViewPager(aViewPager);
    }

    private void criarMenuLateral(){
        this.hearderNavegationLeft = new AccountHeader()
                .withActivity(this)
                .withCompactStyle(false)
                .withHeaderBackground(R.color.primary)
                .withThreeSmallProfileImages(false)
                .withSelectionListEnabledForSingleProfile(false)
//                .withHeaderBackground(R.drawable.header)
                .addProfiles(new ProfileDrawerItem()
                        .withName("Usuário")
                        //.withIcon(getResources().getDrawable(R.mipmap.ic_usuario_dog))
                        .withEmail(getResources().getString(R.string.app_name)))
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
                .withIcon(ColorUtil.alterarCorDrawableMenuItem(getApplication(), R.drawable.icon_home))
                .withTextColor(getResources().getColor(R.color.colorPrimary)));

        this.navegationDrawerLeft.addItem(new PrimaryDrawerItem()
                .withIdentifier(MenuLateralProps.FAVORITOS.getmId())
                .withName(MenuLateralProps.FAVORITOS.getmNomeTela())
                .withIcon(ColorUtil.alterarCorDrawableMenuItem(getApplication(), R.drawable.ic_favorite))
                .withTextColor(getResources().getColor(R.color.colorPrimary)));

        this.navegationDrawerLeft.addItem(new PrimaryDrawerItem()
                .withIdentifier(MenuLateralProps.ECONTRE_UM_AMIGO.getmId())
                .withName(MenuLateralProps.ECONTRE_UM_AMIGO.getmNomeTela())
                .withIcon(ColorUtil.alterarCorDrawableMenuItem(getApplication(), R.drawable.icon_search))
                .withTextColor(getResources().getColor(R.color.colorPrimary)));

        this.navegationDrawerLeft.addItem(new DividerDrawerItem());

        this.navegationDrawerLeft.addItem(new PrimaryDrawerItem()
                .withIdentifier(MenuLateralProps.Entrar.getmId())
                .withName(MenuLateralProps.Entrar.getmNomeTela())
                .withIcon(ColorUtil.alterarCorDrawableMenuItem(getApplication(), R.drawable.ic_login))
                .withTextColor(getResources().getColor(R.color.colorPrimary)));

        this.navegationDrawerLeft.addItem(new PrimaryDrawerItem()
                .withIdentifier(MenuLateralProps.AJUDA.getmId())
                .withName(MenuLateralProps.AJUDA.getmNomeTela())
                .withIcon(ColorUtil.alterarCorDrawableMenuItem(getApplication(), R.drawable.ic_help))
                .withTextColor(getResources().getColor(R.color.colorPrimary)));

        this.navegationDrawerLeft.addItem(new PrimaryDrawerItem()
                .withIdentifier(MenuLateralProps.SAIR.getmId())
                .withName(MenuLateralProps.SAIR.getmNomeTela())
                .withIcon(ColorUtil.alterarCorDrawableMenuItem(getApplication(), R.drawable.icon_exit))
                .withTextColor(getResources().getColor(R.color.colorPrimary)));
        navegationDrawerLeft.setSelection(0);
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
            case MenuLateralOpcoesProps.ECONTRE_UM_AMIGO:
                startActivity(new Intent(MainActivity.this, DesenvolvimentoActivity.class));
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
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                break;
        }

    }

    private void showDialogoSair(){
        DialogInterface.OnClickListener lYesClick = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        };

        DialogInterface.OnClickListener lNoClick = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        };
        DialogUtil.dialogYesNo(MainActivity.this, "Deseja sair do aplicativo?", lYesClick, lNoClick).show();
    }

    private void utilizandoBancoDeDados() {
        UsuarioDao usuarioDao = new UsuarioDao(MainActivity.this);
        //Necessário o método open() para abrir o banco de dados, para poder fazer as operações de salvar, alterar, deletar, consultar
        usuarioDao.open();

        // Cria usuário para teste
        Usuario lUsuario = new Usuario();
        lUsuario.setIdade(21);
        lUsuario.setNome("Mahala Hala (Pipoca doce)");
        lUsuario.setEndereco("Rua Mahala hala hala");
        lUsuario.setEmail("mahala@gmail.com");

        //Após criar o objeto do usuário vamos inserilo em nosso banco de dados
        usuarioDao.salvar(lUsuario);

        List<Usuario> usuarios = usuarioDao.carregarTodosOsUruarios();
        if(usuarioDao != null)
            for (Usuario usuario : usuarios) {
                Toast.makeText(MainActivity.this, usuario.getNome(), Toast.LENGTH_SHORT).show();
            }
    }
}
