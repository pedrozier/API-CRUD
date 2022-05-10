/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Util.ApplicationClient;
import View.Registro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import Model.MyUser;

/**
 *
 * @author zier
 */
public class RegistroController {
    
    public static void registro(ApplicationClient client, Registro registro) {
        
        registro.getSaveBtn().addActionListener((new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    client.save(new MyUser(registro.getUsernameFieldSave().getText(), String.valueOf(registro.getPasswordFieldSave().getPassword())));
                    registro.getUsernameFieldSave().setText("");
                    registro.getPasswordFieldSave().setText("");
                    JOptionPane optionPane = new JOptionPane(client.getResult(), JOptionPane.INFORMATION_MESSAGE);
                    JDialog dialog = optionPane.createDialog("Salvo com Sucesso!");
                    dialog.setAlwaysOnTop(true);
                    dialog.setVisible(true);
                } catch (Exception ex) {
                    JOptionPane optionPane = new JOptionPane("Um Erro Ocorreu:\n" + client.getStatus(), JOptionPane.WARNING_MESSAGE);
                    JDialog dialog = optionPane.createDialog("Aviso!");
                    dialog.setAlwaysOnTop(true);
                    dialog.setVisible(true);
                    ex.printStackTrace();
                }
            }
        }));
        
    }
}
