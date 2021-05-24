INSERT INTO public.dms_activities_master (activity_id, activity_details, pre_requisite, reference, createts, modifyts, activity_type) VALUES('A10001', 'CAL Package Verified', '', '', '2020-12-05 14:52:31.311', '2020-12-05 14:52:31.311', 'Standard');
INSERT INTO public.dms_activities_master (activity_id, activity_details, pre_requisite, reference, createts, modifyts, activity_type) VALUES('A10002', 'POS Prereqs Deployed', '', '', '2020-12-05 14:53:06.061', '2020-12-05 14:53:06.061', 'Standard');
INSERT INTO public.dms_activities_master (activity_id, activity_details, pre_requisite, reference, createts, modifyts, activity_type) VALUES('A10003', 'KDS Prereqs Deployed', '', '', '2020-12-05 14:53:06.061', '2020-12-05 14:53:06.061', 'Standard');
INSERT INTO public.dms_activities_master (activity_id, activity_details, pre_requisite, reference, createts, modifyts, activity_type) VALUES('A10004', 'Ready for Night Upgrade', '', '', '2020-12-05 14:53:06.061', '2020-12-05 14:53:06.061', 'Standard');


INSERT INTO public.dms_master_data (entity, "type", id, value, "desc", createts, modifyts) VALUES('Schedule', 'Status', 'NotStarted', 'NotStarted', NULL, NULL, NULL);
INSERT INTO public.dms_master_data (entity, "type", id, value, "desc", createts, modifyts) VALUES('Schedule', 'Status', 'InProgress', 'InProgress', NULL, NULL, NULL);
INSERT INTO public.dms_master_data (entity, "type", id, value, "desc", createts, modifyts) VALUES('Schedule', 'Status', 'Completed', 'Completed', NULL, NULL, NULL);
INSERT INTO public.dms_master_data (entity, "type", id, value, "desc", createts, modifyts) VALUES('Schedule', 'Status', 'Delayed', 'Delayed', NULL, NULL, NULL);
INSERT INTO public.dms_master_data (entity, "type", id, value, "desc", createts, modifyts) VALUES('Schedule', 'Status', 'OnHold', 'On Hold', NULL, NULL, NULL);
INSERT INTO public.dms_master_data (entity, "type", id, value, "desc", createts, modifyts) VALUES('Activity', 'Status', 'NotStarted', 'Not Started', NULL, NULL, NULL);
INSERT INTO public.dms_master_data (entity, "type", id, value, "desc", createts, modifyts) VALUES('Activity', 'Status', 'InProgress', 'In Progress', NULL, NULL, NULL);
INSERT INTO public.dms_master_data (entity, "type", id, value, "desc", createts, modifyts) VALUES('Activity', 'Status', 'Completed', 'Completed', NULL, NULL, NULL);
INSERT INTO public.dms_master_data (entity, "type", id, value, "desc", createts, modifyts) VALUES('Activity', 'Status', 'Delayed', 'Delayed', NULL, NULL, NULL);
INSERT INTO public.dms_master_data (entity, "type", id, value, "desc", createts, modifyts) VALUES('Activity', 'Status', 'OnHold', 'On Hold', NULL, NULL, NULL);



INSERT INTO public.dms_store_software_master (store_id, software_id, software_name, "version") VALUES('356661', 'fiPay', ' FiPAY', '2.3');
INSERT INTO public.dms_store_software_master (store_id, software_id, software_name, "version") VALUES('356661', 'posVer', ' POS Version', '9.2.1');
INSERT INTO public.dms_store_software_master (store_id, software_id, software_name, "version") VALUES('356661', 'IV', ' Image Version', '8.5.0.0');
INSERT INTO public.dms_store_software_master (store_id, software_id, software_name, "version") VALUES('356661', 'webtitan', ' WebTitan', '0.1');
INSERT INTO public.dms_store_software_master (store_id, software_id, software_name, "version") VALUES('356661', 'splunk', ' Splunk', '8.1.0');
INSERT INTO public.dms_store_software_master (store_id, software_id, software_name, "version") VALUES('356661', 'tls', ' TLS1.0', '1.0');
INSERT INTO public.dms_store_software_master (store_id, software_id, software_name, "version") VALUES('356661', 'microsCAL', ' MicrosCAL Version', '7.20');
INSERT INTO public.dms_store_software_master (store_id, software_id, software_name, "version") VALUES('356661', '.net', ' .Net', '4.5');
INSERT INTO public.dms_store_software_master (store_id, software_id, software_name, "version") VALUES('888892', 'splunk', ' Splunk', '8.1.0');
INSERT INTO public.dms_store_software_master (store_id, software_id, software_name, "version") VALUES('888892', 'tls', ' TLS1.0', '1.0');
INSERT INTO public.dms_store_software_master (store_id, software_id, software_name, "version") VALUES('888892', 'microsCAL', ' MicrosCAL Version', '7.20');
INSERT INTO public.dms_store_software_master (store_id, software_id, software_name, "version") VALUES('300425', 'webtitan', ' WebTitan', '0.1');
INSERT INTO public.dms_store_software_master (store_id, software_id, software_name, "version") VALUES('300425', 'splunk', ' Splunk EAP', '8.1.0');



