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
    private String firstName, lastName, hireDate, endDate, province, birthDate, email, employeeID, SIN, rateOfPay, gender;
    private boolean isSalary, isActive;
    
    Employee(){
        employeeID = "-1";
        firstName = "";
        lastName = "";
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
    public boolean isIsActive() {
        return isActive;
    }

    /**
     * @param isActive the isActive to set
     */
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
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
    
    public boolean validate(){
        int num1, num3;
        double num2;
        try{ // try to turn the id's into numbers
            num1 = Integer.parseInt(employeeID);
            num2 = Double.parseDouble(rateOfPay);
            num3 = Integer.parseInt(SIN);
        }catch(NumberFormatException e){ // id is not a number
            return false;
        }
        // if ids are a number
        return num1 >= 0 && num2 >= 0 && (!lastName.equals("") && !firstName.equals("")); // String !empty
    }
    
}
