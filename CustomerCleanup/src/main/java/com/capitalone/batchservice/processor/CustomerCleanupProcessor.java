package com.capitalone.batchservice.processor;

import com.capitalone.batchservice.model.CustPhoneDetails;
import com.capitalone.batchservice.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arpit Khatri on 9/7/2018.
 */
public class CustomerCleanupProcessor implements ItemProcessor<Customer, Customer> {

    private static final Logger       log           = LoggerFactory.getLogger(CustomerCleanupProcessor.class);
    private              List<CustPhoneDetails> previousItems = new ArrayList<>();

    @Override
    public Customer process(final Customer custPhoneDetails) throws Exception {

//        return new CustPhoneDetails(firstName, lastName, custPhoneDetails.getEmail(), custPhoneDetails.getAge());
        return null;
    }
}