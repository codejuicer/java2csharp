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

import java.util.Map;

import org.apache.maven.plugin.logging.Log;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.IBinding;

public class FileASTRequestor extends org.eclipse.jdt.core.dom.FileASTRequestor {
	private Log logger;

	private String inputFolder;

	private Map<String, CompilationUnitExtended> sourcePathEntry;

	public FileASTRequestor(Log logger, String inputFolder,
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
