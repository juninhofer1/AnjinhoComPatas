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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

import br.com.edu.ifsc.anjinhocompatas.R;
import br.com.edu.ifsc.anjinhocompatas.modelos.Animal;
import br.com.edu.ifsc.anjinhocompatas.modelos.Usuario;
import br.com.edu.ifsc.anjinhocompatas.utilitarios.DialogoUtil;
import br.com.edu.ifsc.anjinhocompatas.utilitarios.ImagemUtil;
import br.com.edu.ifsc.anjinhocompatas.utilitarios.SharedPreferencesUtil;

/**
 * Created by keila on 06/11/2017.
 */

public class CadastroAnimalActivity extends AppCompatActivity {

    private Bitmap imagemBitmapAnimalCadastro;
    private ImageView imageViewImagemCadastroAnimal;
    private Animal mAnimal;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_animal);

        mAnimal = new Animal();

        final String[] racaCao = {"Indeterminada",
                "Bulldog Francês", "Bulldog Inglês",
                "Chow Chow",
                "Golden Retriever",
                "Labrador Retriever", "Lulu da Pomerânia", "Lhasa Apso",
                "Poodle", "Pug", "Pincher",
                "Rottweiler",
                "Vira-lata",
                "Yorkshire Terrier"
        };
        final String[] racaGato = {"Indeterminada",
                "Angorá",
                "Burmese", "British Shorthair",
                "Gato-de-bengaçla",
                "Himalaia",
                "Persa",
                "Ragdoll",
                "Siamês", "Siberiano", "Sphynx"

        };

        final String[] tamanho = {"Pequeno", "Médio", "Grande"};
        final String[] cor = {"Amarelado", "Branco", "Cinza", "Malhado", "Marrom", "Preto"};
        final Integer[] idade = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

        //spinner cao  (contexto, telinha, Array que vai no spinner)
        final ArrayAdapter<String> adapterCao = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, racaCao);
        adapterCao.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); //para definir o elemento view que vai aparecer o spinner, funciona sem tbm

        //spinner gato  (contexto, telinha, Array que vai no spinner)
        final ArrayAdapter<String> adapterGato = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, racaGato);
        adapterGato.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); //para definir o elemento view que vai aparecer o spinner, funciona sem tbm

        //spinner tamanho animal  (contexto, telinha, Array que vai no spinner)
        final ArrayAdapter<String> adapterTamanho = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, tamanho);
        adapterTamanho.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); //para definir o elemento view que vai aparecer o spinner, funciona sem tbm

        //spinner cor animal  (contexto, telinha, Array que vai no spinner)
        final ArrayAdapter<String> adapterCor = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, cor);
        adapterCor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); //para definir o elemento view que vai aparecer o spinner, funciona sem tbm

        //spinner cor animal  (contexto, telinha, Array que vai no spinner)
        final ArrayAdapter<Integer> adapterIdade = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item, idade);
        adapterIdade.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); //para definir o elemento view que vai aparecer o spinner, funciona sem tbm


        //cast dos itens da telinha cadastroAnimal
        final EditText cadastroAnimalNome = (EditText) findViewById(R.id.cadastroAnimalNomeId);
        final CheckBox checkBoxGato = (CheckBox) findViewById(R.id.checkBoxGatoId);
        final CheckBox checkBoxCao = (CheckBox) findViewById(R.id.checkBoxCaoId);
        final Spinner spinnerCadastroAnimalRaca = (Spinner) findViewById(R.id.spinnerCadastroAnimalRacaId);
        final Spinner spinnerCadastroAnimalTamanho = (Spinner) findViewById(R.id.spinnerCadastroAnimalTamanhoId);
        Spinner spinnerCadastroAnimalCor = (Spinner) findViewById(R.id.spinnerCadastroAnimalCorId);
        Spinner spinnerCadastroAnimalIdade = (Spinner) findViewById(R.id.spinnerCadastroAnimalIdadeId);
        imageViewImagemCadastroAnimal = (ImageView) findViewById(R.id.imageViewCadastroAnimalId);
        Button botaoCadastrarAnimal = (Button) findViewById(R.id.botaoCadastrarAnimalId);


        //quando clica no check box cão
        checkBoxCao.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBoxCao.isChecked()) {
                    //spinner da raça (cão)
                    spinnerCadastroAnimalRaca.setAdapter(adapterCao);
                    spinnerCadastroAnimalRaca.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            //seta a raça selecionada do spinner na raca
                            mAnimal.setRaca(racaCao[position]);
                            mAnimal.setTipoAnimal("cao");
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            Toast.makeText(getApplicationContext(), "Informe qual o tipo de animal!", Toast.LENGTH_LONG).show();
                        }

                    });
                    checkBoxGato.setChecked(false);//para desmarcar o check box gato, assim fica só um check box selecionado
                }
            }
        });


        //quando clica no check box gato
        checkBoxGato.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBoxGato.isChecked()) {
                    //spinner da raça (Gato)
                    spinnerCadastroAnimalRaca.setAdapter(adapterGato);
                    spinnerCadastroAnimalRaca.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            //seta a raça selecionada do spinner na raca
                            mAnimal.setRaca(racaGato[position]);
                            mAnimal.setTipoAnimal("gato");
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            //Toast.makeText(getApplicationContext(), "Informe qual o tipo de animal!", Toast.LENGTH_LONG).show();
                        }
                    });
                    checkBoxCao.setChecked(false); //para desmarcar o check box cao, assim fica só um check box selecionado
                }
            }
        });

        //spinner tamanho animal
        spinnerCadastroAnimalTamanho.setAdapter(adapterTamanho);
        spinnerCadastroAnimalTamanho.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mAnimal.setTamanho(tamanho[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //spinner cor animal
        spinnerCadastroAnimalCor.setAdapter(adapterCor);
        spinnerCadastroAnimalCor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mAnimal.setCor(cor[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //spinner idade animal
        spinnerCadastroAnimalIdade.setAdapter(adapterIdade);
        spinnerCadastroAnimalIdade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mAnimal.setIdade(idade[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        imageViewImagemCadastroAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventoDeEscolherFormaDePegarImagemAnimalCadastro();
            }
        });


        //botão cadastrar
        botaoCadastrarAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //seta o nome animal
                mAnimal.setNome(cadastroAnimalNome.getText().toString());

                //se não tiver nenhum nome no animal
                if (cadastroAnimalNome.toString().isEmpty()) {
                    if (checkBoxCao.isChecked())
                        Toast.makeText(getApplicationContext(), "Adicione um nome para o cão", Toast.LENGTH_SHORT).show();
                    if (checkBoxGato.isChecked())
                        Toast.makeText(getApplicationContext(), "Adicione um nome para o gato", Toast.LENGTH_SHORT).show();
                } else {
                    cadastrar();
                }
            }
        });
    }


    private void cadastrar(){
        String lEmail = SharedPreferencesUtil.lerPreferenciaString(CadastroAnimalActivity.this, R.string.key_usuriao_logado);
        Usuario.carregarPorEmail(CadastroAnimalActivity.this, lEmail);
        mAnimal.setIdDoador(Usuario.carregarPorEmail(CadastroAnimalActivity.this, lEmail).getId());
        if(Animal.savarAnimalBaseDados(CadastroAnimalActivity.this, mAnimal)){
            Toast.makeText(CadastroAnimalActivity.this, R.string.msm_cadastro_sucesso, Toast.LENGTH_LONG).show();
            finish();
        } else {
            Toast.makeText(CadastroAnimalActivity.this, R.string.msm_cadastro_erro, Toast.LENGTH_LONG).show();
        }
    }


    private void eventoDeEscolherFormaDePegarImagemAnimalCadastro() {

        DialogInterface.OnClickListener onClickListenerCamera = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                //                2 = chave
                startActivityForResult(cameraIntent, 2);
            }
        };

        DialogInterface.OnClickListener onClickListenerGaleria = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                //                1 = chave
                startActivityForResult(intent, 1);
            }
        };

        DialogoUtil.dialogCamera(CadastroAnimalActivity.this, "Escolha uma opção para carregar a foto do animal", onClickListenerGaleria , onClickListenerCamera).show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 2 && resultCode == Activity.RESULT_OK) {
            imagemBitmapAnimalCadastro = (Bitmap) data.getExtras().get("data");
            imageViewImagemCadastroAnimal.setImageBitmap(imagemBitmapAnimalCadastro);
            mAnimal.setFoto(ImagemUtil.converter(imagemBitmapAnimalCadastro));
        } else if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            Uri imagemUriAnimalCadastro = data.getData();
            InputStream imagemStreamAnimalCadastro = null;
            try {
                imagemStreamAnimalCadastro = getContentResolver().openInputStream(imagemUriAnimalCadastro);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            imagemBitmapAnimalCadastro = BitmapFactory.decodeStream(imagemStreamAnimalCadastro);
            mAnimal.setFoto(ImagemUtil.converter(imagemBitmapAnimalCadastro));
            imageViewImagemCadastroAnimal.setImageBitmap(imagemBitmapAnimalCadastro);
        }
    }
    }




