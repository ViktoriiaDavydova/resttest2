/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;

/**
 *
 * @author 703174
 */
public class Vaccine {

    private String vaccine;
    private Date expirationDate;

    public Vaccine() {
    }

    public Vaccine(String vaccine, Date expirationDate) {
        this.vaccine = vaccine;
        this.expirationDate = expirationDate;
    }

}