-- public.dms_activities_master definition

-- Drop table

-- DROP TABLE public.dms_activities_master;

CREATE TABLE public.dms_activities_master (
	activity_id varchar NOT NULL,
	activity_details varchar NULL,
	pre_requisite varchar NULL,
	reference varchar NULL,
	createts timestamp NULL,
	modifyts timestamp NULL,
	activity_type varchar NOT NULL,
	createuserid varchar NULL,
	modifyuserid varchar NULL,
	CONSTRAINT dms_activities_master_pkey PRIMARY KEY (activity_id)
);
CREATE UNIQUE INDEX dms_activities_master_activity_id_idx ON public.dms_activities_master USING btree (activity_id);


-- public.dms_host_software_version definition

-- Drop table

-- DROP TABLE public.dms_host_software_version;

CREATE TABLE public.dms_host_software_version (
	store_id varchar NULL,
	host_name varchar NULL,
	software_id varchar NULL,
	"version" varchar NULL,
	createts timestamp NOT NULL,
	modifyts timestamp NOT NULL
);
CREATE UNIQUE INDEX dms_host_software_version_host_name_idx ON public.dms_host_software_version USING btree (host_name);


-- public.dms_master_data definition

-- Drop table

-- DROP TABLE public.dms_master_data;

CREATE TABLE public.dms_master_data (
	entity varchar NOT NULL,
	"type" varchar NOT NULL,
	id varchar NOT NULL,
	value varchar NULL,
	"desc" varchar NULL,
	createts timestamp NULL,
	modifyts timestamp NULL,
	CONSTRAINT dms_master_data_pk PRIMARY KEY (entity, type, id)
);


-- public.dms_schedule_technician_assignment_details definition

-- Drop table

-- DROP TABLE public.dms_schedule_technician_assignment_details;

CREATE TABLE public.dms_schedule_technician_assignment_details (
	schedule_id varchar NOT NULL,
	technician_id varchar NOT NULL,
	acitivity_id varchar NULL,
	createuserid varchar NULL,
	modifyuserid varchar NULL,
	createts timestamp NULL,
	modifyts timestamp NULL,
	schedule_technician_id varchar NOT NULL,
	CONSTRAINT dms_schedule_technician_assignment_details_pkey PRIMARY KEY (schedule_technician_id)
);


-- public.dms_store_schedule_activities definition

-- Drop table

-- DROP TABLE public.dms_store_schedule_activities;

CREATE TABLE public.dms_store_schedule_activities (
	activity_id varchar NOT NULL,
	start_date timestamp NULL,
	end_date timestamp NULL,
	status varchar NOT NULL,
	createts timestamp NULL,
	modifyts timestamp NULL,
	schedule_id varchar NULL,
	login_id varchar NULL,
	activity_details varchar NULL,
	pre_requisite varchar NULL,
	reference varchar NULL,
	createuserid varchar NULL,
	modifyuserid varchar NULL,
	activity_schedule_id varchar NOT NULL,
	CONSTRAINT dms_store_schedule_activities_pkey PRIMARY KEY (activity_schedule_id)
);


-- public.dms_store_schedules definition

-- Drop table

-- DROP TABLE public.dms_store_schedules;

CREATE TABLE public.dms_store_schedules (
	schedule_id varchar NOT NULL,
	store_id varchar NOT NULL,
	software_id varchar NOT NULL,
	"version" varchar NULL,
	login_id varchar NULL,
	software_name varchar NULL,
	createts timestamp NULL,
	modifyts timestamp NULL,
	schedule_dt timestamp NULL DEFAULT now(),
	status varchar NULL,
	schedule_start_date timestamp NULL,
	schedule_end_date timestamp NULL,
	notes varchar NULL,
	createuserid varchar NULL,
	modifyuserid varchar NULL,
	CONSTRAINT dms_store_schedules_pkey PRIMARY KEY (schedule_id)
);


-- public.dms_store_software_master definition

-- Drop table

