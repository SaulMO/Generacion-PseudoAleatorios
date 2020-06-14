package sample;

import java.util.ArrayList;

public class NumerosPseudoA
{
    ArrayList<Float> numerosPseudoAleatorios;

    NumerosPseudoA(){
        numerosPseudoAleatorios = new ArrayList<>();
    }

    public void addNumero(float numeroPseudoA){
        numerosPseudoAleatorios.add(numeroPseudoA);
    }

    public ArrayList<Float> getNumerosPseudoAleatorios(){return numerosPseudoAleatorios;}
}