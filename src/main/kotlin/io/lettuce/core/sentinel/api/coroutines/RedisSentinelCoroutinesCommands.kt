/*
 * Copyright 2020-2022 the original author or authors.
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

package io.lettuce.core.sentinel.api.coroutines

import io.lettuce.core.ClientListArgs
import io.lettuce.core.ExperimentalLettuceCoroutinesApi
import io.lettuce.core.KillArgs
import io.lettuce.core.output.CommandOutput
import io.lettuce.core.protocol.CommandArgs
import io.lettuce.core.protocol.ProtocolKeyword
import kotlinx.coroutines.flow.Flow
import java.net.SocketAddress

/**
 * Coroutine executed commands for Redis Sentinel.
 *
 * @param <K> Key type.
 * @param <V> Value type.
 * @author Mikhael Sokolov
 * @since 6.0
 * @generated by io.lettuce.apigenerator.CreateKotlinCoroutinesApi
 */
@ExperimentalLettuceCoroutinesApi
interface RedisSentinelCoroutinesCommands<K : Any, V : Any> {

    /**
     * Return the ip and port number of the master with that name.
     *
     * @param key the key.
     * @return SocketAddress.
     */
    suspend fun getMasterAddrByName(key: K): SocketAddress

    /**
     * Enumerates all the monitored masters and their states.
     *
     * @return Map<K, V>>.
     */
    suspend fun masters(): List<Map<K, V>>

    /**
     * Show the state and info of the specified master.
     *
     * @param key the key.
     * @return Map<K, V>.
     */
    suspend fun master(key: K): Map<K, V>

    /**
     * Provides a list of replicas for the master with the specified name.
     *
     * @param key the key.
     * @return List<Map<K, V>>.
     * @deprecated since 6.2, use #replicas(Any) instead.
     */
    @Deprecated("Use [replicas(key)] instead.", ReplaceWith("replicas(key)"))
    suspend fun slaves(key: K): List<Map<K, V>>

    /**
     * This command will reset all the masters with matching name.
     *
     * @param key the key.
     * @return Long.
     */
    suspend fun reset(key: K): Long

    /**
     * Provides a list of replicas for the master with the specified name.
     *
     * @param key the key.
     * @return List<Map<K, V>>.
     * @since 6.2
     */
    suspend fun replicas(key: K): List<Map<K, V>>

    /**
     * Perform a failover.
     *
     * @param key the master id.
     * @return String.
     */
    suspend fun failover(key: K): String

    /**
     * This command tells the Sentinel to start monitoring a new master with the specified name, ip, port, and quorum.
     *
     * @param key the key.
     * @param ip the IP address.
     * @param port the port.
     * @param quorum the quorum count.
     * @return String.
     */
    suspend fun monitor(key: K, ip: String, port: Int, quorum: Int): String

    /**
     * Multiple option / value pairs can be specified (or none at all).
     *
     * @param key the key.
     * @param option the option.
     * @param value the value.
     * @return String simple-string-reply `OK` if `SET` was executed correctly.
     */
    suspend fun set(key: K, option: String, value: V): String?

    /**
     * remove the specified master.
     *
     * @param key the key.
     * @return String.
     */
    suspend fun remove(key: K): String

    /**
     * Get the current connection name.
     *
     * @return K bulk-string-reply The connection name, or a null bulk reply if no name is set.
     */
    suspend fun clientGetname(): K?

    /**
     * Set the current connection name.
     *
     * @param name the client name.
     * @return simple-string-reply `OK` if the connection name was successfully set.
     */
    suspend fun clientSetname(name: K): String?

    /**
     * Assign various info attributes to the current connection.
     *
     * @param key the key.
     * @param value the value.
     * @return simple-string-reply `OK` if the connection name was successfully set.
     * @since 6.3
     */
    suspend fun clientSetinfo(key: String, value: String): String?

    /**
     * Kill the connection of a client identified by ip:port.
     *
     * @param addr ip:port.
     * @return String simple-string-reply `OK` if the connection exists and has been closed.
     */
    suspend fun clientKill(addr: String): String?

    /**
     * Kill connections of clients which are filtered by `killArgs`.
     *
     * @param killArgs args for the kill operation.
     * @return Long integer-reply number of killed connections.
     */
    suspend fun clientKill(killArgs: KillArgs): Long?

    /**
     * Stop processing commands from clients for some time.
     *
     * @param timeout the timeout value in milliseconds.
     * @return String simple-string-reply The command returns OK or an error if the timeout is invalid.
     */
    suspend fun clientPause(timeout: Long): String?

    /**
     * Get the list of client connections.
     *
     * @return String bulk-string-reply a unique string, formatted as follows: One client connection per line (separated by LF),
     *         each line is composed of a succession of property=value fields separated by a space character.
     */
    suspend fun clientList(): String?

    /**
     * Get the list of client connections which are filtered by `clientListArgs`.
     *
     * @return String bulk-string-reply a unique string, formatted as follows: One client connection per line (separated by LF),
     *         each line is composed of a succession of property=value fields separated by a space character.
     */
    suspend fun clientList(clientListArgs: ClientListArgs): String?

    /**
     * Get the list of the current client connection.
     *
     * @return String bulk-string-reply a unique string, formatted as a succession of property=value fields separated by a space character.
     * @since 6.3
     */
    suspend fun clientInfo(): String?

    /**
     * Get information and statistics about the server.
     *
     * @return String bulk-string-reply as a collection of text lines.
     */
    suspend fun info(): String?

    /**
     * Get information and statistics about the server.
     *
     * @param section the section type: string.
     * @return String bulk-string-reply as a collection of text lines.
     */
    suspend fun info(section: String): String?

    /**
     * Ping the server.
     *
     * @return String simple-string-reply.
     */
    suspend fun ping(): String

    /**
     * Dispatch a command to the Redis Server. Please note the command output type must fit to the command response.
     *
     * @param type the command, must not be `null`.
     * @param output the command output, must not be `null`.
     * @param <T> response type.
     * @return the command response.
     * @since 6.0.2
     */
    fun <T : Any> dispatch(type: ProtocolKeyword, output: CommandOutput<K, V, T>): Flow<T>

    /**
     * Dispatch a command to the Redis Server. Please note the command output type must fit to the command response.
     *
     * @param type the command, must not be `null`.
     * @param output the command output, must not be `null`.
     * @param args the command arguments, must not be `null`.
     * @param <T> response type.
     * @return the command response.
     * @since 6.0.2
     */
    fun <T : Any> dispatch(type: ProtocolKeyword, output: CommandOutput<K, V, T>, args: CommandArgs<K, V>): Flow<T>

    /**
     *
     * @return @code true} if the connection is open (connected and not closed).
     */
    fun isOpen(): Boolean

}

