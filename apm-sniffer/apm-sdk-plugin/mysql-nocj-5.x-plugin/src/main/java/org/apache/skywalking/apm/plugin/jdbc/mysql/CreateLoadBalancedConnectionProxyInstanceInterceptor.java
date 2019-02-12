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

package org.apache.skywalking.apm.plugin.jdbc.mysql;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Properties;

import com.mysql.jdbc.LoadBalancedConnection;
import com.mysql.jdbc.NonRegisteringDriver;
import org.apache.skywalking.apm.agent.core.plugin.interceptor.enhance.MethodInterceptResult;
import org.apache.skywalking.apm.agent.core.plugin.interceptor.enhance.StaticMethodsAroundInterceptor;
import org.apache.skywalking.apm.network.trace.component.ComponentsDefine;
import org.apache.skywalking.apm.plugin.jdbc.mysql.wrapper.LoadBalancedConnectionWrapper;
import org.apache.skywalking.apm.plugin.jdbc.trace.ConnectionInfo;

public class CreateLoadBalancedConnectionProxyInstanceInterceptor implements StaticMethodsAroundInterceptor {
    @Override public void beforeMethod(Class clazz, Method method, Object[] allArguments, Class<?>[] parameterTypes,
        MethodInterceptResult result) {

    }

    @Override public Object afterMethod(Class clazz, Method method, Object[] allArguments, Class<?>[] parameterTypes,
        Object ret) {
        List<String> connectionUrls = (List<String>)allArguments[0];
        String dataBaseName = null;
        StringBuilder hosts = new StringBuilder();
        for (String url : connectionUrls) {
            final Properties properties = NonRegisteringDriver.expandHostKeyValues(url);
            hosts.append(properties.getProperty("host")).append(":").append(properties.getProperty("port")).append(",");
            dataBaseName = properties.getProperty(NonRegisteringDriver.DBNAME_PROPERTY_KEY);
        }
        ConnectionInfo connectionInfo = new ConnectionInfo(ComponentsDefine.MYSQL_JDBC_DRIVER, "Mysql", hosts.toString(), dataBaseName);
        return new LoadBalancedConnectionWrapper((LoadBalancedConnection)ret, connectionInfo);
    }

    @Override
    public void handleMethodException(Class clazz, Method method, Object[] allArguments, Class<?>[] parameterTypes,
        Throwable t) {

    }
}
