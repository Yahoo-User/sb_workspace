SET FEEDBACK OFF
SET TERMOUT OFF
SET TIMING OFF

-- ALTER SESSION SET nls_date_format = 'YYYY/MM/DD hh24:mi:ss';
ALTER SESSION SET nls_date_format = 'hh24:mi:ss';

SET sqlprompt "( _user'@'_connect_identifier - _date )$ "

SET FEEDBACK ON
SET TERMOUT ON
SET TIMING ON
SET TRIM ON

CLEAR BUFFER
SET LINESIZE 200
SET PAGESIZE 100

-- SHOW USER