INSERT INTO public.dms_user_store_access_details (store_id, login_id, createts, modifyts) VALUES('356661', 'admin', '2020-11-02 05:09:14.000', '2020-11-02 05:09:14.000');
INSERT INTO public.dms_user_store_access_details (store_id, login_id, createts, modifyts) VALUES('356768', 'admin', '2020-11-02 05:09:14.000', '2020-11-02 05:09:14.000');
INSERT INTO public.dms_user_store_access_details (store_id, login_id, createts, modifyts) VALUES('356661', 'rsamant', '2020-11-02 05:09:14.000', '2020-11-02 05:09:14.000');
INSERT INTO public.dms_user_store_access_details (store_id, login_id, createts, modifyts) VALUES('356661', 'sasirekha', '2020-11-02 05:09:14.000', '2020-11-02 05:09:14.000');
INSERT INTO public.dms_user_store_access_details (store_id, login_id, createts, modifyts) VALUES('300480', 'admin', '2020-11-02 05:09:14.000', '2020-11-02 05:09:14.000');
INSERT INTO public.dms_user_store_access_details (store_id, login_id, createts, modifyts) VALUES('361490', 'admin', '2020-11-15 05:09:14.000', '2020-11-15 05:09:14.000');


INSERT INTO public.dms_users (login_id, first_name, last_name, email_id, reference_1, reference_2, reference_3, createts, modifyts, emp_id) VALUES('rsamant', 'Raghavendra', 'Samant', NULL, NULL, NULL, NULL, NULL, NULL, '35145');
INSERT INTO public.dms_users (login_id, first_name, last_name, email_id, reference_1, reference_2, reference_3, createts, modifyts, emp_id) VALUES('admin', 'Robert', 'Lafour', NULL, NULL, NULL, NULL, NULL, NULL, '231111');


