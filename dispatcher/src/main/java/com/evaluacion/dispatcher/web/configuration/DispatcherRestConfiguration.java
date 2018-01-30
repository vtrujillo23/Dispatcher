package com.evaluacion.dispatcher.web.configuration;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.evaluacion.dispatcher.core.conf.DispacherCoreConfiguration;

@Configuration
@ComponentScan({"com.evaluacion.dispatcher.controller"})
@Import({DispacherCoreConfiguration.class})
public class DispatcherRestConfiguration extends WebMvcConfigurerAdapter {
}
