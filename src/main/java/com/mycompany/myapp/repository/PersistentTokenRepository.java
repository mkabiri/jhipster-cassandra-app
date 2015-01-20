package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.PersistentToken;
import com.mycompany.myapp.domain.User;
import org.joda.time.LocalDate;

import java.util.List;

/**
 * Cassandra repository for the PersistentToken entity.
 */
public class PersistentTokenRepository {

    public PersistentToken findOne(String presentedSeries) {
        return null;
    }

    public List<PersistentToken> findByUser(User user) {
        return null;
    }

    public List<PersistentToken> findByTokenDateBefore(LocalDate localDate) {
        return null;
    }

    public void save(PersistentToken token) {

    }

    public void delete(PersistentToken token) {

    }

    public void delete(String decodedSeries) {
    }
}
