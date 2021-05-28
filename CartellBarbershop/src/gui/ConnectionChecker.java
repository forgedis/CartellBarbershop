package gui;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.Consumer;

import db.DBConnection;

public class ConnectionChecker extends Thread {
	private Consumer<Boolean> consumer;
	private boolean running;
	
	
	public ConnectionChecker(Consumer<Boolean> consumer) {
		this.consumer = consumer;
		running = true;
		start();
	}
	
	public void restart() {
		running = true;
		consumer.accept(true);
		DBConnection.getInstance().reOpenConnection();
	}

	public void run() {
		
		
		while(true) {
			try {
				if(running) {
					String strSelect = "select 1 from Customer";	
					DBConnection.getInstance().getConnection();
			        Statement stmt = DBConnection.getInstance().getConnection().createStatement();
			        ResultSet rs = stmt.executeQuery(strSelect);
					consumer.accept(true);
				} 
			} catch (SQLException e1) {
				consumer.accept(false);
				//running = false;
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
}
