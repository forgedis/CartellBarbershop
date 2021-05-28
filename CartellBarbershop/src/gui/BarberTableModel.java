package gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Barber;



class BarberTabelModel extends AbstractTableModel {

	private static final int ID_COL = 0;
	private static final int NAME_COL = 1;


	private String[] columnNames = { "ID", "Name" };
	private List<Barber> Barbers;

	public BarberTabelModel(List<Barber> theBarbers) {
		Barbers = theBarbers;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return Barbers.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		Barber tempBarber = Barbers.get(row);

		switch (col) {
		case ID_COL:
			return tempBarber.getId();
		case NAME_COL:
			return tempBarber.getName();
		}
		return tempBarber;
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
