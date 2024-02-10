<h2 align="center">Подписки пользователей</h2>

### 1.9 Создание контейнера с именем "postgres-db" и поднятие в нем БД Postgres через командную строку докера
#### Создание БД users, далее подмонтировать соответствующий каталог (куда будут сохраняться данные) -v D:/1/postgres-data, с указанием пути для инициализации БД, где расположен файл *.sql скрипта создания объектов и заполнения данных в БД.

    docker run --name postgres-db \
    -e POSTGRES_DB=users \
    -e POSTGRES_USER=pguser \
    -e POSTGRES_PASSWORD=pgpass \
    -v D:/1/postgres-data:/var/lib/postgresql/data \
    -p 5001:5432 \
    -v D:\1:/docker-entrypoint-initdb.d postgres:14

после прогона, в созданной БД users созданы таблицы, индексы, ограничения целостности, а также заполнены все данные.
![img.png](images%2Fimg.png)

#### Поднять контейнер pgAdmin с именем "pgAdmin", где по адресу http://localhost:8888/browser/ будет интерфейс программы pgAdmin, данные учетки для входа: user - root12345@admin.org, пароль - 123
    docker run --name pgAdmin -e PGADMIN_DEFAULT_EMAIL=root12345@admin.org -e PGADMIN_DEFAULT_PASSWORD=123 -p 8888:80 dpage/pgadmin4:6.9

#### Создание сети между pgAdmin и БД (например имя db-net) для взаимодействия вышесоданных контейнеров.
    docker network create db-net
#### Подключить к сети (db-net) контейнер (postgres-db)
    docker network connect db-net postgres-db
#### Подключить к сети (db-net) контейнер (pgAdmin)
    docker network connect db-net pgAdmin
#### Проверить, что данные контейнеры объединились в одну сеть
    docker network inspect db-net
должен быть некий результат, см. "Containers":
C:\Users\Megadjsound>docker network inspect db-net
[
{
"Name": "db-net",
"Id": "8ffd15d4a246b69bc53f1f41acfbdd314edb662e009380963ae0f4faaec03ee5",
"Created": "2024-02-10T07:03:52.945902588Z",
"Scope": "local",
"Driver": "bridge",
"EnableIPv6": false,
"IPAM": {
"Driver": "default",
"Options": {},
"Config": [
{
"Subnet": "172.19.0.0/16",
"Gateway": "172.19.0.1"
}
]
},
"Internal": false,
"Attachable": false,
"Ingress": false,
"ConfigFrom": {
"Network": ""
},
"ConfigOnly": false,
"Containers": {
"52ee02fa061d2a832adaac03558b759ad1d3109173894a476681716aa6a8ac72": {
"Name": "postgres-db",
"EndpointID": "847d2d89f0b875a1983d955d4be4f620147b9b78aa1158d2ec69c7a6c81565cc",
"MacAddress": "02:42:ac:13:00:02",
"IPv4Address": "172.19.0.2/16",
"IPv6Address": ""
},
"afe50b6d58c6293f9187150004df6ce6c33ef85613e9c7250e2a6ce658cad022": {
"Name": "pgAdmin",
"EndpointID": "d56c82aaa6618e18931bd4b649f636df443e1af3e3ed507390489c06b0428b8f",
"MacAddress": "02:42:ac:13:00:03",
"IPv4Address": "172.19.0.3/16",
"IPv6Address": ""
}
},
"Options": {},
"Labels": {}
}

далее настройка рабочего места в PgAdmin и все готово! Rest запросы от Postman работоспособные.