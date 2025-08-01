public class UserIdsGenerator {
    private static UserIdsGenerator instance;
    private  int idCounter = 0;
    
    private UserIdsGenerator() {}
    
    public static UserIdsGenerator getInstance() {
        if (instance == null)
            instance = new UserIdsGenerator();
        return instance;
    }
    
    public int generateId() {
        return ++idCounter;
    }
}