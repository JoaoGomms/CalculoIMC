package com.example.calculoimc;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CalculoImc {


    FirebaseDatabase database;
    DatabaseReference myRef;

    public void iniciarFirebase(){
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
    }


    public double getImc(Double peso, Double altura){
    Double imc=peso/(altura*altura);
    return imc;
}

public String getDecricao(Double imc){
    if(imc<18.5) {
        return "Abaixo do peso";
    }else{
        if(imc <25){
            return "Peso adequado";
        }else{
            if(imc <30){
                return "Sobrepeso";
            }else{
                if(imc<35){
                    return "Obesidade";
                }else{
                    if (imc<40){
                        return "Obesidade nível 2";
                    }else{
                        return "Obesidade nível 3";
                    }
                }
            }
        }

    }
    }
}
