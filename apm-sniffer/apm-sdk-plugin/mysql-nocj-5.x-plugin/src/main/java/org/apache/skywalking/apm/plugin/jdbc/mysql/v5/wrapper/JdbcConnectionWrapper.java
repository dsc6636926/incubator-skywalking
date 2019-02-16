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

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.*;
import java.util.concurrent.Executor;

import com.mysql.jdbc.*;
import com.mysql.jdbc.log.Log;
import com.mysql.jdbc.profiler.ProfilerEventHandler;
import org.apache.skywalking.apm.agent.core.plugin.interceptor.enhance.EnhancedInstance;
import org.apache.skywalking.apm.plugin.jdbc.trace.ConnectionInfo;

public class JdbcConnectionWrapper implements MySQLConnection, EnhancedInstance {

    public JdbcConnectionWrapper(MySQLConnection delegate, ConnectionInfo connectionInfo) {
        this.delegate = delegate;
        this.connectionInfo = connectionInfo;
    }

    private final MySQLConnection delegate;
    private final ConnectionInfo connectionInfo;
    private Object dynamicField;

    @Override public Object getSkyWalkingDynamicField() {
        return dynamicField;
    }

    @Override public void setSkyWalkingDynamicField(Object value) {
        this.dynamicField = value;
    }

    @Override public SQLXML createSQLXML() throws SQLException {
        return delegate.createSQLXML();
    }

    @Override public Array createArrayOf(String s, Object[] objects) throws SQLException {
        return delegate.createArrayOf(s, objects);
    }

    @Override public Struct createStruct(String s, Object[] objects) throws SQLException {
        return delegate.createStruct(s, objects);
    }

    @Override public Properties getClientInfo() throws SQLException {
        return delegate.getClientInfo();
    }

    @Override public String getClientInfo(String s) throws SQLException {
        return delegate.getClientInfo(s);
    }

    @Override public boolean isValid(int i) throws SQLException {
        return delegate.isValid(i);
    }

    @Override public void setClientInfo(Properties properties) throws SQLClientInfoException {
        delegate.setClientInfo(properties);
    }

    @Override public void setClientInfo(String s, String s1) throws SQLClientInfoException {
        delegate.setClientInfo(s, s1);
    }

    @Override public boolean isWrapperFor(Class<?> aClass) throws SQLException {
        return delegate.isWrapperFor(aClass);
    }

    @Override public <T> T unwrap(Class<T> aClass) throws SQLException {
        return delegate.unwrap(aClass);
    }

    @Override public Blob createBlob() throws SQLException {
        return delegate.createBlob();
    }

    @Override public Clob createClob() throws SQLException {
        return delegate.createClob();
    }

    @Override public NClob createNClob() throws SQLException {
        return delegate.createNClob();
    }

    @Override public boolean isProxySet() {
        return delegate.isProxySet();
    }

    @Override public void createNewIO(boolean b) throws SQLException {
        delegate.createNewIO(b);
    }

    @Override public void dumpTestcaseQuery(String s) {
        delegate.dumpTestcaseQuery(s);
    }

    @Override public Connection duplicate() throws SQLException {
        return delegate.duplicate();
    }

    @Override public ResultSetInternalMethods execSQL(StatementImpl statement, String s, int i, Buffer buffer, int i1, int i2, boolean b, String s1, Field[] fields) throws SQLException {
        return delegate.execSQL(statement, s, i, buffer, i1, i2, b, s1, fields);
    }

    @Override public ResultSetInternalMethods execSQL(StatementImpl statement, String s, int i, Buffer buffer, int i1, int i2, boolean b, String s1, Field[] fields, boolean b1) throws SQLException {
        return delegate.execSQL(statement, s, i, buffer, i1, i2, b, s1, fields, b1);
    }

    @Override public String extractSqlFromPacket(String s, Buffer buffer, int i) throws SQLException {
        return delegate.extractSqlFromPacket(s, buffer, i);
    }

    @Override public StringBuilder generateConnectionCommentBlock(StringBuilder stringBuilder) {
        return delegate.generateConnectionCommentBlock(stringBuilder);
    }

    @Override public void changeUser(String s, String s1) throws SQLException {
        delegate.changeUser(s, s1);
    }

    @Override public void clearHasTriedMaster() {
        delegate.clearHasTriedMaster();
    }

    @Override public PreparedStatement clientPrepareStatement(String s) throws SQLException {
        return delegate.clientPrepareStatement(s);
    }

    @Override public PreparedStatement clientPrepareStatement(String s, int i) throws SQLException {
        return delegate.clientPrepareStatement(s, i);
    }

    @Override public PreparedStatement clientPrepareStatement(String s, int i, int i1) throws SQLException {
        return delegate.clientPrepareStatement(s, i, i1);
    }

    @Override public PreparedStatement clientPrepareStatement(String s, int[] ints) throws SQLException {
        return delegate.clientPrepareStatement(s, ints);
    }

    @Override public PreparedStatement clientPrepareStatement(String s, int i, int i1, int i2) throws SQLException {
        return delegate.clientPrepareStatement(s, i, i1, i2);
    }

    @Override public PreparedStatement clientPrepareStatement(String s, String[] strings) throws SQLException {
        return delegate.clientPrepareStatement(s, strings);
    }

    @Override public int getActiveStatementCount() {
        return delegate.getActiveStatementCount();
    }

    @Override public int getAutoIncrementIncrement() {
        return delegate.getAutoIncrementIncrement();
    }

    @Override public boolean hasSameProperties(Connection connection) {
        return delegate.hasSameProperties(connection);
    }

    @Override public CachedResultSetMetaData getCachedMetaData(String s) {
        return delegate.getCachedMetaData(s);
    }

    @Override public Calendar getCalendarInstanceForSessionOrNew() {
        return delegate.getCalendarInstanceForSessionOrNew();
    }

    @Override public Timer getCancelTimer() {
        return delegate.getCancelTimer();
    }

    @Override public String getCharacterSetMetadata() {
        return delegate.getCharacterSetMetadata();
    }

    @Override public SingleByteCharsetConverter getCharsetConverter(String s) throws SQLException {
        return delegate.getCharsetConverter(s);
    }

    @Override public String getCharsetNameForIndex(int i) throws SQLException {
        return delegate.getCharsetNameForIndex(i);
    }

    @Override public String getEncodingForIndex(int i) throws SQLException {
        return delegate.getEncodingForIndex(i);
    }

    @Override public TimeZone getDefaultTimeZone() {
        return delegate.getDefaultTimeZone();
    }

    @Override public String getErrorMessageEncoding() {
        return delegate.getErrorMessageEncoding();
    }

    @Override public String exposeAsXml() throws SQLException {
        return delegate.exposeAsXml();
    }

    @Override public boolean getAllowLoadLocalInfile() {
        return delegate.getAllowLoadLocalInfile();
    }

    @Override public boolean getAllowMultiQueries() {
        return delegate.getAllowMultiQueries();
    }

    @Override public boolean getAllowNanAndInf() {
        return delegate.getAllowNanAndInf();
    }

    @Override public boolean getAllowUrlInLocalInfile() {
        return delegate.getAllowUrlInLocalInfile();
    }

    @Override public boolean getAlwaysSendSetIsolation() {
        return delegate.getAlwaysSendSetIsolation();
    }

    @Override public boolean getAutoDeserialize() {
        return delegate.getAutoDeserialize();
    }

    @Override public boolean getAutoGenerateTestcaseScript() {
        return delegate.getAutoGenerateTestcaseScript();
    }

    @Override public boolean getAutoReconnectForPools() {
        return delegate.getAutoReconnectForPools();
    }

    @Override public int getBlobSendChunkSize() {
        return delegate.getBlobSendChunkSize();
    }

    @Override public boolean getCacheCallableStatements() {
        return delegate.getCacheCallableStatements();
    }

    @Override public boolean getCachePreparedStatements() {
        return delegate.getCachePreparedStatements();
    }

    @Override public boolean getCacheResultSetMetadata() {
        return delegate.getCacheResultSetMetadata();
    }

    @Override public boolean getCacheServerConfiguration() {
        return delegate.getCacheServerConfiguration();
    }

    @Override public int getCallableStatementCacheSize() {
        return delegate.getCallableStatementCacheSize();
    }

    @Override public boolean getCapitalizeTypeNames() {
        return delegate.getCapitalizeTypeNames();
    }

    @Override public String getCharacterSetResults() {
        return delegate.getCharacterSetResults();
    }

    @Override public boolean getClobberStreamingResults() {
        return delegate.getClobberStreamingResults();
    }

    @Override public String getClobCharacterEncoding() {
        return delegate.getClobCharacterEncoding();
    }

    @Override public String getConnectionCollation() {
        return delegate.getConnectionCollation();
    }

    @Override public int getConnectTimeout() {
        return delegate.getConnectTimeout();
    }

    @Override public boolean getContinueBatchOnError() {
        return delegate.getContinueBatchOnError();
    }

    @Override public boolean getCreateDatabaseIfNotExist() {
        return delegate.getCreateDatabaseIfNotExist();
    }

    @Override public int getDefaultFetchSize() {
        return delegate.getDefaultFetchSize();
    }

    @Override public boolean getDontTrackOpenResources() {
        return delegate.getDontTrackOpenResources();
    }

    @Override public boolean getDumpQueriesOnException() {
        return delegate.getDumpQueriesOnException();
    }

