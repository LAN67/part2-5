
CREATE TABLE IF NOT EXISTS tpp_ref_account_type
(
	internal_id serial PRIMARY KEY ,
	value VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS tpp_ref_product_class
(
	internal_id serial PRIMARY KEY ,
	value VARCHAR(100) UNIQUE NOT NULL,
	gbi_code VARCHAR(50),
	gbi_name VARCHAR(100),
    product_row_code VARCHAR(50),
    product_row_name VARCHAR(100),
    subclass_code VARCHAR(50),
    subclass_name VARCHAR(100)
);


CREATE TABLE IF NOT EXISTS tpp_ref_product_register_type
(
	internal_id serial PRIMARY KEY ,
	value VARCHAR(100) UNIQUE NOT NULL,
	register_type_name VARCHAR(100) NOT NULL,
    product_class_code VARCHAR(100) NOT NULL,
    register_type_start_date TIMESTAMP,
    register_type_end_date TIMESTAMP,
    account_type VARCHAR(100)
);
ALTER TABLE tpp_ref_product_register_type
ADD FOREIGN KEY (product_class_code) REFERENCES tpp_ref_product_class (value);

ALTER TABLE tpp_ref_product_register_type
ADD FOREIGN KEY (account_type) REFERENCES tpp_ref_account_type (value);

CREATE TABLE IF NOT EXISTS tpp_product_register
(
	id serial PRIMARY KEY ,
	product_id BIGINT,
    type VARCHAR(100) NOT NULL,
    account BIGINT,
    currency_code VARCHAR(30),
    state VARCHAR(50),
    account_number VARCHAR(25)
);

ALTER TABLE tpp_product_register
ADD FOREIGN KEY (type) REFERENCES tpp_ref_product_register_type (value);

CREATE TABLE IF NOT EXISTS account_pool(
    id serial PRIMARY KEY,
    branch_code VARCHAR(50),
    currency_code VARCHAR(30),
    mdm_code VARCHAR(50),
    priority_code VARCHAR(30),
    registry_type_code VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS account(
    id serial PRIMARY KEY,
    account_pool_id integer,
    account_number VARCHAR(25),
    bussy BOOLEAN
);

ALTER TABLE account
ADD FOREIGN KEY (account_pool_id) REFERENCES account_pool (id);
--ON DELETE CASCADE;

CREATE TABLE IF NOT EXISTS tpp_product
(
	id serial PRIMARY KEY,
--	agreement_id BIGINT,
	product_code_id BIGINT,
	client_id BIGINT,
	type VARCHAR(50),
	number VARCHAR(50),
	priority BIGINT,
	date_of_conclusion TIMESTAMP,
	start_date_time TIMESTAMP,
	end_date_time TIMESTAMP,
	days BIGINT,
	penalty_rate DECIMAL,
	nso DECIMAL,
	threshold_amount DECIMAL,
	requisite_type VARCHAR(50),
	interest_rate_type VARCHAR(50),
	tax_rate DECIMAL,
    reasone_close VARCHAR(100),
    state VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS agreement
(
	id serial PRIMARY KEY,
	product_id integer,
	general_agreement_id VARCHAR(50),
	supplementary_agreement_id VARCHAR(50),
	arrangement_type VARCHAR(50),
	sheduler_job_id BIGINT,
	number VARCHAR(50),
    opening_date TIMESTAMP,
    closing_date TIMESTAMP,
    cancel_date TIMESTAMP,
    validity_duration BIGINT,
    cancellation_reason VARCHAR(100),
    status VARCHAR(50),
    interest_calculation_date TIMESTAMP,
    interest_rate DECIMAL,
    coefficient DECIMAL,
    coefficient_action VARCHAR(50),
    minimum_interest_rate DECIMAL,
    minimum_interest_rate_coefficient DECIMAL,
    minimum_interest_rate_coefficient_action VARCHAR(50),
    maximal_interest_rate DECIMAL,
    maximal_interest_rate_coefficient DECIMAL,
    maximal_interest_rate_coefficient_action VARCHAR(50)
);

ALTER TABLE agreement
ADD FOREIGN KEY (product_id) REFERENCES tpp_product (id);




INSERT INTO tpp_ref_account_type (value)
VALUES ('Клиентский'),
       ('Внутрибанковский');

INSERT INTO tpp_ref_product_class (value, gbi_code, gbi_name, product_row_code, product_row_name, subclass_code, subclass_name)
VALUES ('03.012.002', '03', 'Розничный бизнес', '012', 'Драг. металлы', '002', 'Хранение'),
       ('02.001.005', '02', 'Розничный бизнес', '001', 'Сырье', '005', 'Продажа');

INSERT INTO tpp_ref_product_register_type (value
                                           , register_type_name
                                           , product_class_code
                                           , account_type)
VALUES ('03.012.002_47533_ComSoLd', 'Хранение ДМ.', '03.012.002', 'Клиентский'),
       ('02.001.005_45343_CoDowFF', 'Серебро. Выкуп.', '02.001.005', 'Клиентский');
--        ('RKO', 'Расчетный счет', 'RKO', TIMESTAMP '2023-12-03 00:00:00', 'CLIENT_ACCOUNT');

INSERT INTO account_pool (branch_code
                          , currency_code
                          , mdm_code
                          , priority_code
                          , registry_type_code)
VALUES ('0022', '800', '15', '00', '03.012.002_47533_ComSoLd'),
       ('0021', '500', '13', '00', '02.001.005_45343_CoDowFF');

INSERT INTO account(account_pool_id, account_number, bussy)
SELECT id, '475335516415314841861', false
FROM account_pool
WHERE registry_type_code = '03.012.002_47533_ComSoLd';

INSERT INTO account(account_pool_id, account_number, bussy)
SELECT id, '4753321651354151', false
FROM account_pool
WHERE registry_type_code = '03.012.002_47533_ComSoLd';

INSERT INTO account(account_pool_id, account_number, bussy)
SELECT id, '4753352543276345', false
FROM account_pool
WHERE registry_type_code = '03.012.002_47533_ComSoLd';

INSERT INTO account(account_pool_id, account_number, bussy)
SELECT id, '453432352436453276', false
FROM account_pool
WHERE registry_type_code = '02.001.005_45343_CoDowFF';

INSERT INTO account(account_pool_id, account_number, bussy)
SELECT id, '45343221651354151', false
FROM account_pool
WHERE registry_type_code = '02.001.005_45343_CoDowFF';

INSERT INTO account(account_pool_id, account_number, bussy)
SELECT id, '4534352543276345', false
FROM account_pool
WHERE registry_type_code = '02.001.005_45343_CoDowFF';
