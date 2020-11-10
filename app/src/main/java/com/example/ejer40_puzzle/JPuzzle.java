package com.example.ejer40_puzzle;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class JPuzzle {

    /***********************************************************************************************
     Atributos
     **********************************************************************************************/

    private final String TAG = "Ejer_40";
    private ArrayList<Integer> casillas;
    private int posAuxiliar;
    private int casillasCorrectas;

    /***********************************************************************************************
     Propiedades
     **********************************************************************************************/

    public ArrayList<Integer> getCasillas() {
        return casillas;
    }

    public int getCasillasCorrectas(){
        posicionesCorrectas();
        return casillasCorrectas;
    }

    /***********************************************************************************************
     Constructor
     **********************************************************************************************/

    public JPuzzle() {

        casillas = new ArrayList<>();
        for (int i = 0; i <= 8; i++) {
            casillas.add(i);
        }
        Collections.shuffle(casillas);
    }

    /***********************************************************************************************
     Funciones
     **********************************************************************************************/

    // --------------------------------------------------------------------------------------------
    // cambiaPosiciones() - Intercambia las posiciones en el ArrayList después del 2º click en las casillas
    // --------------------------------------------------------------------------------------------
    public void cambiaPosiciones(int posicionClicada1, int posicionClicada2) {

        posAuxiliar = casillas.get(posicionClicada1);
        casillas.set(posicionClicada1, casillas.get(posicionClicada2));
        casillas.set(posicionClicada2, posAuxiliar);

    }

    // --------------------------------------------------------------------------------------------
    // isOrdenada() - Comprueba si hemos completado el puzzle
    // --------------------------------------------------------------------------------------------

    public boolean isOrdenada() {
        boolean ordenada = true;
        for (int i = 1; i < casillas.size(); i++) {
            if (casillas.get(i - 1).compareTo(casillas.get(i)) > 0) ordenada = false;
        }
        return ordenada;
    }

    // --------------------------------------------------------------------------------------------
    // posicionesCorrectas() - Cuenta las posiciones correctas
    // --------------------------------------------------------------------------------------------

    public int posicionesCorrectas(){
        casillasCorrectas=0;
        for (int i = 0; i <= 8; i++){
            if (casillas.get(i)==i){
                casillasCorrectas++;
            }
        }
        return casillasCorrectas;
    }


}
