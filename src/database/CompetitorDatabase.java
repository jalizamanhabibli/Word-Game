package database;

import beans.Competitor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CompetitorDatabase extends AbstractDatabase<Competitor> implements OnlyCompetitorInter {

    @Override
    public List<Competitor> select(Connection conn) {
        List<Competitor> competitors = new ArrayList<>();
        try {

            PreparedStatement ps = conn.prepareStatement("select  cb.*,ci.* from competitors_base cb\n"
                    + "inner join competitors_info ci where com_base_com_info=com_id");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Competitor.CompetitorBase competitorBase = new Competitor().new CompetitorBase();
                Competitor.CompetitorInfo competitorInfo = new Competitor().new CompetitorInfo();

                competitorBase.setId(rs.getInt(1)).setUsername(rs.getString(2)).
                        setPassword(rs.getString(3)).setcInfoId(rs.getInt(4));

                competitorInfo.setId(rs.getInt(5)).setName(rs.getString(6)).setSurname(rs.getString(7)).
                        setPoint(rs.getInt(8)).setPosition(rs.getBoolean(9));

                Competitor competitor = new Competitor(competitorBase, competitorInfo);
                competitors.add(competitor);
            }
            return competitors;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public int insert(Competitor obj, Connection conn) {
        try {
            PreparedStatement ps = conn.prepareStatement("call insertCompetitor(?,?,?,?,?,?)");
            ps.setString(1, obj.getCompetitorBase().getUsername());
            ps.setString(2, obj.getCompetitorBase().getPassword());
            ps.setString(3, obj.getCompetitorInfo().getName());
            ps.setString(4, obj.getCompetitorInfo().getSurname());
            ps.setInt(5, obj.getCompetitorInfo().getPoint());
            ps.setBoolean(6, obj.getCompetitorInfo().isPosition());
            return ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return -1;
    }

    @Override
    public int uptade(Competitor obj, Connection conn) {
        try {
            PreparedStatement ps = conn.prepareStatement("call updateCompetitor(?,?,?,?,?,?,?)");
            ps.setInt(1, obj.getCompetitorBase().getId());
            ps.setString(2, obj.getCompetitorBase().getUsername());
            ps.setString(3, obj.getCompetitorBase().getPassword());
            ps.setString(4, obj.getCompetitorInfo().getName());
            ps.setString(5, obj.getCompetitorInfo().getSurname());
            ps.setInt(6, obj.getCompetitorInfo().getPoint());
            ps.setBoolean(7, obj.getCompetitorInfo().isPosition());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return -1;
    }

    @Override
    public int delete(int id, Connection conn) {
        try {
            PreparedStatement ps = conn.prepareStatement("call deleteCompetitor(?)");
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    @Override
    public Competitor.CompetitorInfo checkUserDB(Connection conn, String username, String password) {
        String query = "select  competitors_info.* from competitors_base\n"
                + "inner JOIN competitors_info\n"
                + "where com_base_username = ? && com_base_password = ? && com_base_id=com_id";
        Competitor.CompetitorInfo competitorInfo = null;
        try {

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                competitorInfo = new Competitor().new CompetitorInfo(rs.getString(2), rs.getString(3), rs.getInt(4), rs.getBoolean(5)).setId(rs.getInt(1));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return competitorInfo;
    }

    @Override
    public List competitorInfo(Connection conn) {
        List<Competitor.CompetitorInfo> competitorInfos = new ArrayList();
        String query = "select ci.com_id       id,\n"
                + "       ci.com_name     name,\n"
                + "       ci.com_surname  surname,\n"
                + "       ci.com_point    point,\n"
                + "       ci.com_position position\n"
                + "from competitors_base cb\n"
                + "         inner join competitors_info ci\n"
                + "where com_base_com_info = com_id";
        try {
            Statement s = conn.createStatement();
            s.execute(query);
            ResultSet rs = s.getResultSet();
            while (rs.next()) {
                Competitor.CompetitorInfo competitorInfo = new Competitor().new CompetitorInfo(
                        rs.getString(2), rs.getString(3), rs.getInt(4), rs.getBoolean(5)).setId(rs.getInt(1));
                competitorInfos.add(competitorInfo);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return competitorInfos;
    }

    @Override
    public int updateCompetitorInfo(int id, boolean bool, Connection conn) {
        String query = "update competitors_info\n"
                + "set com_position = ?\n"
                + "where com_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setBoolean(1, bool);
            ps.setInt(2, id);
            return ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    @Override
    public Competitor.CompetitorInfo checkComInfo(int id, Connection conn) {
        Competitor.CompetitorInfo competitorInfo = null;
        String query = "Select *from competitors_info where com_id=?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                competitorInfo = new Competitor().new CompetitorInfo(rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getBoolean(5)).setId(rs.getInt(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return competitorInfo;
    }
    @Override
    public Competitor.CompetitorBase checkComBase(int id, Connection conn) {
        Competitor.CompetitorBase competitorBase = null;
        String query = "Select *from competitors_base where com_base_id=?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                competitorBase = new Competitor().new CompetitorBase(rs.getString(2), rs.getString(3)).
                        setId(rs.getInt(1)).setcInfoId(rs.getInt(4));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return competitorBase;
    }
}
