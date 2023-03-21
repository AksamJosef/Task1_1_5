package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {

    private void save(String name, String lastName, byte age) {
//        (name, lastName, age);

    }
    public static void main(String[] args) throws SQLException {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        System.out.println();

        userService.saveUser("Nigmat", "Nigmatullin", (byte) 25);
        userService.saveUser("Ayusha", "Nigmatullina", (byte) 25);
        userService.saveUser("Assaf Josef", "Nigmatullin", (byte) 1);
        userService.saveUser("Jotaro", "Kujo", (byte) 111);
        System.out.println();


        userService.getAllUsers().forEach(System.out::println);
        System.out.println();

        userService.cleanUsersTable();
        System.out.println();

        userService.dropUsersTable();
        System.out.println();



    }
}
