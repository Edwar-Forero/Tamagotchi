import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;

public class TamagotchiGui extends JFrame {
    JPanel panel = new JPanel();
    public static JProgressBar hambreBarra, felicidadBarra, suciedadBarra, energiaBarra, nivelBarra;
    JLabel lblCantNivel = new JLabel();
    static JTextArea estadosMascota;
    static JLabel lblImagen;
    private InfoMascotas infoMascotas;
    public static ImageIcon Imagen = new ImageIcon("src\\Imagenes\\loboNormal.png");
    Color colorLetras = new Color(255, 255, 255, 255);
    static JButton btnDormir = new JButton("DORMIR");
    static JButton btnBanar = new JButton("BAÑAR");
    static JButton btnEntrenar = new JButton("ENTRENAR");
    static JButton btnAlimentar = new JButton("ALIMENTAR");
    public static Timer imagenTimer = new Timer();
    private ImageIcon imagenOriginal;
    public TamagotchiGui(InfoMascotas infoMascotas){
        setTitle("TAMAGOTCHI");
        setSize(700,600);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Color customColor = new Color(15, 37, 72, 255);
        panel.setBackground(customColor);
        panel.setLayout(null);
        getContentPane().add(panel);
        this.infoMascotas = infoMascotas;
        componentesVentana();

    }
    public void componentesVentana(){
        etiquetas();
        barrasDeParamentros();
        botones();
        //imagenTimer = new Timer(1000, null);
        imagenOriginal = Imagen;

    }

    public void etiquetas(){
        JLabel lblHambre = new JLabel("Hambre");
        lblHambre.setBounds(10, 460, 100, 20);
        lblHambre.setForeground(colorLetras);
        panel.add(lblHambre);

        JLabel lblFelicidad = new JLabel("Felicidad");
        lblFelicidad.setBounds(460, 460, 100, 20);
        lblFelicidad.setForeground(colorLetras);
        panel.add(lblFelicidad);

        JLabel lblSuciedad = new JLabel("Suciedad");
        lblSuciedad.setBounds(10, 500, 100, 20);
        lblSuciedad.setForeground(colorLetras);
        panel.add(lblSuciedad);

        JLabel lblEnergia = new JLabel("Energía");
        lblEnergia.setBounds(460, 500, 100, 20);
        lblEnergia.setForeground(colorLetras);
        panel.add(lblEnergia);

        JLabel lblNivel = new JLabel("Nivel");
        lblNivel.setBounds(350, 20, 100, 20);
        lblNivel.setForeground(colorLetras);
        panel.add(lblNivel);

        lblCantNivel.setBounds(600,15,50, 35);
        Font fuente = new Font("Arial", Font.BOLD, 30);
        lblCantNivel.setForeground(colorLetras);
        lblCantNivel.setFont(fuente);
        lblCantNivel.setText(String.valueOf(infoMascotas.getLevel()));
        panel.add(lblCantNivel);

        lblImagen = new JLabel();
        lblImagen.setBounds(100, 110, 500, 300);
        //lblImagen.setIcon(new ImageIcon(Imagen.getImage().getScaledInstance(500, 300, Image.SCALE_SMOOTH)));
        panel.add(lblImagen);

        // Cargar la imagen en un hilo separado utilizando SwingWorker
        SwingWorker<ImageIcon, Void> worker = new SwingWorker<ImageIcon, Void>() {
            @Override
            protected ImageIcon doInBackground() throws Exception {
                return new ImageIcon(Imagen.getImage().getScaledInstance(500, 300, Image.SCALE_SMOOTH));
            }
            @Override
            protected void done() {
                try {
                    ImageIcon imagen = get();
                    lblImagen.setIcon(imagen);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        worker.execute();

        estadosMascota = new JTextArea();
        Font fuente2 = new Font("Arial", Font.BOLD, 12);
        estadosMascota.setFont(fuente2);
        estadosMascota.setBounds(235,450,210,50);
        estadosMascota.setBackground(Color.getHSBColor(100,100,110));
        estadosMascota.setBorder(BorderFactory.createCompoundBorder(estadosMascota.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        estadosMascota.setEditable(false);
        panel.add(estadosMascota);
    }

    public void barrasDeParamentros() {
        hambreBarra = new JProgressBar(0, 100);
        hambreBarra.setBounds(70, 465, 150, 15);
        panel.add(hambreBarra);

        felicidadBarra = new JProgressBar(0, 130);
        felicidadBarra.setBounds(520, 465, 150, 15);
        panel.add(felicidadBarra);

        suciedadBarra = new JProgressBar(0, 100);
        suciedadBarra.setBounds(70, 505, 150, 15);
        panel.add(suciedadBarra);

        energiaBarra = new JProgressBar(0, 100);
        energiaBarra.setBounds(520, 505, 150, 15);
        panel.add(energiaBarra);

        nivelBarra = new JProgressBar(0, 150);
        nivelBarra.setBounds(390, 25, 150, 15);
        panel.add(nivelBarra);
    }

    public void botones(){
        // Alimentarlo, entrenarlo, bañarlo, acostarlo a dormir

        btnAlimentar.setBounds(100,80,120, 25);
        panel.add(btnAlimentar);

        btnAlimentar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (infoMascotas.getHambreMascota()>=4){
                    hambreBarra.setValue(infoMascotas.getHambreMascota()-4);
                    infoMascotas.setHambreMascota(infoMascotas.getHambreMascota()-4);

                    felicidadBarra.setValue(infoMascotas.getFelicidadMascota()+2);
                    infoMascotas.setFelicidadMascota(infoMascotas.getFelicidadMascota()+2);

                    nivelBarra.setValue(infoMascotas.getNivelMascota()+2);
                    infoMascotas.setNivelMascota(infoMascotas.getNivelMascota()+2);

                    subirNivel();
                }
                else{
                    JOptionPane.showMessageDialog(null, "La mascota no tiene hambre");
                }

                if(hambreBarra.getValue()<=80){
                    Temp_Estados.prioridad=1;
                }
            }
        });

        btnEntrenar.setBounds(480,80,120,25);
        panel.add(btnEntrenar);

        btnEntrenar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (infoMascotas.getNivelMascota()>=0){
                    nivelBarra.setValue(infoMascotas.getNivelMascota()+4);
                    infoMascotas.setNivelMascota(infoMascotas.getNivelMascota()+4);

                    felicidadBarra.setValue(infoMascotas.getFelicidadMascota()+1);
                    infoMascotas.setFelicidadMascota(infoMascotas.getFelicidadMascota()+1);

                    energiaBarra.setValue(infoMascotas.getEnergiaMascota()-2);
                    infoMascotas.setEnergiaMascota(infoMascotas.getEnergiaMascota()-2);

                    subirNivel();
                }
                Temp_Estados.prioridad=2;
            }
        });

