package tech.leonam.entidade;

public class Real extends Moeda{
    public static final double CINCO_CENTAVOS = 0.05;
    public static final double DEZ_CENTAVOS = 0.10;
    public static final double VINTE_E_CINCO_CENTAVOS = 0.25;
    public static final double CINQUENTA_CENTAVOS = 0.50;
    public static final double UM_REAL = 1;

    @Override
    public double converter() {
        return getValor();
    }

    @Override
    public String info() {
        return String.format("R$%.2f",getValor());
    }
    public boolean moedaExiste(double moeda){
        return moeda == CINCO_CENTAVOS || moeda == DEZ_CENTAVOS || moeda == VINTE_E_CINCO_CENTAVOS || moeda == CINQUENTA_CENTAVOS || moeda == UM_REAL;
    }
}