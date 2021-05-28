package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;

import db.DBConnection;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.function.Consumer;

import javax.swing.border.BevelBorder;

public class MainMenu extends JFrame implements Consumer<Boolean>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton reservationMenuBtn = new JButton("Reservation Menu");
	private JButton serviceMenuBtn = new JButton("Service Menu");
	private JButton customerMenuBtn = new JButton("Customer Menu");
	private JButton barberMenuBtn = new JButton("Barber Menu");
	private JButton connectionCheckerButton = new JButton("Restart Connection");
	private JLabel firstNameLabel = new JLabel("Main Menu");
	
	private JPanel panel = new JPanel();
	
	private JLabel lblDatabaseOnline = new JLabel("Database Status");
	
	private ConnectionChecker connectionChecker;
	private JButton btnCloseConnection = new JButton("Close Connection");
	
	public MainMenu() {
		 setTitle("Main Menu");
		    setSize(507,400);
		    setLocation(new Point(300,200));
		    getContentPane().setLayout(null);
		    getContentPane().setBackground(Color.decode("#C3C3C3")); 
		    initComponent();
		    initEvent();
		    connectionChecker = new ConnectionChecker(this);
		    //make button when pressed it will reconnect connectionChecker.restart();
		    
	}
	
	
	private void initComponent() {
		 reservationMenuBtn.setBounds(70, 70, 150, 30);
		 reservationMenuBtn.setBorder(new LineBorder(Color.BLACK));
		 reservationMenuBtn.setBackground(Color.decode("#FFFFFF"));
		 reservationMenuBtn.setFocusPainted(false);
		 serviceMenuBtn.setBounds(70, 130, 150, 30);
		 serviceMenuBtn.setBorder(new LineBorder(Color.BLACK));
		 serviceMenuBtn.setBackground(Color.decode("#FFFFFF"));
		 serviceMenuBtn.setFocusPainted(false);
		 customerMenuBtn.setBounds(70, 190, 150, 30);
		 customerMenuBtn.setBorder(new LineBorder(Color.BLACK));
		 customerMenuBtn.setBackground(Color.decode("#FFFFFF"));
		 customerMenuBtn.setFocusPainted(false);
		 barberMenuBtn.setBounds(70, 250, 150, 30);
		 barberMenuBtn.setBorder(new LineBorder(Color.BLACK));
		 barberMenuBtn.setBackground(Color.decode("#FFFFFF"));
		 barberMenuBtn.setFocusPainted(false);
		 firstNameLabel.setBounds(20,100, 150, 30);
	
		 JLabel label = new JLabel("");
		 label.setBounds(260,70,201,210);
		 
		 //Scaling of image in main menu
		 BufferedImage img = null;
		 try {
		     img = ImageIO.read(new File("./img/mainmenucartell.png"));
		 } catch (IOException e) {
		     e.printStackTrace();
		 }
		 Image dimg = img.getScaledInstance(label.getWidth(), label.getHeight(),
			        Image.SCALE_SMOOTH);
		 ImageIcon imageIcon = new ImageIcon(dimg);
		 label.setIcon(imageIcon);
		 
		 getContentPane().add(label);
		 getContentPane().add(reservationMenuBtn);
		 getContentPane().add(serviceMenuBtn);
		 getContentPane().add(customerMenuBtn);
		 getContentPane().add(barberMenuBtn);
		 
		 JLabel mainMenuLabel = new JLabel("Main Menu");
		 mainMenuLabel.setFont(new Font("Tahoma", Font.PLAIN, 21));
		 mainMenuLabel.setHorizontalAlignment(SwingConstants.CENTER);
		 mainMenuLabel.setBounds(70, 24, 150, 35);
		 getContentPane().add(mainMenuLabel);
		 
		 panel.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.BLACK, null, Color.BLACK));
		 panel.setBounds(465, 340, 10, 10);
		 getContentPane().add(panel);
		 
		 lblDatabaseOnline.setHorizontalAlignment(SwingConstants.CENTER);
		 lblDatabaseOnline.setFont(new Font("Tahoma", Font.PLAIN, 11));
		 lblDatabaseOnline.setBounds(368, 340, 93, 10);
		 getContentPane().add(lblDatabaseOnline);
		 
		 connectionCheckerButton.setFocusPainted(false);
		 connectionCheckerButton.setBorder(new LineBorder(Color.BLACK));
		 connectionCheckerButton.setBackground(Color.WHITE);
		 connectionCheckerButton.setBounds(249, 334, 103, 23);
		 getContentPane().add(connectionCheckerButton);
		 connectionCheckerButton.setVisible(false);
		 
		 //for testing purposes
		 btnCloseConnection.setFocusPainted(false);
		 btnCloseConnection.setBorder(new LineBorder(Color.BLACK));
		 btnCloseConnection.setBackground(Color.WHITE);
		 btnCloseConnection.setBounds(99, 334, 103, 23);
		 getContentPane().add(btnCloseConnection);	 
		 btnCloseConnection.setVisible(false);
	} 
	

	private void initEvent() {
		
		this.addWindowListener(new WindowAdapter() {
		      public void windowClosing(WindowEvent e){
		       System.exit(1);
		      }
		});
		
		reservationMenuBtn.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e) {
				ReservationMenu reservationMenu = new ReservationMenu();
				reservationMenu.setVisible(true);
				}
			});
		
		serviceMenuBtn.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e) {
				ServiceMenu serviceMenu = new ServiceMenu();
				serviceMenu.setVisible(true);
				}
			});
		
		customerMenuBtn.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e) {
				CustomerMenu customerMenu = new CustomerMenu();
				customerMenu.setVisible(true);
				}
			});
		
		barberMenuBtn.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e) {
				BarberMenu barberMenu = new BarberMenu();
				barberMenu.setVisible(true);
				}
			});
		
		connectionCheckerButton.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e) {
				connectionChecker.restart();
				}
			});
		
		btnCloseConnection.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e) {
				try {
					DBConnection.getInstance().getConnection().close();
				//	connectionChecker.close();
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				}
			});
		
		
	}

	@Override
	public void accept(Boolean b) {
		if(b) {
			//System.out.println("True");
			panel.setBackground(Color.GREEN);
			lblDatabaseOnline.setText("Database Online");
			connectionCheckerButton.setVisible(false);
		}
		else {
			panel.setBackground(Color.RED);
			lblDatabaseOnline.setText("Database Offline");
			connectionCheckerButton.setVisible(true);
			//System.out.println("False");
		}

	}
}