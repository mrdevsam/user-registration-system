alter table if exists account_roles
	add constraint FK_RoleId_Id
	foreign key (role_id)
	references role (id);
 
alter table if exists account_roles
	add constraint FK_AccountId_Id
	foreign key (account_id)
	references account (id);
