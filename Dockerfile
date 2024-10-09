FROM openjdk:15

# Copy wait-for-it script
COPY  docker.entrypoint.sh docker.entrypoint.sh
RUN chmod +x wait-for-it.sh docker-entrypoint.sh

COPY target/TodoList-1.0-SNAPSHOT.jar /app/demo-docker.jar

ENTRYPOINT ["docker-entrypoint.sh"]
CMD ["java", "-jar", "/app/demo-docker.jar"]