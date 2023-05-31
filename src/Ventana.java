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
import javax.swing.SwingConstants;

public class Ventana extends JFrame {
	JPanel panel_Contenido = new JPanel();
	JPanel panel_Opciones = new JPanel();
	
	public Ventana() {
		this.setVisible(true);
		this.setSize(900, 550);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setTitle("Renta de carros");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel_Opciones.setBackground(Color.decode("#2F0909"));
		panel_Opciones.setBounds(0, 0, 225, 550);
		this.add(panel_Opciones);
		
		ImageIcon foto = new ImageIcon("carro.png");
		JLabel icono = new JLabel();
		icono.setSize(85, 85);
		icono.setLocation(75, 60);
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
			}
			
		});
		
		panel_Contenido.setBackground(Color.decode("#F2E8E8"));
		panel_Contenido.setBounds(225, 0, 659, 511);
		this.add(panel_Contenido);
		
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
}