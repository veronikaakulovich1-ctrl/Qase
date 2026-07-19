# README

## Overview

Автотесты для [Qase](https://app.qase.io/) (дипломный проект).  
Покрыты UI (Selenide) и API (RestAssured).

## Stack

- Java 17, Maven, TestNG  
- Selenide, RestAssured  
- Lombok, JavaFaker, Log4j2  

## Project structure

- `ui/pages`, `ui/steps`, `ui/dto` — UI Page Object / Steps  
- `api/adapters`, `api/models` — API adapters и модели  
- `tests/ui`, `tests/api` — тесты  
- `src/test/resources/*.xml` — TestNG suites  

## How to run

```bash
# UI regression (default в pom)
mvn test

# UI smoke
mvn test -DsuiteXmlFile=src/test/resources/smoke.xml

# Cross-browser (smoke, Chrome / Edge / Firefox)
mvn test -DsuiteXmlFile=src/test/resources/CrossBrowser.xml

# API
mvn test -DsuiteXmlFile=src/test/resources/api-smoke.xml
mvn test -DsuiteXmlFile=src/test/resources/api-regression.xml
```

Config: `src/test/resources/config.properties`  
(`base.url`, `api.base.url`, `user`, `password`, `api.token`)

## Test Checklist

### UI

#### 1. LoginTest
- **Login page opens** — отображаются элементы email, password, Sign in  
- **Forgot password link** — открывается password reset page 
- **Create an account link** — открывается signup page 
- **Required field errors** — ошибки валидации при вводе невалидных данных  
- **Remember me** — чекбокс "Remember me" виден и кликабелен  
- **Sign out** — возврат на login  

#### 2. RegistrationTest
- **Signup page opens** — поля регистрации отображаются  
- **Successful registration** — отображается успешная регистрация пользователя  
- **Validation errors** — ошибки валидации при вводе невалидных данных 

#### 3. ProjectTest
- **Create project** — Создание проекта с обязательными полями name + code  
- **Create full project** — Создание проекта с обязательными полями и description + Public access  
- **Update settings** — изменения данных в полях name и code  

#### 4. SuiteTest
- **Create suite** — Создание suite c обязательным полeм name 
- **Create full suite** — Создание suite c полями name, description, preconditions  

#### 5. CaseTest
- **Create case** — Создание кейса с полями title + Status / Severity / Priority / Type  
- **Create case with attachment** — Создание кейса с загрузкой файла  
- **Quick test** — Создание кейса через Quick test в suite  
- **Delete case** — Удаление проекта и отображение кнопки "Create new case"  

#### 6. PlanTest
- **Create plan** — Создание тест-плана с созданными кейсами  
- **Delete plan** — Удаление тест-плана и проверка что плана нет в списке  

#### 7. RunTest
- **Create run** — Создание тест-рана с созданными кейсами  
- **Delete run** — Удаление тест-рана и проверка что его нет в списке

*Cleanup:* удаление проекта в `@AfterMethod` (`projectToDelete`).

### API

#### ProjectAPITest
- Create / Delete project  

#### CaseAPITest / SuiteAPITest / PlanAPITest / RunAPITest
- Create / Get / Update / Delete  

#### ResultAPITest
- Create result (разные статусы) / Delete  

## Suites & groups

| Suite | Что запускает |
|-------|----------------|
| `smoke.xml` | `groups = "smoke"` |
| `regression.xml` | `groups = "regression"` |
| `CrossBrowser.xml` | UI smoke на 3 браузерах |
| `api-smoke.xml` / `api-regression.xml` | API-сценарии |
