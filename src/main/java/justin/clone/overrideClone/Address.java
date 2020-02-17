package justin.clone.overrideClone;

import lombok.Data;

@Data
public class Address implements Cloneable {

    private String city;
    private String country;

    public Address(String city, String country) {
        this.city = city;
        this.country = country;
    }

    @Override
    public Address clone() throws CloneNotSupportedException {
        return (Address) super.clone();
    }

}
