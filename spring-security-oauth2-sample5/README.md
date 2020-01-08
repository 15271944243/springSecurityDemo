Oauth2.0 + JWT 流程:  

- client端用浏览器和Postman代替
- access_token为JWT
- 资源服务器启动时通过访问授权服务器的token_key(/oauth/token_key)端点,
来获取JWT签名秘钥(即JWK)

1. 资源服务器启动时从认证服务器上获取JWT签名秘钥(即JWK)
2. 浏览器访问授权服务器获取code
3. Postman使用code访问授权服务器换取access_token
4. 授权服务器生成access_token(JWT)
5. Postman使用access_token访问资源服务器的资源
6. 资源服务器校验token,校验通过,返回资源服务器Authentication
7. 资源服务器允许client访问受保护的资源

#### 这里的token校验到底是如何处理的?
这里只做了很简单的校验,比如:
- JWT格式是否正常  
- access_token是否过期
- JWT的signature进行验签(使用步骤1获取的JWK)