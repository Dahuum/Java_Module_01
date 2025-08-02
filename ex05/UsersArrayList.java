public class UsersArrayList implements UsersList {
    
    private User[] users = new User[10];
    private int size = 0;
    
    public void addUser(User user) {
        if (size >= users.length) {
            User[] newArr = new User[users.length + ( users.length / 2 )];
            System.arraycopy(users, 0, newArr, 0, users.length);
            users = newArr;
        }
        users[size++] = user;
    }
    
    public User getUserById(int id) throws UserNotFoundException {
        for (int i = 0; i < size; i++) {
            if (id == users[i].getId())
                return users[i];
        }
        throw new UserNotFoundException("User with ID " + id + " not found");
    }
    
    public User getUserByIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for size " + size);
        return users[index];
    }
    
    public int getNumberOfUsers() {
        return size;
    }
	
}