    @Override public boolean getDynamicCalendars() {
        return delegate.getDynamicCalendars();
    }

    @Override public boolean getElideSetAutoCommits() {
        return delegate.getElideSetAutoCommits();
    }

    @Override public boolean getEmptyStringsConvertToZero() {
        return delegate.getEmptyStringsConvertToZero();
    }

    @Override public boolean getEmulateLocators() {
        return delegate.getEmulateLocators();
    }

    @Override public boolean getEmulateUnsupportedPstmts() {
        return delegate.getEmulateUnsupportedPstmts();
    }

    @Override public boolean getEnablePacketDebug() {
        return delegate.getEnablePacketDebug();
    }

    @Override public String getEncoding() {
        return delegate.getEncoding();
    }

    @Override public boolean getExplainSlowQueries() {
        return delegate.getExplainSlowQueries();
    }

    @Override public boolean getFailOverReadOnly() {
        return delegate.getFailOverReadOnly();
    }

    @Override public boolean getGatherPerformanceMetrics() {
        return delegate.getGatherPerformanceMetrics();
    }

    @Override public boolean getHoldResultsOpenOverStatementClose() {
        return delegate.getHoldResultsOpenOverStatementClose();
    }

    @Override public boolean getIgnoreNonTxTables() {
        return delegate.getIgnoreNonTxTables();
    }

    @Override public int getInitialTimeout() {
        return delegate.getInitialTimeout();
    }

    @Override public boolean getInteractiveClient() {
        return delegate.getInteractiveClient();
    }

    @Override public boolean getIsInteractiveClient() {
        return delegate.getIsInteractiveClient();
    }

    @Override public boolean getJdbcCompliantTruncation() {
        return delegate.getJdbcCompliantTruncation();
    }

    @Override public int getLocatorFetchBufferSize() {
        return delegate.getLocatorFetchBufferSize();
    }

    @Override public String getLogger() {
        return delegate.getLogger();
    }

    @Override public String getLoggerClassName() {
        return delegate.getLoggerClassName();
    }

    @Override public boolean getLogSlowQueries() {
        return delegate.getLogSlowQueries();
    }

    @Override public boolean getMaintainTimeStats() {
        return delegate.getMaintainTimeStats();
    }

    @Override public int getMaxQuerySizeToLog() {
        return delegate.getMaxQuerySizeToLog();
    }

    @Override public int getMaxReconnects() {
        return delegate.getMaxReconnects();
    }

    @Override public int getMaxRows() {
        return delegate.getMaxRows();
    }

    @Override public int getMetadataCacheSize() {
        return delegate.getMetadataCacheSize();
    }

    @Override public boolean getNoDatetimeStringSync() {
        return delegate.getNoDatetimeStringSync();
    }

    @Override public boolean getNullCatalogMeansCurrent() {
        return delegate.getNullCatalogMeansCurrent();
    }

    @Override public boolean getNullNamePatternMatchesAll() {
        return delegate.getNullNamePatternMatchesAll();
    }

    @Override public int getPacketDebugBufferSize() {
        return delegate.getPacketDebugBufferSize();
    }

    @Override public boolean getParanoid() {
        return delegate.getParanoid();
    }

    @Override public boolean getPedantic() {
        return delegate.getPedantic();
    }

    @Override public int getPreparedStatementCacheSize() {
        return delegate.getPreparedStatementCacheSize();
    }

    @Override public int getPreparedStatementCacheSqlLimit() {
        return delegate.getPreparedStatementCacheSqlLimit();
    }

    @Override public boolean getProfileSql() {
        return delegate.getProfileSql();
    }

    @Override public boolean getProfileSQL() {
        return delegate.getProfileSQL();
    }

    @Override public String getPropertiesTransform() {
        return delegate.getPropertiesTransform();
    }

    @Override public int getQueriesBeforeRetryMaster() {
        return delegate.getQueriesBeforeRetryMaster();
    }

    @Override public boolean getReconnectAtTxEnd() {
        return delegate.getReconnectAtTxEnd();
    }

    @Override public boolean getRelaxAutoCommit() {
        return delegate.getRelaxAutoCommit();
    }

    @Override public int getReportMetricsIntervalMillis() {
        return delegate.getReportMetricsIntervalMillis();
    }

    @Override public boolean getRequireSSL() {
        return delegate.getRequireSSL();
    }

    @Override public boolean getRollbackOnPooledClose() {
        return delegate.getRollbackOnPooledClose();
    }

    @Override public boolean getRoundRobinLoadBalance() {
        return delegate.getRoundRobinLoadBalance();
    }

    @Override public boolean getRunningCTS13() {
        return delegate.getRunningCTS13();
    }

    @Override public int getSecondsBeforeRetryMaster() {
        return delegate.getSecondsBeforeRetryMaster();
    }

    @Override public String getServerTimezone() {
        return delegate.getServerTimezone();
    }

    @Override public String getSessionVariables() {
        return delegate.getSessionVariables();
    }

    @Override public int getSlowQueryThresholdMillis() {
        return delegate.getSlowQueryThresholdMillis();
    }

    @Override public String getSocketFactoryClassName() {
        return delegate.getSocketFactoryClassName();
    }

    @Override public int getSocketTimeout() {
        return delegate.getSocketTimeout();
    }

    @Override public boolean getStrictFloatingPoint() {
        return delegate.getStrictFloatingPoint();
    }

    @Override public boolean getStrictUpdates() {
        return delegate.getStrictUpdates();
    }

    @Override public boolean getTinyInt1isBit() {
        return delegate.getTinyInt1isBit();
    }

    @Override public boolean getTraceProtocol() {
        return delegate.getTraceProtocol();
    }

    @Override public boolean getTransformedBitIsBoolean() {
        return delegate.getTransformedBitIsBoolean();
    }

    @Override public boolean getUseCompression() {
        return delegate.getUseCompression();
    }

    @Override public boolean getUseFastIntParsing() {
        return delegate.getUseFastIntParsing();
    }

    @Override public boolean getUseHostsInPrivileges() {
        return delegate.getUseHostsInPrivileges();
    }

    @Override public boolean getUseInformationSchema() {
        return delegate.getUseInformationSchema();
    }

    @Override public boolean getUseLocalSessionState() {
        return delegate.getUseLocalSessionState();
    }

    @Override public boolean getUseOldUTF8Behavior() {
        return delegate.getUseOldUTF8Behavior();
    }

    @Override public boolean getUseOnlyServerErrorMessages() {
        return delegate.getUseOnlyServerErrorMessages();
    }

    @Override public boolean getUseReadAheadInput() {
        return delegate.getUseReadAheadInput();
    }

    @Override public boolean getUseServerPreparedStmts() {
        return delegate.getUseServerPreparedStmts();
    }

    @Override public boolean getUseSqlStateCodes() {
        return delegate.getUseSqlStateCodes();
    }

    @Override public boolean getUseSSL() {
        return delegate.getUseSSL();
    }

    @Override public boolean isUseSSLExplicit() {
        return delegate.isUseSSLExplicit();
    }

    @Override public boolean getUseStreamLengthsInPrepStmts() {
        return delegate.getUseStreamLengthsInPrepStmts();
    }

    @Override public boolean getUseTimezone() {
        return delegate.getUseTimezone();
    }

    @Override public boolean getUseUltraDevWorkAround() {
        return delegate.getUseUltraDevWorkAround();
    }

    @Override public boolean getUseUnbufferedInput() {
        return delegate.getUseUnbufferedInput();
    }

    @Override public boolean getUseUnicode() {
        return delegate.getUseUnicode();
    }

    @Override public boolean getUseUsageAdvisor() {
        return delegate.getUseUsageAdvisor();
    }

    @Override public boolean getYearIsDateType() {
        return delegate.getYearIsDateType();
    }

    @Override public String getZeroDateTimeBehavior() {
        return delegate.getZeroDateTimeBehavior();
    }

    @Override public void setAllowLoadLocalInfile(boolean b) {
        delegate.setAllowLoadLocalInfile(b);
    }

    @Override public void setAllowMultiQueries(boolean b) {
        delegate.setAllowMultiQueries(b);
    }

    @Override public void setAllowNanAndInf(boolean b) {
        delegate.setAllowNanAndInf(b);
    }

    @Override public void setAllowUrlInLocalInfile(boolean b) {
        delegate.setAllowLoadLocalInfile(b);
    }

    @Override public void setAlwaysSendSetIsolation(boolean b) {
        delegate.setAlwaysSendSetIsolation(b);
    }

    @Override public void setAutoDeserialize(boolean b) {
        delegate.setAutoDeserialize(b);
    }

    @Override public void setAutoGenerateTestcaseScript(boolean b) {
        delegate.setAutoGenerateTestcaseScript(b);
    }

    @Override public void setAutoReconnect(boolean b) {
        delegate.setAutoReconnect(b);
    }

    @Override public void setAutoReconnectForConnectionPools(boolean b) {
        delegate.setAutoReconnectForConnectionPools(b);
    }

    @Override public void setAutoReconnectForPools(boolean b) {
        delegate.setAutoReconnectForPools(b);
    }

    @Override public void setBlobSendChunkSize(String s) throws SQLException {
        delegate.setBlobSendChunkSize(s);
    }

    @Override public void setCacheCallableStatements(boolean b) {
        delegate.setCacheCallableStatements(b);
    }

