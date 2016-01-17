-- Table: "user"

-- DROP TABLE "user";

CREATE TABLE test."user"
(
  firstname character varying(255),
  lastname character varying(255)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE test."user"
  OWNER TO test;
