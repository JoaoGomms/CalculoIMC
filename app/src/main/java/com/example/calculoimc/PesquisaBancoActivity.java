package com.example.calculoimc;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import example.model.Pessoas;

public class PesquisaBancoActivity extends AppCompatActivity {


    private DatabaseReference databaseReference;
    private PesquisaBancoActivity<Pessoas> listaPessoas = new ArrayList<Pessoas>();
    private ArrayAdapter<Pessoas> arrayAdapter;
    private EditText nomePesquisa
    private ListView listView;
    private FirebaseDatabase firebaseDatabase


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa_banco);

        nomePesquisa = findViewById(R.id.idNomePesquisa);
        listView = findViewById(R.id.idListaPesquisa);

        iniciarFirebase();
        eventoEdit();
    }


    public void iniciarFirebase(){
        FirebaseApp.initializeApp(PesquisaBancoActivity.this);
        firebaseDatabase = firebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

  public void eventoEdit(){
        nomePesquisa.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String palavra = nomePesquisa.getText().toString();
                pesquisarPalavra(palavra);
            }
        });
  }


  private void pesquisarPalavra(String palavra){
        Query query;
        if (palavra.equals("")){
            query = databaseReference.child("Pessoas").orderByChild("nome");


        }else{
            query = databaseReference.child("Pessoas").orderByChild("nome").startAt(palavra).endAt();
        }
    query.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            for (DataSnapshot objSnap: dataSnapshot.getChildren()){
                Pessoas p = objSnap.getValue(Pessoas.class);
            }
            arrayAdapter = new ArrayAdapter<Pessoas>(PesquisaBancoActivity.this, android.R.layout.simple_list_item_1, listaPessoas;
            listView.setAdapter(arrayAdapter);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });


  }

}
