package com.capitalone.batchservice.repository;

import com.capitalone.batchservice.model.CustPhoneDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;


/**
 * Created by Arpit Khatri on 9/7/2018.
 */
@Transactional
@Repository
public interface CustPhoneDetailsRepository extends JpaRepository<CustPhoneDetails, Long> {
}
