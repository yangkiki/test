# 部署指南(Deploymen Guide) #

本文是测试环境和生产环境部署整个系统的参考文档。

## 推荐硬件资源 ##

运行整个系统，需要安装和部署以下应用（或服务器）。在实际运行环境中，至少使用以下 **最小配置** ，根据用户访问量，硬件和服务器软件可以进行适当的扩充。

<table>
<tr>
<th>
应用
</th>
<th>
软件
</th>
<th>
最少配置
</th>
<th>
推荐配置
</th>
<th>
说明
</th>
</tr>

<tr>
<td>
API
</td>
<td>Java 8, Tomcat 8
</td>
<td>
1
</td>
<td>
2
</td>
<td>	API 服务器在测试时可以使用一台机器，生产环境至少两台，外加一层 LB
</td>
</tr>

<tr>
<td>
API LB
</td>
<td>	Apache 或 Ngnix
</td>
<td>	0	
</td>
<td>	1	
</td>
<td>API 负载服务器LB（Aapace 或者 Ngnix）
</td>
</tr>

<tr>
<td>
Payment Gateway API
</td>
<td>Java 8, Tomcat 8
</td>
<td>
1
</td>
<td>
2
</td>
<td> 支付网关后台数据处理，与主 API 中分拆出来，以减少对 API 的压力。
</td>
</tr>

<tr>
<td>
Redis	
</td>
<td>Redis	
</td>
<td>1	
</td>
<td>2	
</td>
<td>目前主要用作缓存
</td>
</tr>
<tr>
<td>
RabbitMQ
</td>
<td>RabbitMQ
</td>
<td>1	
</td>
<td>2	
</td>
<td> 消息中间件
</td>
</tr>
<tr>
<td>
Mongo	
</td>
<td>Mongo	
</td>
<td>1	
</td>
<td>2	
</td>
<td>文档服务器，目前主要用于存放各类图片
</td>
</tr>

<tr>
<td>
MySQL	
</td>
<td>MySQL	
</td>
<td>1	
</td>
<td>2	
</td>
<td>核心业务数据库
</td>
</tr>

<tr>
<td>
Console	
</td>
<td>Apache, Ngnix或NodeJS	
</td>
<td>1	
</td>
<td>1
</td>
<td>后台管理程序
</td>
</tr>

<tr>
<td>
Website	
</td>
<td>Apache, Ngnix或NodeJS	
</td>
<td>1	
</td>
<td>2	
</td>
<td>前端外部网站，按需求扩展以后可能会增加多台，在网站前端再加一层LB
</td>
</tr>
<tr>
<td>
Website	LB
</td>
<td>Apache 或 Ngnix
</td>
<td>0	
</td>
<td>1	
</td>
<td>前端外部网站LB
</td>
</tr>
<tr>
<td>
Notification 	
</td>
<td>Java 8, Tomcat 8	
</td>
<td>1	
</td>
<td>2	
</td>
<td>处理短信，邮件
</td>
</tr>

<tr>
<td>
Job	
</td>
<td>Java 8, Tomcat 8	
</td>
<td>1	
</td>
<td>2	
</td>
<td>定时任务处理（财务相关计算，定时邮件）
</td>
</tr>

<tr>
<td>
Broker (Order processing etc)	
</td>
<td>Java 8, 
Tomcat 8	
</td>
<td>1	
</td>
<td>4	
</td>
<td>后台处理程序，分担 API 中高并发业务，目前主要是考虑订单处理，等
</td>
</tr>
</table>

其中，API， Payment Gateway API, Broker, Notifier，Job 是项目开发的产出，需要部署到服务器。

需要说明的是，

1. 测试环境，如果仅仅只是为了功能测试，可以将不同的软件安装至一台服务器。
2. 最终 UAT 测试（用户验收测试）和压力测试，应该提供接近生产环境的测试环境。

我们假设已经硬件环境，基础系统准备就绪。

部署整个系统，必须事先准备好以下软件，以方便软件的部署。

