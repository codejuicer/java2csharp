java2csharp
===========

maven plugin to convert java classes to c# using a subset of sharpen library.



ABOUT SHARPEN
-------------

Sharpen is an Eclipse plugin which can covert Java code to C#.
This free plugin has been implemented by db4o.

See the following pages for more information:

Product description:
http://developer.db4o.com/Projects/html/projectspaces/db4o_product_design/sharpen.html

User guide:
http://developer.db4o.com/Documentation/Reference/db4o-7.12/java/reference/html/reference/sharpen.html

Source code:
https://source.db4o.com/db4o/trunk/sandbox/blogs/SharpenExamples/ContactList


You can find the original sharpen code used for this plugin here:
https://github.com/slluis/sharpen


## Installation

java2csharp plugin are available on the [releases page](https://github.com/ggerla/java2csharp/releases). 

### Integration with Maven

To use the official release of java2csharp, please use the following snippet in your pom.xml

```xml
    <build>
		<plugins>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>java2csharp-maven-plugin</artifactId>
				<version>1.0.0</version>
				<executions>
					<execution>
						<phase>compile</phase>
						<goals>
							<goal>java2csharp</goal>
						</goals>
					</execution>
				</executions>
				<configuration>
					<conversionConfigurations>
						<conversionconfiguration>
							<name>
								name of configuration unit
							</name>
							<sourcePath>
								path to your source path
							</sourcePath>
							<outputDirectory>
								path to your output directory
							</outputDirectory>
						</conversionconfiguration>
					</conversionConfigurations>
				</configuration>
			</plugin>
			
			....
			</plugins>
		</pluginManagement>
	</build>
```
