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

package org.codejuicer.java2csharp.sharpen.customization;

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
