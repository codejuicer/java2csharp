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

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;

import org.apache.maven.plugin.logging.Log;

/**
 * An utility Class to perform tests on JaxB.
 * 
 */
public class JaxbTool {

	/**
	 * The JaxB context.
	 */
	private JAXBContext jc;

	private Log logger;

	/**
	 * Instantiates a new JaxB tool.
	 * 
	 * @param logger
	 *            plugin logger instance
	 * @param clazz
	 *            root class of xsd schema
	 */
	public JaxbTool(Log logger, final Class<?> clazz) {
		this.logger = logger;
		logger.debug("Initializing JaxB.");
		try {
			jc = JAXBContext.newInstance(clazz);
		} catch (final JAXBException e) {
			logger.error("Error Initializing JaxB! ", e);
			jc = null;
		}
	}

	/**
	 * Gets the schema.
	 * 
	 * @param filename
	 *            the output file name 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void printSchema(final String filename) throws IOException {

		final List<ByteArrayOutputStream> map = new ArrayList<ByteArrayOutputStream>();

		final SchemaOutputResolver outputResolver = new SchemaOutputResolver() {
			@Override
			public Result createOutput(final String namespaceUri,
					final String suggestedFileName) throws IOException {

				logger.debug("Creating String result for: " + namespaceUri
						+ " with file name " + suggestedFileName);

				final ByteArrayOutputStream baos = new ByteArrayOutputStream();

				map.add(baos);
				final StreamResult ret = new StreamResult(baos);

				ret.setSystemId(filename);
				return ret;
			}
		};

		jc.generateSchema(outputResolver);
		if (map.size() == 1) {
			for (ByteArrayOutputStream entry : map) {
				final ByteArrayOutputStream baos = entry;

				PrintStream writetoEngineer = new PrintStream(
						new FileOutputStream(filename, false));

				writetoEngineer.println(baos.toString());
				writetoEngineer.println();
				writetoEngineer.flush();
				writetoEngineer.close();
			}
		} else {
			throw new IOException("Too many file generated");
		}

	}
}