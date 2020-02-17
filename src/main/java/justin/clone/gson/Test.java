package justin.clone.gson;

import com.google.gson.Gson;

import static org.junit.Assert.assertNotSame;

public class Test {

    @org.junit.Test
    public void gsonCopy() {

        Address address = new Address("杭州", "中国");
        User user = new User("大山", address);


        // 使用Gson序列化进行深拷贝
        Gson gson = new Gson();
        User copyUser = gson.fromJson(gson.toJson(user), User.class);

        // 修改源对象的值
        user.getAddress().setCity("深圳");

        // 检查两个对象的值不同
        assertNotSame(user.getAddress().getCity(), copyUser.getAddress().getCity());

    }
}
