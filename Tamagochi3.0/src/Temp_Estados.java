import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class Temp_Estados extends TimerTask {

    private int imagenTemp=0;
    static byte prioridad=0;
    int contador= 0;
    private InfoMascotas infoMascotas;
    private static final int TIEMPO_TOTAL = 10; // Establece el valor adecuado en segundos

    public Temp_Estados(InfoMascotas infoMascotas){
        this.infoMascotas = infoMascotas;
    }

    @Override
    public void run() {
        contador++;

        TamagotchiGui.TerminaPartida();

        TamagotchiGui.felicidadBarra.setValue(infoMascotas.getFelicidadMascota()-1);
        infoMascotas.setFelicidadMascota(infoMascotas.getFelicidadMascota()-1);

        TamagotchiGui.energiaBarra.setValue(infoMascotas.getEnergiaMascota()-1);
        infoMascotas.setEnergiaMascota(infoMascotas.getEnergiaMascota()-1);

        TamagotchiGui.hambreBarra.setValue(infoMascotas.getHambreMascota()+2);
        infoMascotas.setHambreMascota(infoMascotas.getHambreMascota()+2);

        TamagotchiGui.suciedadBarra.setValue(infoMascotas.getSuciedadMascota()+3);
        infoMascotas.setSuciedadMascota(infoMascotas.getSuciedadMascota()+3);


        if(contador%2!=0){
            TamagotchiGui.estadosMascota.setText("");
        }else{
            TamagotchiGui.aviso();
        }

    }

    TimerTask imagenBoton = new TimerTask() {
        @Override
        public void run() {
            try{
                if(prioridad==0){
                    TamagotchiGui.cambiarImagen("src\\Imagenes\\loboNormal.png");
                }

                if (prioridad == 1) {
                    TamagotchiGui.cambiarImagen("src\\Imagenes\\comiendo.png");
                    prioridad=0;
                }

                if (prioridad == 2) {
                    TamagotchiGui.cambiarImagen("src\\Imagenes\\ejercicio.png");
                    prioridad=0;
                }

                if (prioridad == 3) {
                    TamagotchiGui.cambiarImagen("src\\Imagenes\\bañando.png");
                    prioridad=0;
                }

                if (prioridad == 4) {
                    TamagotchiGui.cambiarImagen("src\\Imagenes\\durmiendo.png");
                    prioridad=0;
                }

                if(prioridad == 5){
                    imagenBoton.wait();
                }
            }catch (Exception e){}


        }
    };
}
