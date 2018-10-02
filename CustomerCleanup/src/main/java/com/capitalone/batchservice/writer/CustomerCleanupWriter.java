package com.capitalone.batchservice.writer;

import com.capitalone.batchservice.model.CustPhoneDetails;
import com.capitalone.batchservice.model.Customer;
import com.capitalone.batchservice.repository.CustPhoneDetailsRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * Created by Arpit Khatri on 9/7/2018.
 */
public class CustomerCleanupWriter implements ItemWriter<Customer> {

    @Autowired
    CustPhoneDetailsRepository custPhoneDetailsRepository;

    @Override
    public void write(List<? extends Customer> items) throws Exception {
    }
}
