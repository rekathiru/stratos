/**
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at

 *  http://www.apache.org/licenses/LICENSE-2.0

 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package org.apache.stratos.cli.commands;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.stratos.cli.Command;
import org.apache.stratos.cli.RestCommandLineService;
import org.apache.stratos.cli.StratosCommandContext;
import org.apache.stratos.cli.exception.CommandException;
import org.apache.stratos.cli.utils.CliConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Describe application policy command.
 */
public class DescribeApplicationPolicyCommand implements Command<StratosCommandContext> {
    private static final Logger log = LoggerFactory.getLogger(DescribeApplicationPolicyCommand.class);

    @Override
    public String getName() {
        return "describe-application-policy";
    }

    @Override
    public String getDescription() {
        return "Describe application policy";
    }

    @Override
    public String getArgumentSyntax() {
        return "[application-policy-id]";
    }

    @Override
    public Options getOptions() {
        return null;
    }

    @Override
    public int execute(StratosCommandContext context, String[] args, Option[] alreadyParsedOpts) throws CommandException {
        if (log.isDebugEnabled()) {
            log.debug("Executing {} command...", getName());
        }
        if (args != null && args.length == 1) {
            String applicationPolicyId = args[0];
            if (log.isDebugEnabled()) {
                log.debug("Getting application policy {}", applicationPolicyId);
            }
            RestCommandLineService.getInstance().describeApplicationPolicy(applicationPolicyId);
            return CliConstants.COMMAND_SUCCESSFULL;
        } else {
            context.getStratosApplication().printUsage(getName());
            return CliConstants.COMMAND_FAILED;
        }
    }
}
