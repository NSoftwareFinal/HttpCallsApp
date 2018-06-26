package com.example.nsoftware.httpcallsapp.Model;

public class User {

    private Company company;
    private String website;
    private String phone;
    private Address address;
    private String email;
    private String username;
    private String name;
    private int id;

    public Company getCompany() {
        return company;
    }

    public String getWebsite() {
        return website;
    }

    public String getPhone() {
        return phone;
    }

    public Address getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public static class Company {
        private String bs;
        private String catchphrase;
        private String name;

        public String getBs() {
            return bs;
        }

        public String getCatchphrase() {
            return catchphrase;
        }

        public String getName() {
            return name;
        }
    }

    public static class Address {
        private Geo geo;
        private String zipcode;
        private String city;
        private String suite;
        private String street;

        public Geo getGeo() {
            return geo;
        }

        public String getZipcode() {
            return zipcode;
        }

        public String getCity() {
            return city;
        }

        public String getSuite() {
            return suite;
        }

        public String getStreet() {
            return street;
        }
    }

    public static class Geo {
        private String lng;
        private String lat;

        public String getLng() {
            return lng;
        }

        public String getLat() {
            return lat;
        }
    }
}
