开放平台例子
===========
步骤:
1 创建mysql数据库openplatform

2 执行openplatform.sql文件创建表和初始化数据

3 用8080端口启动opentform-server项目

4 访问http://127.0.0.1:8080/openplatform-server/register注册一个帐号

5 访问http://127.0.0.1:8080/openplatform-server/login并登录(如果是走流程4，该步可以省略，刚注册帐号默认会登录)

6 访问http://127.0.0.1:8080/openplatform-server/open/index,并添加一个应用

7 将添加的应用获取的appkey，secret和redirectUrl填写到opentform-client项目的oauth.properties文件里

8 用8081端口启动opentform-client项目,新打开一个浏览器，访问http://127.0.0.1:8081/openplatform-client  (新打开一个浏览器目的是为了重新授权，因为在同一个浏览器下4,5步骤已经授权)

9 点击"开始授权"，应用跳转到服务器登录授权页面，填写步骤4注册的帐号，并登录

10 登录完后请"授权"

11 授权完后，点击"获取用户信息"，就可以获取缓存的用户信息

说明:默认的appkey:874146317135
           默认的secret:k7RSQH46AKp0bfmJS78oRqaCnW0H
          默认的帐号密码:dylan/111111
          如果启动项目的端口与上面步骤不一样，需要修改openplatform-client项目的oauth.properties和openplatform-sdk项目的_oauth.properties相应的选项