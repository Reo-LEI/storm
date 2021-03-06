/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.storm.scheduler.resource.strategies.scheduling;

import org.apache.storm.scheduler.resource.NormalizedResources;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class NormalizedResourcesRule implements TestRule {

    public static class NRStatement extends Statement {
        private final Statement base;

        public NRStatement(Statement base) {
            this.base = base;
        }

        @Override
        public void evaluate() throws Throwable {
            NormalizedResources.resetResourceNames();
            try {
                base.evaluate();
            } finally {
                NormalizedResources.resetResourceNames();
            }
        }
    }

    @Override
    public Statement apply(Statement statement, Description description) {
        return new NRStatement(statement);
    }
}
