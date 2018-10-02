package com.capitalone.batchservice.config;

import com.capitalone.batchservice.listener.JobCompletionNotificationListener;
import com.capitalone.batchservice.model.CustPhoneDetails;
import com.capitalone.batchservice.model.Customer;
import com.capitalone.batchservice.processor.CustomerCleanupProcessor;
import com.capitalone.batchservice.reader.CustomerCleanupReader;
import com.capitalone.batchservice.writer.CustomerCleanupWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.explore.support.MapJobExplorerFactoryBean;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Created by Arpit Khatri on 9/7/2018.
 */
@Configuration
@EnableBatchProcessing
public class CustomerCleanupConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public ItemReader<Customer> reader() throws Exception {
        return new CustomerCleanupReader().read();
    }

    @Bean
    public CustomerCleanupProcessor processor() {
        return new CustomerCleanupProcessor();
    }

    @Bean
    public ItemWriter<Customer> writer() {
        return new CustomerCleanupWriter();
    }

    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener) throws Exception {
        return jobBuilderFactory.get("importUserJob")
                                .incrementer(new RunIdIncrementer())
                                .listener(listener)
                                .flow(step1())
                                .end()
                                .build();
    }

    @Bean
    public Step step1() throws Exception {
        return stepBuilderFactory.get("step1")
                .<Customer, Customer>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager();
    }

    @Bean
    public JobExplorer jobExplorer() throws Exception {
        MapJobExplorerFactoryBean jobExplorerFactory = new MapJobExplorerFactoryBean(mapJobRepositoryFactoryBean());
        jobExplorerFactory.afterPropertiesSet();
        return jobExplorerFactory.getObject();
    }

    @Bean
    public MapJobRepositoryFactoryBean mapJobRepositoryFactoryBean() {
        MapJobRepositoryFactoryBean mapJobRepositoryFactoryBean = new MapJobRepositoryFactoryBean();
        mapJobRepositoryFactoryBean.setTransactionManager(transactionManager());
        return mapJobRepositoryFactoryBean;
    }

    @Bean
    public JobRepository jobRepository() throws Exception {
        return mapJobRepositoryFactoryBean().getObject();
    }

    @Bean
    public JobLauncher jobLauncher() throws Exception {
        SimpleJobLauncher simpleJobLauncher = new SimpleJobLauncher();
        simpleJobLauncher.setJobRepository(jobRepository());
        return simpleJobLauncher;
    }
}