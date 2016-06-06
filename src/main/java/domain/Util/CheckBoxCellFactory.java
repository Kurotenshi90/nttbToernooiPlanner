package domain.Util;

import domain.CommissieLidInToernooi;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.util.Callback;

/**
 * Created by Peter-Paul on 21/04/2016.
 */
public class CheckBoxCellFactory implements Callback {


    @Override
    public TableCell call(Object param) {
        CheckBoxTableCell<CommissieLidInToernooi, Boolean> checkBoxTableCell= new CheckBoxTableCell<>();
        return checkBoxTableCell;
    }
}
