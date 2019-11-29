package com.example.demo.Reposetory.Dbhelper;

import com.example.demo.Model.Post;
import com.example.demo.Model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository("UserReposetoryImpl")
public class UserReposetoryImpl implements UserReposetory{

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private dbhelper dbh;


    //----------------------------------------------- USER METODER -----------------------------------------------

    public int addUser(User user) {
        log.info("Saving user");

        try {
            Connection connection = dbh.createConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into Users (first_name, last_name, password, username, user_id, admin) values(?,?,?,?,?,?)");

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getUsername());
            preparedStatement.setInt(5, user.getUserId());
            preparedStatement.setString(6, user.getAdmin());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        dbh.close();
    }
        return 0;
    }

    public int delete(int id) {
        log.info("Deleting user");

        try {

            Connection connection = dbh.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete Users where user_id = ?");
            preparedStatement.setInt(1, id);

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<User> getAllUsers (){
        log.info("Getting all users");

        List<User> userListDb = new ArrayList<>();

        try {

            Connection connection = dbh.createConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM Webblog.Users");

            while (resultSet.next()) {

                User user = new User();


                        user.setUserId(resultSet.getInt("user_id"));
                        user.setPassword(resultSet.getString("password"));
                        user.setUsername(resultSet.getString("username"));
                        user.setAdmin(resultSet.getString("user_admin"));

                        log.info("Køre add users to ARRAY METODE");
                        log.info(user.getUsername());
                        log.info("ADDEDE USER TIL METODE");
                        userListDb.add(user);

            }

        }catch(SQLException e) {
            log.info("Could not find user" + getClass());
            e.printStackTrace();
        }
        return userListDb;
    }


    public List<User> fetchAllUsers() {
        log.info("Fetching all users");

        List<User> users = new ArrayList<>();

        String sql = "SELECT * FROM Users";

        try {
            Connection connection = dbh.createConnection();
            Statement stmt = connection.createStatement();

            ResultSet resultSet = stmt.executeQuery(sql);

            while (resultSet.next()) {



                String password = resultSet.getString("password");
                String username = resultSet.getString("username");
               // String userid = resultSet.getInt("user_id");
                String admin = resultSet.getString("user_admin");

                User currentuser = new User(password, username ,admin);
                users.add(currentuser);
            }

            return users;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public User authenticateUser(String username, String password) {

        try {
            log.info("Trying to connect to authenticateUser");
            Connection connection = dbh.createConnection();
            log.info("connected to Database");
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM Webblog.Users WHERE username = ? AND password = ?");
            log.info("Finished pstmt");

            pstmt.setString(1, username);
            pstmt.setString(2, password);
            log.info("username: " + username);
            log.info("password: " + password);

            ResultSet resultSet = pstmt.executeQuery();

            log.info("resultset finished");

            if (resultSet.next()) {
                User user = new User();

                user.setUserId(resultSet.getInt(1));
                user.setUsername(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setAdmin(resultSet.getString(4));

                log.info("authorization metode finished");
                return user;
            } else {
                log.info("Login failed in: " + this.getClass());
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public User findById(int id) {
        log.info("Køre user FindById metode");

        getAllUsers();

        for (User users : getAllUsers()) {
            log.info("går ind i forloopet");

            if (id == users.getUserId()) {

                log.info("Finished user FindById metode");
                log.info("Du har fat i følgende User");
                log.info(users.getUsername());
                log.info(String.valueOf(users.getUserId()));
                return users;
            }
        }
        log.info("------- fandt ingen users -----");

        return null;
    }

    public Optional<User> findUserById(int userId) {
        log.info("Find student by id");

        try {
            Connection connection = dbh.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from Webblog.Users where user_id = ?");
            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return Optional.of(new User(
                        resultSet.getString("password"),
                        resultSet.getString("username"),
                        resultSet.getInt("user_id"),
                        resultSet.getString("user_admin")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    //----------------------------------------------- POST metoder -----------------------------------------------

    public List<Post> getAllPost (){
        log.info("Featching all Posts");

        List<Post> postListDb = new ArrayList<>();

        try {
            log.info("GetAllPosts: trying to connect to db");
            Connection connection = dbh.createConnection();
            Statement stmt = connection.createStatement();
            log.info("Trying to run SQL stmt");
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM Webblog.Posts");

            while (resultSet.next()) {
                log.info("running while loop posts");
                Post post = new Post();

                post.setPostId(resultSet.getInt("post_id"));
                post.setPostTitle(resultSet.getString("post_title"));
                post.setPostContent(resultSet.getString("post_content"));
                post.setAuther(resultSet.getString("post_auther"));
                post.setDate(resultSet.getString("post_date"));

                postListDb.add(post);
                log.info("Added posts to arraylist");

            }

        }catch(SQLException e) {
            log.info("Could not find posts");
        }
        log.info("Fandt ingen post");
        return postListDb;
    }

    public int create(Post post) {

        try {
            Connection connection = dbh.createConnection();
            log.info("AddPost: Connection complete");
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO Webblog.Posts (post_title, post_content, post_auther, post_date) VALUES (?,?,?,?) ");

            log.info("AddPost: adder prepared statement");
            pstmt.setString(1, post.getPostTitle());
            pstmt.setString(2, post.getPostContent());
            pstmt.setString(3, post.getAuther());
            pstmt.setString(4, post.getDate());

            return pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int deletePost(int id) {

        try {
            log.info("køre DeletePost");
            Connection connection = dbh.createConnection();
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM Webblog.Posts Where post_id = ?");
            pstmt.setInt(1, id);
            return pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }



    public Post findPostById(int id) {
    log.info("Køre find post by id");

    getAllPost();

    for (Post posts : getAllPost()) {

        if (id == posts.getPostId()) {
            log.info("Finished Post FindById metode");
            log.info("Du har fat i følgende Post");
            log.info(posts.getPostTitle());
            log.info(String.valueOf(posts.getPostId()));

            return posts;
        }
    }
    log.info("---------------fandt ingen posts---------------");
    return null;
    }

}
