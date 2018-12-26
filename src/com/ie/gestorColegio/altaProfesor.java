package com.ie.gestorColegio;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class altaProfesor extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField codigoProfesor;
	JTextField nombre;
	JTextField apellido;
	JTextField telefono;
	JTextField direccion;
	JTextField poblacion;
	JComboBox<String> asignaturas;
	JPanel altaProfesor;

	public  altaProfesor() {
		altaProfesor=this;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JPanel aux = new JPanel();
		aux.setLayout(new GridLayout(7, 2, 2, 2));
		aux.setBorder(new TitledBorder("Datos Alumno"));
		aux.add(new JLabel("Codigo Alumno: "));
		codigoProfesor = new JTextField();
		codigoProfesor.setEditable(false);
		aux.add(codigoProfesor);
		aux.add(new JLabel("Nombre: "));
		nombre = new JTextField();
		aux.add(nombre);
		aux.add(new JLabel("Apellido: "));
		apellido = new JTextField();
		aux.add(apellido);
		aux.add(new JLabel("Teléfono: "));
		telefono = new JTextField();
		aux.add(telefono);
		aux.add(new JLabel("Dirección: "));
		direccion=new JTextField();
		aux.add(direccion);
		aux.add(new JLabel("Población:"));
		poblacion=new JTextField();
		aux.add(poblacion);
		this.add(aux);
		aux.add(new JLabel("Asignaturas "));
		asignaturas = new JComboBox<String>();
		aux.add(asignaturas);
		this.add(aux);
		
		aux=new JPanel();
		JButton limpiar = new JButton("Limpiar");
		aux.add(limpiar);
		limpiar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				limpiar();
			}
		});
		JButton AltaProfesor = new JButton("Alta Profesor");
		aux.add(AltaProfesor);
		this.add(aux);
		AltaProfesor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					Connection conn = null;
					PreparedStatement stmt = null;
					Class.forName("com.mysql.jdbc.Driver");
					conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/gestion_colegio", "root", "");
					/*stmt = conn.prepareStatement("Insert into alumnos values(?,?,?,?,?)");
					stmt.setString(1, nombre.getText());
					stmt.setString(2, apellido.getText());
					stmt.setInt(3, Integer.parseInt(telefono.getText()));
					stmt.setString(4, direccion.getText());
					stmt.setString(5, poblacion.getText());
					stmt.executeUpdate();*/
					String sql = "Insert into profesor (Nombre,Apellidos,Telefono,Domicilio,Poblacion) values( ' " + nombre.getText() + " ', ' " +
							  apellido.getText() + " ', " + Integer.parseInt(telefono.getText())+ "  , ' " +
							  direccion.getText()+ "' ,' " + poblacion.getText() + "' )";
							  stmt=conn.prepareStatement(sql); 
							  stmt.executeUpdate(sql);
							 /* sql="Select codigoProfesor from profesor where nombre=?";
							 String sql = "Delete from alumnos where clave=?";
								stmt = conn.prepareStatement(sql);
								stmt.setString(1, (String) clave.getSelectedItem());
								stmt.executeUpdate();*/
							/*  ResultSet rs = stmt.executeQuery(sql);
							  rs.next();
							  rs.getInt(1);
							  sql="Update codigoProfesor from Asignatura where codigoProfesor=?";
							  stmt=conn.prepareStatement(sql);
							  stmt.setInt(1,Integer.parseInt(codigoProfesor.getText()));
							  stmt.executeUpdate();*/
					JOptionPane.showMessageDialog(AltaProfesor, "Dato insertado correctamente", "Comprobacion de sentencia",
							JOptionPane.INFORMATION_MESSAGE);
					limpiar();
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		this.add(aux);

		this.addComponentListener(new ComponentAdapter() {
			public void componentShown(ComponentEvent e) {
				asignaturas.removeAllItems();
				Connection conn = null;
				Statement stmt = null;
				ResultSet rs = null;

				try {
					Class.forName("com.mysql.jdbc.Driver");
					conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/gestion_colegio", "root", "");
					String [] asignatura=new String[] {"Matemáticas", "Física" ,"Química", "Tecnología","Dibujo","Historia","Literatura" };
					
					while (rs.next()) {
						//asignaturas.addItem(.);
					}
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} finally {
					try {
						if (stmt != null)
							stmt.close();
						if (conn != null)
							conn.close();
						if (rs != null)
							rs.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
		
	
	
	
	asignaturas.addItemListener(new ItemListener() {

		public void itemStateChanged(ItemEvent e) {
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;

			if (e.getStateChange() == ItemEvent.SELECTED) {
				try {
					String claveCombo = (String) e.getItem();
					Class.forName("com.mysql.jdbc.Driver");
					conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/universidad", "root", "");
					stmt = conn.prepareStatement("Select codigoProfesor from profesor where asignatura=?");
					stmt.setString(1, claveCombo);
					rs = stmt.executeQuery();
					rs.first();
					codigoProfesor.setText(Integer.toString(rs.getInt(1)));
					String sql="Update codigoProfesor from Asignatura where codigoProfesor=?";
					  stmt.setInt(1,Integer.parseInt(codigoProfesor.getText()));
					  stmt.executeUpdate(sql);
		
				} catch (ClassNotFoundException e1) {
					JOptionPane.showMessageDialog(AltaProfesor, "Error de conexion a la base de datos",
							"Comprobacion de sentencia", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(AltaProfesor, "Problemas al operar en la base de datos",
							"Comprobacion de sentencia", JOptionPane.INFORMATION_MESSAGE);
				} finally {
					try {
						if (conn != null)
							conn.close();
						if (stmt != null)
							stmt.close();
						if (rs != null)
							rs.close();
					} catch (SQLException e1) {
						System.out.println(e1.getMessage());
					}
				}
			}

		}
	});
}

	private void limpiar() {
		codigoProfesor.setText("");
		nombre.setText("");
		apellido.setText("");
		telefono.setText("");
		direccion.setText("");
		poblacion.setText("");

	}
}
