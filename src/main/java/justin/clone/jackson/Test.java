package justin.clone.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.IOException;

import static org.junit.Assert.assertNotSame;

public class Test {

    @org.junit.Test
    public void jacksonCopy() throws IOException {

        Address address = new Address("杭州", "中国");
        User user = new User("大山", address);



        // 使用Jackson序列化进行深拷贝
        ObjectMapper objectMapper = new ObjectMapper();
        User copyUser = objectMapper.readValue(objectMapper.writeValueAsString(user), User.class);

        // 修改源对象的值
        user.getAddress().setCity("深圳");

        // 检查两个对象的值不同
        assertNotSame(user.getAddress().getCity(), copyUser.getAddress().getCity());

    }
}
