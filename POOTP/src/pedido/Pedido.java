package pedido;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import Autopartes.Autoparte;
import cliente.Cliente;
import empresa.Empresa;


public class Pedido implements Serializable{
	private static final long serialVersionUID = 1L;
	private Cliente cliente;
	private int idPedido;
	private int cantArticulos;
	private Date fecha;
	private Map <Autoparte, Integer> autopartes;
	
	public Pedido(Cliente cliente, int idPedido, int cantArticulos, Date fecha, Map<Autoparte, Integer> autopartes) {
		this.cliente = cliente;
		this.idPedido = idPedido;
		this.cantArticulos = cantArticulos;
		this.fecha = fecha;
		this.autopartes = autopartes;
	}
	


	public Cliente getCliente() {
		return cliente;
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


	public Map<Autoparte, Integer> getAutopartes() {
		return autopartes;
	}


	public void setAutopartes(Map<Autoparte, Integer> pedido) {
		this.autopartes = pedido;
	}


	public void registrarPedido(Pedido p) {
		System.out.println("el pedido ha sido registrado y guardado...");
		
	}
	
	
	public void cancelarPedido(Pedido p) {
		System.out.println("cancelar pedidooo");
	}
	
	public void mostrarPedido() {
		for (Map.Entry<Autoparte, Integer> entry : this.getAutopartes().entrySet()) {
            System.out.println("- " + entry.getKey().getDenominacion() + " - " + entry.getValue());
        }
	}
	

	
	
}
