package tech.leonam.entidade;

public class Dolar extends Moeda {
    public static final double CINCO_CENTAVOS = 0.05;
    public static final double DEZ_CENTAVOS = 0.10;
    public static final double VINTE_E_CINCO_CENTAVOS = 0.25;
    public static final double CINQUENTA_CENTAVOS = 0.50;
    public static final double UM_DOLAR = 1;
    @Override
    public double converter() {
        return getValor() * 5 ;
    }

    @Override
    public String info() {
        return String.format("$%.2f", getValor());
    }

    @Override
    public boolean moedaExiste(double moeda) {
        return moeda == CINCO_CENTAVOS || moeda == DEZ_CENTAVOS || moeda == VINTE_E_CINCO_CENTAVOS || moeda == CINQUENTA_CENTAVOS || moeda == UM_DOLAR;
    }
}
