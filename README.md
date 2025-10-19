# 📚 MediatecaSmart - Sistema de Gestión de Biblioteca

## 🎯 Descripción del Proyecto

**MediatecaSmart** es un sistema de gestión de biblioteca desarrollado en Java que implementa un sistema de préstamos de libros utilizando estructuras de datos personalizadas. El proyecto demuestra el uso de **Lista Enlazada personalizada** para la gestión de préstamos, integrado con **JPA** y **MySQL** para la persistencia de datos.

## 🏗️ Arquitectura del Sistema

### **Estructura de Datos Personalizada**
- **MiListaPrestamos**: Implementación de Lista Enlazada desde cero para gestionar préstamos activos
- **Operaciones**: Agregar, buscar, eliminar y listar préstamos
- **Integración**: Sincronización con base de datos MySQL mediante JPA

### **Tecnologías Utilizadas**
- **Java Swing**: Interfaz gráfica de usuario
- **JPA (Java Persistence API)**: Mapeo objeto-relacional
- **MySQL**: Base de datos relacional
- **EclipseLink**: Implementación de JPA
- **NetBeans IDE**: Entorno de desarrollo

## 📋 Funcionalidades Implementadas

### **1. Gestión de Libros**
- ✅ Agregar nuevos libros al inventario
- ✅ Listar todos los libros con unidades disponibles
- ✅ Modificar información de libros existentes
- ✅ Eliminar libros del inventario
- ✅ Búsqueda de libros por título o ID

### **2. Sistema de Préstamos**
- ✅ Registrar préstamos de libros
- ✅ Listar préstamos activos usando Lista Enlazada personalizada
- ✅ Devolver libros prestados
- ✅ Control de unidades disponibles
- ✅ Validación de disponibilidad

### **3. Interfaz de Usuario**
- ✅ Ventana principal con navegación
- ✅ Formularios para agregar libros
- ✅ Tabla de inventario con funcionalidades CRUD
- ✅ Sistema de préstamos con interfaz intuitiva
- ✅ Diseño minimalista y consistente

## 🗂️ Estructura del Proyecto

```
MediatecaSmart/
├── src/
│   ├── gui/                           # Interfaces gráficas
│   │   ├── Principal.java             # Ventana principal
│   │   ├── SistemaPrestamos.java     # Sistema de préstamos
│   │   ├── ModificarMAT.java         # Inventario de libros
│   │   └── FormAgregarMaterialLib.java # Agregar libros
│   ├── logica/                       # Lógica de negocio
│   │   ├── Logic.java                # Lógica principal
│   │   ├── Control.java              # Controlador principal
│   │   └── clasesYmetodos/           # Clases de datos
│   │       ├── Libro.java            # Entidad Libro
│   │       ├── Prestamo.java         # Entidad Préstamo
│   │       └── MiListaPrestamos.java  # Lista Enlazada personalizada
│   ├── Persistencia/                 # Capa de persistencia
│   │   └── ControlPersistencia.java  # Controlador JPA
│   └── META-INF/
│       └── persistence.xml            # Configuración JPA
├── dist/lib/                         # Librerías JAR
├── build/                           # Archivos compilados
└── README.md                        # Este archivo
```

## 🚀 Instalación y Configuración

### **Requisitos Previos**
- Java JDK 8 o superior
- NetBeans IDE 8.2 o superior
- MySQL Server 5.7 o superior
- MySQL Workbench (opcional)

### **Pasos de Instalación**

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/JONHREYES95/BibliotecaSmart.git
   cd BibliotecaSmart
   ```

2. **Configurar Base de Datos**
   ```sql
   CREATE DATABASE mediateca CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
   ```

3. **Configurar Conexión**
   - Editar `src/META-INF/persistence.xml`
   - Actualizar credenciales de MySQL

4. **Compilar y Ejecutar**
   - Abrir proyecto en NetBeans
   - Clean and Build Project
   - Run Project (F6)

## 🎓 Conceptos Académicos Demostrados

### **Estructuras de Datos**
- **Lista Enlazada**: Implementación desde cero sin usar Collections de Java
- **Nodos y Enlaces**: Conceptos fundamentales de estructuras de datos
- **Operaciones CRUD**: Inserción, búsqueda, eliminación y listado

### **Programación Orientada a Objetos**
- **Encapsulación**: Clases con métodos públicos y privados
- **Herencia**: Relaciones entre entidades
- **Polimorfismo**: Uso de interfaces y clases abstractas

### **Persistencia de Datos**
- **JPA**: Mapeo objeto-relacional
- **Entidades**: Anotaciones @Entity, @Id, @GeneratedValue
- **Relaciones**: @ManyToOne, @JoinColumn

## 📊 Diagrama de Clases

```
┌─────────────────┐    ┌──────────────────┐    ┌─────────────────┐
│     Libro       │    │    Prestamo      │    │ MiListaPrestamos│
├─────────────────┤    ├──────────────────┤    ├─────────────────┤
│ - id: Long      │    │ - id: Long       │    │ - cabeza: Nodo  │
│ - titulo: String│◄───┤ - nombrePersona  │    │ - tamano: int    │
│ - autor: String │    │ - cedula: String │    ├─────────────────┤
│ - editorial     │    │ - libro: Libro   │    │ + agregar()     │
│ - isbn: String  │    │ - fechaPrestamo  │    │ + buscar()      │
│ - nPaginas: int │    │ - estado: String │    │ + eliminar()     │
│ - fechaPublic   │    └──────────────────┘    │ + listar()      │
│ - unidades: int │                              └─────────────────┘
└─────────────────┘
```

## 🔧 Funcionalidades Técnicas

### **Lista Enlazada Personalizada**
```java
public class MiListaPrestamos {
    private NodoPrestamo cabeza;
    private int tamano;
    
    public void agregarPrestamo(Prestamo prestamo) { ... }
    public Prestamo buscarPrestamoPorId(Long id) { ... }
    public boolean devolverPrestamo(Long id) { ... }
    public List<Prestamo> obtenerTodosLosPrestamos() { ... }
}
```

### **Integración JPA**
```java
@Entity
@Table(name = "LIBRO")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "TITULO")
    private String titulo;
    // ... más campos
}
```

## 🎯 Objetivos del Proyecto

1. **Demostrar comprensión** de estructuras de datos fundamentales
2. **Implementar solución real** para gestión de biblioteca
3. **Integrar tecnologías** modernas (JPA, MySQL, Swing)
4. **Aplicar conceptos** de programación orientada a objetos
5. **Desarrollar interfaz** de usuario intuitiva y funcional

## 👨‍💻 Autor

**Desarrollado por:** [Tu Nombre]  
**Curso:** Programación con Estructura de Datos  
**Institución:** [Nombre de la Universidad]  
**Año:** 2025

## 📝 Licencia

Este proyecto es desarrollado con fines académicos y educativos.

---

**¡MediatecaSmart - Donde la tecnología se encuentra con la literatura!** 📚✨
