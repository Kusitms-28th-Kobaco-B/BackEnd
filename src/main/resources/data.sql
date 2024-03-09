INSERT INTO MEMBER (id, password) VALUES ('example_id', 'example_password');

INSERT INTO ADVERTISEMENT_COPY (member_id, service, project_name, product_name, target_gender, target_age, tone, message)
VALUES (1, 'HEAD', '생활용품', '샴푸', 'MALE', 'TEN', 'REVIEW', '샴푸가 좋아요.....');
INSERT INTO ADVERTISEMENT_COPY (member_id, service, project_name, product_name, target_gender, target_age, tone, message)
VALUES (1, 'HEAD', '생활용품', '샴푸', 'MALE', 'TEN', 'REVIEW', '샴푸가 좋아요.....');
INSERT INTO ADVERTISEMENT_COPY (member_id, service, project_name, product_name, target_gender, target_age, tone, message)
VALUES (1, 'HEAD', '생활용품', '샴푸', 'MALE', 'TEN', 'REVIEW', '샴푸가 좋아요.....');


INSERT INTO COPY_GALLERY (advertisement_copy_id, member_id, service, product_name, tone, views, message)
VALUES (1, 1, 'HEAD', '샴푸', 'REVIEW', 0, '샴푸가 좋아요.....');
INSERT INTO COPY_GALLERY (advertisement_copy_id, member_id, service, product_name, tone, views, message)
VALUES (2, 1, 'HEAD', '샴푸', 'REVIEW', 0, '샴푸가 좋아요.....');
INSERT INTO COPY_GALLERY (advertisement_copy_id, member_id, service, product_name, tone, views, message)
VALUES (3, 1, 'HEAD', '샴푸', 'REVIEW', 0, '샴푸가 좋아요.....');

INSERT INTO COPY_GALLERY_KEYWORDS (COPY_GALLERY_copy_gallery_id, keywords)
VALUES (1, '샴푸'), (1, '향긋함');

