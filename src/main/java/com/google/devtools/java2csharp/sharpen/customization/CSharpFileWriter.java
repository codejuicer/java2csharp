package com.google.devtools.java2csharp.sharpen.customization;

import java.io.FileWriter;
import java.io.IOException;

import sharpen.core.ConfigurationFactory;
import sharpen.core.csharp.CSharpPrinter;
import sharpen.core.csharp.ast.CSCompilationUnit;

public class CSharpFileWriter {

	private String fileName;

	private CSCompilationUnit cscu;

	public CSharpFileWriter(String fileName, CSCompilationUnit cscu) {
		this.fileName = fileName;
		this.cscu = cscu;
	}

	public void writeFile() throws IOException {
		if (!cscu.ignore() && !cscu.types().isEmpty()) {
			FileWriter outputFile = new FileWriter(fileName);

			print(cscu, outputFile);

			outputFile.close();
		}
	}

	private void print(CSCompilationUnit unit, FileWriter writer) {
		printHeader(writer);
		printTree(unit, writer);
	}

	private void printHeader(FileWriter writer) {
		try {
			writer.write(ConfigurationFactory.defaultConfiguration().header());
		} catch (IOException x) {
			throw new RuntimeException(x);
		}
	}

	private void printTree(CSCompilationUnit unit, FileWriter writer) {
		CSharpPrinter printer = new CSharpPrinter();
		printer.setWriter(writer, ConfigurationFactory.defaultConfiguration()
				.getIndentString(), ConfigurationFactory.defaultConfiguration()
				.getMaxColumns());
		printer.print(unit);
	}
}
