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

package org.apache.skywalking.apm.plugin.jdbc.mysql.v8;

import com.mysql.cj.conf.HostInfo;
import org.apache.skywalking.apm.agent.core.plugin.interceptor.enhance.EnhancedInstance;
import org.apache.skywalking.apm.agent.core.plugin.interceptor.enhance.InstanceConstructorInterceptor;
import org.apache.skywalking.apm.plugin.jdbc.connectionurl.parser.URLParser;

/**
 *
 * @version V1.0
 * @Title: ConnectionImplConstructorInterceptor
 * @Package org.apache.skywalking.apm.plugin.jdbc.mysql.v8
 * @Description:
 * @author: dingshaocheng
 * @date: 2019/2/16
 */
public class ConnectionImplConstructorInterceptor implements InstanceConstructorInterceptor {

    @Override
    public void onConstruct(EnhancedInstance objInst, Object[] allArguments) {

        objInst.setSkyWalkingDynamicField(URLParser.parser(((HostInfo)allArguments[0]).getDatabaseUrl()));
    }
}
