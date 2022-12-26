package dao;

import db.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import model.Namot;

public class NamotDao {

    //  Get Insert Nama motor Dao in Database
    public int insert(Namot namamotor) {
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement("insert into namot (id, namamotor) value (?, ?)");
            statement.setString(1, namamotor.getId());
            statement.setString(2, namamotor.getNamamotor());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    //  Get Update Nama motor Dao in Database
    public int update(Namot namamotor) {
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement("update namot set namamotor = ? where id = ?");
            statement.setString(1, namamotor.getNamamotor());
            statement.setString(2, namamotor.getId());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int delete(Namot namamotor) {
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement("delete from namot where id = ?");
            statement.setString(1, namamotor.getId());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Namot> findAll() {
        List<Namot> list = new ArrayList<>();
        try (Connection connection = MySqlConnection.getInstance().getConnection();
                Statement statement = connection.createStatement();) {
            try (ResultSet resultSet = statement.executeQuery("select * from namot");) {
                // Retrieving the data
                while (resultSet.next()) {
                    Namot namamotor = new Namot();
                    namamotor.setId(resultSet.getString("id"));
                    namamotor.setNamamotor(resultSet.getString("namamotor"));
                    list.add(namamotor);
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
