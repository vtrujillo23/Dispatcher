package com.evaluacion.dispatcher.core.conf;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.evaluacion.dispatcher.persistence.configuration.DispatcherPersistenceConf;

@Configuration
@EnableScheduling
@Import({DispatcherPersistenceConf.class})
@ComponentScan({"com.evaluacion.dispatcher.core.manager.impl","com.evaluacion.dispatcher.core.schedul"})
public class DispacherCoreConfiguration {

}
