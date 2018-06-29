package de.seriea.nx3.prototype.eventconsumer;

import org.elasticsearch.client.Client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.TransportClientFactoryBean;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@EnableElasticsearchRepositories(basePackages = "de.seriea.nx3.prototype.eventconsumer")
@Configuration
public class ElasticSearchIntegrationConfiguration {
	
	@Bean
    public TransportClientFactoryBean factory() {
		TransportClientFactoryBean factory = new TransportClientFactoryBean();
        return factory;
    }
 
    @Bean
    public Client transportClient() throws Exception {
        return factory().getObject();
    }
	
	@Bean
	public ElasticsearchTemplate elasticsearchTemplate() throws Exception {
		return new ElasticsearchTemplate(transportClient());
	}
}
