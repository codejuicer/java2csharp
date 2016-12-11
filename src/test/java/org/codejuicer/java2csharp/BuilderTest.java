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

package org.codejuicer.java2csharp;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.codejuicer.java2csharp.ConversionConfiguration;
import org.codejuicer.java2csharp.Java2CsharpMojo;
import org.junit.Test;

public class BuilderTest {

    @Test
    public void testCSharpBuilder() throws MojoExecutionException, IOException {
        String path = getClass().getClassLoader().getResource(".").getPath();
        ConversionConfiguration conf = new ConversionConfiguration();
        conf.setSourcePath(path + "../../src/test/java/org/codejuicer/java2csharp/testclasses");
        conf.setOutputDirectory(path + "outputfolder");
        conf.setName("test");
        Java2CsharpMojo mojo = new Java2CsharpMojo();
        mojo.setConversionConfigurations(new ConversionConfiguration[] {
                                                                        conf
        });
        mojo.execute();

        List<String> expectedFiles = listFilesForFolder(new File(path + "expectedFolder"));

        List<String> outputFiles = listFilesForFolder(new File(path + "outputfolder"));
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
