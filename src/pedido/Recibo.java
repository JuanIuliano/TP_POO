package pedido;

import java.util.ArrayList;

import Autopartes.Autoparte;
import cliente.Cliente;

public class Recibo {
	Venta venta;
	
	public Recibo generarRecibo(Venta v) {
		//Guardamos el cliente de la venta
		Pedido p = v.getPedido();
		Cliente c = p.getCliente();
		
		//Mostramos la información
		System.out.println("------------------RECIBO---------------");
		System.out.println(p.getFecha());
		System.out.println("ID del pedido: "+p.getIdPedido());
		p.mostrarPedido();
		System.out.println();
		//Información del usuario
		System.out.println("Nombre y apellido: "+c.getNombreApellido());
		System.out.println("Número de telefono: "+c.getTelefono());
		System.out.println("Provincia: "+c.getProvincia());
		System.out.println("Dirección: "+c.getDireccion());
		System.out.println();
		System.out.println("PAGO");
		System.out.println("Monto inicial: "+v.getMontoInicial());
		System.out.println("MONTO FINAL: "+v.getMontoFinal());
		System.out.println("Medio de pago: "+v.getMedioPago());
		return null;
	}
	
	public Recibo generarReciboDirecta(VentaDirecta vd) {
		Cliente c = vd.getCliente();
		
		//Mostramos la información
		System.out.println("------------------RECIBO---------------");
		System.out.println(vd.getFecha());
		System.out.println("ID del pedido: "+vd.getIdVenta());
		System.out.println();
		//Información del usuario
		System.out.println("Nombre y apellido: "+c.getNombreApellido());
		System.out.println("Número de telefono: "+c.getTelefono());
		System.out.println("Provincia: "+c.getProvincia());
		System.out.println("Dirección: "+c.getDireccion());
		System.out.println();
		System.out.println("PAGO");
		System.out.println("Monto inicial: "+vd.getMontoInicial());
		System.out.println("MONTO FINAL: "+vd.getMontoFinal());
		System.out.println("Medio de pago: "+vd.getMedioPago());
		return null;
	}
}
