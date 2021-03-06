/*
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 */
package org.apache.stratos.kubernetes.client.model;

import org.apache.commons.lang3.ArrayUtils;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;

@XmlRootElement
public class ReplicationControllerList {

    private String kind;
    private String apiVersion;
    private ReplicationController[] items;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public ReplicationController[] getItems() {
        return items;
    }

    public void setItems(ReplicationController[] items) {
        this.items = ArrayUtils.clone(items);
    }

    @Override
    public String toString() {
        return "ReplicationControllerList [kind=" + kind + ", apiVersion="
                + apiVersion + ", items=" + Arrays.toString(items) + "]";
    }

}
