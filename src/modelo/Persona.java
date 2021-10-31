
package modelo;

public class Persona {
    String nom;
    String ape;
    String correo;
    String contra;
    
    public Persona(){
        
    }

    public Persona(String nom, String ape, String correo, String contra) {
        this.nom = nom;
        this.ape = ape;
        this.correo = correo;
        this.contra = contra;
    }
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getApe() {
        return ape;
    }

    public void setApe(String ape) {
        this.ape = ape;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }
    
}
