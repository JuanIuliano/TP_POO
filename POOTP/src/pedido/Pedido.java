package pedido;

import empresa.Empresa;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import Autopartes.Autoparte;

public class Pedido {

	String username;
	int idPedido = 0;
	int cantArticulos;
	Date fecha;
	ArrayList <ItemPedido> pedido = new ArrayList<>();
	ArrayList <Pedido> baseDePedidos = new ArrayList<>();
	Scanner scanner = new Scanner(System.in);
	
	
	//Clase usada para registrar cada parte del pedido con su autoaparte requerida y la cantidad de la misma, la lista de pedido está compuesta por objetos de esta clase.
	private class ItemPedido {
		Autoparte autoparte;
		int cantidad;
	}
	
	
	//CONSTRUCTOR CON PARAMETROS
	public Pedido(String username, int idPedido, int cantArticulos, Date fecha, ArrayList <ItemPedido> pedido) {
		this.username = username;
		this.idPedido = idPedido;
		this.cantArticulos = cantArticulos;
		this.fecha = fecha;
		this.pedido = pedido;
	}
	
	//CONSTRUCTOR SIN PARAMETROS
	public Pedido() {
		
	}
	
	
	public void crearPedido(){
		int id = 0;
		int cantidad = 0;
		int cantidadTotal = 0;
		Empresa.catalogo.listarCatalogo();	
		System.out.println();
		pedido = new ArrayList <ItemPedido> ();
		System.out.println("Ingresá [ID] de la autoparte que quiere agregar al carrito ");
		System.out.println("Ingresá [-1] para continuar ");
		id = scanner.nextInt();
		
		while (id != -1) {
			if (Empresa.catalogo.autoparteExistente(id)) {
				System.out.println("Ingresá la cantidad que quiere agregar: ");
				cantidad = scanner.nextInt();
				if (Empresa.catalogo.devolverAutoparte(id).getStock() >= cantidad) {
					//creamos el item que vamos a agregar a la lista de pedidos
					ItemPedido item = new ItemPedido();
					item.autoparte = Empresa.catalogo.devolverAutoparte(id);
					item.cantidad = cantidad;
					
					//Agregamos el item a la lista de pedidos
					pedido.add(item);
					
					//System.out.println("id " + id + " cantidad " + cantidad);
					Empresa.catalogo.restarStock(id, cantidad);
					cantidadTotal = cantidadTotal + cantidad;
				}
			}
			else {
				System.out.println("Esa autoparte no existe.");
			}
			System.out.println("Ingresá [ID] de la autoparte que quiere agregar al carrito ");
			System.out.println("Ingresá [-1] para continuar ");
			id = scanner.nextInt();
		}
		
		
		if (!pedido.isEmpty()) {
			System.out.println();
			System.out.println("EL PEDIDO ES EL SIGUIENTE: ");
			for (ItemPedido autoparte2 : pedido) {
				System.out.println(" - " + autoparte2.autoparte.getDenominacion()+" -- cantidad: "+autoparte2.cantidad );
			}
			System.out.println();
			System.out.println("Ingresá nombre del cliente: ");
			String username = scanner.next();
			
			System.out.println("Ingresá id del cliente: ");
			int idPedido = scanner.nextInt();
			Pedido p = new Pedido(username, idPedido, cantidadTotal, new Date(), pedido);
			System.out.println("Confirmar pedido: s/n");
			scanner.nextLine(); //Sirve para limpiar el buffer y no ingresar un salto de línea
			String conf = scanner.nextLine();
			if (conf.equals("s")) {
				baseDePedidos.add(p);
				System.out.println("el pedido ha sido registrado y guardado...");
				return;
			}
			else {
				System.out.println("Pedido cancelado");
				return;
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
	
	public void verPedidos(){
		int i=0;
		System.out.println("Estos son todos los pedidos registrados: ");
		for(Pedido p : baseDePedidos) {
			System.out.println("--------------------------------------");
			System.out.println("["+(i+1)+"]");
			System.out.println("ID del pedido: "+ p.idPedido);
			System.out.println("Nombre: "+ p.username);
			System.out.println("Fecha: "+p.fecha);
			System.out.println("Autopartes reservadas: ");
			//Obtenemos la lista de cosas que reservó el cliente
			ArrayList <ItemPedido> reserva = p.pedido;
			for(ItemPedido item : reserva) {
				System.out.println(" - " + item.autoparte.getDenominacion()+" -- cantidad: "+item.cantidad );
			}
			System.out.println("--------------------------------------");
			i++;
		}
	}
	
	
}
