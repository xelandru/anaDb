USE candidate ;

CREATE TABLE example (id smallint unsigned not null auto_increment, name varchar (20) not null, constraint pk_example primary key (id));

INSERT INTO example (
                    id,
                    name) VALUES (
                                 NULL,
                                 'Sample data');


