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
import model.Mermo;
import model.Sorum;

public class SorumDao {

    // Insert Sorum Dao In Database
    public int insert(Sorum sorum) {
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement(
                            "insert into sorum (id, namapem, alamat, jenismot, namot_id, mermo_id) value (?, ?, ?, ?, ?, ?)");
            statement.setString(1, sorum.getId());
            statement.setString(2, sorum.getNamapem());
            statement.setString(3, sorum.getAlamat());
            statement.setString(4, sorum.getJenismot());
            statement.setString(5, sorum.getNamot().getId());
            statement.setString(6, sorum.getMermo().getId());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Update Sorum Dao in Database
    public int update(Sorum sorum) {
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement(
                            "update sorum set namapem = ?, alamat = ?, jenismot = ?, mermo_id = ?, namot_id = ? where id = ?");
            statement.setString(1, sorum.getNamapem());
            statement.setString(1, sorum.getAlamat());
            statement.setString(1, sorum.getJenismot());
            statement.setString(2, sorum.getNamot().getId());
            statement.setString(2, sorum.getMermo().getId());
            statement.setString(3, sorum.getId());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Delete in sorum Dao Database
    public int delete(Sorum sorum) {
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement("delete from sorum where id = ?");
            statement.setString(1, sorum.getId());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Sorum> findAll() {
        List<Sorum> list = new ArrayList<>();
        try (Connection connection = MySqlConnection.getInstance().getConnection();
                Statement statement = connection.createStatement();) {
            try (ResultSet resultSet = statement
                    .executeQuery(
                            "select sorum.*, namot.*, mermo.* "
                                    + "from sorum "
                                    + "LEFT OUTER JOIN namot on namot.id = sorum.namot_id "
                                    + "LEFT OUTER JOIN mermo on mermo.id = sorum.mermo_id");) {
                // Retrieving the data
                while (resultSet.next()) {
                    Sorum sorum = new Sorum();
                    sorum.setId(resultSet.getString("id"));
                    sorum.setNamapem(resultSet.getString("namapem"));
                    sorum.setAlamat(resultSet.getString("alamat"));
                    sorum.setJenismot(resultSet.getString("jenismot"));

                    Namot namot = new Namot();
                    namot.setId(resultSet.getString("sorum.namot_id"));
                    namot.setNamamotor(resultSet.getString("namot.namamotor"));

                    Mermo mermo = new Mermo();
                    mermo.setId(resultSet.getString("sorum.mermo_id"));
                    mermo.setMerekmotor(resultSet.getString("mermo.merekmotor"));

                    sorum.setNamot(namot);
                    sorum.setMermo(mermo);

                    list.add(sorum);
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
