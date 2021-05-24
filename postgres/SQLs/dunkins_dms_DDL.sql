-- dms.dms_activity_audit_seq definition

-- DROP SEQUENCE dms.dms_activity_audit_seq;

CREATE SEQUENCE dms.dms_activity_audit_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


-- dms.dms_activity_seq definition

-- DROP SEQUENCE dms.dms_activity_seq;

CREATE SEQUENCE dms.dms_activity_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 100000
	CACHE 1
	NO CYCLE;


-- dms.dms_common_error_table_error_id_seq definition

-- DROP SEQUENCE dms.dms_common_error_table_error_id_seq;

CREATE SEQUENCE dms.dms_common_error_table_error_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


-- dms.dms_common_error_table_error_id_seq1 definition

-- DROP SEQUENCE dms.dms_common_error_table_error_id_seq1;

CREATE SEQUENCE dms.dms_common_error_table_error_id_seq1
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


-- dms.dms_common_error_table_error_id_seq2 definition

-- DROP SEQUENCE dms.dms_common_error_table_error_id_seq2;

CREATE SEQUENCE dms.dms_common_error_table_error_id_seq2
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


-- dms.dms_new_activity_seq definition

-- DROP SEQUENCE dms.dms_new_activity_seq;

CREATE SEQUENCE dms.dms_new_activity_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 2000
	CACHE 1
	NO CYCLE;


-- dms.dms_schedule_audit_seq definition

-- DROP SEQUENCE dms.dms_schedule_audit_seq;

CREATE SEQUENCE dms.dms_schedule_audit_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


-- dms.dms_schedule_technician_seq definition

-- DROP SEQUENCE dms.dms_schedule_technician_seq;

CREATE SEQUENCE dms.dms_schedule_technician_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 100000000
	CACHE 1
	NO CYCLE;


-- dms.dms_store_schedules_seq definition

-- DROP SEQUENCE dms.dms_store_schedules_seq;

CREATE SEQUENCE dms.dms_store_schedules_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 20000000
	CACHE 1
	NO CYCLE;


-- dms.dms_store_terminal_data_store_terminal_id_seq definition

-- DROP SEQUENCE dms.dms_store_terminal_data_store_terminal_id_seq;

CREATE SEQUENCE dms.dms_store_terminal_data_store_terminal_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;


-- dms.dms_technician_audit_seq definition

-- DROP SEQUENCE dms.dms_technician_audit_seq;

CREATE SEQUENCE dms.dms_technician_audit_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
	
	-- dms.dms_activities_master definition

-- Drop table

-- DROP TABLE dms.dms_activities_master;

CREATE TABLE dms.dms_activities_master (
	brand varchar NULL,
	activity_id varchar NOT NULL,
	activity_details varchar NULL,
	pre_requisite varchar NULL,
	reference varchar NULL,
	createts timestamp NULL,
	modifyts timestamp NULL,
	activity_type varchar NOT NULL,
	create_user_id varchar NULL,
	modify_user_id varchar NULL,
	CONSTRAINT dms_activities_master_pkey PRIMARY KEY (activity_id)
);
CREATE UNIQUE INDEX dms_activities_master_activity_id_idx ON dms.dms_activities_master USING btree (activity_id);


-- dms.dms_activity_audit definition

-- Drop table

-- DROP TABLE dms.dms_activity_audit;

CREATE TABLE dms.dms_activity_audit (
	activity_audit_id varchar(255) NOT NULL,
	activity_details varchar(255) NULL,
	activity_id varchar(255) NOT NULL,
	audit_id varchar(255) NULL,
	createts timestamp NULL,
	create_user_id varchar(255) NULL,
	end_date timestamp NULL,
	login_id varchar(255) NULL,
	modifyts timestamp NULL,
	modify_user_id varchar(255) NULL,
	pre_requisite varchar(255) NULL,
	reference varchar(255) NULL,
	schedule_id varchar(255) NULL,
	start_date timestamp NULL,
	status varchar(255) NULL,
	CONSTRAINT dms_activity_audit_pkey PRIMARY KEY (activity_audit_id)
);


-- dms.dms_common_error_table definition

-- Drop table

-- DROP TABLE dms.dms_common_error_table;

