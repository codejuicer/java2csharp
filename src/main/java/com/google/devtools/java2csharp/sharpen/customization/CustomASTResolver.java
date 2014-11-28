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
			if (logger.isDebugEnabled()) {
				logger.debug("Called resolver for binding " + binding.getName());
			}
			for (String sourceFilePath : sourcePathEntry.keySet()) {
				CompilationUnit cu = sourcePathEntry.get(sourceFilePath)
						.getCompilationUnit();
				ASTNode node = cu.findDeclaringNode(binding);
				if (null != node) {
					if (logger.isDebugEnabled()) {
						logger.debug("Found ASTNode " + node.toString());
					}
					return node;
				}
			}
		} else if (logger.isDebugEnabled()) {
			logger.debug("Called resolver for null binding");
		}

		return null;
	}

}
