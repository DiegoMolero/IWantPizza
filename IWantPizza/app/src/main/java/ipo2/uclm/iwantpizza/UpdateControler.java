package ipo2.uclm.iwantpizza;

import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import ipo2.uclm.iwantpizza.Comida.Comida;
import ipo2.uclm.iwantpizza.Comida.Pedido;

/**
 * Created by User on 16/05/2017.
 */

public class UpdateControler {
    private Pedido pedido;
    private ListView lstpedidoView;
    private ListView lstcomidaView;
    private Float cantidad;
    private TextView textCantidad;

    public UpdateControler(Pedido pedido, ListView lstpedidoView, ListView lstcomidaView, Float cantidad, TextView textCantidad) {
        this.pedido = pedido;
        this.lstpedidoView = lstpedidoView;
        this.lstcomidaView = lstcomidaView;
        this.cantidad = cantidad;
        this.textCantidad = textCantidad;
    }

    public void update(){
        updatePrecio();
        ((BaseAdapter) lstpedidoView.getAdapter()).notifyDataSetChanged();
        ((BaseAdapter) lstcomidaView.getAdapter()).notifyDataSetChanged();
    }

    public void calcularCantidad(){
        float aux= (float) 0;
        for (Comida comida : pedido.getLista())
        {
            aux+= comida.getAmount();
        }
        cantidad=aux;
    }

    public void updatePrecio(){
        calcularCantidad();
        String base="Precio Total: ";
        String euro= "€";
        textCantidad.setText(base+cantidad.toString()+euro);
    }
}
