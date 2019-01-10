package de.mhens.events.eventconsumer.elastic;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.RestClientFactoryBean;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@EnableElasticsearchRepositories(basePackages = "de.mhens.events.eventconsumer")
@Configuration
public class ElasticSearchIntegrationConfiguration {
	 
    @Bean
    public RestHighLevelClient client() throws Exception {
        return factory().getObject();
    }
	
	@Bean
	public ElasticsearchRestTemplate elasticsearchTemplate() throws Exception {
		return new ElasticsearchRestTemplate(client());
	}
	
	@Bean
	public RestClientFactoryBean factory() {
		RestClientFactoryBean factory = new RestClientFactoryBean();
		return factory;
	}
}
