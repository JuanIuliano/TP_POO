package cliente;

import java.io.Serializable;
import java.util.Scanner;

import empresa.Empresa;

public class Cliente implements Serializable{
	private static final long serialVersionUID = 1L;
	int id;
	String nombreApellido;
	String direccion; //Calle + número + código postal
	String telefono; //Característica + "-" + número
	String localidad;
	String provincia;
	String mail;
	Scanner scanner = new Scanner(System.in);
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombreApellido() {
		return nombreApellido;
	}

	public void setNombreApellido(String nombreApellido) {
		this.nombreApellido = nombreApellido;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Scanner getScanner() {
		return scanner;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}

	
	
	public Cliente(int id, String nombreApellido, String direccion, String telefono, String localidad, String provincia,
			String mail) {
		super();;
		this.id = id;
		this.nombreApellido = nombreApellido;
		this.direccion = direccion;
		this.telefono = telefono;
		this.localidad = localidad;
		this.provincia = provincia;
		this.mail = mail;
	}


}