-- DROP TABLE public.dms_store_software_master;

CREATE TABLE public.dms_store_software_master (
	store_id varchar NOT NULL,
	software_id varchar NOT NULL,
	software_name varchar NOT NULL,
	"version" varchar NULL
);
CREATE UNIQUE INDEX dms_store_softwares_store_id_idx ON public.dms_store_software_master USING btree (store_id, software_id);


-- public.dms_store_terminal_data definition

-- Drop table

-- DROP TABLE public.dms_store_terminal_data;

CREATE TABLE public.dms_store_terminal_data (
	store_id varchar NOT NULL,
	group_id varchar NULL,
	terminal_id varchar NOT NULL,
	account_id varchar NULL,
	description varchar NULL,
	hoststatechangedate timestamp NULL,
	ishostonline varchar NULL,
	createts timestamp NULL,
	modifyts timestamp NULL,
	terminal_desc varchar NULL,
	group_name varchar NULL,
	terminal_type varchar NULL,
	store_terminal_id serial NOT NULL,
	CONSTRAINT dms_store_terminal_data_pkey PRIMARY KEY (store_terminal_id)
);
CREATE INDEX dms_store_terminal_data_terminal_type_idx ON public.dms_store_terminal_data USING btree (terminal_type, store_id);


-- public.dms_storemaster definition

-- Drop table

-- DROP TABLE public.dms_storemaster;

CREATE TABLE public.dms_storemaster (
	dma_code varchar NOT NULL,
	dma_desc varchar NULL,
	pfcntr varchar NOT NULL,
	opertnl_stat_code varchar NOT NULL,
	latitude varchar NULL,
	longitude varchar NULL,
	open_brands_desc varchar NULL,
	legal_entity_name varchar NULL,
	createts timestamp NOT NULL,
	modifyts timestamp NOT NULL,
	city varchar NULL,
	state varchar NULL,
	zipcode varchar NULL,
	country varchar NULL,
	county varchar NULL
);
CREATE UNIQUE INDEX dms_storemaster_pfcntr_idx ON public.dms_storemaster USING btree (pfcntr);


-- public.dms_user_store_access_details definition

-- Drop table

-- DROP TABLE public.dms_user_store_access_details;

CREATE TABLE public.dms_user_store_access_details (
	store_id varchar NOT NULL,
	login_id varchar NOT NULL,
	createts timestamp NOT NULL,
	modifyts timestamp NOT NULL
);
CREATE UNIQUE INDEX dms_user_store_access_details_store_id_idx ON public.dms_user_store_access_details USING btree (store_id, login_id);


-- public.dms_users definition

-- Drop table

-- DROP TABLE public.dms_users;

CREATE TABLE public.dms_users (
	login_id varchar NOT NULL,
	first_name varchar NULL,
	last_name varchar NULL,
	email_id varchar NULL,
	reference_1 varchar NULL,
	reference_2 varchar NULL,
	reference_3 varchar NULL,
	createts timestamp NULL,
	modifyts timestamp NULL,
	emp_id varchar NULL
);
CREATE UNIQUE INDEX dms_users_loginid_idx ON public.dms_users USING btree (login_id);


-- public.activity_seq definition

-- DROP SEQUENCE public.activity_seq;

CREATE SEQUENCE public.activity_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 297101
	CACHE 1
	NO CYCLE;


-- public.new_activity_seq definition

-- DROP SEQUENCE public.new_activity_seq;

CREATE SEQUENCE public.new_activity_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 200000000
	CACHE 1
	NO CYCLE;


-- public.schedule_technician_seq definition

-- DROP SEQUENCE public.schedule_technician_seq;

CREATE SEQUENCE public.schedule_technician_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 100000000
	CACHE 1
	NO CYCLE;


-- public.store_schedules_seq definition

-- DROP SEQUENCE public.store_schedules_seq;

CREATE SEQUENCE public.store_schedules_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 100000000
	CACHE 1
	NO CYCLE;