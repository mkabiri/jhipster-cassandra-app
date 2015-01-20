
CREATE TABLE IF NOT EXISTS user (
    id text,
    login text,
    password text,
    firstname text,
    lastname text,
    email text,
    activated boolean,
    langKey text,
    activationKey text,
    authorities set<text>,
    PRIMARY KEY(login)
);

CREATE TABLE IF NOT EXISTS authority (
  name text,
  PRIMARY KEY(name)
)

CREATE TABLE IF NOT EXISTS persistent_token (
    series text,
    token_date timestamp,
    authorities set<text>,
    user_agent text,
    token_value text,
    login text,
    pass text,
    ip_address text,
    PRIMARY KEY(series)
);
