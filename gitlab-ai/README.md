# Перед запуском необходимо создать сеть
docker network create gitlab-network

# Получение пароля
docker exec -it gitlab grep 'Password:' /etc/gitlab/initial_root_password

# Если пароль и пользователь не найдейны. Создание пользователя
## 1️⃣ создаём организацию (обязательно для 17.x)
org = Organizations::Organization.create!(
name: 'Default Organization',
path: 'default-org'
)

## 2️⃣ создаём пользователя
user = User.new(
username: 'root',
name: 'Administrator',
email: 'admin@example.com',
password: 'StrongPassword123!',
password_confirmation: 'StrongPassword123!',
admin: true
)
user.skip_confirmation!
user.save!(validate: false)

## 3️⃣ создаём namespace, связанный с пользователем и организацией
ns = Namespaces::UserNamespace.create!(
name: user.name,
path: user.username,
owner: user,
organization: org
)

## 4️⃣ привязываем namespace к пользователю## 4️⃣ привязываем namespace к пользователю
user.namespace = ns
user.save!(validate: false)


## 4️⃣ Генерация токена
docker exec -it gitlab bash
gitlab-rails runner "puts Gitlab::CurrentSettings.current_application_settings.runners_registration_token"


## 4️⃣ Регистрация ранера
docker exec -it gitlab-runner gitlab-runner register

- Пример
Enter the GitLab instance URL: http://gitlab   http://172.18.0.2/
Enter the registration token: <paste your token> sHLrpZLxCC5so2Vbhpis
Enter a description for the runner: docker-runner
Enter tags for the runner (comma-separated): docker
Enter executor: docker
Enter the default Docker image: alpine:latest

## 4️⃣ Перезагрузка после успешной регистрации
docker restart gitlab-runner


## 4️⃣ Проблема token signing key не установлен по дефолту
- В логе будет ошибка - "CI job token signing key is not set","exception.backtrace"

- Починка
1) docker exec -it gitlab bash
2) gitlab-rails console
3) # Установите CI JWT signing key
ApplicationSetting.current_without_cache.update!(ci_job_token_signing_key: OpenSSL::PKey::RSA.new(2048).to_pem)

# Или альтернативный способ:
Gitlab::CurrentSettings.update!(ci_job_token_signing_key: OpenSSL::PKey::RSA.new(2048).to_pem)

# Проверьте, что ключ установлен
Gitlab::CurrentSettings.ci_job_token_signing_key?

4) docker restart gitlab

5) Альтернативное решение
```
version: '3.8'
services:
  gitlab:
    image: gitlab/gitlab-ce:latest
    environment:
      GITLAB_OMNIBUS_CONFIG: |
        gitlab_rails['ci_job_token_signing_key'] = File.read('/path/to/your/private.key')
    # ... остальные настройки
```