package empresa;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import Autopartes.Autoparte;
import pedido.Pedido;
import pedido.Venta;

public class Tutta {
	private ArrayList <Pedido> pedidos;
	private ArrayList <Venta> ventas;
	private int id;
	
	
	public Tutta() {
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
	
	public void agregarPedido(Pedido p) {
		if(p != null) {
			this.pedidos.add(p);
		}
	}
	
	public void crearPedido() {
		Scanner scanner = new Scanner(System.in);
		int id = 0;
		int cantidad = 0;
		Empresa.catalogo.listarCatalogo();	
		System.out.println();
		Map<Autoparte, Integer> autopartes = new HashMap <Autoparte, Integer> ();
		
		while (id != -1) {
			System.out.println("Ingresá [ID] de la autoparte que quiere agregar al carrito ");
			System.out.println("Ingresá [-1] para continuar ");
			id = scanner.nextInt();
			//Si la autoparte en cuestión existe pedimos la cantidad a reservar
			if (Empresa.catalogo.autoparteExistente(id)) {
				System.out.println("Ingresá la cantidad que quiere agregar: ");
				cantidad = scanner.nextInt();
				System.out.println();
				//Calculamos si la cantidad a reservar no excede al stock
				if (Empresa.catalogo.devolverAutoparte(id).getStock() >= cantidad) {
					//Obtenemos el objeto autoparte correspondiente a el ID
					Autoparte autoparteExistente = Empresa.catalogo.devolverAutoparte(id);
					//Lo agregamos al hashmap (obtenemos la cantidad actual y le sumamos la nueva)
                    autopartes.put(autoparteExistente, autopartes.getOrDefault(autoparteExistente, 0) + cantidad);
                    //Actualizamos stock
                    Empresa.catalogo.restarStock(id, cantidad);
                    System.out.println("id " + id + " cantidad " + cantidad);
                    System.out.println();
				}
				else {
					System.out.println("Error al realizar la reserva, las cantidades exceden al stock.");
					System.out.println();
				}
				
			}
			else {
				System.out.println("Esa autoparte no existe.");
				System.out.println();
			}
		}
		
		
		//Si el hashmap del pedido no esta vacío, es decir, si cargamos autopartes al pedido
		if (!autopartes.isEmpty()) {
			System.out.println();
			System.out.println("EL PEDIDO ES EL SIGUIENTE: ");
			int i = 1;
			//Mostramos el pedido por pantalla
			for (Map.Entry<Autoparte, Integer> entry : autopartes.entrySet()) {
                System.out.println(i + " - " + entry.getKey().getDenominacion() + " - " + entry.getValue());
                i++;
             }
			
			
			//Agregamos la información del usuario
			System.out.println();
			System.out.println("Ingresá nombre del cliente: ");
			String username = scanner.next();
			int idPedido = this.id + 1;
			this.id++;
			
			//Creamos el objeto pedido y lo agregamos a la lista de pedidos
			Pedido p = new Pedido(username, idPedido, autopartes.size(), new Date(), autopartes);
			this.agregarPedido(p);
		}
	}
	
	
	public void cancelarPedido() {
		Scanner scanner = new Scanner(System.in);
		if(this.pedidos.size() > 0) {					
			System.out.println("Pedidos: ");
			
			for (Pedido pedido : this.pedidos) {
				System.out.println("--------------");
                System.out.println("ID: "+pedido.getIdPedido() + " -- A nombre de: "+pedido.getUsername());
                System.out.println("Autopartes del pedido: ");
                for (Map.Entry<Autoparte, Integer> entry : pedido.getAutopartes().entrySet()) {
                    System.out.println("- " + entry.getKey().getDenominacion() + " - " + entry.getValue());
                }
                System.out.println("--------------");
            }
            
            int flagBorrar = 0;
            while (flagBorrar != -1) {
                System.out.println("Ingresá [ID] del pedido que quieras cancelar");
                System.out.println("Ingresá [-1] para continuar ");
                int borrarPedido = scanner.nextInt();
                flagBorrar = borrarPedido;
                boolean borrado = false;
                for (Pedido pedido : this.pedidos) {
                    if (pedido.getIdPedido() == borrarPedido) {
                        for (Map.Entry<Autoparte, Integer> entry : pedido.getAutopartes().entrySet()) {
                            Empresa.catalogo.sumarStock(entry.getKey().getId(), entry.getValue());
                        }
                        pedidos.remove(pedido);
                        System.out.println("El pedido ha sido cancelado satisfactoriamente.");
                        borrado = true;
                        break;
                    }
                }
                if (!borrado) {
                    System.out.println("No existe pedido con ese id.");
                } else {
                    break;
                }
            }
			
		}else {
			System.out.println("No hay pedidos");
			System.out.println("Ingresá cualquier tecla para continuar");
			scanner.next();
		}
	}
	
	
}

