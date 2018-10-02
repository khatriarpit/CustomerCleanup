package com.capitalone.batchservice.reader;

import com.capitalone.batchservice.model.CustPhoneDetails;
import com.capitalone.batchservice.model.Customer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;

import java.util.List;

/**
 * Created by Arpit Khatri on 9/7/2018.
 */
public class CustomerCleanupReader implements ItemReader<FlatFileItemReader<Customer>> {

    @Override
    public FlatFileItemReader<Customer> read() throws Exception, UnexpectedInputException, ParseException,
                                                    NonTransientResourceException {
        FlatFileItemReader<Customer> reader = new FlatFileItemReader<Customer>();
        reader.setResource(new ClassPathResource("test.csv"));
        reader.setLineMapper(new DefaultLineMapper<Customer>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] { "id"});
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper() {{
                setTargetType(Customer.class);
            }});
        }});
        return reader;
    }
}