CREATE TABLE dms.dms_common_error_table (
	error_id int8 NOT NULL GENERATED ALWAYS AS IDENTITY,
	error_code varchar NULL,
	error_type varchar NULL,
	error_description varchar NULL,
	error_context varchar NULL,
	interface varchar NULL,
	reference varchar NULL,
	createts timestamp NULL,
	modifyts timestamp NULL,
	create_user_id varchar NOT NULL,
	modify_user_id varchar NOT NULL,
	create_ts timestamp NULL,
	modify_ts timestamp NULL,
	create_userid varchar(255) NULL,
	CONSTRAINT dms_common_error_table_pkey PRIMARY KEY (error_id)
);


-- dms.dms_host_software_version definition

-- Drop table

-- DROP TABLE dms.dms_host_software_version;

CREATE TABLE dms.dms_host_software_version (
	acc_no varchar NULL,
	store_id varchar NOT NULL,
	host_name varchar NOT NULL,
	terminal_type varchar NULL,
	group_id varchar NULL,
	group_name varchar NULL,
	software_name varchar NOT NULL,
	"version" varchar NULL,
	createts timestamp NULL,
	modifyts timestamp NULL,
	create_user_id varchar NULL,
	modify_user_id varchar NULL,
	CONSTRAINT dms_host_software_version_pk PRIMARY KEY (host_name, software_name)
);

CREATE INDEX dms_host_software_version_acc_no_idx ON public.dms_host_software_version USING btree (acc_no);

-- dms.dms_master_data definition

-- Drop table

-- DROP TABLE dms.dms_master_data;

CREATE TABLE dms.dms_master_data (
	brand varchar NULL,
	entity varchar NOT NULL,
	"type" varchar NOT NULL,
	id varchar NOT NULL,
	value varchar NULL,
	"desc" varchar NULL,
	createts timestamp NULL,
	modifyts timestamp NULL,
	create_user_id varchar NULL,
	modify_user_id varchar NULL,
	CONSTRAINT dms_master_data_pk PRIMARY KEY (entity, type, id)
);


-- dms.dms_schedule_audit definition

-- Drop table

-- DROP TABLE dms.dms_schedule_audit;

CREATE TABLE dms.dms_schedule_audit (
	audit_id varchar NOT NULL,
	brand varchar NULL,
	audit_status varchar(255) NULL,
	schedule_end_dt timestamp NULL,
	schedule_id varchar(255) NULL,
	schedule_notes varchar(255) NULL,
	schedule_start_dt timestamp NULL,
	schedule_status varchar(255) NULL,
	user_id varchar(255) NULL,
	software_id varchar(255) NULL,
	status_notes varchar(255) NULL,
	"version" varchar(255) NULL,
	pre_req_end_dt timestamp NULL,
	pre_req_status varchar(255) NULL,
	createts timestamp NULL,
	modifyts timestamp NULL,
	create_user_id varchar NULL,
	modify_user_id varchar NULL,
	CONSTRAINT dms_schedule_audit_pkey PRIMARY KEY (audit_id)
);

 

CREATE TABLE public.dms_store_schedules (
	brand varchar NOT NULL,
	store_id varchar NOT NULL,
	schedule_id varchar NOT NULL,
	software_id varchar NOT NULL,
	software_name varchar NULL,
	status varchar NULL,
	schedule_start_date timestamp NULL,
	schedule_end_date timestamp NULL,
	notes varchar NULL,
	schedule_original_date timestamp NULL,
	status_notes varchar(255) NULL,
	pre_req_end_dt timestamp NULL,
	pre_req_status varchar(255) NULL,
	"version" varchar NULL,
	login_id varchar NULL,
	createts timestamp NULL,
	modifyts timestamp NULL,
	create_user_id varchar NULL,
	modify_user_id varchar NULL,
	CONSTRAINT dms_store_schedules_pkey PRIMARY KEY (schedule_id)
);


-- dms.dms_software_master definition

-- Drop table

-- DROP TABLE dms.dms_software_master;

CREATE TABLE dms.dms_software_master (
	brand varchar NULL,
	software_id varchar NOT NULL,
	software_name varchar NOT NULL,
	"version" varchar NULL,
	createts timestamp NULL,
	modifyts timestamp NULL,
	create_user_id varchar NULL,
	modify_user_id varchar NULL,
	description varchar NULL
);


-- dms.dms_store_terminal_data definition

-- Drop table

-- DROP TABLE dms.dms_store_terminal_data;

