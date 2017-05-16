package ipo2.uclm.iwantpizza.Comida;

/**
 * Created by User on 20/04/2017.
 */

public class Comida {
    protected String tipo;
    protected Float amount;

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Comida(String tipo, Float amount) {
        this.amount = amount;
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
