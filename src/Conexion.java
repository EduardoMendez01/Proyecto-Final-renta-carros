import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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
	private static final String CLAVE = "Elcasconegro1";

	Date fechaInicial_RentasPasadas;
	Date fechaInicial_NuevaRenta;
	Date fechaFinal_RentasPasadas;
	Date fechaFinal_NuevaRenta;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	java.sql.Date fecha_renta_sql;
	java.sql.Date fecha_entrega_sql;
	Date fecha_Nacimiento;
	java.sql.Date fecha_Nacimiento_sql;

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
			rs = stm.executeQuery(
					"select categoria.nombre, vehiculo.modelo, categoria.cantidad_llantas, categoria.uso, categoria.peso_promedio from vehiculo, categoria where categoria.nombre = vehiculo.categoria;");

			while (rs.next()) {
				tabla.addRow(
						new Object[] { rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5) });
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
				tabla.addRow(new Object[] { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), });
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
				tabla.addRow(new Object[] { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6) });
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
			JTextField año, JComboBox cmb, JComboBox cmb_aux) {
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
						.prepareStatement("INSERT INTO vehiculo VALUE(?,?,?,?,?,?,?)");

				stm.setString(1, modelo.getText().trim());
				stm.setString(2, nombre.getText().trim());
				stm.setString(3, cmb.getSelectedItem().toString());
				stm.setString(4, transmision.getText().trim());
				stm.setInt(5, Integer.valueOf(tarifa.getText()));
				stm.setString(6, año.getText());
				stm.setString(7, cmb_aux.getSelectedItem().toString());
				stm.executeUpdate();
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return existente;
	}

	public boolean añadir_marca(String nombre_marca, String pais_origen_marca, String representante_marca,
			String correo_contacto, String numero_contacto, JComboBox cmb) {
		conexion = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		boolean marca_existente = false;

		try {
			Class.forName(CONTROLADOR);
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
			System.out.println("Conexión OK");

			stm = (PreparedStatement) conexion.prepareStatement("SELECT * FROM marca WHERE nombre = ?");
			stm.setString(1, nombre_marca.trim());
			rs = stm.executeQuery();

			if (rs.next()) {
				marca_existente = true;
			}

			if (!marca_existente) {
				stm = (PreparedStatement) conexion.prepareStatement(
						"INSERT INTO marca (nombre, pais_origen, representante, correo_contacto, numero_contacto) VALUES (?, ?, ?, ?, ?)");
				stm.setString(1, nombre_marca.trim());
				stm.setString(2, pais_origen_marca.trim());
				stm.setString(3, representante_marca.trim());
				stm.setString(4, correo_contacto.trim());
				stm.setString(5, numero_contacto.trim());
				stm.executeUpdate();
				return true;
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stm != null) {
					stm.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean añadir_cliente(String nombre_cliente, String numero_telefono, String apellidos_cliente,
			String contrasena_cliente, String fecha_nacimiento) {
		conexion = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		Boolean cliente_existente = false;

		try {
			Class.forName(CONTROLADOR);
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
			System.out.println("Conexión OK");

			stm = (PreparedStatement) conexion.prepareStatement("SELECT * FROM cliente WHERE nombre = ?");
			stm.setString(1, nombre_cliente);
			rs = stm.executeQuery();

			while (rs.next()) {
				cliente_existente = true;
				break;
			}

			if (!cliente_existente) {
				stm = (PreparedStatement) conexion.prepareStatement(
						"INSERT INTO cliente (nombre, numero_telefono, apellidos, contrasena, fecha_nacimiento) VALUES (?, ?, ?, ?, ?)");
				stm.setString(1, nombre_cliente.trim());
				stm.setString(3, numero_telefono.trim());
				stm.setString(2, apellidos_cliente.trim());
				stm.setString(4, contrasena_cliente.trim());
				stm.setString(5, fecha_nacimiento.trim());
				stm.executeUpdate();
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stm != null) {
					stm.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return cliente_existente;
	}

	public int añadir_Renta(JTextField nombre_cliente, JTextField apellidos_cliente, JTextField fecha_renta,
			JTextField fecha_entrega, JComboBox cmb) {
		conexion = null;
		stm = null;
		rs = null;
		int tarifa = 0;
		String modelo = "";
		String telefono = "";
		int resultado = 0;

		try {
			Class.forName(CONTROLADOR);
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
			System.out.println("Conexión OK");

			// Se crean las variables de fechas para ser manipuladas en java y asignarlas a
			// la tabla en SQL
			fechaInicial_NuevaRenta = sdf.parse(fecha_renta.getText());
			fechaFinal_NuevaRenta = sdf.parse(fecha_entrega.getText());
			fecha_renta_sql = new java.sql.Date(fechaInicial_NuevaRenta.getTime());
			fecha_entrega_sql = new java.sql.Date(fechaFinal_NuevaRenta.getTime());

			stm = (Statement) conexion.createStatement();
			rs = stm.executeQuery("SELECT * FROM renta");

			// ESTE WHILE SIRVE PARA VALIDAR SI EL MODELO QUE EL USUARIO QUIERE RENTAR YA HA
			// SIDO RENTADO ANTES Y VALIDAR SI LAS FECHAS DE RENTA NO SE CRUZAN
			while (rs.next()) {
				if (cmb.getSelectedItem().toString().contains(rs.getString(8))) {
					try {
						modelo = rs.getString(8);
						fechaInicial_RentasPasadas = sdf.parse(rs.getString(6));
						fechaFinal_RentasPasadas = sdf.parse(rs.getString(7));
						if (fechaInicial_NuevaRenta.before(fechaFinal_RentasPasadas)
								&& fechaInicial_NuevaRenta.after(fechaInicial_RentasPasadas)) {
							resultado = 1;
						}
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

			// SE VALIDA QUE LA FECHA DE ENTREGA DE LA RENTA NO SEA ANTERIOR A LA FECHA
			// INICIAL
			if (fechaInicial_NuevaRenta.after(fechaFinal_NuevaRenta)) {
				resultado = 3;
			}
			// ESTE IF SIRVE PARA EN CASO DE QUE NO SE HAYA ENTRADO AL WHILE ANTERIOR PODER
			// ASIGNARLE UN VALOR AL CAMPO MODELO
			if (modelo.equals("")) {
				String line_datos_vehiculo = cmb.getSelectedItem().toString();
				String[] datos_vehiculo = line_datos_vehiculo.split(" ");
				modelo = datos_vehiculo[2];
			}

			// SE BUSCA LA TARIFA QUE LE PERTENECE AL VEHICULO
			rs = stm.executeQuery("SELECT * FROM vehiculo");
			while (rs.next()) {
				if (cmb.getSelectedItem().toString().contains(rs.getString(1))) {
					tarifa = rs.getInt(5);
				}
			}

			// SE BUSCA EL TELEFONO QUE LE PERTENECE AL CLIENTE
			rs = stm.executeQuery("SELECT * FROM cliente");
			while (rs.next()) {
				if (nombre_cliente.getText().equals(rs.getString(2))
						&& apellidos_cliente.getText().equals(rs.getString(3))) {
					telefono = rs.getString(4);
				}
			}

			// SE VERIFICA SI SE ENCONTRÓ EL TELEFONO DE EL CLIENTE Y POR ENDE EXISTE
			if (telefono == "") {
				resultado = 2;
			}

			if (resultado == 0) {
				// SE CALCULA LA DISTANCIA DE DIAS QUE EXISTEN ENTRE LAS FECHAS DE RENTA PARA
				// ASI CALCULAR EL COSTO TOTAL
				double costo_total = calcular_distancia_dias(fechaInicial_NuevaRenta, fechaFinal_NuevaRenta, tarifa);

				PreparedStatement stm = (PreparedStatement) conexion
						.prepareStatement("INSERT INTO renta VALUE(?,?,?,?,?,?,?,?,?)");

				stm.setString(1, "0");
				stm.setString(2, nombre_cliente.getText().trim());
				stm.setString(3, apellidos_cliente.getText().trim());
				stm.setString(4, telefono);
				stm.setInt(5, tarifa);
				stm.setDate(6, fecha_renta_sql);
				stm.setDate(7, fecha_entrega_sql);
				stm.setString(8, modelo);
				stm.setDouble(9, costo_total);
				stm.executeUpdate();
			}
		} catch (SQLException | ClassNotFoundException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
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

	public void llenar_CMB_Clientes(JComboBox cmb_cliente) {
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
				cmb_cliente.addItem(rs.getString(2) + " " + rs.getString(3));
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public void llenar_CMB_Marcas(JComboBox cmb_marca) {
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
				cmb_marca.addItem(rs.getString(1));
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void llenar_CMB_Rentas(JComboBox cmb) {
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
				cmb.addItem(rs.getString(2) + " " + rs.getString(3) + " - " + rs.getString(8) + " - " + rs.getString(6)
						+ " a " + rs.getString(7));
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
			JTextField tarifa, JTextField año, JComboBox cmb, JComboBox cmb_aux) {
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
						"UPDATE vehiculo set nombre = ?, marca = ?, transmision = ?, tarifa = ?, año = ?, categoria = ? where modelo = '"
								+ modelo_id + "'");
				stm.setString(1, nombre.getText().trim());
				stm.setString(2, marca.getText().trim());
				stm.setString(3, transmision.getText().trim());
				stm.setInt(4, Integer.valueOf(tarifa.getText()));
				stm.setInt(5, Integer.valueOf(año.getText()));
				stm.setString(6, cmb_aux.getSelectedItem().toString());
				stm.executeUpdate();
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return existe;
	}


	public boolean editar_Marca(JTextField nombreMarca, JTextField paisOrigen, JTextField representante,
			JTextField correo, JTextField numero, JComboBox cmb_marca) {
		conexion = null;
		stm = null;
		rs = null;
		boolean existe = false;

		try {
			Class.forName(CONTROLADOR);
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
			System.out.println("Conexión OK");

			stm = (Statement) conexion.createStatement();
			rs = stm.executeQuery("SELECT * FROM marca");

			while (rs.next()) {
				if (!nombreMarca.getText().equals(cmb_marca.getSelectedItem().toString())) {
					if (nombreMarca.getText().equals(rs.getString(1))) {
						existe = true;
					}
				}
			}

			if (!existe) {
				PreparedStatement stm = (PreparedStatement) conexion.prepareStatement(
						"UPDATE marca set nombre = ?, representante = ?, pais_origen = ?, numero_contacto = ?, correo_contacto = ? where nombre = '"
								+ cmb_marca.getSelectedItem().toString() + "'");
				stm.setString(1, nombreMarca.getText().trim());
				stm.setString(2, representante.getText().trim());
				stm.setString(3, paisOrigen.getText().trim());
				stm.setString(4, numero.getText().trim());
				stm.setString(5, correo.getText().trim());
				stm.executeUpdate();
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return existe;
	}

	public boolean editar_Cliente(JTextField in_apellidos_cliente, JTextField in_nombre_cliente,
			JTextField in_contrasena_cliente, JTextField in_fecha_nacimiento, JTextField in_numero_telefono,
			JComboBox cmb_cliente) {
		conexion = null;
		stm = null;
		rs = null;

		int cliente_id = 0;
		boolean existe = false;

		try {
			Class.forName(CONTROLADOR);
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
			System.out.println("Conexión OK");

			stm = (Statement) conexion.createStatement();
			rs = stm.executeQuery("SELECT * FROM cliente");

			while (rs.next()) {
				if (cmb_cliente.getSelectedItem().toString().contains(rs.getString(2))
						&& cmb_cliente.getSelectedItem().toString().contains(rs.getString(3))) {
					cliente_id = rs.getInt("cliente_id");
					break;
				}
			}

			if (cliente_id != 0) {
				// El cliente fue encontrado en la base de datos, se puede realizar la edición
				existe = true;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				fecha_Nacimiento = sdf.parse(in_fecha_nacimiento.getText());
				fecha_Nacimiento = new java.sql.Date(fecha_Nacimiento.getTime());

				PreparedStatement stm = (PreparedStatement) conexion.prepareStatement(
						"UPDATE cliente SET nombre = ?, apellidos = ?, numero_telefono = ?, fecha_nacimiento = ?, contrasena = ? WHERE cliente_id = ?");
				stm.setString(1, in_nombre_cliente.getText().trim());
				stm.setString(2, in_apellidos_cliente.getText().trim());
				stm.setString(3, in_numero_telefono.getText().trim());
				stm.setString(4, in_fecha_nacimiento.getText().trim());
				stm.setString(5, in_contrasena_cliente.getText().trim());
				stm.setInt(6, cliente_id);
				stm.executeUpdate();
			} else {
				JOptionPane.showMessageDialog(null,
						"Error. No se encontró el cliente seleccionado en la base de datos.");
			}
		} catch (SQLException | ClassNotFoundException | ParseException e) {
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

	public int editar_rentas(JTextField nombre_cliente, JTextField apellidos_cliente, JTextField fecha_renta,
			JTextField fecha_entrega, JComboBox cmb) {
		conexion = null;
		stm = null;
		rs = null;
		int tarifa = 0;
		int resultado = 0;
		int renta_id = 0;

		try {
			Class.forName(CONTROLADOR);
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
			System.out.println("Conexión OK");

			// Se crean las variables de fechas para ser manipuladas en java y asignarlas a
			// la tabla en SQL
			fechaInicial_NuevaRenta = sdf.parse(fecha_renta.getText());
			fechaFinal_NuevaRenta = sdf.parse(fecha_entrega.getText());
			fecha_renta_sql = new java.sql.Date(fechaInicial_NuevaRenta.getTime());
			fecha_entrega_sql = new java.sql.Date(fechaFinal_NuevaRenta.getTime());

			stm = (Statement) conexion.createStatement();

			// SE BUSCA QUE EL EL CLIENTE EXISTA
			rs = stm.executeQuery("SELECT * FROM cliente");
			while (rs.next()) {
				if (nombre_cliente.getText().equals(rs.getString(2))
						&& apellidos_cliente.getText().equals(rs.getString(3))) {
					resultado = 0;
					break;
				} else {
					resultado = 2;
				}
			}

			// ESTE WHILE SIRVE PARA VALIDAR SI EL MODELO QUE EL USUARIO QUIERE RENTAR YA HA
			// SIDO RENTADO ANTES Y VALIDAR SI LAS NUEVAS FECHAS DE RENTA NO SE CRUZAN CON
			// UNAS YA EXISTENTES
			rs = stm.executeQuery("SELECT * FROM renta");
			while (rs.next() && resultado == 0) {
				// ESTE IF VALIDA SI LA RENTA QUE ESTA SIENDO EDITADA ES LA MISMA QUE ESTA
				// SIENDO EVALUADA EN EL SELECT Y DE ESTA MANERA SE OBTIENE EL ID
				if (cmb.getSelectedItem().toString().contains(rs.getString(8))
						&& cmb.getSelectedItem().toString().contains(rs.getString(6))
						&& cmb.getSelectedItem().toString().contains(rs.getString(7))) {
					renta_id = rs.getInt(1);
				}
			}

			rs = stm.executeQuery("SELECT * FROM renta");
			while (rs.next() && resultado == 0) {
				if (cmb.getSelectedItem().toString().contains(rs.getString(8))) {
					try {
						fechaInicial_RentasPasadas = sdf.parse(rs.getString(6));
						fechaFinal_RentasPasadas = sdf.parse(rs.getString(7));
						if (((fechaInicial_NuevaRenta.before(fechaFinal_RentasPasadas)
								&& fechaInicial_NuevaRenta.after(fechaInicial_RentasPasadas))
								|| (fechaInicial_NuevaRenta.before(fechaFinal_RentasPasadas)
										&& fechaFinal_NuevaRenta.after(fechaInicial_RentasPasadas)))
								&& renta_id != rs.getInt(1)) {
							resultado = 1;
						}
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

			// SE VALIDA QUE LA FECHA DE ENTREGA DE LA RENTA NO SEA ANTERIOR A LA FECHA
			// INICIAL
			if (fechaInicial_NuevaRenta.after(fechaFinal_NuevaRenta)) {
				resultado = 3;
			}

			// SE BUSCA LA TARIFA QUE LE PERTENECE AL VEHICULO
			rs = stm.executeQuery("SELECT * FROM vehiculo");
			while (rs.next()) {
				if (cmb.getSelectedItem().toString().contains(rs.getString(1))) {
					tarifa = rs.getInt(5);
				}
			}

			if (resultado == 0) {
				// SE CALCULA LA DISTANCIA DE DIAS QUE EXISTEN ENTRE LAS FECHAS DE RENTA PARA
				// ASI CALCULAR EL COSTO TOTAL
				double costo_total = calcular_distancia_dias(fechaInicial_NuevaRenta, fechaFinal_NuevaRenta, tarifa);

				PreparedStatement stm = (PreparedStatement) conexion.prepareStatement(
						"UPDATE renta set nombre_cliente = ?, apellidos_cliente = ?, fecha_inicial_renta = ?, fecha_final_renta = ?, costo_final = ? where renta_id = "
								+ renta_id);
				stm.setString(1, nombre_cliente.getText().trim());
				stm.setString(2, apellidos_cliente.getText().trim());
				stm.setDate(3, fecha_renta_sql);
				stm.setDate(4, fecha_entrega_sql);
				stm.setDouble(5, costo_total);
				stm.executeUpdate();
			}
		} catch (SQLException | ClassNotFoundException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
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

	public void eliminar_Marca(JComboBox cmb_marca) {
		conexion = null;
		PreparedStatement stm = null;

		try {
			Class.forName(CONTROLADOR);
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
			System.out.println("Conexión OK");

			String selectedMarca = cmb_marca.getSelectedItem() != null ? cmb_marca.getSelectedItem().toString() : null;
			System.out.println("Selected marca: " + selectedMarca);

			if (selectedMarca != null) {
				String query = "DELETE FROM marca WHERE nombre = ?";
				stm = (PreparedStatement) conexion.prepareStatement(query);
				stm.setString(1, selectedMarca);
				int rowCount = stm.executeUpdate();

				if (rowCount > 0) {
					JOptionPane.showMessageDialog(null, "Marca eliminada correctamente");
				} else {
					JOptionPane.showMessageDialog(null,
							"Error. No se encontró la marca seleccionada en la base de datos.");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Error. No se ha seleccionado ninguna marca.");
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stm != null) {
					stm.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
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

	public void eliminar_Cliente(JComboBox cmb_cliente) {
		conexion = null;
		stm = null;
		rs = null;
		int cliente_id = 0;

		try {
			Class.forName(CONTROLADOR);
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
			System.out.println("Conexión OK");

			stm = (Statement) conexion.createStatement();
			rs = stm.executeQuery("SELECT * FROM cliente");

			while (rs.next()) {
				if (cmb_cliente.getSelectedItem().toString().contains(rs.getString(2))
						&& cmb_cliente.getSelectedItem().toString().contains(rs.getString(3))) {
					cliente_id = rs.getInt("cliente_id");
					break;
				}
			}

			if (cliente_id != 0) {
				// El cliente fue encontrado en la base de datos, se puede realizar la
				// eliminación
				PreparedStatement stm = (PreparedStatement) conexion
						.prepareStatement("DELETE FROM cliente WHERE cliente_id = ?");
				stm.setInt(1, cliente_id);
				stm.executeUpdate();
			} else {
				JOptionPane.showMessageDialog(null,
						"Error. No se encontró el cliente seleccionado en la base de datos.");
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void eliminar_Renta(JComboBox cmb) {
		conexion = null;
		stm = null;
		rs = null;
		int renta_id = 0;

		try {
			Class.forName(CONTROLADOR);
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
			System.out.println("Conexión OK");

			stm = (Statement) conexion.createStatement();
			rs = stm.executeQuery("SELECT * FROM renta");

			while (rs.next()) {
				// ESTE IF VALIDA SI LA RENTA QUE SE QUIERE ELIMINAR ES LA MISMA QUE ESTA
				// SIENDO EVALUADA EN EL SELECT Y DE ESTA MANERA SE OBTIENE EL ID
				if (cmb.getSelectedItem().toString().contains(rs.getString(8))
						&& cmb.getSelectedItem().toString().contains(rs.getString(6))
						&& cmb.getSelectedItem().toString().contains(rs.getString(7))) {
					renta_id = rs.getInt(1);
				}
			}

			PreparedStatement stm = (PreparedStatement) conexion
					.prepareStatement("delete from renta where renta_id = " + renta_id);

			stm.executeUpdate();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public double calcular_distancia_dias(Date fecha_Inicial, Date fecha_Final, int tarifa) {
		double costo_total;
		long diferencia_dias_milisegundos = fecha_Final.getTime() - fecha_Inicial.getTime();
		TimeUnit convertidor = TimeUnit.DAYS;
		long dias_transcurridos_long = convertidor.convert(diferencia_dias_milisegundos, TimeUnit.MILLISECONDS);
		int dias_transcurridos_int = (int) dias_transcurridos_long;
		if (dias_transcurridos_int == 0) {
			costo_total = tarifa;
		} else {
			costo_total = dias_transcurridos_int * tarifa;
		}
		return costo_total;
	}
}
