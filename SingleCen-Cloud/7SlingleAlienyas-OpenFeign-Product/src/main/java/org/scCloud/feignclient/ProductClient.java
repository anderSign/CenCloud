package org.scCloud.feignclient;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * 注意此接口虽然有写到但是并未真正使用发送请求放仍然是类别模块详见类别模块
 */
@FeignClient(value = "CategoryApplication")
public interface ProductClient {

}
