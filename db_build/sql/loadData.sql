-- 두 개의 그룹을 생성 
  insert into groups(group_name) 
  values ('Users');
  
  insert into groups(group_name) 
  values ('Administrators');
  
-- 그룹의 권한을 설정한다.  
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

-- user를 생성한다.  
  insert into users(username, password, enabled) 
  values ('admin','admin',true);
  
  insert into users(username, password, enabled) 
  values ('guest','guest',true);

-- user를 그룹에 포함시킨다.  
  insert into group_members(group_id, username) 
  select id,'guest' 
    from groups 
   where group_name='Users';
  insert into group_members(group_id, username) 
  select id,'admin' 
    from groups 
   where group_name='Administrators';
  
commit;
