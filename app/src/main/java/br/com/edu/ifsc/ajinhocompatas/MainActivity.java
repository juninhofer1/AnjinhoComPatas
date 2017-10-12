package br.com.edu.ifsc.ajinhocompatas;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.SwitchDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.List;

import br.com.edu.ifsc.ajinhocompatas.dao.implementacao.UsuarioDao;
import br.com.edu.ifsc.ajinhocompatas.props.ActivityProps;
import br.com.edu.ifsc.ajinhocompatas.utilitarios.ColorUtil;
import br.com.edu.ifsc.ajinhocompatas.utilitarios.DialogUtil;
import br.com.edu.ifsc.ajinhocompatas.view.TesteActivity;
import br.com.edu.ifsc.ajinhocompatas.vo.Usuario;

public class MainActivity extends AppCompatActivity {

//  TODO esboço de programa para testes, será feito um novo projeto....
    private Drawer.Result navegationDrawerLeft;
    private AccountHeader.Result hearderNavegationLeft;
    private Toolbar toolbar;

    private EditText mCaixaTexto;
    private Button mBotaoIdade;
    private TextView mResultadoIdade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setLogo(R.drawable.icon_app_s);
        getSupportActionBar().setTitle(null);

        //TODO - Keila - aplicativo idade cachorro e gato.
        mCaixaTexto = (EditText) findViewById(R.id.caixaTextoId);
        mBotaoIdade = (Button) findViewById(R.id.botaoIdadeId);
        mResultadoIdade = (TextView) findViewById(R.id.resultadoIdadeId);

        mBotaoIdade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //recuperar o que foi digitado
                //toString converte para string
                String textoDigitado = mCaixaTexto.getText().toString();

                //isEmpty verifica se essa String está vazia
                if( textoDigitado.isEmpty() ){
                    //string vazia
                    Snackbar.make(view, "Nenhum número digitado!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }else{
                    //converte a string com a idade do cachorro para int
                    int valorDigitado = Integer.parseInt(textoDigitado);
                    int resultadoFinal= valorDigitado * 7;

                    mResultadoIdade.setText("A idade do cachorro em anos humanos é: " + resultadoFinal + " anos");
                }
            }
        });

//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        criarMenuLateral();
        //Inserindo o primeiro registro no banco, só descomentar :D
//        utilizandoBancoDeDados();
    }

    private void criarMenuLateral(){
        this.hearderNavegationLeft = new AccountHeader()
                .withActivity(this)
                .withCompactStyle(false)
                .withHeaderBackground(R.color.colorPrimary)
                .withThreeSmallProfileImages(false)
                .withSelectionListEnabledForSingleProfile(false)
                .addProfiles(new ProfileDrawerItem()
                        .withName("Usuário")
                        .withEmail(getResources().getString(R.string.app_name)))
                .build();

        this.navegationDrawerLeft = new Drawer()
                .withActivity(this)
                .withToolbar(this.toolbar)
                .withDisplayBelowToolbar(false)
                .withActionBarDrawerToggle(true)
                .withDrawerGravity(Gravity.LEFT)
                .withTranslucentStatusBar(false)
                .withSelectedItem(-1)
                .withAccountHeader(hearderNavegationLeft)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                        if (drawerItem.getIdentifier() == ActivityProps.SAIR.getmId()) {
                            showDialogoSair();
                        } else if(drawerItem.getIdentifier() != ActivityProps.INICIAL.getmId()){
                            startActivity(new Intent(MainActivity.this, TesteActivity.class));
                        }
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
                .withIdentifier(ActivityProps.INICIAL.getmId())
                .withName(ActivityProps.INICIAL.getmNomeTela())
                .withIcon(ColorUtil.alterarCorDrawableMenuItem(getApplication(), R.drawable.icon_home))
                .withTextColor(getResources().getColor(R.color.colorPrimary)));

        this.navegationDrawerLeft.addItem(new PrimaryDrawerItem()
                .withIdentifier(ActivityProps.FAVORITOS.getmId())
                .withName(ActivityProps.FAVORITOS.getmNomeTela())
                .withIcon(ColorUtil.alterarCorDrawableMenuItem(getApplication(), R.drawable.ic_favorite))
                .withTextColor(getResources().getColor(R.color.colorPrimary)));

        this.navegationDrawerLeft.addItem(new PrimaryDrawerItem()
                .withIdentifier(ActivityProps.ECONTRE_UM_AMIGO.getmId())
                .withName(ActivityProps.ECONTRE_UM_AMIGO.getmNomeTela())
                .withIcon(ColorUtil.alterarCorDrawableMenuItem(getApplication(), R.drawable.icon_search))
                .withTextColor(getResources().getColor(R.color.colorPrimary)));

        this.navegationDrawerLeft.addItem(new DividerDrawerItem());

        this.navegationDrawerLeft.addItem(new PrimaryDrawerItem()
                .withIdentifier(ActivityProps.AJUDA.getmId())
                .withName(ActivityProps.AJUDA.getmNomeTela())
                .withIcon(ColorUtil.alterarCorDrawableMenuItem(getApplication(), R.drawable.ic_help))
                .withTextColor(getResources().getColor(R.color.colorPrimary)));

        this.navegationDrawerLeft.addItem(new PrimaryDrawerItem()
                .withIdentifier(ActivityProps.SAIR.getmId())
                .withName(ActivityProps.AJUDA.getmNomeTela())
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

//    private void evetoClickItensMenuLateral(int identificador){
//        switch (identificador) {
//            case ActivityProps.INICIAL.getmId():
//                case;
//        }
//
//    }

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
