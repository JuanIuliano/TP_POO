package usuario;
import java.util.Scanner;
 
public class Usuario {
	//Atributos de objeto usuario
	int id;
	String username;
	String contraseña;
	String mail;
	//Objeto de la clase Scanner
	Scanner scanner = new Scanner(System.in);
	
	
	//Métodos
	
	//constructor
	public Usuario(int id, String username, String contraseña, String mail) {
		super();
		this.id = id;
		this.username = username;
		this.contraseña = contraseña;
		this.mail = mail;
	}
 
	
	//Getters y Setters
	public int getId() {
		return id;
	}
 
	public void setId(int id) {
		this.id = id;
	}
 
	public String getUsername() {
		return username;
	}
 
	public void setUsername(String username) {
		this.username = username;
	}
 
	public String getContraseña() {
		return contraseña;
	}
 
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
 
	public String getMail() {
		return mail;
	}
 
	public void setMail(String mail) {
		this.mail = mail;
	}
 
	
	//Método para ver los datos del usuario
	public void verInfo(Usuario user) {
		System.out.println("ID: "+this.id);
		System.out.println("Username: "+this.username);
		System.out.println("Mail: "+this.mail);
	}
 
 
}