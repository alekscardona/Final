package Chat.ejecutables;

import java.net.InetAddress;


import Chat.Hilos_CLiente.usuario;
public class ChatApp {
	public static void main(String[] args) {
		try {
			//Direccion ip del servidor a mano
			InetAddress ip = InetAddress.getByName("192.168.1.93");
			usuario cliente = new usuario(ip,6000);
			cliente.iniciar();
		} catch (Exception e) {
			System.err.println("falla"+e.getMessage());
		}
	}
}
