package com.google.devtools.java2csharp;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.junit.Test;

public class BuilderTest {

	@Test
	public void testCSharpBuilder() throws MojoExecutionException, IOException {
		String path = getClass().getClassLoader().getResource(".").getPath();
		ConversionConfiguration conf = new ConversionConfiguration();
		conf.setSourcePath(path
				+ "../../src/test/java/com/google/devtools/java2csharp/testclasses");
		conf.setOutputDirectory(path + "outputfolder");
		Java2CsharpMojo mojo = new Java2CsharpMojo();
		mojo.setConversionConfigurations(new ConversionConfiguration[] { conf });
		mojo.execute();

		List<String> expectedFiles = listFilesForFolder(new File(
				path
						+ "expectedFolder"));

		List<String> outputFiles = listFilesForFolder(new File(path
				+ "outputfolder"));
		for (int i = 0; i < expectedFiles.size(); i++) {
			File sourceFile = new File(expectedFiles.get(i));
			File outputFile = new File(outputFiles.get(i));
			assertTrue(FileUtils.contentEquals(sourceFile, outputFile));
		}
	}

	private List<String> listFilesForFolder(final File folder) {
		List<String> files = new ArrayList<String>();
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				files.addAll(listFilesForFolder(fileEntry));
			} else {
				files.add(fileEntry.getAbsolutePath());
			}
		}
		return files;
	}
}
