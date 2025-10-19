# ====== STAGE 1: Build ======
FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /app

# Copia arquivos de definição primeiro para cache de dependências
COPY pom.xml ./
COPY .mvn .mvn
COPY mvnw mvnw
RUN ./mvnw -q -B -DskipTests dependency:go-offline || mvn -q -B -DskipTests dependency:go-offline

# Copia o código-fonte e compila
COPY src ./src
RUN ./mvnw -q -B -DskipTests package || mvn -q -B -DskipTests package

# ====== STAGE 2: Runtime ======
FROM eclipse-temurin:17-jre AS runtime

# Dependências úteis para fuso horário e fontes (PDF/docx normalmente precisam de fontes)
RUN apt-get update \
 && apt-get install -y --no-install-recommends tzdata fonts-dejavu ca-certificates curl \
 && rm -rf /var/lib/apt/lists/*

# Fuso padrão (ajuste se quiser outro)
ENV TZ=America/Sao_Paulo

# Usuário não-root
RUN useradd -r -u 10001 -g root appuser

# Diretório de trabalho
WORKDIR /opt/app

# Copia JAR do estágio de build
# Ajuste o padrão do arquivo caso seu JAR final tenha outro nome
COPY --from=build /app/target/*.jar app.jar

# Diretório de saída dos PDFs (mapeável)
VOLUME ["/var/app/docs/saidas"]

# Porta (segue sua config server.port = ${PORTA_API:8080})
ENV PORTA_API=8080
EXPOSE 8080

# Variáveis opcionais (segredos vêm de env vars em runtime)
# MAIL_USER / MAIL_PASS / SHOW_SQL / SPRING_PROFILES_ACTIVE etc.
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0 -Duser.timezone=America/Sao_Paulo"

# Healthcheck (se tiver actuator habilitado)
# HEALTHCHECK --interval=30s --timeout=3s --start-period=30s --retries=3 \
#   CMD curl -fsS "http://localhost:${PORTA_API}/actuator/health" || exit 1

USER appuser

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar --server.port=${PORTA_API}"]
