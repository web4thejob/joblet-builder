/*******************************************************************************
 * Copyright (c) 2013 Veniamin Isaias.
 * 
 * This file is part of joblet-builder.
 * 
 * Joblet-builder is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Joblet-builder is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with joblet-builder.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package org.web4thejob.joblet.base;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.web4thejob.context.ContextUtil;
import org.web4thejob.module.JobletInstaller;
import org.web4thejob.orm.DatasourceProperties;

/**
 * @author Veniamin Isaias
 * @since 1.0.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:org/web4thejob/conf/orm-config.xml" })
public abstract class AbstractORMTest {
	private static boolean initialized;
	
	@Before
	public void setUp() {

		if (!initialized && !ContextUtil.getSystemJoblet().isInstalled()) {
			Properties datasource = new Properties();
			try {
				datasource
						.load(new ClassPathResource(DatasourceProperties.PATH)
								.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}

			JobletInstaller jobletInstaller;
			jobletInstaller = ContextUtil.getBean(JobletInstaller.class);
			jobletInstaller.setConnectionInfo(datasource);
			List<Exception> errors = jobletInstaller.installAll();
			if (!errors.isEmpty()) {
				throw new RuntimeException(
						"Test Context initialization failed.");
			}
			initialized=true;			
		}

		ContextUtil.getMRS().refreshMetaCache();

	}
}
