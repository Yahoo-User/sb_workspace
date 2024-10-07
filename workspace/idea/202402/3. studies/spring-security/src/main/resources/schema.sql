DROP TABLE member;

CREATE TABLE member (
    id VARCHAR2(10) PRIMARY KEY,
    password VARCHAR2(100),
    name VARCHAR2(30),
    role VARCHAR2(12),
    enabled NUMBER(1, 0) CHECK (enabled = 0 OR enabled = 1)
);

INSERT INTO member VALUES ( 'member', 'member123', 'MEMBER', 'ROLE_MEMBER', 1 );
INSERT INTO member VALUES ( 'manager', 'manager123', 'MANAGER', 'ROLE_MANAGER', 1);
INSERT INTO member VALUES ('admin', 'admin123', 'ADMIN', 'ROLE_ADMIN', 1);

COMMIT;
