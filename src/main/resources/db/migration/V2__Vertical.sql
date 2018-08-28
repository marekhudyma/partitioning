CREATE SEQUENCE global_sequence START WITH 1 INCREMENT BY 10 NO MINVALUE NO MAXVALUE CACHE 1;

CREATE TABLE accounts
(
  id               UUID PRIMARY KEY,
  created          TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT clock_timestamp(),
  updated          TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT clock_timestamp(),
  name             VARCHAR NOT NULL,
  additional_info  VARCHAR NOT NULL,
  scoring          INTEGER NOT NULL
);

CREATE UNIQUE INDEX accounts_idx ON accounts(name);

CREATE TABLE transactions (
    sequence       BIGINT DEFAULT nextval('global_sequence'::regclass) NOT NULL,
    created        TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT clock_timestamp(),
    updated        TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT clock_timestamp(),
    account_id     UUID NOT NULL,
    amount         NUMERIC NOT NULL
) PARTITION BY RANGE (created);

CREATE TABLE transactions_2015 PARTITION OF transactions FOR VALUES FROM ('2015-01-01 00:00:00') TO ('2016-01-01 00:00:00');
CREATE TABLE transactions_2016 PARTITION OF transactions FOR VALUES FROM ('2016-01-01 00:00:00') TO ('2017-01-01 00:00:00');
CREATE TABLE transactions_2017 PARTITION OF transactions FOR VALUES FROM ('2017-01-01 00:00:00') TO ('2018-01-01 00:00:00');
CREATE TABLE transactions_2018 PARTITION OF transactions FOR VALUES FROM ('2018-01-01 00:00:00') TO ('2019-01-01 00:00:00');
CREATE TABLE transactions_2019 PARTITION OF transactions FOR VALUES FROM ('2019-01-01 00:00:00') TO ('2020-01-01 00:00:00');

ALTER TABLE ONLY transactions_2015
ADD CONSTRAINT transactions_2015_account_id_fkey FOREIGN KEY (account_id) REFERENCES accounts(id) ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE ONLY transactions_2016
ADD CONSTRAINT transactions_2016_account_id_fkey FOREIGN KEY (account_id) REFERENCES accounts(id) ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE ONLY transactions_2017
ADD CONSTRAINT transactions_2017_account_id_fkey FOREIGN KEY (account_id) REFERENCES accounts(id) ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE ONLY transactions_2018
ADD CONSTRAINT transactions_2018_account_id_fkey FOREIGN KEY (account_id) REFERENCES accounts(id) ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE ONLY transactions_2019
ADD CONSTRAINT transactions_2019_account_id_fkey FOREIGN KEY (account_id) REFERENCES accounts(id) ON UPDATE RESTRICT ON DELETE RESTRICT;

CREATE INDEX ON transactions_2015 (account_id);
CREATE INDEX ON transactions_2016 (account_id);
CREATE INDEX ON transactions_2017 (account_id);
CREATE INDEX ON transactions_2018 (account_id);
CREATE INDEX ON transactions_2019 (account_id);