/*
 * Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package org.apache.stratos.load.balancer.context.map;

import org.apache.stratos.messaging.domain.topology.Cluster;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Cluster id, cluster map for accessing clusters using cluster id:
 * Map[ClusterId, Cluster]
 */
public class ClusterIdClusterMap {

    private ConcurrentHashMap<String, Cluster> concurrentHashMap;

    public ClusterIdClusterMap() {
        concurrentHashMap = new ConcurrentHashMap<String, Cluster>();
    }

    public Cluster getCluster(String clusterId) {
        return concurrentHashMap.get(clusterId);
    }

    public boolean containsCluster(String clusterId) {
        return concurrentHashMap.containsKey(clusterId);
    }

    public void addCluster(Cluster cluster) {
        concurrentHashMap.put(cluster.getClusterId(), cluster);
    }

    public void removeCluster(String clusterId) {
        concurrentHashMap.remove(clusterId);
    }

    public void clear() {
        concurrentHashMap.clear();
    }
}
