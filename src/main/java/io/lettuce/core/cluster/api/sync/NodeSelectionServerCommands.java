/*
 * Copyright 2017-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.lettuce.core.cluster.api.sync;

import java.util.Date;
import java.util.List;
import java.util.Map;

import io.lettuce.core.ClientListArgs;
import io.lettuce.core.FlushMode;
import io.lettuce.core.KillArgs;
import io.lettuce.core.TrackingArgs;
import io.lettuce.core.UnblockType;
import io.lettuce.core.protocol.CommandType;

/**
 * Synchronous executed commands on a node selection for Server Control.
 *
 * @param <K> Key type.
 * @param <V> Value type.
 * @author Mark Paluch
 * @since 4.0
 * @generated by io.lettuce.apigenerator.CreateSyncNodeSelectionClusterApi
 */
public interface NodeSelectionServerCommands<K, V> {

    /**
     * Asynchronously rewrite the append-only file.
     *
     * @return String simple-string-reply always {@code OK}.
     */
    Executions<String> bgrewriteaof();

    /**
     * Asynchronously save the dataset to disk.
     *
     * @return String simple-string-reply.
     */
    Executions<String> bgsave();

    /**
     * Control tracking of keys in the context of server-assisted client cache invalidation.
     *
     * @param enabled {@code true} to enable key tracking.
     * @return String simple-string-reply {@code OK}.
     * @since 6.0
     */
    Executions<String> clientCaching(boolean enabled);

    /**
     * Get the current connection name.
     *
     * @return K bulk-string-reply The connection name, or a null bulk reply if no name is set.
     */
    Executions<K> clientGetname();

    /**
     * Returns the client ID we are redirecting our tracking notifications to.
     *
     * @return the ID of the client we are redirecting the notifications to. The command returns -1 if client tracking is not
     *         enabled, or 0 if client tracking is enabled but we are not redirecting the notifications to any client.
     * @since 6.0
     */
    Executions<Long> clientGetredir();

    /**
     * Get the id of the current connection.
     *
     * @return Long The command just returns the ID of the current connection.
     * @since 5.3
     */
    Executions<Long> clientId();

    /**
     * Kill the connection of a client identified by ip:port.
     *
     * @param addr ip:port.
     * @return String simple-string-reply {@code OK} if the connection exists and has been closed.
     */
    Executions<String> clientKill(String addr);

    /**
     * Kill connections of clients which are filtered by {@code killArgs}.
     *
     * @param killArgs args for the kill operation.
     * @return Long integer-reply number of killed connections.
     */
    Executions<Long> clientKill(KillArgs killArgs);

    /**
     * Get the list of client connections.
     *
     * @return String bulk-string-reply a unique string, formatted as follows: One client connection per line (separated by LF),
     *         each line is composed of a succession of property=value fields separated by a space character.
     */
    Executions<String> clientList();

    /**
     * Get the list of client connections which are filtered by {@code clientListArgs}.
     *
     * @return String bulk-string-reply a unique string, formatted as follows: One client connection per line (separated by LF),
     *         each line is composed of a succession of property=value fields separated by a space character.
     * @since 6.3
     */
    Executions<String> clientList(ClientListArgs clientListArgs);

    /**
     * Get the list of the current client connection.
     *
     * @return String bulk-string-reply a unique string, formatted as a succession of property=value fields separated by a space
     *         character.
     * @since 6.3
     */
    Executions<String> clientInfo();

    /**
     * Sets the client eviction mode for the current connection.
     *
     * @param on {@code true} will turn eviction mode on, and {@code false} will turn it off.
     * @return String simple-string-reply {@code OK}.
     * @since 6.2
     */
    Executions<String> clientNoEvict(boolean on);

    /**
     * Stop processing commands from clients for some time.
     *
     * @param timeout the timeout value in milliseconds.
     * @return String simple-string-reply The command returns OK or an error if the timeout is invalid.
     */
    Executions<String> clientPause(long timeout);

    /**
     * Set the current connection name.
     *
     * @param name the client name.
     * @return simple-string-reply {@code OK} if the connection name was successfully set.
     */
    Executions<String> clientSetname(K name);

