alter table if exists account
	add constraint Unique_Email unique (user_email);
