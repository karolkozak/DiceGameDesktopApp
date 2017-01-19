package com.dicegame.configuration;

import com.google.gson.Gson;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import javax.jms.*;

/**
 * Created by Karol on 11/01/2017.
 */
@Configuration
@EnableAutoConfiguration
@EnableJms
public class ConfigJMS {

    private static final String OUTER_BROKER_URL = "tcp://localhost:61616";
    //private static final String OUTER_BROKER_URL = "tcp://192.168.43.111:61616";
    private static final String BROKER_URL = "vm://localhost?broker.persistent=false";  // persistent - message won't be saved to drive
    private static final String username = "admin";
    private static final String password = "admin";

    @Bean
    public ConnectionFactory connectionFactory(){
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(BROKER_URL);   // change to OUTER_BROKER_URL when actimeMQ run separately
        connectionFactory.setUserName(username);
        connectionFactory.setPassword(password);
        connectionFactory.setTrustAllPackages(true);
        return connectionFactory;
    }


    @Bean
    public JmsTemplate jmsTemplate(){
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory());
        template.setMessageConverter(gsonConverter());
        template.setDefaultDestinationName("default");
        return template;
    }

    @Bean
    public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
                                                    DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        // This provides all boot's default to this factory, including the message converter
        configurer.configure(factory, connectionFactory);
        // You could still override some of Boot's default if necessary.
        return factory;
    }


    @Bean
    MessageConverter gsonConverter(){
//        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
//        converter.setTargetType(MessageType.TEXT);
//        converter.setTypeIdPropertyName("_type");
        //return converter;

        MessageConverter gsonConverter = new MessageConverter(){

            @Autowired
            private Gson gson;

            @Override
            public Message toMessage(Object o, Session session) throws JMSException, MessageConversionException {
                String json = gson.toJson(o);
                return session.createTextMessage(json);
            }

            @Override
            public Object fromMessage(Message message) throws JMSException, MessageConversionException {
                // not invoked dont know why
                if(message instanceof TextMessage){
                    TextMessage textMessage = (TextMessage) message;
                    return gson.fromJson(textMessage.getText(), Object.class);
                }else{
                    throw new MessageConversionException("Cannot convert from not TextMessage.");
                }
            }

            @Override
            public String toString() {
                return super.toString().concat("MyGSON");
            }
        };

        return gsonConverter;
    }


}