    @Override public void setCachePreparedStatements(boolean b) {
        delegate.setCachePreparedStatements(b);
    }

    @Override public void setCacheResultSetMetadata(boolean b) {
        delegate.setCacheResultSetMetadata(b);
    }

    @Override public void setCacheServerConfiguration(boolean b) {
        delegate.setCacheServerConfiguration(b);
    }

    @Override public void setCallableStatementCacheSize(int i) throws SQLException {
        delegate.setCallableStatementCacheSize(i);
    }

    @Override public void setCapitalizeDBMDTypes(boolean b) {
        delegate.setCapitalizeDBMDTypes(b);
    }

    @Override public void setCapitalizeTypeNames(boolean b) {
        delegate.setCapitalizeTypeNames(b);
    }

    @Override public void setCharacterEncoding(String s) {
        delegate.setCharacterEncoding(s);
    }

    @Override public void setCharacterSetResults(String s) {
        delegate.setCharacterSetResults(s);
    }

    @Override public void setClobberStreamingResults(boolean b) {
        delegate.setClobberStreamingResults(b);
    }

    @Override public void setClobCharacterEncoding(String s) {
        delegate.setClobCharacterEncoding(s);
    }

    @Override public void setConnectionCollation(String s) {
        delegate.setConnectionCollation(s);
    }

    @Override public void setConnectTimeout(int i) throws SQLException {
        delegate.setConnectTimeout(i);
    }

    @Override public void setContinueBatchOnError(boolean b) {
        delegate.setContinueBatchOnError(b);
    }

    @Override public void setCreateDatabaseIfNotExist(boolean b) {
        delegate.setCreateDatabaseIfNotExist(b);
    }

    @Override public void setDefaultFetchSize(int i) throws SQLException {
        delegate.setDefaultFetchSize(i);
    }

    @Override public void setDetectServerPreparedStmts(boolean b) {
        delegate.setDetectServerPreparedStmts(b);
    }

    @Override public void setDontTrackOpenResources(boolean b) {
        delegate.setDontTrackOpenResources(b);
    }

    @Override public void setDumpQueriesOnException(boolean b) {
        delegate.setDumpQueriesOnException(b);
    }

    @Override public void setDynamicCalendars(boolean b) {
        delegate.setDynamicCalendars(b);
    }

    @Override public void setElideSetAutoCommits(boolean b) {
        delegate.setElideSetAutoCommits(b);
    }

    @Override public void setEmptyStringsConvertToZero(boolean b) {
        delegate.setEmptyStringsConvertToZero(b);
    }

    @Override public void setEmulateLocators(boolean b) {
        delegate.setEmulateLocators(b);
    }

    @Override public void setEmulateUnsupportedPstmts(boolean b) {
        delegate.setEmulateUnsupportedPstmts(b);
    }

    @Override public void setEnablePacketDebug(boolean b) {
        delegate.setEnablePacketDebug(b);
    }

    @Override public void setEncoding(String s) {
        delegate.setEncoding(s);
    }

    @Override public void setExplainSlowQueries(boolean b) {
        delegate.setExplainSlowQueries(b);
    }

    @Override public void setFailOverReadOnly(boolean b) {
        delegate.setFailOverReadOnly(b);
    }

    @Override public void setGatherPerformanceMetrics(boolean b) {
        delegate.setGatherPerformanceMetrics(b);
    }

    @Override public void setHoldResultsOpenOverStatementClose(boolean b) {
        delegate.setHoldResultsOpenOverStatementClose(b);
    }

    @Override public void setIgnoreNonTxTables(boolean b) {
        delegate.setIgnoreNonTxTables(b);
    }

    @Override public void setInitialTimeout(int i) throws SQLException {
        delegate.setInitialTimeout(i);
    }

    @Override public void setIsInteractiveClient(boolean b) {
        delegate.setIsInteractiveClient(b);
    }

    @Override public void setJdbcCompliantTruncation(boolean b) {
        delegate.setJdbcCompliantTruncation(b);
    }

    @Override public void setLocatorFetchBufferSize(String s) throws SQLException {
        delegate.setLocatorFetchBufferSize(s);
    }

    @Override public void setLogger(String s) {
        delegate.setLogger(s);
    }

    @Override public void setLoggerClassName(String s) {
        delegate.setLoggerClassName(s);
    }

    @Override public void setLogSlowQueries(boolean b) {
        delegate.setLogSlowQueries(b);
    }

    @Override public void setMaintainTimeStats(boolean b) {
        delegate.setMaintainTimeStats(b);
    }

    @Override public void setMaxQuerySizeToLog(int i) throws SQLException {
        delegate.setMaxQuerySizeToLog(i);
    }

    @Override public void setMaxReconnects(int i) throws SQLException {
        delegate.setMaxReconnects(i);
    }

    @Override public void setMaxRows(int i) throws SQLException {
        delegate.setMaxRows(i);
    }

    @Override public void setMetadataCacheSize(int i) throws SQLException {
        delegate.setMetadataCacheSize(i);
    }

    @Override public void setNoDatetimeStringSync(boolean b) {
        delegate.setNoDatetimeStringSync(b);
    }

    @Override public void setNullCatalogMeansCurrent(boolean b) {
        delegate.setNullCatalogMeansCurrent(b);
    }

    @Override public void setNullNamePatternMatchesAll(boolean b) {
        delegate.setNullNamePatternMatchesAll(b);
    }

    @Override public void setPacketDebugBufferSize(int i) throws SQLException {
        delegate.setPacketDebugBufferSize(i);
    }

    @Override public void setParanoid(boolean b) {
        delegate.setParanoid(b);
    }

    @Override public void setPedantic(boolean b) {
        delegate.setPedantic(b);
    }

    @Override public void setPreparedStatementCacheSize(int i) throws SQLException {
        delegate.setPreparedStatementCacheSize(i);
    }

    @Override public void setPreparedStatementCacheSqlLimit(int i) throws SQLException {
        delegate.setPreparedStatementCacheSqlLimit(i);
    }

    @Override public void setProfileSql(boolean b) {
        delegate.setProfileSql(b);
    }

    @Override public void setProfileSQL(boolean b) {
        delegate.setProfileSQL(b);
    }

    @Override public void setPropertiesTransform(String s) {
        delegate.setPropertiesTransform(s);
    }

    @Override public void setQueriesBeforeRetryMaster(int i) throws SQLException {
        delegate.setQueriesBeforeRetryMaster(i);
    }

    @Override public void setReconnectAtTxEnd(boolean b) {
        delegate.setReconnectAtTxEnd(b);
    }

    @Override public void setRelaxAutoCommit(boolean b) {
        delegate.setRelaxAutoCommit(b);
    }

    @Override public void setReportMetricsIntervalMillis(int i) throws SQLException {
        delegate.setReportMetricsIntervalMillis(i);
    }

    @Override public void setRequireSSL(boolean b) {
        delegate.setRequireSSL(b);
    }

    @Override public void setRetainStatementAfterResultSetClose(boolean b) {
        delegate.setRetainStatementAfterResultSetClose(b);
    }

    @Override public void setRollbackOnPooledClose(boolean b) {
        delegate.setRollbackOnPooledClose(b);
    }

    @Override public void setRoundRobinLoadBalance(boolean b) {
        delegate.setRoundRobinLoadBalance(b);
    }

    @Override public void setRunningCTS13(boolean b) {
        delegate.setRunningCTS13(b);
    }

    @Override public void setSecondsBeforeRetryMaster(int i) throws SQLException {
        delegate.setSecondsBeforeRetryMaster(i);
    }

    @Override public void setServerTimezone(String s) {
        delegate.setServerTimezone(s);
    }

    @Override public void setSessionVariables(String s) {
        delegate.setSessionVariables(s);
    }

    @Override public void setSlowQueryThresholdMillis(int i) throws SQLException {
        delegate.setSlowQueryThresholdMillis(i);
    }

    @Override public void setSocketFactoryClassName(String s) {
        delegate.setSocketFactoryClassName(s);
    }

    @Override public void setSocketTimeout(int i) throws SQLException {
        delegate.setSocketTimeout(i);
    }

    @Override public void setStrictFloatingPoint(boolean b) {
        delegate.setStrictFloatingPoint(b);
    }

    @Override public void setStrictUpdates(boolean b) {
        delegate.setStrictUpdates(b);
    }

    @Override public void setTinyInt1isBit(boolean b) {
        delegate.setTinyInt1isBit(b);
    }

    @Override public void setTraceProtocol(boolean b) {
        delegate.setTraceProtocol(b);
    }

    @Override public void setTransformedBitIsBoolean(boolean b) {
        delegate.setTransformedBitIsBoolean(b);
    }

    @Override public void setUseCompression(boolean b) {
        delegate.setUseCompression(b);
    }

    @Override public void setUseFastIntParsing(boolean b) {
        delegate.setUseFastIntParsing(b);
    }

    @Override public void setUseHostsInPrivileges(boolean b) {
        delegate.setUseHostsInPrivileges(b);
    }

    @Override public void setUseInformationSchema(boolean b) {
        delegate.setUseInformationSchema(b);
    }

    @Override public void setUseLocalSessionState(boolean b) {
        delegate.setUseLocalSessionState(b);
    }

    @Override public void setUseOldUTF8Behavior(boolean b) {
        delegate.setUseOldUTF8Behavior(b);
    }

