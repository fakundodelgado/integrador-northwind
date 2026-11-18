package aplicacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
//import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.FlowLayout;

public class InterfaceApp extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField_2;
	private JComboBox<String> comboBox;//---------->NUEVO
	private JTable table;  //------------->NUEVO
	private VentanaSec ventanaSec = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceApp frame = new InterfaceApp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InterfaceApp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(800,600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{28, 60, 114, 60, 114, 19, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{27, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNewLabel = new JLabel("Cliente: ");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.anchor = GridBagConstraints.WEST;
		gbc_textField_1.insets = new Insets(0, 0, 0, 5);
		gbc_textField_1.gridx = 2;
		gbc_textField_1.gridy = 0;
		panel.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Empleado: ");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_1.gridx = 3;
		gbc_lblNewLabel_1.gridy = 0;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.anchor = GridBagConstraints.WEST;
		gbc_textField_2.insets = new Insets(0, 0, 0, 5);
		gbc_textField_2.gridx = 4;
		gbc_textField_2.gridy = 0;
		panel.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		

		JButton btnNewButton = new JButton("Buscar"); 
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				buscarDatos();   //---------->NUEVO
			}
		});
		
				
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnNewButton.gridx = 5;
		gbc_btnNewButton.gridy = 0;
		panel.add(btnNewButton, gbc_btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Reset");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetearFiltros();
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_1.gridx = 6;
		gbc_btnNewButton_1.gridy = 0;
		panel.add(btnNewButton_1, gbc_btnNewButton_1);
										
		JLabel lblVista = new JLabel("Vista: ");   //---------->NUEVO
		GridBagConstraints gbc_lblVista = new GridBagConstraints();
		gbc_lblVista.insets = new Insets(0, 0, 0, 5);
		gbc_lblVista.anchor = GridBagConstraints.EAST;
		gbc_lblVista.gridx = 10;
		gbc_lblVista.gridy = 0;
		panel.add(lblVista, gbc_lblVista);
										
		comboBox = new JComboBox();   //------------->NUEVO
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				// Asegurarse de que el evento corresponde al elemento seleccionado
		        if (e.getStateChange() == ItemEvent.SELECTED) {
		        	
		        	try {
		        		Operador op = new Operador();
			        	String opcion = (String) comboBox.getSelectedItem();
			        	DefaultTableModel modelo;
			        	if("Clientes".equals(opcion)) {
			        		modelo = op.consulta_cli("", "");
			        		lblNewLabel.setText("Nombre: ");
			        		lblNewLabel_1.setText("Lugar: ");
			        	}else {
			        		modelo = op.consulta_ord("", "");
			        		lblNewLabel.setText("Cliente: ");
			        		lblNewLabel_1.setText("Empleado: ");
			        	}
			        	table.setModel(modelo);
			        	
			        	javax.swing.SwingUtilities.invokeLater(new Runnable() {
			                @Override
			                public void run() {
			                    autoajustarAnchoColumnas(table);
			                }
			            });
			        	
			        	limpiarCampos();
			        	
		            } catch (SQLException ex) {
		                JOptionPane.showMessageDialog(InterfaceApp.this, "Error al cargar datos iniciales: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		            }
		        	
		        }
			}
		});	
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Ordenes", "Clientes"}));
		comboBox.setSelectedIndex(0);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 11;
		gbc_comboBox.gridy = 0;
		panel.add(comboBox, gbc_comboBox);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton btnCargar = new JButton("ABM");
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Verifica si la ventana no existe o ya fue cerrada
		        if (ventanaSec == null || !ventanaSec.isVisible()) {
		            ventanaSec = new VentanaSec();
		            ventanaSec.setVisible(true);
		        } else {
		            // Si ya está abierta, la trae al frente en lugar de crear otra
		            ventanaSec.toFront();
		            ventanaSec.requestFocus();
		        }
			}
		});
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel_1.add(btnCargar);
		
		JScrollPane scrollPane = new JScrollPane();  //------------->NUEVO
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel_2 = new JPanel();  //------------->NUEVO
		scrollPane.setViewportView(panel_2);
		
		table = new JTable();
		panel_2.add(table);
		
		cargarTablaInicial(); //---------->NUEVO

	}
	
