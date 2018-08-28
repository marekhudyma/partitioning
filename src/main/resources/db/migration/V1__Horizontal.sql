CREATE TABLE employer
(
  id                  SERIAL PRIMARY KEY,
  created             TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT clock_timestamp(),
  updated             TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT clock_timestamp(),
  firstname           VARCHAR NOT NULL,
  lastname            VARCHAR NOT NULL,
  employer_details_id BIGINT
);

CREATE TABLE employer_details (
    id               SERIAL PRIMARY KEY,
    created          TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT clock_timestamp(),
    updated          TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT clock_timestamp(),
    bank_account     VARCHAR,
    street_number    VARCHAR
);

ALTER TABLE ONLY employer
ADD CONSTRAINT employer_employer_details_id_fkey FOREIGN KEY (employer_details_id) REFERENCES employer_details(id) ON UPDATE RESTRICT ON DELETE RESTRICT;



