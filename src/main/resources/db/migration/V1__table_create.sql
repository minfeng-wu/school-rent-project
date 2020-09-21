CREATE TABLE account (

    id              BIGSERIAL NOT NULL,
	school_id       BIGSERIAL,
    first_name      VARCHAR(30),
    last_name       VARCHAR(30),
	address         VARCHAR(150),
	account_name    VARCHAR(30),
	password       VARCHAR(30),
	balance        NUMERIC(10, 2)
);

ALTER TABLE account ADD CONSTRAINT account_pk PRIMARY KEY ( id );


CREATE TABLE school(
	id BIGSERIAL,
	name  VARCHAR(30),
	city  VARCHAR(30),
	state  VARCHAR(30),
	address         VARCHAR(150)

);

ALTER TABLE school ADD CONSTRAINT school_pk PRIMARY KEY ( id );


CREATE TABLE item(
	id  BIGSERIAL NOT NULL,
	name VARCHAR(30),
	avaliability boolean,
	brand VARCHAR(30),
	category VARCHAR(30),
	price NUMERIC(10, 2)
);

ALTER TABLE item ADD CONSTRAINT item_pk PRIMARY KEY ( id );



CREATE TABLE order_history (
	id BIGSERIAL NOT NULL,
	account_id BIGSERIAL NOT NULL,
	item_id BIGSERIAL NOT NULL,
	start_day date ,
	end_day date
);

ALTER TABLE order_history ADD CONSTRAINT order_history_pk PRIMARY KEY ( id );
ALTER TABLE order_history
    ADD CONSTRAINT order_history_account_fk FOREIGN KEY ( account_id )
        REFERENCES account ( id );
ALTER TABLE order_history
    ADD CONSTRAINT order_history_item_fk FOREIGN KEY ( item_id )
        REFERENCES item ( id );
ALTER TABLE account
    ADD CONSTRAINT account_school_fk FOREIGN KEY ( school_id )
        REFERENCES school ( id );

