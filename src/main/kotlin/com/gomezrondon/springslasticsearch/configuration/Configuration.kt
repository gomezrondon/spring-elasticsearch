package com.gomezrondon.springslasticsearch.configuration

//import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.client.Client
import org.elasticsearch.common.settings.Settings
import org.elasticsearch.common.transport.TransportAddress
import org.elasticsearch.transport.client.PreBuiltTransportClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.elasticsearch.core.ElasticsearchOperations
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories
import java.net.InetAddress


@Configuration
class Configuration {

/*    @Bean
    fun  client(): Client {
        return PreBuiltTransportClient(Settings.EMPTY)
                
                .addTransportAddress(TransportAddress(InetAddress.getByName(""), 9300));
    }

    @Bean
    fun elasticsearchTemplate(): ElasticsearchOperations {
        return ElasticsearchTemplate(client())
    }*/
}