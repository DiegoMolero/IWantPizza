package ipo2.uclm.iwantpizza;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import ipo2.uclm.iwantpizza.Comida.*;


public class AdaptadorListaPedido extends ArrayAdapter<Comida> {
    private Activity context;
    private UpdateControler updatecontroler;
    private ArrayList<Comida> arrayPedido;
    private ImageButton boton;
    private Pedido pedido;
    private ListView listViewPedido;
    public AdaptadorListaPedido(Activity context, Pedido pedido, UpdateControler updateControler) {
        super(context, R.layout.layout_item_pedido, pedido.getLista());
        this.context = context;
        this.arrayPedido = pedido.getLista();
        this.pedido = pedido;
        this.updatecontroler =updateControler;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View v = inflater.inflate(R.layout.layout_item_pedido, null);
        boton = (ImageButton) v.findViewById(R.id.btn_add);
        TextView lblComida = (TextView) v.findViewById(R.id.lblComida);
        lblComida.setText(arrayPedido.get(position).getClass().getSimpleName());
        TextView lblTipo = (TextView) v.findViewById(R.id.lblTipo);
        lblTipo.setText(arrayPedido.get(position).getTipo());
        TextView lblAmount = (TextView) v.findViewById(R.id.lblAmount);
        lblAmount.setText(Float.toString(arrayPedido.get(position).getAmount())+"€");
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pedido.removeComida(position);
                updatecontroler.update();
            }
        });
        return v;
    }
}
