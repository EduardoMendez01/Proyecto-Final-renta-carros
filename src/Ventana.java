import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Ventana extends JFrame {
	JPanel panel_Contenido = new JPanel();
	JPanel panel_Opciones = new JPanel();
	JPanel padre = new JPanel();
	Conexion conexion = new Conexion();

	public Ventana() {
		this.setVisible(true);
		this.setSize(900, 550);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setTitle("Renta de carros");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel_Opciones.setBackground(Color.decode("#2F0909"));
		panel_Opciones.setBounds(0, 0, 225, 550);

		ImageIcon foto = new ImageIcon("carro.png");
		JLabel icono = new JLabel();
		icono.setSize(85, 85);
		icono.setLocation(75, 20);
		icono.setIcon(new ImageIcon(
				foto.getImage().getScaledInstance(icono.getWidth(), icono.getHeight(), Image.SCALE_SMOOTH)));
		panel_Opciones.add(icono);

		this.revalidate();
		this.repaint();

		JButton btn_vehiculos = new JButton("Vehiculos");
		btn_vehiculos.setSize(150, 30);
		btn_vehiculos.setLocation(35, 125);
		btn_vehiculos.setFont(new Font("Arial", Font.BOLD, 20));
		panel_Opciones.add(btn_vehiculos);
		btn_vehiculos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				panel_Contenido.removeAll();
				panel_vehiculos();
				panel_Contenido.repaint();
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
				panel_Contenido.removeAll();
				panel_Clientes();
				panel_Contenido.repaint();
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
				panel_Contenido.removeAll();
				panel_Rentas();
				panel_Contenido.repaint();
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
				panel_Contenido.removeAll();
				panel_Categorias();
				panel_Contenido.repaint();
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
				panel_Contenido.removeAll();
				panel_Marcas();
				panel_Contenido.repaint();
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
				volver_Login();
			}

		});

		panel_Contenido.setBackground(Color.decode("#F2E8E8"));
		panel_Contenido.setBounds(225, 0, 659, 511);
		
		volver_Login();
		
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
				if (in_ingresar_Nombre.getText().isEmpty() == false
						&& contrasena_Formato_String.isEmpty() == false) {
					if(validarDigitos(in_ingresar_Nombre)) {
						conexion.validar_Inicio_Sesion(in_ingresar_Nombre, in_ingresar_Contrasena);
						padre.removeAll();
						padre.add(panel_Contenido);
						padre.add(panel_Opciones);
						padre.repaint();
					}else {
						JOptionPane.showMessageDialog(null, "Error. No se permiten valores numericos en el nombre.");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Error. No pueden haber campos vacios.");
				}
			}
		});
		
		return login;
	}
	
	public void volver_Login() {
		padre.removeAll();
		padre = login();
		this.add(padre);

		this.repaint();
	}

	public void panel_vehiculos() {

		JLabel titulo_Panel_Vehiculos = new JLabel("Vehículos", JLabel.CENTER);
		titulo_Panel_Vehiculos.setSize(650, 30);
		titulo_Panel_Vehiculos.setLocation(0, 30);
		titulo_Panel_Vehiculos.setFont(new Font("Arial", Font.ITALIC, 23));
		panel_Contenido.add(titulo_Panel_Vehiculos);

		JButton btn_volver_vehicu = new JButton("Volver");
		btn_volver_vehicu.setSize(150, 30);
		btn_volver_vehicu.setLocation(70, 450);
		btn_volver_vehicu.setFont(new Font("Arial", Font.BOLD, 20));
		panel_Contenido.add(btn_volver_vehicu);
		btn_volver_vehicu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				panel_Contenido.removeAll();
				panel_Contenido.repaint();
			}

		});
	}

	public void panel_Clientes() {
		JLabel titulo_Panel_Clientes = new JLabel("Clientes", JLabel.CENTER);
		titulo_Panel_Clientes.setSize(650, 30);
		titulo_Panel_Clientes.setLocation(0, 30);
		titulo_Panel_Clientes.setFont(new Font("Arial", Font.ITALIC, 23));
		panel_Contenido.add(titulo_Panel_Clientes);

		JButton btn_volver = new JButton("Volver");
		btn_volver.setSize(150, 30);
		btn_volver.setLocation(70, 450);
		btn_volver.setFont(new Font("Arial", Font.BOLD, 20));
		panel_Contenido.add(btn_volver);

		btn_volver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				panel_Contenido.removeAll();
				panel_Contenido.repaint();
			}

		});
	}

	public void panel_Rentas() {
		JLabel titulo_Panel_Rentas = new JLabel("Rentas", JLabel.CENTER);
		titulo_Panel_Rentas.setSize(650, 30);
		titulo_Panel_Rentas.setLocation(0, 30);
		titulo_Panel_Rentas.setFont(new Font("Arial", Font.ITALIC, 23));
		panel_Contenido.add(titulo_Panel_Rentas);

		JButton btn_volver_renta = new JButton("Volver");
		btn_volver_renta.setSize(150, 30);
		btn_volver_renta.setLocation(70, 450);
		btn_volver_renta.setFont(new Font("Arial", Font.BOLD, 20));
		panel_Contenido.add(btn_volver_renta);
		btn_volver_renta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				panel_Contenido.removeAll();
				panel_Contenido.repaint();
			}

		});
	}

	public void panel_Categorias() {
		JLabel titulo_Panel_Categorias = new JLabel("Categorias", JLabel.CENTER);
		titulo_Panel_Categorias.setSize(650, 30);
		titulo_Panel_Categorias.setLocation(0, 30);
		titulo_Panel_Categorias.setFont(new Font("Arial", Font.ITALIC, 23));
		panel_Contenido.add(titulo_Panel_Categorias);

		JButton btn_volver_cate = new JButton("Volver");
		btn_volver_cate.setSize(150, 30);
		btn_volver_cate.setLocation(70, 450);
		btn_volver_cate.setFont(new Font("Arial", Font.BOLD, 20));
		panel_Contenido.add(btn_volver_cate);
		btn_volver_cate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				panel_Contenido.removeAll();
				panel_Contenido.repaint();
			}

		});
	}

	public void panel_Marcas() {
		JLabel titulo_Panel_Marcas = new JLabel("Marcas", JLabel.CENTER);
		titulo_Panel_Marcas.setSize(650, 30);
		titulo_Panel_Marcas.setLocation(0, 30);
		titulo_Panel_Marcas.setFont(new Font("Arial", Font.ITALIC, 23));
		panel_Contenido.add(titulo_Panel_Marcas);

		JButton btn_volver_marca = new JButton("Volver");
		btn_volver_marca.setSize(150, 30);
		btn_volver_marca.setLocation(70, 450);
		btn_volver_marca.setFont(new Font("Arial", Font.BOLD, 20));
		panel_Contenido.add(btn_volver_marca);
		btn_volver_marca.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				panel_Contenido.removeAll();
				panel_Contenido.repaint();
			}

		});
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
}