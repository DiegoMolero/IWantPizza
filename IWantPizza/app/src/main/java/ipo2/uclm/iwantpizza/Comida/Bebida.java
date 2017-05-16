package ipo2.uclm.iwantpizza.Comida;

/**
 * Created by User on 20/04/2017.
 */

public class Bebida extends Comida{

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    @Override
    public String getTipo() {
        return tipo;
    }

    @Override
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Bebida(String tipo, Float amount) {
        super(tipo,amount);
    }
}
