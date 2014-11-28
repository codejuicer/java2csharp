package com.google.devtools.java2csharp.sharpen.customization;

import java.util.Map;

import org.apache.maven.plugin.logging.Log;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FileASTRequestor;
import org.eclipse.jdt.core.dom.IBinding;

public class CustomFileASTRequestor extends FileASTRequestor {
	private Log logger;

	private String inputFolder;

	private Map<String, CompilationUnitExtended> sourcePathEntry;

	public CustomFileASTRequestor(Log logger, String inputFolder,
			Map<String, CompilationUnitExtended> sourcePathEntry) {
		super();
		this.logger = logger;
		this.inputFolder = inputFolder;
		this.sourcePathEntry = sourcePathEntry;
	}

	public void acceptBinding(String bindingKey, IBinding binding) {
		if (logger.isDebugEnabled()) {
			logger.debug("Called requestor with bindingKey " + bindingKey
					+ " and binding " + binding.getName());
		}
	}

	public void acceptAST(String sourceFilePath, CompilationUnit ast) {
		CompilationUnitExtended cue = new CompilationUnitExtended(
				sourceFilePath, inputFolder, ast);
		sourcePathEntry.put(sourceFilePath, cue);
	}
}
