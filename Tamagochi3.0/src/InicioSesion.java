import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Timer;
import java.util.TimerTask;

public class InicioSesion extends JFrame {

    JPanel panel = new JPanel();
    private TamagotchiGui tamagotchiGui;
    JComboBox jcbPartidas = new JComboBox();
    ImageIcon Imagen = new ImageIcon("src\\Imagenes\\LogoTamagotchi.png");
    JTextField txtNombre = new JTextField();
    public static Timer timer = new Timer();


    public InicioSesion()
    {
        setTitle("Inicio de sesion: Tamagotchi");
        setSize(380, 520);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel.setLayout(null);
        Color customColor = new Color(10, 63, 239, 115); // Definir el color personalizado (valores RGB)
        panel.setBackground(customColor);
        getContentPane().add(panel);
        componetesVentana();
    }

    public void componetesVentana(){
        etiquetas();
        botones();
    }

    public void etiquetas(){
        JLabel lblTitulo = new JLabel("TAMAGOTCHI");
        lblTitulo.setBounds(140,10,90,25);
        panel.add(lblTitulo);

        JLabel lblEleccion = new JLabel("NOMBRE DE LA MASCOTA");
        lblEleccion.setBounds(10,240,170,25);
        panel.add(lblEleccion);

        JLabel lblImagen = new JLabel();
        lblImagen.setBounds(85, 40, 200, 200);
        panel.add(lblImagen);

        // Cargar la imagen en un hilo separado utilizando SwingWorker
        SwingWorker<ImageIcon, Void> worker = new SwingWorker<ImageIcon, Void>() {
            @Override
            protected ImageIcon doInBackground() throws Exception {
                return new ImageIcon(Imagen.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));
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

        txtNombre.setBounds(190,245,130,20);
        panel.add(txtNombre);

        jcbPartidas.setBounds(10,300,130,20);
        panel.add(jcbPartidas);

        for (String nomC : InfoMascotas.cargarCuentasGuardadas()){
            jcbPartidas.addItem(nomC);
        }
    }

    public void botones(){
        JButton btnCrearMascota = new JButton("Crear Mascota");
        btnCrearMascota.setBounds(190,270,120,25);
        panel.add(btnCrearMascota);

        btnCrearMascota.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean aviso = true;
                String nombreMascota = txtNombre.getText().trim();
                if (!nombreMascota.isEmpty()) {
                    for (String nomC : InfoMascotas.cargarCuentasGuardadas()){
                        if (nomC.equals(nombreMascota)){
                            aviso = true;
                            break;
                        }
                        else{
                            aviso = false;
                        }
                    }

                    if (!aviso){
                        InfoMascotas mascota = new InfoMascotas();
                        InfoMascotas.guardarMascota(nombreMascota, mascota,0,100, 0,100,0,0);
                        JOptionPane.showMessageDialog(null, "Mascota creada exitosamente");
                        jcbPartidas.addItem(nombreMascota);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Ya existe una mascota con ese nombre");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Ingrese un nombre de mascota válido");
                }
            }
        });

        JButton btnInicar = new JButton("Iniciar Partida");
        btnInicar.setBounds(200,370,120,26);
        panel.add(btnInicar);

        btnInicar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seleccion = (String) jcbPartidas.getSelectedItem();
                if (seleccion != null) {
                    InfoMascotas mascota = InfoMascotas.cargarMascota(seleccion);
                    //InfoMascotas.guardarMascota(seleccion,mascota,0,100, 0,100,0,0);
                    tamagotchiGui = new TamagotchiGui(mascota);
                    tamagotchiGui.setVisible(true);
                    tamagotchiGui.actualizarBarrasDeParametros();

                    TimerTask timerTask = new Temp_Estados(mascota);
                    timer.schedule(timerTask,0,1000);

                    TimerTask timerTask1 = new GuardarPartida(mascota);
                    timer.schedule(timerTask1,0,3000);

                    TimerTask timerTask2 = new Temp_Estados(mascota).imagenBoton;
                    TamagotchiGui.imagenTimer.schedule(timerTask2,1000,900);

                    GuardarPartida.nomCuenta = seleccion;
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "No hay ninguna cuenta seleccionada");
                }
            }
        });
    }

    public static void main(String[] args) {
        new InicioSesion();
    }
}
