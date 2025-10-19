package Persistencia;

import java.util.List;
import logica.clasesYmetodos.Libro;
import logica.clasesYmetodos.Prestamo;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * Control de persistencia con JPA para MySQL
 * @author hunks
 */
public class ControlPersistencia {
    
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("MediatecaSmartPU");
    private EntityManager em = emf.createEntityManager();

    public ControlPersistencia() {
    }

    public void GuardarLibro(Libro libro) {
        try {
            em.getTransaction().begin();
            em.persist(libro);
            em.getTransaction().commit();
            System.out.println("Libro guardado en BD: " + libro.getTitulo());
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al guardar libro: " + e.getMessage());
        }
    }

    public List<Libro> traerLibros() {
        try {
            em.clear(); // Limpiar caché antes de consultar
            Query query = em.createQuery("SELECT l FROM Libro l", Libro.class);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Error al traer libros: " + e.getMessage());
            return null;
        }
    }
    
    public Libro buscarLibroPorId(Long id) {
        try {
            Libro libro = em.find(Libro.class, id);
            if (libro != null) {
                em.refresh(libro); // Forzar actualización desde la BD
            }
            return libro;
        } catch (Exception e) {
            System.err.println("Error al buscar libro: " + e.getMessage());
            return null;
        }
    }
    
    public void modificarLibro(Libro libro) {
        try {
            em.getTransaction().begin();
            em.merge(libro);
            em.getTransaction().commit();
            System.out.println("Libro modificado en BD: " + libro.getTitulo());
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al modificar libro: " + e.getMessage());
        }
    }
    
    public void eliminarLibro(Long id) {
        try {
            em.getTransaction().begin();
            Libro libro = em.find(Libro.class, id);
            if (libro != null) {
                em.remove(libro);
            }
            em.getTransaction().commit();
            System.out.println("Libro eliminado de BD: " + id);
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al eliminar libro: " + e.getMessage());
        }
    }
    
    
    // Métodos para Préstamos
    public void guardarPrestamo(Prestamo prestamo) {
        try {
            em.getTransaction().begin();
            em.persist(prestamo);
            em.getTransaction().commit();
            System.out.println("Préstamo guardado en BD: " + prestamo.getNombrePersona());
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al guardar préstamo: " + e.getMessage());
        }
    }
    
    public List<Prestamo> traerPrestamos() {
        try {
            Query query = em.createQuery("SELECT p FROM Prestamo p", Prestamo.class);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Error al traer préstamos: " + e.getMessage());
            return null;
        }
    }
    
    public List<Prestamo> traerPrestamosActivos() {
        try {
            Query query = em.createQuery("SELECT p FROM Prestamo p WHERE p.activo = true", Prestamo.class);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Error al traer préstamos activos: " + e.getMessage());
            return null;
        }
    }
    
    public Prestamo buscarPrestamoPorId(Long id) {
        try {
            return em.find(Prestamo.class, id);
        } catch (Exception e) {
            System.err.println("Error al buscar préstamo: " + e.getMessage());
            return null;
        }
    }
    
    public void actualizarPrestamo(Prestamo prestamo) {
        try {
            em.getTransaction().begin();
            em.merge(prestamo);
            em.getTransaction().commit();
            System.out.println("Préstamo actualizado en BD: " + prestamo.getId());
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al actualizar préstamo: " + e.getMessage());
        }
    }
    
    public void cerrarConexion() {
        if (em != null) {
            em.close();
        }
        if (emf != null) {
            emf.close();
        }
    }
}