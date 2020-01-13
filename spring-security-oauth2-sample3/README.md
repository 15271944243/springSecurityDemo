Oauth2.0 + JWT 流程:  

- client端用浏览器和Postman代替
- 资源服务器通过访问授权服务器的check_token(/oauth/check_token)端点,
来进行token校验
- access_token为JWT(签名的key默认是随机字符串,存储在内存中:JwtAccessTokenConverter#signingKey)
- 签名的key可以通过JwtAccessTokenConverter#setSigningKey配置

0. authorize request http://127.0.0.1:8079/auth/oauth/authorize?response_type=code&client_id=client01&state=xyz123
1. 浏览器访问授权服务器`AuthorizationEndpoint`
2. 用户批准授权
3. 用户登录,返回code
4. Postman使用code访问授权服务器换取access_token
5. 授权服务器生成access_token(JWT)
6. Postman使用access_token访问资源服务器的资源
7. 资源服务器携带access_token访问授权服务器的check_token(/oauth/check_token)端点
8. 授权服务器校验token,校验通过,返回资源服务器Authentication
9. 资源服务器允许client访问资源

#### 这里的token校验到底是如何处理的?
这里只做了很简单的校验,比如:
- JWT格式是否正常  
- access_token是否过期
- JWT的signature进行验签