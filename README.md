# OTUS_RestAssured
Homework for RestAssured

# Positive User test

1. Проверить, что юзер со всеми параметрами успешно создается.
   StatusCode = 200, type = unknown, message = 12345.
   Все поля в базе заполнены верно. 

2. Проверить, что юзер с параметрами по умолчанию(id, username, password) создается.
   Все незаполненные поля null в запросе.
   StatusCode = 200, type = unknown, message = 12346.
   id, UserName, password, UserStatus в базе заполнены верно.

# Negative User test

1. Проверить наличие ошибки "User not found" для user101.
   Status code = 404, Status Line = "HTTP/1.1 404 Not Found",
    code = 1, type = error, message = User not found.
   
2. Проверить, что endpoint /user/{username} не используется с методом POST. 
