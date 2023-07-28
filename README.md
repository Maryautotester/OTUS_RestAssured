# OTUS_RestAssured
Homework for RestAssured

# Create User test

1. Проверить, что юзер со всеми параметрами успешно создается.
  StatusCode = 200, type = unknown, message = 12345

2. Проверить, что юзер с параметрами по умолчанию(id, username, password) создается.
   Все незаполненные поля null в запросе.
   StatusCode = 200, type = unknown, message = 12346

# Get User test

3. Проверить, что DefaultUser созданный в п.2 находится в базе.
   Status code = 200.
   id, UserName, password, UserStatus заполнены верно.

4. Проверить наличие ошибки "User not found" для user101.
   Status code = 404, Status Line = "HTTP/1.1 404 Not Found",
    code = 1, type = error, message = User not found.
   
5. Проверить, что endpoint /user/{username} не используется с методом POST. 
