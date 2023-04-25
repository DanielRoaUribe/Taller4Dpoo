package interfaz;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class interfaz extends JFrame {
    private boolean[][] lights;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    public interfaz() {
        setLayout(new BorderLayout());
        
        // Configurar panel de opciones
        panel1 = new JPanel();
        panel1.setBackground(Color.LIGHT_GRAY);
        add(panel1, BorderLayout.NORTH);

        JRadioButton facil = new JRadioButton("Facil");
        JRadioButton medio = new JRadioButton("Medio");
        JRadioButton dificil = new JRadioButton("Dificil");

        ButtonGroup nivel = new ButtonGroup();
        nivel.add(facil);
        nivel.add(medio);
        nivel.add(dificil);

        JComboBox combobox = new JComboBox();
        combobox.addItem("5x5");
        combobox.addItem("6x6");
        combobox.addItem("7x7");
        combobox.addItem("8x8");
        
        combobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) combobox.getSelectedItem();
                int size = Integer.parseInt(selected.substring(0, 1));
                lights = new boolean[size][size];
                panel2.setLayout(new GridLayout(size, size)); // Actualizar el GridLayout
                panel2.removeAll(); // Limpiar los paneles anteriores
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        lights[i][j] = false;
                        JPanel cell = new JPanel();
                        cell.setBackground(Color.YELLOW);
                        cell.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                // Cambiar estado de celda al hacer clic
                                int row = panel2.getComponentZOrder(cell) / size;
                                int col = panel2.getComponentZOrder(cell) % size;
                                lights[row][col] = !lights[row][col];
                                updateColors();
                            }
                        });
                        panel2.add(cell);
                        cell.setBorder(BorderFactory.createLineBorder(Color.BLUE));
                    }
                }
                panel2.revalidate(); // Actualizar la vista del panel
                panel2.repaint();
            }
        });

        panel1.add(combobox);
        panel1.add(facil);
        panel1.add(medio);
        panel1.add(dificil);

        // Configurar panel de la cuadricula
        panel2 = new JPanel(new GridLayout(5, 5));
        panel2.setBackground(Color.LIGHT_GRAY);
        add(panel2, BorderLayout.CENTER);

        // Inicializar matriz de luces en falso (amarillo)
        lights = new boolean[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                lights[i][j] = false;
                JPanel cell = new JPanel();
                cell.setBackground(Color.YELLOW);
                cell.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        // Cambiar estado de celda al hacer clic
                        int row = panel2.getComponentZOrder(cell) / 5;
                        int col = panel2.getComponentZOrder(cell) % 5;
                        lights[row][col] = !lights[row][col];
                        updateColors();
                    }
                });
                panel2.add(cell);
                cell.setBorder(BorderFactory.createLineBorder(Color.BLUE));
            }
        }
        // Configurar el panel del este
        panel3 = new JPanel(new GridLayout(4, 1));
        panel3.setBackground(Color.LIGHT_GRAY);
        add(panel3, BorderLayout.EAST);
        
        // Añadir los botones del panel
        JButton boton1 = new JButton("NUEVO");
        panel3.add(boton1);
        boton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el tamaño de la cuadrícula actual
                String selected = (String) combobox.getSelectedItem();
                int size = Integer.parseInt(selected.substring(0, 1));
                // Reinicializar la matriz de luces
                lights = new boolean[size][size];
                // Actualizar el GridLayout
                panel2.setLayout(new GridLayout(size, size));
                // Limpiar los paneles anteriores
                panel2.removeAll();
                // Crear las celdas de la nueva cuadrícula
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        lights[i][j] = false;
                        JPanel cell = new JPanel();
                        cell.setBackground(Color.YELLOW);
                        cell.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                // Cambiar estado de celda al hacer clic
                                int row = panel2.getComponentZOrder(cell) / size;
                                int col = panel2.getComponentZOrder(cell) % size;
                                lights[row][col] = !lights[row][col];
                                updateColors();
                            }
                        });
                        panel2.add(cell);
                        cell.setBorder(BorderFactory.createLineBorder(Color.BLUE));
                    }
                }
                // Actualizar la vista del panel
                panel2.revalidate();
                panel2.repaint();
                
            }
        });

        JButton boton2 = new JButton("REINICIAR");
        panel3.add(boton2);
        JButton boton3 = new JButton("TOP-10");
        panel3.add(boton3);
        JButton boton4 = new JButton("CAMBIAR JUGADOR");
        panel3.add(boton4);
        
        //Configurar el panel del sur
        panel4 = new JPanel(new GridLayout(1, 3));
        panel4.setBackground(Color.LIGHT_GRAY);
        add(panel4, BorderLayout.SOUTH);
        
        // Añadir los labels del panel
        JTextField text1 = new JTextField("Jugadas: ");
        text1.setBackground(Color.LIGHT_GRAY);
        panel4.add(text1);
        JTextField text2 = new JTextField("0");
        text2.setBackground(Color.LIGHT_GRAY);
        panel4.add(text2);
        JTextField text3 = new JTextField("Jugador: ");
        text3.setBackground(Color.LIGHT_GRAY);
        panel4.add(text3);
        
        // Mostrar ventana
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("LightsOut");
        setVisible(true);
    }
    private void updateColors() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                JPanel cell = (JPanel) panel2.getComponent(i * 5 + j);
                if (lights[i][j]) {
                    cell.setBackground(Color.BLACK);
                } else {
                    cell.setBackground(Color.YELLOW);
                }
            }
        }
    }

    
    public static void main(String[] args) {
        interfaz interfaz1 = new interfaz();
    }
}