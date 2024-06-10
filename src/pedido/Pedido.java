package pedido;

import java.util.ArrayList;
import java.util.Date;

import Autopartes.Autoparte;
import usuario.Usuario;

public class Pedido {

	private String username;
	private int idPedido;
	private int cantArticulos;
	private Date fecha;
	private ArrayList <Autoparte> autopartes;
	
	public Pedido(String username, int idPedido, int cantArticulos, Date fecha, ArrayList <Autoparte> autopartes) {
		this.username = username;
		this.idPedido = idPedido;
		this.cantArticulos = cantArticulos;
		this.fecha = fecha;
		this.autopartes = autopartes;
	}
	
	
	
	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public int getIdPedido() {
		return idPedido;
	}



	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}



	public int getCantArticulos() {
		return cantArticulos;
	}



	public void setCantArticulos(int cantArticulos) {
		this.cantArticulos = cantArticulos;
	}



	public Date getFecha() {
		return fecha;
	}



	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}



	public ArrayList<Autoparte> getAutopartes() {
		return autopartes;
	}



	public void setAutopartes(ArrayList<Autoparte> pedido) {
		this.autopartes = pedido;
	}



	public void registrarPedido(Pedido p) {
		System.out.println("el pedido ha sido registrado y guardado...");
		
	}
	
	public void cancelarPedido(Pedido p) {
		System.out.println("cancelar pedidooo");
	}
	
}