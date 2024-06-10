package empresa;

import java.util.ArrayList;

import pedido.Pedido;
import pedido.Venta;

public class Tutta {
	private ArrayList <Pedido> pedidos;
	private ArrayList <Venta> ventas;
	
	
	public Tutta(ArrayList <Pedido> pedidos, ArrayList <Venta> ventas) {
		this.pedidos = new ArrayList <Pedido> ();
		this.ventas = new ArrayList <Venta> ();
	}


	public ArrayList<Pedido> getPedidos() {
		return pedidos;
	}


	public void setPedidos(ArrayList<Pedido> pedidos) {
		this.pedidos = pedidos;
	}


	public ArrayList<Venta> getVentas() {
		return ventas;
	}


	public void setVentas(ArrayList<Venta> ventas) {
		this.ventas = ventas;
	}
	
	
	
}

