package gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Barber;
import model.Customer;
import model.Reservation;
import model.Service;
import db.CustomerDB;
import db.DataAccessException;
import db.ReservationDB;



class ReservationTabelModel extends AbstractTableModel {


	private static final int RES_COL = 0;
	private static final int DATE_COL = 1;
	private static final int TIME_COL = 2;
	private static final int CUS_COL = 3;
	private static final int BAR_COL = 4;

	private String[] columnNames = {"Reservation No", "Date", "Time", "Customer Id", "Barber Id"};
	private List<Reservation> reservations;

	

	public ReservationTabelModel(List<Reservation> theReservations) {
		reservations = theReservations;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return reservations.size();
	}
	
	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}
	@Override
	public Object getValueAt(int row, int col) {
		Reservation tempReservation = reservations.get(row);
		
		switch (col) {
		case RES_COL:
			return tempReservation.getReservationNo();
		case DATE_COL:
			return tempReservation.getDate();
		case TIME_COL:
			return tempReservation.getTime();
		case CUS_COL:
			return tempReservation.getCustomer_id();
		 case BAR_COL:
			 return tempReservation.getBarber_id();
		default:
			return tempReservation.getReservationNo();
		}
	}
}
