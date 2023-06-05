import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Ventana extends JFrame {
	private String actual = "login";
	private JPanel gran_panel = null;
	Conexion conexion = new Conexion();
	JComboBox cmb = new JComboBox();

	public Ventana() {
		this.setVisible(true);
		this.setSize(900, 550);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setTitle("Renta de carros");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.route();
	}

	public void route() {
		if (gran_panel != null) {
			this.remove(gran_panel);
		}
		if (actual.equals("login")) {
			gran_panel = login();
		}
		if (actual.equals("dash")) {
			gran_panel = dash();
		}
		if (actual.equals("vehiculos")) {
			gran_panel = vehiculos();
		}
		if (actual.equals("clientes")) {
			gran_panel = clientes();
		}
		if (actual.equals("rentas")) {
			gran_panel = rentas();
		}
		if (actual.equals("marcas")) {
			gran_panel = marcas();
		}
		if (actual.equals("categorias")) {
			gran_panel = categorias();
		}
		if (actual.equals("categorias_consultar")) {
			gran_panel = categorias_Consultar();
		}
		if (actual.equals("categorias_añadir")) {
			gran_panel = categorias_Añadir();
		}
		if (actual.equals("categorias_editar")) {
			gran_panel = categorias_Editar();
		}
		if (actual.equals("categorias_eliminar")) {
			gran_panel = categorias_Eliminar();
		}
		if (actual.equals("vehiculos")) {
			gran_panel = vehiculos();
		}
		if (actual.equals("vehiculos_consultar")) {
			gran_panel = vehiculos_Consultar();
		}
		if (actual.equals("vehiculos_añadir")) {
			gran_panel = vehiculos_añadir();
		}
		if (actual.equals("vehiculos_editar")) {
			gran_panel = vehiculos_editar();
		}
		if (actual.equals("vehiculos_eliminar")) {
			gran_panel = vehiculos_eliminar();
		}
		this.add(gran_panel);
		this.revalidate();
		this.repaint();
	}

	public JPanel login() {
		JPanel login = new JPanel();
		login.setVisible(true);
		login.setSize(900, 550);
		login.setLocation(0, 0);
		login.setBackground(Color.decode("#2F0909"));
		login.setLayout(null);

		JLabel ingresar_Nombre = new JLabel("Nombre del cliente: ", JLabel.CENTER);
		ingresar_Nombre.setSize(200, 30);
		ingresar_Nombre.setLocation(100, 120);
		ingresar_Nombre.setFont(new Font("Arial", Font.PLAIN, 23));
		ingresar_Nombre.setForeground(Color.white);
		login.add(ingresar_Nombre);

		JTextField in_ingresar_Nombre = new JTextField();
		in_ingresar_Nombre.setSize(300, 30);
		in_ingresar_Nombre.setLocation(100, 170);
		in_ingresar_Nombre.setFont(new Font("Arial", Font.PLAIN, 23));
		login.add(in_ingresar_Nombre);

		JLabel ingresar_Contrasena = new JLabel("Contraseña:", JLabel.CENTER);
		ingresar_Contrasena.setSize(200, 30);
		ingresar_Contrasena.setLocation(60, 250);
		ingresar_Contrasena.setFont(new Font("Arial", Font.PLAIN, 23));
		ingresar_Contrasena.setForeground(Color.white);
		login.add(ingresar_Contrasena);

		JPasswordField in_ingresar_Contrasena = new JPasswordField();
		in_ingresar_Contrasena.setSize(300, 30);
		in_ingresar_Contrasena.setLocation(100, 300);
		in_ingresar_Contrasena.setFont(new Font("Arial", Font.PLAIN, 23));
		login.add(in_ingresar_Contrasena);

		ImageIcon foto = new ImageIcon("logo_Login.jpg");
		JLabel icono = new JLabel();
		icono.setSize(350, 250);
		icono.setLocation(500, 120);
		icono.setIcon(new ImageIcon(
				foto.getImage().getScaledInstance(icono.getWidth(), icono.getHeight(), Image.SCALE_SMOOTH)));
		login.add(icono);

		JButton btn_ingresar = new JButton("Iniciar Sesión");
		btn_ingresar.setSize(200, 40);
		btn_ingresar.setLocation(150, 450);
		btn_ingresar.setFont(new Font("Arial", Font.BOLD, 20));
		login.add(btn_ingresar);

		btn_ingresar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String contrasena_Formato_String = new String(in_ingresar_Contrasena.getPassword());
				if (in_ingresar_Nombre.getText().isEmpty() == false && contrasena_Formato_String.isEmpty() == false) {
					if (validarDigitos(in_ingresar_Nombre)) {
						if (conexion.validar_Inicio_Sesion(in_ingresar_Nombre, in_ingresar_Contrasena)) {
							actual = "dash";
							route();
						}
					} else {
						JOptionPane.showMessageDialog(null, "Error. No se permiten valores numericos en el nombre.");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Error. No pueden haber campos vacios.");
				}
			}
		});

		return login;
	}

	public JPanel dash() {
		JPanel dash = new JPanel();
		dash.setVisible(true);
		dash.setSize(900, 550);
		dash.setLocation(0, 0);
		dash.setBackground(Color.decode("#D9D9D9"));
		dash.setLayout(null);

		JPanel panel_opciones = panel_Opciones();
		dash.add(panel_opciones);

		return dash;
	}

	public JPanel vehiculos() {
		JPanel vehiculos = new JPanel();
		vehiculos.setVisible(true);
		vehiculos.setSize(900, 550);
		vehiculos.setLocation(0, 0);
		vehiculos.setBackground(Color.decode("#D9D9D9"));
		vehiculos.setLayout(null);

		JPanel panel_opciones = panel_Opciones();
		vehiculos.add(panel_opciones);

		JLabel titulo_Panel_Vehiculos = new JLabel("Vehículos", JLabel.CENTER);
		titulo_Panel_Vehiculos.setSize(150, 30);
		titulo_Panel_Vehiculos.setLocation(475, 30);
		titulo_Panel_Vehiculos.setFont(new Font("Arial", Font.BOLD, 23));
		vehiculos.add(titulo_Panel_Vehiculos);

		ImageIcon foto = new ImageIcon("vehiculo_icono.png");
		JLabel icono = new JLabel();
		icono.setSize(85, 85);
		icono.setLocation(510, 60);
		icono.setIcon(new ImageIcon(
				foto.getImage().getScaledInstance(icono.getWidth(), icono.getHeight(), Image.SCALE_SMOOTH)));
		vehiculos.add(icono);

		JButton btn_consultar = new JButton("Consultar");
		btn_consultar.setSize(250, 30);
		btn_consultar.setLocation(295, 260);
		btn_consultar.setBackground(Color.decode("#2F0909"));
		btn_consultar.setFont(new Font("Arial", Font.BOLD, 20));
		btn_consultar.setForeground(Color.white);
		vehiculos.add(btn_consultar);

		ImageIcon foto_consultar = new ImageIcon("consultar_icono.png");
		JLabel icono_consultar = new JLabel();
		icono_consultar.setSize(85, 85);
		icono_consultar.setLocation(375, 170);
		icono_consultar.setIcon(new ImageIcon(foto_consultar.getImage().getScaledInstance(icono_consultar.getWidth(),
				icono_consultar.getHeight(), Image.SCALE_SMOOTH)));
		vehiculos.add(icono_consultar);

		JButton btn_añadir = new JButton("Añadir");
		btn_añadir.setSize(250, 30);
		btn_añadir.setLocation(575, 260);
		btn_añadir.setBackground(Color.decode("#2F0909"));
		btn_añadir.setFont(new Font("Arial", Font.BOLD, 20));
		btn_añadir.setForeground(Color.white);
		vehiculos.add(btn_añadir);

		ImageIcon foto_añadir = new ImageIcon("añadir_icono.png");
		JLabel icono_añadir = new JLabel();
		icono_añadir.setSize(85, 85);
		icono_añadir.setLocation(655, 170);
		icono_añadir.setIcon(new ImageIcon(foto_añadir.getImage().getScaledInstance(icono_añadir.getWidth(),
				icono_añadir.getHeight(), Image.SCALE_SMOOTH)));
		vehiculos.add(icono_añadir);

		JButton btn_editar = new JButton("Editar");
		btn_editar.setSize(250, 30);
		btn_editar.setLocation(295, 440);
		btn_editar.setBackground(Color.decode("#2F0909"));
		btn_editar.setFont(new Font("Arial", Font.BOLD, 20));
		btn_editar.setForeground(Color.white);
		vehiculos.add(btn_editar);

		ImageIcon foto_editar = new ImageIcon("editar_icono.png");
		JLabel icono_editar = new JLabel();
		icono_editar.setSize(85, 85);
		icono_editar.setLocation(385, 340);
		icono_editar.setIcon(new ImageIcon(foto_editar.getImage().getScaledInstance(icono_editar.getWidth(),
				icono_editar.getHeight(), Image.SCALE_SMOOTH)));
		vehiculos.add(icono_editar);

		JButton btn_Eliminar = new JButton("Eliminar");
		btn_Eliminar.setSize(250, 30);
		btn_Eliminar.setLocation(575, 440);
		btn_Eliminar.setBackground(Color.decode("#2F0909"));
		btn_Eliminar.setFont(new Font("Arial", Font.BOLD, 20));
		btn_Eliminar.setForeground(Color.white);
		vehiculos.add(btn_Eliminar);

		ImageIcon foto_eliminar = new ImageIcon("eliminar_icono.png");
		JLabel icono_eliminar = new JLabel();
		icono_eliminar.setSize(85, 85);
		icono_eliminar.setLocation(655, 340);
		icono_eliminar.setIcon(new ImageIcon(foto_eliminar.getImage().getScaledInstance(icono_eliminar.getWidth(),
				icono_eliminar.getHeight(), Image.SCALE_SMOOTH)));
		vehiculos.add(icono_eliminar);

		btn_consultar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				actual = "vehiculos_consultar";
				route();
			}
		});

		btn_añadir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cmb.removeAllItems();
				conexion.llenar_CMB_Marcas(cmb);
				actual = "vehiculos_añadir";
				route();
			}
		});

		btn_editar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cmb.removeAllItems();
				conexion.llenar_CMB_Vehiculos(cmb);
				actual = "vehiculos_editar";
				route();
			}
		});

		btn_Eliminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cmb.removeAllItems();
				conexion.llenar_CMB_Vehiculos(cmb);
				actual = "vehiculos_eliminar";
				route();
			}
		});

		return vehiculos;
	}

	public JPanel vehiculos_Consultar() {
		JPanel vehiculos_Consultar = new JPanel();
		vehiculos_Consultar.setVisible(true);
		vehiculos_Consultar.setSize(900, 550);
		vehiculos_Consultar.setLocation(0, 0);
		vehiculos_Consultar.setBackground(Color.decode("#D9D9D9"));
		vehiculos_Consultar.setLayout(null);

		JPanel panel_opciones = panel_Opciones();
		vehiculos_Consultar.add(panel_opciones);

		JLabel titulo_Panel_Vehiculos = new JLabel("Vehículos", JLabel.CENTER);
		titulo_Panel_Vehiculos.setSize(150, 30);
		titulo_Panel_Vehiculos.setLocation(325, 30);
		titulo_Panel_Vehiculos.setFont(new Font("Arial", Font.BOLD, 23));
		vehiculos_Consultar.add(titulo_Panel_Vehiculos);

		JLabel titulo_Panel_Vehiculos_Consultar = new JLabel("Consultar");
		titulo_Panel_Vehiculos_Consultar.setSize(180, 30);
		titulo_Panel_Vehiculos_Consultar.setLocation(655, 30);
		titulo_Panel_Vehiculos_Consultar.setFont(new Font("Arial", Font.BOLD, 23));
		vehiculos_Consultar.add(titulo_Panel_Vehiculos_Consultar);

		ImageIcon foto_consultar = new ImageIcon("consultar_icono.png");
		JLabel icono_consultar = new JLabel();
		icono_consultar.setSize(85, 85);
		icono_consultar.setLocation(520, 10);
		icono_consultar.setIcon(new ImageIcon(foto_consultar.getImage().getScaledInstance(icono_consultar.getWidth(),
				icono_consultar.getHeight(), Image.SCALE_SMOOTH)));
		vehiculos_Consultar.add(icono_consultar);

		JLabel titulo_tabla_rentas = new JLabel("Historial de rentas");
		titulo_tabla_rentas.setSize(130, 30);
		titulo_tabla_rentas.setLocation(495, 100);
		titulo_tabla_rentas.setFont(new Font("Arial", Font.BOLD, 12));
		vehiculos_Consultar.add(titulo_tabla_rentas);

		String nombresColumna[] = { "Modelo", "Cliente", "F. Inicial", "F.Final", "Costo total" };
		JTable tabla = new JTable();
		DefaultTableModel tablaModel = new DefaultTableModel();
		tablaModel.setColumnIdentifiers(nombresColumna);
		tabla.setModel(tablaModel);
		conexion.consultar_Rentas(tablaModel);
		JScrollPane sp = new JScrollPane(tabla);
		sp.setSize(530, 100);
		sp.setLocation(285, 140);
		vehiculos_Consultar.add(sp);

		JLabel titulo_tabla_tarifas = new JLabel("Tarifas");
		titulo_tabla_tarifas.setSize(130, 30);
		titulo_tabla_tarifas.setLocation(530, 260);
		titulo_tabla_tarifas.setFont(new Font("Arial", Font.BOLD, 12));
		vehiculos_Consultar.add(titulo_tabla_tarifas);

		String nombresColumna2[] = { "Marca", "Modelo", "Tarifa" };
		JTable tabla2 = new JTable();
		DefaultTableModel tablaModel2 = new DefaultTableModel();
		tablaModel2.setColumnIdentifiers(nombresColumna2);
		tabla2.setModel(tablaModel2);
		conexion.consultar_Vehiculos(tablaModel2);
		JScrollPane sp2 = new JScrollPane(tabla2);
		sp2.setSize(530, 100);
		sp2.setLocation(285, 290);
		vehiculos_Consultar.add(sp2);

		JButton btn_Volver = new JButton("Volver");
		btn_Volver.setSize(250, 30);
		btn_Volver.setLocation(425, 455);
		btn_Volver.setBackground(Color.decode("#2F0909"));
		btn_Volver.setFont(new Font("Arial", Font.BOLD, 20));
		btn_Volver.setForeground(Color.white);
		vehiculos_Consultar.add(btn_Volver);

		btn_Volver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				actual = "vehiculos";
				route();
			}
		});

		return vehiculos_Consultar;
	}

	public JPanel vehiculos_añadir() {
		JPanel vehiculos_añadir = new JPanel();
		vehiculos_añadir.setVisible(true);
		vehiculos_añadir.setSize(900, 550);
		vehiculos_añadir.setLocation(0, 0);
		vehiculos_añadir.setBackground(Color.decode("#D9D9D9"));
		vehiculos_añadir.setLayout(null);

		JPanel panel_opciones = panel_Opciones();
		vehiculos_añadir.add(panel_opciones);

		JLabel titulo_Panel_Vehiculos = new JLabel("Vehiculos", JLabel.CENTER);
		titulo_Panel_Vehiculos.setSize(150, 30);
		titulo_Panel_Vehiculos.setLocation(325, 30);
		titulo_Panel_Vehiculos.setFont(new Font("Arial", Font.BOLD, 23));
		vehiculos_añadir.add(titulo_Panel_Vehiculos);

		JLabel titulo_Panel_Vehiculos_Añadir = new JLabel("Añadir");
		titulo_Panel_Vehiculos_Añadir.setSize(180, 30);
		titulo_Panel_Vehiculos_Añadir.setLocation(655, 30);
		titulo_Panel_Vehiculos_Añadir.setFont(new Font("Arial", Font.BOLD, 23));
		vehiculos_añadir.add(titulo_Panel_Vehiculos_Añadir);

		ImageIcon foto_añadir = new ImageIcon("añadir_icono.png");
		JLabel icono_añadir = new JLabel();
		icono_añadir.setSize(85, 85);
		icono_añadir.setLocation(520, 10);
		icono_añadir.setIcon(new ImageIcon(foto_añadir.getImage().getScaledInstance(icono_añadir.getWidth(),
				icono_añadir.getHeight(), Image.SCALE_SMOOTH)));
		vehiculos_añadir.add(icono_añadir);

		cmb.setSize(440, 30);
		cmb.setLocation(325, 110);
		vehiculos_añadir.add(cmb);

		JLabel marca_vehículo = new JLabel("Selecciona la marca de el vehículo");
		marca_vehículo.setSize(300, 30);
		marca_vehículo.setLocation(425, 140);
		marca_vehículo.setFont(new Font("Arial", Font.BOLD, 14));
		vehiculos_añadir.add(marca_vehículo);

		JLabel nombre_vehículo = new JLabel("Ingresa el nombre de el vehículo");
		nombre_vehículo.setSize(300, 30);
		nombre_vehículo.setLocation(265, 180);
		nombre_vehículo.setFont(new Font("Arial", Font.BOLD, 16));
		vehiculos_añadir.add(nombre_vehículo);

		JTextField in_nombre_vehiculo = new JTextField();
		in_nombre_vehiculo.setSize(280, 30);
		in_nombre_vehiculo.setLocation(250, 210);
		in_nombre_vehiculo.setFont(new Font("Arial", Font.PLAIN, 16));
		vehiculos_añadir.add(in_nombre_vehiculo);

		/*
		 * JLabel marca_vehiculo = new JLabel("Ingresa la marca de el vehículo");
		 * marca_vehiculo.setSize(300, 30); marca_vehiculo.setLocation(605, 150);
		 * marca_vehiculo.setFont(new Font("Arial", Font.BOLD, 16));
		 * vehiculos_añadir.add(marca_vehiculo);
		 * 
		 * JTextField in_marca_vehiculo = new JTextField();
		 * in_marca_vehiculo.setSize(280, 30); in_marca_vehiculo.setLocation(585, 180);
		 * in_marca_vehiculo.setFont(new Font("Arial", Font.PLAIN, 16));
		 * vehiculos_añadir.add(in_marca_vehiculo);
		 */

		JLabel modelo_vehiculo = new JLabel("Ingresa el modelo de el vehículo");
		modelo_vehiculo.setSize(300, 30);
		modelo_vehiculo.setLocation(265, 270);
		modelo_vehiculo.setFont(new Font("Arial", Font.BOLD, 16));
		vehiculos_añadir.add(modelo_vehiculo);

		JTextField in_modelo_vehiculo = new JTextField();
		in_modelo_vehiculo.setSize(280, 30);
		in_modelo_vehiculo.setLocation(250, 300);
		in_modelo_vehiculo.setFont(new Font("Arial", Font.PLAIN, 16));
		vehiculos_añadir.add(in_modelo_vehiculo);

		JLabel transmision_vehiculo = new JLabel("Ingresa la transmisión de el vehículo");
		transmision_vehiculo.setSize(300, 30);
		transmision_vehiculo.setLocation(590, 180);
		transmision_vehiculo.setFont(new Font("Arial", Font.BOLD, 16));
		vehiculos_añadir.add(transmision_vehiculo);

		JTextField in_transmision_vehiculo = new JTextField();
		in_transmision_vehiculo.setSize(280, 30);
		in_transmision_vehiculo.setLocation(585, 210);
		in_transmision_vehiculo.setFont(new Font("Arial", Font.PLAIN, 16));
		vehiculos_añadir.add(in_transmision_vehiculo);

		JLabel tarifa_vehiculo = new JLabel("Ingresa la tarifa de el vehículo");
		tarifa_vehiculo.setSize(300, 30);
		tarifa_vehiculo.setLocation(275, 360);
		tarifa_vehiculo.setFont(new Font("Arial", Font.BOLD, 16));
		vehiculos_añadir.add(tarifa_vehiculo);

		JTextField in_tarifa_vehiculo = new JTextField();
		in_tarifa_vehiculo.setSize(280, 30);
		in_tarifa_vehiculo.setLocation(250, 390);
		in_tarifa_vehiculo.setFont(new Font("Arial", Font.PLAIN, 16));
		vehiculos_añadir.add(in_tarifa_vehiculo);

		JLabel año_vehiculo = new JLabel("Ingresa el año de el vehículo");
		año_vehiculo.setSize(300, 30);
		año_vehiculo.setLocation(615, 270);
		año_vehiculo.setFont(new Font("Arial", Font.BOLD, 16));
		vehiculos_añadir.add(año_vehiculo);

		JTextField in_año_vehiculo = new JTextField();
		in_año_vehiculo.setSize(280, 30);
		in_año_vehiculo.setLocation(585, 300);
		in_año_vehiculo.setFont(new Font("Arial", Font.PLAIN, 16));
		vehiculos_añadir.add(in_año_vehiculo);

		JButton btn_Volver = new JButton("Volver");
		btn_Volver.setSize(250, 30);
		btn_Volver.setLocation(275, 455);
		btn_Volver.setBackground(Color.decode("#2F0909"));
		btn_Volver.setFont(new Font("Arial", Font.BOLD, 20));
		btn_Volver.setForeground(Color.white);
		vehiculos_añadir.add(btn_Volver);

		JButton btn_Crear = new JButton("Añadir");
		btn_Crear.setSize(250, 30);
		btn_Crear.setLocation(575, 455);
		btn_Crear.setBackground(Color.decode("#2A5729"));
		btn_Crear.setFont(new Font("Arial", Font.BOLD, 20));
		btn_Crear.setForeground(Color.white);
		vehiculos_añadir.add(btn_Crear);

		btn_Volver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				actual = "vehiculos";
				route();
			}
		});

		btn_Crear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (!in_nombre_vehiculo.getText().isEmpty() && !in_modelo_vehiculo.getText().isEmpty()
						&& !in_transmision_vehiculo.getText().isEmpty() && !in_año_vehiculo.getText().isEmpty()
						&& !in_tarifa_vehiculo.getText().isEmpty()) {
					if (validarDigitos(in_nombre_vehiculo) && validarDigitos(in_transmision_vehiculo)) {
						if (validarNumeros(in_año_vehiculo) && validarNumeros(in_tarifa_vehiculo)) {
							if (!conexion.añadir_Vehiculo(in_nombre_vehiculo, in_modelo_vehiculo,
									in_transmision_vehiculo, in_tarifa_vehiculo, in_año_vehiculo, cmb)) {
								JOptionPane.showMessageDialog(null, "Vehiculo añadido correctamente");
								in_año_vehiculo.setText("");
								in_modelo_vehiculo.setText("");
								in_nombre_vehiculo.setText("");
								in_tarifa_vehiculo.setText("");
								in_transmision_vehiculo.setText("");
							} else {
								JOptionPane.showMessageDialog(null, "Error. Este vehículo ya se encuentra añadido");
							}
						} else {
							JOptionPane.showMessageDialog(null,
									"Error. Solo se permiten valores numericos para el año y tarifa de el vehículo");
						}
					} else {
						JOptionPane.showMessageDialog(null,
								"Error. No se permiten valores numericos para el nombre o transmisión de el vehículo");
					}

				} else {
					JOptionPane.showMessageDialog(null, "Error. Todos los campos deben estar llenados");
				}
			}
		});

		return vehiculos_añadir;
	}

	public JPanel vehiculos_editar() {
		JPanel vehiculos_editar = new JPanel();
		vehiculos_editar.setVisible(true);
		vehiculos_editar.setSize(900, 550);
		vehiculos_editar.setLocation(0, 0);
		vehiculos_editar.setBackground(Color.decode("#D9D9D9"));
		vehiculos_editar.setLayout(null);

		JPanel panel_opciones = panel_Opciones();
		vehiculos_editar.add(panel_opciones);

		JLabel titulo_Panel_Vehiculos = new JLabel("Vehículos", JLabel.CENTER);
		titulo_Panel_Vehiculos.setSize(150, 30);
		titulo_Panel_Vehiculos.setLocation(325, 30);
		titulo_Panel_Vehiculos.setFont(new Font("Arial", Font.BOLD, 23));
		vehiculos_editar.add(titulo_Panel_Vehiculos);

		JLabel titulo_Panel_Vehiculos_Editar = new JLabel("Editar");
		titulo_Panel_Vehiculos_Editar.setSize(180, 30);
		titulo_Panel_Vehiculos_Editar.setLocation(655, 30);
		titulo_Panel_Vehiculos_Editar.setFont(new Font("Arial", Font.BOLD, 23));
		vehiculos_editar.add(titulo_Panel_Vehiculos_Editar);

		ImageIcon foto_editar = new ImageIcon("editar_icono.png");
		JLabel icono_editar = new JLabel();
		icono_editar.setSize(85, 85);
		icono_editar.setLocation(520, 10);
		icono_editar.setIcon(new ImageIcon(foto_editar.getImage().getScaledInstance(icono_editar.getWidth(),
				icono_editar.getHeight(), Image.SCALE_SMOOTH)));
		vehiculos_editar.add(icono_editar);

		cmb.setSize(440, 30);
		cmb.setLocation(325, 120);
		vehiculos_editar.add(cmb);

		JLabel instruccion = new JLabel("Selecciona el vehículo que deseas editar");
		instruccion.setSize(300, 30);
		instruccion.setLocation(425, 150);
		instruccion.setFont(new Font("Arial", Font.PLAIN, 12));
		vehiculos_editar.add(instruccion);

		JLabel nombre_vehículo = new JLabel("Ingresa el nombre de el vehículo");
		nombre_vehículo.setSize(300, 30);
		nombre_vehículo.setLocation(265, 180);
		nombre_vehículo.setFont(new Font("Arial", Font.BOLD, 16));
		vehiculos_editar.add(nombre_vehículo);

		JTextField in_nombre_vehiculo = new JTextField();
		in_nombre_vehiculo.setSize(280, 30);
		in_nombre_vehiculo.setLocation(250, 210);
		in_nombre_vehiculo.setFont(new Font("Arial", Font.PLAIN, 16));
		vehiculos_editar.add(in_nombre_vehiculo);

		JLabel marca_vehiculo = new JLabel("Ingresa la marca de el vehículo");
		marca_vehiculo.setSize(300, 30);
		marca_vehiculo.setLocation(605, 180);
		marca_vehiculo.setFont(new Font("Arial", Font.BOLD, 16));
		vehiculos_editar.add(marca_vehiculo);

		JTextField in_marca_vehiculo = new JTextField();
		in_marca_vehiculo.setSize(280, 30);
		in_marca_vehiculo.setLocation(585, 210);
		in_marca_vehiculo.setFont(new Font("Arial", Font.PLAIN, 16));
		vehiculos_editar.add(in_marca_vehiculo);

		JLabel modelo_vehiculo = new JLabel("Ingresa el modelo de el vehículo");
		modelo_vehiculo.setSize(300, 30);
		modelo_vehiculo.setLocation(265, 270);
		modelo_vehiculo.setFont(new Font("Arial", Font.BOLD, 16));
		vehiculos_editar.add(modelo_vehiculo);

		JTextField in_modelo_vehiculo = new JTextField();
		in_modelo_vehiculo.setSize(280, 30);
		in_modelo_vehiculo.setLocation(250, 300);
		in_modelo_vehiculo.setFont(new Font("Arial", Font.PLAIN, 16));
		in_modelo_vehiculo.setEditable(false);
		vehiculos_editar.add(in_modelo_vehiculo);

		JLabel transmision_vehiculo = new JLabel("Ingresa la transmisión de el vehículo");
		transmision_vehiculo.setSize(300, 30);
		transmision_vehiculo.setLocation(590, 270);
		transmision_vehiculo.setFont(new Font("Arial", Font.BOLD, 16));
		vehiculos_editar.add(transmision_vehiculo);

		JTextField in_transmision_vehiculo = new JTextField();
		in_transmision_vehiculo.setSize(280, 30);
		in_transmision_vehiculo.setLocation(585, 300);
		in_transmision_vehiculo.setFont(new Font("Arial", Font.PLAIN, 16));
		vehiculos_editar.add(in_transmision_vehiculo);

		JLabel tarifa_vehiculo = new JLabel("Ingresa la tarifa de el vehículo");
		tarifa_vehiculo.setSize(300, 30);
		tarifa_vehiculo.setLocation(275, 360);
		tarifa_vehiculo.setFont(new Font("Arial", Font.BOLD, 16));
		vehiculos_editar.add(tarifa_vehiculo);

		JTextField in_tarifa_vehiculo = new JTextField();
		in_tarifa_vehiculo.setSize(280, 30);
		in_tarifa_vehiculo.setLocation(250, 390);
		in_tarifa_vehiculo.setFont(new Font("Arial", Font.PLAIN, 16));
		vehiculos_editar.add(in_tarifa_vehiculo);

		JLabel año_vehiculo = new JLabel("Ingresa el año de el vehículo");
		año_vehiculo.setSize(300, 30);
		año_vehiculo.setLocation(615, 360);
		año_vehiculo.setFont(new Font("Arial", Font.BOLD, 16));
		vehiculos_editar.add(año_vehiculo);

		JTextField in_año_vehiculo = new JTextField();
		in_año_vehiculo.setSize(280, 30);
		in_año_vehiculo.setLocation(585, 390);
		in_año_vehiculo.setFont(new Font("Arial", Font.PLAIN, 16));
		vehiculos_editar.add(in_año_vehiculo);

		JButton btn_Volver = new JButton("Volver");
		btn_Volver.setSize(250, 30);
		btn_Volver.setLocation(275, 455);
		btn_Volver.setBackground(Color.decode("#2F0909"));
		btn_Volver.setFont(new Font("Arial", Font.BOLD, 20));
		btn_Volver.setForeground(Color.white);
		vehiculos_editar.add(btn_Volver);

		JButton btn_Editar = new JButton("Editar");
		btn_Editar.setSize(250, 30);
		btn_Editar.setLocation(575, 455);
		btn_Editar.setBackground(Color.decode("#2A5729"));
		btn_Editar.setFont(new Font("Arial", Font.BOLD, 20));
		btn_Editar.setForeground(Color.white);
		vehiculos_editar.add(btn_Editar);

		btn_Volver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				actual = "vehiculos";
				route();
			}
		});

		btn_Editar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (!in_nombre_vehiculo.getText().isEmpty() && !in_marca_vehiculo.getText().isEmpty()
						&& !in_transmision_vehiculo.getText().isEmpty() && !in_tarifa_vehiculo.getText().isEmpty()
						&& !in_año_vehiculo.getText().isEmpty()) {
					if (validarDigitos(in_nombre_vehiculo) && validarDigitos(in_marca_vehiculo)
							&& validarDigitos(in_transmision_vehiculo)) {
						if (validarNumeros(in_año_vehiculo) && validarNumeros(in_tarifa_vehiculo)) {
							int confirmacion = JOptionPane.showConfirmDialog(null,
									"¿Está seguro de editar este vehículo?");
							if (confirmacion == 0) {
								if (conexion.editar_Vehiculo(in_modelo_vehiculo, in_nombre_vehiculo, in_marca_vehiculo,
										in_transmision_vehiculo, in_tarifa_vehiculo, in_año_vehiculo, cmb)) {
									JOptionPane.showMessageDialog(null, "Vehiculo editado correctamente");
									in_año_vehiculo.setText("");
									in_marca_vehiculo.setText("");
									in_modelo_vehiculo.setText("");
									in_nombre_vehiculo.setText("");
									in_tarifa_vehiculo.setText("");
									in_transmision_vehiculo.setText("");
								} else {
									JOptionPane.showMessageDialog(null, "Error. Esta marca no se encuentra registrada");
								}
							}
						} else {
							JOptionPane.showMessageDialog(null,
									"Error. Solo se permiten valores numericos para el año y tarifa de el vehículo");
						}
					} else {
						JOptionPane.showMessageDialog(null,
								"Error. No se permiten valores numericos para el nombre, marca y transmision de el vehículo");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Error. Todos los campos deben estar llenados");
				}
			}
		});
		return vehiculos_editar;

	}

	public JPanel vehiculos_eliminar() {
		JPanel vehiculos_eliminar = new JPanel();
		vehiculos_eliminar.setVisible(true);
		vehiculos_eliminar.setSize(900, 550);
		vehiculos_eliminar.setLocation(0, 0);
		vehiculos_eliminar.setBackground(Color.decode("#D9D9D9"));
		vehiculos_eliminar.setLayout(null);

		JPanel panel_opciones = panel_Opciones();
		vehiculos_eliminar.add(panel_opciones);

		JLabel titulo_Panel_Vehiculos = new JLabel("Vehículos", JLabel.CENTER);
		titulo_Panel_Vehiculos.setSize(150, 30);
		titulo_Panel_Vehiculos.setLocation(325, 30);
		titulo_Panel_Vehiculos.setFont(new Font("Arial", Font.BOLD, 23));
		vehiculos_eliminar.add(titulo_Panel_Vehiculos);

		JLabel titulo_Panel_Categorias_Eliminar = new JLabel("Elminar");
		titulo_Panel_Categorias_Eliminar.setSize(180, 30);
		titulo_Panel_Categorias_Eliminar.setLocation(655, 30);
		titulo_Panel_Categorias_Eliminar.setFont(new Font("Arial", Font.BOLD, 23));
		vehiculos_eliminar.add(titulo_Panel_Categorias_Eliminar);

		ImageIcon foto_editar = new ImageIcon("eliminar_icono.png");
		JLabel icono_editar = new JLabel();
		icono_editar.setSize(85, 85);
		icono_editar.setLocation(520, 10);
		icono_editar.setIcon(new ImageIcon(foto_editar.getImage().getScaledInstance(icono_editar.getWidth(),
				icono_editar.getHeight(), Image.SCALE_SMOOTH)));
		vehiculos_eliminar.add(icono_editar);

		cmb.setSize(440, 30);
		cmb.setLocation(325, 120);
		vehiculos_eliminar.add(cmb);

		JLabel instruccion = new JLabel("Selecciona el vehículo que deseas eliminar");
		instruccion.setSize(300, 30);
		instruccion.setLocation(425, 150);
		instruccion.setFont(new Font("Arial", Font.PLAIN, 12));
		vehiculos_eliminar.add(instruccion);

		JLabel nombre_vehículo = new JLabel("Ingresa el nombre de el vehículo");
		nombre_vehículo.setSize(300, 30);
		nombre_vehículo.setLocation(265, 180);
		nombre_vehículo.setFont(new Font("Arial", Font.BOLD, 16));
		vehiculos_eliminar.add(nombre_vehículo);

		JTextField in_nombre_vehiculo = new JTextField();
		in_nombre_vehiculo.setSize(280, 30);
		in_nombre_vehiculo.setLocation(250, 210);
		in_nombre_vehiculo.setFont(new Font("Arial", Font.PLAIN, 16));
		in_nombre_vehiculo.setEditable(false);
		vehiculos_eliminar.add(in_nombre_vehiculo);

		JLabel marca_vehiculo = new JLabel("Ingresa la marca de el vehículo");
		marca_vehiculo.setSize(300, 30);
		marca_vehiculo.setLocation(605, 180);
		marca_vehiculo.setFont(new Font("Arial", Font.BOLD, 16));
		vehiculos_eliminar.add(marca_vehiculo);

		JTextField in_marca_vehiculo = new JTextField();
		in_marca_vehiculo.setSize(280, 30);
		in_marca_vehiculo.setLocation(585, 210);
		in_marca_vehiculo.setFont(new Font("Arial", Font.PLAIN, 16));
		in_marca_vehiculo.setEditable(false);
		vehiculos_eliminar.add(in_marca_vehiculo);

		JLabel modelo_vehiculo = new JLabel("Ingresa el modelo de el vehículo");
		modelo_vehiculo.setSize(300, 30);
		modelo_vehiculo.setLocation(265, 270);
		modelo_vehiculo.setFont(new Font("Arial", Font.BOLD, 16));
		vehiculos_eliminar.add(modelo_vehiculo);

		JTextField in_modelo_vehiculo = new JTextField();
		in_modelo_vehiculo.setSize(280, 30);
		in_modelo_vehiculo.setLocation(250, 300);
		in_modelo_vehiculo.setFont(new Font("Arial", Font.PLAIN, 16));
		in_modelo_vehiculo.setEditable(false);
		vehiculos_eliminar.add(in_modelo_vehiculo);

		JLabel transmision_vehiculo = new JLabel("Ingresa la transmisión de el vehículo");
		transmision_vehiculo.setSize(300, 30);
		transmision_vehiculo.setLocation(590, 270);
		transmision_vehiculo.setFont(new Font("Arial", Font.BOLD, 16));
		vehiculos_eliminar.add(transmision_vehiculo);

		JTextField in_transmision_vehiculo = new JTextField();
		in_transmision_vehiculo.setSize(280, 30);
		in_transmision_vehiculo.setLocation(585, 300);
		in_transmision_vehiculo.setFont(new Font("Arial", Font.PLAIN, 16));
		in_transmision_vehiculo.setEditable(false);
		vehiculos_eliminar.add(in_transmision_vehiculo);

		JLabel tarifa_vehiculo = new JLabel("Ingresa la tarifa de el vehículo");
		tarifa_vehiculo.setSize(300, 30);
		tarifa_vehiculo.setLocation(275, 360);
		tarifa_vehiculo.setFont(new Font("Arial", Font.BOLD, 16));
		vehiculos_eliminar.add(tarifa_vehiculo);

		JTextField in_tarifa_vehiculo = new JTextField();
		in_tarifa_vehiculo.setSize(280, 30);
		in_tarifa_vehiculo.setLocation(250, 390);
		in_tarifa_vehiculo.setFont(new Font("Arial", Font.PLAIN, 16));
		in_tarifa_vehiculo.setEditable(false);
		vehiculos_eliminar.add(in_tarifa_vehiculo);

		JLabel año_vehiculo = new JLabel("Ingresa el año de el vehículo");
		año_vehiculo.setSize(300, 30);
		año_vehiculo.setLocation(615, 360);
		año_vehiculo.setFont(new Font("Arial", Font.BOLD, 16));
		vehiculos_eliminar.add(año_vehiculo);

		JTextField in_año_vehiculo = new JTextField();
		in_año_vehiculo.setSize(280, 30);
		in_año_vehiculo.setLocation(585, 390);
		in_año_vehiculo.setFont(new Font("Arial", Font.PLAIN, 16));
		in_año_vehiculo.setEditable(false);
		vehiculos_eliminar.add(in_año_vehiculo);

		JButton btn_Volver = new JButton("Volver");
		btn_Volver.setSize(250, 30);
		btn_Volver.setLocation(275, 455);
		btn_Volver.setBackground(Color.decode("#2A5729"));
		btn_Volver.setFont(new Font("Arial", Font.BOLD, 20));
		btn_Volver.setForeground(Color.white);
		vehiculos_eliminar.add(btn_Volver);

		JButton btn_Eliminar = new JButton("Eliminar");
		btn_Eliminar.setSize(250, 30);
		btn_Eliminar.setLocation(575, 455);
		btn_Eliminar.setBackground(Color.decode("#2F0909"));
		btn_Eliminar.setFont(new Font("Arial", Font.BOLD, 20));
		btn_Eliminar.setForeground(Color.white);
		vehiculos_eliminar.add(btn_Eliminar);

		btn_Volver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				actual = "vehiculos";
				route();
			}
		});

		btn_Eliminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int confirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar este vehículo?");
				if (confirmacion == 0) {
					conexion.eliminar_Vehiculo(cmb);
					JOptionPane.showMessageDialog(null, "Vehículo eliminado correctamente");
					cmb.setSelectedIndex(0);
				}
			}
		});
		return vehiculos_eliminar;
	}

	public JPanel clientes() {
		JPanel clientes = new JPanel();
		clientes.setVisible(true);
		clientes.setSize(900, 550);
		clientes.setLocation(0, 0);
		clientes.setBackground(Color.decode("#D9D9D9"));
		clientes.setLayout(null);

		JPanel panel_opciones = panel_Opciones();
		clientes.add(panel_opciones);

		JLabel titulo_Panel_Clientes = new JLabel("Clientes", JLabel.CENTER);
		titulo_Panel_Clientes.setSize(150, 30);
		titulo_Panel_Clientes.setLocation(305, 30);
		titulo_Panel_Clientes.setFont(new Font("Arial", Font.ITALIC, 23));
		clientes.add(titulo_Panel_Clientes);

		JButton btn_volver_Clientes = new JButton("Volver");
		btn_volver_Clientes.setSize(150, 30);
		btn_volver_Clientes.setLocation(295, 450);
		btn_volver_Clientes.setFont(new Font("Arial", Font.BOLD, 20));
		clientes.add(btn_volver_Clientes);
		btn_volver_Clientes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				actual = "dash";
				route();
			}

		});

		return clientes;
	}

	public JPanel rentas() {
		JPanel rentas = new JPanel();
		rentas.setVisible(true);
		rentas.setSize(900, 550);
		rentas.setLocation(0, 0);
		rentas.setBackground(Color.decode("#D9D9D9"));
		rentas.setLayout(null);

		JPanel panel_opciones = panel_Opciones();
		rentas.add(panel_opciones);

		JLabel titulo_Panel_Rentas = new JLabel("Rentas", JLabel.CENTER);
		titulo_Panel_Rentas.setSize(150, 30);
		titulo_Panel_Rentas.setLocation(305, 30);
		titulo_Panel_Rentas.setFont(new Font("Arial", Font.ITALIC, 23));
		rentas.add(titulo_Panel_Rentas);

		JButton btn_volver_Rentas = new JButton("Volver");
		btn_volver_Rentas.setSize(150, 30);
		btn_volver_Rentas.setLocation(295, 450);
		btn_volver_Rentas.setFont(new Font("Arial", Font.BOLD, 20));
		rentas.add(btn_volver_Rentas);
		btn_volver_Rentas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				actual = "dash";
				route();
			}

		});

		return rentas;
	}

	public JPanel categorias() {
		JPanel categorias = new JPanel();
		categorias.setVisible(true);
		categorias.setSize(900, 550);
		categorias.setLocation(0, 0);
		categorias.setBackground(Color.decode("#D9D9D9"));
		categorias.setLayout(null);

		JPanel panel_opciones = panel_Opciones();
		categorias.add(panel_opciones);

		JLabel titulo_Panel_Categorias = new JLabel("Categorias", JLabel.CENTER);
		titulo_Panel_Categorias.setSize(150, 30);
		titulo_Panel_Categorias.setLocation(475, 30);
		titulo_Panel_Categorias.setFont(new Font("Arial", Font.BOLD, 23));
		categorias.add(titulo_Panel_Categorias);

		ImageIcon foto = new ImageIcon("categoria_icono.png");
		JLabel icono = new JLabel();
		icono.setSize(85, 85);
		icono.setLocation(510, 60);
		icono.setIcon(new ImageIcon(
				foto.getImage().getScaledInstance(icono.getWidth(), icono.getHeight(), Image.SCALE_SMOOTH)));
		categorias.add(icono);

		JButton btn_consultar = new JButton("Consultar");
		btn_consultar.setSize(250, 30);
		btn_consultar.setLocation(295, 260);
		btn_consultar.setBackground(Color.decode("#2F0909"));
		btn_consultar.setFont(new Font("Arial", Font.BOLD, 20));
		btn_consultar.setForeground(Color.white);
		categorias.add(btn_consultar);

		ImageIcon foto_consultar = new ImageIcon("consultar_icono.png");
		JLabel icono_consultar = new JLabel();
		icono_consultar.setSize(85, 85);
		icono_consultar.setLocation(375, 170);
		icono_consultar.setIcon(new ImageIcon(foto_consultar.getImage().getScaledInstance(icono_consultar.getWidth(),
				icono_consultar.getHeight(), Image.SCALE_SMOOTH)));
		categorias.add(icono_consultar);

		JButton btn_añadir = new JButton("Añadir");
		btn_añadir.setSize(250, 30);
		btn_añadir.setLocation(575, 260);
		btn_añadir.setBackground(Color.decode("#2F0909"));
		btn_añadir.setFont(new Font("Arial", Font.BOLD, 20));
		btn_añadir.setForeground(Color.white);
		categorias.add(btn_añadir);

		ImageIcon foto_añadir = new ImageIcon("añadir_icono.png");
		JLabel icono_añadir = new JLabel();
		icono_añadir.setSize(85, 85);
		icono_añadir.setLocation(655, 170);
		icono_añadir.setIcon(new ImageIcon(foto_añadir.getImage().getScaledInstance(icono_añadir.getWidth(),
				icono_añadir.getHeight(), Image.SCALE_SMOOTH)));
		categorias.add(icono_añadir);

		JButton btn_editar = new JButton("Editar");
		btn_editar.setSize(250, 30);
		btn_editar.setLocation(295, 440);
		btn_editar.setBackground(Color.decode("#2F0909"));
		btn_editar.setFont(new Font("Arial", Font.BOLD, 20));
		btn_editar.setForeground(Color.white);
		categorias.add(btn_editar);

		ImageIcon foto_editar = new ImageIcon("editar_icono.png");
		JLabel icono_editar = new JLabel();
		icono_editar.setSize(85, 85);
		icono_editar.setLocation(385, 340);
		icono_editar.setIcon(new ImageIcon(foto_editar.getImage().getScaledInstance(icono_editar.getWidth(),
				icono_editar.getHeight(), Image.SCALE_SMOOTH)));
		categorias.add(icono_editar);

		JButton btn_Eliminar = new JButton("Eliminar");
		btn_Eliminar.setSize(250, 30);
		btn_Eliminar.setLocation(575, 440);
		btn_Eliminar.setBackground(Color.decode("#2F0909"));
		btn_Eliminar.setFont(new Font("Arial", Font.BOLD, 20));
		btn_Eliminar.setForeground(Color.white);
		categorias.add(btn_Eliminar);

		ImageIcon foto_eliminar = new ImageIcon("eliminar_icono.png");
		JLabel icono_eliminar = new JLabel();
		icono_eliminar.setSize(85, 85);
		icono_eliminar.setLocation(655, 340);
		icono_eliminar.setIcon(new ImageIcon(foto_eliminar.getImage().getScaledInstance(icono_eliminar.getWidth(),
				icono_eliminar.getHeight(), Image.SCALE_SMOOTH)));
		categorias.add(icono_eliminar);

		btn_consultar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				actual = "categorias_consultar";
				route();
			}
		});

		btn_añadir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				actual = "categorias_añadir";
				route();
			}
		});

		btn_editar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cmb.removeAllItems();
				conexion.llenar_CMB_Categorias(cmb);
				actual = "categorias_editar";
				route();
			}
		});

		btn_Eliminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cmb.removeAllItems();
				conexion.llenar_CMB_Categorias(cmb);
				actual = "categorias_eliminar";
				route();
			}
		});

		return categorias;
	}

	public JPanel categorias_Consultar() {
		JPanel categorias_consultar = new JPanel();
		categorias_consultar.setVisible(true);
		categorias_consultar.setSize(900, 550);
		categorias_consultar.setLocation(0, 0);
		categorias_consultar.setBackground(Color.decode("#D9D9D9"));
		categorias_consultar.setLayout(null);

		JPanel panel_opciones = panel_Opciones();
		categorias_consultar.add(panel_opciones);

		JLabel titulo_Panel_Categorias = new JLabel("Categorias", JLabel.CENTER);
		titulo_Panel_Categorias.setSize(150, 30);
		titulo_Panel_Categorias.setLocation(325, 30);
		titulo_Panel_Categorias.setFont(new Font("Arial", Font.BOLD, 23));
		categorias_consultar.add(titulo_Panel_Categorias);

		JLabel titulo_Panel_Categorias_Consultar = new JLabel("Consultar");
		titulo_Panel_Categorias_Consultar.setSize(180, 30);
		titulo_Panel_Categorias_Consultar.setLocation(655, 30);
		titulo_Panel_Categorias_Consultar.setFont(new Font("Arial", Font.BOLD, 23));
		categorias_consultar.add(titulo_Panel_Categorias_Consultar);

		ImageIcon foto_consultar = new ImageIcon("consultar_icono.png");
		JLabel icono_consultar = new JLabel();
		icono_consultar.setSize(85, 85);
		icono_consultar.setLocation(520, 10);
		icono_consultar.setIcon(new ImageIcon(foto_consultar.getImage().getScaledInstance(icono_consultar.getWidth(),
				icono_consultar.getHeight(), Image.SCALE_SMOOTH)));
		categorias_consultar.add(icono_consultar);

		String nombresColumna[] = { "ID", "Nombre", "Cantidad de llantas", "Uso", "Peso promedio (Kg)" };
		JTable tabla = new JTable();
		DefaultTableModel tablaModel = new DefaultTableModel();
		tablaModel.setColumnIdentifiers(nombresColumna);
		tabla.setModel(tablaModel);
		conexion.consultar_Categorias(tablaModel);
		JScrollPane sp = new JScrollPane(tabla);
		sp.setSize(530, 300);
		sp.setLocation(285, 140);
		categorias_consultar.add(sp);

		JButton btn_Volver = new JButton("Volver");
		btn_Volver.setSize(250, 30);
		btn_Volver.setLocation(425, 455);
		btn_Volver.setBackground(Color.decode("#2F0909"));
		btn_Volver.setFont(new Font("Arial", Font.BOLD, 20));
		btn_Volver.setForeground(Color.white);
		categorias_consultar.add(btn_Volver);

		btn_Volver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				actual = "categorias";
				route();
			}
		});

		return categorias_consultar;
	}

	public JPanel categorias_Añadir() {
		JPanel categorias_Añadir = new JPanel();
		categorias_Añadir.setVisible(true);
		categorias_Añadir.setSize(900, 550);
		categorias_Añadir.setLocation(0, 0);
		categorias_Añadir.setBackground(Color.decode("#D9D9D9"));
		categorias_Añadir.setLayout(null);

		JPanel panel_opciones = panel_Opciones();
		categorias_Añadir.add(panel_opciones);

		JLabel titulo_Panel_Categorias = new JLabel("Categorias", JLabel.CENTER);
		titulo_Panel_Categorias.setSize(150, 30);
		titulo_Panel_Categorias.setLocation(325, 30);
		titulo_Panel_Categorias.setFont(new Font("Arial", Font.BOLD, 23));
		categorias_Añadir.add(titulo_Panel_Categorias);

		JLabel titulo_Panel_Categorias_Añadir = new JLabel("Añadir");
		titulo_Panel_Categorias_Añadir.setSize(180, 30);
		titulo_Panel_Categorias_Añadir.setLocation(655, 30);
		titulo_Panel_Categorias_Añadir.setFont(new Font("Arial", Font.BOLD, 23));
		categorias_Añadir.add(titulo_Panel_Categorias_Añadir);

		ImageIcon foto_añadir = new ImageIcon("añadir_icono.png");
		JLabel icono_añadir = new JLabel();
		icono_añadir.setSize(85, 85);
		icono_añadir.setLocation(520, 10);
		icono_añadir.setIcon(new ImageIcon(foto_añadir.getImage().getScaledInstance(icono_añadir.getWidth(),
				icono_añadir.getHeight(), Image.SCALE_SMOOTH)));
		categorias_Añadir.add(icono_añadir);

		JLabel nombre_categoria = new JLabel("Ingresa el nombre de la categoria");
		nombre_categoria.setSize(300, 30);
		nombre_categoria.setLocation(265, 180);
		nombre_categoria.setFont(new Font("Arial", Font.BOLD, 16));
		categorias_Añadir.add(nombre_categoria);

		JTextField in_nombre_categoria = new JTextField();
		in_nombre_categoria.setSize(280, 30);
		in_nombre_categoria.setLocation(250, 210);
		in_nombre_categoria.setFont(new Font("Arial", Font.PLAIN, 16));
		categorias_Añadir.add(in_nombre_categoria);

		JLabel uso_categoria = new JLabel("Ingresa el uso dado para la categoria");
		uso_categoria.setSize(300, 30);
		uso_categoria.setLocation(585, 180);
		uso_categoria.setFont(new Font("Arial", Font.BOLD, 16));
		categorias_Añadir.add(uso_categoria);

		JTextField in_uso_categoria = new JTextField();
		in_uso_categoria.setSize(280, 30);
		in_uso_categoria.setLocation(585, 210);
		in_uso_categoria.setFont(new Font("Arial", Font.PLAIN, 16));
		categorias_Añadir.add(in_uso_categoria);

		JLabel cantidad_llantas_categoria_1 = new JLabel("Ingresa la cantidad de llantas que");
		cantidad_llantas_categoria_1.setSize(300, 30);
		cantidad_llantas_categoria_1.setLocation(265, 270);
		cantidad_llantas_categoria_1.setFont(new Font("Arial", Font.BOLD, 16));
		categorias_Añadir.add(cantidad_llantas_categoria_1);

		JLabel cantidad_llantas_categoria_2 = new JLabel("usa esta categoria");
		cantidad_llantas_categoria_2.setSize(300, 30);
		cantidad_llantas_categoria_2.setLocation(265, 290);
		cantidad_llantas_categoria_2.setFont(new Font("Arial", Font.BOLD, 16));
		categorias_Añadir.add(cantidad_llantas_categoria_2);

		JTextField in_cantidad_llantas_categoria = new JTextField();
		in_cantidad_llantas_categoria.setSize(280, 30);
		in_cantidad_llantas_categoria.setLocation(250, 350);
		in_cantidad_llantas_categoria.setFont(new Font("Arial", Font.PLAIN, 16));
		categorias_Añadir.add(in_cantidad_llantas_categoria);

		JLabel peso_categoria_1 = new JLabel("Ingresa el peso promedio de los");
		peso_categoria_1.setSize(300, 30);
		peso_categoria_1.setLocation(600, 270);
		peso_categoria_1.setFont(new Font("Arial", Font.BOLD, 16));
		categorias_Añadir.add(peso_categoria_1);

		JLabel peso_categoria_2 = new JLabel("vehículos en esta categoria (Kg)");
		peso_categoria_2.setSize(300, 30);
		peso_categoria_2.setLocation(600, 290);
		peso_categoria_2.setFont(new Font("Arial", Font.BOLD, 16));
		categorias_Añadir.add(peso_categoria_2);

		JTextField in_peso_categoria = new JTextField();
		in_peso_categoria.setSize(280, 30);
		in_peso_categoria.setLocation(585, 350);
		in_peso_categoria.setFont(new Font("Arial", Font.PLAIN, 16));
		categorias_Añadir.add(in_peso_categoria);

		JButton btn_Volver = new JButton("Volver");
		btn_Volver.setSize(250, 30);
		btn_Volver.setLocation(275, 455);
		btn_Volver.setBackground(Color.decode("#2F0909"));
		btn_Volver.setFont(new Font("Arial", Font.BOLD, 20));
		btn_Volver.setForeground(Color.white);
		categorias_Añadir.add(btn_Volver);

		JButton btn_Crear = new JButton("Crear");
		btn_Crear.setSize(250, 30);
		btn_Crear.setLocation(575, 455);
		btn_Crear.setBackground(Color.decode("#2A5729"));
		btn_Crear.setFont(new Font("Arial", Font.BOLD, 20));
		btn_Crear.setForeground(Color.white);
		categorias_Añadir.add(btn_Crear);

		btn_Volver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				actual = "categorias";
				route();
			}
		});

		btn_Crear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (!in_nombre_categoria.getText().isEmpty() && !in_cantidad_llantas_categoria.getText().isEmpty()
						&& !in_peso_categoria.getText().isEmpty() && !in_uso_categoria.getText().isEmpty()) {
					if (validarDigitos(in_nombre_categoria) && validarDigitos(in_uso_categoria)) {
						if (validarNumeros(in_peso_categoria) && validarNumeros(in_cantidad_llantas_categoria)) {
							if (!conexion.añadir_Categorias(in_nombre_categoria, in_cantidad_llantas_categoria,
									in_uso_categoria, in_peso_categoria)) {
								JOptionPane.showMessageDialog(null, "Categoria añadida correctamente");
								in_cantidad_llantas_categoria.setText("");
								in_nombre_categoria.setText("");
								in_peso_categoria.setText("");
								in_uso_categoria.setText("");
							} else {
								JOptionPane.showMessageDialog(null, "Error. Esta categoria ya se encuentra añadida");
							}
						} else {
							JOptionPane.showMessageDialog(null,
									"Error. Solo se permiten valores numericos para el peso y cantidad de llantas");
						}
					} else {
						JOptionPane.showMessageDialog(null,
								"Error. No se permiten valores numericos para el nombre y uso");
					}

				} else {
					JOptionPane.showMessageDialog(null, "Error. Todos los campos deben estar llenados");
				}

			}
		});

		return categorias_Añadir;
	}

	public JPanel categorias_Editar() {
		JPanel categorias_Editar = new JPanel();
		categorias_Editar.setVisible(true);
		categorias_Editar.setSize(900, 550);
		categorias_Editar.setLocation(0, 0);
		categorias_Editar.setBackground(Color.decode("#D9D9D9"));
		categorias_Editar.setLayout(null);

		JPanel panel_opciones = panel_Opciones();
		categorias_Editar.add(panel_opciones);

		JLabel titulo_Panel_Categorias = new JLabel("Categorias", JLabel.CENTER);
		titulo_Panel_Categorias.setSize(150, 30);
		titulo_Panel_Categorias.setLocation(325, 30);
		titulo_Panel_Categorias.setFont(new Font("Arial", Font.BOLD, 23));
		categorias_Editar.add(titulo_Panel_Categorias);

		JLabel titulo_Panel_Categorias_Editar = new JLabel("Editar");
		titulo_Panel_Categorias_Editar.setSize(180, 30);
		titulo_Panel_Categorias_Editar.setLocation(655, 30);
		titulo_Panel_Categorias_Editar.setFont(new Font("Arial", Font.BOLD, 23));
		categorias_Editar.add(titulo_Panel_Categorias_Editar);

		ImageIcon foto_editar = new ImageIcon("editar_icono.png");
		JLabel icono_editar = new JLabel();
		icono_editar.setSize(85, 85);
		icono_editar.setLocation(520, 10);
		icono_editar.setIcon(new ImageIcon(foto_editar.getImage().getScaledInstance(icono_editar.getWidth(),
				icono_editar.getHeight(), Image.SCALE_SMOOTH)));
		categorias_Editar.add(icono_editar);

		cmb.setSize(440, 30);
		cmb.setLocation(325, 120);
		categorias_Editar.add(cmb);

		JLabel instruccion = new JLabel("Selecciona la categoria que deseas editar");
		instruccion.setSize(300, 30);
		instruccion.setLocation(425, 150);
		instruccion.setFont(new Font("Arial", Font.PLAIN, 12));
		categorias_Editar.add(instruccion);

		JLabel nombre_categoria = new JLabel("Ingresa el nombre de la categoria");
		nombre_categoria.setSize(300, 30);
		nombre_categoria.setLocation(265, 210);
		nombre_categoria.setFont(new Font("Arial", Font.BOLD, 16));
		categorias_Editar.add(nombre_categoria);

		JTextField in_nombre_categoria = new JTextField();
		in_nombre_categoria.setSize(280, 30);
		in_nombre_categoria.setLocation(250, 240);
		in_nombre_categoria.setFont(new Font("Arial", Font.PLAIN, 16));
		categorias_Editar.add(in_nombre_categoria);

		JLabel uso_categoria = new JLabel("Ingresa el uso dado para la categoria");
		uso_categoria.setSize(300, 30);
		uso_categoria.setLocation(585, 210);
		uso_categoria.setFont(new Font("Arial", Font.BOLD, 16));
		categorias_Editar.add(uso_categoria);

		JTextField in_uso_categoria = new JTextField();
		in_uso_categoria.setSize(280, 30);
		in_uso_categoria.setLocation(585, 240);
		in_uso_categoria.setFont(new Font("Arial", Font.PLAIN, 16));
		categorias_Editar.add(in_uso_categoria);

		JLabel cantidad_llantas_categoria_1 = new JLabel("Ingresa la cantidad de llantas que");
		cantidad_llantas_categoria_1.setSize(300, 30);
		cantidad_llantas_categoria_1.setLocation(265, 300);
		cantidad_llantas_categoria_1.setFont(new Font("Arial", Font.BOLD, 16));
		categorias_Editar.add(cantidad_llantas_categoria_1);

		JLabel cantidad_llantas_categoria_2 = new JLabel("usa esta categoria");
		cantidad_llantas_categoria_2.setSize(300, 30);
		cantidad_llantas_categoria_2.setLocation(265, 320);
		cantidad_llantas_categoria_2.setFont(new Font("Arial", Font.BOLD, 16));
		categorias_Editar.add(cantidad_llantas_categoria_2);

		JTextField in_cantidad_llantas_categoria = new JTextField();
		in_cantidad_llantas_categoria.setSize(280, 30);
		in_cantidad_llantas_categoria.setLocation(250, 380);
		in_cantidad_llantas_categoria.setFont(new Font("Arial", Font.PLAIN, 16));
		categorias_Editar.add(in_cantidad_llantas_categoria);

		JLabel peso_categoria_1 = new JLabel("Ingresa el peso promedio de los");
		peso_categoria_1.setSize(300, 30);
		peso_categoria_1.setLocation(600, 300);
		peso_categoria_1.setFont(new Font("Arial", Font.BOLD, 16));
		categorias_Editar.add(peso_categoria_1);

		JLabel peso_categoria_2 = new JLabel("vehículos en esta categoria");
		peso_categoria_2.setSize(300, 30);
		peso_categoria_2.setLocation(600, 320);
		peso_categoria_2.setFont(new Font("Arial", Font.BOLD, 16));
		categorias_Editar.add(peso_categoria_2);

		JTextField in_peso_categoria = new JTextField();
		in_peso_categoria.setSize(280, 30);
		in_peso_categoria.setLocation(585, 380);
		in_peso_categoria.setFont(new Font("Arial", Font.PLAIN, 16));
		categorias_Editar.add(in_peso_categoria);

		JButton btn_Volver = new JButton("Volver");
		btn_Volver.setSize(250, 30);
		btn_Volver.setLocation(275, 455);
		btn_Volver.setBackground(Color.decode("#2F0909"));
		btn_Volver.setFont(new Font("Arial", Font.BOLD, 20));
		btn_Volver.setForeground(Color.white);
		categorias_Editar.add(btn_Volver);

		JButton btn_Editar = new JButton("Editar");
		btn_Editar.setSize(250, 30);
		btn_Editar.setLocation(575, 455);
		btn_Editar.setBackground(Color.decode("#2A5729"));
		btn_Editar.setFont(new Font("Arial", Font.BOLD, 20));
		btn_Editar.setForeground(Color.white);
		categorias_Editar.add(btn_Editar);

		/*
		 * cmb.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) { // TODO Auto-generated
		 * method stub
		 * conexion.llenar_Campos_Categoria_Segun_ComboBox(in_nombre_categoria,
		 * in_cantidad_llantas_categoria, in_uso_categoria, in_peso_categoria, cmb); }
		 * });
		 */

		btn_Volver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				actual = "categorias";
				route();
			}
		});

		btn_Editar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (!in_nombre_categoria.getText().isEmpty() && !in_cantidad_llantas_categoria.getText().isEmpty()
						&& !in_peso_categoria.getText().isEmpty() && !in_uso_categoria.getText().isEmpty()) {
					if (validarDigitos(in_nombre_categoria) && validarDigitos(in_uso_categoria)) {
						if (validarNumeros(in_peso_categoria) && validarNumeros(in_cantidad_llantas_categoria)) {
							int confirmacion = JOptionPane.showConfirmDialog(null,
									"¿Está seguro de editar esta categoria?");
							if (confirmacion == 0) {
								if (conexion.editar_Categoria(in_nombre_categoria, in_cantidad_llantas_categoria,
										in_uso_categoria, in_peso_categoria, cmb)) {
									JOptionPane.showMessageDialog(null, "Categoria editada correctamente");
									in_cantidad_llantas_categoria.setText("");
									in_nombre_categoria.setText("");
									in_peso_categoria.setText("");
									in_uso_categoria.setText("");
								} else {
									JOptionPane.showMessageDialog(null,
											"Error. Esta categoria no existe o ya ha sido registrada con el mismo nombre");
								}
							}
						} else {
							JOptionPane.showMessageDialog(null,
									"Error. Solo se permiten valores numericos para el peso y cantidad de llantas");
						}
					} else {
						JOptionPane.showMessageDialog(null,
								"Error. No se permiten valores numericos para el nombre y uso");
					}

				} else {
					JOptionPane.showMessageDialog(null, "Error. Todos los campos deben estar llenados");
				}
			}
		});

		return categorias_Editar;
	}

	public JPanel categorias_Eliminar() {
		JPanel categorias_Eliminar = new JPanel();
		categorias_Eliminar.setVisible(true);
		categorias_Eliminar.setSize(900, 550);
		categorias_Eliminar.setLocation(0, 0);
		categorias_Eliminar.setBackground(Color.decode("#D9D9D9"));
		categorias_Eliminar.setLayout(null);

		JPanel panel_opciones = panel_Opciones();
		categorias_Eliminar.add(panel_opciones);

		JLabel titulo_Panel_Categorias = new JLabel("Categorias", JLabel.CENTER);
		titulo_Panel_Categorias.setSize(150, 30);
		titulo_Panel_Categorias.setLocation(325, 30);
		titulo_Panel_Categorias.setFont(new Font("Arial", Font.BOLD, 23));
		categorias_Eliminar.add(titulo_Panel_Categorias);

		JLabel titulo_Panel_Categorias_Eliminar = new JLabel("Elminar");
		titulo_Panel_Categorias_Eliminar.setSize(180, 30);
		titulo_Panel_Categorias_Eliminar.setLocation(655, 30);
		titulo_Panel_Categorias_Eliminar.setFont(new Font("Arial", Font.BOLD, 23));
		categorias_Eliminar.add(titulo_Panel_Categorias_Eliminar);

		ImageIcon foto_editar = new ImageIcon("eliminar_icono.png");
		JLabel icono_editar = new JLabel();
		icono_editar.setSize(85, 85);
		icono_editar.setLocation(520, 10);
		icono_editar.setIcon(new ImageIcon(foto_editar.getImage().getScaledInstance(icono_editar.getWidth(),
				icono_editar.getHeight(), Image.SCALE_SMOOTH)));
		categorias_Eliminar.add(icono_editar);

		cmb.setSize(440, 30);
		cmb.setLocation(325, 120);
		categorias_Eliminar.add(cmb);

		JLabel instruccion = new JLabel("Selecciona la categoria que deseas eliminar");
		instruccion.setSize(300, 30);
		instruccion.setLocation(425, 150);
		instruccion.setFont(new Font("Arial", Font.PLAIN, 12));
		categorias_Eliminar.add(instruccion);

		JLabel nombre_categoria = new JLabel("Ingresa el nombre de la categoria");
		nombre_categoria.setSize(300, 30);
		nombre_categoria.setLocation(265, 210);
		nombre_categoria.setFont(new Font("Arial", Font.BOLD, 16));
		categorias_Eliminar.add(nombre_categoria);

		JTextField in_nombre_categoria = new JTextField();
		in_nombre_categoria.setSize(280, 30);
		in_nombre_categoria.setLocation(250, 240);
		in_nombre_categoria.setFont(new Font("Arial", Font.PLAIN, 16));
		categorias_Eliminar.add(in_nombre_categoria);

		JLabel uso_categoria = new JLabel("Ingresa el uso dado para la categoria");
		uso_categoria.setSize(300, 30);
		uso_categoria.setLocation(585, 210);
		uso_categoria.setFont(new Font("Arial", Font.BOLD, 16));
		categorias_Eliminar.add(uso_categoria);

		JTextField in_uso_categoria = new JTextField();
		in_uso_categoria.setSize(280, 30);
		in_uso_categoria.setLocation(585, 240);
		in_uso_categoria.setFont(new Font("Arial", Font.PLAIN, 16));
		categorias_Eliminar.add(in_uso_categoria);

		JLabel cantidad_llantas_categoria_1 = new JLabel("Ingresa la cantidad de llantas que");
		cantidad_llantas_categoria_1.setSize(300, 30);
		cantidad_llantas_categoria_1.setLocation(265, 300);
		cantidad_llantas_categoria_1.setFont(new Font("Arial", Font.BOLD, 16));
		categorias_Eliminar.add(cantidad_llantas_categoria_1);

		JLabel cantidad_llantas_categoria_2 = new JLabel("usa esta categoria");
		cantidad_llantas_categoria_2.setSize(300, 30);
		cantidad_llantas_categoria_2.setLocation(265, 320);
		cantidad_llantas_categoria_2.setFont(new Font("Arial", Font.BOLD, 16));
		categorias_Eliminar.add(cantidad_llantas_categoria_2);

		JTextField in_cantidad_llantas_categoria = new JTextField();
		in_cantidad_llantas_categoria.setSize(280, 30);
		in_cantidad_llantas_categoria.setLocation(250, 380);
		in_cantidad_llantas_categoria.setFont(new Font("Arial", Font.PLAIN, 16));
		categorias_Eliminar.add(in_cantidad_llantas_categoria);

		JLabel peso_categoria_1 = new JLabel("Ingresa el peso promedio de los");
		peso_categoria_1.setSize(300, 30);
		peso_categoria_1.setLocation(600, 300);
		peso_categoria_1.setFont(new Font("Arial", Font.BOLD, 16));
		categorias_Eliminar.add(peso_categoria_1);

		JLabel peso_categoria_2 = new JLabel("vehículos en esta categoria");
		peso_categoria_2.setSize(300, 30);
		peso_categoria_2.setLocation(600, 320);
		peso_categoria_2.setFont(new Font("Arial", Font.BOLD, 16));
		categorias_Eliminar.add(peso_categoria_2);

		JTextField in_peso_categoria = new JTextField();
		in_peso_categoria.setSize(280, 30);
		in_peso_categoria.setLocation(585, 380);
		in_peso_categoria.setFont(new Font("Arial", Font.PLAIN, 16));
		categorias_Eliminar.add(in_peso_categoria);

		JButton btn_Volver = new JButton("Volver");
		btn_Volver.setSize(250, 30);
		btn_Volver.setLocation(275, 455);
		btn_Volver.setBackground(Color.decode("#2A5729"));
		btn_Volver.setFont(new Font("Arial", Font.BOLD, 20));
		btn_Volver.setForeground(Color.white);
		categorias_Eliminar.add(btn_Volver);

		JButton btn_Eliminar = new JButton("Eliminar");
		btn_Eliminar.setSize(250, 30);
		btn_Eliminar.setLocation(575, 455);
		btn_Eliminar.setBackground(Color.decode("#2F0909"));
		btn_Eliminar.setFont(new Font("Arial", Font.BOLD, 20));
		btn_Eliminar.setForeground(Color.white);
		categorias_Eliminar.add(btn_Eliminar);

		btn_Volver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				actual = "categorias";
				route();
			}
		});

		btn_Eliminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int confirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar esta categoria?");
				if (confirmacion == 0) {
					conexion.eliminar_Categoria(cmb);
					JOptionPane.showMessageDialog(null, "Categoria eliminada correctamente");
				}
			}
		});

		return categorias_Eliminar;
	}

	public JPanel marcas() {
		JPanel marcas = new JPanel();
		marcas.setVisible(true);
		marcas.setSize(900, 550);
		marcas.setLocation(0, 0);
		marcas.setBackground(Color.decode("#D9D9D9"));
		marcas.setLayout(null);

		JPanel panel_opciones = panel_Opciones();
		marcas.add(panel_opciones);

		JLabel titulo_Panel_Marcas = new JLabel("Marcas", JLabel.CENTER);
		titulo_Panel_Marcas.setSize(150, 30);
		titulo_Panel_Marcas.setLocation(305, 30);
		titulo_Panel_Marcas.setFont(new Font("Arial", Font.ITALIC, 23));
		marcas.add(titulo_Panel_Marcas);

		JButton btn_volver_Marcas = new JButton("Volver");
		btn_volver_Marcas.setSize(150, 30);
		btn_volver_Marcas.setLocation(295, 450);
		btn_volver_Marcas.setFont(new Font("Arial", Font.BOLD, 20));
		marcas.add(btn_volver_Marcas);
		btn_volver_Marcas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				actual = "dash";
				route();
			}

		});

		return marcas;
	}

	public JPanel panel_Opciones() {
		JPanel panel_Opciones = new JPanel();
		panel_Opciones.setLayout(null);
		panel_Opciones.setBackground(Color.decode("#2F0909"));
		panel_Opciones.setSize(225, 550);
		panel_Opciones.setLocation(0, 0);

		ImageIcon foto = new ImageIcon("carro.png");
		JLabel icono = new JLabel();
		icono.setSize(85, 85);
		icono.setLocation(75, 20);
		icono.setIcon(new ImageIcon(
				foto.getImage().getScaledInstance(icono.getWidth(), icono.getHeight(), Image.SCALE_SMOOTH)));
		panel_Opciones.add(icono);

		JButton btn_vehiculos = new JButton("Vehiculos");
		btn_vehiculos.setSize(150, 30);
		btn_vehiculos.setLocation(35, 125);
		btn_vehiculos.setFont(new Font("Arial", Font.BOLD, 20));
		panel_Opciones.add(btn_vehiculos);

		btn_vehiculos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				actual = "vehiculos";
				route();
			}
		});

		JButton btn_Clientes = new JButton("Clientes");
		btn_Clientes.setSize(150, 30);
		btn_Clientes.setLocation(35, 188);
		btn_Clientes.setFont(new Font("Arial", Font.BOLD, 20));
		panel_Opciones.add(btn_Clientes);

		btn_Clientes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				actual = "clientes";
				route();
			}
		});

		JButton btn_Rentas = new JButton("Rentas");
		btn_Rentas.setSize(150, 30);
		btn_Rentas.setLocation(35, 250);
		btn_Rentas.setFont(new Font("Arial", Font.BOLD, 20));
		panel_Opciones.add(btn_Rentas);

		btn_Rentas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				actual = "rentas";
				route();
			}
		});

		JButton btn_Categorias = new JButton("Categorias");
		btn_Categorias.setSize(150, 30);
		btn_Categorias.setLocation(35, 315);
		btn_Categorias.setFont(new Font("Arial", Font.BOLD, 20));
		panel_Opciones.add(btn_Categorias);

		btn_Categorias.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				actual = "categorias";
				route();
			}
		});

		JButton btn_Marcas = new JButton("Marcas");
		btn_Marcas.setSize(150, 30);
		btn_Marcas.setLocation(35, 380);
		btn_Marcas.setFont(new Font("Arial", Font.BOLD, 20));
		panel_Opciones.add(btn_Marcas);

		btn_Marcas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				actual = "marcas";
				route();
			}
		});

		JButton btn_Cerrar_Ses = new JButton("Cerrar sesión");
		btn_Cerrar_Ses.setSize(150, 30);
		btn_Cerrar_Ses.setLocation(35, 440);
		btn_Cerrar_Ses.setFont(new Font("Arial", Font.BOLD, 17));
		panel_Opciones.add(btn_Cerrar_Ses);

		btn_Cerrar_Ses.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "Cerrando sesion...");
				actual = "login";
				route();
			}
		});

		return panel_Opciones;
	}

	public boolean validarDigitos(JTextField campo) {
		char arreglo_Campo;
		for (int i = 0; i < campo.getText().length(); i++) {
			arreglo_Campo = campo.getText().charAt(i);
			if (!Character.isLetter(arreglo_Campo) && arreglo_Campo != ' ') {
				return false;
			}
		}
		return true;
	}

	public boolean validarNumeros(JTextField campo) {
		char arreglo_Numeros;
		for (int i = 0; i < campo.getText().length(); i++) {
			arreglo_Numeros = campo.getText().charAt(i);
			if (!Character.isDigit(arreglo_Numeros)) {
				return false;
			}
		}
		return true;
	}

	public boolean validarFecha(JTextField campo) {
		String line_fecha = campo.getText();
		String[] fecha = line_fecha.split("-");
		if (fecha[0].length() == 4 && fecha[1].length() == 2 && fecha[2].length() == 2) {
			return true;
		} else {
			return false;
		}
	}
}