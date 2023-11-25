```bash
curl http://localhost:8080/api/users/41 | python3 -m 'json.tool'
```

```bash
curl -X DELETE -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJoZXhsZXRAZXhhbXBsZS5jb20iLCJleHAiOjE3MDA5NjMzOTAsImlhdCI6MTcwMDkyNzM5MH0.B3UoBDzQ_ujkVaO9aB_8uOKFAD39ixAdfg6ZVPUBS8M" http://localhost:8080/api/users/1 | python3 -m 'json.tool'
```

```bash
curl http://localhost:8080/welcome && echo ""
```

```bash
curl -X POST -H "Content-Type: application/json" -d '{
  "username": "hexlet@example.com",
  "password": "qwerty"
}' http://localhost:8080/api/login && echo ""
```

```bash
curl -X POST -H "Content-Type: application/json" -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJoZXhsZXRAZXhhbXBsZS5jb20iLCJleHAiOjE3MDA5NjE1ODgsImlhdCI6MTcwMDkyNTU4OH0.nr--SrJjGrJtJclc0dqc4gV50GKsfn9z15RviYxitWQ" -d '{
  "firstName": "John",
  "lastName": "Doe",
  "password": "johny",
  "email": "johndoe@example.com"
}' http://localhost:8080/api/users | python3 -m json.tool
```

```bash
curl -X PUT -H "Content-Type: application/json" -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJoZXhsZXRAZXhhbXBsZS5jb20iLCJleHAiOjE3MDA5NjMzOTAsImlhdCI6MTcwMDkyNzM5MH0.B3UoBDzQ_ujkVaO9aB_8uOKFAD39ixAdfg6ZVPUBS8M" -d '{
  "firstName": "New First Name",
  "lastName": "New Last Name"
}' http://localhost:8080/api/users/1 | python3 -m 'json.tool'
```

```bash
curl -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJoZXhsZXRAZXhhbXBsZS5jb20iLCJleHAiOjE3MDA5NjE3MjMsImlhdCI6MTcwMDkyNTcyM30.K4TauLvn8EBCC2MPPAvKUYgd5PRDHs0JYwuQJCb3_Ek" http://localhost:8080/api/users | python3 -m 'json.tool'
```