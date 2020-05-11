package spock.demo;

import java.util.Objects;

public class Calculator {

    private CacheService cacheService;

    public Calculator(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    public boolean isLoggedInUser(String userName) {
        return Objects.equals(userName, cacheService.getUserName());
    }
}
