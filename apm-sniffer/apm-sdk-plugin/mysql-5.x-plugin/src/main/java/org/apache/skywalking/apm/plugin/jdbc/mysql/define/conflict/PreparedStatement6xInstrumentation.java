/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */


package org.apache.skywalking.apm.plugin.jdbc.mysql.define.conflict;

import org.apache.skywalking.apm.agent.core.plugin.match.ClassMatch;
import org.apache.skywalking.apm.plugin.jdbc.mysql.define.Constants;
import org.apache.skywalking.apm.plugin.jdbc.mysql.define.PreparedStatementInstrumentation;

import static org.apache.skywalking.apm.agent.core.plugin.match.NameMatch.byName;

/**
 * {@link PreparedStatement6xInstrumentation} define that the mysql-2.x plugin intercepts the following methods in the
 * com.mysql.jdbc.JDBC42PreparedStatement, com.mysql.jdbc.PreparedStatement and 
 * com.mysql.cj.jdbc.PreparedStatement class:
 * 1. execute 
 * 2. executeQuery 
 * 3. executeUpdate 
 * 4. executeLargeUpdate 
 * 5. addBatch 
 *
 * @author zhangxin
 */
public class PreparedStatement6xInstrumentation extends PreparedStatementInstrumentation {

    public static final String MYSQL6_PREPARED_STATEMENT_CLASS_NAME = "com.mysql.cj.jdbc.PreparedStatement";

    @Override protected ClassMatch enhanceClass() {
        return byName(MYSQL6_PREPARED_STATEMENT_CLASS_NAME);
    }


    @Override protected String[] witnessClasses() {
        return new String[] {Constants.WITNESS_MYSQL_NOCJ_CLASS};
    }
}
