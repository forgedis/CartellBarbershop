package gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Price;



class PriceTabelModel extends AbstractTableModel {

	private static final int ID_COL = 0;
	private static final int DATE_COL = 1;
	private static final int PRICE_COL = 2;



	private String[] columnNames = { "ID", "From Date", "Price" };
	private List<Price> Prices;

	public PriceTabelModel(List<Price> thePrices) {
		Prices = thePrices;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return Prices.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		Price tempPrice = Prices.get(row);

		switch (col) {
		case ID_COL:
			return tempPrice.getId();
		case DATE_COL:
			return tempPrice.getFromDate();
		case PRICE_COL:
			return tempPrice.getPrice();
		}
		return tempPrice;
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}