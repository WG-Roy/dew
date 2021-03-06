/*
 * Copyright 2020. the original author or authors.
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

package group.idealworld.dew.example.tracing;

import group.idealworld.dew.Dew;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Tracing 1 example controller.
 *
 * @author gudaoxuri
 */
@RestController
@RequestMapping("/")
public class Tracing1ExampleController {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Ping.
     *
     * @param code the code
     * @return the result
     */
    @GetMapping("ping")
    public String ping(@RequestParam("code") String code) {
        Dew.cluster.mq.publish("test", code);
        return restTemplate.getForObject("http://tracing-invoke2-example/ping?code=" + code, String.class);
    }

}
