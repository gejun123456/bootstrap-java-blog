call gradlew updateProd
call gradlew build -x test
call java -Xms128m -Xmx512m -Dspring.profiles.active=prod -Djava.security.egd=file:/dev/./urandom -jar  build/libs/bootstrap.javablog-1.0.jar
