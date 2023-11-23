package Models;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;

import ListasGenericas.ListaSimple;

public class Aerolinea {

	public String nombre;


	public ListaSimple<Ciudad> listaCiudadesAerolinea = new ListaSimple<>();

	//ArrayList porque no interesa el orden ni el rendimiento
	private ArrayList<Ruta> listaRutas = new ArrayList<>();
	private ArrayList<Aeronave> listaFlotas = new ArrayList<>();

	//Hashset porque no interesa el orden pero si el rendimiento
	
	private HashSet<Equipaje> listaEquipaje = new HashSet<>();

	//TreeSet porque interesa el orden
	private TreeSet<Cliente> listaUsuarios = new TreeSet<>();
	public ArrayList<Ruta> getListaRutas() {
		return listaRutas;
	}
	
	/**
	 * 
	 * Metodos setters y getters de los atributos
	 * 
	 */
	public void setListaRutas(ArrayList<Ruta> listaRutas) {
		this.listaRutas = listaRutas;
	}
	public ArrayList<Aeronave> getListaFlotas() {
		return listaFlotas;
	}
	public void setListaFlotas(ArrayList<Aeronave> listaFlotas) {
		this.listaFlotas = listaFlotas;
	}
	

	public void setListaEquipaje(HashSet<Equipaje> listaEquipaje) {
		this.listaEquipaje = listaEquipaje;
	}
	public TreeSet<Cliente> getListaUsuarios() {
		return listaUsuarios;
	}
	public void setListaUsuarios(TreeSet<Cliente> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the listaCiudadesAerolinea
	 */
	public ListaSimple<Ciudad> getListaCiudadesAerolinea() {
		return listaCiudadesAerolinea;
	}
	/**
	 * @param listaCiudadesAerolinea the listaCiudadesAerolinea to set
	 */
	public void setListaCiudadesAerolinea(ListaSimple<Ciudad> listaCiudadesAerolinea) {
		this.listaCiudadesAerolinea = listaCiudadesAerolinea;
	}



	/**
	 * constructor vacio
	 */
	public Aerolinea() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * Crea todos los tripulantes y elementos necesarios basicos
	 * para que la aerolinea funcione, aviones, rutas, sillas, ciudades, etc.
	 */
	@SuppressWarnings("unchecked")
	public void inicializarDatos(){
		aniadir();
		 Ciudad ciudadMonterrey = new Ciudad();
		 ciudadMonterrey.setNombre("Monterrey");
		 listaCiudadesAerolinea.agregarInicio(ciudadMonterrey);
		 Ciudad ciudadCancun = new Ciudad();
		 ciudadCancun.setNombre("Cancun");
		 listaCiudadesAerolinea.agregarInicio(ciudadCancun);
		 Ciudad ciudadBuenosAires = new Ciudad();
		 ciudadBuenosAires.setNombre("Buenos Aires");
		 listaCiudadesAerolinea.agregarInicio(ciudadBuenosAires);
		 Ciudad ciudadLosAngeles = new Ciudad();
		 ciudadLosAngeles.setNombre("Los Angeles");
		 listaCiudadesAerolinea.agregarInicio(ciudadLosAngeles);
		 Ciudad ciudadBogota = new Ciudad();
		 ciudadBogota.setNombre("Bogota");
		 listaCiudadesAerolinea.agregarInicio(ciudadBogota);
		 Ciudad ciudadPanama = new Ciudad();
		 ciudadPanama.setNombre("Panama");
		 listaCiudadesAerolinea.agregarInicio(ciudadPanama);

		 try {
			 ObjectInputStream clienteFichero = new ObjectInputStream(new FileInputStream("src/archivos/clientes.dat"));
			 listaUsuarios = (TreeSet<Cliente>) clienteFichero.readObject();
			 clienteFichero.close();


			 ObjectInputStream rutasFichero = new ObjectInputStream(new FileInputStream("src/archivos/rutas.dat"));
			 listaRutas = (ArrayList<Ruta>) rutasFichero.readObject();
			 rutasFichero.close();

			 ObjectInputStream flotasArchivo = new ObjectInputStream(new FileInputStream("src/archivos/flotas.dat"));
			 listaFlotas = (ArrayList<Aeronave>) flotasArchivo.readObject();
			 flotasArchivo.close();

		

		 } catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }

	}
	//solo se debe llamar una vez para inicar la 
	public void aniadir() {
        TreeSet<Cliente> listaClientes = leerClientesDesdeArchivo();

        if (listaClientes.isEmpty()) {
            listaClientes.add(new Cliente("Camilo", "Lopez", "tebaida", "camilin@gmail.com", "952405", "24/11/2000", new ArrayList<>()));
            listaClientes.add(new Cliente("Aleks", "Cardona", "Coliseo", "Alesito@gmail.com", "446354", "17/11/2003", new ArrayList<>()));
            

            // Guarda la lista actualizada en el archivo
            escribirClientesEnArchivo(listaClientes);
        }
    }

    private TreeSet<Cliente> leerClientesDesdeArchivo() {
        TreeSet<Cliente> listaClientes = new TreeSet<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/archivos/clientes.dat"))) {
            listaClientes = (TreeSet<Cliente>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.err.println("Error: Archivo de clientes no encontrado.");
            e.printStackTrace();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al deserializar clientes.");
            e.printStackTrace();
        }
        return listaClientes;
    }

    private void escribirClientesEnArchivo(TreeSet<Cliente> listaClientes) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/archivos/clientes.dat"))) {
            oos.writeObject(listaClientes);
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo de clientes.");
            e.printStackTrace();
        }
    }


}
