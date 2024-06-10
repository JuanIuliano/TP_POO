package Autopartes;

public interface AutoparteInterface {
	void modificarDatos();
	void mostrarAutoparte();
	void borrarAutoparte(Autoparte autoparte);
	void restarStock(Autoparte autoparte, int cantidad);
}
