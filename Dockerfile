# Define a imagem base
FROM ibm-semeru-runtimes:open-17-jre-focal

# Define as variáveis de ambiente
ENV DATA_URL=${DATA_URL}
ENV DATA_USERNAME=${DATA_USERNAME}
ENV DATA_PASSWORD=${DATA_PASSWORD}

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o arquivo jar do projeto para dentro do container
COPY target/desafioti360-backend-0.0.1-SNAPSHOT.jar /app/app.jar

# Define a porta em que a aplicação estará disponível
EXPOSE 8080

# Define o comando de inicialização da aplicação
CMD java -XX:MaxRAM=70m -jar /app/app.jar