    @Override public void setUseOnlyServerErrorMessages(boolean b) {
        delegate.setUseOnlyServerErrorMessages(b);
    }

    @Override public void setUseReadAheadInput(boolean b) {
        delegate.setUseReadAheadInput(b);
    }

    @Override public void setUseServerPreparedStmts(boolean b) {
        delegate.setUseServerPreparedStmts(b);
    }

    @Override public void setUseSqlStateCodes(boolean b) {
        delegate.setUseSqlStateCodes(b);
    }

    @Override public void setUseSSL(boolean b) {
        delegate.setUseSSL(b);
    }

    @Override public void setUseStreamLengthsInPrepStmts(boolean b) {
        delegate.setUseStreamLengthsInPrepStmts(b);
    }

    @Override public void setUseTimezone(boolean b) {
        delegate.setUseTimezone(b);
    }

    @Override public void setUseUltraDevWorkAround(boolean b) {
        delegate.setUseUltraDevWorkAround(b);
    }

    @Override public void setUseUnbufferedInput(boolean b) {
        delegate.setUseUnbufferedInput(b);
    }

    @Override public void setUseUnicode(boolean b) {
        delegate.setUseUnicode(b);
    }

    @Override public void setUseUsageAdvisor(boolean b) {
        delegate.setUseUsageAdvisor(b);
    }

    @Override public void setYearIsDateType(boolean b) {
        delegate.setYearIsDateType(b);
    }

    @Override public void setZeroDateTimeBehavior(String s) {
        delegate.setZeroDateTimeBehavior(s);
    }

    @Override public boolean useUnbufferedInput() {
        return delegate.useUnbufferedInput();
    }

    @Override public boolean getUseCursorFetch() {
        return delegate.getUseCursorFetch();
    }

    @Override public void setUseCursorFetch(boolean b) {
        delegate.setUseCursorFetch(b);
    }

    @Override public boolean getOverrideSupportsIntegrityEnhancementFacility() {
        return delegate.getOverrideSupportsIntegrityEnhancementFacility();
    }

    @Override public void setOverrideSupportsIntegrityEnhancementFacility(boolean b) {
        delegate.setOverrideSupportsIntegrityEnhancementFacility(b);
    }

    @Override public boolean getNoTimezoneConversionForTimeType() {
        return delegate.getNoTimezoneConversionForTimeType();
    }

    @Override public void setNoTimezoneConversionForTimeType(boolean b) {
        delegate.setNoTimezoneConversionForTimeType(b);
    }

    @Override public boolean getNoTimezoneConversionForDateType() {
        return delegate.getNoTimezoneConversionForDateType();
    }

    @Override public void setNoTimezoneConversionForDateType(boolean b) {
        delegate.setNoTimezoneConversionForDateType(b);
    }

    @Override public boolean getCacheDefaultTimezone() {
        return delegate.getCacheDefaultTimezone();
    }

    @Override public void setCacheDefaultTimezone(boolean b) {
        delegate.setCacheDefaultTimezone(b);
    }

    @Override public boolean getUseJDBCCompliantTimezoneShift() {
        return delegate.getUseJDBCCompliantTimezoneShift();
    }

    @Override public void setUseJDBCCompliantTimezoneShift(boolean b) {
        delegate.setUseJDBCCompliantTimezoneShift(b);
    }

    @Override public boolean getAutoClosePStmtStreams() {
        return delegate.getAutoClosePStmtStreams();
    }

    @Override public void setAutoClosePStmtStreams(boolean b) {
        delegate.setAutoClosePStmtStreams(b);
    }

    @Override public boolean getProcessEscapeCodesForPrepStmts() {
        return delegate.getProcessEscapeCodesForPrepStmts();
    }

    @Override public void setProcessEscapeCodesForPrepStmts(boolean b) {
        delegate.setProcessEscapeCodesForPrepStmts(b);
    }

    @Override public boolean getUseGmtMillisForDatetimes() {
        return delegate.getUseGmtMillisForDatetimes();
    }

    @Override public void setUseGmtMillisForDatetimes(boolean b) {
        delegate.setUseGmtMillisForDatetimes(b);
    }

    @Override public boolean getDumpMetadataOnColumnNotFound() {
        return delegate.getDumpMetadataOnColumnNotFound();
    }

    @Override public void setDumpMetadataOnColumnNotFound(boolean b) {
        delegate.setDumpMetadataOnColumnNotFound(b);
    }

    @Override public String getResourceId() {
        return delegate.getResourceId();
    }

    @Override public void setResourceId(String s) {
        delegate.setResourceId(s);
    }

    @Override public boolean getRewriteBatchedStatements() {
        return delegate.getRewriteBatchedStatements();
    }

    @Override public void setRewriteBatchedStatements(boolean b) {
        delegate.setRewriteBatchedStatements(b);
    }

    @Override public boolean getJdbcCompliantTruncationForReads() {
        return delegate.getJdbcCompliantTruncationForReads();
    }

    @Override public void setJdbcCompliantTruncationForReads(boolean b) {
        delegate.setJdbcCompliantTruncationForReads(b);
    }

    @Override public boolean getUseJvmCharsetConverters() {
        return delegate.getUseJvmCharsetConverters();
    }

    @Override public void setUseJvmCharsetConverters(boolean b) {
        delegate.setUseJvmCharsetConverters(b);
    }

    @Override public boolean getPinGlobalTxToPhysicalConnection() {
        return delegate.getPinGlobalTxToPhysicalConnection();
    }

    @Override public void setPinGlobalTxToPhysicalConnection(boolean b) {
        delegate.setPinGlobalTxToPhysicalConnection(b);
    }

    @Override public void setGatherPerfMetrics(boolean b) {
        delegate.setGatherPerfMetrics(b);
    }

    @Override public boolean getGatherPerfMetrics() {
        return delegate.getGatherPerfMetrics();
    }

    @Override public void setUltraDevHack(boolean b) {
        delegate.setUltraDevHack(b);
    }

    @Override public boolean getUltraDevHack() {
        return delegate.getUltraDevHack();
    }

    @Override public void setInteractiveClient(boolean b) {
        delegate.setInteractiveClient(b);
    }

    @Override public void setSocketFactory(String s) {
        delegate.setSocketFactory(s);
    }

    @Override public String getSocketFactory() {
        return delegate.getSocketFactory();
    }

    @Override public void setUseServerPrepStmts(boolean b) {
        delegate.setUseServerPrepStmts(b);
    }

    @Override public boolean getUseServerPrepStmts() {
        return delegate.getUseServerPrepStmts();
    }

    @Override public void setCacheCallableStmts(boolean b) {
        delegate.setCacheCallableStmts(b);
    }

    @Override public boolean getCacheCallableStmts() {
        return delegate.getCacheCallableStmts();
    }

    @Override public void setCachePrepStmts(boolean b) {
        delegate.setCachePrepStmts(b);
    }

    @Override public boolean getCachePrepStmts() {
        return delegate.getCachePrepStmts();
    }

    @Override public void setCallableStmtCacheSize(int i) throws SQLException {
        delegate.setCallableStmtCacheSize(i);
    }

    @Override public int getCallableStmtCacheSize() {
        return delegate.getCallableStmtCacheSize();
    }

    @Override public void setPrepStmtCacheSize(int i) throws SQLException {
        delegate.setPrepStmtCacheSize(i);
    }

    @Override public int getPrepStmtCacheSize() {
        return delegate.getPrepStmtCacheSize();
    }

    @Override public void setPrepStmtCacheSqlLimit(int i) throws SQLException {
        delegate.setPrepStmtCacheSqlLimit(i);
    }

    @Override public int getPrepStmtCacheSqlLimit() {
        return delegate.getPrepStmtCacheSqlLimit();
    }

    @Override public boolean getNoAccessToProcedureBodies() {
        return delegate.getNoAccessToProcedureBodies();
    }

    @Override public void setNoAccessToProcedureBodies(boolean b) {
        delegate.setNoAccessToProcedureBodies(b);
    }

    @Override public boolean getUseOldAliasMetadataBehavior() {
        return delegate.getUseOldAliasMetadataBehavior();
    }

    @Override public void setUseOldAliasMetadataBehavior(boolean b) {
        delegate.setUseOldAliasMetadataBehavior(b);
    }

    @Override public String getClientCertificateKeyStorePassword() {
        return delegate.getClientCertificateKeyStorePassword();
    }

    @Override public void setClientCertificateKeyStorePassword(String s) {
        delegate.setClientCertificateKeyStorePassword(s);
    }

    @Override public String getClientCertificateKeyStoreType() {
        return delegate.getClientCertificateKeyStoreType();
    }

    @Override public void setClientCertificateKeyStoreType(String s) {
        delegate.setClientCertificateKeyStoreType(s);
    }

    @Override public String getClientCertificateKeyStoreUrl() {
        return delegate.getClientCertificateKeyStoreUrl();
    }

    @Override public void setClientCertificateKeyStoreUrl(String s) {
        delegate.setClientCertificateKeyStoreUrl(s);
    }

    @Override public String getTrustCertificateKeyStorePassword() {
        return delegate.getTrustCertificateKeyStorePassword();
    }

    @Override public void setTrustCertificateKeyStorePassword(String s) {
        delegate.setTrustCertificateKeyStorePassword(s);
    }

    @Override public String getTrustCertificateKeyStoreType() {
        return delegate.getTrustCertificateKeyStoreType();
    }

