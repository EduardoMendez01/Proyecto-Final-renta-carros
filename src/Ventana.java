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
	
	public Ventana() {
	
		this.setVisible(true);
		this.setSize(900, 550);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(81, 0, 2));
		panel.setBounds(0, 0, 225, 511);
		getContentPane().add(panel);
		
		ImageIcon foto = new ImageIcon("carro.png");
		JLabel icono = new JLabel();
		icono.setSize(100, 100);
		icono.setLocation(75, 60);
		icono.setIcon(new ImageIcon(
				foto.getImage().getScaledInstance(icono.getWidth(), icono.getHeight(), Image.SCALE_SMOOTH)));
		panel.add(icono);
		
		this.revalidate();
		this.repaint();
		
		JButton btn_vehiculos = new JButton("Vehiculos");
		btn_vehiculos.setSize(150, 40);
		btn_vehiculos.setLocation(35, 125);
		btn_vehiculos.setFont(new Font("Arial", Font.BOLD, 22));
		panel.add(btn_vehiculos);
		btn_vehiculos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				panel_vehiculos();
				panel.repaint();
			}
			
		});
		
		JButton btn_Clientes = new JButton("Clientes");
		btn_Clientes.setSize(150, 40);
		btn_Clientes.setLocation(35, 185);
		btn_Clientes.setFont(new Font("Arial", Font.BOLD, 22));
		panel.add(btn_Clientes);
		
		JButton btn_Rentas = new JButton("Rentas");
		btn_Rentas.setSize(150, 40);
		btn_Rentas.setLocation(35, 245);
		btn_Rentas.setFont(new Font("Arial", Font.BOLD, 22));
		panel.add(btn_Rentas);
		
		JButton btn_Categorias = new JButton("Categorias");
		btn_Categorias.setSize(150, 40);
		btn_Categorias.setLocation(35, 300);
		btn_Categorias.setFont(new Font("Arial", Font.BOLD, 22));
		panel.add(btn_Categorias);
		
		JButton btn_Marcas = new JButton("Marcas");
		btn_Marcas.setSize(150, 40);
		btn_Marcas.setLocation(35, 355);
		btn_Marcas.setFont(new Font("Arial", Font.BOLD, 22));
		panel.add(btn_Marcas);
		
		JButton btn_Cerrar_Ses = new JButton("Cerrar sesión");
		btn_Cerrar_Ses.setSize(150, 40);
		btn_Cerrar_Ses.setLocation(35, 410);
		btn_Cerrar_Ses.setFont(new Font("Arial", Font.BOLD, 17));
		panel.add(btn_Cerrar_Ses);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(164, 164, 164));
		panel_1.setBounds(225, 0, 659, 511);
		getContentPane().add(panel_1);
		this.setTitle("Renta de carros");
		this.setBackground(Color.BLUE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		
	}
	
	public void panel_vehiculos() {
		
		JPanel vehiculos = new JPanel();
		vehiculos.setVisible(true);
		vehiculos.setSize(900, 550);
		vehiculos.setLocation(0, 0);
		vehiculos.setBackground(Color.decode("#EEE5DA"));
		vehiculos.setLayout(null);
		
		JLabel titulo2 = new JLabel("Panel de clientes", JLabel.CENTER);
		titulo2.setSize(650, 30);
		titulo2.setLocation(0, 20);
		titulo2.setFont(new Font("Arial", Font.BOLD, 23));
		vehiculos.add(titulo2);
		

	}
}