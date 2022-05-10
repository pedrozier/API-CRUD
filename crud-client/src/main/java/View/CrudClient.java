/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package View;

import Controller.ConsultaController;
import Controller.LoginController;
import Controller.MenuController;
import Controller.RegistroController;
import Controller.UpdateController;
import Util.ApplicationClient;

/**
 *
 * @author zier
 */
public class CrudClient {

    public static void main(String[] args) {

        ApplicationClient client = new ApplicationClient();
        Menu menu = new Menu();
        Registro registro = new Registro();
        Consulta consulta = new Consulta();
        Update update = new Update();

        LoginController.login(client, menu);
        MenuController.menu(client, registro, consulta, menu);
        RegistroController.registro(client, registro);
        UpdateController.update(client, consulta, update);
        ConsultaController.consulta(client, consulta, update);

    }

}
