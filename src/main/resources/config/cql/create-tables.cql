
CREATE TABLE IF NOT EXISTS user (
    id text,
    login text,
    password text,
    firstname text,
    lastname text,
    email text,
    activated boolean,
    lang_key text,
    activation_key text,
    authorities set<text>,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS user_by_login (
    login text,
    id text,
    PRIMARY KEY(login, id)
);

CREATE TABLE IF NOT EXISTS user_by_email (
    email text,
    id text,
    PRIMARY KEY(email, id)
);

CREATE TABLE IF NOT EXISTS user_by_activation_key (
    activation_key text,
    id text,
    PRIMARY KEY(activation_key, id)
);

CREATE TABLE IF NOT EXISTS activation_key_by_creation_date (
    creation_date timeuuid,
    activation_key text,
    PRIMARY KEY(creation_date, activation_key)
);

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
