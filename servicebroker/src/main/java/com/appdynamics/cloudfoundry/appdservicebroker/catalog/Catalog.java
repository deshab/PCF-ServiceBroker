/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.appdynamics.cloudfoundry.appdservicebroker.catalog;

import java.util.ArrayList;
import java.util.List;

final class Catalog {

    private volatile List<Service> services;

    private final Object monitor = new Object();

    List<Service> getServices() {
        synchronized (this.monitor) {
            return this.services;
        }
    }

    Service service() {
        synchronized (this.monitor) {
            if (this.services == null) {
                this.services = new ArrayList<>();
            }

            Service service = new Service(this);
            this.services.add(service);
            return service;
        }
    }

}
