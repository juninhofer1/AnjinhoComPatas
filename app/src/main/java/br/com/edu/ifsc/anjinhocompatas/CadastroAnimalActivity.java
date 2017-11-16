package br.com.edu.ifsc.anjinhocompatas;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import br.com.edu.ifsc.anjinhocompatas.modelos.Animal;

/**
 * Created by keila on 06/11/2017.
 */

public class CadastroAnimalActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_animal);

        final Animal mAnimal = new Animal();

        final String[] racaCao = {"Indeterminada",
                "Bulldog Francês", "Bulldog Inglês",
                "Chow Chow",
                "Golden Retriever",
                "Labrador Retriever","Lulu da Pomerânia", "Lhasa Apso",
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
        final Integer[] idade = {1,2,3,4,5,6,7,8,9,10,11,12,13};

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
        Button botaoEnviarImagem = (Button) findViewById(R.id.botaoEnviarImagemId);
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
                if(checkBoxGato.isChecked()){
                    //spinner da raça (Gato)
                    spinnerCadastroAnimalRaca.setAdapter(adapterGato);
                    spinnerCadastroAnimalRaca.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            //seta a raça selecionada do spinner na raca
                            mAnimal.setRaca(racaGato[position]);
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


        //botão cadastrar
        botaoCadastrarAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //seta o nome animal
                mAnimal.setNome(cadastroAnimalNome.getText().toString());

                //se não tiver nenhum nome no animal
                if(cadastroAnimalNome.toString().isEmpty()) {
                    if (checkBoxCao.isChecked())
                        Toast.makeText(getApplicationContext(), "Adicione um nome para o cão", Toast.LENGTH_SHORT).show();
                    if (checkBoxGato.isChecked())
                        Toast.makeText(getApplicationContext(), "Adicione um nome para o cão", Toast.LENGTH_SHORT).show();
                }
            }
        });








    }
 }
