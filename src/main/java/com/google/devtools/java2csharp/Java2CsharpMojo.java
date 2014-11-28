/*
 * Copyright 2014 Giuseppe Gerla. All Rights Reserved.
 *
 * Licensed under the GNU General Public License, Version 2.0;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.gnu.org/licenses/gpl2.txt
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 
package com.google.devtools.java2csharp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FileASTRequestor;

import com.google.devtools.java2csharp.sharpen.customization.CSharpFileWriter;
import com.google.devtools.java2csharp.sharpen.customization.CompilationUnitExtended;
import com.google.devtools.java2csharp.sharpen.customization.CustomASTResolver;
import com.google.devtools.java2csharp.sharpen.customization.CustomFileASTRequestor;

import sharpen.core.CSharpBuilder;
import sharpen.core.Configuration;
import sharpen.core.ConfigurationFactory;
import sharpen.core.csharp.ast.CSCompilationUnit;
import sharpen.core.framework.ASTResolver;
import sharpen.core.framework.ASTUtility;
import sharpen.core.framework.Environment;
import sharpen.core.framework.Environments;

/**
 * @goal java2csharp
 * @description Java To C# class converter
 * @requiresDependencyResolution compile
 * @threadSafe
 */
public class Java2CsharpMojo extends AbstractMojo {

	/**
	 * @parameter
	 * @required
	 */
	private ConversionConfiguration[] conversionConfigurations;

	private List<String> inputPaths;

	private List<String> charsetEntry;

	private Map<String, CompilationUnitExtended> sourcePathEntry;

	private void listFilesForFolder(final File folder) {
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				inputPaths.add(fileEntry.getAbsolutePath());
				listFilesForFolder(fileEntry);
			} else {
				charsetEntry.add("UTF-8");
				sourcePathEntry.put(fileEntry.getAbsolutePath(), null);
			}
		}
	}

	private void processFolderToFindJavaClass(File inputFolder) {
		inputPaths = new ArrayList<String>();
		inputPaths.add(inputFolder.getAbsolutePath());
		sourcePathEntry = new HashMap<String, CompilationUnitExtended>();
		charsetEntry = new ArrayList<String>();
		listFilesForFolder(inputFolder);
	}

	private void createCompilationUnitsForJavaFiles(final File inputFolder) {
		// first process input folder to find java file
		processFolderToFindJavaClass(inputFolder);

		// now prepare ASTParser to process al java file found
		ASTParser parser = ASTParser.newParser(AST.JLS4);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setResolveBindings(true);
		// parser.setBindingsRecovery(true);
		parser.setEnvironment(null,
				new String[] { inputFolder.getAbsolutePath() },
				new String[] { "UTF-8" }, true);

		// add compiler compliance rules to convert enums
		@SuppressWarnings("unchecked")
		Hashtable<String, String> options = JavaCore.getOptions();
		options.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_1_5);
		options.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM,
				JavaCore.VERSION_1_5);
		options.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_5);

		parser.setCompilerOptions(options);
		FileASTRequestor requestor = new CustomFileASTRequestor(getLog(),
				inputFolder.getAbsolutePath(), sourcePathEntry);
		parser.createASTs(sourcePathEntry.keySet().toArray(new String[0]),
				charsetEntry.toArray(new String[0]), new String[] { "" },
				requestor, null);
	}

	private Configuration createCustomConfiguration() {
		Configuration configuration = ConfigurationFactory
				.defaultConfiguration();
		configuration.enableNativeTypeSystem();
		configuration.enableOrganizeUsings();

		return configuration;
	}

	private void processCompilationUnitAndGenerateCSharpUnit(
			CompilationUnit cu, CSCompilationUnit compilationUnit) {
		ASTUtility.checkForProblems(cu, false);

		ASTResolver resolver = new CustomASTResolver(getLog(), sourcePathEntry);

		final Environment environment = Environments
				.newConventionBasedEnvironment(cu, createCustomConfiguration(),
						resolver, compilationUnit);
		try {
			Environments.runWith(environment, new Runnable() {
				public void run() {
					CSharpBuilder builder = new CSharpBuilder();
					builder.run();
				}
			});
		} catch (Exception e) {
			getLog().error("Error during parsing java file", e);
		}
	}

	public void execute() throws MojoExecutionException {
		getLog().info("start Java2Csharp execution");

		if (getLog().isDebugEnabled()) {
			getLog().debug(
					"Java2Csharp analize " + conversionConfigurations.length
							+ " configurations");
		}
		for (ConversionConfiguration configuration : conversionConfigurations) {
			final File inpuFolder = new File(configuration.getSourcePath());
			if (inpuFolder.exists() && inpuFolder.isDirectory()) {
				File outputFolder = new File(configuration.getOutputDirectory());
				if (outputFolder.exists() && outputFolder.isDirectory()) {
					try {
						createCompilationUnitsForJavaFiles(inpuFolder);

						for (String filename : sourcePathEntry.keySet()) {
							CompilationUnitExtended cue = sourcePathEntry
									.get(filename);
							CompilationUnit cu = cue.getCompilationUnit();
							final CSCompilationUnit compilationUnit = new CSCompilationUnit();

							processCompilationUnitAndGenerateCSharpUnit(cu,
									compilationUnit);

							String outputFileName = cue
									.retrieveCSharpFileNameFromJavaFileName(filename);

							String outputSubFolderName = configuration
									.getOutputDirectory()
									+ "/"
									+ cue.getRelativePath();
							File outputSubFolder = new File(outputSubFolderName);
							if (outputSubFolder.exists()
									|| outputSubFolder.mkdir()) {
								CSharpFileWriter writer = new CSharpFileWriter(
										outputSubFolderName + "/"
												+ outputFileName,
										compilationUnit);

								writer.writeFile();
							}
						}
					} catch (IOException e) {
						getLog().error("Error during reading java file", e);
					}
				} else {
					throw new MojoExecutionException(
							"Invalid output directory "
									+ configuration.getOutputDirectory());
				}
			} else {
				throw new MojoExecutionException("Invalid source path "
						+ configuration.getSourcePath());
			}
			getLog().info(
					"Java2Csharp configuration " + configuration.getName()
							+ " created");
		}
	}

	public void setConversionConfigurations(
			ConversionConfiguration[] xsdConfigurations) {
		this.conversionConfigurations = xsdConfigurations;
	}
}
