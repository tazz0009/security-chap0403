-- �� ���� �׷��� ���� 
  insert into groups(group_name) 
  values ('Users');
  
  insert into groups(group_name) 
  values ('Administrators');
  
-- �׷��� ������ �����Ѵ�.  
  insert into group_authorities(group_id, authority) 
  select id
       , 'ROLE_USER' 
    from groups 
   where group_name='Users'; 
  
  insert into group_authorities(group_id, authority) 
  select id
  	   , 'ROLE_USER' 
    from groups 
   where group_name='Administrators'; 
  
  insert into group_authorities(group_id, authority) 
  select id
       , 'ROLE_ADMIN' 
    from groups 
   where group_name='Administrators'; 

-- user�� �����Ѵ�.  
  insert into users(username, password, enabled) 
  values ('admin','admin',true);
  
  insert into users(username, password, enabled) 
  values ('guest','guest',true);

-- user�� �׷쿡 ���Խ�Ų��.  
  insert into group_members(group_id, username) 
  select id,'guest' 
    from groups 
   where group_name='Users';
  insert into group_members(group_id, username) 
  select id,'admin' 
    from groups 
   where group_name='Administrators';
  
commit;
