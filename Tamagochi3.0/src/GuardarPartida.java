import java.util.TimerTask;

public class GuardarPartida extends TimerTask {

    public static String nomCuenta="";
    private InfoMascotas infoMascotas;
    public GuardarPartida(InfoMascotas infoMascotas){this.infoMascotas = infoMascotas;}
    @Override
    public void run() {
        int hm = infoMascotas.getHambreMascota();
        int fl = infoMascotas.getFelicidadMascota();
        int sc = infoMascotas.getSuciedadMascota();
        int en = infoMascotas.getEnergiaMascota();
        int lv = infoMascotas.getNivelMascota();
        int nv = infoMascotas.getLevel();
        InfoMascotas mascota = new InfoMascotas();
        InfoMascotas.guardarMascota(nomCuenta, mascota, hm, fl, sc, en, lv,nv);
    }
}
