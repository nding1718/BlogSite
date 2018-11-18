-- INSERT INTO user (id, username, password, name, email) VALUES (1, 'admin', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', 'Nan Ding', 'ndingdw@gmaill.com');
-- INSERT INTO user (id, username, password, name, email)  VALUES (2, 'ndingdw', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', 'nan ding', 'nding1718@gmail.com');
--
-- INSERT INTO authority (id, name) VALUES (1, 'ROLE_ADMIN');
-- INSERT INTO authority (id, name) VALUES (2, 'ROLE_USER');
--
-- INSERT INTO user_authority (user_id, authority_id) VALUES (1, 1);
-- INSERT INTO user_authority (user_id, authority_id) VALUES (2, 2);


INSERT INTO user (id, username, password, name, email) VALUES (1, 'admin', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', 'supernatural', 'eric@gmail.com');
INSERT INTO user (id, username, password, name, email)  VALUES (2, 'ndingdw', '$2a$10$GBmmhx0bAjFl3NOnkjrdmeWrzvcBir2oMuIWPm8kmPVaBc5X1YpAi', 'eric', 'ndingdw@gmail.com');

INSERT INTO authority (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO authority (id, name) VALUES (2, 'ROLE_USER');

INSERT INTO user_authority (user_id, authority_id) VALUES (1, 1);
INSERT INTO user_authority (user_id, authority_id) VALUES (2, 2);
