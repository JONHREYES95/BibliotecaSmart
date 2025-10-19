package logica.clasesYmetodos;

import java.util.ArrayList;
import java.util.List;

/**
 * Lista Enlazada Personalizada para manejar préstamos
 * Estructura de datos implementada desde cero
 * @author hunks
 */
public class MiListaPrestamos {
    
    // Nodo interno para la lista enlazada
    private static class NodoPrestamo {
        Prestamo prestamo;
        NodoPrestamo siguiente;
        
        public NodoPrestamo(Prestamo prestamo) {
            this.prestamo = prestamo;
            this.siguiente = null;
        }
    }
    
    private NodoPrestamo cabeza;
    private int tamano;
    
    public MiListaPrestamos() {
        this.cabeza = null;
        this.tamano = 0;
    }
    
    /**
     * Agrega un préstamo al final de la lista
     * @param prestamo El préstamo a agregar
     */
    public void agregarPrestamo(Prestamo prestamo) {
        NodoPrestamo nuevoNodo = new NodoPrestamo(prestamo);
        
        if (cabeza == null) {
            // Lista vacía
            cabeza = nuevoNodo;
        } else {
            // Buscar el último nodo
            NodoPrestamo actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }
        tamano++;
    }
    
    /**
     * Busca un préstamo por ID
     * @param id ID del préstamo a buscar
     * @return El préstamo encontrado o null si no existe
     */
    public Prestamo buscarPorId(Long id) {
        NodoPrestamo actual = cabeza;
        
        while (actual != null) {
            if (actual.prestamo.getId().equals(id)) {
                return actual.prestamo;
            }
            actual = actual.siguiente;
        }
        return null;
    }
    
    /**
     * Marca un préstamo como devuelto
     * @param id ID del préstamo a devolver
     * @return true si se encontró y devolvió, false si no existe
     */
    public boolean devolverPrestamo(Long id) {
        Prestamo prestamo = buscarPorId(id);
        if (prestamo != null && prestamo.isActivo()) {
            prestamo.devolver();
            return true;
        }
        return false;
    }
    
    /**
     * Obtiene todos los préstamos activos
     * @return Lista de préstamos activos
     */
    public List<Prestamo> listarPrestamosActivos() {
        List<Prestamo> prestamosActivos = new ArrayList<>();
        NodoPrestamo actual = cabeza;
        
        while (actual != null) {
            if (actual.prestamo.isActivo()) {
                prestamosActivos.add(actual.prestamo);
            }
            actual = actual.siguiente;
        }
        return prestamosActivos;
    }
    
    /**
     * Obtiene todos los préstamos (activos e inactivos)
     * @return Lista de todos los préstamos
     */
    public List<Prestamo> listarTodosLosPrestamos() {
        List<Prestamo> todosLosPrestamos = new ArrayList<>();
        NodoPrestamo actual = cabeza;
        
        while (actual != null) {
            todosLosPrestamos.add(actual.prestamo);
            actual = actual.siguiente;
        }
        return todosLosPrestamos;
    }
    
    /**
     * Verifica si un libro está prestado
     * @param libroId ID del libro a verificar
     * @return true si está prestado, false si no
     */
    public boolean estaLibroPrestado(Long libroId) {
        NodoPrestamo actual = cabeza;
        
        while (actual != null) {
            if (actual.prestamo.isActivo() && 
                actual.prestamo.getLibro() != null && 
                actual.prestamo.getLibro().getId().equals(libroId)) {
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }
    
    /**
     * Obtiene el tamaño de la lista
     * @return Número de elementos en la lista
     */
    public int getTamano() {
        return tamano;
    }
    
    /**
     * Verifica si la lista está vacía
     * @return true si está vacía, false si no
     */
    public boolean estaVacia() {
        return cabeza == null;
    }
    
    /**
     * Busca préstamos por nombre de persona
     * @param nombre Nombre a buscar
     * @return Lista de préstamos de esa persona
     */
    public List<Prestamo> buscarPorNombre(String nombre) {
        List<Prestamo> prestamosEncontrados = new ArrayList<>();
        NodoPrestamo actual = cabeza;
        
        while (actual != null) {
            if (actual.prestamo.getNombrePersona().toLowerCase().contains(nombre.toLowerCase())) {
                prestamosEncontrados.add(actual.prestamo);
            }
            actual = actual.siguiente;
        }
        return prestamosEncontrados;
    }
    
    /**
     * Busca préstamos por cédula
     * @param cedula Cédula a buscar
     * @return Lista de préstamos de esa cédula
     */
    public List<Prestamo> buscarPorCedula(String cedula) {
        List<Prestamo> prestamosEncontrados = new ArrayList<>();
        NodoPrestamo actual = cabeza;
        
        while (actual != null) {
            if (actual.prestamo.getCedula().equals(cedula)) {
                prestamosEncontrados.add(actual.prestamo);
            }
            actual = actual.siguiente;
        }
        return prestamosEncontrados;
    }
}
