/*
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
 */
package org.apache.stratos.kubernetes.client.rest;

/**
 * Holds the data extracted from a HttpResponse.
 */
public class HttpResponse {

    private int statusCode;
    private String content;
    private String reason;
    private KubernetesResponse kubernetesResponse;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public KubernetesResponse getKubernetesResponse() {
        return kubernetesResponse;
    }

    public void setKubernetesResponse(KubernetesResponse kubernetesResponse) {
        this.kubernetesResponse = kubernetesResponse;
    }

    @Override
    public String toString() {
        return "HttpResponse [statusCode=" + statusCode + ", content=" + content
                + ", reason=" + reason + ", kubernetesResponse=" + kubernetesResponse + "]";
    }
}
