package com.mycompany.myapp.repository;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.mycompany.myapp.domain.User;

import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;

/**
 * Cassandra repository for the User entity.
 */
@Repository
public class UserRepository {

    @Inject
    private Session session;

    Mapper<User> mapper;

    private PreparedStatement findOneByActivationKeyStmt;

    private PreparedStatement insertByActivationKeyStmt;

    private PreparedStatement deleteByActivationKeyStmt;

    private PreparedStatement findOneByLoginStmt;

    private PreparedStatement insertByLoginStmt;

    private PreparedStatement deleteByLoginStmt;

    private PreparedStatement findOneByEmailStmt;

    private PreparedStatement insertByEmailStmt;

    private PreparedStatement deleteByEmailStmt;

    @PostConstruct
    public void init() {
        mapper = new MappingManager(session).mapper(User.class);

        findOneByActivationKeyStmt = session.prepare(
            "SELECT id " +
            "FROM user_by_activation_key " +
            "WHERE activation_key = :activation_key");

        insertByActivationKeyStmt = session.prepare(
            "INSERT INTO user_by_activation_key (activation_key, id) " +
            "VALUES (:activation_key, :id)");

        deleteByActivationKeyStmt = session.prepare(
            "DELETE FROM user_by_activation_key " +
            "WHERE activation_key = :activation_key");

        findOneByLoginStmt = session.prepare(
            "SELECT id " +
            "FROM user_by_login " +
            "WHERE login = :login");

        insertByLoginStmt = session.prepare(
            "INSERT INTO user_by_login (login, id) " +
                "VALUES (:login, :id)");

        deleteByLoginStmt = session.prepare(
            "DELETE FROM user_by_login " +
                "WHERE login = :login");

        findOneByEmailStmt = session.prepare(
            "SELECT id " +
            "FROM user_by_email " +
            "WHERE login = :email");

        insertByEmailStmt = session.prepare(
            "INSERT INTO user_by_email (email, id) " +
                "VALUES (:email, :id)");

        deleteByEmailStmt = session.prepare(
            "DELETE FROM user_by_email " +
                "WHERE email = :email");
    }

    public Optional<User> findOneByActivationKey(String activationKey) {
        BoundStatement stmt = findOneByActivationKeyStmt.bind();
        stmt.setString("activation_key", activationKey);
        return findOneFromIndex(stmt);
    }

    public Optional<User> findOneByEmail(String email) {
        BoundStatement stmt = findOneByEmailStmt.bind();
        stmt.setString("email", email);
        return findOneFromIndex(stmt);
    }

    public Optional<User> findOneByLogin(String login) {
        BoundStatement stmt = findOneByLoginStmt.bind();
        stmt.setString("login", login);
        return findOneFromIndex(stmt);
    }

    public List<User> findAllByActivatedIsFalseAndCreatedDateBefore(DateTime dateTime) {
        // TODO
        return null;
    }

    public void save(User user) {
        BatchStatement batch = new BatchStatement();
        batch.add(mapper.saveQuery(user));
        batch.add(deleteByActivationKeyStmt.bind().setString("activation_key", user.getActivationKey()));
        batch.add(deleteByLoginStmt.bind().setString("login", user.getLogin()));
        batch.add(deleteByEmailStmt.bind().setString("email", user.getEmail()));
        batch.add(insertByActivationKeyStmt.bind()
            .setString("activation_key", user.getActivationKey())
            .setString("id", user.getId()));
        batch.add(insertByLoginStmt.bind()
            .setString("login", user.getLogin())
            .setString("id", user.getId()));
        batch.add(insertByEmailStmt.bind()
            .setString("email", user.getEmail())
            .setString("id", user.getId()));
        session.execute(batch);
    }

    public void delete(User user) {
        BatchStatement batch = new BatchStatement();
        batch.add(mapper.deleteQuery(user));
        batch.add(deleteByActivationKeyStmt.bind().setString("activation_key", user.getActivationKey()));
        batch.add(deleteByLoginStmt.bind().setString("login", user.getLogin()));
        batch.add(deleteByEmailStmt.bind().setString("email", user.getEmail()));
        session.execute(batch);
    }

    private Optional<User> findOneFromIndex(BoundStatement stmt) {
        ResultSet rs = session.execute(stmt);
        if (rs.isExhausted()) {
            return Optional.empty();
        }
        return Optional.ofNullable(rs.one().getString("id"))
            .map(id -> Optional.ofNullable(mapper.get(id)))
            .get();
    }
}
