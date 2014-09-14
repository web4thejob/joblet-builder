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
package org.web4thejob.joblet;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;
import org.web4thejob.context.ContextUtil;
import org.web4thejob.joblet.base.AbstractORMTest;
import org.web4thejob.module.MyJoblet;
import org.web4thejob.orm.Path;
import org.web4thejob.orm.query.Condition;
import org.web4thejob.orm.query.Query;
import org.web4thejob.security.UserIdentity;

/**
 * <p/>
 * Type your tests here.
 * <p/>
 * Switch to the <strong>example</strong> branch or visit <a href=
 * "https://github.com/web4thejob/joblet-builder/blob/example/src/test/java/org/web4thejob/joblet/ORMTest.java"
 * >github</a> for an example.
 */
public class ORMTest extends AbstractORMTest {

    @Test @Transactional
    public void test1() {
        Query query = ContextUtil.getEntityFactory().buildQuery(UserIdentity.class);
        query.addCriterion(new Path(UserIdentity.FLD_CODE), Condition.EQ, "new_user");
        UserIdentity userIdentity = ContextUtil.getDRS().findUniqueByQuery(query);
        if (userIdentity != null) {
            ContextUtil.getDWS().delete(userIdentity);
        }

        userIdentity = ContextUtil.getEntityFactory().buildUserIdentity();
        userIdentity.setCode("new_user");
        userIdentity.setPassword("xxxx");
        userIdentity.setLastName("Lastname");
        userIdentity.setFirstName("Firstname");
        ContextUtil.getDWS().save(userIdentity);
    }

}
