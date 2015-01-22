package com.mycompany.myapp.repository;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.mycompany.myapp.domain.PersistentToken;
import com.mycompany.myapp.domain.User;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Cassandra repository for the PersistentToken entity.
 */
@Repository
public class PersistentTokenRepository {

    @Inject
    private Session session;

    Mapper<PersistentToken> mapper;

    private PreparedStatement findPersistentTokenSeriesByUserIdStmt;

    @PostConstruct
    public void init() {
        mapper = new MappingManager(session).mapper(PersistentToken.class);

        findPersistentTokenSeriesByUserIdStmt = session.prepare(
            "SELECT persistent_token_series " +
            "FROM persistent_token_by_user " +
            "WHERE user_id = :user_id");
    }

    public PersistentToken findOne(String presentedSeries) {
        return mapper.get(presentedSeries);
    }

    public List<PersistentToken> findByUser(User user) {
        BoundStatement stmt = findPersistentTokenSeriesByUserIdStmt.bind();
        stmt.setString("user_id", user.getId());
        ResultSet rs = session.execute(stmt);
        List<PersistentToken> persistentTokens = new ArrayList<>();
        rs.all().stream()
            .map(row -> row.getString("persistent_token_series"))
            .map(token -> mapper.get(token))
            .forEach(persistentTokens::add);

        return persistentTokens;
    }

    public List<PersistentToken> findByTokenDateBefore(LocalDate localDate) {
        // TODO
        return null;
    }

    public void save(PersistentToken token) {
        mapper.save(token);
    }

    public void delete(PersistentToken token) {
        mapper.delete(token);
    }

    public void delete(String decodedSeries) {
        mapper.delete(decodedSeries);
    }
}
