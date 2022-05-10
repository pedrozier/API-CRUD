/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Util.ApplicationClient;
import View.Login;
import View.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author zier
 */
public class LoginController {

    public static void login(ApplicationClient client, Menu menu) {

        Login login = new Login();
        login.setVisible(true);
        login.setAlwaysOnTop(true);
        login.getLoginBtn().addActionListener((new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    client.doLogin(login.getUsernameField().getText(), login.getPasswordField().getPassword());
                    login.setVisible(false);
                    login.getUsernameField().setText("");
                    login.getPasswordField().setText("");
                    menu.setVisible(true);

                } catch (Exception ex) {
                    JOptionPane optionPane = new JOptionPane("Nome ou Senha Invalidos", JOptionPane.WARNING_MESSAGE);
                    JDialog dialog = optionPane.createDialog("Aviso!");
                    dialog.setAlwaysOnTop(true);
                    dialog.setVisible(true);
                    ex.printStackTrace();
                }
            }
        }));
    }
}
