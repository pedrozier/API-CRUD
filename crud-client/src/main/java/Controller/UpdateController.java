/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Util.ApplicationClient;
import View.Consulta;
import View.Update;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import Model.MyUser;

/**
 *
 * @author zier
 */
public class UpdateController {

    public static void update(ApplicationClient client, Consulta consulta, Update update) {

        update.getUpdateBtn().addActionListener((new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    client.update(new MyUser(Long.parseLong(update.getIdField().getText()), update.getUsernameField().getText(), String.valueOf(update.getPasswordField().getPassword())));
                    update.setVisible(false);
                    JOptionPane optionPane = new JOptionPane(client.getResult(), JOptionPane.INFORMATION_MESSAGE);
                    JDialog dialog = optionPane.createDialog("Salvo com Sucesso!");
                    dialog.setAlwaysOnTop(true);
                    dialog.setVisible(true);

                    client.findAll();
                    consulta.setVisible(true);
                    //TODO//
                    JList jList = new JList(new Vector<MyUser>(client.findAll()));

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
