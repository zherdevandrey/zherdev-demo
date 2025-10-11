
docker exec -it gitlab bash
gitlab-rails console -e production


# Создание пользователя
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

## 4️⃣ привязываем namespace к пользователю
user.namespace = ns
user.save!(validate: false)


## Получение токера для ранера
docker exec -it gitlab-runner gitlab-runner register --non-interactive --url "http://gitlab/" --registration-token "sHLrpZLxCC5so2Vbhpis" --executor "docker" --docker-image "alpine:latest" --description "docker-runner" --tag-list "docker,linux" --run-untagged="true" --locked="false" --access-level="not_protected"


## Удаление ранера
docker exec -it gitlab-runner gitlab-runner unregister --name "docker-runner"
