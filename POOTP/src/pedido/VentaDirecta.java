package pedido;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;

import Autopartes.Autoparte;
import cliente.Cliente;


public class VentaDirecta implements Serializable{
	private static final long serialVersionUID = 1L;
	private Cliente cliente;
	private int idVenta;
	private int cantArticulos;
	private Date fecha;
	private Map <Autoparte, Integer> autopartes;
	private String medioPago;
	private double montoInicial;
	private double montoFinal;
	
	
	public VentaDirecta(Cliente cliente, int idVenta, int cantArticulos, Date fecha, Map <Autoparte, Integer> autopartes, String medioPago, double montoInicial, double montoFinal) {
		this.setCliente(cliente);
		this.setIdVenta(idVenta);
		this.setCantArticulos(cantArticulos);
		this.setFecha(fecha);
		this.setAutopartes(autopartes);
		this.setMedioPago(medioPago);
		this.setMontoInicial(montoInicial);
		this.setMontoFinal(montoFinal);
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public int getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
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

	public Map <Autoparte, Integer> getAutopartes() {
		return autopartes;
	}

	public void setAutopartes(Map <Autoparte, Integer> autopartes) {
		this.autopartes = autopartes;
	}

	public String getMedioPago() {
		return medioPago;
	}

	public void setMedioPago(String medioPago) {
		this.medioPago = medioPago;
	}

	public double getMontoInicial() {
		return montoInicial;
	}

	public void setMontoInicial(double montoInicial) {
		this.montoInicial = montoInicial;
	}

	public double getMontoFinal() {
		return montoFinal;
	}

	public void setMontoFinal(double montoFinal) {
		this.montoFinal = montoFinal;
	}
	
	public void generarRecibo(VentaDirecta vd){
		System.out.println("Generando recibo..");
		Recibo r = new Recibo();
		r.generarReciboDirecta(vd);
	}
}
