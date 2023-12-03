package com.spring.boot.redis.example.controller;

import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.redis.example.model.CacheKey;
import com.spring.boot.redis.example.model.UserDto;
import com.spring.boot.redis.example.service.RedisService;
import com.spring.boot.redis.example.service.UserService;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户控制器
 *
 * @author guang.yi
 * @since 2023/11/27
 */
@Slf4j
@RestController("userController")
public class UserController {

    private final UserService userService;

    private final RedisService redisService;

    public UserController(
            UserService userService,
            RedisService redisService
    ) {
        this.userService = userService;
        this.redisService = redisService;
    }

    @GetMapping("/user/{userId}")
    public UserDto index(@PathVariable("userId") Long userId) {
        UserDto userDto = userService.getById(userId);
        log.info("userDto={}", userDto);
        return userDto;
    }

    /**
     * 清理Redis缓存的键模式
     * <pre>
     * cleanCache end, totalTimeMillis=24
     * </pre>
     *
     * @param cacheKey 缓存的键
     * @return 操作结果
     */
    @PostMapping("/cache/cleanCache")
    @ResponseBody
    public String cleanCache(@RequestBody CacheKey cacheKey) {
        log.info("cleanCache start");
        StopWatch stopWatch = new StopWatch("cleanCache");
        stopWatch.start();

        redisService.clean(cacheKey.getCacheName(), cacheKey.getKeyPattern());

        stopWatch.stop();
        log.info("cleanCache end, totalTimeMillis={}", stopWatch.getTotalTimeMillis());
        return "{\"code\":0,\"message\":\"ok\"}";
    }

}