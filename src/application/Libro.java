package application;

public class Libro {
	String titulo;
	String autor;
	String genero;
	
	public Libro(String tit, String aut, String gen) {
		this.titulo = tit;
		this.autor = aut;
		this.genero = gen;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}
}