CREATE TABLE dms.dms_store_terminal_data (
	store_terminal_id varchar NOT NULL,
	brand varchar NULL,
	store_id varchar NOT NULL,
	terminal_type varchar NULL,
	group_id varchar NULL,
	terminal_id varchar NOT NULL,
	company_id varchar NULL,
	description varchar NULL,
	hoststatechangedate timestamp NULL,
	ishostonline varchar NULL,
	terminal_desc varchar NULL,
	group_name varchar NULL,
	createts timestamp NULL,
	modifyts timestamp NULL,
	create_user_id varchar NULL,
	modify_user_id varchar NULL,
	CONSTRAINT dms_store_terminal_data_pk PRIMARY KEY (terminal_id)
);


-- dms.dms_storemaster definition

-- Drop table

-- DROP TABLE dms.dms_storemaster;

CREATE TABLE dms.dms_storemaster (
	brand varchar NULL,
	dma_code varchar NOT NULL,
	dma_desc varchar NULL,
	pfcntr varchar NOT NULL,
	opertnl_stat_code varchar NOT NULL,
	latitude float8 NULL,
	longitude float8 NULL,
	legal_entity_name varchar NULL,
	city varchar NULL,
	state varchar NULL,
	zipcode varchar NULL,
	county varchar NULL,
	country varchar NULL,
	createts timestamp NULL,
	modifyts timestamp NULL,
	create_user_id varchar NULL,
	modify_user_id varchar NULL
);
CREATE UNIQUE INDEX dms_storemaster_pfcntr_idx ON dms.dms_storemaster USING btree (pfcntr);


-- dms.dms_technician_audit definition

-- Drop table

-- DROP TABLE dms.dms_technician_audit;

CREATE TABLE dms.dms_technician_audit (
	technician_audit_id varchar(255) NOT NULL,
	technician_id varchar(255) NULL,
	schedule_id varchar(255) NULL,
	audit_id varchar(255) NULL,
	createts timestamp NULL,
	modifyts timestamp NULL,
	create_user_id varchar(255) NULL,
	modify_user_id varchar(255) NULL,
	CONSTRAINT dms_technician_audit_pkey PRIMARY KEY (technician_audit_id)
);


-- dms.dms_user_access_details definition

-- Drop table

-- DROP TABLE dms.dms_user_access_details;

CREATE TABLE dms.dms_user_access_details (
	login_id varchar NOT NULL,
	brand varchar NOT NULL,
	"role" varchar NULL,
	createts timestamp NULL,
	modifyts timestamp NULL,
	create_user_id varchar NULL,
	modify_user_id varchar NULL
);


-- dms.dms_schedule_technician_assignment_details definition

-- Drop table

-- DROP TABLE dms.dms_schedule_technician_assignment_details;

CREATE TABLE dms.dms_schedule_technician_assignment_details (
	schedule_technician_id varchar NOT NULL,
	schedule_id varchar NOT NULL,
	technician_id varchar NOT NULL,
	acitivity_id varchar NULL,
	create_user_id varchar NULL,
	modify_user_id varchar NULL,
	createts timestamp NULL,
	modifyts timestamp NULL,
	CONSTRAINT dms_schedule_technician_assignment_details_pkey PRIMARY KEY (schedule_technician_id)
);


-- dms.dms_store_schedule_activities definition

-- Drop table

-- DROP TABLE dms.dms_store_schedule_activities;

CREATE TABLE dms.dms_store_schedule_activities (
	activity_schedule_id varchar NOT NULL,
	schedule_id varchar NULL,
	activity_id varchar NOT NULL,
	start_date timestamp NULL,
	end_date timestamp NULL,
	status varchar NOT NULL,
	login_id varchar NULL,
	activity_details varchar NULL,
	pre_requisite varchar NULL,
	reference varchar NULL,
	createts timestamp NULL,
	modifyts timestamp NULL,
	create_user_id varchar NULL,
	modify_user_id varchar NULL,
	CONSTRAINT dms_store_schedule_activities_pkey PRIMARY KEY (activity_schedule_id)
);


-- dms.dms_schedule_technician_assignment_details foreign keys

ALTER TABLE dms.dms_schedule_technician_assignment_details ADD CONSTRAINT fkltv3a2s2f1qgh5twrtq5ptxg1 FOREIGN KEY (schedule_id) REFERENCES dms_store_schedules(schedule_id);


-- dms.dms_store_schedule_activities foreign keys

ALTER TABLE dms.dms_store_schedule_activities ADD CONSTRAINT fk98dryodojs2j8uvvsbs8fsk12 FOREIGN KEY (schedule_id) REFERENCES dms_store_schedules(schedule_id);