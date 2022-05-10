/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Util.ApplicationClient;
import View.Consulta;
import View.Menu;
import View.Registro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import Model.TableModel;

/**
 *
 * @author zier
 */
public class MenuController {

    public static void menu(ApplicationClient client, Registro registro, Consulta consulta, Menu menu) {

        menu.getExtBtn().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menu.setVisible(false);
                menu.dispose();
            }
        });
        menu.getRegistroBtn().addActionListener((new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registro.setVisible(true);
            }
        }));
        menu.getConsultaBtn().addActionListener((new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {

                    consulta.setVisible(true);

                    TableModel tableModel = new TableModel();
                    tableModel.addRow(client.findAll());
                    consulta.getTable().setModel(tableModel);

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
