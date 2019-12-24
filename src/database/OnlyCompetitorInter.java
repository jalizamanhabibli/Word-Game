package database;

import beans.Competitor;
import java.sql.Connection;
import java.util.List;

public interface OnlyCompetitorInter {

    Competitor.CompetitorInfo checkUserDB(Connection conn, String username, String password);

    List competitorInfo(Connection conn);

    int updateCompetitorInfo(int id, boolean bool, Connection conn);

    Competitor.CompetitorInfo checkComInfo(int id, Connection conn);

    Competitor.CompetitorBase checkComBase(int id, Connection conn);
}
