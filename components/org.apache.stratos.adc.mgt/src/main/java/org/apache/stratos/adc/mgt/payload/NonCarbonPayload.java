/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.stratos.adc.mgt.payload;

import org.apache.stratos.adc.mgt.utils.CartridgeConstants;
import org.apache.stratos.cloud.controller.util.xsd.CartridgeInfo;

import java.text.DecimalFormat;

public class NonCarbonPayload extends Payload {

    public NonCarbonPayload(String payloadFilePath) {
        super(payloadFilePath);
    }

    public void populatePayload(PayloadArg payloadArg) {

        super.populatePayload(payloadArg);
        payloadBuilder.append(",");

        //general
        payloadBuilder.append("REPO_INFO_EPR=" + System.getProperty(CartridgeConstants.REPO_INFO_EPR));
        payloadBuilder.append(",");
        payloadBuilder.append("CARTRIDGE_AGENT_EPR=" + System.getProperty(CartridgeConstants.CARTRIDGE_AGENT_EPR));
        payloadBuilder.append(",");
        payloadBuilder.append("APP_PATH=" + payloadArg.getCartridgeInfo().getBaseDir());
        payloadBuilder.append(",");

        //port mapping specific
        payloadBuilder.append(createPortMappingPayloadString(payloadArg.getCartridgeInfo()));
        payloadBuilder.append(",");

        //git repository specific
        payloadBuilder.append("GIT_REPO=" + getRepositoryUrlParam(payloadArg));
        payloadBuilder.append(",");

        //BAM specific
        payloadBuilder.append("BAM_IP=" + System.getProperty(CartridgeConstants.BAM_IP));
        payloadBuilder.append(",");
        payloadBuilder.append("BAM_PORT=" + System.getProperty(CartridgeConstants.BAM_PORT));

        //Autoscale policy specific
        payloadBuilder.append(getAutoscalingParams(payloadArg));
    }

    private String createPortMappingPayloadString(CartridgeInfo cartridgeInfo) {
        // port mappings
        StringBuilder portMapBuilder = new StringBuilder();
        org.apache.stratos.cloud.controller.util.xsd.PortMapping[] portMappings = cartridgeInfo.getPortMappings();
        for (org.apache.stratos.cloud.controller.util.xsd.PortMapping portMapping : portMappings) {
            String port = portMapping.getPort();
            String protocol = portMapping.getProtocol();
            String proxyPort = portMapping.getProxyPort();
            portMapBuilder.append(protocol).append(":").append(port).append(":").append(proxyPort).append("|");
        }

        // remove last "|" character
        String portMappingString = portMapBuilder.toString().replaceAll("\\|$", "");
	        /*String portMappingPayloadString = null;
	        if (portMappingString.charAt(portMappingString.length() - 1) == '|') {
	            portMappingPayloadString = portMappingString.substring(0, portMappingString.length() - 1);
	        } else {
	            portMappingPayloadString = portMappingString;
	        }*/

        return "PORTS=" + portMappingString;
    }

    private String getRepositoryUrlParam (PayloadArg arg) {

        String gitRepoURL = null;
        if (arg.getRepoURL() != null) {
            gitRepoURL = arg.getRepoURL();
        } else {
            gitRepoURL = "git@" + System.getProperty(CartridgeConstants.GIT_HOST_IP) + ":" + arg.getTenantDomain()
                    + System.getProperty("file.separator") + arg.getCartridgeAlias() + ".git";
        }
        return gitRepoURL;
    }

    private String getAutoscalingParams (PayloadArg arg) {

        DecimalFormat df = new DecimalFormat("##.##");
        df.setParseBigDecimal(true);

        StringBuilder autoscalingPayloadBuilder = new StringBuilder();
        autoscalingPayloadBuilder.append(",");
        autoscalingPayloadBuilder.append("MIN=" + arg.getPolicy().getMinAppInstances());
        autoscalingPayloadBuilder.append(",");
        autoscalingPayloadBuilder.append("MAX=" + arg.getPolicy().getMaxAppInstances());
        autoscalingPayloadBuilder.append(",");
        autoscalingPayloadBuilder.append("ALARMING_LOWER_RATE=" + arg.getPolicy().getAlarmingLowerRate());
        autoscalingPayloadBuilder.append(",");
        autoscalingPayloadBuilder.append("ALARMING_UPPER_RATE=" + arg.getPolicy().getAlarmingUpperRate());
        autoscalingPayloadBuilder.append(",");
        autoscalingPayloadBuilder.append("MAX_REQUESTS_PER_SEC=" + arg.getPolicy().getMaxRequestsPerSecond());
        autoscalingPayloadBuilder.append(",");
        autoscalingPayloadBuilder.append("ROUNDS_TO_AVERAGE=" + arg.getPolicy().getRoundsToAverage());
        autoscalingPayloadBuilder.append(",");
        autoscalingPayloadBuilder.append("SCALE_DOWN_FACTOR=" + arg.getPolicy().getScaleDownFactor());

        return autoscalingPayloadBuilder.toString();
    }
}
