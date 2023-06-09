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
import java.util.ArrayList;

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

	Date fechaInicial_RentasPasadas;
	Date fechaInicial_NuevaRenta;
	Date fechaFinal_RentasPasadas;
	Date fechaFinal_NuevaRenta;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	java.sql.Date fecha_renta_sql;
	java.sql.Date fecha_entrega_sql;
	Date fecha_Nacimiento;
	java.sql.Date fecha_Nacimiento_sql;
	ArrayList<String> valores = new ArrayList<String>();

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

				if (nombre_completo.equals(cliente.getText()) && contrasena.equals(contrasena_Formato_String) && rs.getBoolean(7)) {
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
					"select categoria.nombre, vehiculo.modelo, vehiculo.año, categoria.cantidad_llantas, categoria.uso, categoria.peso_promedio from vehiculo, categoria where categoria.nombre = vehiculo.categoria AND categoria.es_visible = true AND vehiculo.es_visible = true");

			while (rs.next()) {
				tabla.addRow(new Object[] { rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4),
						rs.getString(5), rs.getInt(6) });
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
			rs = stm.executeQuery("SELECT * FROM marca where es_visible = true");

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
			rs = stm.executeQuery("SELECT * FROM cliente where es_visible = true");

			while (rs.next()) {
				tabla.addRow(new Object[] { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6) });
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void consultar_Vehiculos(DefaultTableModel tabla_Rentas, DefaultTableModel tabla_Tarifas, JComboBox cmb) {
		conexion = null;
		stm = null;
		rs = null;

		try {
			Class.forName(CONTROLADOR);
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
			System.out.println("Conexión OK");

			stm = (Statement) conexion.createStatement();
			rs = stm.executeQuery("SELECT * FROM vehiculo where es_visible = true");

			while (rs.next()) {
				if (cmb.getSelectedItem().toString().contains(rs.getString(2))
						&& cmb.getSelectedItem().toString().contains(String.valueOf(rs.getInt(7)))) {
					tabla_Tarifas.addRow(
							new Object[] { rs.getString(4), rs.getString(2), rs.getInt(7), "$" + rs.getInt(6) });
				}
			}
			rs = stm.executeQuery("SELECT * FROM renta where es_visible = true");

			while (rs.next()) {
				if (cmb.getSelectedItem().toString().contains(rs.getString(8))
						&& cmb.getSelectedItem().toString().contains(String.valueOf(rs.getInt(9)))) {
					tabla_Rentas.addRow(
							new Object[] { rs.getString(8), rs.getInt(9), rs.getString(2) + " " + rs.getString(3),
									rs.getDate(6), rs.getDate(7), "$" + rs.getDouble(10) });
				}
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
			rs = stm.executeQuery("SELECT * FROM renta where es_visible = true");

			while (rs.next()) {
				String nom_cliente = rs.getString(2) + " " + rs.getString(3);
				tabla.addRow(new Object[] { rs.getString(8), rs.getInt(9), nom_cliente, rs.getDate(6), rs.getDate(7),
						"$" + rs.getDouble(10) });
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
		int vehiculo_id = 0;
		Boolean existente = false, existente_antiguo = false;

		try {
			Class.forName(CONTROLADOR);
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
			System.out.println("Conexión OK");

			stm = (Statement) conexion.createStatement();
			rs = stm.executeQuery("SELECT * FROM vehiculo");

			while (rs.next()) {
				if (rs.getString(2).equals(modelo.getText()) && rs.getInt(7) == Integer.parseInt(año.getText())) {
					if (rs.getBoolean(9) == true) {
						existente = true;
					} else {
						existente_antiguo = true;
					}

				}
			}

			rs = stm.executeQuery("SELECT * FROM vehiculo");
			// SE BUSCA EL ID DE EL VEHICULO
			while (rs.next()) {
				if (modelo.getText().equals(rs.getString(2))
						&& año.getText().equals(String.valueOf(rs.getInt(7)))) {
					vehiculo_id = rs.getInt(1);
				}
			}

			if (existente == false && existente_antiguo == false) {
				PreparedStatement stm = (PreparedStatement) conexion
						.prepareStatement("INSERT INTO vehiculo VALUE(?,?,?,?,?,?,?,?,?)");

				stm.setString(1, "0");
				stm.setString(2, modelo.getText().trim());
				stm.setString(3, nombre.getText().trim());
				stm.setString(4, cmb.getSelectedItem().toString());
				stm.setString(5, transmision.getText().trim());
				stm.setInt(6, Integer.valueOf(tarifa.getText()));
				stm.setString(7, año.getText());
				stm.setString(8, cmb_aux.getSelectedItem().toString());
				stm.setBoolean(9, true);
				stm.executeUpdate();
			} else if (existente == false && existente_antiguo == true) {
				PreparedStatement stm = (PreparedStatement) conexion.prepareStatement(
						"UPDATE vehiculo set nombre = ?, marca = ?, modelo = ?, transmision = ?, tarifa = ?, año = ?, categoria = ?, es_visible = ? where id_vehiculo = "
								+ vehiculo_id);

				stm.setString(1, nombre.getText().trim());
				stm.setString(2, cmb.getSelectedItem().toString());
				stm.setString(3, modelo.getText().trim());
				stm.setString(4, transmision.getText().trim());
				stm.setInt(5, Integer.valueOf(tarifa.getText()));
				stm.setString(6, año.getText());
				stm.setString(7, cmb_aux.getSelectedItem().toString());
				stm.setBoolean(8, true);
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
		boolean marca_existente = false, marca_antigua = false;

		try {
			Class.forName(CONTROLADOR);
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
			System.out.println("Conexión OK");

			stm = (PreparedStatement) conexion.prepareStatement("SELECT * FROM marca WHERE nombre = ?");
			stm.setString(1, nombre_marca.trim());
			rs = stm.executeQuery();

			if (rs.next()) {
				if (rs.getBoolean(6) == true) {
					marca_existente = true;
				} else {
					marca_antigua = true;
				}
			}

			if (!marca_existente && !marca_antigua) {
				stm = (PreparedStatement) conexion.prepareStatement(
						"INSERT INTO marca (nombre, pais_origen, representante, correo_contacto, numero_contacto, es_visible) VALUES (?, ?, ?, ?, ?, ?)");
				stm.setString(1, nombre_marca.trim());
				stm.setString(2, pais_origen_marca.trim());
				stm.setString(3, representante_marca.trim());
				stm.setString(4, correo_contacto.trim());
				stm.setString(5, numero_contacto.trim());
				stm.setBoolean(6, true);
				stm.executeUpdate();
				return true;
			} else if (!marca_existente && marca_antigua) {
				stm = (PreparedStatement) conexion.prepareStatement(
						"UPDATE marca set nombre = ?, pais_origen = ?, representante = ?, correo_contacto = ?, numero_contacto = ?, es_visible = ? WHERE nombre = '"
								+ nombre_marca + "'");
				stm.setString(1, nombre_marca.trim());
				stm.setString(2, pais_origen_marca.trim());
				stm.setString(3, representante_marca.trim());
				stm.setString(4, correo_contacto.trim());
				stm.setString(5, numero_contacto.trim());
				stm.setBoolean(6, true);
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

	public boolean añadir_cliente(String nombre_cliente,String apellidos_cliente,String numero_telefono,
			String contrasena_cliente, String fecha_nacimiento) {
		conexion = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		int cliente_id = 0;
		Boolean cliente_existente = false, cliente_existente_antiguo = false;

		try {
			Class.forName(CONTROLADOR);
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
			System.out.println("Conexión OK");

			stm = (PreparedStatement) conexion.prepareStatement("SELECT * FROM cliente WHERE nombre = ? AND apellidos = ?");
			stm.setString(1, nombre_cliente);
			stm.setString(2, apellidos_cliente);
			rs = stm.executeQuery();

			while (rs.next()) {
				if (rs.getBoolean(7) == true) {
					cliente_existente = true;
				} else {
					cliente_existente_antiguo = true;
				}
				break;
			}

			// SE BUSCA EL ID DE EL CLIENTE
			rs.close();
			rs = stm.executeQuery("SELECT * FROM cliente");
			while (rs.next()) {
				if (nombre_cliente.equals(rs.getString(2)) && apellidos_cliente.equals(rs.getString(3))) {
					cliente_id = rs.getInt(1);
					break;
				}
			}

			System.out.println(cliente_id);
			if (!cliente_existente && !cliente_existente_antiguo) {
				stm = (PreparedStatement) conexion.prepareStatement(
						"INSERT INTO cliente (nombre, apellidos, numero_telefono, contrasena, fecha_nacimiento, es_visible) VALUES (?, ?, ?, ?, ?,?)");
				stm.setString(1, nombre_cliente.trim());
				stm.setString(2, apellidos_cliente.trim());
				stm.setString(3, numero_telefono.trim());
				stm.setString(4, contrasena_cliente.trim());
				stm.setString(5, fecha_nacimiento.trim());
				stm.setBoolean(6, true);
				stm.executeUpdate();
			} else if (!cliente_existente && cliente_existente_antiguo) {
				stm = (PreparedStatement) conexion.prepareStatement(
						"UPDATE cliente SET nombre = ?, apellidos = ?, numero_telefono = ?, fecha_nacimiento = ?, contrasena = ?, es_visible = ? WHERE cliente_id = ?");
				stm.setString(1, nombre_cliente.trim());
				stm.setString(2, apellidos_cliente.trim());
				stm.setString(3, numero_telefono.trim());
				stm.setString(4, fecha_nacimiento.trim());
				stm.setString(5, contrasena_cliente.trim());
				stm.setBoolean(6, true);
				stm.setInt(7, cliente_id);
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
		String modelo = "", telefono = "";
		int resultado = 0, año = 0;

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
				if (cmb.getSelectedItem().toString().contains(rs.getString(8))
						&& cmb.getSelectedItem().toString().contains(String.valueOf(rs.getInt(9)))) {
					try {
						modelo = rs.getString(8);
						año = rs.getInt(9);
						fechaInicial_RentasPasadas = sdf.parse(rs.getString(6));
						fechaFinal_RentasPasadas = sdf.parse(rs.getString(7));
						if ((((fechaInicial_NuevaRenta.before(fechaFinal_RentasPasadas)
								&& (fechaInicial_NuevaRenta.after(fechaInicial_RentasPasadas)
										|| fechaInicial_NuevaRenta.equals(fechaInicial_RentasPasadas)))
								|| (fechaInicial_NuevaRenta.before(fechaFinal_RentasPasadas)
										&& fechaFinal_NuevaRenta.after(fechaInicial_RentasPasadas))))
								&& rs.getBoolean(11) == true) {
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
				año = Integer.parseInt(datos_vehiculo[3]);
			}

			// SE BUSCA LA TARIFA QUE LE PERTENECE AL VEHICULO
			rs = stm.executeQuery("SELECT * FROM vehiculo");
			while (rs.next()) {
				if (cmb.getSelectedItem().toString().contains(rs.getString(2))
						&& cmb.getSelectedItem().toString().contains(String.valueOf(rs.getInt(7)))) {
					tarifa = rs.getInt(6);
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
						.prepareStatement("INSERT INTO renta VALUE(?,?,?,?,?,?,?,?,?,?,?)");

				stm.setString(1, "0");
				stm.setString(2, nombre_cliente.getText().trim());
				stm.setString(3, apellidos_cliente.getText().trim());
				stm.setString(4, telefono);
				stm.setInt(5, tarifa);
				stm.setDate(6, fecha_renta_sql);
				stm.setDate(7, fecha_entrega_sql);
				stm.setString(8, modelo);
				stm.setInt(9, año);
				stm.setDouble(10, costo_total);
				stm.setBoolean(11, true);
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
		int id = 0;
		Boolean existente = false, existente_antiguo = false;

		try {
			Class.forName(CONTROLADOR);
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
			System.out.println("Conexión OK");

			stm = (Statement) conexion.createStatement();
			rs = stm.executeQuery("SELECT * FROM categoria");

			while (rs.next()) {
				if (rs.getString(2).equals(nombre.getText())) {
					if (rs.getBoolean(6) == true) {
						existente = true;
					} else {
						existente_antiguo = true;
					}

				}
			}

			rs = stm.executeQuery("SELECT * FROM categoria");
			// Ciclo para buscar y almacenar el ID de la categoria que se quiere modificar
			while (rs.next()) {
				if (rs.getString(2).equals(nombre.getText())) {
					id = rs.getInt(1);
				}
			}
			System.out.println(id);

			if (existente == false && existente_antiguo == false) {
				PreparedStatement stm = (PreparedStatement) conexion
						.prepareStatement("INSERT INTO categoria VALUE(?,?,?,?,?,?)");

				stm.setString(1, "0");
				stm.setString(2, nombre.getText().trim());
				stm.setInt(3, Integer.valueOf(cant_llantas.getText()));
				stm.setString(4, uso.getText().trim());
				stm.setInt(5, Integer.valueOf(peso.getText()));
				stm.setBoolean(6, true);
				stm.executeUpdate();
			} else if (existente == false && existente_antiguo == true) {
				PreparedStatement stm = (PreparedStatement) conexion.prepareStatement(
						"UPDATE categoria set nombre = ?, cantidad_llantas = ?, uso = ?, peso_promedio = ?, es_visible = ? where id = "
								+ id);
				stm.setString(1, nombre.getText().trim());
				stm.setInt(2, Integer.valueOf(cant_llantas.getText()));
				stm.setString(3, uso.getText().trim());
				stm.setInt(4, Integer.valueOf(peso.getText()));
				stm.setBoolean(5, true);
				stm.executeUpdate();
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return existente;
	}

	public boolean editar_Vehiculo(JTextField modelo, JTextField nombre, JTextField marca, JTextField transmision,
			JTextField tarifa, JTextField año, JComboBox cmb, JComboBox cmb_aux) {
		conexion = null;
		stm = null;
		rs = null;
		int vehiculo_id = 0;
		boolean existe = false;

		try {
			Class.forName(CONTROLADOR);
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
			System.out.println("Conexión OK");

			stm = (Statement) conexion.createStatement();
			rs = stm.executeQuery("SELECT * FROM vehiculo");

			while (rs.next()) {
				if (cmb.getSelectedItem().toString().contains(rs.getString(2))
						&& cmb.getSelectedItem().toString().contains(String.valueOf(rs.getInt(7)))) {
					vehiculo_id = rs.getInt(1);
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
						"UPDATE vehiculo set nombre = ?, marca = ?, modelo = ?, transmision = ?, tarifa = ?, año = ?, categoria = ? where id_vehiculo = '"
								+ vehiculo_id + "'");
				stm.setString(1, nombre.getText().trim());
				stm.setString(2, marca.getText().trim());
				stm.setString(3, modelo.getText().trim());
				stm.setString(4, transmision.getText().trim());
				stm.setInt(5, Integer.valueOf(tarifa.getText()));
				stm.setInt(6, Integer.valueOf(año.getText()));
				stm.setString(7, cmb_aux.getSelectedItem().toString());
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
						&& cmb.getSelectedItem().toString().contains(rs.getString(7))
						&& cmb.getSelectedItem().toString().contains(String.valueOf(rs.getInt(9)))) {
					renta_id = rs.getInt(1);
				}
			}

			rs = stm.executeQuery("SELECT * FROM renta");
			while (rs.next() && resultado == 0) {
				if (cmb.getSelectedItem().toString().contains(rs.getString(8))
						&& cmb.getSelectedItem().toString().contains(String.valueOf(rs.getInt(9)))) {
					try {
						fechaInicial_RentasPasadas = sdf.parse(rs.getString(6));
						fechaFinal_RentasPasadas = sdf.parse(rs.getString(7));
						if (((fechaInicial_NuevaRenta.before(fechaFinal_RentasPasadas)
								&& (fechaInicial_NuevaRenta.after(fechaInicial_RentasPasadas)
										|| fechaInicial_NuevaRenta.equals(fechaInicial_RentasPasadas)))
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
				if (cmb.getSelectedItem().toString().contains(rs.getString(2))
						&& cmb.getSelectedItem().toString().contains(String.valueOf(rs.getInt(7)))) {
					tarifa = rs.getInt(6);
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
		int vehiculo_id = 0;

		try {
			Class.forName(CONTROLADOR);
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
			System.out.println("Conexión OK");

			stm = (Statement) conexion.createStatement();
			rs = stm.executeQuery("SELECT * FROM vehiculo");

			while (rs.next()) {
				if (cmb.getSelectedItem().toString().contains(rs.getString(2))
						&& cmb.getSelectedItem().toString().contains(String.valueOf(rs.getInt(7)))) {
					vehiculo_id = rs.getInt(1);
				}
			}

			// ELIMINADO FISICO
			/*
			 * PreparedStatement stm = (PreparedStatement) conexion
			 * .prepareStatement("delete from vehiculo where id_vehiculo = '" + vehiculo_id
			 * + "'");
			 * 
			 * stm.executeUpdate();
			 */
			// ELIMINADO LOGICO
			PreparedStatement stm = (PreparedStatement) conexion
					.prepareStatement("UPDATE vehiculo set es_visible = ? where id_vehiculo = '" + vehiculo_id + "'");
			stm.setBoolean(1, false);
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

			// ELIMINADO FISICO
			/*
			 * if (selectedMarca != null) { String query =
			 * "DELETE FROM marca WHERE nombre = ?"; stm = (PreparedStatement)
			 * conexion.prepareStatement(query); stm.setString(1, selectedMarca); int
			 * rowCount = stm.executeUpdate();
			 * 
			 * if (rowCount > 0) { JOptionPane.showMessageDialog(null,
			 * "Marca eliminada correctamente"); } else {
			 * JOptionPane.showMessageDialog(null,
			 * "Error. No se encontró la marca seleccionada en la base de datos."); } } else
			 * { JOptionPane.showMessageDialog(null,
			 * "Error. No se ha seleccionado ninguna marca."); }
			 */
			// ELIMINADO LOGICO
			if (selectedMarca != null) {
				String query = "UPDATE marca set es_visible = ? where nombre = ?";
				stm = (PreparedStatement) conexion.prepareStatement(query);
				stm.setBoolean(1, false);
				stm.setString(2, selectedMarca);
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

			// ELIMINADO FISICO
			/*
			 * PreparedStatement stm = (PreparedStatement) conexion
			 * .prepareStatement("delete from categoria where id = " + id);
			 */

			// ELIMINADO LOGICO
			PreparedStatement stm = (PreparedStatement) conexion
					.prepareStatement("UPDATE categoria set es_visible = ? where id = " + id);
			stm.setBoolean(1, false);
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
				// ELIMINADO FISICO
				/*
				 * PreparedStatement stm = (PreparedStatement) conexion
				 * .prepareStatement("DELETE FROM cliente WHERE cliente_id = ?"); stm.setInt(1,
				 * cliente_id); stm.executeUpdate();
				 */
				// ELIMINADO LOGICO
				PreparedStatement stm = (PreparedStatement) conexion
						.prepareStatement("UPDATE cliente set es_visible = ? WHERE cliente_id = ?");
				stm.setBoolean(1, false);
				stm.setInt(2, cliente_id);
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
						&& cmb.getSelectedItem().toString().contains(String.valueOf(rs.getInt(9)))
						&& cmb.getSelectedItem().toString().contains(rs.getString(6))
						&& cmb.getSelectedItem().toString().contains(rs.getString(7))) {
					renta_id = rs.getInt(1);
				}
			}

			// ELIMINADO FISICO
			/*
			 * PreparedStatement stm = (PreparedStatement) conexion
			 * .prepareStatement("delete from renta where renta_id = " + renta_id);
			 * stm.executeUpdate();
			 */
			// ELIMINADO LOGICO
			PreparedStatement stm = (PreparedStatement) conexion
					.prepareStatement("UPDATE renta set es_visible = ? where renta_id = " + renta_id);
			stm.setBoolean(1, false);
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

	public void llenar_CMB_Clientes(JComboBox cmb) {
		conexion = null;
		stm = null;
		rs = null;

		try {
			Class.forName(CONTROLADOR);
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
			System.out.println("Conexión OK");

			stm = (Statement) conexion.createStatement();
			rs = stm.executeQuery("SELECT * FROM cliente where es_visible = true");
			valores.clear();

			// SE AÑADEN LOS VALORES DENTRO DE LA CONSULTA AL COMBOBOX
			while (rs.next()) {
				cmb.addItem(rs.getString(2) + " " + rs.getString(3));
			}

			// SE AGREGAN LOS VALORES QUE ESTEN DENTRO DEL COMBOBOX A UN ARRAYLIST
			for (int i = 0; i < cmb.getItemCount(); i++) {
				valores.add(cmb.getItemAt(i).toString());
			}

			// SE RECORRE EL ARRAYLIST PARA VERIFICAR SI HAY VALORES REPETIDOS, EN CASO DE
			// QUE LOS HAYA SE ELIMINAN DE EL ARRAY Y EL COMOBOBOX
			for (int i = 0; i < valores.size(); i++) {
				for (int j = 0; j < valores.size(); j++) {
					if (valores.get(i).equals(valores.get(j)) && i != j) {
						valores.remove(j);
						cmb.removeItemAt(j);
					}
				}
			}

			// SE VUELVE A HACER LA CONSULTA PARA REMOVER ELEMENTOS QUE ESTEN DENTRO DEL
			// COMBOBOX QUE NO HAYAN SIDO ENCONTRADOS COMO REPETIDOS PERO ESTEN ELIMINADOS
			rs = stm.executeQuery("SELECT * FROM cliente");
			while (rs.next()) {
				for (int i = 0; i < cmb.getItemCount(); i++) {
					if (cmb.getItemAt(i).toString().contains(rs.getString(2))
							&& cmb.getItemAt(i).toString().contains(rs.getString(3)) && rs.getBoolean(7) == false) {
						cmb.removeItemAt(i);
					}
				}
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
			rs = stm.executeQuery("SELECT * FROM vehiculo where es_visible = true");
			valores.clear();

			// SE AÑADEN LOS VALORES DENTRO DE LA CONSULTA AL COMBOBOX
			while (rs.next()) {
				cmb.addItem(rs.getString(4) + " " + rs.getString(3) + " " + rs.getString(2) + " " + rs.getInt(7));
			}

			// SE AGREGAN LOS VALORES QUE ESTEN DENTRO DEL COMBOBOX A UN ARRAYLIST
			for (int i = 0; i < cmb.getItemCount(); i++) {
				valores.add(cmb.getItemAt(i).toString());
			}

			// SE RECORRE EL ARRAYLIST PARA VERIFICAR SI HAY VALORES REPETIDOS, EN CASO DE
			// QUE LOS HAYA SE ELIMINAN DE EL ARRAY Y EL COMOBOBOX
			for (int i = 0; i < valores.size(); i++) {
				for (int j = 0; j < valores.size(); j++) {
					if (valores.get(i).equals(valores.get(j)) && i != j) {
						valores.remove(j);
						cmb.removeItemAt(j);
					}
				}
			}

			// SE VUELVE A HACER LA CONSULTA PARA REMOVER ELEMENTOS QUE ESTEN DENTRO DEL
			// COMBOBOX QUE NO HAYAN SIDO ENCONTRADOS COMO REPETIDOS PERO ESTEN ELIMINADOS
			rs = stm.executeQuery("SELECT * FROM vehiculo");
			while (rs.next()) {
				for (int i = 0; i < cmb.getItemCount(); i++) {
					if (cmb.getItemAt(i).toString().contains(rs.getString(2))
							&& cmb.getItemAt(i).toString().contains(String.valueOf(rs.getInt(7)))
							&& rs.getBoolean(9) == false) {
						cmb.removeItemAt(i);
					}
				}
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
			rs = stm.executeQuery("SELECT * FROM categoria where es_visible = true");
			valores.clear();

			// SE AÑADEN LOS VALORES DENTRO DE LA CONSULTA AL COMBOBOX
			while (rs.next()) {
				cmb.addItem(rs.getString(2));
			}

			// SE AGREGAN LOS VALORES QUE ESTEN DENTRO DEL COMBOBOX A UN ARRAYLIST
			for (int i = 0; i < cmb.getItemCount(); i++) {
				valores.add(cmb.getItemAt(i).toString());
			}

			// SE RECORRE EL ARRAYLIST PARA VERIFICAR SI HAY VALORES REPETIDOS, EN CASO DE
			// QUE LOS HAYA SE ELIMINAN DE EL ARRAY Y EL COMOBOBOX
			for (int i = 0; i < valores.size(); i++) {
				for (int j = 0; j < valores.size(); j++) {
					if (valores.get(i).equals(valores.get(j)) && i != j) {
						valores.remove(j);
						cmb.removeItemAt(j);
					}
				}
			}

			// SE VUELVE A HACER LA CONSULTA PARA REMOVER ELEMENTOS QUE ESTEN DENTRO DEL
			// COMBOBOX QUE NO HAYAN SIDO ENCONTRADOS COMO REPETIDOS PERO ESTEN ELIMINADOS
			rs = stm.executeQuery("SELECT * FROM categoria");
			while (rs.next()) {
				for (int i = 0; i < cmb.getItemCount(); i++) {
					if (cmb.getItemAt(i).toString().equals(rs.getString(2)) && rs.getBoolean(6) == false) {
						cmb.removeItemAt(i);
					}
				}
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
			rs = stm.executeQuery("SELECT * FROM marca where es_visible = true");
			valores.clear();

			// SE AÑADEN LOS VALORES DENTRO DE LA CONSULTA AL COMBOBOX
			while (rs.next()) {
				cmb.addItem(rs.getString(1));
			}

			// SE AGREGAN LOS VALORES QUE ESTEN DENTRO DEL COMBOBOX A UN ARRAYLIST
			for (int i = 0; i < cmb.getItemCount(); i++) {
				valores.add(cmb.getItemAt(i).toString());
			}

			// SE RECORRE EL ARRAYLIST PARA VERIFICAR SI HAY VALORES REPETIDOS, EN CASO DE
			// QUE LOS HAYA SE ELIMINAN DE EL ARRAY Y EL COMOBOBOX
			for (int i = 0; i < valores.size(); i++) {
				for (int j = 0; j < valores.size(); j++) {
					if (valores.get(i).equals(valores.get(j)) && i != j) {
						valores.remove(j);
						cmb.removeItemAt(j);
					}
				}
			}

			// SE VUELVE A HACER LA CONSULTA PARA REMOVER ELEMENTOS QUE ESTEN DENTRO DEL
			// COMBOBOX QUE NO HAYAN SIDO ENCONTRADOS COMO REPETIDOS PERO ESTEN ELIMINADOS
			rs = stm.executeQuery("SELECT * FROM marca");
			while (rs.next()) {
				for (int i = 0; i < cmb.getItemCount(); i++) {
					if (cmb.getItemAt(i).toString().equals(rs.getString(1)) && rs.getBoolean(6) == false) {
						cmb.removeItemAt(i);
					}
				}
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
			rs = stm.executeQuery("SELECT * FROM renta where es_visible = true");
			valores.clear();

			// SE AÑADEN LOS VALORES DENTRO DE LA CONSULTA AL COMBOBOX
			while (rs.next()) {
				cmb.addItem(rs.getString(2) + " " + rs.getString(3) + " - " + rs.getString(8) + " " + rs.getInt(9)
						+ " - " + rs.getString(6) + " a " + rs.getString(7));
			}

			// SE AGREGAN LOS VALORES QUE ESTEN DENTRO DEL COMBOBOX A UN ARRAYLIST
			for (int i = 0; i < cmb.getItemCount(); i++) {
				valores.add(cmb.getItemAt(i).toString());
			}

			// SE RECORRE EL ARRAYLIST PARA VERIFICAR SI HAY VALORES REPETIDOS, EN CASO DE
			// QUE LOS HAYA SE ELIMINAN DE EL ARRAY Y EL COMOBOBOX
			for (int i = 0; i < valores.size(); i++) {
				for (int j = 0; j < valores.size(); j++) {
					if (valores.get(i).equals(valores.get(j)) && i != j) {
						valores.remove(j);
						cmb.removeItemAt(j);
					}
				}
			}

			// SE VUELVE A HACER LA CONSULTA PARA REMOVER ELEMENTOS QUE ESTEN DENTRO DEL
			// COMBOBOX QUE NO HAYAN SIDO ENCONTRADOS COMO REPETIDOS PERO ESTEN ELIMINADOS
			rs = stm.executeQuery("SELECT * FROM renta");
			while (rs.next()) {
				for (int i = 0; i < cmb.getItemCount(); i++) {
					if (cmb.getItemAt(i).toString().contains(rs.getString(2))
							&& cmb.getItemAt(i).toString().contains(rs.getString(3))
							&& cmb.getItemAt(i).toString().contains(rs.getString(8))
							&& cmb.getItemAt(i).toString().contains(String.valueOf(rs.getInt(9)))
							&& rs.getBoolean(11) == false) {
						cmb.removeItemAt(i);
					}
				}
			}

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void llenar_TextField_Vehiculos(JComboBox cmb, JTextField nombre, JTextField modelo, JTextField tarifa,
			JTextField marca, JTextField transmision, JTextField año) {
		conexion = null;
		stm = null;
		rs = null;

		try {
			Class.forName(CONTROLADOR);
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
			System.out.println("Conexión OK");

			stm = (Statement) conexion.createStatement();
			rs = stm.executeQuery("select * from vehiculo");

			while (rs.next()) {
				if (cmb.getSelectedItem().toString().contains(rs.getString(2))
						&& cmb.getSelectedItem().toString().contains(String.valueOf(rs.getInt(7)))) {
					nombre.setText(rs.getString(3));
					modelo.setText(rs.getString(2));
					tarifa.setText(String.valueOf(rs.getInt(6)));
					marca.setText(rs.getString(4));
					transmision.setText(rs.getString(5));
					año.setText(String.valueOf(rs.getInt(7)));

				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void llenar_TextField_Clientes(JComboBox cmb, JTextField nombre, JTextField telefono, JTextField apellidos,
			JTextField fecha_nacimiento) {
		conexion = null;
		stm = null;
		rs = null;

		try {
			Class.forName(CONTROLADOR);
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
			System.out.println("Conexión OK");

			stm = (Statement) conexion.createStatement();
			rs = stm.executeQuery("select * from cliente");

			while (rs.next()) {
				if (cmb.getSelectedItem().toString().contains(rs.getString(2))
						&& cmb.getSelectedItem().toString().contains(rs.getString(3))) {
					nombre.setText(rs.getString(2));
					telefono.setText(rs.getString(4));
					apellidos.setText(rs.getString(3));
					fecha_nacimiento.setText(rs.getDate(6).toString());

				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void llenar_TextField_Rentas(JComboBox cmb, JTextField nombre, JTextField apellidos,
			JTextField fecha_inicial, JTextField fecha_final) {
		conexion = null;
		stm = null;
		rs = null;

		try {
			Class.forName(CONTROLADOR);
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
			System.out.println("Conexión OK");

			stm = (Statement) conexion.createStatement();
			rs = stm.executeQuery("select * from renta");

			while (rs.next()) {
				if (cmb.getSelectedItem().toString().contains(rs.getString(8))
						&& cmb.getSelectedItem().toString().contains(String.valueOf(rs.getInt(9)))
						&& cmb.getSelectedItem().toString().contains(rs.getString(6))
						&& cmb.getSelectedItem().toString().contains(rs.getString(7))) {
					nombre.setText(rs.getString(2));
					apellidos.setText(rs.getString(3));
					fecha_inicial.setText(rs.getDate(6).toString());
					fecha_final.setText(rs.getDate(7).toString());

				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void llenar_TextField_Categorias(JComboBox cmb, JTextField nombre, JTextField cant_llantas, JTextField uso,
			JTextField peso) {
		conexion = null;
		stm = null;
		rs = null;

		try {
			Class.forName(CONTROLADOR);
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
			System.out.println("Conexión OK");

			stm = (Statement) conexion.createStatement();
			rs = stm.executeQuery("select * from categoria");

			while (rs.next()) {
				if (cmb.getSelectedItem().toString().equals(rs.getString(2))) {
					nombre.setText(rs.getString(2));
					cant_llantas.setText(String.valueOf(rs.getInt(3)));
					uso.setText(rs.getString(4));
					peso.setText(String.valueOf(rs.getInt(5)));

				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void llenar_TextField_Marcas(JComboBox cmb, JTextField nombre, JTextField pais, JTextField correo,
			JTextField representante, JTextField numero) {
		conexion = null;
		stm = null;
		rs = null;

		try {
			Class.forName(CONTROLADOR);
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
			System.out.println("Conexión OK");

			stm = (Statement) conexion.createStatement();
			rs = stm.executeQuery("select * from marca");

			while (rs.next()) {
				if (cmb.getSelectedItem().toString().equals(rs.getString(1))) {
					nombre.setText(rs.getString(1));
					pais.setText(rs.getString(3));
					correo.setText(rs.getString(5));
					representante.setText(rs.getString(2));
					numero.setText(rs.getString(4));
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
