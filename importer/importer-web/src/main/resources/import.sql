INSERT INTO security.t_authority (name) VALUES ('ROLE_USER');
INSERT INTO security.t_authority (NAME) VALUES ('ROLE_ADMIN');

INSERT INTO security.t_user(id, login, password, created_date, first_name, last_name, email, activated, lang_key, created_by, pesel) VALUES (1,'system','$2a$10$mE.qmcV0mFU5NcKh73TZx.z4ueI/.bDWbj0T1BYyqP481kGGarKLG', TIMESTAMP('2015-09-04 00:00:00'), 'System','System','system@localhost',true,'en','system', '91010121515');

INSERT INTO security.t_user(id, login, password, created_date, first_name, last_name, email, activated, lang_key, created_by, pesel) VALUES (2,'anonymousUser','$2a$10$j8S5d7Sr7.8VTOYNviDPOeWX8KcYILUVJBsYV83Y5NtECayypx9lO', TIMESTAMP('2015-09-04 00:00:00'), 'Anonymous','User','anonymous@localhost',true,'en','system', '91010121515');

INSERT INTO security.t_user(id, login, password, created_date, first_name, last_name, email, activated, lang_key, created_by, pesel) VALUES (3,'admin','$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC', TIMESTAMP('2015-09-04 00:00:00'),'Administrator','Administrator','admin@localhost',true,'en','system', '91010121515');

INSERT INTO security.t_user(id, login, password, created_date, first_name, last_name, email, activated, lang_key, created_by, pesel) VALUES (4,'user','$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K', TIMESTAMP('2015-09-04 00:00:00'),'User','User','user@localhost',true,'en','system', '91010121515');

INSERT INTO security.t_user_authority(user_id, authority_name) VALUES (1,'ROLE_ADMIN');

INSERT INTO security.t_user_authority(user_id, authority_name) VALUES (1,'ROLE_USER');

INSERT INTO security.t_user_authority(user_id, authority_name) VALUES (3,'ROLE_ADMIN');

INSERT INTO security.t_user_authority(user_id, authority_name) VALUES (3,'ROLE_USER');

INSERT INTO security.t_user_authority(user_id, authority_name) VALUES (4,'ROLE_USER');
