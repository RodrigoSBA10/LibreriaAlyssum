package application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Libreria implements Serializable {
	private static final long serialVersionUID= 1L;
	private List<Libro> libros;
	public Libreria() {
		this.libros=new ArrayList<>();
	}
	public void agregarLibros(Libro nuevoLibro) {
		for(Libro libro : libros) {
			if (libro.getIsbn().equals(nuevoLibro.getIsbn())) {
				libro.setStock(libro.getStock() + nuevoLibro.getStock());
				return;
			}
		}
		libros.add(nuevoLibro);
	}
	public List<Libro> getLibros(){
		return new ArrayList<Libro>(libros);
	}
	public void cargarLibros(String archivo)throws IOException, ClassNotFoundException{
		try(FileInputStream fileOut= new FileInputStream(archivo); ObjectInputStream in = new ObjectInputStream(fileOut)){
			this.libros=(List<Libro>) in.readObject();
		}	
	}
	
	public void guardarLibros(String archivo)throws IOException{
		try(FileOutputStream fileout = new FileOutputStream(archivo);
				ObjectOutputStream out= new ObjectOutputStream(fileout)){
					out.writeObject(libros);
				}
	}
}
