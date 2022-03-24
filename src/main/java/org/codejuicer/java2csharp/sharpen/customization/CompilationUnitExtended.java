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
