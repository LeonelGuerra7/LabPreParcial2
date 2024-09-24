package lab1;

public class Personas {
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField5;
    private JComboBox<String> comboBox1;
    private JButton buttonAgregar;
    private JButton buttoBuscar;  // Corregí el nombre a buttonBuscar
    private JButton buttonEliminar;
    private JButton ButtonSalir;
    private JPanel BotTelegram;

    public Personas(JTextField textField1) {
        this.textField1 = textField1;
        initializeButtons();
    }

    private void initializeButtons() {
        // Acción para el botón Agregar
        buttonAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = textField1.getText();
                String apellido = textField2.getText();
                // Lógica para agregar persona (ej. agregar a una lista o base de datos)
                JOptionPane.showMessageDialog(null, "Persona agregada: " + nombre + " " + apellido);
            }
        });

        // Acción para el botón Buscar
        buttoBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreBuscar = textField1.getText();
                // Lógica para buscar la persona (ej. búsqueda en una lista o base de datos)
                JOptionPane.showMessageDialog(null, "Buscando persona: " + nombreBuscar);
            }
        });

        // Acción para el botón Eliminar
        buttonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreEliminar = textField1.getText();
                // Lógica para eliminar persona (ej. eliminar de una lista o base de datos)
                JOptionPane.showMessageDialog(null, "Persona eliminada: " + nombreEliminar);
            }
        });

        // Acción para el botón Salir
        ButtonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Sale de la aplicación
            }
        });
    }

    // Método para crear el panel con los botones
    public JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.add(buttonAgregar);
        panel.add(buttoBuscar); // Añadí el botón Buscar
        panel.add(buttonEliminar);
        panel.add(ButtonSalir);
        return panel;
    }
}
