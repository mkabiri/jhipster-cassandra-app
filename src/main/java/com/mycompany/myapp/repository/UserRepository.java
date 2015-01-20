package com.mycompany.myapp.repository;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import com.mycompany.myapp.domain.User;

import org.joda.time.DateTime;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * Cassandra repository for the User entity.
 */
public class UserRepository {

    @Inject
    private Session session;

    private PreparedStatement findOneByLoginStmt;

    private PreparedStatement findOneStmt;

    private PreparedStatement saveStmt;

    private PreparedStatement deleteStmt;

    @PostConstruct
    public void init() {
        findOneByLoginStmt = session.prepare("SELECT " +
            "id " +
            "FROM user_by_login " +
            "WHERE login = :login");

        findOneStmt = session.prepare("SELECT " +
            "id," +
            "login," +
            "password," +
            "firstname," +
            "lastname," +
            "email," +
            "activated," +
            "langKey," +
            "activationKey," +
            "authorities " +
            "FROM user " +
            "WHERE id = :id");

        saveStmt = session.prepare("INSERT INTO user (" +
            "id," +
            "login," +
            "password," +
            "firstname," +
            "lastname," +
            "email," +
            "activated," +
            "langKey," +
            "activationKey," +
            "authorities" +
            ") VALUES (" +
            ":id," +
            ":login," +
            ":password," +
            ":firstname," +
            ":lastname," +
            ":email," +
            ":activated," +
            ":langKey," +
            ":activationKey," +
            ":authorities" +
            ")");

        deleteStmt = session.prepare("DELETE FROM user WHERE id = :id");
    }

    public Optional<User> findOneByActivationKey(String activationKey) {
        return null;
    }

    public List<User> findAllByActivatedIsFalseAndCreatedDateBefore(DateTime dateTime) {
        return null;
    }

    public Optional<User> findOneByEmail(String email) {
        return null;
    }

    public Optional<User> findOneByLogin(String login) {
        BoundStatement stmt = findOneByLoginStmt.bind();
        stmt.setString("login", login);
        session.execute(stmt);

        // TODO
        return null;
    }

    public void save(User user) {
        BoundStatement stmt = saveStmt.bind();
        stmt.setString("id", user.getId());
        stmt.setString("login", user.getLogin());
        stmt.setString("password", user.getPassword());
        stmt.setString("firstname", user.getFirstName());
        stmt.setString("lastname", user.getLastName());
        stmt.setString("email", user.getEmail());
        stmt.setBool("activated", user.getActivated());
        stmt.setString("langKey", user.getLangKey());
        stmt.setString("activationKey", user.getActivationKey());
        stmt.setSet("authorities", user.getAuthorities());
        session.execute(stmt);
    }

    public void delete(User user) {
        BoundStatement stmt = deleteStmt.bind();
        stmt.setString("id", user.getId());
        session.execute(stmt);
    }
}
