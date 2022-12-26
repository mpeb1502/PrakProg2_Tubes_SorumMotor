package dao;

import db.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import model.Mermo;

public class MermoDao {
    //  Get Insert Merekmotor Dao in Database
    public int insert(Mermo merekmotor) {
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement("insert into mermo (id, merekmotor) value (?, ?)");
            statement.setString(1, merekmotor.getId());
            statement.setString(2, merekmotor.getMerekmotor());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Update Merekmotor Dao in Database
    public int update(Mermo merekmotor) {
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement("update mermo set merekmotor = ? where id = ?");
            statement.setString(1, merekmotor.getMerekmotor());
            statement.setString(2, merekmotor.getId());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Delete Merekmotor dao in database
    public int delete(Mermo merekmotor) {
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement("delete from mermo where id = ?");
            statement.setString(1, merekmotor.getId());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Mermo> findAll() {
        List<Mermo> list = new ArrayList<>();
        try (Connection connection = MySqlConnection.getInstance().getConnection();
                Statement statement = connection.createStatement();) {
            try (ResultSet resultSet = statement.executeQuery("select * from mermo");) {
                // Retrieving the data
                while (resultSet.next()) {
                    Mermo merekmotor = new Mermo();
                    merekmotor.setId(resultSet.getString("id"));
                    merekmotor.setMerekmotor(resultSet.getString("merekmotor"));
                    list.add(merekmotor);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
