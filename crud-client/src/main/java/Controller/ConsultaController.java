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
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import Model.MyUser;
import Model.TableModel;

/**
 *
 * @author zier
 */
public class ConsultaController {

    public static void consulta(ApplicationClient client, Consulta consulta, Update update) {

        consulta.getTable().setSelectionMode(0);

        consulta.getUpdateBtn().addActionListener((new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    MyUser user = new MyUser();
                    int index = consulta.getTable().getSelectedRow();
                    user.setId((long) consulta.getTable().getValueAt(index, 0));
                    user.setUserName((String) consulta.getTable().getValueAt(index, 1));
                    user.setPassword((String) consulta.getTable().getValueAt(index, 2));

                    update.getIdField().setText(String.valueOf(user.getId()));
                    update.getUsernameField().setText(user.getUserName());
                    update.getPasswordField().setText(user.getPassword());
                    update.setVisible(true);

                } catch (Exception ex) {
                    JOptionPane optionPane = new JOptionPane("Um Erro Ocorreu:\n" + client.getStatus(), JOptionPane.WARNING_MESSAGE);
                    JDialog dialog = optionPane.createDialog("Aviso!");
                    dialog.setAlwaysOnTop(true);
                    dialog.setVisible(true);
                    ex.printStackTrace();

                }
            }
        }));

        consulta.getDeleteBtn().addActionListener((new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    client.delete((long) consulta.getTable().getValueAt(consulta.getTable().getSelectedRow(), 0));

                    JOptionPane optionPane = new JOptionPane(client.getResult(), JOptionPane.INFORMATION_MESSAGE);
                    JDialog dialog = optionPane.createDialog("Excluído com Sucesso!");
                    dialog.setAlwaysOnTop(true);
                    dialog.setVisible(true);

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

        consulta.getAtualizarListaBtn().addActionListener((new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
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

        consulta.getpesquisaField().addActionListener((new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    TableModel tableModel = new TableModel();

                    for (MyUser user : client.findAll()) {
                        if (user.toString().contains(consulta.getpesquisaField().getText())) {
                            List<MyUser> listUser = new ArrayList<>();
                            listUser.add(user);
                            tableModel.addRow(listUser);
                        }
                    }
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
