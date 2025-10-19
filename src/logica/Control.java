package logica;

import gui.Principal;

public class Control {
    
    public static void main(String[] args) {
        // Configurar encoding UTF-8 para caracteres latinoamericanos
        System.setProperty("file.encoding", "UTF-8");
        System.setProperty("sun.jnu.encoding", "UTF-8");
        
        // Configurar propiedades del sistema para Swing
        System.setProperty("swing.aatext", "true");
        System.setProperty("swing.defaultlaf", "javax.swing.plaf.nimbus.NimbusLookAndFeel");
        
        //Llamamos GUI , esta no debe ser cambiada.
        Principal vista = new Principal();        
        vista.setVisible(true);
        vista.setLocationRelativeTo(null);
    }
    
}
/*
public LibroJpaController(){
        emf = Persistence.createEntityManagerFactory("MediatecaSmartPU");
    }
*/