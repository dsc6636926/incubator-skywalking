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


package org.apache.skywalking.apm.plugin.jdbc.mysql.v5;

import com.mysql.jdbc.ReplicationConnection;
import org.apache.skywalking.apm.agent.core.plugin.interceptor.enhance.EnhancedInstance;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @Title: CreateReplicationConnectionProxyInstanceInterceptorTest
 * @Package org.apache.skywalking.apm.plugin.jdbc.mysql.v5
 * @Description:
 * @author: dingshaocheng
 * @date: 2019/2/18
 */
@RunWith(MockitoJUnitRunner.class)
public class CreateReplicationConnectionProxyInstanceInterceptorTest {
    private CreateReplicationConnectionProxyInstanceInterceptor interceptor;

    @Mock
    private ReplicationConnection ret;
    @Before
    public void setUp() throws Exception {
        interceptor = new CreateReplicationConnectionProxyInstanceInterceptor();
    }

    @Test
    public void testResultIsEnhanceInstance() {
        final List<String> hosts = new ArrayList<String>();
        hosts.add("jdbc:mysql:replication://localhost:3306,localhost:3306,localhost:3306/test?useUnicode=true&characterEncoding=utf8&useSSL=false&roundRobinLoadBalance=true");
        Object result = interceptor.afterMethod(null,null,new Object[]{hosts,new Properties()},null,ret);
        Assert.assertTrue("wrap fail",result instanceof EnhancedInstance);
    }
}