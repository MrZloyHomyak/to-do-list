# To-Do List Application

Простое и эффективное приложение для управления задачами, построенное на Spring Boot с использованием современных технологий.

## 🚀 Технологический стек

- **Backend**: Java 17+, Spring Boot 3.5.0
- **База данных**: PostgreSQL 15+
- **Миграции**: Flyway
- **Сборка**: Maven
- **API**: RESTful HTTP JSON API

## 📋 Функциональность

### Основные возможности
- ✅ Создание, чтение, обновление и удаление задач (CRUD)
- 👥 Управление пользователями
- 🔗 Связь задач с пользователями (Many-to-One)
- 🗃️ Автоматические миграции базы данных

### Модели данных

#### Пользователь (User)
```java
public class User {
    private Long id;
    private String username;    // Уникальное
    private String email;       // Уникальное  
    private String password;
    private List<Task> tasks;
}
```

#### Задача (Task)
```java
public class Task {
    private Long id;
    private String title;
    private String description;
    private boolean completed;
    private User user;          // Владелец задачи
}
```
## 🛠️ Установка и запуск
### Предварительные требования
- Java 17 или выше

- PostgreSQL 15 или выше

- Maven 3.6+

### 1. Клонирование репозитория
```bash
git clone <your-repo-url>
cd To-Do-list
```
### 2. Настройка базы данных
#### Создай базу данных в PostgreSQL:

```sql
CREATE DATABASE todolist;
CREATE USER todolist_user WITH PASSWORD 'password';
GRANT ALL PRIVILEGES ON DATABASE todolist TO todolist_user;
```
### 3. Конфигурация
#### Обнови src/main/resources/application.properties:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/todolist
spring.datasource.username=todolist_user
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true
```
### 4. Запуск приложения
```bash
# Сборка и запуск
mvn spring-boot:run

# Или собери JAR
mvn clean package
java -jar target/To-Do-list-1.0.0.jar
```
#### Приложение будет доступно по адресу: http://localhost:8080

## 📡 API Endpoints

### Управление задачами

| Метод | Endpoint | Описание |
|-------|----------|----------|
| GET | `/api/tasks` | Получить все задачи |
| GET | `/api/tasks/{id}` | Получить задачу по ID |
| GET | `/api/tasks/user/{userId}` | Получить задачи пользователя |
| POST | `/api/tasks` | Создать новую задачу |
| PUT | `/api/tasks/{id}` | Обновить задачу |
| DELETE | `/api/tasks/{id}` | Удалить задачу |

### Управление пользователями

| Метод | Endpoint | Описание |
|-------|----------|----------|
| GET | `/api/users` | Получить всех пользователей |
| GET | `/api/users/{id}` | Получить пользователя по ID |
| POST | `/api/users` | Создать нового пользователя |
| PUT | `/api/users/{id}` | Обновить пользователя |
| DELETE | `/api/users/{id}` | Удалить пользователя |

### Примеры запросов
#### Создание пользователя:
```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "username": "john_doe",
    "email": "john@example.com",
    "password": "securepassword"
  }'
```
#### Создание задачи:

```bash
curl -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Изучить Spring Boot",
    "description": "Освоить основы Spring Boot разработки",
    "completed": false,
    "userId": 1
  }'
  ```
## 🗃️ Структура базы данных
### Миграции Flyway
- `V1__create_task_table.sql` - Создание таблицы задач

- `V2__create_user_table.sql` - Создание таблицы пользователей

- `V3__add_user_id_to_task.sql` - Добавление связи между таблицами

### Схема базы данных
```sql
TABLE "user" (
    id BIGINT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

TABLE task (
    id BIGINT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    completed BOOLEAN DEFAULT false,
    user_id BIGINT REFERENCES "user"(id)
);
```
## 🧪 Тестирование
### Для тестирования API используйте:

1. **Postman или Insomnia** - для ручного тестирования

2. **curl** - для командной строки

3. **Swagger** - можно добавить позже для документации API

### Пример тестирования через curl:

```bash
# Проверка здоровья приложения
curl http://localhost:8080/actuator/health

# Получение всех задач
curl http://localhost:8080/api/tasks

# Получение всех пользователей
curl http://localhost:8080/api/users
```

## 📝 Лицензия
Этот проект создан для образовательных целей и портфолио.

## 🤝 Контакты
Гаврилин Дмитрий - ddd.gavrilin@gmail.com - https://github.com/MrZloyHomyak