    /**
     * Assign various info attributes to the current connection.
     *
     * @param key the key.
     * @param value the value.
     * @return simple-string-reply {@code OK} if the connection name was successfully set.
     * @since 6.3
     */
    Executions<String> clientSetinfo(String key, V value);

    /**
     * Enables the tracking feature of the Redis server, that is used for server assisted client side caching. Tracking messages
     * are either available when using the RESP3 protocol or through Pub/Sub notification when using RESP2.
     *
     * @param args for the CLIENT TRACKING operation.
     * @return String simple-string-reply {@code OK}.
     * @since 6.0
     */
    Executions<String> clientTracking(TrackingArgs args);

    /**
     * Unblock the specified blocked client.
     *
     * @param id the client id.
     * @param type unblock type.
     * @return Long integer-reply number of unblocked connections.
     * @since 5.1
     */
    Executions<Long> clientUnblock(long id, UnblockType type);

    /**
     * Returns an array reply of details about all Redis commands.
     *
     * @return List&lt;Object&gt; array-reply.
     */
    Executions<List<Object>> command();

    /**
     * Get total number of Redis commands.
     *
     * @return Long integer-reply of number of total commands in this Redis server.
     */
    Executions<Long> commandCount();

    /**
     * Returns an array reply of details about the requested commands.
     *
     * @param commands the commands to query for.
     * @return List&lt;Object&gt; array-reply.
     */
    Executions<List<Object>> commandInfo(String... commands);

    /**
     * Returns an array reply of details about the requested commands.
     *
     * @param commands the commands to query for.
     * @return List&lt;Object&gt; array-reply.
     */
    Executions<List<Object>> commandInfo(CommandType... commands);

    /**
     * Get the value of a configuration parameter.
     *
     * @param parameter name of the parameter.
     * @return Map&lt;String, String&gt; bulk-string-reply.
     */
    Executions<Map<String, String>> configGet(String parameter);

    /**
     * Get the value of multiple pattern parameters.
     *
     * @param parameters patterns names of Redis server's configuration.
     * @return Map&lt;String, String&gt; bulk-string-reply.
     * @since 6.2
     */
    Executions<Map<String, String>> configGet(String... parameters);

    /**
     * Reset the stats returned by INFO.
     *
     * @return String simple-string-reply always {@code OK}.
     */
    Executions<String> configResetstat();

    /**
     * Rewrite the configuration file with the in memory configuration.
     *
     * @return String simple-string-reply {@code OK} when the configuration was rewritten properly. Otherwise an error is
     *         returned.
     */
    Executions<String> configRewrite();

    /**
     * Set a configuration parameter to the given value.
     *
     * @param parameter the parameter name.
     * @param value the parameter value.
     * @return String simple-string-reply: {@code OK} when the configuration was set properly. Otherwise an error is returned.
     */
    Executions<String> configSet(String parameter, String value);

    /**
     * Set multiple parameters to the given value.
     *
     * @param kvs the parameter name and value.
     * @return String simple-string-reply: {@code OK} when the configuration was set properly. Otherwise an error is returned.
     * @since 6.2
     */
    Executions<String> configSet(Map<String, String> kvs);

    /**
     * Return the number of keys in the selected database.
     *
     * @return Long integer-reply.
     */
    Executions<Long> dbsize();

    /**
     * Crash and recover.
     *
     * @param delay optional delay in milliseconds.
     * @return String simple-string-reply.
     */
    Executions<String> debugCrashAndRecover(Long delay);

    /**
     * Get debugging information about the internal hash-table state.
     *
     * @param db the database number.
     * @return String simple-string-reply.
     */
    Executions<String> debugHtstats(int db);

    /**
     * Get debugging information about a key.
     *
     * @param key the key.
     * @return String simple-string-reply.
     */
    Executions<String> debugObject(K key);

    /**
     * Save RDB, clear the database and reload RDB.
     *
     * @return String simple-string-reply The commands returns OK on success.
     */
    Executions<String> debugReload();

    /**
     * Restart the server gracefully.
     *
     * @param delay optional delay in milliseconds.
     * @return String simple-string-reply.
     */
    Executions<String> debugRestart(Long delay);

