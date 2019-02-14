/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.apache.skywalking.apm.plugin.jdbc.mysql.v5;

import com.mysql.jdbc.LoadBalancedConnection;
import org.apache.skywalking.apm.agent.core.plugin.interceptor.enhance.MethodInterceptResult;
import org.apache.skywalking.apm.plugin.jdbc.mysql.v5.wrapper.JdbcConnectionWrapper;
import org.apache.skywalking.apm.plugin.jdbc.mysql.v5.wrapper.LoadBalancedConnectionWrapper;
import org.apache.skywalking.apm.plugin.jdbc.trace.ConnectionInfo;

import java.lang.reflect.Method;

public class CreateLoadBalancedConnectionProxyInstanceInterceptor extends AbstractConnectionProxyInstanceInterceptor  {
    @Override public void beforeMethod(Class clazz, Method method, Object[] allArguments, Class<?>[] parameterTypes,
        MethodInterceptResult result) {

    }

    @Override
    JdbcConnectionWrapper wrapRtn(Object ret, ConnectionInfo connectionInfo) {
        return new LoadBalancedConnectionWrapper((LoadBalancedConnection)ret, connectionInfo);
    }
}
