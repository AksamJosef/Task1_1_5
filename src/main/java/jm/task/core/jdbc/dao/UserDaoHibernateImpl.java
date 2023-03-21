package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS User (\n" +
                "\tid INTEGER AUTO_INCREMENT PRIMARY KEY,\n" +
                "    name VARCHAR (40),\n" +
                "    lastName VARCHAR (40),\n" +
                "    age INTEGER\n" +
                ")";
        try (SessionFactory sessionFactory = Util.getSession()) {
            Session session = sessionFactory.openSession();

            session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            session.getTransaction().commit();

            System.out.println("DB created");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS User";
        try (SessionFactory sessionFactory = Util.getSession()) {
            Session session = sessionFactory.openSession();

            session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            session.getTransaction().commit();

            System.out.println("DB dropped");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        try (SessionFactory sessionFactory = Util.getSession()) {
            Session session = sessionFactory.openSession();

            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();

            System.out.println("User with name " + name + " was added");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        String hql = "DELETE FROM User WHERE id = :id";
        try (SessionFactory sessionFactory = Util.getSession()) {
            Session session = sessionFactory.openSession();

            session.beginTransaction();
            session.createQuery(hql)
                    .setString("id", String.valueOf(id))
                    .executeUpdate();
            session.getTransaction().commit();

            System.out.println("User with id " + id + " was removed!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        String hql = "FROM User";
        List<User> userList = new ArrayList<>();
        try (SessionFactory sessionFactory = Util.getSession()) {
            Session session = sessionFactory.openSession();
            Query query = session.createQuery(hql);
            userList = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        String hql = "DELETE FROM User";
        try (SessionFactory sessionFactory = Util.getSession()) {
            Session session = sessionFactory.openSession();

            session.beginTransaction();
            session.createQuery(hql)
                    .executeUpdate();
            session.getTransaction().commit();

            System.out.println("Deleted all rows from TABLE Users");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
