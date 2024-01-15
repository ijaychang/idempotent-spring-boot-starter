package com.zcckj.plugin.idempotent;

import com.zcckj.plugin.idempotent.aspect.IdempotentAspect;
import com.zcckj.plugin.idempotent.expression.ExpressionResolver;
import com.zcckj.plugin.idempotent.expression.KeyResolver;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lengleng
 * @date 2020/9/25
 * <p>
 * 幂等插件初始化
 */
@AutoConfiguration
@Configuration(proxyBeanMethods = false)
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class IdempotentAutoConfiguration {

	/**
	 * 切面 拦截处理所有 @Idempotent
	 * @return Aspect
	 */
	@Bean
	public IdempotentAspect idempotentAspect() {
		return new IdempotentAspect();
	}

	/**
	 * key 解析器
	 * @return KeyResolver
	 */
	@Bean
	@ConditionalOnMissingBean(KeyResolver.class)
	public KeyResolver keyResolver() {
		return new ExpressionResolver();
	}

}
