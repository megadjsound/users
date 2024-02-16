# собрать образ с именем subscription_users в тек.директории
docker build -t subscription_users .
# запустить контейнер с привязкой порта 8008
docker run --name subscription_users -p 8008:8080 subscription_users