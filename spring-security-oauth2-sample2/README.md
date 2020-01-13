基础Oauth2.0流程:  

- client端用浏览器和Postman代替
- 资源服务器通过访问授权服务器的TokenEndpoint(/oauth/check_token),
来进行token校验

0. authorize request http://127.0.0.1:8079/auth/oauth/authorize?response_type=code&client_id=client01&state=xyz123
1. 浏览器访问授权服务器`AuthorizationEndpoint`
2. 用户批准授权
3. 用户登录,返回code
4. Postman使用code访问授权服务器换取access_token(存储于redis中)
5. Postman使用access_token访问资源服务器的资源
6. 资源服务器携带access_token访问授权服务器的TokenEndpoint(/oauth/check_token)
7. 授权服务器校验token,校验通过,返回资源服务器Authentication
8. 资源服务器允许client访问资源