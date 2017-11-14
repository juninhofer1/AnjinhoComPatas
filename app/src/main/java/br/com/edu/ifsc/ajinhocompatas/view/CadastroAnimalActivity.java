package br.com.edu.ifsc.ajinhocompatas.view;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import br.com.edu.ifsc.anjinhocompatas.R;
import br.com.edu.ifsc.anjinhocompatas.vo.Animal;

/**
 * Created by keila on 06/11/2017.
 */

public class CadastroAnimalActivity extends AppCompatActivity {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.cadastro_animal);
        final Animal mAnimal = new Animal();

        final String[] racaCao = {"Indeterminada",
                "Bulldog Francês", "Bulldog Inglês",
                "Chow Chow",
                "Golden Retriever",
                "Labrador Retriever","Lulu da Pomerânia", "Lhasa Apso",
                "Poodle", "pug", "Pincher",
                "Rottweiler",
                "vira-lata",
                "Yorkshire Terrier"
        };
        String[] racaGato = {"Angorá",
                "Burmese", "British Shorthair",
                "Gato-de-bengaçla",
                "Himalaia",
                "Persa",
                "Ragdoll",
                "Siamês", "Siberiano", "Sphynx"

        };

        String [] tamanho = {"Pequeno", "Médio", "Grande"};
        String [] cor = {"Amarelado", "Branco", "Cinza", "Malhado", "Marrom", "Preto"};
        int[] idade = {1,2,3,4,5,6,7,8,9,10,11,12,13};

        //spinner cao  (contexto, telinha, Array que vai no spinner)
        ArrayAdapter<String> adapterCao = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, racaCao);
        adapterCao.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); //para definir o elemento view que vai aparecer o spinner, funciona sem tbm

        //cast dos itens da telinha cadastroAnimal
        EditText cadastroAnimalNome = (EditText) findViewById(R.id.cadastroAnimalNomeId);
        CheckBox checkBoxGato = (CheckBox) findViewById(R.id.checkBoxGatoId);
        CheckBox checkBoxCao = (CheckBox) findViewById(R.id.checkBoxCaoId);
        Spinner spinnerCadastroAnimalRaca = (Spinner) findViewById(R.id.spinnerCadastroAnimalRacaId);
        Spinner spinnerCadastroAnimalTamanho = (Spinner) findViewById(R.id.spinnerCadastroAnimalTamanhoId);
        Spinner spinnerCadastroAnimalCor = (Spinner) findViewById(R.id.spinnerCadastroAnimalCorId);
        Spinner spinnerCadastroAnimalIdade = (Spinner) findViewById(R.id.spinnerCadastroAnimalIdadeId);
        Button botaoEnviarImagem = (Button) findViewById(R.id.botaoEnviarImagemId);
        Button botaoCadastrarAnimal = (Button) findViewById(R.id.botaoCadastrarAnimalId);

        //seta o nome animal
        mAnimal.setNome(cadastroAnimalNome.getText().toString());

        //se for cão
        if (checkBoxCao.isChecked() ){

            //spinner da raça cão
            spinnerCadastroAnimalRaca.setAdapter(adapterCao);
            spinnerCadastroAnimalRaca.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    //seta a raça selecionada do spinner na raca
                    mAnimal.setRaca(racaCao[position]);
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


        //se for gato
        } else {

        }


    }

}
