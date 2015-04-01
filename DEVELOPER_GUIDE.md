开发人员手册(Developer Guide)
===========================

系统后台 API 主要由 Java 开发，前端界面（包含运营管理界面，手机端 APP）使用 JavaScript/NodeJS。


## REST API 开发

### 基本需求（Requirements）

   * Oracle JDK 8

     必须安装 Oracle Java 8，从 [Oracle 网站](http://java.oracle.com) 下载最新的 Oracle JDK 8，安装到系统。 
     
     设置 `JAVA _HOME` 环境变量，指向 Java 安装位置，并把 `JAVA_HOME\bin` 追加加到系统 `PATH` 中。

   * Apache Maven
   
     从 [Apache Maven 官方网站](http://maven.apache.org) 下载最新的 Maven 3.x, 解压到本地硬盘 。 
    
     设置 `M2_HOME` 环境变量，指向 Apache Maven 文件夹位置，并把 `M2_HOME\bin` 追加加到系统 `PATH` 中。 

   * Apache Tomcat 8
   
     从 [Apache Tomcat 官方网站](http://tomcat.apache.org) 下载最新的 Apache Tomcat 8, 解压到本地硬盘 。 
     
     设置 `CATALINA\_HOME` 环境变量，指向 Apache Tomcat 文件夹位置，并把 `CATALINA\_HOME\bin` 追加加到系统 `PATH` 中。
    

### 获取源代码

   使用 Git 命令行 或者你熟悉的 Git 客户端（如 TortoiseGit 或者 IDE 集成的 GIT 客户端 ）从 Gitbucket 服务器上取出源代码。
   
   
    http://192.168.0.111:8080/gitbucket/git/fljr/server.git
   
   
### 使用 Apache Maven 编译项目

 
  1. 打开命令行终端工具，在项目根目录下输入以下命令编译整个项目。
   
	    mvn clean install
	   
  2. 进入相关 WEB 模块 `mvn tomcat7:run` 启动一个嵌入式 Tomcat 7 来运行这个 WEB 程序。
  
        mvn tomcat7:run
        
    也可以用 Jetty 来运行项目。
    
        mvn jetty:run
      

### IDE 设置

目前比较流行的 IDE 包括 NetBeans， Eclipse， IntelliJ IDEA（商业软件），推荐使用基于 Eclipse 的 Spring 官方开发工具 Spring ToolSuite。

#### Spring ToolSuite

   从 [Spring 官方网站](http://spring.io)下载最新 Spring ToolSuite， 或者从 [Eclipse 网站](http://www.eclipse.org)下载 Eclipse Java EE Bundle，然后从 Eclipse Marketplace 中安装 Spring ToolSuite 插件。
   
  1. 启动 Spring ToolSuite。
  2. 从 **File** 菜单或者 **Packages** 视图 Context Menu 中选择 **Import**。
  3. 在 **Import** 窗口选择 **Maven**/ **Existing Maven projects**。
  4. 选择 `server` 源文件文件夹位置，导入IDE。 如果之前没有使用过 Apache Maven， 可能需要一点时间下载项目依赖，请耐心等待 IDE 完成导入操作。
  3. 在 **Packages** 视图中右键点击项目 Context Menu 中选择 **Run as**/ **Maven Build** 来编译整个项目。弹出的 **Maven Build** 窗口, `goals` 一栏输入 `clean install`。
  4. 可以通过拖放操作直接项目拖到 **Servers** 视图中的服务器节点上，或者点击项目的 Context Menu 的 **Run as*/ **Application Server**, 弹出窗口选择服务器，将项目部署到服务器上运行。

#### NetBeans IDE
	
   NetBeans IDE 能够识别 Maven 项目，可以像打开一个传统 NetBeans 项目那样直接打开 Maven 项目。

  1. 打开 server 项目。
  2. 在项目 Context menu 中选择 **Clean and Build** 编译项目。
  3. 选择 **Run** 将项目部署到服务器上运行。


## Console（后台运行管理系统）

Console 完全由静态页面组成，主要使用 Google 的 AngularJS 和 Bootstrap 框架。项目使用 NodeJS 体系构建。

### 必需软件

  1. NodeJS
   
     从 [NodeJS 官方网站](http://nodejs.org) 下载最新的 NodeJS，安装到系统中。安装完毕，NodeJS 会自动添加NodeJS 工具命令到 `PATH` 中去。
   
  2. 安装 Bower
   
        npm installl -g bower

  3. 安装 Gulp
   
        npm installl -g gulp

### 获取源代码

从 Gitbucket 上获取 console 源代码。
    

    http://192.168.0.111:8080/gitbucket/git/fljr/console.git


### 编译

进入项目根目录，使用以下命令来下载项目的依赖。
    

    npm install
    bower install

   
运行项目。

    gulp


默认情况下，gulp 会执行 gulpfile.js 中的 `default` 任务。执行完毕，gulp 会自动打开浏览器，跳转到首页。
    
其中 gulpfile.js 文件中， 针对不同的环境 Gulp 也定义了很多不同的任务。
    
### IDE 环境

console 主要由 Javascript 编写，Javascript 是一门弱类型语言，没有哪个 IDE 能够做像 Java 那样支持 Javascript。你可以用你喜欢的文本编辑器，如 Editplus， Sublime，Textmate等。

NetBeans IDE 提供很好的 Javascript 支持，并支持 AngularJS 指令。

Intellij Webstorm 也是开发前端产品的利器，不过它是商业产品。