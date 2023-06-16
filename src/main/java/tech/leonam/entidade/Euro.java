package tech.leonam.entidade;

public class Euro extends Moeda {
    public static final double UM_CENTAVOS = 0.01;
    public static final double DOIS_CENTAVOS = 0.02;
    public static final double CINCO_CENTAVOS = 0.05;
    public static final double DEZ_CENTAVOS = 0.10;
    public static final double VINTE_E_CINCO_CENTAVOS = 0.25;
    public static final double CINQUENTA_CENTAVOS = 0.50;
    public static final double UM_EURO = 1;
    public static final double DOIS_EUROS = 2;

    @Override
    public double converter() {
        return getValor() * 5.5;
    }

    @Override
    public String info() {
        return String.format("€%.2f", getValor());
    }

    @Override
    public boolean moedaExiste(double valor) {
        return valor == UM_CENTAVOS || valor == DOIS_CENTAVOS || valor == CINCO_CENTAVOS || valor == DEZ_CENTAVOS ||
                valor == VINTE_E_CINCO_CENTAVOS || valor == CINQUENTA_CENTAVOS || valor == UM_EURO || valor == DOIS_EUROS;
    }
}