## 所需软件安装 ##

本文不包含操作系统层面的细节，你可以选择你熟悉的 Linux，在开发时我们选择了 Ubuntu Linux 14.04 LTS 版。

基础软件配置具体细节，如果存在无法与本文描述对应的地方，请查阅相应系统官方文档。

### Oracle Java 8


Ubuntu 本身不提供 Oracle Java 8 的 Ubuntu 安装源。但可以通过第三提供的 PPA 源进行安装。 

打开终端工具，输入以下的命令，加入第三方 Java 8 PPA 源。

<pre>
sudo add-apt-repository ppa:webupd8team/java
</pre>

更新系统所有包元信息：

<pre>
sudo apt-get update
</pre>

开发安装 Java 8:

<pre>
sudo apt-get install oracle-java8-installer
</pre>

安装以下工具设置 Java 8 为系统默认虚拟机。

<pre>
sudo apt-get install oracle-java8-set-default
</pre>

确认Java 8 安装完毕:

<pre>
java -version
</pre>

你应该可以看到以下信息：

<pre>
fljr@fljr:~$ java -version
java version "1.8.0_25"
Java(TM) SE Runtime Environment (build 1.8.0_25-b17)
Java HotSpot(TM) 64-Bit Server VM (build 25.25-b02, mixed mode)
</pre>

