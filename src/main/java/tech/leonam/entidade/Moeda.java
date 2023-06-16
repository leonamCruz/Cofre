package tech.leonam.entidade;

public abstract class Moeda {
    private double valor;
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    public abstract double converter();
    public abstract String info();
    public abstract boolean moedaExiste(double valor);
}
