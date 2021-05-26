package com.copious.training.model.Request;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

public class UserSignupRequestModel implements Serializable {

    @ApiModelProperty(notes = "valid ID")
    @NotNull(message = "Id cant be empty")
    @Size(min = 5, max = 5, message = "Id should have 5 characters")
    private String id;

    @ApiModelProperty(notes = "First Name")
    @Size(min = 2, message = "firstname should have atleast 2 characters")
    @NotEmpty(message = "First name should not be empty")
    private String fName;

    @ApiModelProperty(notes = "Last Name")
    @Size(min = 2, message = "lastname should have atleast 2 characters")
    @NotEmpty(message = "Last name should not be empty")
    private String lName;

    @ApiModelProperty(notes = "Valid Mobile number")
    @Pattern(regexp="[\\d]{10}", message="Invalid Mobile Number, it should be of ten digits")
    private String mobileNumber;

    @ApiModelProperty(notes = "Address can include both integer and strings")
    private String address;

    public UserSignupRequestModel(String id, String fName, String lName, String mobileNumber, String address) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.mobileNumber = mobileNumber;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSignupRequestModel that = (UserSignupRequestModel) o;
        return Objects.equals(id, that.id) && Objects.equals(fName, that.fName) && Objects.equals(lName, that.lName) && Objects.equals(mobileNumber, that.mobileNumber) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fName, lName, mobileNumber, address);
    }

    @Override
    public String toString() {
        return "UserSignupRequestModel{" +
                "id=" + id +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

