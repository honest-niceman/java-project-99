```bash
curl http://localhost:8080/api/users/41 | python3 -m 'json.tool'
```

```bash
curl -X DELETE http://localhost:8080/api/users/41 | python3 -m 'json.tool'
```

```bash
curl -X POST -H "Content-Type: application/json" -d '{
  "firstName": "John",
  "lastName": "Doe",
  "password": "johny",
  "email": "johndoe@example.com"
}' http://localhost:8080/api/users | python3 -m 'json.tool'
```

```bash
curl -X PUT -H "Content-Type: application/json" -d '{
  "firstName": "New First Name",
  "lastName": "New Last Name",
  "email": "newemail@example.com"
}' http://localhost:8080/api/users/40 | python3 -m 'json.tool'
```

```bash
curl http://localhost:8080/api/users | python3 -m 'json.tool'
```


