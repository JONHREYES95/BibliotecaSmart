package logica;

import Persistencia.ControlPersistencia;
import java.util.List;
import logica.clasesYmetodos.Libro;
import logica.clasesYmetodos.Prestamo;
import logica.clasesYmetodos.MiListaPrestamos;

public class Logic {
    
    ControlPersistencia ControlP = new ControlPersistencia();
    // Estructura de datos personalizada para préstamos
    private MiListaPrestamos listaPrestamos = new MiListaPrestamos();

    public void GuardarLibro(String Titulo, String Autor, Integer NPaginas, 
            String Editorial, String ISBN, String FechaDePublicacion, String UnidadesDisponibles) {
        //Crear objeto libro en el formulario
        Libro Libr = new Libro();        
        Libr.setTitulo(Titulo);
        Libr.setAutor(Autor);
        Libr.setNPaginas(NPaginas);
        Libr.setEditorial(Editorial);
        Libr.setISBN(ISBN);
        Libr.setFechaDePublicacion(FechaDePublicacion);
        Libr.setUnidadesDisponibles(UnidadesDisponibles);
        //Llevarlo a nivel de persistencia
       ControlP.GuardarLibro(Libr);
    }
    
    //traer datos de BD y llevarlos a GUI
    public List<Libro> traerLibros() {
        return ControlP.traerLibros();
    }
    
    public Libro buscarLibroPorId(Long id) {
        return ControlP.buscarLibroPorId(id);
    }
    
    public void modificarLibro(Libro libro) {
        ControlP.modificarLibro(libro);
    }
    
    public void eliminarLibro(Long id) {
        ControlP.eliminarLibro(id);
    }
    
    // ========== SISTEMA DE PRÉSTAMOS ==========
    
    /**
     * Registra un nuevo préstamo usando la estructura de datos personalizada
     * @param nombrePersona Nombre de la persona
     * @param cedula Cédula de la persona
     * @param libroId ID del libro a prestar
     * @return true si se registró exitosamente, false si no hay unidades disponibles
     */
    public boolean registrarPrestamo(String nombrePersona, String cedula, Long libroId) {
        // Buscar el libro en la base de datos
        List<Libro> libros = ControlP.traerLibros();
        Libro libroEncontrado = null;
        
        for (Libro libro : libros) {
            if (libro.getId().equals(libroId)) {
                libroEncontrado = libro;
                break;
            }
        }
        
        if (libroEncontrado == null) {
            return false; // Libro no encontrado
        }
        
        // Verificar si hay unidades disponibles
        int unidadesDisponibles = Integer.parseInt(libroEncontrado.getUnidadesDisponibles());
        if (unidadesDisponibles <= 0) {
            return false; // No hay unidades disponibles
        }
        
        // No verificar si está prestado, solo verificar unidades disponibles
        // Un libro puede prestarse múltiples veces si tiene múltiples unidades
        
        // Crear el préstamo
        Prestamo nuevoPrestamo = new Prestamo(nombrePersona, cedula, libroEncontrado);
        
        // Guardar en BD con JPA
        ControlP.guardarPrestamo(nuevoPrestamo);
        
        // Agregar a la lista personalizada (para demostrar estructura de datos)
        listaPrestamos.agregarPrestamo(nuevoPrestamo);
        
        // Actualizar unidades disponibles en la base de datos
        libroEncontrado.setUnidadesDisponibles(String.valueOf(unidadesDisponibles - 1));
        ControlP.modificarLibro(libroEncontrado);
        
        return true;
    }
    
    /**
     * Devuelve un préstamo
     * @param prestamoId ID del préstamo a devolver
     * @return true si se devolvió exitosamente
     */
    public boolean devolverPrestamo(Long prestamoId) {
        // Buscar en BD con JPA
        Prestamo prestamo = ControlP.buscarPrestamoPorId(prestamoId);
        if (prestamo != null && prestamo.isActivo()) {
            // Marcar como devuelto
            prestamo.devolver();
            
            // Actualizar en BD con JPA
            ControlP.actualizarPrestamo(prestamo);
            
            // También actualizar en lista personalizada
            listaPrestamos.devolverPrestamo(prestamoId);
            
            // Actualizar unidades disponibles en la base de datos
            Libro libro = prestamo.getLibro();
            int unidadesActuales = Integer.parseInt(libro.getUnidadesDisponibles());
            libro.setUnidadesDisponibles(String.valueOf(unidadesActuales + 1));
            ControlP.modificarLibro(libro);
            
            return true;
        }
        return false;
    }
    
    /**
     * Obtiene todos los préstamos activos
     * @return Lista de préstamos activos
     */
    public List<Prestamo> obtenerPrestamosActivos() {
        // Obtener de BD con JPA
        List<Prestamo> prestamosBD = ControlP.traerPrestamosActivos();
        
        // También actualizar lista personalizada para sincronización
        listaPrestamos = new MiListaPrestamos();
        if (prestamosBD != null) {
            for (Prestamo p : prestamosBD) {
                listaPrestamos.agregarPrestamo(p);
            }
        }
        
        return prestamosBD;
    }
    
    /**
     * Obtiene todos los préstamos
     * @return Lista de todos los préstamos
     */
    public List<Prestamo> obtenerTodosLosPrestamos() {
        // Obtener de BD con JPA
        return ControlP.traerPrestamos();
    }
    
    /**
     * Busca préstamos por nombre
     * @param nombre Nombre a buscar
     * @return Lista de préstamos encontrados
     */
    public List<Prestamo> buscarPrestamosPorNombre(String nombre) {
        return listaPrestamos.buscarPorNombre(nombre);
    }
    
    /**
     * Busca préstamos por cédula
     * @param cedula Cédula a buscar
     * @return Lista de préstamos encontrados
     */
    public List<Prestamo> buscarPrestamosPorCedula(String cedula) {
        return listaPrestamos.buscarPorCedula(cedula);
    }
    
    /**
     * Verifica si un libro está disponible para préstamo
     * @param libroId ID del libro
     * @return true si está disponible, false si no
     */
    public boolean estaLibroDisponible(Long libroId) {
        List<Libro> libros = ControlP.traerLibros();
        for (Libro libro : libros) {
            if (libro.getId().equals(libroId)) {
                int unidades = Integer.parseInt(libro.getUnidadesDisponibles());
                // Solo verificar unidades disponibles, no si está prestado
                // porque las unidades ya se actualizan cuando se presta
                return unidades > 0;
            }
        }
        return false;
    }
}     
