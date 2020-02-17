package justin.clone.overrideClone;

import lombok.Data;

@Data
public class User implements Cloneable {

    private String name;
    private Address address;

    public User(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public User clone() throws CloneNotSupportedException {
        User user = (User) super.clone();
        user.setAddress(this.address.clone());
        return user;
        //需要注意的是，super.clone()其实是浅拷贝，所以在重写User类的clone()方法时，address对象需要调用address.clone()重新赋值。
    }

}