    @Override public void setTrustCertificateKeyStoreType(String s) {
        delegate.setTrustCertificateKeyStoreType(s);
    }

    @Override public String getTrustCertificateKeyStoreUrl() {
        return delegate.getTrustCertificateKeyStoreUrl();
    }

    @Override public void setTrustCertificateKeyStoreUrl(String s) {
        delegate.setTrustCertificateKeyStoreUrl(s);
    }

    @Override public boolean getUseSSPSCompatibleTimezoneShift() {
        return delegate.getUseSSPSCompatibleTimezoneShift();
    }

    @Override public void setUseSSPSCompatibleTimezoneShift(boolean b) {
        delegate.setUseSSPSCompatibleTimezoneShift(b);
    }

    @Override public boolean getTreatUtilDateAsTimestamp() {
        return delegate.getTreatUtilDateAsTimestamp();
    }

    @Override public void setTreatUtilDateAsTimestamp(boolean b) {
        delegate.setTreatUtilDateAsTimestamp(b);
    }

    @Override public boolean getUseFastDateParsing() {
        return delegate.getUseFastDateParsing();
    }

    @Override public void setUseFastDateParsing(boolean b) {
        delegate.setUseFastDateParsing(b);
    }

    @Override public String getLocalSocketAddress() {
        return delegate.getLocalSocketAddress();
    }

    @Override public void setLocalSocketAddress(String s) {
        delegate.setLocalSocketAddress(s);
    }

    @Override public void setUseConfigs(String s) {
        delegate.setUseConfigs(s);
    }

    @Override public String getUseConfigs() {
        return delegate.getUseConfigs();
    }

    @Override public boolean getGenerateSimpleParameterMetadata() {
        return delegate.getGenerateSimpleParameterMetadata();
    }

    @Override public void setGenerateSimpleParameterMetadata(boolean b) {
        delegate.setGenerateSimpleParameterMetadata(b);
    }

    @Override public boolean getLogXaCommands() {
        return delegate.getLogXaCommands();
    }

    @Override public void setLogXaCommands(boolean b) {
        delegate.setLogXaCommands(b);
    }

    @Override public int getResultSetSizeThreshold() {
        return delegate.getResultSetSizeThreshold();
    }

    @Override public void setResultSetSizeThreshold(int i) throws SQLException {
        delegate.setResultSetSizeThreshold(i);
    }

    @Override public int getNetTimeoutForStreamingResults() {
        return delegate.getNetTimeoutForStreamingResults();
    }

    @Override public void setNetTimeoutForStreamingResults(int i) throws SQLException {
        delegate.setNetTimeoutForStreamingResults(i);
    }

    @Override public boolean getEnableQueryTimeouts() {
        return delegate.getEnableQueryTimeouts();
    }

    @Override public void setEnableQueryTimeouts(boolean b) {
        delegate.setEnableQueryTimeouts(b);
    }

    @Override public boolean getPadCharsWithSpace() {
        return delegate.getPadCharsWithSpace();
    }

    @Override public void setPadCharsWithSpace(boolean b) {
        delegate.setPadCharsWithSpace(b);
    }

    @Override public boolean getUseDynamicCharsetInfo() {
        return delegate.getUseDynamicCharsetInfo();
    }

    @Override public void setUseDynamicCharsetInfo(boolean b) {
        delegate.setUseDynamicCharsetInfo(b);
    }

    @Override public String getClientInfoProvider() {
        return delegate.getClientInfoProvider();
    }

    @Override public void setClientInfoProvider(String s) {
        delegate.setClientInfoProvider(s);
    }

    @Override public boolean getPopulateInsertRowWithDefaultValues() {
        return delegate.getPopulateInsertRowWithDefaultValues();
    }

    @Override public void setPopulateInsertRowWithDefaultValues(boolean b) {
        delegate.setPopulateInsertRowWithDefaultValues(b);
    }

    @Override public String getLoadBalanceStrategy() {
        return delegate.getLoadBalanceStrategy();
    }

    @Override public void setLoadBalanceStrategy(String s) {
        delegate.setLoadBalanceStrategy(s);
    }

    @Override public String getServerAffinityOrder() {
        return delegate.getServerAffinityOrder();
    }

    @Override public void setServerAffinityOrder(String s) {
        delegate.setServerAffinityOrder(s);
    }

    @Override public boolean getTcpNoDelay() {
        return delegate.getTcpNoDelay();
    }

    @Override public void setTcpNoDelay(boolean b) {
        delegate.setTcpNoDelay(b);
    }

    @Override public boolean getTcpKeepAlive() {
        return delegate.getTcpKeepAlive();
    }

    @Override public void setTcpKeepAlive(boolean b) {
        delegate.setTcpKeepAlive(b);
    }

    @Override public int getTcpRcvBuf() {
        return delegate.getTcpRcvBuf();
    }

    @Override public void setTcpRcvBuf(int i) throws SQLException {
        delegate.setTcpRcvBuf(i);
    }

    @Override public int getTcpSndBuf() {
        return delegate.getTcpSndBuf();
    }

    @Override public void setTcpSndBuf(int i) throws SQLException {
        delegate.setTcpSndBuf(i);
    }

    @Override public int getTcpTrafficClass() {
        return delegate.getTcpTrafficClass();
    }

    @Override public void setTcpTrafficClass(int i) throws SQLException {
        delegate.setTcpTrafficClass(i);
    }

    @Override public boolean getUseNanosForElapsedTime() {
        return delegate.getUseNanosForElapsedTime();
    }

    @Override public void setUseNanosForElapsedTime(boolean b) {
        delegate.setUseNanosForElapsedTime(b);
    }

    @Override public long getSlowQueryThresholdNanos() {
        return delegate.getSlowQueryThresholdNanos();
    }

    @Override public void setSlowQueryThresholdNanos(long l) throws SQLException {
        delegate.setSlowQueryThresholdNanos(l);
    }

    @Override public String getStatementInterceptors() {
        return delegate.getStatementInterceptors();
    }

    @Override public void setStatementInterceptors(String s) {
        delegate.setStatementInterceptors(s);
    }

    @Override public boolean getUseDirectRowUnpack() {
        return delegate.getUseDirectRowUnpack();
    }

    @Override public void setUseDirectRowUnpack(boolean b) {
        delegate.setUseDirectRowUnpack(b);
    }

    @Override public String getLargeRowSizeThreshold() {
        return delegate.getLargeRowSizeThreshold();
    }

    @Override public void setLargeRowSizeThreshold(String s) throws SQLException {
        delegate.setLargeRowSizeThreshold(s);
    }

    @Override public boolean getUseBlobToStoreUTF8OutsideBMP() {
        return delegate.getUseBlobToStoreUTF8OutsideBMP();
    }

    @Override public void setUseBlobToStoreUTF8OutsideBMP(boolean b) {
        delegate.setUseBlobToStoreUTF8OutsideBMP(b);
    }

    @Override public String getUtf8OutsideBmpExcludedColumnNamePattern() {
        return delegate.getUtf8OutsideBmpExcludedColumnNamePattern();
    }

    @Override public void setUtf8OutsideBmpExcludedColumnNamePattern(String s) {
        delegate.setUtf8OutsideBmpExcludedColumnNamePattern(s);
    }

    @Override public String getUtf8OutsideBmpIncludedColumnNamePattern() {
        return delegate.getUtf8OutsideBmpIncludedColumnNamePattern();
    }

    @Override public void setUtf8OutsideBmpIncludedColumnNamePattern(String s) {
        delegate.setUtf8OutsideBmpExcludedColumnNamePattern(s);
    }

    @Override public boolean getIncludeInnodbStatusInDeadlockExceptions() {
        return delegate.getIncludeInnodbStatusInDeadlockExceptions();
    }

    @Override public void setIncludeInnodbStatusInDeadlockExceptions(boolean b) {
        delegate.setIncludeInnodbStatusInDeadlockExceptions(b);
    }

    @Override public boolean getIncludeThreadDumpInDeadlockExceptions() {
        return delegate.getIncludeThreadDumpInDeadlockExceptions();
    }

    @Override public void setIncludeThreadDumpInDeadlockExceptions(boolean b) {
        delegate.setIncludeThreadDumpInDeadlockExceptions(b);
    }

    @Override public boolean getIncludeThreadNamesAsStatementComment() {
        return delegate.getIncludeThreadNamesAsStatementComment();
    }

    @Override public void setIncludeThreadNamesAsStatementComment(boolean b) {
        delegate.setIncludeThreadNamesAsStatementComment(b);
    }

    @Override public boolean getBlobsAreStrings() {
        return delegate.getBlobsAreStrings();
    }

    @Override public void setBlobsAreStrings(boolean b) {
        delegate.setBlobsAreStrings(b);
    }

    @Override public boolean getFunctionsNeverReturnBlobs() {
        return delegate.getFunctionsNeverReturnBlobs();
    }

    @Override public void setFunctionsNeverReturnBlobs(boolean b) {
        delegate.setFunctionsNeverReturnBlobs(b);
    }

    @Override public boolean getAutoSlowLog() {
        return delegate.getAutoSlowLog();
    }

    @Override public void setAutoSlowLog(boolean b) {
        delegate.setAutoSlowLog(b);
    }

    @Override public String getConnectionLifecycleInterceptors() {
        return delegate.getConnectionLifecycleInterceptors();
    }

