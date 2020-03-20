package justin.designPattern.singleton.enumSingleton_recommend;


public class EnumSingletonClass {

    public EnumSingletonClass() {
    }

    private enum Singleton{
        INSTANCE;

        private final EnumSingletonClass instance;

        Singleton(){
            instance = new EnumSingletonClass();
        }

        public EnumSingletonClass getInstance(){
            return instance;
        }
    }

    public static EnumSingletonClass getInstance(){
        return Singleton.INSTANCE.getInstance();
    }
}
