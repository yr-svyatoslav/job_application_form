📌 Job Application Form

📖 Описание проекта

Job Application Form — это веб-приложение для обработки анкет кандидатов на работу.
Позволяет хранить и управлять информацией о кандидатах, их образовании, опыте работы, контактных данных и другой информацией.

⚙️ Технические требования

Перед запуском убедитесь, что у вас установлены:

JDK 21+ (java -version)

Maven 3+ (mvn -version)

Docker 25+ (docker --version)

Git (для клонирования репозитория)

Postman или браузер (для тестирования API)

БД - используется H2 Console

🚀 Установка и запуск

1️ Клонируйте репозиторий

git clone https://github.com/your-repo/job-application-form.git
cd job-application-form

2️ Соберите проект с помощью Maven

mvn clean package -DskipTests

3️ Запустите в Docker (рекомендуемый вариант)

docker compose up --build

После этого приложение будет доступно по адресу:

http://localhost:8080

Если порт 8080 занят, измените его в compose.yaml:

ports:
  - "8081:8080"

И запустите снова:

docker compose up --build

4️ Запуск без Docker (локально через IntelliJ IDEA)

Откройте проект в IntelliJ IDEA.

Запустите класс JobApplicationFormApplication.java.

🛠 Использование API

📌 1. Получить шаблон анкеты

Запрос:

GET http://localhost:8080/candidates/template

Ответ:

{
  "ИИН": null,
  "ФИО": null,
  "Дата рождения": null,
  "Место рождения": null,
  "Национальность": null,
  "Гражданство": null,
  "Документ": {},
  "Контакты": [{}],
  "Адрес": [{}],
  "Образование": [{}],
  "Курсы": [{}]
}

📌 2. Создать нового кандидата

Запрос:

POST http://localhost:8080/candidates/create
Content-Type: application/json

Тело запроса заполнить в соответствии с шаблоном.

Образец:


📌 3. Получить анкету кандидата по ID

Запрос:

GET http://localhost:8080/candidates/{id}

Ответ (если найден):

{
  "ИИН": "123456789012",
  "ФИО": "Иван Иванов",
  "Контакты": [{"numberMobile": "+79991234567"}]
}

Ответ (если не найден):

404 NOT FOUND

📌 4. Обновить анкету кандидата

Запрос:

PUT http://localhost:8080/candidates/{id}
Content-Type: application/json

Тело запроса:

{
  "iin": "123456789012",
  "fullName": "Иван Петров",
  "birthDate": "1990-01-01",
  "contacts": [{"numberMobile": "+79991234567"}]
}

Ответ:

{
  "ИИН": "123456789012",
  "ФИО": "Иван Петров",
  "Контакты": [{"numberMobile": "+79991234567"}]
}

📌 5. Удалить анкету кандидата

Запрос:

DELETE http://localhost:8080/candidates/{id}

Ответ (если успешно удалено):

204 NO CONTENT

Ответ (если не найден):

404 NOT FOUND


📂 Работа с базой данных H2

Консоль H2 доступна по адресу:

http://localhost:8080/h2-console

JDBC URL: jdbc:h2:mem:testdb

Username: sa

Password: (оставьте пустым)


🔍 Логирование и отладка

Логи приложения можно посмотреть через Docker:

docker logs job-application-form

Если возникли ошибки, проверьте консоль IntelliJ IDEA или файлы логов.

🎯 Итог

📌 Полноценное API для работы с кандидатами

✅ Удобный запуск через Docker

🔍 Встроенная база H2 с консолью для тестирования

🛠 Логи для диагностики ошибок