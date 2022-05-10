/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.standart.crud.client;

import Util.ApplicationClient;
import com.google.gson.Gson;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import model.MyUser;

/**
 *
 * @author zier
 */
public class CrudClient {

    public static void main(String[] args) {

        Gson gson = new Gson();

        ApplicationClient client = new ApplicationClient();
        Consulta consulta = new Consulta();
        Registro registro = new Registro();
        Login login = new Login();
        Update update = new Update();
        Menu menu = new Menu();

        menu.setVisible(true);

        menu.getLoginBtn().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login.setVisible(true);
                login.setAlwaysOnTop(true);
            }
        });
        menu.getExtBtn().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menu.setVisible(false);
                menu.dispose();
            }
        });

        login.getLoginBtn().addActionListener((new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    client.doLogin(login.getUsernameField().getText(), login.getPasswordField().getPassword());
                    menu.getRegistroBtn().setEnabled(true);
                    menu.getConsultaBtn().setEnabled(true);
                    login.setVisible(false);
                    login.getUsernameField().setText("");
                    login.getPasswordField().setText("");

                } catch (Exception ex) {
                    JOptionPane optionPane = new JOptionPane("Nome ou Senha Invalidos", JOptionPane.WARNING_MESSAGE);
                    JDialog dialog = optionPane.createDialog("Aviso!");
                    dialog.setAlwaysOnTop(true);
                    dialog.setVisible(true);
                    menu.getRegistroBtn().setEnabled(false);
                    menu.getConsultaBtn().setEnabled(false);
                    ex.printStackTrace();
                }
            }
        }));

        menu.getRegistroBtn().addActionListener((new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registro.setVisible(true);
            }
        }));

        registro.getSaveBtn().addActionListener((new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    client.save(new MyUser(registro.getUsernameFieldSave().getText(), String.valueOf(registro.getPasswordFieldSave().getPassword())));
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

        menu.getConsultaBtn().addActionListener((new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    client.findAll();
                    consulta.setVisible(true);
                    consulta.getList().setSelectionMode(1);
                    JList jList = new JList(new Vector<MyUser>(client.getResultList()));
                    consulta.getList().setModel(jList.getModel());

                } catch (Exception ex) {
                    JOptionPane optionPane = new JOptionPane("Um Erro Ocorreu:\n" + client.getStatus(), JOptionPane.WARNING_MESSAGE);
                    JDialog dialog = optionPane.createDialog("Aviso!");
                    dialog.setAlwaysOnTop(true);
                    dialog.setVisible(true);
                    ex.printStackTrace();
                }
            }
        }));

        consulta.getUpdateBtn().addActionListener((new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    MyUser user = gson.fromJson(gson.toJson(consulta.getList().getSelectedValue()), MyUser.class);
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
                    MyUser user = gson.fromJson(gson.toJson(consulta.getList().getSelectedValue()), MyUser.class);
                    client.delete(user.getId());
                    JOptionPane optionPane = new JOptionPane(client.getResult(), JOptionPane.INFORMATION_MESSAGE);
                    JDialog dialog = optionPane.createDialog("Excluído com Sucesso!");
                    dialog.setAlwaysOnTop(true);
                    dialog.setVisible(true);

                    client.findAll();
                    consulta.setVisible(true);
                    consulta.getList().setSelectionMode(1);
                    JList jList = new JList(new Vector<MyUser>(client.getResultList()));
                    consulta.getList().setModel(jList.getModel());

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
                    client.findAll();
                    consulta.setVisible(true);
                    consulta.getList().setSelectionMode(1);
                    JList jList = new JList(new Vector<MyUser>(client.getResultList()));
                    consulta.getList().setModel(jList.getModel());
                } catch (Exception ex) {
                    JOptionPane optionPane = new JOptionPane("Um Erro Ocorreu:\n" + client.getStatus(), JOptionPane.WARNING_MESSAGE);
                    JDialog dialog = optionPane.createDialog("Aviso!");
                    dialog.setAlwaysOnTop(true);
                    dialog.setVisible(true);
                    ex.printStackTrace();
                }
            }
        }));

        consulta.getIdField().addActionListener((new ActionListener() {
            public void changedUpdate(DocumentEvent e) {
                try {
                    client.findByID(Long.parseLong(consulta.getIdField().getText()));
                    List<MyUser> result = new ArrayList<>();
                    result.add(client.getObjResult());
                    JList jList = new JList(new Vector<MyUser>(result));
                    consulta.getList().setModel(jList.getModel());
                } catch (Exception ex) {
                    JOptionPane optionPane = new JOptionPane("Um Erro Ocorreu:\n" + client.getStatus(), JOptionPane.WARNING_MESSAGE);
                    JDialog dialog = optionPane.createDialog("Aviso!");
                    dialog.setAlwaysOnTop(true);
                    dialog.setVisible(true);
                    ex.printStackTrace();
                }
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    client.findByID(Long.parseLong(consulta.getIdField().getText()));
                    List<MyUser> result = new ArrayList<>();
                    result.add(client.getObjResult());
                    JList jList = new JList(new Vector<MyUser>(result));
                    consulta.getList().setModel(jList.getModel());
                } catch (Exception ex) {
                    JOptionPane optionPane = new JOptionPane("Um Erro Ocorreu:\n" + client.getStatus(), JOptionPane.WARNING_MESSAGE);
                    JDialog dialog = optionPane.createDialog("Aviso!");
                    dialog.setAlwaysOnTop(true);
                    dialog.setVisible(true);
                    ex.printStackTrace();
                }
            }
        }));

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
                    consulta.getList().setSelectionMode(1);
                    JList jList = new JList(new Vector<MyUser>(client.getResultList()));
                    consulta.getList().setModel(jList.getModel());

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
