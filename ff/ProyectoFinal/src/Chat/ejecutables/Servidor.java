package Chat.ejecutables;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Chat.Hilos_CLiente.Servidor_hilo;



public class Servidor extends JFrame {
	//Creo un formato para guardar una fecha ( en este caso para el historial de nuestro chat)
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	private ServerSocket server;
	private CopyOnWriteArrayList<Servidor_hilo> hilosClientes;
	private List<String> mensajesEnSesion;
	public static ArrayList<String> usuariosConectados = new ArrayList<>();
	private JButton btnGenerarHistorial;
	private JButton btnApagarServidor;
	private JTextArea campoTexto;

	public Servidor() {
		hilosClientes = new CopyOnWriteArrayList<>();
		mensajesEnSesion = new ArrayList<>();
		createGUI();
	}

	public void iniciarServidor() {
		try {
			server = new ServerSocket(6000);
			campoTexto.append("Servidor iniciado\n");
			while (true) {
				Socket cliente = server.accept();
				Servidor_hilo hilo = new Servidor_hilo(cliente, this);
				hilosClientes.add(hilo);
				hilo.start();

				campoTexto.append("Creada la conexión con el cliente " + hilo.getNombreCliente() + "\n");
			}
		} catch (IOException ex) {
		}
	}

	public void cerrarServidor() {
		try {
			server.close();
			campoTexto.append("Servidor detenido\n");

			for (Servidor_hilo hilo : hilosClientes) {
				hilo.interrupt();
			}
		} catch (IOException ex) {
			System.out.println("Jmmm");
		}
	}

	public void enviarListaUsuarios() {
		String listaUsuarios = Servidor.obtenerListaUsuarios(usuariosConectados);	
	}

	public void agregarCliente(String nombreCliente) {
		enviarListaUsuarios();
		if (!usuariosConectados.contains(nombreCliente)) {
			campoTexto.append("Cliente conectado: " + nombreCliente + "\n");
			usuariosConectados.add(nombreCliente);
		}	
	}

	public static String obtenerListaUsuarios(ArrayList<String> usuarios) {
		StringBuilder sb = new StringBuilder();
		for (String usuario : usuarios) {
			sb.append(usuario).append("\n");
		}
		return sb.toString();
	}

	public void removerCliente(String nombreCliente) {
		try {
			campoTexto.append("Cliente desconectado: " + nombreCliente + "\n");
			usuariosConectados.remove(nombreCliente);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void guardarMensaje(String mensaje, String nombreArchivo) {
		String timestamp = LocalDateTime.now().format(FORMATTER);
		mensajesEnSesion.add("[" + timestamp + "] " + mensaje);
	}
	
	public void enviarMensajeAClientes(String mensaje) {
		for (Servidor_hilo cliente : hilosClientes) {
			try {
				cliente.salida.writeUTF(mensaje);
			} catch (IOException e) {
			}
		}
	}
	//Una interfaz para hacer mas presentable el manejo del servidor
	private void createGUI() {
		setTitle("Servidor LifeInvader");
		
		//Para cuando se apague el servidor desde la X
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	    addWindowListener(new WindowAdapter() {
	    	
	            @Override
	            public void windowClosing(WindowEvent e) {
	            	try {
	            	cerrarServidor();
	            	JOptionPane.showMessageDialog(null,"Servidor apagado");
	            	dispose();
	            }catch (Exception a) {
					System.out.println("Error al cerrar");
				}
	            	
	            }
	            @Override
	            public void windowIconified(WindowEvent e) {
	                setState(JFrame.ICONIFIED);
	            }
	        });


		btnGenerarHistorial = new JButton("Generar Historial");
		btnGenerarHistorial.addActionListener(e -> generarHistorial());

		btnApagarServidor = new JButton("Apagar Servidor");
		btnApagarServidor.addActionListener(e -> {
			int confirm = JOptionPane.showConfirmDialog(this, "�Desea apagar el servidor?", "Apagar Servidor",
					JOptionPane.YES_NO_OPTION);
			if (confirm == JOptionPane.YES_OPTION) {
				cerrarServidor();
				JOptionPane.showMessageDialog(null,"Servidor apagado");
				dispose();
			}

		});

		JPanel panel = new JPanel();
		panel.add(btnGenerarHistorial);
		panel.add(btnApagarServidor);

		campoTexto = new JTextArea();
		campoTexto.setEditable(false);

		JScrollPane scrollPane = new JScrollPane(campoTexto);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		add(scrollPane, BorderLayout.CENTER);
		add(panel, BorderLayout.SOUTH);
		setSize(284, 375);
		setVisible(true);
	}
	
	private void generarHistorial() {
		String nombreArchivo = JOptionPane.showInputDialog(this, "Ingrese el nombre del archivo:");
		//Creo un archivo txt que guarda todos los mensajes enviados junto con la fecha y hora
		if (nombreArchivo != null && !nombreArchivo.isEmpty()) {
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo + ".txt"))) {
				for (String mensaje : mensajesEnSesion) {
					writer.write(mensaje);
					writer.newLine();
				}
				JOptionPane.showMessageDialog(this, "Historial generado correctamente");
			} catch (IOException e) {
				JOptionPane.showMessageDialog(this, "Error al generar el historial", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(this, "Nombre de archivo inválido", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void main(String[] args) {
		Servidor servidor = new Servidor();
		servidor.iniciarServidor();
	}
}
