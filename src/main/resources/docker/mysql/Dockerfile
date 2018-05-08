FROM mysql/mysql-server:5.6

ENV MYSQL_USER=test
ENV MYSQL_PASSWORD=test
ENV MYSQL_DATABASE=MyDbTest

ENV SQL_SCRIPT=init.sql


COPY $SQL_SCRIPT /docker-entrypoint-initdb.d/
EXPOSE 3306
