# --- 第1ステージ：ビルド用（JDKとMavenが入ったイメージ） ---
FROM maven:3.8.5-openjdk-17 AS build
COPY . /app
WORKDIR /app
# コンテナの中でmvnコマンドを実行してJARを作る
RUN ./mvnw clean package -DskipTests

# --- 第2ステージ：実行用（軽量な実行専用イメージ） ---
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
# ビルドステージで作ったJARファイルだけを持ってくる
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]