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



4) 
