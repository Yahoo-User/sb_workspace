CREATE TABLE member {
    id VARCHAR2(10) PRIMARY KEY,
    password VARCHAR2(100),
    name VARCHAR2(30),
    role VARCHAR2(12),
    enabled NUMBER(1,0)
};
