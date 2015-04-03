The backend application
========================

This application serves as backend REST API producer.


##Requirements

   * JDK 8

     Oracle Java 8 is required, go to [Oracle Java website](http://java.oracle.com) to download it and install into your system. 
     
     Optionally, you can set **JAVA\_HOME** environment variable and add *&lt;JDK installation dir>/bin* in your **PATH** environment variable.

   * Apache Maven
   
     Download the latest Apache Maven from [http://maven.apache.org](http://maven.apache.org), and uncompress it into your local system. 
    
     Optionally, you can set **M2\_HOME** environment varible, and also do not forget to append *&lt;Maven Installation dir>/bin* your **PATH** environment variable.  

   * Apache Tomcat 8
   
     Download the latest Apache Tomcat 8 from [http://tomcat.apache.org](http://tomcat.apache.org), and uncompress it into your local system. 
    
     Optionally, you can set **CATALINA\_HOME** environment varible and the value is the location of extracted tomcat folder, and also do not forget to append *&lt;Tomcat Installation dir>/bin* your **PATH** environment variable. 

## Get project source codes

   Clone the codes into your local system via your favorite GIT client, such as TortoiseGit or Eclipse integrated GIT client.

   
   	git clone 114.215.189.121:8080/gitbucket/git/moxian/server.git
   

## Build project via Apache Maven

 
   1. Open your system command line tool, and build the project.
   
		mvn clean install
	
   2. Enter the project root folder, run `mvn tomcat7:run` to start up an embedded tomcat7 to serve this application.
     
    	mvn tomcat7:run
    
   3. Run in embedded Jetty.
  
    	mvn jetty:run
      


## Build project in Spring Tool Suite

   Because this is a Spring based project, the official Spring ToolSuite is highly recommended as the development tool. Or you can get a copy of Java EE bundle from official [Eclipse website](http://www.eclipse.org), and install the Spring ToolSuite plugin from Eclipse Marketplace.

  1. Download Spring ToolSuite from [http://spring.io](http://spring.io), and extract the files into your local system.
  2. Start up Spring ToolSuite, import this project as **Existing Maven project**(under **Maven** folder in the **Import** wizard).
  3. Select **Run as**/**Maven Build** in the root project context menu and build the whole project. In the popup window, in the **goals** field, input **clean install**.
  4. Deploy the project into your favorite server by drag and drop or select **Run as** in the project context menu.

## Build project in NetBeans IDE
	
  Maven projects can be recognised by NetBeans IDE automatically.

  1. Open the project as a normal NetBeans project in NetBeans IDE directly.
  2. Select **Clean and Build** in the root project context menu to build the whole project.
  3. Select **Run** to run the project on an existing servers.

