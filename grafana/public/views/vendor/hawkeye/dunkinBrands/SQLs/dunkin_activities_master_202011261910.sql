INSERT INTO public.dunkin_activities_master (activity_id,activity_details,pre_requisite,reference,createts,modifyts,activity_type) VALUES
	 ('A10002','Upgrade servers to the required version','','DEFAULT',NULL,NULL,'Standard'),
	 ('A10003','Install latest certified security patches','','DEFAULT',NULL,NULL,'Standard'),
	 ('A10004','Perform Test Upgrade','No downtime required','DEFAULT',NULL,NULL,'Standard'),
	 ('A10006','PMS Pre-Live Configuration','','DEFAULT',NULL,NULL,'Standard'),
	 ('A10007','PMS User Access testing ','','DEFAULT',NULL,NULL,'Standard'),
	 ('A10009','Upgrade IFCS','Downtime for IFCS will continue. We will release IFCs one by one.','DEFAULT',NULL,NULL,'Standard'),
	 ('A10010','Technical Live Cover and Dataguard','','DEFAULT',NULL,NULL,'Standard'),
	 ('A10011','PMS Live Cover','','DEFAULT',NULL,NULL,'Standard'),
	 ('A10001','Install New Servers','Corp to provide servers with OS installed','DEFAULT',NULL,NULL,'Standard'),
	 ('A10005','New Features Training','Store staff to be available for the presentation on the new','DEFAULT',NULL,NULL,'Standard');
INSERT INTO public.dunkin_activities_master (activity_id,activity_details,pre_requisite,reference,createts,modifyts,activity_type) VALUES
	 ('A10008','Final Upgrade','Store to arrange 8-10 hours downtime','DEFAULT',NULL,NULL,'Standard');