FROM openjdk:17
RUN mkdir /app
COPY users.jar /app
EXPOSE 8080
ENV DB_URL=jdbc:postgresql://172.17.0.2:5432/users
ENV DB_USER=pguser
ENV DB_PASS=pgpass
WORKDIR /app
CMD java -jar users.jar