        btnBanar.setBounds(100,420,120,25);
        panel.add(btnBanar);

        btnBanar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (infoMascotas.getSuciedadMascota()>=10){
                    suciedadBarra.setValue(infoMascotas.getSuciedadMascota()-6);
                    infoMascotas.setSuciedadMascota(infoMascotas.getSuciedadMascota()-6);

                    felicidadBarra.setValue(infoMascotas.getFelicidadMascota()+1);
                    infoMascotas.setFelicidadMascota(infoMascotas.getFelicidadMascota()+1);

                    nivelBarra.setValue(infoMascotas.getNivelMascota()+2);
                    infoMascotas.setNivelMascota(infoMascotas.getNivelMascota()+2);

                    subirNivel();
                }
                else{
                    JOptionPane.showMessageDialog(null,"La mascota no está suficientemente sucia");
                }
                if (suciedadBarra.getValue()<=85){
                    Temp_Estados.prioridad=3;
                }
            }
        });


        btnDormir.setBounds(480,420,125,25);
        panel.add(btnDormir);

        btnDormir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (infoMascotas.getEnergiaMascota()<=95){
                    energiaBarra.setValue(infoMascotas.getEnergiaMascota()+5);
                    infoMascotas.setEnergiaMascota(infoMascotas.getEnergiaMascota()+5);

                    felicidadBarra.setValue(infoMascotas.getFelicidadMascota()+2);
                    infoMascotas.setFelicidadMascota(infoMascotas.getFelicidadMascota()+2);

                    nivelBarra.setValue(infoMascotas.getNivelMascota()+2);
                    infoMascotas.setNivelMascota(infoMascotas.getNivelMascota()+2);

                    subirNivel();
                }
                else{
                    JOptionPane.showMessageDialog(null,"La mascota no tiene sueño");
                }

                if(energiaBarra.getValue()>=15){
                    Temp_Estados.prioridad=4;
                }
            }
        });
    }

    public void actualizarBarrasDeParametros() {
        hambreBarra.setValue(infoMascotas.getHambreMascota());
        felicidadBarra.setValue(infoMascotas.getFelicidadMascota());
        suciedadBarra.setValue(infoMascotas.getSuciedadMascota());
        energiaBarra.setValue(infoMascotas.getEnergiaMascota());
        nivelBarra.setValue(infoMascotas.getNivelMascota());
    }

    public static void aviso() {
        estadosMascota.setAlignmentY(Component.CENTER_ALIGNMENT);
        estadosMascota.setAlignmentX(Component.CENTER_ALIGNMENT);
        ImageIcon dimensImagen;
        String mensaje="";
        if (TamagotchiGui.suciedadBarra.getValue()>85) {
            Temp_Estados.prioridad=5;
            mensaje = GuardarPartida.nomCuenta+" se encuentra muy sucio";
            estadosMascota.setText(mensaje);

            TamagotchiGui.Imagen = new ImageIcon("src\\Imagenes\\mugre.png");
            dimensImagen = new ImageIcon(Imagen.getImage().getScaledInstance(500, 300, Image.SCALE_SMOOTH));
            lblImagen.setIcon(dimensImagen);
        }

        if (TamagotchiGui.hambreBarra.getValue()>80) {
            Temp_Estados.prioridad=5;
            mensaje = GuardarPartida.nomCuenta+" empieza a tener hambre";
            estadosMascota.setText(mensaje);

            TamagotchiGui.Imagen = new ImageIcon("src\\Imagenes\\Hambre.png");
            dimensImagen = new ImageIcon(Imagen.getImage().getScaledInstance(500, 300, Image.SCALE_SMOOTH));
            lblImagen.setIcon(dimensImagen);
        }

        if(TamagotchiGui.energiaBarra.getValue()<15){
            Temp_Estados.prioridad=5;
            mensaje = GuardarPartida.nomCuenta+" se encuentra cansado";
            estadosMascota.setText(mensaje);

            TamagotchiGui.Imagen = new ImageIcon("src\\Imagenes\\cansado.png");
            dimensImagen = new ImageIcon(Imagen.getImage().getScaledInstance(500, 300, Image.SCALE_SMOOTH));
            lblImagen.setIcon(dimensImagen);
        }

        if(TamagotchiGui.felicidadBarra.getValue()<20){
            Temp_Estados.prioridad=5;
            mensaje = GuardarPartida.nomCuenta+" está algo aburrido";
            estadosMascota.setText(mensaje);

            TamagotchiGui.Imagen = new ImageIcon("src\\Imagenes\\triste.png");
            dimensImagen = new ImageIcon(Imagen.getImage().getScaledInstance(500, 300, Image.SCALE_SMOOTH));
            lblImagen.setIcon(dimensImagen);
        }
    }

    public void subirNivel(){
        if (nivelBarra.getValue() >= nivelBarra.getMaximum()) {
            infoMascotas.setLevel(infoMascotas.getLevel() + 1);
            infoMascotas.setNivelMascota(0);
            lblCantNivel.setText(String.valueOf(infoMascotas.getLevel()));
            int nv = infoMascotas.getLevel();
            InfoMascotas mascota = new InfoMascotas();
            InfoMascotas.guardarMascota(GuardarPartida.nomCuenta, mascota,0,100, 0,100,0,nv);

            cambiarImagen("src\\Imagenes\\SubirNivel.png");
        }
    }

    public static void cambiarImagen(String ruta) {
        Imagen = new ImageIcon(ruta);
        lblImagen.setIcon(new ImageIcon(Imagen.getImage().getScaledInstance(500, 300, Image.SCALE_SMOOTH)));
    }

    public static void TerminaPartida(){
        ImageIcon dimensImagen;
        if(hambreBarra.getValue() >= hambreBarra.getMaximum() &&
                felicidadBarra.getValue() <= felicidadBarra.getMinimum() && +
                + suciedadBarra.getValue() >= suciedadBarra.getMaximum() && +
                + energiaBarra.getValue() <= energiaBarra.getMinimum())
        {
            System.out.println("la mascota murio");
            btnAlimentar.setEnabled(false);
            btnDormir.setEnabled(false);
            btnBanar.setEnabled(false);
            btnEntrenar.setEnabled(false);

            TamagotchiGui.Imagen = new ImageIcon("src\\Imagenes\\muerto.png");
            dimensImagen = new ImageIcon(Imagen.getImage().getScaledInstance(500, 300, Image.SCALE_SMOOTH));
            lblImagen.setIcon(dimensImagen);

            InicioSesion.timer.cancel();
            TamagotchiGui.imagenTimer.cancel();


        }
    }


}