    @Override public void setConnectionLifecycleInterceptors(String s) {
        delegate.setConnectionLifecycleInterceptors(s);
    }

    @Override public String getProfilerEventHandler() {
        return delegate.getProfilerEventHandler();
    }

    @Override public void setProfilerEventHandler(String s) {
        delegate.setProfilerEventHandler(s);
    }

    @Override public boolean getVerifyServerCertificate() {
        return delegate.getVerifyServerCertificate();
    }

    @Override public void setVerifyServerCertificate(boolean b) {
        delegate.setVerifyServerCertificate(b);
    }

    @Override public boolean getUseLegacyDatetimeCode() {
        return delegate.getUseLegacyDatetimeCode();
    }

    @Override public void setUseLegacyDatetimeCode(boolean b) {
        delegate.setUseLegacyDatetimeCode(b);
    }

    @Override public boolean getSendFractionalSeconds() {
        return delegate.getSendFractionalSeconds();
    }

    @Override public void setSendFractionalSeconds(boolean b) {
        delegate.setSendFractionalSeconds(b);
    }

    @Override public int getSelfDestructOnPingSecondsLifetime() {
        return delegate.getSelfDestructOnPingSecondsLifetime();
    }

    @Override public void setSelfDestructOnPingSecondsLifetime(int i) throws SQLException {
        delegate.setSelfDestructOnPingSecondsLifetime(i);
    }

    @Override public int getSelfDestructOnPingMaxOperations() {
        return delegate.getSelfDestructOnPingMaxOperations();
    }

    @Override public void setSelfDestructOnPingMaxOperations(int i) throws SQLException {
        delegate.setSelfDestructOnPingMaxOperations(i);
    }

    @Override public boolean getUseColumnNamesInFindColumn() {
        return delegate.getUseColumnNamesInFindColumn();
    }

    @Override public void setUseColumnNamesInFindColumn(boolean b) {
        delegate.setUseColumnNamesInFindColumn(b);
    }

    @Override public boolean getUseLocalTransactionState() {
        return delegate.getUseLocalTransactionState();
    }

    @Override public void setUseLocalTransactionState(boolean b) {
        delegate.setUseLocalTransactionState(b);
    }

    @Override public boolean getCompensateOnDuplicateKeyUpdateCounts() {
        return delegate.getCompensateOnDuplicateKeyUpdateCounts();
    }

    @Override public void setCompensateOnDuplicateKeyUpdateCounts(boolean b) {
        delegate.setCompensateOnDuplicateKeyUpdateCounts(b);
    }

    @Override public void setUseAffectedRows(boolean b) {
        delegate.setUseAffectedRows(b);
    }

    @Override public boolean getUseAffectedRows() {
        return delegate.getUseAffectedRows();
    }

    @Override public void setPasswordCharacterEncoding(String s) {
        delegate.setPasswordCharacterEncoding(s);
    }

    @Override public String getPasswordCharacterEncoding() {
        return delegate.getPasswordCharacterEncoding();
    }

    @Override public int getLoadBalanceBlacklistTimeout() {
        return delegate.getLoadBalanceBlacklistTimeout();
    }

    @Override public void setLoadBalanceBlacklistTimeout(int i) throws SQLException {
        delegate.setLoadBalanceBlacklistTimeout(i);
    }

    @Override public void setRetriesAllDown(int i) throws SQLException {
        delegate.setRetriesAllDown(i);
    }

    @Override public int getRetriesAllDown() {
        return delegate.getRetriesAllDown();
    }

    @Override public ExceptionInterceptor getExceptionInterceptor() {
        return delegate.getExceptionInterceptor();
    }

    @Override public void setExceptionInterceptors(String s) {
        delegate.setExceptionInterceptors(s);
    }

    @Override public String getExceptionInterceptors() {
        return delegate.getExceptionInterceptors();
    }

    @Override public boolean getQueryTimeoutKillsConnection() {
        return delegate.getQueryTimeoutKillsConnection();
    }

    @Override public void setQueryTimeoutKillsConnection(boolean b) {
        delegate.setQueryTimeoutKillsConnection(b);
    }

    @Override public int getMaxAllowedPacket() {
        return delegate.getMaxAllowedPacket();
    }

    @Override public boolean getRetainStatementAfterResultSetClose() {
        return delegate.getRetainStatementAfterResultSetClose();
    }

    @Override public int getLoadBalancePingTimeout() {
        return delegate.getLoadBalancePingTimeout();
    }

    @Override public void setLoadBalancePingTimeout(int i) throws SQLException {
        delegate.setLoadBalancePingTimeout(i);
    }

    @Override public boolean getLoadBalanceValidateConnectionOnSwapServer() {
        return delegate.getLoadBalanceValidateConnectionOnSwapServer();
    }

    @Override public void setLoadBalanceValidateConnectionOnSwapServer(boolean b) {
        delegate.setLoadBalanceValidateConnectionOnSwapServer(b);
    }

    @Override public String getLoadBalanceConnectionGroup() {
        return delegate.getLoadBalanceConnectionGroup();
    }

    @Override public void setLoadBalanceConnectionGroup(String s) {
        delegate.setLoadBalanceConnectionGroup(s);
    }

    @Override public String getLoadBalanceExceptionChecker() {
        return delegate.getLoadBalanceExceptionChecker();
    }

    @Override public void setLoadBalanceExceptionChecker(String s) {
        delegate.setLoadBalanceExceptionChecker(s);
    }

    @Override public String getLoadBalanceSQLStateFailover() {
        return delegate.getLoadBalanceSQLStateFailover();
    }

    @Override public void setLoadBalanceSQLStateFailover(String s) {
        delegate.setLoadBalanceSQLStateFailover(s);
    }

    @Override public String getLoadBalanceSQLExceptionSubclassFailover() {
        return delegate.getLoadBalanceSQLExceptionSubclassFailover();
    }

    @Override public void setLoadBalanceSQLExceptionSubclassFailover(String s) {
        delegate.setLoadBalanceSQLExceptionSubclassFailover(s);
    }

    @Override public boolean getLoadBalanceEnableJMX() {
        return delegate.getLoadBalanceEnableJMX();
    }

    @Override public void setLoadBalanceEnableJMX(boolean b) {
        delegate.setLoadBalanceEnableJMX(b);
    }

    @Override public void setLoadBalanceHostRemovalGracePeriod(int i) throws SQLException {
        delegate.setLoadBalanceHostRemovalGracePeriod(i);
    }

    @Override public int getLoadBalanceHostRemovalGracePeriod() {
        return delegate.getLoadBalanceHostRemovalGracePeriod();
    }

    @Override public void setLoadBalanceAutoCommitStatementThreshold(int i) throws SQLException {
        delegate.setLoadBalanceAutoCommitStatementThreshold(i);
    }

    @Override public int getLoadBalanceAutoCommitStatementThreshold() {
        return delegate.getLoadBalanceAutoCommitStatementThreshold();
    }

    @Override public void setLoadBalanceAutoCommitStatementRegex(String s) {
        delegate.setLoadBalanceAutoCommitStatementRegex(s);
    }

    @Override public String getLoadBalanceAutoCommitStatementRegex() {
        return delegate.getLoadBalanceAutoCommitStatementRegex();
    }

    @Override public void setAuthenticationPlugins(String s) {
        delegate.setAuthenticationPlugins(s);
    }

    @Override public String getAuthenticationPlugins() {
        return delegate.getAuthenticationPlugins();
    }

    @Override public void setDisabledAuthenticationPlugins(String s) {
        delegate.setDisabledAuthenticationPlugins(s);
    }

    @Override public String getDisabledAuthenticationPlugins() {
        return delegate.getDisabledAuthenticationPlugins();
    }

    @Override public void setDefaultAuthenticationPlugin(String s) {
        delegate.setDefaultAuthenticationPlugin(s);
    }

    @Override public String getDefaultAuthenticationPlugin() {
        return delegate.getDefaultAuthenticationPlugin();
    }

    @Override public void setParseInfoCacheFactory(String s) {
        delegate.setParseInfoCacheFactory(s);
    }

    @Override public String getParseInfoCacheFactory() {
        return delegate.getParseInfoCacheFactory();
    }

    @Override public void setServerConfigCacheFactory(String s) {
        delegate.setServerConfigCacheFactory(s);
    }

    @Override public String getServerConfigCacheFactory() {
        return delegate.getServerConfigCacheFactory();
    }

    @Override public void setDisconnectOnExpiredPasswords(boolean b) {
        delegate.setDisconnectOnExpiredPasswords(b);
    }

    @Override public boolean getDisconnectOnExpiredPasswords() {
        return delegate.getDisconnectOnExpiredPasswords();
    }

    @Override public boolean getAllowMasterDownConnections() {
        return delegate.getAllowMasterDownConnections();
    }

    @Override public void setAllowMasterDownConnections(boolean b) {
        delegate.setAllowMasterDownConnections(b);
    }

    @Override public boolean getAllowSlaveDownConnections() {
        return delegate.getAllowSlaveDownConnections();
    }

    @Override public void setAllowSlaveDownConnections(boolean b) {
        delegate.setAllowSlaveDownConnections(b);
    }

    @Override public boolean getReadFromMasterWhenNoSlaves() {
        return delegate.getReadFromMasterWhenNoSlaves();
    }

