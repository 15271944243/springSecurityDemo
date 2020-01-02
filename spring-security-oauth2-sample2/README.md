基础Oauth2.0流程:  

- client端用浏览器和Postman代替
- 资源服务器通过访问授权服务器的check_token(/oauth/check_token)端点,
来进行token校验

1. 浏览器访问授权服务器获取code
2. Postman使用code访问授权服务器换取access_token(存储于redis中)
3. Postman使用access_token访问资源服务器的资源
4. 资源服务器携带access_token访问授权服务器的check_token(/oauth/check_token)端点
5. 授权服务器校验token,校验通过,返回资源服务器Authentication
6. 资源服务器允许client访问资源