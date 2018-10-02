package com.capitalone.batchservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Arpit Khatri on 9/7/2018.
 */
@Data
@AllArgsConstructor
@Entity
@Table(name = "person")
public class SMSEnrollment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "person_id")
    private int personID;


}
