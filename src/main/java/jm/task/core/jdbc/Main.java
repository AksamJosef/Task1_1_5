package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.SQLException;
import java.util.List;

public class Main {

    private void save(String name, String lastName, byte age) {
//        (name, lastName, age);

    }
    public static void main(String[] args) throws SQLException {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();



        userService.saveUser("Nigmat", "Nigmatullin", (byte) 25);
        userService.saveUser("Ayusha", "Nigmatullina", (byte) 25);
        userService.saveUser("Assaf Josef", "Nigmatullin", (byte) 1);
        userService.saveUser("Jotaro", "Kujo", (byte) 111);

        userService.getAllUsers().stream()
                        .forEach(System.out::println);

        userService.cleanUsersTable();
        userService.dropUsersTable();


    }
}
