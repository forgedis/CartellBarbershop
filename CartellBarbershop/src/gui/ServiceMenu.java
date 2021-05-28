package gui;

import javax.swing.*;
import javax.swing.border.LineBorder;

import db.DataAccessException;

import java.awt.*;
import java.awt.event.*;

public class ServiceMenu extends JFrame {
	private JButton createServiceBtn = new JButton("Create Service");
	private JButton findServiceBtn = new JButton("Find Service");
	private JButton deleteServiceBtn = new JButton("Delete Service");
	private JButton updateServiceBtn = new JButton("Update Service");
	private JButton priceMenu = new JButton("Price Menu");
	private final JLabel serviceMenuLabel = new JLabel("Service Menu");
	
	public ServiceMenu() {
		setTitle("Service Management Menu");
	    setSize(300,380);
	    setLocation(new Point(300,200));
	    getContentPane().setLayout(null);    
	    setResizable(false);
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    getContentPane().setBackground(Color.decode("#C3C3C3"));
	    initComponent(); 
	    initEvent();
	}
	
	private void initComponent() {
		createServiceBtn.setBounds(70, 70, 150, 30);
		createServiceBtn.setBorder(new LineBorder(Color.BLACK));
		createServiceBtn.setBackground(Color.decode("#FFFFFF"));
		createServiceBtn.setFocusPainted(false);
		findServiceBtn.setBounds(70, 125, 150, 30);
		findServiceBtn.setBorder(new LineBorder(Color.BLACK));
		findServiceBtn.setBackground(Color.decode("#FFFFFF"));
		findServiceBtn.setFocusPainted(false);
		deleteServiceBtn.setBounds(70, 180, 150, 30);
		deleteServiceBtn.setBorder(new LineBorder(Color.BLACK));
		deleteServiceBtn.setBackground(Color.decode("#FFFFFF"));
		deleteServiceBtn.setFocusPainted(false);
		updateServiceBtn.setBounds(70, 235, 150, 30);
		updateServiceBtn.setBorder(new LineBorder(Color.BLACK));
		updateServiceBtn.setBackground(Color.decode("#FFFFFF"));
		updateServiceBtn.setFocusPainted(false);
		priceMenu.setBounds(70, 290, 150, 30);
		priceMenu.setBorder(new LineBorder(Color.BLACK));
		priceMenu.setBackground(Color.decode("#FFFFFF"));
		priceMenu.setFocusPainted(false);
		
		getContentPane().add(createServiceBtn);
		getContentPane().add(findServiceBtn);
		getContentPane().add(deleteServiceBtn);
		getContentPane().add(updateServiceBtn);
		getContentPane().add(priceMenu);
		serviceMenuLabel.setHorizontalAlignment(SwingConstants.CENTER);
		serviceMenuLabel.setFont(new Font("Tahoma", Font.PLAIN, 21));
		serviceMenuLabel.setBounds(0, 20, 294, 35);
		
		getContentPane().add(serviceMenuLabel);
	}
	
	private void initEvent() {
		createServiceBtn.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e) {
				CreateServiceWindow CBW = new CreateServiceWindow();
				CBW.setVisible(true);
				}
			});
		
		findServiceBtn.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e) {
				FindServiceWindow FBW;
				try {
					FBW = new FindServiceWindow();
					FBW.setVisible(true);
				} catch (DataAccessException e1) {
					e1.printStackTrace();
				}
				}
			});
		
		deleteServiceBtn.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e) {
				DeleteServiceWindow DBW = new DeleteServiceWindow();
				DBW.setVisible(true);
				}
			});
		
		updateServiceBtn.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e) {
				UpdateServiceWindow UBW = new UpdateServiceWindow();
				UBW.setVisible(true);
				}
			});
		priceMenu.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e) {
				PriceMenu priceMenu = new PriceMenu();
				priceMenu.setVisible(true);
				}
			});
	}
}
