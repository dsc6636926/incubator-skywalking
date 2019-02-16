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
import org.apache.skywalking.apm.plugin.jdbc.mysql.define.StatementInstrumentation;

import static org.apache.skywalking.apm.agent.core.plugin.match.MultiClassNameMatch.byMultiClassMatch;

/**
 * {@link Statement6xInstrumentation} intercepts the following methods in the
 * com.mysql.jdbc.StatementImpl and com.mysql.cj.jdbc.StatementImpl class.
 * 1. execute
 * 2. executeQuery
 * 3. executeUpdate
 * 4. executeLargeUpdate
 * 5. addBatch
 * 6. executeBatchInternal
 * 7. executeUpdateInternal
 * 8. executeQuery
 * 9. executeBatch
 *
 * @author zhangxin
 */
public class Statement6xInstrumentation extends StatementInstrumentation {
    private static final String STATEMENT_CLASS_NAME = "com.mysql.cj.jdbc.StatementImpl";

    @Override protected ClassMatch enhanceClass() {
        return byMultiClassMatch(STATEMENT_CLASS_NAME);
    }

    @Override protected String[] witnessClasses() {
        return new String[] {Constants.WITNESS_MYSQL_6X_CLASS};
    }
}
