package Chat.chat;

import javax.swing.*;

import Chat.Hilos_CLiente.usuario;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public class ChatInterfaz {
	private static final DateTimeFormatter FORMATTER =DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	private JFrame frame;
	private JTextArea areaMensajes;
	private JTextField campoMensaje;
	private usuario cliente;
	private JLabel labelNombreUsuario;
	private Set<String> nombresUsuarios;

	public ChatInterfaz() {
		initialize();
		nombresUsuarios = new HashSet<>();
	}

	private void initialize() {
		// Crear la ventana de chat
		frame = new JFrame("LifeInvader");
		frame.setBounds(100, 100, 400, 300);

		// Cerrar la ventana de forma segura
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cerrarVentana();
			}

			@Override
			public void windowIconified(WindowEvent e) {
				frame.setState(JFrame.ICONIFIED);
			}
		});

		// Panel superior para el nombre de usuario
		JPanel panelSuperior = new JPanel(new BorderLayout());
		labelNombreUsuario = new JLabel();
		panelSuperior.add(labelNombreUsuario, BorderLayout.WEST);
		frame.getContentPane().add(panelSuperior, BorderLayout.NORTH);

		// Ã�rea de mensajes
		areaMensajes = new JTextArea();
		areaMensajes.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(areaMensajes);
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

		// Panel inferior para el campo de texto y los botones de enviar y enviar
		// archivo
		JPanel panelInferior = new JPanel(new BorderLayout());
		campoMensaje = new JTextField();
		JButton botonEnviar = new JButton("Enviar");
		botonEnviar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Cuando se presiona el botÃ³n Enviar, enviar el mensaje
				String mensaje = obtenerMensaje();
				cliente.enviarMensaje(mensaje);
				campoMensaje.setText("");
			}
		});
		JButton botonEnviarArchivo = new JButton("Enviar Archivo");
		botonEnviarArchivo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Cuando se presiona el botÃ³n Enviar Archivo, mostrar un diÃ¡logo de selecciÃ³n
				// de archivo
				JFileChooser fileChooser = new JFileChooser();
				int seleccion = fileChooser.showOpenDialog(frame);
				if (seleccion == JFileChooser.APPROVE_OPTION) {
					File archivo = fileChooser.getSelectedFile();
					String nombreArchivo = archivo.getName();
					cliente.enviarArchivo(archivo);
					mostrarMensaje("Archivo enviado: " + nombreArchivo);
				}
			}
		});
		// Me imprime en pantalla los usuarios en sesion
		JButton botonObtenerUsuarios = new JButton("Obtener Usuarios");
		botonObtenerUsuarios.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Obtener la lista de usuarios
				cliente.obtenerListaUsuarios();
			}
		});

		panelInferior.add(campoMensaje, BorderLayout.CENTER);
		panelInferior.add(botonEnviar, BorderLayout.EAST);
		panelInferior.add(botonEnviarArchivo, BorderLayout.WEST);
		panelInferior.add(botonObtenerUsuarios, BorderLayout.SOUTH);

		frame.getContentPane().add(panelInferior, BorderLayout.SOUTH);
		// Mostrar la ventana de chat
		frame.setVisible(true);
	}

	public String obtenerNombreUsuario() {
		String nombreUsuario = JOptionPane.showInputDialog(frame, "Indica tu nombre:");
		if (nombreUsuario.isEmpty()) {
			JOptionPane.showMessageDialog(frame,
					"El nombre de usuario no puede estar vacio. Por favor, elige otro.",
					"Nombre de usuario invalido", JOptionPane.ERROR_MESSAGE);
			obtenerNombreUsuario();
		}
		else if (nombreUsuario != null) {
			nombresUsuarios.add(nombreUsuario);
		}
		return nombreUsuario;
	}

	public void cerrarVentana() {
		int opcion = JOptionPane.showConfirmDialog(frame, "Estas seguro de que quieres cerrar el chat?",
				"Confirmar cierre", JOptionPane.YES_NO_OPTION);
		if (opcion == JOptionPane.YES_OPTION) {
			frame.dispose();
			System.exit(0);// Avisa al servidor

		}

	}

	public String obtenerMensaje() {
		return campoMensaje.getText();
	}

	public void mostrarMensaje(String mensaje) {
		String timestamp = LocalDateTime.now().format(FORMATTER);
		areaMensajes.append(mensaje +"                    |"+timestamp+"|"+"\n");
	}

	public void setCliente(usuario cliente) {
		this.cliente = cliente;
	}

	public void setNombreUsuario(String nombreUsuario) {
		labelNombreUsuario.setText("Usuario: " + nombreUsuario);
	}

}
