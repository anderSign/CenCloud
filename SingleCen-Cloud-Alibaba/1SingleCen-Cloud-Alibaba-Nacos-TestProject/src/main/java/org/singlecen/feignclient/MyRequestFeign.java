package org.singlecen.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("Test1")
public interface MyRequestFeign {

    @GetMapping("/{id}/{name}")
    String doCallPublic(@PathVariable int id,@PathVariable String name);

}
