package com.example.calculoimc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.UUID;

import example.model.Pessoas;

public class MainActivity extends AppCompatActivity  {




    FirebaseDatabase database;
    DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        iniciarFirebase();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final CalculoImc calcularImc = new CalculoImc();

        final EditText editAltura = (EditText) findViewById(R.id.altura);

        final EditText editPeso = (EditText) findViewById(R.id.peso);

        final TextView resultado = (TextView) findViewById(R.id.tvResultado);

        final TextView descricao = (TextView) findViewById(R.id.tvDescricao);

        final EditText nome = (EditText) findViewById(R.id.nome);
        
        final TextView editImc = (TextView) findViewById(R.id.imc);

        final TextView pIdeal = (TextView) findViewById(R.id.tvPesoIdeal);

        final EditText editIdade = (EditText) findViewById(R.id.idade);


        final Spinner genero = (Spinner) findViewById(R.id.sexo);
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.generos, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genero.setAdapter(adapter);




        /*
        *
        *
        *
        *
        * */




        final Button salvar = (Button) findViewById(R.id.salvar);

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Pessoas pessoa = new Pessoas(UUID.randomUUID().toString(), genero.getSelectedItem().toString() ,nome.getText().toString(), Integer.parseInt(editIdade.getText().toString()), Integer.parseInt(editAltura.getText().toString()), Integer.parseInt(editPeso.getText().toString()));
                myRef.child("Pessoa").child(pessoa.getUid()).setValue(pessoa);

                Toast.makeText(MainActivity.this, "Os seus dados foram salvos", Toast.LENGTH_LONG).show();


            }
        });

















        genero.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    String escolha = parent.getItemAtPosition(position).toString();

                if (escolha.equals("Masculino")){
                    Button calculo = (Button) findViewById(R.id.botaoCalcular);
                    calculo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            double peso = Integer.parseInt(editPeso.getText().toString());
                            double altura = Double.parseDouble(editAltura.getText().toString()) / 100;
                            double imc = calcularImc.getImc(peso, altura);
                            double pesoIdeal = (72.7 * altura) - 58;

                            descricao.setText(calcularImc.getDecricao(imc));
                            resultado.setText("Caro, " +nome.getText() + " o seu indice de massa corporal é: ");
                            editImc.setText("" + imc);
                            pIdeal.setText("O seu peso ideal é: " + pesoIdeal);
                        }
                    });

                }else if (escolha.equals("Feminino")){
                    Button calculo = (Button) findViewById(R.id.botaoCalcular);

                    calculo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            double peso = Integer.parseInt(editPeso.getText().toString());
                            double altura = Double.parseDouble(editAltura.getText().toString()) / 100;
                            double imc =  calcularImc.getImc(peso, altura);
                            double pesoIdeal = (62.1 * altura) - 44.7;

                            resultado.setText(nome.getText() + "o seu índice de Massa corporal é: ");
                            editImc.setText("" + imc);
                            descricao.setText(calcularImc.getDecricao(imc));
                            pIdeal.setText("O seu peso ideal é: " + pesoIdeal);

                        }
                    });

                }else{
                    Button calculo = (Button) findViewById(R.id.botaoCalcular);
                    calculo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(MainActivity.this, "Selecione o seu gênero", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        final Button btReset = (Button) findViewById(R.id.reset);
        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editImc.setText(null);
                genero.setAdapter(adapter);
                editAltura.setText(null);
                editPeso.setText(null);
                resultado.setText(null);
                descricao.setText(null);
                editIdade.setText(null);
                nome.setText(null);
                pIdeal.setText(null);


            }
        });

    }

    public void iniciarFirebase(){
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
    }

}
