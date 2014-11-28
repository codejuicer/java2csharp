package com.google.devtools.java2csharp.sharpen.customization;

import java.util.Map;

import org.apache.maven.plugin.logging.Log;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.IBinding;

import sharpen.core.framework.ASTResolver;

public class CustomASTResolver implements ASTResolver {

	private Log logger;

	private Map<String, CompilationUnitExtended> sourcePathEntry;

	public CustomASTResolver(Log logger,
			Map<String, CompilationUnitExtended> sourcePathEntry) {
		super();
		this.logger = logger;
		this.sourcePathEntry = sourcePathEntry;
	}

	@Override
	public ASTNode findDeclaringNode(IBinding binding) {
		if (binding != null) {
			logger.info("Called resolver for binding " + binding.getName());
			for (String sourceFilePath : sourcePathEntry.keySet()) {
				CompilationUnit cu = sourcePathEntry.get(sourceFilePath)
						.getCompilationUnit();
				ASTNode node = cu.findDeclaringNode(binding);
				if (null != node) {
					logger.info("Found ASTNode " + node.toString());
					return node;
				}
			}
		} else {
			logger.info("Called resolver for null binding");
		}

		return null;
	}

}
