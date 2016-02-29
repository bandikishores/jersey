# jersey-springmvc-web-service
Testing Jersey & springmvc web service in one server with logback.<br/>
making use of JAXB for automatic XML conversion and Jersey support for Jackson to perform automatic JSON conversion.

Problems:
1) If one of the class level Path is defined and the other class just method level is defined then jersey will not search inside the class which doesn't have Path defined.
2) Tradional ServletRequest cannot be injected by Jersey. Only HttpServletRequest is supported.
3) 

if you encounter 
		SEVERE: Error listenerStart
During application startup, then 
	a) goto WEB-INF/classes
	b) create a file named logging.properties
	c) paste below lines, save.

org.apache.catalina.core.ContainerBase.[Catalina].level = INFO
org.apache.catalina.core.ContainerBase.[Catalina].handlers = java.util.logging.ConsoleHandler

	d) Now restart, Tomcat will display the issue for the startup.



4) When using Spring-Jersey jar for loading spring dependency, then Don't include 
	org.springframework.web.context.ContextLoaderListener 
  as a listener in your web.xml

  Because apparently Spring-Jersey jar has a ContextLoader which causes issue of double initializer's of Context Servlet during server start up.
Resulting in Exception

SEVERE: Exception sending context initialized event to listener instance of class org.springframework.web.context.ContextLoaderListener
java.lang.IllegalStateException: Cannot initialize context because there is already a root application context present - check whether you have multiple ContextLoader* definitions in your web.xml!
        at org.springframework.web.context.ContextLoader.initWebApplicationContext(ContextLoader.java:277)
        at org.springframework.web.context.ContextLoaderListener.contextInitialized(ContextLoaderListener.java:106)


5) 
