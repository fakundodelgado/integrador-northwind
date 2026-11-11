package ar.edu.unsa.pabd;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class VentanaSec extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_11;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;

	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaSec frame = new VentanaSec();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	 
	public VentanaSec() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(500,400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{30, 120, 126, 80, 128, 0};
		gbl_contentPane.rowHeights = new int[]{25, 25, 25, 25, 25, 25, 25, 25, 0, 0, 25, 25, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				// Asegurarse de que el evento corresponde al elemento seleccionado
		        if (e.getStateChange() == ItemEvent.SELECTED) {
		            String opcionSeleccionada = (String) comboBox.getSelectedItem();
		            
		         // Comprobación de condición
		            if ("B".equals(opcionSeleccionada)) {
		                textField_2.setEnabled(false);   // Deshabilita el campo de texto
		                textField_3.setEnabled(false); 
		                textField_4.setEnabled(false); 
		                textField_5.setEnabled(false); 
		                textField_6.setEnabled(false); 
		                textField_7.setEnabled(false); 
		                textField_8.setEnabled(false); 
		                textField_9.setEnabled(false); 
		                textField_10.setEnabled(false); 
		                textField_11.setEnabled(false); 
		            } else {
		            	 textField_2.setEnabled(true);   // Habilita el campo de texto
			             textField_3.setEnabled(true); 
			             textField_4.setEnabled(true); 
			             textField_5.setEnabled(true); 
			             textField_6.setEnabled(true); 
			             textField_7.setEnabled(true); 
			             textField_8.setEnabled(true); 
			             textField_9.setEnabled(true); 
			             textField_10.setEnabled(true); 
			             textField_11.setEnabled(true); 
		            }
		            
		        }    
			}
		});
		
		JLabel lblAccionaB = new JLabel("Accion:  ");
		GridBagConstraints gbc_lblAccionaB = new GridBagConstraints();
		gbc_lblAccionaB.anchor = GridBagConstraints.WEST;
		gbc_lblAccionaB.insets = new Insets(0, 0, 5, 5);
		gbc_lblAccionaB.gridx = 1;
		gbc_lblAccionaB.gridy = 0;
		contentPane.add(lblAccionaB, gbc_lblAccionaB);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"A", "B", "M"}));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.anchor = GridBagConstraints.NORTH;
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 0;
		contentPane.add(comboBox, gbc_comboBox);
		
		JLabel lblAAlta = new JLabel(" A = Alta");
		GridBagConstraints gbc_lblAAlta = new GridBagConstraints();
		gbc_lblAAlta.anchor = GridBagConstraints.WEST;
		gbc_lblAAlta.insets = new Insets(0, 0, 5, 0);
		gbc_lblAAlta.gridx = 4;
		gbc_lblAAlta.gridy = 0;
		contentPane.add(lblAAlta, gbc_lblAAlta);
		
		JLabel lblId = new JLabel("ID: ");
		GridBagConstraints gbc_lblId = new GridBagConstraints();
		gbc_lblId.anchor = GridBagConstraints.WEST;
		gbc_lblId.insets = new Insets(0, 0, 5, 5);
		gbc_lblId.gridx = 1;
		gbc_lblId.gridy = 1;
		contentPane.add(lblId, gbc_lblId);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.gridx = 2;
		gbc_textField_1.gridy = 1;
		contentPane.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JLabel lblBBaja = new JLabel(" B = Baja");
		GridBagConstraints gbc_lblBBaja = new GridBagConstraints();
		gbc_lblBBaja.anchor = GridBagConstraints.WEST;
		gbc_lblBBaja.insets = new Insets(0, 0, 5, 0);
		gbc_lblBBaja.gridx = 4;
		gbc_lblBBaja.gridy = 1;
		contentPane.add(lblBBaja, gbc_lblBBaja);
		
		JLabel lblNombre = new JLabel("Nombre: ");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.WEST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 1;
		gbc_lblNombre.gridy = 2;
		contentPane.add(lblNombre, gbc_lblNombre);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.gridx = 2;
		gbc_textField_2.gridy = 2;
		contentPane.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		JLabel lblMModificacion = new JLabel(" M = Modificacion");
		GridBagConstraints gbc_lblMModificacion = new GridBagConstraints();
		gbc_lblMModificacion.anchor = GridBagConstraints.WEST;
		gbc_lblMModificacion.insets = new Insets(0, 0, 5, 0);
		gbc_lblMModificacion.gridx = 4;
		gbc_lblMModificacion.gridy = 2;
		contentPane.add(lblMModificacion, gbc_lblMModificacion);
		
		JLabel lblContacto = new JLabel("Contacto: ");
		GridBagConstraints gbc_lblContacto = new GridBagConstraints();
		gbc_lblContacto.anchor = GridBagConstraints.WEST;
		gbc_lblContacto.insets = new Insets(0, 0, 5, 5);
		gbc_lblContacto.gridx = 1;
		gbc_lblContacto.gridy = 3;
		contentPane.add(lblContacto, gbc_lblContacto);
		
		textField_3 = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.gridx = 2;
		gbc_textField_3.gridy = 3;
		contentPane.add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);
		
		JLabel lblTitulo = new JLabel("Titulo: ");
		GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
		gbc_lblTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitulo.anchor = GridBagConstraints.WEST;
		gbc_lblTitulo.gridx = 1;
		gbc_lblTitulo.gridy = 4;
		contentPane.add(lblTitulo, gbc_lblTitulo);
		
		textField_4 = new JTextField();
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.insets = new Insets(0, 0, 5, 5);
		gbc_textField_4.gridx = 2;
		gbc_textField_4.gridy = 4;
		contentPane.add(textField_4, gbc_textField_4);
		textField_4.setColumns(10);
		
		JLabel lblDireccion = new JLabel("Direccion: ");
		GridBagConstraints gbc_lblDireccion = new GridBagConstraints();
		gbc_lblDireccion.insets = new Insets(0, 0, 5, 5);
		gbc_lblDireccion.anchor = GridBagConstraints.WEST;
		gbc_lblDireccion.gridx = 1;
		gbc_lblDireccion.gridy = 5;
		contentPane.add(lblDireccion, gbc_lblDireccion);
		
		textField_5 = new JTextField();
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_5.insets = new Insets(0, 0, 5, 5);
		gbc_textField_5.gridx = 2;
		gbc_textField_5.gridy = 5;
		contentPane.add(textField_5, gbc_textField_5);
		textField_5.setColumns(10);
		
		JLabel lblCiudad = new JLabel("Ciudad: ");
		GridBagConstraints gbc_lblCiudad = new GridBagConstraints();
		gbc_lblCiudad.anchor = GridBagConstraints.WEST;
		gbc_lblCiudad.insets = new Insets(0, 0, 5, 5);
		gbc_lblCiudad.gridx = 1;
		gbc_lblCiudad.gridy = 6;
		contentPane.add(lblCiudad, gbc_lblCiudad);
		
		textField_11 = new JTextField();
		GridBagConstraints gbc_textField_11 = new GridBagConstraints();
		gbc_textField_11.insets = new Insets(0, 0, 5, 5);
		gbc_textField_11.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_11.gridx = 2;
		gbc_textField_11.gridy = 6;
		contentPane.add(textField_11, gbc_textField_11);
		textField_11.setColumns(10);
		
		JLabel lblRegion = new JLabel("Region: ");
		GridBagConstraints gbc_lblRegion = new GridBagConstraints();
		gbc_lblRegion.anchor = GridBagConstraints.WEST;
		gbc_lblRegion.insets = new Insets(0, 0, 5, 5);
		gbc_lblRegion.gridx = 1;
		gbc_lblRegion.gridy = 7;
		contentPane.add(lblRegion, gbc_lblRegion);
		
		textField_6 = new JTextField();
		GridBagConstraints gbc_textField_6 = new GridBagConstraints();
		gbc_textField_6.insets = new Insets(0, 0, 5, 5);
		gbc_textField_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_6.gridx = 2;
		gbc_textField_6.gridy = 7;
		contentPane.add(textField_6, gbc_textField_6);
		textField_6.setColumns(10);
		
		JLabel lblCodigoPostal = new JLabel("Codigo Postal: ");
		GridBagConstraints gbc_lblCodigoPostal = new GridBagConstraints();
		gbc_lblCodigoPostal.anchor = GridBagConstraints.WEST;
		gbc_lblCodigoPostal.insets = new Insets(0, 0, 5, 5);
		gbc_lblCodigoPostal.gridx = 1;
		gbc_lblCodigoPostal.gridy = 8;
		contentPane.add(lblCodigoPostal, gbc_lblCodigoPostal);
		
		textField_7 = new JTextField();
		GridBagConstraints gbc_textField_7 = new GridBagConstraints();
		gbc_textField_7.insets = new Insets(0, 0, 5, 5);
		gbc_textField_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_7.gridx = 2;
		gbc_textField_7.gridy = 8;
		contentPane.add(textField_7, gbc_textField_7);
		textField_7.setColumns(10);
		
		JLabel lblPais = new JLabel("Pais: ");
		GridBagConstraints gbc_lblPais = new GridBagConstraints();
		gbc_lblPais.anchor = GridBagConstraints.WEST;
		gbc_lblPais.insets = new Insets(0, 0, 5, 5);
		gbc_lblPais.gridx = 1;
		gbc_lblPais.gridy = 9;
		contentPane.add(lblPais, gbc_lblPais);
		
		textField_8 = new JTextField();
		GridBagConstraints gbc_textField_8 = new GridBagConstraints();
		gbc_textField_8.insets = new Insets(0, 0, 5, 5);
		gbc_textField_8.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_8.gridx = 2;
		gbc_textField_8.gridy = 9;
		contentPane.add(textField_8, gbc_textField_8);
		textField_8.setColumns(10);
		
		JLabel lblTelefono = new JLabel("Telefono: ");
		GridBagConstraints gbc_lblTelefono = new GridBagConstraints();
		gbc_lblTelefono.anchor = GridBagConstraints.WEST;
		gbc_lblTelefono.insets = new Insets(0, 0, 5, 5);
		gbc_lblTelefono.gridx = 1;
		gbc_lblTelefono.gridy = 10;
		contentPane.add(lblTelefono, gbc_lblTelefono);
		
		textField_9 = new JTextField();
		GridBagConstraints gbc_textField_9 = new GridBagConstraints();
		gbc_textField_9.insets = new Insets(0, 0, 5, 5);
		gbc_textField_9.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_9.gridx = 2;
		gbc_textField_9.gridy = 10;
		contentPane.add(textField_9, gbc_textField_9);
		textField_9.setColumns(10);
		
		JLabel lblFax = new JLabel("Fax: ");
		GridBagConstraints gbc_lblFax = new GridBagConstraints();
		gbc_lblFax.anchor = GridBagConstraints.WEST;
		gbc_lblFax.insets = new Insets(0, 0, 5, 5);
		gbc_lblFax.gridx = 1;
		gbc_lblFax.gridy = 11;
		contentPane.add(lblFax, gbc_lblFax);
		
		JButton btnCargar = new JButton("Cargar");
		btnCargar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Operador op = new Operador();
		        
		        try {
		            // Pasamos los 12 parametros a la funcion en el orden exacto de los textFields
		            String respuesta = op.abm(
		                (String) comboBox.getSelectedItem(), // Trae "A", "B" o "M"
		                textField_1.getText(),   // customerid
		                textField_2.getText(),   // companyname
		                textField_3.getText(),   // contactname
		                textField_4.getText(),   // contacttitle
		                textField_5.getText(),   // address
		                textField_11.getText(),  // city
		                textField_6.getText(),   // region
		                textField_7.getText(),   // postalcode
		                textField_8.getText(),   // country
		                textField_9.getText(),   // phone
		                textField_10.getText()   // fax
		            );

		            // Muestra el mensaje de EXITO que devuelve PostgreSQL
		            JOptionPane.showMessageDialog(
		                VentanaSec.this, 
		                respuesta, 
		                "Operación Exitosa", 
		                JOptionPane.INFORMATION_MESSAGE
		            );

		        } catch (SQLException ex) {
		            // Ataja cualquier RAISE EXCEPTION de PL/pgSQL
		            JOptionPane.showMessageDialog(
		                VentanaSec.this, 
		                ex.getMessage(), 
		                "Error en la Base de Datos", 
		                JOptionPane.ERROR_MESSAGE
		            );
		        }
		    }
		});
		
		textField_10 = new JTextField();
		GridBagConstraints gbc_textField_10 = new GridBagConstraints();
		gbc_textField_10.insets = new Insets(0, 0, 5, 5);
		gbc_textField_10.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_10.gridx = 2;
		gbc_textField_10.gridy = 11;
		contentPane.add(textField_10, gbc_textField_10);
		textField_10.setColumns(10);
		GridBagConstraints gbc_btnCargar = new GridBagConstraints();
		gbc_btnCargar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCargar.gridx = 4;
		gbc_btnCargar.gridy = 12;
		contentPane.add(btnCargar, gbc_btnCargar);

	}
}