INSERT INTO public.dms_host_software_version (store_id, host_name, software_id, "version", createts, modifyts) VALUES('356606', 'TN356606POS2', 'splunk', '8.0.0', '2020-10-31 17:46:16.000', '2020-10-31 17:46:16.000');
INSERT INTO public.dms_host_software_version (store_id, host_name, software_id, "version", createts, modifyts) VALUES('356606', 'TN356606POS1', 'splunk', '8.0.0', '2020-10-31 17:46:16.000', '2020-10-31 17:46:16.000');
INSERT INTO public.dms_host_software_version (store_id, host_name, software_id, "version", createts, modifyts) VALUES('356606', 'TN356606POS21', 'splunk', '8.0.0', '2020-10-31 17:46:16.000', '2020-10-31 17:46:16.000');
INSERT INTO public.dms_host_software_version (store_id, host_name, software_id, "version", createts, modifyts) VALUES('356606', 'TN356606POS26', 'splunk', '8.0.0', '2020-10-31 17:46:16.000', '2020-10-31 17:46:16.000');
INSERT INTO public.dms_host_software_version (store_id, host_name, software_id, "version", createts, modifyts) VALUES('356630', 'FL356630POS1', 'splunk', '8.0.0', '2020-10-31 17:46:16.000', '2020-10-31 17:46:16.000');
INSERT INTO public.dms_host_software_version (store_id, host_name, software_id, "version", createts, modifyts) VALUES('356630', 'FL356630POS21', 'splunk', '8.0.0', '2020-10-31 17:46:16.000', '2020-10-31 17:46:16.000');
INSERT INTO public.dms_host_software_version (store_id, host_name, software_id, "version", createts, modifyts) VALUES('356630', 'FL356630POS26', 'splunk', '8.0.0', '2020-10-31 17:46:16.000', '2020-10-31 17:46:16.000');
INSERT INTO public.dms_host_software_version (store_id, host_name, software_id, "version", createts, modifyts) VALUES('356658', 'NH356658POS2', 'splunk', '8.0.0', '2020-10-31 17:46:16.000', '2020-10-31 17:46:16.000');
INSERT INTO public.dms_host_software_version (store_id, host_name, software_id, "version", createts, modifyts) VALUES('356658', 'NH356658POS1', 'splunk', '8.0.0', '2020-10-31 17:46:16.000', '2020-10-31 17:46:16.000');
INSERT INTO public.dms_host_software_version (store_id, host_name, software_id, "version", createts, modifyts) VALUES('356658', 'NH356658POS21', 'splunk', '8.0.0', '2020-10-31 17:46:16.000', '2020-10-31 17:46:16.000');
INSERT INTO public.dms_host_software_version (store_id, host_name, software_id, "version", createts, modifyts) VALUES('356658', 'NH356658POS26', 'splunk', '8.0.0', '2020-10-31 17:46:16.000', '2020-10-31 17:46:16.000');
INSERT INTO public.dms_host_software_version (store_id, host_name, software_id, "version", createts, modifyts) VALUES('356659', 'CA356659POS1', 'splunk', '8.0.0', '2020-10-31 17:46:16.000', '2020-10-31 17:46:16.000');
INSERT INTO public.dms_host_software_version (store_id, host_name, software_id, "version", createts, modifyts) VALUES('356659', 'CA356659POS21', 'splunk', '8.0.0', '2020-10-31 17:46:16.000', '2020-10-31 17:46:16.000');
INSERT INTO public.dms_host_software_version (store_id, host_name, software_id, "version", createts, modifyts) VALUES('356659', 'CA356659POS26 (1)', 'splunk', '8.0.0', '2020-10-31 17:46:16.000', '2020-10-31 17:46:16.000');
INSERT INTO public.dms_host_software_version (store_id, host_name, software_id, "version", createts, modifyts) VALUES('356645', 'IA356645POS21', 'splunk', '8.0.0', '2020-10-31 17:46:16.000', '2020-10-31 17:46:16.000');
INSERT INTO public.dms_host_software_version (store_id, host_name, software_id, "version", createts, modifyts) VALUES('356645', 'IA356645POS1', 'splunk', '8.0.0', '2020-10-31 17:46:16.000', '2020-10-31 17:46:16.000');
INSERT INTO public.dms_host_software_version (store_id, host_name, software_id, "version", createts, modifyts) VALUES('356645', 'IA356645POS26', 'splunk', '8.0.0', '2020-10-31 17:46:16.000', '2020-10-31 17:46:16.000');
INSERT INTO public.dms_host_software_version (store_id, host_name, software_id, "version", createts, modifyts) VALUES('356645', 'IA356645POS2', 'splunk', '8.0.0', '2020-10-31 17:46:16.000', '2020-10-31 17:46:16.000');
INSERT INTO public.dms_host_software_version (store_id, host_name, software_id, "version", createts, modifyts) VALUES('356661', 'VA356661POS26', 'splunk', '8.0.0', '2020-10-31 17:46:16.000', '2020-10-31 17:46:16.000');
INSERT INTO public.dms_host_software_version (store_id, host_name, software_id, "version", createts, modifyts) VALUES('356661', 'VA356661POS21', 'splunk', '8.0.0', '2020-10-31 17:46:16.000', '2020-10-31 17:46:16.000');
INSERT INTO public.dms_host_software_version (store_id, host_name, software_id, "version", createts, modifyts) VALUES('356661', 'VA356661POS1', 'splunk', '8.0.0', '2020-10-31 17:46:16.000', '2020-10-31 17:46:16.000');
INSERT INTO public.dms_host_software_version (store_id, host_name, software_id, "version", createts, modifyts) VALUES('356655', 'PA356655POS26', 'splunk', '8.0.0', '2020-10-31 17:46:16.000', '2020-10-31 17:46:16.000');
INSERT INTO public.dms_host_software_version (store_id, host_name, software_id, "version", createts, modifyts) VALUES('356655', 'PA356655POS21', 'splunk', '8.0.0', '2020-10-31 17:46:16.000', '2020-10-31 17:46:16.000');
INSERT INTO public.dms_host_software_version (store_id, host_name, software_id, "version", createts, modifyts) VALUES('356655', 'PA356655POS1', 'splunk', '8.0.0', '2020-10-31 17:46:16.000', '2020-10-31 17:46:16.000');
INSERT INTO public.dms_host_software_version (store_id, host_name, software_id, "version", createts, modifyts) VALUES('356627', 'GA356627POS1', 'splunk', '8.0.0', '2020-10-31 17:46:16.000', '2020-10-31 17:46:16.000');
INSERT INTO public.dms_host_software_version (store_id, host_name, software_id, "version", createts, modifyts) VALUES('356627', 'GA356627POS2', 'splunk', '8.0.0', '2020-10-31 17:46:16.000', '2020-10-31 17:46:16.000');
INSERT INTO public.dms_host_software_version (store_id, host_name, software_id, "version", createts, modifyts) VALUES('356627', 'GA356627POS21', 'splunk', '8.0.0', '2020-10-31 17:46:16.000', '2020-10-31 17:46:16.000');
INSERT INTO public.dms_host_software_version (store_id, host_name, software_id, "version", createts, modifyts) VALUES('356627', 'GA356627POS26', 'splunk', '8.0.0', '2020-10-31 17:46:16.000', '2020-10-31 17:46:16.000');

