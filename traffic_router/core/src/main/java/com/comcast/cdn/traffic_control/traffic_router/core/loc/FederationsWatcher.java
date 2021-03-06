/*
 * Copyright 2015 Comcast Cable Communications Management, LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.comcast.cdn.traffic_control.traffic_router.core.loc;

import com.comcast.cdn.traffic_control.traffic_router.core.util.AbstractResourceWatcher;
import org.apache.log4j.Logger;

public class FederationsWatcher extends AbstractResourceWatcher {
    private static final Logger LOGGER = Logger.getLogger(FederationsWatcher.class);
    private FederationRegistry federationRegistry;

    @Override
    public boolean useData(final String data) {
        try {
            federationRegistry.setFederations(new FederationsBuilder().fromJSON(data));
            return true;
        } catch (Exception e) {
            LOGGER.warn("Failed updating federations data from " + dataBaseURL);
        }

        return false;
    }

    public void setFederationRegistry(final FederationRegistry federationRegistry) {
        this.federationRegistry = federationRegistry;
    }

    public String getWatcherConfigPrefix() {
        return "federationmapping";
    }
}