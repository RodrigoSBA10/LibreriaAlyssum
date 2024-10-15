package application.persistencia;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import application.modelo.Libro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LibroArchivo {
	final static String FILE_NAME = "LibrosData.dat";
	static ObservableList<Libro> listLibro = FXCollections.observableArrayList();
	
	public static void load() {
		
		ObjectInputStream inStream = null;
		
		try {
			
			inStream = new ObjectInputStream(new FileInputStream(FILE_NAME));
			Libro[] arrayProductos = (Libro[]) inStream.readObject();
			listLibro.clear();
			listLibro.addAll(arrayProductos);
			inStream.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Regresa la lista de productos
	public static ObservableList<Libro> getAll() {
		return listLibro;
	}
	
	//Agrega un producto a la lista
	public static void add(Libro p) {
		listLibro.add(p);
	}
	
	//Guarda la lista de Productos en el Archivo
	public static void save() {
		
		ObjectOutputStream outStream = null;
		
		try {
			Libro[] arrayProductos = listLibro.toArray(new Libro[0]);
			outStream = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
			outStream.writeObject(arrayProductos);
			outStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Borra lo que tiene la lista
	public static void delete() {
		listLibro.clear();
	}
	
	//Borra el Producto del archivo
	public static boolean delete(Libro lib) {
		return false;
	}
	
	//Busca el nombre de la lista de Libros y regresa el objeto
	public static Libro buscar(String nombre) {
		return null;
	}
}
