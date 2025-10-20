package logica.clasesYmetodos;
    
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Libro implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Titulo;
    private String Autor;
    private Integer NPaginas;
    private String Editorial;
    private String ISBN; //International Standard Book Number
    private String FechaDePublicacion;
    private String UnidadesDisponibles;

    public Libro() {
    }

    public Libro(Long id, String Autor, Integer NPaginas, String Editorial, String ISBN) {
        this.id = id;
        this.Autor = Autor;
        this.NPaginas = NPaginas;
        this.Editorial = Editorial;
        this.ISBN = ISBN;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAutor() {
        return Autor;
    }

    public void setAutor(String Autor) {
        this.Autor = Autor;
    }

    public Integer getNPaginas() {
        return NPaginas;
    }

    public void setNPaginas(Integer NPaginas) {
        this.NPaginas = NPaginas;
    }

    public String getEditorial() {
        return Editorial;
    }

    public void setEditorial(String Editorial) {
        this.Editorial = Editorial;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    public String getFechaDePublicacion() {
        return FechaDePublicacion;
    }

    public void setFechaDePublicacion(String FechaDePublicacion) {
        this.FechaDePublicacion = FechaDePublicacion;
    }

    public String getUnidadesDisponibles() {
        return UnidadesDisponibles;
    }

    public void setUnidadesDisponibles(String UnidadesDisponibles) {
        this.UnidadesDisponibles = UnidadesDisponibles;
    }
    
}
