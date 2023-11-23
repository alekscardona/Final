package Chat.Hilos_CLiente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import Chat.chat.ChatInterfaz;

public class usuario {

	private InetAddress direccionIP;
	private int puerto;
	private ChatInterfaz interfaz;
	private DataOutputStream salida;

	public usuario(InetAddress ip, int puerto) {
		this.direccionIP = ip;
		this.puerto = puerto;
		this.interfaz = new ChatInterfaz();
	}

	public void iniciar() {
		try {
			Socket socket = new Socket(direccionIP, puerto);
			DataInputStream entrada = new DataInputStream(socket.getInputStream());
			salida = new DataOutputStream(socket.getOutputStream());

			// Leer nombre de usuario y validar que no se repita
			String nombreUsuario = interfaz.obtenerNombreUsuario();
			salida.writeUTF(nombreUsuario);

			// Configurar la interfaz del cliente
			interfaz.setCliente(this);
			interfaz.setNombreUsuario(nombreUsuario);

			// Iniciar hilo para recibir mensajes del servidor
			Thread hiloServidor = new Thread(() -> recibirMensajesServidor(entrada));
			hiloServidor.start();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void enviarMensaje(String mensaje) {
		try {
			salida.writeUTF(mensaje);
		} catch (IOException ex) {
			System.err.println("Falla al enviar mensaje");
		}
	}

	public void enviarArchivo(File archivo) {
		try {
			// Enviar nombre de archivo al servidor
			salida.writeUTF("ARCHIVO:" + archivo.getName());

			// Leer y enviar contenido del archivo al servidor
			FileInputStream fileInputStream = new FileInputStream(archivo);
			byte[] buffer = new byte[6000];
			int bytesRead;
			while ((bytesRead = fileInputStream.read(buffer)) != -1) {
				salida.write(buffer, 0, bytesRead);
			}
			fileInputStream.close();
		} catch (IOException ex) {
			System.err.println("Falla al enviar archivo");
		}
	}

	private void recibirMensajesServidor(DataInputStream entrada) {
		try {
			while (true) {
				String mensaje = entrada.readUTF();
				if (mensaje.startsWith("03| Usuarios conectados")) {
					// Actualizar la lista de usuarios en la interfaz del cliente
					String listaUsuarios = mensaje.substring(25);
					String[] usuarios = listaUsuarios.split(",");
					interfaz.mostrarMensaje("Usuarios conectados:");
					for (String usuario : usuarios) {
						interfaz.mostrarMensaje("- " + usuario);
					}
				} else if (mensaje.startsWith("ARCHIVO:")==true) {
					// Recibir archivo del servidor
					System.out.println("dsdv");
					String nombreArchivo = mensaje.substring(8);
					recibirArchivo(entrada, nombreArchivo);
				} else {
					interfaz.mostrarMensaje(mensaje);
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void obtenerListaUsuarios() {
		try {
			salida.writeUTF("03|obtener usuarios");
		} catch (IOException ex) {
			System.err.println("Falla al obtener la lista de usuarios");
		}
	}

	private void recibirArchivo(DataInputStream entrada, String nombreArchivo) throws IOException {
		// Especificar la ruta donde se guardarán los archivos recibidos dentro del
		// proyecto
		String rutaDirectorio = "archivos_recibidos" + File.separator;
		// Crear el directorio si no existe
		File directorio = new File(rutaDirectorio);
		directorio.mkdirs();
		
		// Crear el archivo en el cliente para guardar el archivo recibido
		File archivo = new File(rutaDirectorio + nombreArchivo);
		byte[] buffer = new byte[6000];
		int bytesRead;
		// Leer y guardar el contenido del archivo recibido
		FileOutputStream fileOutputStream = new FileOutputStream(archivo);
		while ((bytesRead = entrada.read(buffer)) != -1) {
			fileOutputStream.write(buffer, 0, bytesRead);
		}
		fileOutputStream.close();
		// Mostrar mensaje en la interfaz del cliente indicando que se recibió el
		// archivo
		interfaz.mostrarMensaje("Archivo recibido: " + archivo.getAbsolutePath());
	}

}
