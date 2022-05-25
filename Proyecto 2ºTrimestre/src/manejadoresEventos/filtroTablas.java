package manejadoresEventos;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class filtroTablas {

	public static void filtraEnTabla(JTable table, JTextField textField) 
	{
		
		TableRowSorter<TableModel> sort = new TableRowSorter<>(table.getModel());
		
		table.setRowSorter(sort);

		textField.getDocument().addDocumentListener(new DocumentListener()
		{
			public void insertUpdate(DocumentEvent e) {
				String str = textField.getText();
				if (str.trim().length() == 0) {
					sort.setRowFilter(null);
				} else {
					//(?i) means case insensitive search
					sort.setRowFilter(RowFilter.regexFilter("(?i)" + str));
				}
			}
			public void removeUpdate(DocumentEvent e) {
				String str = textField.getText();
				if (str.trim().length() == 0) {
					sort.setRowFilter(null);
				} else {
					sort.setRowFilter(RowFilter.regexFilter("(?i)" + str));
				}
			}
			public void changedUpdate(DocumentEvent e) {}
		});
	}
}
