package empresa;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import Autopartes.Autoparte;
import Autopartes.Catalogo;
import pedido.PagoDebito;
import pedido.PagoEfectivo;
import pedido.PagoTarjeta;
import pedido.Pedido;
import pedido.Recibo;
import pedido.Venta;
import pedido.VentaDirecta;
import usuario.Usuario;
import cliente.Cliente;

public class Tutta implements Serializable{
	private static final long serialVersionUID = 1L;
	private ArrayList <Pedido> pedidos = new ArrayList<>();
	private ArrayList <Venta> ventas = new ArrayList<>();
	private ArrayList <Cliente> clientes = new ArrayList<>();
	private ArrayList <Autoparte> catalogo = new ArrayList<>();
	private ArrayList <Usuario> listaUsuarios = new ArrayList<>();
	public int cantidadPedidos;
	public int cantidadClientes = 0;
	
	// CONSTRUCTOR SIN PARAMÉTROS----------------------------------------------------------------
	public Tutta() {
	}
	
	// MÉTODOS PARA GUARDAR Y CARGAR OBJETO ------------------------------------------------------------------------------
	//SERIALIZAR
	public void guardarBBDD() throws IOException {
        try (
            FileOutputStream fileOutputStream = new FileOutputStream("BBDD.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)
        ) {
            objectOutputStream.writeObject(this);
            System.out.println("Base de datos guardada con éxito.");
        }
    }
	

	
	//CARGAR OBJETO CON ARCHIVO SERIALIZADO
	 public static Tutta cargarBBDD() throws IOException, ClassNotFoundException{
		 Tutta t = null;
		 try (FileInputStream fileInputStream = new FileInputStream("BBDD.txt");
	             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
	            System.out.println("Base de datos cargada con éxito.");
	            t = (Tutta) objectInputStream.readObject();
	        } catch (FileNotFoundException e) {
	            System.out.println("Archivo de base de datos no existe, creando nueva base de datos");
	            t = new Tutta(); // Si el archivo no existe, crear una nueva instancia vacía
	        } catch (IOException | ClassNotFoundException e) {
	            e.printStackTrace();
	        }

	        if (t == null) {
	            t = new Tutta(); // Por si ocurre alguna excepción inesperada
	        }
	        
	        if (t.listaUsuarios == null) {
	            t.listaUsuarios = new ArrayList<>();
	        }

	        return t;
	    }
	 
	 
	 // MÉTODOS DE CATALOGO/AUTOPARTES ---------------------------------------------------------------------------------------------
	public ArrayList<Autoparte> getCatalogo(){
		return this.catalogo;
	}
		
	public void setCatalogo(ArrayList<Autoparte> catalogo) {
		this.catalogo = catalogo;
	}

	 // MÉTODOS DE USUARIOS/BASEDEUSUARIOS ---------------------------------------------------------------------------------------------
	public ArrayList<Usuario> getListaUsuario(){
		return this.listaUsuarios;
	}
	
	public void setListaUsuario(ArrayList<Usuario> baseDeUsuarios){
		this.listaUsuarios = baseDeUsuarios;
	}


	
	 // MÉTODOS DE CLIENTES -----------------------------------------------------------------------------
	public Cliente crearCliente() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Creando cliente..");
		System.out.println("Ingrese el nombre y apellido del cliente:");
		//scanner.nextLine();
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
		Cliente cliente = new Cliente(idC, nombreApellido, direccion, telefono, localidad, provincia, mail);
		
		//Chequeamos que el cliente no esté registrado
		if(this.clienteExistente(cliente)) {
			//si está registrado mosotramos error por pantalla
			System.out.println("Error, el cliente ya está registrado.");
		}else {
			this.agregarCliente(cliente);
			System.out.println("CLIENTE REGISTRADO CON ÉXITO!");
			System.out.println(idC + " - Nombre: " + nombreApellido);
			//System.out.println("El ID del cliente es: "+idC);
			System.out.println();
			return cliente;
		}
		return null;
	}
	
