Oauth2.0 + JWT 流程:  

- client端用浏览器和Postman代替
- access_token为JWT
- 资源服务器访问授权服务器的自定义jwk-set端点,来获取JWT签名公钥(即JWK公钥,RSA算法)
- JWT签名公钥不是服务启动时获取的,而是第一次访问时获取的

0. authorize request http://127.0.0.1:8079/auth/oauth/authorize?response_type=code&client_id=client01&state=xyz123
1. 浏览器访问授权服务器`AuthorizationEndpoint`
2. 用户批准授权
3. 用户登录,返回code
4. Postman使用code访问授权服务器换取access_token
5. 授权服务器生成access_token(JWT),并使用JWT私钥签名
6. Postman使用access_token访问资源服务器的资源
7. 资源查询内存中是否存在token的kid对应JWT公钥,若没有
则通过配置授权服务器的jwk-set端点获取,并放入内存中
8. 资源服务器校验token
9. 校验通过,资源服务器允许client访问受保护的资源

#### 这里的token校验到底是如何处理的?
这里只做了很简单的校验,比如:
- JWT格式是否正常
- 验证kid是否存在
- 根据kid获取内存中的JWK公钥
- 使用JWK公钥验签
- access_token是否过期