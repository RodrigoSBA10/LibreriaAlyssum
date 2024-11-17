package conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import poo.Libro;

public class BusquedaBD {

    ConectarBD con = new ConectarBD();

    // Método para cargar libros
    public ObservableList<Libro> cargarLibros() {
        ObservableList<Libro> listaLibros = FXCollections.observableArrayList();
        String sql = "SELECT l.*, g.nombre AS nombre_genero, c.nombre AS nombre_categoria FROM libro l LEFT JOIN genero g ON l.id_genero = g.id_genero LEFT JOIN categoria c ON l.id_categoria = c.id_categoria";

        try (Connection conn = con.ConectarBDOracle(); 
             Statement stmt = conn.createStatement(); 
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Libro libro = new Libro(
                    rs.getString("titulo"),
                    rs.getString("autor"),
                    rs.getString("isbn"),
                    rs.getString("editorial"),
                    rs.getInt("id_genero"),  
                    rs.getInt("id_categoria"), 
                    rs.getString("anio"),
                    rs.getDouble("precio"),
                    rs.getInt("stock")
                );
                // Asigna los nombres de categoría y género
                libro.setCategoria(rs.getString("nombre_categoria"));
                libro.setGenero(rs.getString("nombre_genero"));

                listaLibros.add(libro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaLibros; 
    }

    // Método para cargar las categorías de la base de datos
    public ObservableList<String> cargarCategorias() {
        ObservableList<String> categorias = FXCollections.observableArrayList();
        Connection conection = con.ConectarBDOracle();

        String sql = "SELECT nombre FROM categoria";

        try (PreparedStatement pstmt = conection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                categorias.add(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            System.out.println("Error al cargar categorías: " + e.getMessage());
        } finally {
            try {
                if (conection != null && !conection.isClosed()) {
                    conection.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error al cerrar la conexión: " + ex.getMessage());
            }
        }
        return categorias; 
    }

    // Método para cargar los géneros
    public ObservableList<String> cargarGeneros() {
        ObservableList<String> generos = FXCollections.observableArrayList();
        Connection conection = con.ConectarBDOracle();

        String sql = "SELECT nombre FROM genero";

        try (PreparedStatement pstmt = conection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                generos.add(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            System.out.println("Error al cargar géneros: " + e.getMessage());
        } finally {
            try {
                if (conection != null && !conection.isClosed()) {
                    conection.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error al cerrar la conexión: " + ex.getMessage());
            }
        }
        return generos; 
    }
 // Método para eliminar el libro de la base de datos
    public void eliminarLibro(Libro libro) {
        String sql = "DELETE FROM libro WHERE isbn = ?"; 
        try (Connection conection = con.ConectarBDOracle();
             PreparedStatement pstmt = conection.prepareStatement(sql)) {
            pstmt.setString(1, libro.getIsbn()); 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar el libro de la base de datos: " + e.getMessage());
        }
    }
    
    public ObservableList<Libro> buscarLibros(String titulo, String autor, String isbn, String editorial, Integer categoria, Integer genero, String anio, Double precio) {
        ObservableList<Libro> librosEncontrados = FXCollections.observableArrayList();
        StringBuilder sql = new StringBuilder("SELECT l.*, g.nombre AS nombre_genero, c.nombre AS nombre_categoria FROM libro l LEFT JOIN genero g ON l.id_genero = g.id_genero LEFT JOIN categoria c ON l.id_categoria = c.id_categoria WHERE 1=1");
        List<Object> params = new ArrayList<>();

        // Agregar condiciones
        if (titulo != null && !titulo.isEmpty()) {
            sql.append(" AND LOWER(titulo) LIKE LOWER(?)");
            params.add("%" + titulo + "%");
        }
        if (autor != null && !autor.isEmpty()) {
            sql.append(" AND LOWER(autor) LIKE LOWER(?)");
            params.add("%" + autor + "%");
        }
        if (isbn != null && !isbn.isEmpty()) {
            sql.append(" AND LOWER(isbn) LIKE LOWER(?)");
            params.add("%" + isbn + "%");
        }
        if (editorial != null && !editorial.isEmpty()) {
            sql.append(" AND LOWER(editorial) LIKE LOWER(?)");
            params.add("%" + editorial + "%");
        }
        if (categoria != null) {
            sql.append(" AND id_categoria = ?");
            params.add(categoria);
        }
        if (genero != null) {
            sql.append(" AND id_genero = ?");
            params.add(genero);
        }
        if (anio != null && !anio.isEmpty()) {
            sql.append(" AND LOWER(anio) = LOWER(?)");
            params.add(anio);
        }
        if (precio != null) {
            sql.append(" AND precio <= ?");
            params.add(precio);
        }

        // Ejecutar consulta
        try (Connection connection = con.ConectarBDOracle();
             PreparedStatement statement = connection.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                statement.setObject(i + 1, params.get(i));
            }

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Libro libro = new Libro();
                    libro.setTitulo(resultSet.getString("titulo"));
                    libro.setAutor(resultSet.getString("autor"));
                    libro.setIsbn(resultSet.getString("isbn"));
                    libro.setEditorial(resultSet.getString("editorial"));
                    libro.setAnio(resultSet.getString("anio"));
                    libro.setPrecio(resultSet.getDouble("precio"));
                    libro.setStock(resultSet.getInt("stock"));

                    // Asigna los nombres de categoría y género
                    libro.setCategoria(resultSet.getString("nombre_categoria"));
                    libro.setGenero(resultSet.getString("nombre_genero"));

                    librosEncontrados.add(libro);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return librosEncontrados;
    }
    
    public boolean actualizarLibro(Libro libro) {
        String sql = "UPDATE libro SET titulo = ?, autor = ?, editorial = ?, id_genero = ?, id_categoria = ?, anio = ?, precio = ?, stock = ? WHERE isbn = ?";
        
        try (Connection conection = con.ConectarBDOracle();
             PreparedStatement pstmt = conection.prepareStatement(sql)) {

            pstmt.setString(1, libro.getTitulo());
            pstmt.setString(2, libro.getAutor());
            pstmt.setString(3, libro.getEditorial());
            pstmt.setInt(4, libro.getIdGenero());
            pstmt.setInt(5, libro.getIdCategoria());
            pstmt.setString(6, libro.getAnio());
            pstmt.setDouble(7, libro.getPrecio());
            pstmt.setInt(8, libro.getStock());
            pstmt.setString(9, libro.getIsbn()); // Usamos el ISBN como clave

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; // Retorna true si se actualizó al menos una fila

        } catch (SQLException e) {
            System.out.println("Error al actualizar el libro: " + e.getMessage());
            return false; // Manejo del error
        }
    }
    
    public int obtenerIdCategoria(String categoria) {
        String sql = "SELECT id_categoria FROM categoria WHERE nombre = ?";
        try (Connection conn = con.ConectarBDOracle();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, categoria);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id_categoria");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener ID de categoría: " + e.getMessage());
        }
        return -1; // Retorna -1 si no se encuentra
    }

    public int obtenerIdGenero(String genero) {
        String sql = "SELECT id_genero FROM genero WHERE nombre = ?";
        try (Connection conn = con.ConectarBDOracle();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, genero);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id_genero");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener ID de género: " + e.getMessage());
        }
        return -1; // Retorna -1 si no se encuentra
    }




	public Integer getCategoriaIdPorNombre(String nombreCategoria) {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer getGeneroIdPorNombre(String nombreGenero) {
		// TODO Auto-generated method stub
		return null;
	}


}
