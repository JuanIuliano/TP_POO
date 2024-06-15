package empresa;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.*;
import Autopartes.Autoparte;
import pedido.Pedido;
import pedido.Venta;
import cliente.Cliente;

public class Tutta implements Serializable{
	private static final long serialVersionUID = 1L;
	private ArrayList <Pedido> pedidos;
	private ArrayList <Venta> ventas;
	private ArrayList <Cliente> clientes;
	public int cantidadPedidos;
	public int cantidadClientes = 0;

	
	
	public Tutta() {
		this.pedidos = new ArrayList <Pedido> ();
		this.ventas = new ArrayList <Venta> ();
		this.clientes = new ArrayList <Cliente>();
	}
	
	 public void guardarBBDD() throws IOException {
	        try (
	            FileOutputStream fileOutputStream = new FileOutputStream("BBDD.txt");
	            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)
	        ) {
	            objectOutputStream.writeObject(this);
	            System.out.println("Base de datos guardada con éxito.");
	        }
	    }
	 
	 
	 public static Tutta cargarBBDD() throws IOException, ClassNotFoundException{
		 Tutta t = null;
		 try (
			FileInputStream fileInputStream = new FileInputStream("BBDD.txt");
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			){
			 System.out.println("Base de datos cargada con éxito.");
			 t = (Tutta) objectInputStream.readObject();
		 }
		 catch(FileNotFoundException e){
			 System.out.println("Archivo de base de datos no existe, creando nueva base de datos");
			 t = new Tutta();
		 }
		 catch (IOException | ClassNotFoundException e) {
	            e.printStackTrace();
	        }
		 
		 return t;
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
		int opc;
		Cliente cliente = null;
		
		//Seleccionamos el cliente que reservará el pedido/si no existe lo creamos
		System.out.println("CREANDO PEDIDO");
		System.out.println("[1] Cliente existente");
		System.out.println("[2] Nuevo cliente");
		while (true) {
			System.out.println("Seleccione una opción: ");
		    opc = scanner.nextInt();
			if (opc != 1 && opc != 2) {
				System.out.println("Opción incorrecta");
			}
			else {
				break;
			}
		}
		if(opc==1) {
			while(true) {
				System.out.println("Ingresá el nombre y apellido del usuario:");
				scanner.next();
				String nomap = scanner.nextLine();
				System.out.println("Ingresá el ID del usuario:");
				int idcl = scanner.nextInt();
				Cliente c = this.getCliente(nomap, idcl);
				if(c != null) {
					System.out.println("El cliente existe.");
					cliente = c;
					System.out.println();
					break;
				}
				else {
					System.out.println("Error, el cliente no existe");
				}
			}
		}
		
		else {
			System.out.println("Creando cliente..");
			System.out.println("Ingrese el nombre y apellido del cliente:");
			scanner.next();
			String nombreApellido = scanner.nextLine();
			System.out.println("Ingrese el teléfono del cliente:");
			String telefono = scanner.nextLine();
			System.out.println("Ingrese la provincia del cliente:");
			String provincia = scanner.nextLine();
			System.out.println("Ingrese la localidad del cliente:");
			String localidad = scanner.nextLine();
			System.out.println("Ingrese la dirección del cliente (calle + número + código postal):");
			String direccion = scanner.nextLine();
			System.out.println("Ingrese el mail del cliente:");
			String mail = scanner.nextLine();
			System.out.println();
			int idC = this.cantidadClientes+1;
			
			//creamos cliente con estos parámetros
			cliente = new Cliente(idC, nombreApellido, direccion, telefono, localidad, provincia, mail);
			
			//Chequeamos que el cliente no esté registrado
			if(this.clienteExistente(cliente)) {
				//si está registrado mosotramos error por pantalla
				System.out.println("Error, el cliente ya está registrado.");
			}
			else {
				this.agregarCliente(cliente);
				System.out.println("CLIENTE REGISTRADO CON ÉXITO!");
				System.out.println("El ID del cliente es: "+idC);
				System.out.println();
				this.cantidadClientes++;
			}
		}
		
		
		
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

			System.out.println();
			int idPedido = this.cantidadPedidos;
			this.cantidadPedidos++;
			System.out.println("Pedido registrado con éxito.");
			System.out.println();
			
			//Creamos el objeto pedido y lo agregamos a la lista de pedidos
			Pedido p = new Pedido(cliente, idPedido, autopartes.size(), new Date(), autopartes);
			this.agregarPedido(p);
		}
	}
	
	
	
	public void cancelarPedido() {
		Scanner scanner = new Scanner(System.in);
		if(this.pedidos.size() > 0) {					
			mostrarPedidos();
            
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

	
	public void mostrarPedidos() {
		if(this.pedidos.size() > 0) {					
			System.out.println("Pedidos: ");
			
			for (Pedido pedido : this.pedidos) {
				System.out.println("--------------");
                System.out.println("ID: "+pedido.getIdPedido() + " -- A nombre de: "+pedido.getCliente().getNombreApellido());
                System.out.println("Autopartes del pedido: ");
                for (Map.Entry<Autoparte, Integer> entry : pedido.getAutopartes().entrySet()) {
                    System.out.println("- " + entry.getKey().getDenominacion() + " - " + entry.getValue());
                }
                System.out.println("--------------");
            }
		}
	
	}
	
	public Pedido devolverPedido(int id) {
		for(Pedido pedido : pedidos) {
			if(pedido.getIdPedido() == id) {
				return pedido;
			}
		}
		return null;
	}
	
	
	public boolean clienteExistente(Cliente cliente) {
		for(Cliente c : clientes) {
			if (c == cliente) {
				return true;
			}
		}
		return false;
	}
	
	public void agregarCliente(Cliente cliente) {
		clientes.add(cliente);
		cantidadClientes++;
	}
	
	public Cliente getCliente(String nombreApellido, int id) {
		for(Cliente c : clientes) {
			if(c.getNombreApellido().equals(nombreApellido) && c.getId() == id) {
				return c;
			}
		}
		return null;
	}
	
	public void mostrarClientes() {
		for(Cliente c : clientes) {
			System.out.println("ID: "+c.getId());
			System.out.println("Nombre y Apellido: "+c.getNombreApellido());
			System.out.println("Provincia: "+c.getProvincia());
			System.out.println("Direccion: "+c.getDireccion());
			System.out.println();
			System.out.println();
		}
		
	}
	
	
}

