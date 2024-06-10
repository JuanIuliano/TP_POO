package pedido;

import java.util.ArrayList;
import java.util.Date;

import Autopartes.Autoparte;
import usuario.Usuario;

public class Pedido {

	String username;
	int idPedido;
	int cantArticulos;
	Date fecha;
	ArrayList <Autoparte> pedido;
	
	public Pedido(String username, int idPedido, int cantArticulos, Date fecha, ArrayList <Autoparte> pedido) {
		this.username = username;
		this.idPedido = idPedido;
		this.cantArticulos = cantArticulos;
		this.fecha = fecha;
		this.pedido = new ArrayList<Autoparte> ();
	}
	
	
	
	public void registrarPedido(Pedido p) {
		/*for (Autoparte autoparte : p.pedido) {
			if(autoparte.getStock() >= 1) {
				//Hay que definir como se colocarán las autopartes para el pedido.
				autoparte.setStock(autoparte.getStock()-1);
			}else {
				//Esto debe estar contemplado en el catálogo
				System.out.println("No hay stock suficiente de " + autoparte.getId() + " " + autoparte.getDenominacion());
			}
		}*/
		System.out.println("el pedido ha sido registrado y guardado...");
	}
	
	//Hay que definir si recibe solamente un id del pedido o el array con el pedido
	public void cancelarPedido(ArrayList <Autoparte> pedido) {
		for (Autoparte autoparte : pedido) {
			autoparte.setStock(autoparte.getStock()+1);
			pedido.remove(autoparte);
		}
	}
	
}
