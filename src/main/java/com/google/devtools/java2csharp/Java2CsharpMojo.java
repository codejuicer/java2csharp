/*
 * Copyright 2014 Giuseppe Gerla. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.devtools.java2csharp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;

/**
 * @goal java2csharp
 * @description Java To C# class converter
 * @requiresDependencyResolution compile
 * @threadSafe
 */
public class Java2CsharpMojo extends AbstractMojo {

	/**
	 * @parameter default-value="${project}"
	 * @required
	 */
	private MavenProject project;

	/**
	 * @parameter default-value="C:/Program Files (x86)/Xsd2Code"
	 */
	private String xsdToolPath;

	/**
	 * @parameter
	 * @required
	 */
	private XsdConfiguration[] xsdConfigurations;

	/**
	 * @parameter property="project.build.outputDirectory"
	 * @required
	 */
	private String classpath;

	/**
	 * @parameter property="project.compileClasspathElements"
	 * @required
	 */
	private List<?> classpathElements;

	private String openShellCommand = "cmd";

	private String xsdTool = "xsd2code";

//	private String xsdToolParameters = " /c /out:%s %s";

	private String inputPath;

	private String outputFileName;

	private String xsdFileName;

	private String cNameSpace;

	private void extractXsdParameter(String xsdFileName, String className) {
		int inputPathIndex = xsdFileName.lastIndexOf('/');
		inputPath = xsdFileName.substring(0, inputPathIndex);

		int outputFileNameIndex = xsdFileName.substring(inputPathIndex + 1)
				.lastIndexOf('.');
		outputFileName = xsdFileName.substring(inputPathIndex + 1,
				outputFileNameIndex + inputPathIndex + 1) + ".cs";

		this.xsdFileName = xsdFileName.substring(inputPathIndex + 1);

		int nameSpaceIndex = className.lastIndexOf('.');
		cNameSpace = className.substring(0, nameSpaceIndex);
	}

	public void execute() throws MojoExecutionException {
		getLog().info("start Java2Csharp execution");
		ClassLoaderSwitcher clSw = new ClassLoaderSwitcher(getLog());

		getLog().debug(
				"Java2Csharp analize " + xsdConfigurations.length
						+ " configurations");
		for (XsdConfiguration configuration : xsdConfigurations) {
			try {
				getLog().debug(
						"Java2Csharp try to load class "
								+ configuration.getClassName());
				getLog().debug("Java2Csharp loaded classpath " + classpath);
				ClassLoader loader = clSw.switchClassLoader(project, classpath,
						classpathElements);
				getLog().debug("Java2Csharp loaded classloader " + loader);
				Class<?> clazz = loader.loadClass(configuration.getClassName());
				JaxbTool jaxbTool = new JaxbTool(getLog(), clazz);
				getLog().debug(
						"Java2Csharp try to print schema "
								+ configuration.getXsdFileName());
				jaxbTool.printSchema(configuration.getXsdFileName());
				getLog().info(
						"Schema " + configuration.getXsdFileName() + " created");
			} catch (IOException | ClassNotFoundException e) {
				getLog().error("Error during schema generation.", e);
			}
		}

		for (XsdConfiguration configuration : xsdConfigurations) {
			try {
				extractXsdParameter(configuration.getXsdFileName(),
						configuration.getClassName());

				// Open shell
				Process child = Runtime.getRuntime().exec(openShellCommand);
				new Thread(new SyncPipe(child.getErrorStream(), System.err))
						.start();
				new Thread(new SyncPipe(child.getInputStream(), System.out))
						.start();

				// Get output stream to write from it
				PrintWriter stdin = new PrintWriter(child.getOutputStream());

				stdin.println("cd " + inputPath);
				stdin.println(String.format("\"" + xsdToolPath + "/" + xsdTool
						+ "\" " + xsdFileName + " " + cNameSpace + " /o "
						+ outputFileName + " /if-"));

				stdin.println("move /Y " + outputFileName + " "
						+ configuration.getOutputDirectory());
				stdin.close();
			} catch (IOException e) {
				getLog().error(
						"Error during command execution for "
								+ configuration.getXsdFileName(), e);
			}
		}
	}
}
