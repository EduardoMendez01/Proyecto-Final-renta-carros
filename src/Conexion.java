import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class Conexion {
	Connection conexion = null;
	Statement stm = null;
	ResultSet rs = null;
	private static final String CONTROLADOR = "com.mysql.jdbc.Driver";
	// EN ESTA LINEA SE PONE EL PUERTO AL QUE TE CONECTAS EN TU WORKBENCH Y EL
	// NOMBRE QUE TIENE LA BD
	private static final String URL = "jdbc:mysql://localhost:3306/car_rental";
	private static final String USUARIO = "root";
	// EN ESTA LINEA SE PONE LA CONTRASEÑA QUE CONFIGURASTE PARA TU USUARIO A LA
	// HORA DE INSTALAR WORKBENCH
	private static final String CLAVE = "Bahialucila219";
	
	Date fechaInicial_NuevaRenta;
	Date fechaFinal_RentasPasadas;
	Date fechaFinal_NuevaRenta;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	java.sql.Date fecha_renta_sql;
	java.sql.Date fecha_entrega_sql;
	

	public Connection conectar() {
		Connection conexion = null;

		try {
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
			System.out.println("Conexión OK");

		} catch (SQLException e) {
			System.out.println("Error en la conexión");
			e.printStackTrace();
		}

		return conexion;
	}

	public boolean validar_Inicio_Sesion(JTextField cliente, JPasswordField pwd) {
		conexion = null;
		stm = null;
		rs = null;
		boolean acceso = false;

		try {
			Class.forName(CONTROLADOR);
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
			System.out.println("Conexión OK");

			stm = (Statement) conexion.createStatement();
			rs = stm.executeQuery("SELECT * FROM cliente");

			while (rs.next()) {
				String nombre = rs.getString(2);
				String apellidos = rs.getString(3);
				String contrasena = rs.getString(5);
				String nombre_completo = nombre + " " + apellidos;
				String contrasena_Formato_String = new String(pwd.getPassword());

				if (nombre_completo.equals(cliente.getText()) && contrasena.equals(contrasena_Formato_String)) {
					acceso = true;
					break;
				}
			}
			if (acceso) {
				JOptionPane.showMessageDialog(null, "Bienvenido");
			} else {
				JOptionPane.showMessageDialog(null, "Error. Datos incorrectos");
				acceso = false;
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return acceso;
	}

	public void consultar_Categorias(DefaultTableModel tabla) {
		conexion = null;
		stm = null;
		rs = null;

		try {
			Class.forName(CONTROLADOR);
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
			System.out.println("Conexión OK");

			stm = (Statement) conexion.createStatement();
			rs = stm.executeQuery("SELECT * FROM categoria");

			while (rs.next()) {
				tabla.addRow(
						new Object[] { rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5), });
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void consultar_Marcas(DefaultTableModel tabla) {
		conexion = null;
		stm = null;
		rs = null;

		try {
			Class.forName(CONTROLADOR);
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
			System.out.println("Conexión OK");

			stm = (Statement) conexion.createStatement();
			rs = stm.executeQuery("SELECT * FROM marca");

			while (rs.next()) {
				tabla.addRow(
						new Object[] { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), });
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void consultar_Clientes(DefaultTableModel tabla) {
		conexion = null;
		stm = null;
		rs = null;

		try {
			Class.forName(CONTROLADOR);
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
			System.out.println("Conexión OK");

			stm = (Statement) conexion.createStatement();
			rs = stm.executeQuery("SELECT * FROM cliente");

			while (rs.next()) {
				tabla.addRow(
						new Object[] { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6) });
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void consultar_Vehiculos(DefaultTableModel tabla) {
		conexion = null;
		stm = null;
		rs = null;

		try {
			Class.forName(CONTROLADOR);
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
			System.out.println("Conexión OK");

			stm = (Statement) conexion.createStatement();
			rs = stm.executeQuery("SELECT * FROM vehiculo");

			while (rs.next()) {
				tabla.addRow(new Object[] { rs.getString(3), rs.getString(1), rs.getInt(5) });
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void consultar_Rentas(DefaultTableModel tabla) {
		conexion = null;
		stm = null;
		rs = null;

		try {
			Class.forName(CONTROLADOR);
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
			System.out.println("Conexión OK");

			stm = (Statement) conexion.createStatement();
			rs = stm.executeQuery("SELECT * FROM renta");

			while (rs.next()) {
				String nom_cliente = rs.getString(2) + " " + rs.getString(3);
				tabla.addRow(
						new Object[] { rs.getString(8), nom_cliente, rs.getDate(6), rs.getDate(7), rs.getDouble(9) });
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean añadir_Vehiculo(JTextField nombre, JTextField modelo, JTextField transmision, JTextField tarifa,
			JTextField año, JComboBox cmb) {
		conexion = null;
		stm = null;
		rs = null;
		Boolean existente = false;

		try {
			Class.forName(CONTROLADOR);
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
			System.out.println("Conexión OK");

			stm = (Statement) conexion.createStatement();
			rs = stm.executeQuery("SELECT * FROM vehiculo");

			while (rs.next()) {
				if (rs.getString(1).equals(modelo.getText())) {
					existente = true;
				}
			}

			if (existente == false) {
				PreparedStatement stm = (PreparedStatement) conexion
						.prepareStatement("INSERT INTO vehiculo VALUE(?,?,?,?,?,?)");

				stm.setString(1, modelo.getText().trim());
				stm.setString(2, nombre.getText().trim());
				stm.setString(3, cmb.getSelectedItem().toString());
				stm.setString(4, transmision.getText().trim());
				stm.setInt(5, Integer.valueOf(tarifa.getText()));
				stm.setString(6, año.getText());
				stm.executeUpdate();
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return existente;
	}

	public boolean añadir_Renta(JTextField nombre_cliente, JTextField apellidos_cliente, JTextField telefono_cliente, JTextField fecha_renta,
			JTextField fecha_entrega, JComboBox cmb) {
		conexion = null;
		stm = null;
		rs = null;
		boolean vehiculo_ocupado = false;
		int tarifa = 0;
		String modelo = "";
		
		try {
			Class.forName(CONTROLADOR);
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
			System.out.println("Conexión OK");

			stm = (Statement) conexion.createStatement();
			rs = stm.executeQuery("SELECT * FROM renta");

			while (rs.next()) {
				if (cmb.getSelectedItem().toString().contains(rs.getString(8))) {
					try {
						modelo = rs.getString(8);
						fechaInicial_NuevaRenta = sdf.parse(fecha_renta.getText());
						fechaFinal_NuevaRenta = sdf.parse(fecha_entrega.getText());
						fechaFinal_RentasPasadas = sdf.parse(rs.getString(7));
						fecha_renta_sql = new java.sql.Date(fechaInicial_NuevaRenta.getTime());
						fecha_entrega_sql = new java.sql.Date(fechaFinal_NuevaRenta.getTime());
						if(fechaInicial_NuevaRenta.before(fechaFinal_RentasPasadas)) {
							vehiculo_ocupado = true;
						}
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			rs = stm.executeQuery("SELECT * FROM vehiculo");

			while (rs.next()) {
				if (cmb.getSelectedItem().toString().contains(rs.getString(1))) {
						tarifa = rs.getInt(5);
					}
				}
			

			if (vehiculo_ocupado == false) {
				long diferencia_Dias = ChronoUnit.DAYS.between((Temporal) fechaInicial_NuevaRenta, (Temporal) fechaInicial_NuevaRenta);
				int dias_renta = (int) diferencia_Dias;
				double costo_total = dias_renta * tarifa;
				PreparedStatement stm = (PreparedStatement) conexion
						.prepareStatement("INSERT INTO renta VALUE(?,?,?,?,?,?,?,?,?)");

				stm.setString(1, "0");
				stm.setString(2, nombre_cliente.getText().trim());
				stm.setString(3, apellidos_cliente.getText().trim());
				stm.setString(4, telefono_cliente.getText().trim());;
				stm.setInt(5, tarifa);
				stm.setDate(6, fecha_renta_sql);
				stm.setDate(7, fecha_renta_sql);
				stm.setString(8, modelo);
				stm.setDouble(9, costo_total);
				stm.executeUpdate();
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vehiculo_ocupado;
	}
	
	public boolean añadir_Categorias(JTextField nombre, JTextField cant_llantas, JTextField uso, JTextField peso) {
		conexion = null;
		stm = null;
		rs = null;
		Boolean existente = false;

		try {
			Class.forName(CONTROLADOR);
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
			System.out.println("Conexión OK");

			stm = (Statement) conexion.createStatement();
			rs = stm.executeQuery("SELECT * FROM categoria");

			while (rs.next()) {
				if (rs.getString(2).equals(nombre.getText())) {
					existente = true;
				}
			}

			if (existente == false) {
				PreparedStatement stm = (PreparedStatement) conexion
						.prepareStatement("INSERT INTO categoria VALUE(?,?,?,?,?)");

				stm.setString(1, "0");
				stm.setString(2, nombre.getText().trim());
				stm.setInt(3, Integer.valueOf(cant_llantas.getText()));
				stm.setString(4, uso.getText().trim());
				stm.setInt(5, Integer.valueOf(peso.getText()));
				stm.executeUpdate();
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return existente;
	}

	public void llenar_CMB_Vehiculos(JComboBox cmb) {
		conexion = null;
		stm = null;
		rs = null;

		try {
			Class.forName(CONTROLADOR);
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
			System.out.println("Conexión OK");

			stm = (Statement) conexion.createStatement();
			rs = stm.executeQuery("SELECT * FROM vehiculo");

			while (rs.next()) {
				cmb.addItem(rs.getString(3) + " " + rs.getString(2) + " " + rs.getString(1));
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void llenar_CMB_Categorias(JComboBox cmb) {
		conexion = null;
		stm = null;
		rs = null;

		try {
			Class.forName(CONTROLADOR);
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
			System.out.println("Conexión OK");

			stm = (Statement) conexion.createStatement();
			rs = stm.executeQuery("SELECT * FROM categoria");

			while (rs.next()) {
				cmb.addItem(rs.getString(2));
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void llenar_CMB_Marcas(JComboBox cmb) {
		conexion = null;
		stm = null;
		rs = null;

		try {
			Class.forName(CONTROLADOR);
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
			System.out.println("Conexión OK");

			stm = (Statement) conexion.createStatement();
			rs = stm.executeQuery("SELECT * FROM marca");

			while (rs.next()) {
				cmb.addItem(rs.getString(1));
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * public void llenar_Campos_Categoria_Segun_ComboBox(JTextField nombre,
	 * JTextField cant_llantas, JTextField uso, JTextField peso, JComboBox cmb) {
	 * conexion = null; stm = null; rs = null; boolean existe = false;
	 * 
	 * try { Class.forName(CONTROLADOR); conexion = DriverManager.getConnection(URL,
	 * USUARIO, CLAVE); System.out.println("Conexión OK");
	 * 
	 * stm = (Statement) conexion.createStatement(); rs =
	 * stm.executeQuery("SELECT * FROM categoria");
	 * 
	 * while (rs.next()) { if
	 * (rs.getString(2).equals(cmb.getSelectedItem().toString())) {
	 * nombre.setText(rs.getString(2));
	 * cant_llantas.setText(String.valueOf(rs.getInt(3)));
	 * uso.setText(rs.getString(4)); peso.setText(String.valueOf(rs.getInt(5))); } }
	 * } catch (SQLException | ClassNotFoundException e) { // TODO Auto-generated
	 * catch block e.printStackTrace(); } }
	 */

	public boolean editar_Vehiculo(JTextField modelo, JTextField nombre, JTextField marca, JTextField transmision,
			JTextField tarifa, JTextField año, JComboBox cmb) {
		conexion = null;
		stm = null;
		rs = null;
		String modelo_id = "";
		boolean existe = false;

		try {
			Class.forName(CONTROLADOR);
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
			System.out.println("Conexión OK");

			stm = (Statement) conexion.createStatement();
			rs = stm.executeQuery("SELECT * FROM vehiculo");

			while (rs.next()) {
				if (cmb.getSelectedItem().toString().contains(rs.getString(1))) {
					modelo_id = rs.getString(1);
				}
			}

			rs = stm.executeQuery("SELECT * FROM marca");

			while (rs.next()) {
				if (marca.getText().equals(rs.getString(1))) {
					existe = true;
				}
			}
			if (existe) {
				PreparedStatement stm = (PreparedStatement) conexion.prepareStatement(
						"UPDATE vehiculo set nombre = ?, marca = ?, transmision = ?, tarifa = ?, año = ? where modelo = '"
								+ modelo_id + "'");
				stm.setString(1, nombre.getText().trim());
				stm.setString(2, marca.getText().trim());
				stm.setString(3, transmision.getText().trim());
				stm.setInt(4, Integer.valueOf(tarifa.getText()));
				stm.setInt(5, Integer.valueOf(año.getText()));
				stm.executeUpdate();
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return existe;
	}

	public boolean editar_Categoria(JTextField nombre, JTextField cant_llantas, JTextField uso, JTextField peso,
			JComboBox cmb) {
		conexion = null;
		stm = null;
		rs = null;
		boolean existe = false;

		try {
			Class.forName(CONTROLADOR);
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
			System.out.println("Conexión OK");

			stm = (Statement) conexion.createStatement();
			rs = stm.executeQuery("SELECT * FROM categoria");
			int id = 0;

			// Ciclo para buscar y almacenar el ID de la categoria que se quiere modificar
			while (rs.next()) {
				if (rs.getString(2).equals(cmb.getSelectedItem().toString())) {
					id = rs.getInt(1);
				}
			}

			// Ciclo para verificar que si se quiere cambiar el nombre de la categoria este
			// no exista ya
			if (!nombre.getText().equals(cmb.getSelectedItem().toString())) {
				rs = stm.executeQuery("SELECT * FROM categoria");
				while (rs.next()) {
					if (rs.getString(2).equals(nombre.getText())) {
						existe = false;
						id = 0;
					}
				}
			}

			if (id != 0) {
				PreparedStatement stm = (PreparedStatement) conexion.prepareStatement(
						"UPDATE categoria set nombre = ?, cantidad_llantas = ?, uso = ?, peso_promedio = ? where id = "
								+ id);
				stm.setString(1, nombre.getText().trim());
				stm.setInt(2, Integer.valueOf(cant_llantas.getText()));
				stm.setString(3, uso.getText().trim());
				stm.setInt(4, Integer.valueOf(peso.getText()));
				stm.executeUpdate();
				existe = true;
			} else {
				existe = false;
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return existe;
	}

	public void eliminar_Vehiculo(JComboBox cmb) {
		conexion = null;
		stm = null;
		rs = null;
		String modelo_id = "";

		try {
			Class.forName(CONTROLADOR);
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
			System.out.println("Conexión OK");

			stm = (Statement) conexion.createStatement();
			rs = stm.executeQuery("SELECT * FROM vehiculo");

			while (rs.next()) {
				if (cmb.getSelectedItem().toString().contains(rs.getString(1))) {
					modelo_id = rs.getString(1);
				}
			}

			PreparedStatement stm = (PreparedStatement) conexion
					.prepareStatement("delete from vehiculo where modelo = '" + modelo_id + "'");

			stm.executeUpdate();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void eliminar_Categoria(JComboBox cmb) {
		conexion = null;
		stm = null;
		rs = null;

		try {
			Class.forName(CONTROLADOR);
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
			System.out.println("Conexión OK");

			stm = (Statement) conexion.createStatement();
			rs = stm.executeQuery("SELECT * FROM categoria");
			int id = 0;

			// Ciclo para buscar y almacenar el ID de la categoria que se quiere eliminar
			while (rs.next()) {
				if (rs.getString(2).equals(cmb.getSelectedItem().toString())) {
					id = rs.getInt(1);
				}
			}

			PreparedStatement stm = (PreparedStatement) conexion
					.prepareStatement("delete from categoria where id = " + id);

			stm.executeUpdate();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
