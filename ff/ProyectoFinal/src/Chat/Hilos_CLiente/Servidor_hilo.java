package Chat.Hilos_CLiente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import Chat.ejecutables.Servidor;



public class Servidor_hilo extends Thread {

    private Socket cliente;
    private DataInputStream entrada;
    public DataOutputStream salida;
    private String nombreCliente;
    private Servidor servidor;

    public Servidor_hilo(Socket cliente, Servidor servidor) {
        this.cliente = cliente;
        this.servidor = servidor;

        try {
            entrada = new DataInputStream(cliente.getInputStream());
            salida = new DataOutputStream(cliente.getOutputStream());
            nombreCliente = entrada.readUTF();
            servidor.agregarCliente(nombreCliente);
        } catch (IOException ex) {
            System.err.println("Error al obtener los flujos de entrada/salida del cliente");
            ex.printStackTrace();
        }
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void run() {
        try {
        	
            String mensaje;
            boolean salir = false;

            while (!salir) {
                mensaje = entrada.readUTF();

                if (mensaje.startsWith("03|obtener usuarios")) {
                    String listaUsuarios = Servidor.obtenerListaUsuarios(Servidor.usuariosConectados);
                    salida.writeUTF("Usuario Conectados:");
                    salida.writeUTF(listaUsuarios);
                }

                else {
                    String mensajeGuardado = nombreCliente + ": " + mensaje;
                    servidor.guardarMensaje(mensajeGuardado, "historial");
                    servidor.enviarMensajeAClientes(mensajeGuardado);
                }
            }

            // Cerrar conexión con el cliente
            cliente.close();
            servidor.removerCliente(nombreCliente);
            System.out.println("Conexión cerrada con el cliente " + nombreCliente);
        } catch (IOException ex) {
            System.err.println("Usuario " + nombreCliente + " desconectado");
            servidor.removerCliente(nombreCliente);
        }catch (Exception e) {
			// TODO: handle exception
		}
        
    }
}



