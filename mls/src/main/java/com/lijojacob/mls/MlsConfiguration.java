package com.lijojacob.mls;

import javax.jms.ConnectionFactory;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.server.support.HttpSolrServerFactoryBean;
import org.springframework.jms.listener.SimpleMessageListenerContainer;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;

import com.lijojacob.mls.productcatalog.index.AdvancedFacetQuery;
import com.lijojacob.mls.productcatalog.index.AdvancedQueryParser;
import com.lijojacob.mls.productcatalog.index.IndexMessageReceiver;

@Configuration
public class MlsConfiguration {
	
	@Bean
	public SolrServer solrServer(@Value("${spring.data.solr.host}") String solrHost) {
		System.err.println("Inside MlsConfiguration.solrServer");
		return new HttpSolrServer(solrHost);
	}
	
	@Bean
    public SimpleMessageListenerContainer container(MessageListenerAdapter messageListener,
                                             ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setMessageListener(messageListener);
        container.setConnectionFactory(connectionFactory);
        container.setDestinationName("springtest");
        return container;
    }
	
	@Bean
	public IndexMessageReceiver indexMessageReceiver() {
        return new IndexMessageReceiver();
    }
	
	@Bean
    public MessageListenerAdapter adapter(IndexMessageReceiver receiver) {
        MessageListenerAdapter messageListener
                = new MessageListenerAdapter(receiver);
        messageListener.setDefaultListenerMethod("receiveMessage");
        return messageListener;
    }
	
	@Bean
    public HttpSolrServerFactoryBean solrServerFactoryBean() {
        HttpSolrServerFactoryBean factory = new HttpSolrServerFactoryBean();
        factory.setUrl("http://localhost:8983/solr");
        return factory;
    }
	
	@Bean
    @Autowired
    public SolrTemplate solrTemplate(SolrServer solrServer) throws Exception {
        SolrTemplate solrTemplate = new SolrTemplate(solrServer, "products");
        solrTemplate.registerQueryParser(AdvancedFacetQuery.class, new AdvancedQueryParser());
        return solrTemplate;
    }

}