	public Cliente seleccionarCliente() {
		Scanner scanner = new Scanner(System.in);
		Cliente cliente = null;
		while(true) {
			
			System.out.println("Ingresá el nombre y apellido del cliente:");
			String nomap = scanner.nextLine();
			System.out.println("nombre apellido: " + nomap);
			System.out.println("Ingresá el ID del cliente:");
			int idcl = scanner.nextInt();
			System.out.println("id cliente: " + idcl);
			Cliente c = getCliente(nomap, idcl);
			if(c != null) {
				System.out.println("El cliente existe.");
				cliente = c;
				System.out.println();
				return cliente;
			}
			else {
				System.out.println("Error, el cliente no existe");
			}
		}
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
	
	public Cliente getClienteId(int id) {
		for(Cliente c : clientes) {
			if(c.getId() == id) {
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
			System.out.println("--------------------------------");
			System.out.println();
		}
		
	}
	
	
	// MÉTODOS DE PEDIDOS -----------------------------------------------------------------------------
	public void crearPedido(Map<Autoparte, Integer> autopartes, Cliente cliente) {
		Scanner scanner = new Scanner(System.in);
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
			int idPedido = this.cantidadPedidos+1;
			this.cantidadPedidos++;
			System.out.println("Pedido registrado con éxito.");
			System.out.println("Ingrese cualquier tecla para continuar...");
			scanner.next();
			
			
			//Creamos el objeto pedido y lo agregamos a la lista de pedidos
			Pedido p = new Pedido(cliente, idPedido, autopartes.size(), new Date(), autopartes);
			this.agregarPedido(p);
		}
	}
	
	public boolean pedidosVacio() {
		if (this.getPedidos().size() > 0) {
			return false;
		}
		return true;
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
	
	

	// MÉTODOS DE VENTA -------------------------------------------------------------------------------------------------------
	public void crearVentaDirecta(Map<Autoparte, Integer> autopartes, Cliente cliente) {
		Scanner scanner = new Scanner(System.in);
		//Si el hashmap del carrito no esta vacío, es decir, si cargamos autopartes al carrito
		if (!autopartes.isEmpty()) {
			System.out.println();
			System.out.println("LA VENTA ES LA SIGUIENTE: ");
			int i = 1;
			//Mostramos el carrito por pantalla
			for (Map.Entry<Autoparte, Integer> entry : autopartes.entrySet()) {
                System.out.println(i + " - " + entry.getKey().getDenominacion() + " - " + entry.getValue());
                i++;
             }

			System.out.println();
			int idVenta = ventas.size()+1;
			
			double montoInicial = 0;
			for (Map.Entry<Autoparte, Integer> entry : autopartes.entrySet()) {
	            montoInicial = (entry.getKey().getPrecioUnitario() * entry.getValue()) + montoInicial;
	         }
			
			System.out.println("Ingrese el medio de pago...");
			System.out.println("[1] para pagar en efectivo.");
			System.out.println("[2] para pagar con débito.");
			System.out.println("[3] para pagar con crédito.");
			int opcion = scanner.nextInt();
			double montoFinal = 0;
			String medioPago = "";
			switch(opcion) {
			case 1:
				PagoEfectivo pagoef = new PagoEfectivo();
				montoFinal = pagoef.montoFinal(montoInicial);
				medioPago = "Efectivo";
				break;
			
			case 2:
				PagoDebito pagodeb = new PagoDebito();
				montoFinal = pagodeb.montoFinal(montoInicial);
				medioPago = "Débito";
				break;
				
			case 3:
				PagoTarjeta pagotar = new PagoTarjeta();
				montoFinal = pagotar.montoFinal(montoInicial);
				medioPago = "Crédito";
				break;
			}
			System.out.println("EL MONTO FINAL A ABONAR ES: "+montoFinal);
			System.out.println("[s] Confirmar venta");
			System.out.println("Cualquier tecla para cancelar");
			String conf = scanner.next();
			if (conf.equals("s")){
				VentaDirecta vd = new VentaDirecta(cliente, idVenta, autopartes.size(), new Date(), autopartes, medioPago, montoInicial, montoFinal); //mediopago, montoinicial, montofinal
				System.out.println("Venta realizada.");
				System.out.println();
				vd.generarRecibo(vd);
			}
			else {
				System.out.println("Venta cancelada");
			}
			System.out.println("Ingresa cualquier tecla para continuar");
			scanner.next();
			return;
			
		}
	}
	
	
	
	// GETTERS Y SETTERS -----------------------------------------------------
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
	
	public ArrayList<Cliente> getClientes() {
		return clientes;
	}
	
	
}

