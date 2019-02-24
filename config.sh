docker stop blogsite-mysql
docker rm blogsite-mysql
docker run --name blogsite-mysql -e MYSQL_ROOT_PASSWORD=root -d -p 3306:3306 mysql

