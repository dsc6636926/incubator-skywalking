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

package org.apache.skywalking.apm.plugin.jdbc.mysql.v5.wrapper;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.MySQLConnection;
import com.mysql.jdbc.ReplicationConnection;
import org.apache.skywalking.apm.plugin.jdbc.trace.ConnectionInfo;

import java.sql.SQLException;

public class ReplicationConnectionWrapper extends JdbcConnectionWrapper implements ReplicationConnection {

    public ReplicationConnectionWrapper(MySQLConnection delegate, ConnectionInfo connectionInfo) {
        super(delegate, connectionInfo);
    }

    @Override public long getConnectionGroupId() {
        return replicationConnection.getConnectionGroupId();
    }

    @Override public Connection getCurrentConnection() {
        return replicationConnection.getCurrentConnection();
    }

    @Override public Connection getMasterConnection() {
        return replicationConnection.getMasterConnection();
    }

    @Override public void promoteSlaveToMaster(String s) throws SQLException {
        replicationConnection.promoteSlaveToMaster(s);
    }

    @Override public void removeMasterHost(String s) throws SQLException {
        replicationConnection.removeMasterHost(s);
    }

    @Override public void removeMasterHost(String s, boolean b) throws SQLException {
        replicationConnection.removeMasterHost(s, b);
    }

    @Override public boolean isHostMaster(String s) {
        return replicationConnection.isHostMaster(s);
    }

    @Override public Connection getSlavesConnection() {
        return replicationConnection.getSlavesConnection();
    }

    @Override public void addSlaveHost(String s) throws SQLException {
        replicationConnection.addSlaveHost(s);
    }

    @Override public void removeSlave(String s) throws SQLException {
        replicationConnection.removeSlave(s);
    }

    @Override public void removeSlave(String s, boolean b) throws SQLException {
        replicationConnection.removeSlave(s, b);
    }

    @Override public boolean isHostSlave(String s) {
        return replicationConnection.isHostSlave(s);
    }

    private ReplicationConnection replicationConnection;
}
