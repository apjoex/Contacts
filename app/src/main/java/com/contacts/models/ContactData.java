package com.contacts.models;

import java.io.Serializable;

/**
 * Created by apjoe on 10/3/2017.
 */

public class ContactData implements Serializable {

    private String name, id, companyName;
    private boolean isFavourite;
    private String smallImageUrl, largeImageUrl, emailAddress, birthdate, work, home, mobile, street, city, state, country, zipcode;


    public ContactData(String name, String id, String companyName, boolean isFavourite, String smallImageUrl, String largeImageUrl, String emailAddress, String birthdate, String work, String home, String mobile, String street, String city, String state, String country, String zipcode) {
        this.name = name;
        this.id = id;
        this.companyName = companyName;
        this.isFavourite = isFavourite;
        this.smallImageUrl = smallImageUrl;
        this.largeImageUrl = largeImageUrl;
        this.emailAddress = emailAddress;
        this.birthdate = birthdate;
        this.work = work;
        this.home = home;
        this.mobile = mobile;
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipcode = zipcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    public String getSmallImageUrl() {
        return smallImageUrl;
    }

    public void setSmallImageUrl(String smallImageUrl) {
        this.smallImageUrl = smallImageUrl;
    }

    public String getLargeImageUrl() {
        return largeImageUrl;
    }

    public void setLargeImageUrl(String largeImageUrl) {
        this.largeImageUrl = largeImageUrl;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (isFavourite != that.isFavourite) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (companyName != null ? !companyName.equals(that.companyName) : that.companyName != null)
            return false;
        if (smallImageUrl != null ? !smallImageUrl.equals(that.smallImageUrl) : that.smallImageUrl != null)
            return false;
        if (largeImageUrl != null ? !largeImageUrl.equals(that.largeImageUrl) : that.largeImageUrl != null)
            return false;
        if (emailAddress != null ? !emailAddress.equals(that.emailAddress) : that.emailAddress != null)
            return false;
        if (birthdate != null ? !birthdate.equals(that.birthdate) : that.birthdate != null)
            return false;
        if (work != null ? !work.equals(that.work) : that.work != null) return false;
        if (home != null ? !home.equals(that.home) : that.home != null) return false;
        if (mobile != null ? !mobile.equals(that.mobile) : that.mobile != null) return false;
        if (street != null ? !street.equals(that.street) : that.street != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        return zipcode != null ? zipcode.equals(that.zipcode) : that.zipcode == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
        result = 31 * result + (isFavourite ? 1 : 0);
        result = 31 * result + (smallImageUrl != null ? smallImageUrl.hashCode() : 0);
        result = 31 * result + (largeImageUrl != null ? largeImageUrl.hashCode() : 0);
        result = 31 * result + (emailAddress != null ? emailAddress.hashCode() : 0);
        result = 31 * result + (birthdate != null ? birthdate.hashCode() : 0);
        result = 31 * result + (work != null ? work.hashCode() : 0);
        result = 31 * result + (home != null ? home.hashCode() : 0);
        result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (zipcode != null ? zipcode.hashCode() : 0);
        return result;
    }
}
