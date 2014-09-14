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
package org.web4thejob.module;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

@Component
public class MyJoblet extends AbstractJoblet {
    private static final String BASE_PACKAGE = "com.example"; //<-- Set you base package here

    public int getOrdinal() {
        return 99;
    }

    public boolean isInstalled() {
        return true;
    }

    public String[] getSchemas() {
        return new String[]{"example"};
    }

    public List<Resource> getResources() {
        List<Resource> resources = new ArrayList<Resource>();

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            for (Resource resource : resolver
                    .getResources("classpath*:" + packageToPath() + "/**/*.hbm.xml")) {
                resources.add(resource);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return resources;
    }

    public String getBasePackage() {
        return BASE_PACKAGE;
    }

    private static String packageToPath() {
        return BASE_PACKAGE.replaceAll("\\.", "/");
    }

}
