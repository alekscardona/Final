package Chat.ejecutables;

import java.net.InetAddress;


import Chat.Hilos_CLiente.usuario;
public class ChatApp {
	public static void main(String[] args) {
		try {
			//Direccion ip del servidor a mano
			//InetAddress ip = InetAddress.getByName("192.168.1.80");
			usuario cliente = new usuario(InetAddress.getLocalHost(),6000);
			cliente.iniciar();
		} catch (Exception e) {
			System.err.println("falla"+e.getMessage());
		}
	}
}
