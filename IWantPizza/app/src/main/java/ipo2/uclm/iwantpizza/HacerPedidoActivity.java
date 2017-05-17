package ipo2.uclm.iwantpizza;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

import ipo2.uclm.iwantpizza.Comida.*;
import ipo2.uclm.iwantpizza.Database.DatabaseAccess;

public class HacerPedidoActivity extends AppCompatActivity {
    private Pedido pedido;
    private Button btn_comprar;
    private ListView lstpedidoView;
    private ListView lstcomidaView;
    private AdaptadorListaPedido adaptadorListaPedido;
    private AdaptadorListaComida adaptadorListaComidaBebida;
    private AdaptadorListaComida adaptadorListaComidaPizza;
    private ArrayList<Comida> arrayPizzas;
    private ArrayList<Comida> arrayBebidas;
    private TextView textCantidad;
    private TextView textList;
    private TextView textEmail;
    private Float cantidad;
    private UpdateControler updateControler;
    private DatabaseAccess databaseAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_hacer_pedido);
        pedido = new Pedido();

        databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();

        arrayPizzas = databaseAccess.getListPizzas();
        arrayBebidas = databaseAccess.getListBebidas();

        lstpedidoView = (ListView) findViewById(R.id.list_pedido);
        lstcomidaView = (ListView) findViewById(R.id.list_comida);
        textCantidad = (TextView) findViewById(R.id.textCantidad);
        textList = (TextView) findViewById(R.id.textList);
        textEmail = (TextView) findViewById(R.id.textUser);

        textList.setText("Pizzas");
        textEmail.setText(getIntent().getStringExtra("email"));

        updateControler = new UpdateControler(pedido,lstpedidoView,lstcomidaView,cantidad,textCantidad);
        adaptadorListaPedido = new AdaptadorListaPedido(this,pedido, updateControler);
        adaptadorListaComidaPizza = new AdaptadorListaComida(this,pedido, arrayPizzas, updateControler);
        adaptadorListaComidaBebida = new AdaptadorListaComida(this,pedido, arrayBebidas, updateControler);
        lstpedidoView.setAdapter(adaptadorListaPedido);
        lstcomidaView.setAdapter(adaptadorListaComidaPizza);

    }

    public void onClickPizzas(View v) {
        lstcomidaView.setAdapter(adaptadorListaComidaPizza);
        textList.setText("Pizzas");
        updateControler.update();
    }
    public void onClickBebidas(View v) {
        lstcomidaView.setAdapter(adaptadorListaComidaBebida);
        textList.setText("Bebidas");
        updateControler.update();
    }
    public void onClickComprar(View v) {
        if(pedido.getLista().isEmpty()){
            Toast toast = Toast.makeText(this, "No hay comida seleccionada", Toast.LENGTH_LONG);
            toast.show();
        }
        else{
            databaseAccess.close();
            finish();

        }

    }


}
