/*
 * Copyright 2017 Google, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netflix.kayenta.prometheus.canary;

import com.netflix.kayenta.canary.CanaryScope;
import com.netflix.kayenta.canary.CanaryScopeFactory;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Map;

@Component
public class PrometheusCanaryScopeFactory implements CanaryScopeFactory {

  @Override
  public boolean handles(String serviceType) {
    return "prometheus".equals(serviceType);
  }

  @Override
  public CanaryScope buildCanaryScope(String scope,
                                      Instant startTimeInstant,
                                      Instant endTimeInstant,
                                      String step,
                                      Map<String, String> extendedScopeParams) {
    PrometheusCanaryScope prometheusCanaryScope = new PrometheusCanaryScope();
    prometheusCanaryScope.setScope(scope);
    prometheusCanaryScope.setStart(startTimeInstant.toEpochMilli() + "");
    prometheusCanaryScope.setEnd(endTimeInstant.toEpochMilli() + "");
    prometheusCanaryScope.setIntervalStartTimeIso(startTimeInstant + "");
    prometheusCanaryScope.setIntervalEndTimeIso(endTimeInstant + "");
    prometheusCanaryScope.setStep(step);

    return prometheusCanaryScope;
  }
}