    @Override public void setReadFromMasterWhenNoSlaves(boolean b) {
        delegate.setReadFromMasterWhenNoSlaves(b);
    }

    @Override public boolean getReplicationEnableJMX() {
        return delegate.getReplicationEnableJMX();
    }

    @Override public void setReplicationEnableJMX(boolean b) {
        delegate.setReplicationEnableJMX(b);
    }

    @Override public void setGetProceduresReturnsFunctions(boolean b) {
        delegate.setGetProceduresReturnsFunctions(b);
    }

    @Override public boolean getGetProceduresReturnsFunctions() {
        return delegate.getGetProceduresReturnsFunctions();
    }

    @Override public void setDetectCustomCollations(boolean b) {
        delegate.setDetectCustomCollations(b);
    }

    @Override public boolean getDetectCustomCollations() {
        return delegate.getDetectCustomCollations();
    }

    @Override public String getHost() {
        return delegate.getHost();
    }

    @Override public String getHostPortPair() {
        return delegate.getHostPortPair();
    }

    @Override public long getId() {
        return delegate.getId();
    }

    @Override public long getIdleFor() {
        return delegate.getIdleFor();
    }

    @Override public MysqlIO getIO() throws SQLException {
        return delegate.getIO();
    }

    @Override public Log getLog() throws SQLException {
        return delegate.getLog();
    }

    @Override public String getServerCharacterEncoding() {
        return delegate.getServerCharacterEncoding();
    }

    @Override public int getMaxBytesPerChar(String s) throws SQLException {
        return delegate.getMaxBytesPerChar(s);
    }

    @Override public int getMaxBytesPerChar(Integer integer, String s) throws SQLException {
        return delegate.getMaxBytesPerChar(integer, s);
    }

    @Override public Statement getMetadataSafeStatement() throws SQLException {
        return delegate.getMetadataSafeStatement();
    }

    @Override public int getNetBufferLength() {
        return delegate.getNetBufferLength();
    }

    @Override public Properties getProperties() {
        return delegate.getProperties();
    }

    @Override public boolean getRequiresEscapingEncoder() {
        return delegate.getRequiresEscapingEncoder();
    }

    @Override public String getServerCharset() {
        return delegate.getServerCharset();
    }

    @Override public int getServerMajorVersion() {
        return delegate.getServerMajorVersion();
    }

    @Override public int getServerMinorVersion() {
        return delegate.getServerMinorVersion();
    }

    @Override public int getServerSubMinorVersion() {
        return delegate.getServerSubMinorVersion();
    }

    @Override public TimeZone getServerTimezoneTZ() {
        return delegate.getServerTimezoneTZ();
    }

    @Override public String getServerVariable(String s) {
        return delegate.getServerVariable(s);
    }

    @Override public String getServerVersion() {
        return delegate.getServerVersion();
    }

    @Override public Calendar getSessionLockedCalendar() {
        return delegate.getSessionLockedCalendar();
    }

    @Override public String getStatementComment() {
        return delegate.getStatementComment();
    }

    @Override public boolean hasTriedMaster() {
        return delegate.hasTriedMaster();
    }

    @Override public boolean isInGlobalTx() {
        return delegate.isInGlobalTx();
    }

    @Override public void setInGlobalTx(boolean b) {
        delegate.setInGlobalTx(b);
    }

    @Override public boolean isMasterConnection() {
        return delegate.isMasterConnection();
    }

    @Override public boolean isNoBackslashEscapesSet() {
        return delegate.isNoBackslashEscapesSet();
    }

    @Override public boolean isSameResource(Connection connection) {
        return delegate.isSameResource(connection);
    }

    @Override public List<StatementInterceptorV2> getStatementInterceptorsInstances() {
        return delegate.getStatementInterceptorsInstances();
    }

    @Override public String getURL() {
        return delegate.getURL();
    }

    @Override public String getUser() {
        return delegate.getUser();
    }

    @Override public Calendar getUtcCalendar() {
        return delegate.getUtcCalendar();
    }

    @Override public void incrementNumberOfPreparedExecutes() {
        delegate.incrementNumberOfPreparedExecutes();
    }

    @Override public void incrementNumberOfPrepares() {
        delegate.incrementNumberOfPrepares();
    }

    @Override public void incrementNumberOfResultSetsCreated() {
        delegate.incrementNumberOfResultSetsCreated();
    }

    @Override public void initializeResultsMetadataFromCache(String s, CachedResultSetMetaData cachedResultSetMetaData, ResultSetInternalMethods resultSetInternalMethods) throws SQLException {
        delegate.initializeResultsMetadataFromCache(s, cachedResultSetMetaData, resultSetInternalMethods);
    }

    @Override public void initializeSafeStatementInterceptors() throws SQLException {
        delegate.initializeSafeStatementInterceptors();
    }

    @Override public boolean isAbonormallyLongQuery(long l) {
        return delegate.isAbonormallyLongQuery(l);
    }

    @Override public void initializeExtension(Extension extension) throws SQLException {
        delegate.initializeExtension(extension);
    }

    @Override public boolean isClientTzUTC() {
        return delegate.isClientTzUTC();
    }

    @Override public boolean isCursorFetchEnabled() throws SQLException {
        return delegate.isCursorFetchEnabled();
    }

    @Override public boolean isReadInfoMsgEnabled() {
        return delegate.isReadInfoMsgEnabled();
    }

    @Override public Statement createStatement() throws SQLException {
        return new StatementWrapper(delegate.createStatement(), connectionInfo);
    }

    @Override public PreparedStatement prepareStatement(String sql) throws SQLException {
        return new PreparedStatementWrapper(delegate.prepareStatement(sql), connectionInfo, sql);
    }

    @Override public CallableStatement prepareCall(String sql) throws SQLException {
        return new CallableStatementWrapper(delegate.prepareCall(sql), connectionInfo, sql);
    }

    @Override public String nativeSQL(String sql) throws SQLException {
        return delegate.nativeSQL(sql);
    }

    @Override public void setAutoCommit(boolean autoCommit) throws SQLException {
        delegate.setAutoCommit(autoCommit);
    }

    @Override public boolean getAutoCommit() throws SQLException {
        return delegate.getAutoCommit();
    }

    @Override public void commit() throws SQLException {
        delegate.commit();
    }

    @Override public void rollback() throws SQLException {
        delegate.rollback();
    }

    @Override public void close() throws SQLException {
        delegate.close();
    }

    @Override public boolean isClosed() throws SQLException {
        return delegate.isClosed();
    }

    @Override public DatabaseMetaData getMetaData() throws SQLException {
        return delegate.getMetaData();
    }

    @Override public void setReadOnly(boolean readOnly) throws SQLException {
        delegate.setReadOnly(readOnly);
    }

    @Override public boolean isReadOnly() throws SQLException {
        return delegate.isReadOnly();
    }

    @Override public void setCatalog(String catalog) throws SQLException {
        delegate.setCatalog(catalog);
    }

    @Override public String getCatalog() throws SQLException {
        return delegate.getCatalog();
    }

    @Override public void setTransactionIsolation(int level) throws SQLException {
        delegate.setTransactionIsolation(level);
    }

    @Override public int getTransactionIsolation() throws SQLException {
        return delegate.getTransactionIsolation();
    }

    @Override public SQLWarning getWarnings() throws SQLException {
        return delegate.getWarnings();
    }

    @Override public void clearWarnings() throws SQLException {
        delegate.clearWarnings();
    }

