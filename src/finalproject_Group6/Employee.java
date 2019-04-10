/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject_Group6;

import java.io.Serializable;

/**
 *
 * @author Graham
 */

public class Employee implements Serializable{


    // information held in Strings to advoid user input errors
    private String firstName, lastName, hireDate, endDate, province, phone, address, birthDate, email, employeeID, SIN, rateOfPay, gender, type, payMethod;
    public String errors;
    private boolean isSalary, isActive;
    
    Employee(){
        employeeID = "-1";
        firstName = "";
        lastName = "";
        errors = "";
        isActive = true; // default the crew to active when create
    }

    
    /*
        Validate the entered information
    */
    public boolean validate(){
        errors = ""; // reset the error
        int num1, num3; // create number reference
        double num2; // create number reference
        try{ // try to turn the number String into real Numbers
            num1 = Integer.parseInt(getEmployeeID()); // inputed id
            num2 = Double.parseDouble(getRateOfPay()); // inputed rate of pay
            num3 = Integer.parseInt(getSIN()); // inputed SIN
        }catch(NumberFormatException e){ // one is not a number
            errors = "please enter the * feilds correctly"; // set error
            return false; // validation failed
        }
        
        if (lastName.equals("") && firstName.equals("")){ // employee name not set
            errors = "please enter the * feilds correctly"; // set error
            return false; // validation failed
        }
        
        if (num1 < 0 && num1 < 0 && num3 < 0){ // numbers entered are below zero
            errors = "please enter the * feilds correctly"; // set error
            return false; // validation failed 
        }
        
        if (type == null){ // did not select an employee type
            type = "Crew"; // default the type to general crew =
        }
        
        if (payMethod == null){ // did not select an employee type
            payMethod = "Hourly"; // default the type to general crew =
        }
        
        if (phone.equals("")){ // phone number not set
            phone = "N/A"; // default set its text
        }
        
        return true; // validation secussful 
            
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the hireDate
     */
    public String getHireDate() {
        return hireDate;
    }

    /**
     * @param hireDate the hireDate to set
     */
    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    /**
     * @return the endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the province
     */
    public String getProvince() {
        return province;
    }

    /**
     * @param province the province to set
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the birthDate
     */
    public String getBirthDate() {
        return birthDate;
    }

    /**
     * @param birthDate the birthDate to set
     */
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the employeeID
     */
    public String getEmployeeID() {
        return employeeID;
    }

    /**
     * @param employeeID the employeeID to set
     */
    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    /**
     * @return the SIN
     */
    public String getSIN() {
        return SIN;
    }

    /**
     * @param SIN the SIN to set
     */
    public void setSIN(String SIN) {
        this.SIN = SIN;
    }

    /**
     * @return the rateOfPay
     */
    public String getRateOfPay() {
        return rateOfPay;
    }

    /**
     * @param rateOfPay the rateOfPay to set
     */
    public void setRateOfPay(String rateOfPay) {
        this.rateOfPay = rateOfPay;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the payMethod
     */
    public String getPayMethod() {
        return payMethod;
    }

    /**
     * @param payMethod the payMethod to set
     */
    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    /**
     * @return the isSalary
     */
    public boolean isIsSalary() {
        return isSalary;
    }

    /**
     * @param isSalary the isSalary to set
     */
    public void setIsSalary(boolean isSalary) {
        this.isSalary = isSalary;
    }

    /**
     * @return the isActive
     */
    public String getIsActive() {
        if (isActive){
            return "Active";
        }else{
            return "In Active";
        }
    }

    /**
     * @param isActive the isActive to set
     */
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    
}
