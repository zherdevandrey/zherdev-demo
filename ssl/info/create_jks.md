# 1.1. Генерация корневого CA (для подписи сертификатов)

Генерируем приватный ключ и самоподписанный сертификат CA
openssl req -x509 -newkey rsa:4096 -sha256 -days 3650 -nodes \
-keyout ca-key.pem -out ca-cert.pem -subj "/CN=My Root CA"

# 1.2. Генерация сертификата для сервера
Создаём приватный ключ и запрос на сертификат (CSR)
openssl req -newkey rsa:4096 -sha256 -nodes \
-keyout server-key.pem -out server-req.pem -subj "/CN=localhost"

Подписываем CSR с помощью CA
openssl x509 -req -in server-req.pem -CA ca-cert.pem -CAkey ca-key.pem -CAcreateserial \
-out server-cert.pem -days 365 -sha256

# 1.3. Генерация сертификата для клиента
Аналогично для клиента
openssl req -newkey rsa:4096 -sha256 -nodes \
-keyout client-key.pem -out client-req.pem -subj "/CN=SpringClient"

openssl x509 -req -in client-req.pem -CA ca-cert.pem -CAkey ca-key.pem -CAcreateserial \
-out client-cert.pem -days 365 -sha256

# 1.4. Создание JKS (KeyStore и TrustStore)
Конвертируем сертификаты в PKCS12 (нужен для keytool)
openssl pkcs12 -export -in server-cert.pem -inkey server-key.pem \
-out server-keystore.p12 -name "server" -passout pass:123456

openssl pkcs12 -export -in client-cert.pem -inkey client-key.pem \
-out client-keystore.p12 -name "client" -passout pass:123456

Импортируем в JKS
keytool -importkeystore -srckeystore server-keystore.p12 -srcstoretype pkcs12 \
-destkeystore server.jks -deststoretype JKS -srcstorepass 123456 -deststorepass 123456

keytool -importkeystore -srckeystore client-keystore.p12 -srcstoretype pkcs12 \
-destkeystore client.jks -deststoretype JKS -srcstorepass 123456 -deststorepass 123456

Добавляем CA в TrustStore (чтобы сервер/клиент доверяли друг другу)
keytool -importcert -alias ca -file ca-cert.pem -keystore server-truststore.jks -storepass 123456 -noprompt
keytool -importcert -alias ca -file ca-cert.pem -keystore client-truststore.jks -storepass 123456 -noprompt