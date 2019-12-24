package database;

import beans.Word;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WordsDatabase extends AbstractDatabase<Word> {

    @Override
    public List<Word> select(Connection conn) {
        List<Word> words = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("select *from words");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Word word = new Word(rs.getString(2), rs.getString(3)).setId(rs.getInt(1));
                words.add(word);
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return words;
    }

    @Override
    public int insert(Word obj, Connection conn) {
        try {
            PreparedStatement ps = conn.prepareStatement("insert into words(word_en, word_az) values(?,?)");
            ps.setString(1, obj.getWordEn());
            ps.setString(2, obj.getWordAz());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    @Override
    public int uptade(Word obj, Connection conn) {
        try {
            PreparedStatement ps = conn.prepareStatement("update words set word_en=?,word_az=? where word_id=?");
            ps.setString(1, obj.getWordEn());
            ps.setString(2, obj.getWordAz());
            ps.setInt(3, obj.getId());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    @Override
    public int delete(int id, Connection conn) {try {
            PreparedStatement ps = conn.prepareStatement("delete from words where word_id=?");
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 1;
    }

}
