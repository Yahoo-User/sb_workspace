DROP TABLE member PURGE;

CREATE TABLE member (
    id VARCHAR2(10) CONSTRAINT pk_member PRIMARY KEY,
    password VARCHAR2(100) NOT NULL,
    name VARCHAR2(30),
    role VARCHAR2(12),
    enabled NUMBER(1, 0) CONSTRAINT member_enabled_ck CHECK( enabled IN (1, 0) ) NOT NULL
);

INSERT INTO member VALUES ('member', 'member123', '회원', 'ROLE_MEMBER', 1);
INSERT INTO member VALUES ('manager', 'manager123', '관리자', 'ROLE_MANAGER', 1);
INSERT INTO member VALUES ('admin', 'admin123', '어드민', 'ROLE_ADMIN', 1);

COMMIT;

--

SELECT
    id AS username,
    concat('{noop}', password) AS password,
    enabled
FROM
    member
WHERE
    id = 'member';

--

SELECT
    id,
    role
FROM
    member
WHERE
    id = 'admin';

