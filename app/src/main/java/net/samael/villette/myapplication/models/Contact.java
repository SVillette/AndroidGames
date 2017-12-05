package net.samael.villette.myapplication.models;

/**
 * Created by Samaël Villette on 04/12/2017.
 */

public class Contact
{
    private String name;
    private String address;
    private String phone;

    public Contact(String name, String address, String phone)
    {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public Contact(String name, String phone)
    {
        this.name = name;
        this.phone = phone;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }
}
