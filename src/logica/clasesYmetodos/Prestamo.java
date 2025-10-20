package logica.clasesYmetodos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

/**
 * Clase para representar un préstamo de libro
 * Mapeada con JPA para persistencia en base de datos
 * @author hunks
 */
@Entity
public class Prestamo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombrePersona;
    private String cedula;
    
    @ManyToOne
    @JoinColumn(name = "libro_id")
    private Libro libro;
    
    private String fechaPrestamo;
    private String fechaDevolucion;
    private boolean activo;

    public Prestamo() {
        this.activo = true;
        this.fechaPrestamo = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public Prestamo(String nombrePersona, String cedula, Libro libro) {
        this();
        this.nombrePersona = nombrePersona;
        this.cedula = cedula;
        this.libro = libro;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public String getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(String fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public String getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(String fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    /**
     * Marca el préstamo como devuelto
     */
    public void devolver() {
        this.activo = false;
        this.fechaDevolucion = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    @Override
    public String toString() {
        return "Prestamo{" +
                "id=" + id +
                ", nombrePersona='" + nombrePersona + '\'' +
                ", cedula='" + cedula + '\'' +
                ", libro=" + (libro != null ? libro.getTitulo() : "N/A") +
                ", fechaPrestamo='" + fechaPrestamo + '\'' +
                ", activo=" + activo +
                '}';
    }
}
