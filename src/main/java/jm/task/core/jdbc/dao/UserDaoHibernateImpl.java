package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

import static org.hibernate.resource.transaction.spi.TransactionStatus.*;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    private static final SessionFactory sessionFactory = Util.getSession();

    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS User (\n" +
                "\tid INTEGER AUTO_INCREMENT PRIMARY KEY,\n" +
                "    name VARCHAR (40),\n" +
                "    lastName VARCHAR (40),\n" +
                "    age INTEGER\n" +
                ")";
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();

        try (session) {
            transaction.begin();
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();

            System.out.println("DB created");
        } catch (Exception e) {
            if (transaction.getStatus() == ACTIVE || transaction.getStatus() == MARKED_ROLLBACK) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS User";

        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();

        try (session) {
            transaction.begin();
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();

            System.out.println("DB dropped");

        } catch (Exception e) {
            if (transaction.getStatus() == ACTIVE || transaction.getStatus() == MARKED_ROLLBACK) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();

        try (session) {
            transaction.begin();
            session.save(new User(name, lastName, age));
            transaction.commit();

            System.out.println("User with name " + name + " was added");
        } catch (Exception e) {
            if (transaction.getStatus() == ACTIVE || transaction.getStatus() == MARKED_ROLLBACK) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        String hql = "DELETE FROM User WHERE id = :id";

        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();

        try (session) {
            transaction.begin();
            session.createQuery(hql)
                    .setString("id", String.valueOf(id))
                    .executeUpdate();
            transaction.commit();

            System.out.println("User with id " + id + " was removed!");
        } catch (Exception e) {
            if (transaction.getStatus() == ACTIVE || transaction.getStatus() == MARKED_ROLLBACK) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        String hql = "FROM User";
        List<User> userList = new ArrayList<>();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();

        try (session) {
            Query query = session.createQuery(hql);
            userList = query.getResultList();
        } catch (Exception e) {
            if (transaction.getStatus() == ACTIVE || transaction.getStatus() == MARKED_ROLLBACK) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        String hql = "DELETE FROM User";

        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();

        try (session) {
            transaction.begin();
            session.createQuery(hql)
                    .executeUpdate();

            transaction.commit();

            System.out.println("Deleted all rows from TABLE Users");

        } catch (Exception e) {
            if (transaction.getStatus() == ACTIVE || transaction.getStatus() == MARKED_ROLLBACK) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
