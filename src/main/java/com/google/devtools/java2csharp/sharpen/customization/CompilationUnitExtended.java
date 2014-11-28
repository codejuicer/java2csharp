package com.google.devtools.java2csharp.sharpen.customization;

import java.io.IOException;

import org.eclipse.jdt.core.dom.CompilationUnit;

public class CompilationUnitExtended {

	private char windowsFolderSeparator = '\\';

	private char linuxFolderSeparator = '/';

	private String fileName;

	private String basePath;

	private CompilationUnit compilationUnit;

	public CompilationUnitExtended(String fileName, String basePath,
			CompilationUnit compilationUnit) {
		this.compilationUnit = compilationUnit;
		this.basePath = basePath;
		this.fileName = fileName;
	}

	public String getRelativePath() throws IOException {
		String ret = "";
		if (fileName.contains(basePath)) {
			int lastSlashIndex = -1;
			lastSlashIndex = fileName.lastIndexOf(windowsFolderSeparator);
			if (lastSlashIndex == -1) {
				lastSlashIndex = fileName.lastIndexOf(linuxFolderSeparator);
			}
			if (basePath.length() + 1 < lastSlashIndex) {
				ret = fileName.substring(basePath.length() + 1, lastSlashIndex);
			}
			return ret;
		} else {
			throw new IOException("file is not in base path or some subfolder");
		}
	}

	public String retrieveCSharpFileNameFromJavaFileName(String filename) {
		int dotIndex = filename.lastIndexOf(".java");
		int slashIndex = -1;
		slashIndex = fileName.lastIndexOf(windowsFolderSeparator);
		if (slashIndex == -1) {
			slashIndex = fileName.lastIndexOf(linuxFolderSeparator);
		}
		return filename.substring(slashIndex + 1, dotIndex) + ".cs";
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public CompilationUnit getCompilationUnit() {
		return compilationUnit;
	}

	public void setCompilationUnit(CompilationUnit compilationUnit) {
		this.compilationUnit = compilationUnit;
	}

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}
}