    @Override public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
        return new StatementWrapper(delegate.createStatement(resultSetType, resultSetConcurrency), connectionInfo);
    }

    @Override public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return new PreparedStatementWrapper(delegate.prepareStatement(sql, resultSetType, resultSetConcurrency), connectionInfo, sql);
    }

    @Override public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return new CallableStatementWrapper(delegate.prepareCall(sql, resultSetType, resultSetConcurrency), connectionInfo, sql);
    }

    @Override public Map<String, Class<?>> getTypeMap() throws SQLException {
        return delegate.getTypeMap();
    }

    @Override public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
        delegate.setTypeMap(map);
    }

    @Override public void setHoldability(int holdability) throws SQLException {
        delegate.setHoldability(holdability);
    }

    @Override public int getHoldability() throws SQLException {
        return delegate.getHoldability();
    }

    @Override public Savepoint setSavepoint() throws SQLException {
        return delegate.setSavepoint();
    }

    @Override public Savepoint setSavepoint(String name) throws SQLException {
        return delegate.setSavepoint(name);
    }

    @Override public void rollback(Savepoint savepoint) throws SQLException {
        delegate.rollback(savepoint);
    }

    @Override public void releaseSavepoint(Savepoint savepoint) throws SQLException {
        delegate.releaseSavepoint(savepoint);
    }

    @Override public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return new StatementWrapper(delegate.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability), connectionInfo);
    }

    @Override public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return new PreparedStatementWrapper(delegate.prepareStatement(sql, resultSetType, resultSetConcurrency), connectionInfo, sql);
    }

    @Override public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return new CallableStatementWrapper(delegate.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability), connectionInfo, sql);
    }

    @Override public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
        return new PreparedStatementWrapper(delegate.prepareStatement(sql, autoGeneratedKeys), connectionInfo, sql);
    }

    @Override public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
        return new PreparedStatementWrapper(delegate.prepareStatement(sql, columnIndexes), connectionInfo, sql);
    }

    @Override public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
        return new PreparedStatementWrapper(delegate.prepareStatement(sql, columnNames), connectionInfo, sql);
    }

    @Override public boolean isReadOnly(boolean b) throws SQLException {
        return delegate.isReadOnly();
    }

    @Override public boolean isRunningOnJDK13() {
        return delegate.isRunningOnJDK13();
    }

    @Override public boolean isServerTzUTC() {
        return delegate.isServerTzUTC();
    }

    @Override public boolean lowerCaseTableNames() {
        return delegate.lowerCaseTableNames();
    }

    @Override public boolean parserKnowsUnicode() {
        return delegate.parserKnowsUnicode();
    }

    @Override public void ping() throws SQLException {
        delegate.ping();
    }

    @Override public void resetServerState() throws SQLException {
        delegate.resetServerState();
    }

    @Override public PreparedStatement serverPrepareStatement(String s) throws SQLException {
        return delegate.serverPrepareStatement(s);
    }

    @Override public PreparedStatement serverPrepareStatement(String s, int i) throws SQLException {
        return delegate.serverPrepareStatement(s, i);
    }

    @Override public PreparedStatement serverPrepareStatement(String s, int i, int i1) throws SQLException {
        return delegate.serverPrepareStatement(s, i, i1);
    }

    @Override public PreparedStatement serverPrepareStatement(String s, int i, int i1, int i2) throws SQLException {
        return delegate.serverPrepareStatement(s, i, i1, i2);
    }

    @Override public PreparedStatement serverPrepareStatement(String s, int[] ints) throws SQLException {
        return delegate.serverPrepareStatement(s, ints);
    }

    @Override public PreparedStatement serverPrepareStatement(String s, String[] strings) throws SQLException {
        return delegate.serverPrepareStatement(s, strings);
    }

    @Override public void setFailedOver(boolean b) {
        delegate.setFailedOver(b);
    }

    @Override public void setPreferSlaveDuringFailover(boolean b) {
        delegate.setPreferSlaveDuringFailover(b);
    }

    @Override public void setStatementComment(String s) {
        delegate.setStatementComment(s);
    }

    @Override public void pingInternal(boolean b, int i) throws SQLException {
        delegate.pingInternal(b, i);
    }

    @Override public void realClose(boolean b, boolean b1, boolean b2, Throwable throwable) throws SQLException {
        delegate.realClose(b, b1, b2, throwable);
    }

    @Override public void recachePreparedStatement(ServerPreparedStatement serverPreparedStatement) throws SQLException {
        delegate.recachePreparedStatement(serverPreparedStatement);
    }

    @Override public void decachePreparedStatement(ServerPreparedStatement serverPreparedStatement) throws SQLException {
        delegate.decachePreparedStatement(serverPreparedStatement);
    }

    @Override public void registerQueryExecutionTime(long l) {
        delegate.registerQueryExecutionTime(l);
    }

    @Override public void registerStatement(com.mysql.jdbc.Statement statement) {
        delegate.registerStatement(statement);
    }

    @Override public void reportNumberOfTablesAccessed(int i) {
        delegate.reportNumberOfTablesAccessed(i);
    }

    @Override public boolean serverSupportsConvertFn() throws SQLException {
        return delegate.serverSupportsConvertFn();
    }

    @Override public void setProxy(MySQLConnection mySQLConnection) {
        delegate.setProxy(mySQLConnection);
    }

    @Override public boolean isServerLocal() throws SQLException {
        return delegate.isServerLocal();
    }

    @Override public int getSessionMaxRows() {
        return delegate.getSessionMaxRows();
    }

    @Override public void setSessionMaxRows(int i) throws SQLException {
        delegate.setSessionMaxRows(i);
    }

    @Override public void setSchema(String s) throws SQLException {
        delegate.setSchema(s);
    }

    @Override public String getSchema() throws SQLException {
        return delegate.getSchema();
    }

    @Override public void abort(Executor executor) throws SQLException {
        delegate.abort(executor);
    }

    @Override public void setNetworkTimeout(Executor executor, int i) throws SQLException {
        delegate.setNetworkTimeout(executor, i);
    }

    @Override public int getNetworkTimeout() throws SQLException {
        return delegate.getNetworkTimeout();
    }

    @Override public void abortInternal() throws SQLException {
        delegate.abortInternal();
    }

    @Override public void checkClosed() throws SQLException {
        delegate.checkClosed();
    }

    @Override public Object getConnectionMutex() {
        return delegate.getConnectionMutex();
    }

    @Override public void setReadInfoMsgEnabled(boolean b) {
        delegate.setReadInfoMsgEnabled(b);
    }

    @Override public void setReadOnlyInternal(boolean b) throws SQLException {
        delegate.setReadOnlyInternal(b);
    }

    @Override public void shutdownServer() throws SQLException {
        delegate.shutdownServer();
    }

    @Override public boolean supportsIsolationLevel() {
        return delegate.supportsIsolationLevel();
    }

    @Override public boolean supportsQuotedIdentifiers() {
        return delegate.supportsQuotedIdentifiers();
    }

    @Override public boolean supportsTransactions() {
        return delegate.supportsTransactions();
    }

    @Override public boolean versionMeetsMinimum(int i, int i1, int i2) throws SQLException {
        return delegate.versionMeetsMinimum(i, i1, i2);
    }

    @Override public void reportQueryTime(long l) {
        delegate.reportQueryTime(l);
    }

    @Override public boolean storesLowerCaseTableName() {
        return delegate.storesLowerCaseTableName();
    }

    @Override public void throwConnectionClosedException() throws SQLException {
        delegate.throwConnectionClosedException();
    }

    @Override public void transactionBegun() throws SQLException {
        delegate.transactionBegun();
    }

    @Override public void transactionCompleted() throws SQLException {
        delegate.transactionCompleted();
    }

    @Override public void unregisterStatement(com.mysql.jdbc.Statement statement) {
        delegate.unregisterStatement(statement);
    }

    @Override public void unSafeStatementInterceptors() throws SQLException {
        delegate.unSafeStatementInterceptors();
    }

    @Override public boolean useAnsiQuotedIdentifiers() {
        return delegate.useAnsiQuotedIdentifiers();
    }

    @Override public String getConnectionAttributes() throws SQLException {
        return delegate.getConnectionAttributes();
    }

    @Override public String getServerRSAPublicKeyFile() {
        return delegate.getServerRSAPublicKeyFile();
    }

    @Override public void setServerRSAPublicKeyFile(String s) throws SQLException {
        delegate.setServerRSAPublicKeyFile(s);
    }

    @Override public boolean getAllowPublicKeyRetrieval() {
        return delegate.getAllowPublicKeyRetrieval();
    }

    @Override public void setAllowPublicKeyRetrieval(boolean b) throws SQLException {
        delegate.setAllowPublicKeyRetrieval(b);
    }

    @Override public void setDontCheckOnDuplicateKeyUpdateInSQL(boolean b) {
        delegate.setDontCheckOnDuplicateKeyUpdateInSQL(b);
    }

    @Override public boolean getDontCheckOnDuplicateKeyUpdateInSQL() {
        return delegate.getDontCheckOnDuplicateKeyUpdateInSQL();
    }

    @Override public void setSocksProxyHost(String s) {
        delegate.setSocksProxyHost(s);
    }

    @Override public String getSocksProxyHost() {
        return delegate.getSocksProxyHost();
    }

    @Override public void setSocksProxyPort(int i) throws SQLException {
        delegate.setSocksProxyPort(i);
    }

    @Override public int getSocksProxyPort() {
        return delegate.getSocksProxyPort();
    }

    @Override public boolean getReadOnlyPropagatesToServer() {
        return delegate.getReadOnlyPropagatesToServer();
    }

    @Override public void setReadOnlyPropagatesToServer(boolean b) {
        delegate.setReadOnlyPropagatesToServer(b);
    }

    @Override public String getEnabledSSLCipherSuites() {
        return delegate.getEnabledSSLCipherSuites();
    }

    @Override public void setEnabledSSLCipherSuites(String s) {
        delegate.setEnabledSSLCipherSuites(s);
    }

    @Override public String getEnabledTLSProtocols() {
        return delegate.getEnabledTLSProtocols();
    }

    @Override public void setEnabledTLSProtocols(String s) {
        delegate.setEnabledTLSProtocols(s);
    }

    @Override public boolean getEnableEscapeProcessing() {
        return delegate.getEnableEscapeProcessing();
    }

    @Override public void setEnableEscapeProcessing(boolean b) {
        delegate.setEnableEscapeProcessing(b);
    }

    @Override public MySQLConnection getLoadBalanceSafeProxy() {
        return delegate.getLoadBalanceSafeProxy();
    }

    @Override public MySQLConnection getMultiHostSafeProxy() {
        return delegate.getMultiHostSafeProxy();
    }

    @Override public MySQLConnection getActiveMySQLConnection() {
        return delegate.getActiveMySQLConnection();
    }

    @Override public ProfilerEventHandler getProfilerEventHandlerInstance() {
        return delegate.getProfilerEventHandlerInstance();
    }

    @Override public void setProfilerEventHandlerInstance(ProfilerEventHandler profilerEventHandler) {
        delegate.setProfilerEventHandlerInstance(profilerEventHandler);
    }

    @Override public boolean isServerTruncatesFracSecs() {
        return delegate.isServerTruncatesFracSecs();
    }
}