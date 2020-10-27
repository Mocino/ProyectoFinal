package Modelo;

import java.awt.event.KeyEvent;
import javax.swing.JTextField;

public class restricciones {
    
    public void numberKeyPress(KeyEvent evt){
        char car = evt.getKeyChar();
        if((car<'0'||car>'9') && (car!=(char) KeyEvent.VK_BACK_SPACE))
        {
            evt.consume();
        }    
    }
    
    public void numberDecimalKeyPress(KeyEvent evt, JTextField textField) {
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9') && textField.getText().contains(".") && (car != (char) KeyEvent.VK_BACK_SPACE)) {
            evt.consume();
        } else if ((car < '0' || car > '9') && (car != '.') && (car != (char) KeyEvent.VK_BACK_SPACE)) {
            evt.consume();
        }
    }
}
