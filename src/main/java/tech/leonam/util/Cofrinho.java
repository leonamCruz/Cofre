package tech.leonam.util;

import tech.leonam.entidade.Moeda;

import java.util.ArrayList;

public class Cofrinho {
    private final ArrayList<Moeda> moedas = new ArrayList<>();
    public void adicionarMoeda(Moeda moeda){
        moedas.add(moeda);
    }
    public void removerMoeda(int posicao){
        moedas.remove(posicao);
    }
    public ArrayList<Moeda> listagemMoedas(){
        return moedas;
    }
}
