package com.mycompany.myapp.repository;

import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.mycompany.myapp.domain.PersistentToken;
import com.mycompany.myapp.domain.User;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

/**
 * Cassandra repository for the PersistentToken entity.
 */
@Repository
public class PersistentTokenRepository {

    @Inject
    private Session session;

    Mapper<PersistentToken> mapper;

    @PostConstruct
    public void init() {
        mapper = new MappingManager(session).mapper(PersistentToken.class);

    }

    public PersistentToken findOne(String presentedSeries) {
        return mapper.get(presentedSeries);
    }

    public List<PersistentToken> findByUser(User user) {
        return null;
    }

    public List<PersistentToken> findByTokenDateBefore(LocalDate localDate) {
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
