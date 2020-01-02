基础Oauth2.0流程:  

- client端用浏览器和Postman代替
- 授权服务器创建token后写入redis,资源服务器从redis中取出token

1. 浏览器访问授权服务器获取code
2. Postman使用code访问授权服务器换取access_token(存储于redis中)
3. Postman使用access_token访问资源服务器的资源
4. 资源服务器从redis取出token,校验token,校验通过,允许访问资源