package main;
 import entities.Role;
 import java_mysql.Connection;
 import entities.User;

 import java.sql.PreparedStatement;
 import java.sql.ResultSet;
 import java.sql.SQLException;
 import java.util.ArrayList;
 import java.util.List;

public class Main {

    public static void main(String[] args){
        Connection.getConnection();
       Role role = new Role(1,"ADMIN");
        Role Urole = new Role(2,"USER");

        User user = new User("adim@gmail.com","admin",role);
        user = new User("user@gmail.com", "passer", role);

        createUser(user);
        List<User> users = getUsers();

        for(User u: users){
            System.out.println(u);
        }
    }

    private static void createUser(User user) {
        try (java.sql.Connection connection = Connection.getConnection()) {
            String insertUserQuery = "INSERT INTO user (email, passwordhasher, role_id) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertUserQuery)) {
                preparedStatement.setString(1, user.getEmail());
                preparedStatement.setString(2, user.getPasswordHashed());
                preparedStatement.setInt(3, user.getRole().getId());
                preparedStatement.execute();
            }
            System.out.println("Utilisateur ajouté avec succès !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static List<User> getUsers(){
        List<User> users = new ArrayList<>();
        try (java.sql.Connection connection = Connection.getConnection()) {
            String getUserQuery = "SELECT * FROM user";
            try (PreparedStatement preparedStatement = connection.prepareStatement(getUserQuery)) {
                ResultSet rs =  preparedStatement.executeQuery();

                while(rs.next()){
                    Role role = new Role(rs.getInt("role_id"), "ADMIN");
                    User user = new User(rs.getString("email"), rs.getString("passwordhasher"), role);
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    return  users;
    }
}
