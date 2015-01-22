package com.mycompany.myapp;

import com.datastax.driver.core.ResultSet;
import com.mycompany.myapp.config.Constants;
import com.mycompany.myapp.config.apidoc.SwaggerConfiguration;
import org.cassandraunit.CassandraCQLUnit;
import org.cassandraunit.dataset.cql.ClassPathCQLDataSet;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.autoconfigure.MetricFilterAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.MetricRepositoryAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class CassandraKeyspaceTest {

    protected final Logger log = LoggerFactory.getLogger(this.getClass().getCanonicalName());

    @ClassRule
    public static CassandraCQLUnit cassandra = new CassandraCQLUnit(new ClassPathCQLDataSet("config/cql/create-tables.cql", true, "jhipster"));

    @Inject
    private CassandraOperations cassandraOps;

    @Test
    public void shouldHaveParcelTableCreated() throws Exception {
        ResultSet result = cassandraOps.query("select * from user");
        assertThat(result.all()).isEmpty();
    }
}
