/*
 * Copyright 2014 Giuseppe Gerla. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.devtools.java2csharp;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.project.MavenProject;

/**
 * Manages switching to the classloader needed for creating the java sources and
 * restoring the old classloader when finished
 */
public class ClassLoaderSwitcher {

  private Log logger;
  private ClassLoader origContextClassloader;

  public ClassLoaderSwitcher(Log log) {
    this.logger = log;
  }

  /**
   * Create and set the classloader that is needed for creating the java sources
   * from wsdl
   * 
   * @param project
   * @param useCompileClasspath
   * @param classesDir
   */
  public ClassLoader
      switchClassLoader(MavenProject project, String classpath, List<?> classpathElements) {
    final List<URL> urlList = new ArrayList<URL>();
    StringBuilder buf = new StringBuilder();

    try {
      buf.append(classpath);
      buf.append(File.pathSeparatorChar);
      urlList.add(new File(project.getBuild().getOutputDirectory()).toURI().toURL());
    } catch (MalformedURLException e) {
    	logger.error("Error in URL creation for project output directory", e);
    }
    if (classpathElements != null) {
      for (Object classpathElement : classpathElements) {
        buf.append(classpathElement.toString());
        buf.append(File.pathSeparatorChar);
      }
    }

    buf.append(File.pathSeparatorChar);
    Set<Artifact> artifacts = project.getArtifacts();
    for (Artifact a : artifacts) {
      try {
        if (a.getFile() != null && a.getFile().exists()) {
          urlList.add(a.getFile().toURI().toURL());
          buf.append(a.getFile().getAbsolutePath());
          buf.append(File.pathSeparatorChar);
          logger.debug("     " + a.getFile().getAbsolutePath());
        }
      } catch (MalformedURLException e) {
    	  logger.error("Error in URL creation for artifact " + a, e);
      }
    }

    origContextClassloader = Thread.currentThread().getContextClassLoader();
    logger.debug("Orig cl: " + origContextClassloader);
    ClassLoader loader = AccessController.doPrivileged(new PrivilegedAction<ClassLoader>() {
      public ClassLoader run() {
        return new URLClassLoader(urlList.toArray(new URL[1]), origContextClassloader);
      }
    });
    logger.debug("New cl: " + loader);
    
    return loader;
  }
}
