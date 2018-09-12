# swagger2markdown


## 模板格式

## 1. 登录
### 1.1 功能描述
提供手机号和密码的登录方式。
### 1.2 请求说明
> 请求方式：POST
> 请求URL ：/login

### 1.3 请求参数
字段       |字段类型       |字段说明
------------|-----------|-----------
phone       |int        |手机号
password       |string        |密码

### 1.4 返回结果

```json  
{
  "data": {
    "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOi8vc2FsZS1hcGkuZGV2L2xvZ2luIiwiaWF0IjoxNDkxNTMyOTI4LCJleHAiOjE0OTIyNTI5MjgsIm5iZiI6MTQ5MTUzMjkyOCwianRpIjoiN1hCUXdwN1FHZmxUdHVVQiIsInV1aWQiOiI1MDZjYWY3MCJ9.FyyXagHtBfDBtMJZPV_hm2q6CVULpY63JPDGDHXc"
  },
  "code": "200",
  "msg": "SUCCESS"
}
```
### 1.5 返回参数
字段       |字段类型       |字段说明
------------|-----------|-----------
token       |string        |token值
### 1.6 错误状态码
状态码       |说明
------------|-----------
3001       |其他认证错误信息！
3002       |用户不存在！
3003       |用户名或密码有误！
