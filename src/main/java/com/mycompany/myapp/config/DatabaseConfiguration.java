package com.mycompany.myapp.config;

import org.springframework.cassandra.config.java.AbstractClusterConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@EnableCassandraRepositories("com.mycompany.myapp.repository")
public class DatabaseConfiguration extends AbstractClusterConfiguration {
}
