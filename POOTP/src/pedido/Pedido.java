package pedido;

import java.util.ArrayList;

import Autopartes.Autoparte;
import usuario.Usuario;

public class Pedido {

	Usuario cliente;
	int idPedido;
	int cantArticulos;
	String fecha;
	ArrayList <Autoparte> pedido;
	
	public void registrarPedido(Pedido p) {
		for (Autoparte autoparte : p.pedido) {
			if(autoparte.getStock() >= 1) {
				//Hay que definir como se colocarán las autopartes para el pedido.
				autoparte.setStock(autoparte.getStock()-1);
				pedido.add(autoparte);
			}else {
				//Esto debe estar contemplado en el catálogo
				System.out.println("No hay stock suficiente de " + autoparte.getId() + " " + autoparte.getDenominacion());
			}
		}
	}
	
	//Hay que definir si recibe solamente un id del pedido o el array con el pedido
	public void cancelarPedido(ArrayList <Autoparte> pedido) {
		for (Autoparte autoparte : pedido) {
			autoparte.setStock(autoparte.getStock()+1);
			pedido.remove(autoparte);
		}
	}
	
}
