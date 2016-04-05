package com.lijojacob.mls.productcatalog.index;

import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.lijojacob.mls.productcatalog.entity.Product;
import com.lijojacob.mls.productcatalog.entity.Sku;

public class IndexMessageReceiver implements MessageListener{
	
	@Autowired
	private ProductDocumentService productDocumentService;
	
	@Autowired
	private ProductDocumentFactory productDocumentFactory;
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void onMessage(Message message) {
		System.err.println("Inside ProductDocumentIndexingMessageListener.onMessage: ");
		ObjectMessage objMessage = (ObjectMessage) message;
		try {
			ModifiedItem mi = (ModifiedItem) objMessage.getObject();
			System.err.println("Messag Received: " + mi.getEntityClass() + " : " + mi.getId());
			Object entityModified = mongoTemplate.findById(mi.getId(), mi.getEntityClass());
			
			if(mi.getEntityClass() == Product.class) {
				Product productToSave = (Product) entityModified;
				List<ProductDocument> productDocuments = productDocumentFactory.createProductDocuments(productToSave);
				productDocumentService.save(productDocuments);
			} else if(mi.getEntityClass() == Sku.class) {
				Sku skuToSave = (Sku) entityModified;
				List<ProductDocument> productDocuments = productDocumentFactory.createProductDocuments(skuToSave);
				productDocumentService.save(productDocuments);
			}
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
