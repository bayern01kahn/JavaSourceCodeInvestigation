package justin.clone.jackson;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor   //Jackson与Gson相似，可以将对象序列化成JSON，明显不同的地方是拷贝的类（包括其成员变量）需要有默认的无参构造函数。
@AllArgsConstructor
public class User {

    private String name;
    private Address address;

}
