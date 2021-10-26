Oauth2.0 + 系统自动批准授权(无需用户批准授权) 流程:  

> 针对scope设置自动授权: clients.autoApprove("app");  
oidc里还可以针对白名单进行自动授权

- client端用浏览器和Postman代替


0. authorize request http://127.0.0.1:8079/auth/oauth/authorize?response_type=code&client_id=client01&scope=app&state=xyz123&redirect_uri=http%3a%2f%2f
1. 浏览器访问授权服务器`AuthorizationEndpoint`
2. 系统批准授权: 根据配置的自动批准scope(ClientDetailsServiceConfigurer#autoApprove)进行校验
4. 若request里的scope都是autoApprove,则系统自动批准授权;若不是,则跳转到用户批准授权页面(/oauth/confirm_access)让进行用户授权
5. 用户登录成功,返回code
6. 后续与sample2相同
