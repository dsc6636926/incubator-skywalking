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

package org.apache.skywalking.apm.plugin.jdbc.mysql.v8.wrapper;

import com.mysql.cj.ServerVersion;
import com.mysql.cj.Session;
import com.mysql.cj.exceptions.ExceptionInterceptor;
import com.mysql.cj.interceptors.QueryInterceptor;
import com.mysql.cj.jdbc.*;
import com.mysql.cj.jdbc.result.CachedResultSetMetaData;
import com.mysql.cj.jdbc.result.ResultSetInternalMethods;
import org.apache.skywalking.apm.agent.core.plugin.interceptor.enhance.EnhancedInstance;
import org.apache.skywalking.apm.plugin.jdbc.trace.ConnectionInfo;

import java.sql.*;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class JdbcConnectionWrapper implements JdbcConnection, EnhancedInstance {

    public JdbcConnectionWrapper(JdbcConnection delegate, ConnectionInfo connectionInfo) {
        this.delegate = delegate;
        this.connectionInfo = connectionInfo;
    }

    private final JdbcConnection delegate;
    private final ConnectionInfo connectionInfo;
    private Object dynamicField;

    @Override
    public Object getSkyWalkingDynamicField() {
        return dynamicField;
    }

    @Override
    public void setSkyWalkingDynamicField(Object value) {
        this.dynamicField = value;
    }

    @Override
    public JdbcPropertySet getPropertySet() {
        return delegate.getPropertySet();
    }

    @Override
    public void createNewIO(boolean isForReconnect) {
        delegate.createNewIO(isForReconnect);
    }

    @Override
    public long getId() {
        return delegate.getId();
    }

    @Override
    public Properties getProperties() {
        return delegate.getProperties();
    }

    @Override
    public Object getConnectionMutex() {
        return delegate.getConnectionMutex();
    }

    @Override
    public Session getSession() {
        return delegate.getSession();
    }

    @Override
    public String getURL() {
        return delegate.getURL();
    }

    @Override
    public String getUser() {
        return delegate.getUser();
    }

    @Override
    public ExceptionInterceptor getExceptionInterceptor() {
        return delegate.getExceptionInterceptor();
    }

    @Override
    public void checkClosed() {
        delegate.checkClosed();
    }

    @Override
    public void normalClose() {
        delegate.normalClose();
    }

    @Override
    public void cleanup(Throwable whyCleanedUp) {
        delegate.cleanup(whyCleanedUp);
    }

    @Override
    public void changeUser(String userName, String newPassword) throws SQLException {
        delegate.changeUser(userName, newPassword);
    }

    @Override
    public void clearHasTriedMaster() {
        delegate.clearHasTriedMaster();
    }

    @Override
    public PreparedStatement clientPrepareStatement(String sql) throws SQLException {
        return delegate.clientPrepareStatement(sql);
    }

    @Override
    public PreparedStatement clientPrepareStatement(String sql, int autoGenKeyIndex) throws SQLException {
        return delegate.clientPrepareStatement(sql, autoGenKeyIndex);
    }

    @Override
    public PreparedStatement clientPrepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return delegate.clientPrepareStatement(sql, resultSetType, resultSetConcurrency);
    }

    @Override
    public PreparedStatement clientPrepareStatement(String sql, int[] autoGenKeyIndexes) throws SQLException {
        return delegate.clientPrepareStatement(sql, autoGenKeyIndexes);
    }

    @Override
    public PreparedStatement clientPrepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return delegate.clientPrepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    @Override
    public PreparedStatement clientPrepareStatement(String sql, String[] autoGenKeyColNames) throws SQLException {
        return delegate.clientPrepareStatement(sql, autoGenKeyColNames);
    }

    @Override
    public int getActiveStatementCount() {
        return delegate.getActiveStatementCount();
    }

    @Override
    public long getIdleFor() {
        return delegate.getIdleFor();
    }

    @Override
    public String getStatementComment() {
        return delegate.getStatementComment();
    }

    @Override
    public boolean hasTriedMaster() {
        return delegate.hasTriedMaster();
    }

    @Override
    public boolean isInGlobalTx() {
        return delegate.isInGlobalTx();
    }

    @Override
    public void setInGlobalTx(boolean flag) {
        delegate.setInGlobalTx(flag);
    }

    @Override
    public boolean isMasterConnection() {
        return delegate.isMasterConnection();
    }

    @Override
    public boolean isSameResource(JdbcConnection c) {
        return delegate.isSameResource(c);
    }

    @Override
    public boolean lowerCaseTableNames() {
        return delegate.lowerCaseTableNames();
    }

    @Override
    public void ping() throws SQLException {
        delegate.ping();
    }

    @Override
    public void resetServerState() throws SQLException {
        delegate.resetServerState();
    }

    @Override
    public PreparedStatement serverPrepareStatement(String sql) throws SQLException {
        return delegate.serverPrepareStatement(sql);
    }

    @Override
    public PreparedStatement serverPrepareStatement(String sql, int autoGenKeyIndex) throws SQLException {
        return delegate.serverPrepareStatement(sql, autoGenKeyIndex);
    }

    @Override
    public PreparedStatement serverPrepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return delegate.serverPrepareStatement(sql, resultSetType, resultSetConcurrency);
    }

    @Override
    public PreparedStatement serverPrepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return delegate.serverPrepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    @Override
    public PreparedStatement serverPrepareStatement(String sql, int[] autoGenKeyIndexes) throws SQLException {
        return delegate.serverPrepareStatement(sql, autoGenKeyIndexes);
    }

    @Override
    public PreparedStatement serverPrepareStatement(String sql, String[] autoGenKeyColNames) throws SQLException {
        return delegate.serverPrepareStatement(sql, autoGenKeyColNames);
    }

    @Override
    public void setFailedOver(boolean flag) {
        delegate.setFailedOver(flag);
    }

    @Override
    public void setStatementComment(String comment) {
        delegate.setStatementComment(comment);
    }

    @Override
    public void shutdownServer() throws SQLException {
        delegate.shutdownServer();
    }

    @Override
    public int getAutoIncrementIncrement() {
        return delegate.getAutoIncrementIncrement();
    }

    @Override
    public boolean hasSameProperties(JdbcConnection c) {
        return delegate.hasSameProperties(c);
    }

    @Override
    public String getHost() {
        return delegate.getHost();
    }

    @Override
    public String getHostPortPair() {
        return delegate.getHostPortPair();
    }

    @Override
    public void setProxy(JdbcConnection proxy) {
        delegate.setProxy(proxy);
    }

    @Override
    public boolean isServerLocal() throws SQLException {
        return delegate.isServerLocal();
    }

    @Override
    public int getSessionMaxRows() {
        return delegate.getSessionMaxRows();
    }

    @Override
    public void setSessionMaxRows(int max) throws SQLException {
        delegate.setSessionMaxRows(max);
    }

    @Override
    public Statement createStatement() throws SQLException {
        return new StatementWrapper(delegate.createStatement(), connectionInfo);
    }

    @Override
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return new PreparedStatementWrapper(delegate.prepareStatement(sql), connectionInfo, sql);
    }

    @Override
    public CallableStatement prepareCall(String sql) throws SQLException {
        return new CallableStatementWrapper(delegate.prepareCall(sql), connectionInfo, sql);
    }

    @Override
    public String nativeSQL(String sql) throws SQLException {
        return delegate.nativeSQL(sql);
    }

    @Override
    public void setAutoCommit(boolean autoCommit) throws SQLException {
        delegate.setAutoCommit(autoCommit);
    }

    @Override
    public boolean getAutoCommit() throws SQLException {
        return delegate.getAutoCommit();
    }

    @Override
    public void commit() throws SQLException {
        delegate.commit();
    }

    @Override
    public void rollback() throws SQLException {
        delegate.rollback();
    }

    @Override
    public void close() throws SQLException {
        delegate.close();
    }

    @Override
    public boolean isClosed() throws SQLException {
        return delegate.isClosed();
    }

    @Override
    public DatabaseMetaData getMetaData() throws SQLException {
        return delegate.getMetaData();
    }

    @Override
    public void setReadOnly(boolean readOnly) throws SQLException {
        delegate.setReadOnly(readOnly);
    }

    @Override
    public boolean isReadOnly() throws SQLException {
        return delegate.isReadOnly();
    }

    @Override
    public void setCatalog(String catalog) throws SQLException {
        delegate.setCatalog(catalog);
    }

    @Override
    public String getCatalog() throws SQLException {
        return delegate.getCatalog();
    }

    @Override
    public void setTransactionIsolation(int level) throws SQLException {
        delegate.setTransactionIsolation(level);
    }

    @Override
    public int getTransactionIsolation() throws SQLException {
        return delegate.getTransactionIsolation();
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        return delegate.getWarnings();
    }

    @Override
    public void clearWarnings() throws SQLException {
        delegate.clearWarnings();
    }

    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
        return new StatementWrapper(delegate.createStatement(resultSetType, resultSetConcurrency), connectionInfo);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return new PreparedStatementWrapper(delegate.prepareStatement(sql, resultSetType, resultSetConcurrency), connectionInfo, sql);
    }

    @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return new CallableStatementWrapper(delegate.prepareCall(sql, resultSetType, resultSetConcurrency), connectionInfo, sql);
    }

    @Override
    public Map<String, Class<?>> getTypeMap() throws SQLException {
        return delegate.getTypeMap();
    }

    @Override
    public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
        delegate.setTypeMap(map);
    }

    @Override
    public void setHoldability(int holdability) throws SQLException {
        delegate.setHoldability(holdability);
    }

    @Override
    public int getHoldability() throws SQLException {
        return delegate.getHoldability();
    }

    @Override
    public Savepoint setSavepoint() throws SQLException {
        return delegate.setSavepoint();
    }

    @Override
    public Savepoint setSavepoint(String name) throws SQLException {
        return delegate.setSavepoint(name);
    }

    @Override
    public void rollback(Savepoint savepoint) throws SQLException {
        delegate.rollback();
    }

    @Override
    public void releaseSavepoint(Savepoint savepoint) throws SQLException {
        delegate.releaseSavepoint(savepoint);
    }

    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return new StatementWrapper(delegate.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability), connectionInfo);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return new PreparedStatementWrapper(delegate.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability), connectionInfo, sql);
    }

    @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return new CallableStatementWrapper(delegate.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability), connectionInfo, sql);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
        return new PreparedStatementWrapper(delegate.prepareStatement(sql, autoGeneratedKeys), connectionInfo, sql);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
        return new PreparedStatementWrapper(delegate.prepareStatement(sql, columnIndexes), connectionInfo, sql);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
        return new PreparedStatementWrapper(delegate.prepareStatement(sql, columnNames), connectionInfo, sql);
    }

    @Override
    public Clob createClob() throws SQLException {
        return delegate.createClob();
    }

    @Override
    public Blob createBlob() throws SQLException {
        return delegate.createBlob();
    }

    @Override
    public NClob createNClob() throws SQLException {
        return delegate.createNClob();
    }

    @Override
    public SQLXML createSQLXML() throws SQLException {
        return delegate.createSQLXML();
    }

    @Override
    public boolean isValid(int timeout) throws SQLException {
        return delegate.isValid(timeout);
    }

    @Override
    public void setClientInfo(String name, String value) throws SQLClientInfoException {
        delegate.setClientInfo(name, value);
    }

    @Override
    public void setClientInfo(Properties properties) throws SQLClientInfoException {
        delegate.setClientInfo(properties);
    }

    @Override
    public String getClientInfo(String name) throws SQLException {
        return delegate.getClientInfo(name);
    }

    @Override
    public Properties getClientInfo() throws SQLException {
        return delegate.getClientInfo();
    }

    @Override
    public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
        return delegate.createArrayOf(typeName, elements);
    }

    @Override
    public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
        return delegate.createStruct(typeName, attributes);
    }

    @Override
    public void setSchema(String schema) throws SQLException {
        delegate.setSchema(schema);
    }

    @Override
    public String getSchema() throws SQLException {
        return delegate.getSchema();
    }

    @Override
    public void abort(Executor executor) throws SQLException {
        delegate.abort(executor);
    }

    @Override
    public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
        delegate.setNetworkTimeout(executor, milliseconds);
    }

    @Override
    public int getNetworkTimeout() throws SQLException {
        return delegate.getNetworkTimeout();
    }

    @Override
    public void abortInternal() throws SQLException {
        delegate.abortInternal();
    }

    @Override
    public boolean isProxySet() {
        return delegate.isProxySet();
    }

    @Override
    public CachedResultSetMetaData getCachedMetaData(String sql) {
        return delegate.getCachedMetaData(sql);
    }

    @Override
    public String getCharacterSetMetadata() {
        return delegate.getCharacterSetMetadata();
    }

    @Override
    public Statement getMetadataSafeStatement() throws SQLException {
        return delegate.getMetadataSafeStatement();
    }

    @Override
    public ServerVersion getServerVersion() {
        return delegate.getServerVersion();
    }

    @Override
    public List<QueryInterceptor> getQueryInterceptorsInstances() {
        return delegate.getQueryInterceptorsInstances();
    }

    @Override
    public void initializeResultsMetadataFromCache(String sql, CachedResultSetMetaData cachedMetaData, ResultSetInternalMethods resultSet) throws SQLException {
        delegate.initializeResultsMetadataFromCache(sql, cachedMetaData, resultSet);
    }

    @Override
    public void initializeSafeQueryInterceptors() throws SQLException {
        delegate.initializeSafeQueryInterceptors();
    }

    @Override
    public boolean isReadOnly(boolean useSessionStatus) throws SQLException {
        return delegate.isReadOnly(useSessionStatus);
    }

    @Override
    public void pingInternal(boolean checkForClosedConnection, int timeoutMillis) throws SQLException {
        delegate.pingInternal(checkForClosedConnection, timeoutMillis);
    }

    @Override
    public void realClose(boolean calledExplicitly, boolean issueRollback, boolean skipLocalTeardown, Throwable reason) throws SQLException {
        delegate.realClose(calledExplicitly, issueRollback, skipLocalTeardown, reason);
    }

    @Override
    public void recachePreparedStatement(JdbcPreparedStatement pstmt) throws SQLException {
        delegate.recachePreparedStatement(pstmt);
    }

    @Override
    public void decachePreparedStatement(JdbcPreparedStatement pstmt) throws SQLException {
        delegate.decachePreparedStatement(pstmt);
    }

    @Override
    public void registerStatement(JdbcStatement stmt) {
        delegate.registerStatement(stmt);
    }

    @Override
    public void setReadOnlyInternal(boolean readOnlyFlag) throws SQLException {
        delegate.setReadOnlyInternal(readOnlyFlag);
    }

    @Override
    public boolean storesLowerCaseTableName() {
        return delegate.storesLowerCaseTableName();
    }

    @Override
    public void throwConnectionClosedException() throws SQLException {
        delegate.throwConnectionClosedException();
    }

    @Override
    public void unregisterStatement(JdbcStatement stmt) {
        delegate.unregisterStatement(stmt);
    }

    @Override
    public void unSafeQueryInterceptors() throws SQLException {
        delegate.unSafeQueryInterceptors();
    }

    @Override
    public JdbcConnection getMultiHostSafeProxy() {
        return delegate.getMultiHostSafeProxy();
    }

    @Override
    public JdbcConnection getActiveMySQLConnection() {
        return delegate.getActiveMySQLConnection();
    }

    @Override
    public ClientInfoProvider getClientInfoProviderImpl() throws SQLException {
        return delegate.getClientInfoProviderImpl();
    }

    @Override
    public void transactionBegun() {
        delegate.transactionBegun();
    }

    @Override
    public void transactionCompleted() {
        delegate.transactionCompleted();
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return delegate.unwrap(iface);
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return delegate.isWrapperFor(iface);
    }
}
