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

import ipo2.uclm.iwantpizza.Comida.Comida;
import ipo2.uclm.iwantpizza.Comida.Pedido;


public class AdaptadorListaComida extends ArrayAdapter<Comida> {
    private UpdateControler updatecontroler;
    private Activity context;
    private ArrayList<Comida> comidas;
    private ImageButton boton;
    private Pedido pedido;
    private ListView listPedidoView;
    public AdaptadorListaComida(Activity context, Pedido pedido,ArrayList<Comida> comidas, UpdateControler updateControler) {
        super(context, R.layout.layout_item_comida, comidas);
        this.context = context;
        this.comidas = comidas;
        this.pedido = pedido;
        this.updatecontroler =updateControler;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View v = inflater.inflate(R.layout.layout_item_comida, null);
        boton = (ImageButton) v.findViewById(R.id.btn_add);
        TextView lblComida = (TextView) v.findViewById(R.id.lblComida);
        lblComida.setText(comidas.get(position).getTipo());
        TextView lblTipo = (TextView) v.findViewById(R.id.lblTipo);
        lblTipo.setText(Float.toString(comidas.get(position).getAmount())+"€");
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pedido.addComida(comidas.get(position));
                updatecontroler.update();
            }
        });
        return v;
    }


    public ArrayList<Comida> getComidas() {
        return comidas;
    }

    public void setComidas(ArrayList<Comida> comidas) {
        this.comidas = comidas;
    }
}
