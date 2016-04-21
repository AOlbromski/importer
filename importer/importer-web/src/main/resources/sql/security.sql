create schema SA AUTHORIZATION DBA;

INSERT INTO SA.t_authority(name) VALUES ('ROLE_ADMIN');
INSERT INTO SA.t_authority(name) VALUES ('ROLE_USER');

INSERT INTO SA.t_user(id, login, password, created_date, first_name, last_name, email, activated, lang_key, created_by)
    VALUES (1,'system','$2a$10$mE.qmcV0mFU5NcKh73TZx.z4ueI/.bDWbj0T1BYyqP481kGGarKLG','2015-09-04', 'System','System','system@localhost',true,'en','system');

INSERT INTO SA.t_user(id, login, password, created_date, first_name, last_name, email, activated, lang_key, created_by)
    VALUES (2,'anonymousUser','$2a$10$j8S5d7Sr7.8VTOYNviDPOeWX8KcYILUVJBsYV83Y5NtECayypx9lO','2015-09-04', 'Anonymous','User','anonymous@localhost',true,'en','system');

INSERT INTO SA.t_user(id, login, password, created_date, first_name, last_name, email, activated, lang_key, created_by)
    VALUES (3,'admin','$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC','2015-09-04','Administrator','Administrator','admin@localhost',true,'en','system');

INSERT INTO SA.t_user(id, login, password, created_date, first_name, last_name, email, activated, lang_key, created_by)
    VALUES (4,'user','$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K','2015-09-04','User','User','user@localhost',true,'en','system');


INSERT INTO SA.t_user_authority(user_id, authority_name) VALUES (1,'ROLE_ADMIN');

INSERT INTO SA.t_user_authority(user_id, authority_name) VALUES (1,'ROLE_USER');

INSERT INTO SA.t_user_authority(user_id, authority_name) VALUES (3,'ROLE_ADMIN');

INSERT INTO SA.t_user_authority(user_id, authority_name) VALUES (3,'ROLE_USER');

INSERT INTO SA.t_user_authority(user_id, authority_name) VALUES (4,'ROLE_USER');

ALTER SEQUENCE t_user_id_seq RESTART WITH 5;
