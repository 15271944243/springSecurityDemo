基础Oauth2.0流程:  

- client端用浏览器和Postman代替
- 授权服务器创建token后写入redis,资源服务器从redis中取出token

0. authorize request http://127.0.0.1:8079/auth/oauth/authorize?response_type=code&client_id=client01&state=xyz123
1. 浏览器访问授权服务器`AuthorizationEndpoint`
2. 用户批准授权
3. 用户登录,返回code
2. Postman使用code访问授权服务器换取access_token(存储于redis中)
3. Postman使用access_token访问资源服务器的资源
4. 资源服务器从redis取出token,校验token,校验通过,允许访问资源