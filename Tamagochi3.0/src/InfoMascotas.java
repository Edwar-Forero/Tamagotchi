import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class InfoMascotas implements Serializable {
    private static final long serialVersionUID = 8792177624268165094L;
    private int hambreMascota;
    private int felicidadMascota;
    private int suciedadMascota;
    private int energiaMascota;
    private int nivelMascota;
    private int Level;

    public InfoMascotas(){
    }

    public static boolean existeArchivo(String rutaArchivo) {
        File archivo = new File(rutaArchivo);
        return archivo.exists();
    }

    public static void guardarMascota(String nombreMascota, InfoMascotas mascota, int hm, int fl, int sc, int en, int lv,int nv) {
        String rutaArchivo = nombreMascota + ".bin";
        mascota.setHambreMascota(hm);
        mascota.setFelicidadMascota(fl);
        mascota.setSuciedadMascota(sc);
        mascota.setEnergiaMascota(en);
        mascota.setNivelMascota(lv);
        mascota.setLevel(nv);
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {
            outputStream.writeObject(mascota);
        } catch (IOException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al guardar la mascota");
        }


    }

    public static InfoMascotas cargarMascota(String nombreMascota) {
        InfoMascotas mascota = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(nombreMascota + ".bin"))) {
            mascota = (InfoMascotas) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar la mascota");
        }
        return mascota;
    }

    public static ArrayList<String> cargarCuentasGuardadas() {
        ArrayList<String> nombreCuenta = new ArrayList<>();
        File[] archivos = new File(".").listFiles((dir, name) -> name.endsWith(".bin"));

        for (File archivo : archivos) {
            String nombreArchivo = archivo.getName();
            String nombreMascota = nombreArchivo.substring(0, nombreArchivo.lastIndexOf('.'));
            nombreCuenta.add(nombreMascota);

        }
        return nombreCuenta;
    }

    public int getHambreMascota() {
        return hambreMascota;
    }

    public void setHambreMascota(int hambreMascota) {
        this.hambreMascota = hambreMascota;
    }

    public int getFelicidadMascota() {
        return felicidadMascota;
    }

    public void setFelicidadMascota(int felicidadMascota) {
        this.felicidadMascota = felicidadMascota;
    }

    public int getSuciedadMascota() {
        return suciedadMascota;
    }

    public void setSuciedadMascota(int suciedadMascota) {
        this.suciedadMascota = suciedadMascota;
    }

    public int getEnergiaMascota() {
        return energiaMascota;
    }

    public void setEnergiaMascota(int energiaMascota) {
        this.energiaMascota = energiaMascota;
    }

    public int getNivelMascota() {
        return nivelMascota;
    }

    public void setNivelMascota(int nivelMascota) {
        this.nivelMascota = nivelMascota;
    }

    public void setLevel(int Level){
        this.Level = Level;
    }

    public int getLevel(){
        return Level;
    }
}

