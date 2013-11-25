package pizzawatch.gui.functions;

import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class DefaultTableModelNoEdit extends DefaultTableModel
{
    @Override
    public boolean isCellEditable(int row, int column)
    {
        return false;
    }
}
