最基础的Oauth2.0流程:  

client端用浏览器和Postman代替

1. 浏览器访问授权服务器获取code
2. Postman使用code访问授权服务器换取access_token(存储于redis中)
3. Postman使用access_token访问资源服务器的资源
4. 资源服务器校验token,校验通过,允许访问资源