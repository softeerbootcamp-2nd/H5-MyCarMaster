package softeer.be_my_car_master.global.config;

import java.time.Duration;

import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@EnableCaching
@Configuration
public class CacheConfig {

	@Bean
	public RedisCacheConfiguration redisCacheConfiguration() {
		return RedisCacheConfiguration.defaultCacheConfig()
			.entryTtl(Duration.ofSeconds(60))
			.disableCachingNullValues()
			.serializeKeysWith(
				RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer())
			)
			.serializeValuesWith(
				RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer())
			);
	}

	@Bean
	public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
		return (builder) -> builder
			.withCacheConfiguration("redis",
				RedisCacheConfiguration.defaultCacheConfig()
					.computePrefixWith(cacheName -> "prefix::" + cacheName + "::")
					.entryTtl(Duration.ofSeconds(10))
					.disableCachingNullValues()
					.serializeKeysWith(
						RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer())
					)
					.serializeValuesWith(
						RedisSerializationContext.SerializationPair.fromSerializer(
							new GenericJackson2JsonRedisSerializer()
						)
					));
	}
}
