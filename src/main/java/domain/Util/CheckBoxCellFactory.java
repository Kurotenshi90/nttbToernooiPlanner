package domain.Util;

import domain.CommisieLidInToernooi;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.util.Callback;

/**
 * Created by Peter-Paul on 21/04/2016.
 */
public class CheckBoxCellFactory implements Callback {


    @Override
    public TableCell call(Object param) {
        CheckBoxTableCell<CommisieLidInToernooi, Boolean> checkBoxTableCell= new CheckBoxTableCell<>();
        return checkBoxTableCell;
    }
}