CentOS 可以安装 Oracle 官方提供的 RPM 包，可以从 [Oracle 官方网站下载](http://java.oracle.com), 但其没有完全提供系统兼容的链接等。

### Apache Tomcat 8 ###

从 [Apache Tomcat官方网站](http://tomcat.apache.org) 上下载最新的 Tomcat 8， 文件名为 apache-tomcat-8.x.x.zip。

解压到硬盘 `/opt/svr` 中，建创一个soft link, tomcat。

<pre>
fljr@fljr:/opt/svr$ ls -al
ls: 初始化月份字符串出错
总用量 12
drwxr-xr-x 3 root root 4096 10?? 27 16:45 .
drwxr-xr-x 4 root root 4096 10?? 28 09:42 ..
drwxr-xr-x 9 fljr fljr 4096 12?? 27 14:37 apache-tomcat-8.0.14
lrwxrwxrwx 1 root root   20 10?? 27 16:45 tomcat -> apache-tomcat-8.0.14
</pre>


### Redis ###


#### 安装 Redis ####

使用系统安装工具安装 Redis, apt 或者 YUM。

#### 设置 Redis ####

默认情况下，Ubuntu 不允许外部访问 Redis。 打开 `/etc/redis/redis.conf` 文件，找到 bind x.x.x.x 一行， 将它注释掉或者改成。

<pre>
bind 0.0.0.0
</pre>

### RabbitMQ ###

#### 安装 RabbitMQ ####

Ubuntu 系统，使用以下命令行安装 RabbitMQ。

    sudo apt-get install rabbitmq-server amqp-tools

启用管理控制台，提供可视化管理界面。

    sudo rabbitmq-plugins enable rabbitmq_management

#### 设置 RabbitMQ ####

确认 RabbitMQ 在运行，打开地址 [http://localhost:15672](http://localhost:15672)，以 guest/guest 登录。

切换到 Admin Tab，创建一个 账号和 vhost, 例如：

    vhost :/f
    username: fuser
    password: fpwd  

### MySQL ###

#### 安装 MySQL ####

使用系统安装工具安装 MySQL, apt 或者 YUM。


#### 设置 MySQL ####

默认情况下，Ubuntu 不允许外部访问 MySQL 。 打开 `/etc/mysql/my.cnf` 文件，找到 bind_address x.x.x.x 一行， 将它注释掉或者改成。

<pre>
bind_addres 0.0.0.0
</pre>

#### 创建数据库 ####

运行 `mysql -uroot -p` 启动 MySQL 客户端。

<pre>
create database flbill default charset utf8 COLLATE utf8_general_ci;
//grant all on flbill.* to 'fluser'@'localhost' identified by 'flpwd';
grant all on flbill.* to 'fluser'@'%' identified by 'flpwd';
flush privileges ;
</pre>

### Mongo ###

#### 安装 MongoDB ####

使用系统安装工具安装 MongoDB, apt 或者 YUM。

#### 设置 MongoDB ####

默认情况下，Ubuntu 不允许外部访问 MongoDB。 打开 `/etc/mongodb.conf` 文件，找到 `bind_ip x.x.x.x` 一行， 将它注释掉或者改成。

<pre>
bind_ip 0.0.0.0
</pre>


### Apache HTTP Server ###

后台运营管理程序是纯静态程序，只需要一个标准的 Http Server, 可以使用 Apache ， Ngnix， 或者 NodeJS。


## 部署 ##

### REST API 和其它支撑子系统的部署 ###

系统核心是由 Java 编写，需要 Apache Tomcat 8 容器运行，同时需要 Java 8 运行环境。

从 [我们的 GitBucket](http://192.168.0.111:8080/gitbucket/) 中取出源文件，在根目录中运行:

<pre>
mvn clean install 
</pre>

目前定义的 Profiles 有 `dev`, `staging`, `test`, `sit`, `uat`, `prod` 等，如果使用其它 Maven Profiles，请带上参数 `-P`。 

例如，使用 Staging Profile 编译。

<pre>
mvn clean install -Pstaging
</pre>

具体参见 [server 项目下的 README.txt](http://192.168.0.111:8080/gitbucket/fljr/server/blob/master/README.md) 。

将 `api/target/bill-api.war`, `notifiter/target/notifiter.war` 等 WAR 包复制到 Tomcat 目录下 webapps 目录。 

1. 如果 Tomcat 8 已经在运行，WAR 会自动部署。
2. 如果 Tomcat 8 处于停止状态，在 `&lt;TOMCAT_8>/bin` 下运行以下命令启动 Tomcat：

<pre>
sh startup.sh
</pre>

### console(后台运营管理) ###


从 [GitBucket](http://192.168.0.111:8080/gitbucket/) 中取出源文件，在根目录中运行:

<pre>
npm install
bower install
gulp clean
gulp 
</pre> 

具体细节参见 [console 项目下的 README.txt](http://192.168.0.111:8080/gitbucket/fljr/console/blob/master/README.md) 。

在 `dist` 目录下会生成最终要部署的文件，包含：

1. 合并，压缩后的 JS 文件。
2. 合并，压缩后的 CSS 文件。
3. index.html 模板压缩文件。
4. 所有的 AngularJS 模板都合并到一个JS文件，并转换成 Angular Template。

最终生成的静态文件，可以部署在 Apache2, Ngnix, NodeJS等。

这里以 Apache 2 作为 console 运行服务器。

创建一个单独的 VirtualHost，其中 `/home/fljr/wwwdata/console/` 是console 项目的文件夹位置。

    <VirtualHost *:80>
        DocumentRoot /home/fljr/wwwdata/console/
        ProxyPass /api/ http://localhost:8080/bill-api/api/
        ProxyPassReverse /api/ http://localhost:8080/bill-api/api/
        ProxyPass /paygw/ http://www.storevm.org:8660/paygw/
        ProxyPassReverse /paygw/ http://www.storevm.org:8660/paygw/
        <Directory /home/fljr/wwwdata/console/>
        Options Indexes FollowSymLinks
        AllowOverride All
        Require all granted
        </Directory>
        ErrorLog ${APACHE_LOG_DIR}/fljr-console-error.log
        CustomLog ${APACHE_LOG_DIR}/fljr-console-access.log combined
    </VirtualHost>

另外要启用一些的 Apache Module，如 http_proxy 等，打开 Apache 配置文件相应的模块是否启用。

将 dist 中生成的文件复制到  `/home/fljr/wwwdata/console/`。

注意路径针对开发集成环境，对生产环境就修改相关路径。