    /**
     * Get debugging information about the internal SDS length.
     *
     * @param key the key.
     * @return String simple-string-reply.
     */
    Executions<String> debugSdslen(K key);

    /**
     * Remove all keys from all databases.
     *
     * @return String simple-string-reply.
     */
    Executions<String> flushall();

    /**
     * Remove all keys from all databases using the specified {@link FlushMode}.
     *
     * @param flushMode the flush mode (sync/async).
     * @return String simple-string-reply.
     * @since 6.1
     */
    Executions<String> flushall(FlushMode flushMode);

    /**
     * Remove all keys asynchronously from all databases.
     *
     * @return String simple-string-reply.
     * @deprecated since 6.1, use {@link #flushall(FlushMode)} instead.
     */
    @Deprecated
    Executions<String> flushallAsync();

    /**
     * Remove all keys from the current database.
     *
     * @return String simple-string-reply.
     */
    Executions<String> flushdb();

    /**
     * Remove all keys from the current database using the specified {@link FlushMode}.
     *
     * @param flushMode the flush mode (sync/async).
     * @return String simple-string-reply.
     * @since 6.1
     */
    Executions<String> flushdb(FlushMode flushMode);

    /**
     * Remove all keys asynchronously from the current database.
     *
     * @return String simple-string-reply.
     * @deprecated since 6.1, use {@link #flushdb(FlushMode)} instead.
     */
    @Deprecated
    Executions<String> flushdbAsync();

    /**
     * Get information and statistics about the server.
     *
     * @return String bulk-string-reply as a collection of text lines.
     */
    Executions<String> info();

    /**
     * Get information and statistics about the server.
     *
     * @param section the section type: string.
     * @return String bulk-string-reply as a collection of text lines.
     */
    Executions<String> info(String section);

    /**
     * Get the UNIX time stamp of the last successful save to disk.
     *
     * @return Date integer-reply an UNIX time stamp.
     */
    Executions<Date> lastsave();

    /**
     * Reports the number of bytes that a key and its value require to be stored in RAM.
     *
     * @return memory usage in bytes.
     * @since 5.2
     */
    Executions<Long> memoryUsage(K key);

    /**
     * Make the server a replica of another instance.
     *
     * @param host the host type: string.
     * @param port the port type: string.
     * @return String simple-string-reply.
     * @since 6.1.7
     */
    Executions<String> replicaof(String host, int port);

    /**
     * Promote server as master.
     *
     * @return String simple-string-reply.
     * @since 6.1.7
     */
    Executions<String> replicaofNoOne();

    /**
     * Synchronously save the dataset to disk.
     *
     * @return String simple-string-reply The commands returns OK on success.
     */
    Executions<String> save();

    /**
     * Make the server a replica of another instance.
     *
     * @param host the host type: string.
     * @param port the port type: string.
     * @return String simple-string-reply.
     * @deprecated since 6.1.7, use {@link #replicaof(String, int)} instead.
     */
    @Deprecated
    Executions<String> slaveof(String host, int port);

    /**
     * Promote server as master.
     *
     * @return String simple-string-reply.
     * @deprecated since 6.1.7, use {@link #replicaofNoOne()} instead.
     */
    @Deprecated
    Executions<String> slaveofNoOne();

    /**
     * Read the slow log.
     *
     * @return List&lt;Object&gt; deeply nested multi bulk replies.
     */
    Executions<List<Object>> slowlogGet();

    /**
     * Read the slow log.
     *
     * @param count the count.
     * @return List&lt;Object&gt; deeply nested multi bulk replies.
     */
    Executions<List<Object>> slowlogGet(int count);

    /**
     * Obtaining the current length of the slow log.
     *
     * @return Long length of the slow log.
     */
    Executions<Long> slowlogLen();

    /**
     * Resetting the slow log.
     *
     * @return String simple-string-reply The commands returns OK on success.
     */
    Executions<String> slowlogReset();

    /**
     * Return the current server time.
     *
     * @return List&lt;V&gt; array-reply specifically:
     *
     *         A multi bulk reply containing two elements:
     *
     *         unix time in seconds. microseconds.
     */
    Executions<List<V>> time();

}
