/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javafx.main.search;

import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableRow;
import javafx.scene.input.MouseEvent;
/**
 *
 * @author crepanich23
 */
public class MyEventHandler implements EventHandler<MouseEvent> {
 
      @Override
        public void handle(MouseEvent t) {
            TableRow r = (TableRow) t.getSource();
            int index = r.getIndex();
            searchController.selectedRow = index;
        }
}
