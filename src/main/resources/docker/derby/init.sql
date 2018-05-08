connect 'jdbc:derby:MyDbTest;create=true' ;
CREATE TABLE candidate
(
  id      SMALLINT  NOT NULL auto_increment,
  name    VARCHAR(30) NOT NULL,
  job     VARCHAR(30) NOT NULL,
  mail    VARCHAR(30),
  phone   VARCHAR(20),
  link    VARCHAR(50),
  info    VARCHAR(1000),
  PRIMARY KEY (id)
);