//-----------------------NUEVO-------------------------------------
	
	private void cargarTablaInicial() {
        try {
            Operador op = new Operador();
            // Enviamos "" y "" para mostrar la tabla normal en la base de datos
            DefaultTableModel modelo = op.consulta_ord("", "");
           
            table.setModel(modelo);
            
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    autoajustarAnchoColumnas(table);
                }
            });
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(InterfaceApp.this, "Error al cargar datos iniciales: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }	
	
	private void buscarDatos() {
        try {
        	//Capturo lo que se ingresa
            String parametro_1 = textField_1.getText();
            String parametro_2  = textField_2.getText();

            
            Operador op = new Operador();
            String opcion = (String) comboBox.getSelectedItem();
        	DefaultTableModel modelo;
        	if("Clientes".equals(opcion)) {
        		modelo = op.consulta_cli(parametro_1, parametro_2);
        	}else {
        		modelo = op.consulta_ord(parametro_1, parametro_2);
        	}
            
            // Asignamos el modelo a la tabla 
            table.setModel(modelo);
            
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    autoajustarAnchoColumnas(table);
                }
            });

            if (modelo.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "No se encontraron registros.");
            }

        } catch (SQLException ex) {
            // Aquí capturamos el mensaje de error exacto que lanzó el SIGNAL de la Base de Datos
            JOptionPane.showMessageDialog(InterfaceApp.this, ex.getMessage(), "Error desde Base de Datos", JOptionPane.ERROR_MESSAGE);
        }
	}
	
	private void limpiarCampos() {
		textField_1.setText("");
	    textField_2.setText("");
	}
	
	// Este metodo ayuda a redimencionar las columnas para adaptarse al contenido
	public void autoajustarAnchoColumnas(javax.swing.JTable tabla) {
	    
		// Se desactiva el auto ajuste
	    tabla.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
	    
	    // Se recorre cada columna analizando el tamaño de su contenido para asignarle un ancho acorde
	    for (int columna = 0; columna < tabla.getColumnCount(); columna++) {
	        javax.swing.table.TableColumn tableColumn = tabla.getColumnModel().getColumn(columna);
	        int anchoMaximo = 0;

	        
	        javax.swing.table.TableCellRenderer headerRenderer = tabla.getTableHeader().getDefaultRenderer();
	        if (headerRenderer != null) {
	            java.awt.Component headerComponent = headerRenderer.getTableCellRendererComponent(
	                tabla, tableColumn.getHeaderValue(), false, false, 0, columna);
	            anchoMaximo = Math.max(anchoMaximo, headerComponent.getPreferredSize().width);
	        }

	        
	        for (int fila = 0; fila < tabla.getRowCount(); fila++) {
	            javax.swing.table.TableCellRenderer cellRenderer = tabla.getCellRenderer(fila, columna);
	            if (cellRenderer != null) {
	                java.awt.Component c = tabla.prepareRenderer(cellRenderer, fila, columna);
	                anchoMaximo = Math.max(anchoMaximo, c.getPreferredSize().width);
	            }
	        }

	        tableColumn.setPreferredWidth(anchoMaximo + 25);
	    }
	}
	
	// Simplemente es la función del boton de reset que limpia los campos y actualiza la consulta para mostrar todo 
	private void resetearFiltros() {
		
		limpiarCampos();
		
		try {
			Operador op = new Operador();
			String opcion = (String) comboBox.getSelectedItem();
			DefaultTableModel modelo;
			if("Clientes".equals(opcion)) {
				modelo = op.consulta_cli("", "");
			} else {
				modelo = op.consulta_ord("", "");
			}
			table.setModel(modelo);
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					autoajustarAnchoColumnas(table);
				}
			});
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(InterfaceApp.this, "Error al restablecer: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	

	
	
}
