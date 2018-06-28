package de.seriea.nx3.prototype.eventconsumer;

import org.elasticsearch.client.Client;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.client.TransportClientFactoryBean;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@EnableElasticsearchRepositories(basePackages = "de.seriea.nx3.prototype.eventconsumer")
@EnableBinding(value = {ProcessEngineEventChannels.class})
@SpringBootApplication
public class EventConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventConsumerApplication.class, args);
	}

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
