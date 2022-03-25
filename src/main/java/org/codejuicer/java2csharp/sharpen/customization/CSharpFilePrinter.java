package org.codejuicer.java2csharp.sharpen.customization;

import sharpen.core.ConfigurationFactory;
import sharpen.core.csharp.CSharpPrinter;
import sharpen.core.csharp.ast.CSCompilationUnit;

import java.io.FileWriter;
import java.io.IOException;

public class CSharpFilePrinter {

    public void print(CSCompilationUnit unit, FileWriter writer) {
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
