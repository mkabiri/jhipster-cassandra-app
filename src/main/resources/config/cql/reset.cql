--
-- Reset jhipster keyspace
--

SOURCE 'drop-keyspace.cql'
SOURCE 'create-keyspace.cql'

USE jhipster;

SOURCE 'create-tables.cql'
