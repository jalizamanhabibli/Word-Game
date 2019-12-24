package beans;

public class Competitor {

    private CompetitorBase competitorBase;
    private CompetitorInfo competitorInfo;

    public class CompetitorBase {

        private int id;
        private String username;
        private String password;
        private int cInfoId;

        public CompetitorBase() {
        }

        public CompetitorBase(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public int getId() {
            return id;
        }

        public CompetitorBase setId(int id) {
            this.id = id;
            return this;
        }

        public String getUsername() {
            return username;
        }

        public CompetitorBase setUsername(String username) {
            this.username = username;
            return this;
        }

        public String getPassword() {
            return password;
        }

        public CompetitorBase setPassword(String password) {
            this.password = password;
            return this;
        }

        public int getcInfoId() {
            return cInfoId;
        }

        public CompetitorBase setcInfoId(int cInfoId) {
            this.cInfoId = cInfoId;
            return this;
        }

        @Override
        public String toString() {
            return "CompetitorBase{" + "id=" + id + "\n username=" + username + "\n password=" + password + "\n}";
        }
    }

    public class CompetitorInfo {

        private int id;
        private String name;
        private String surname;
        private int point;
        private boolean position;

        public CompetitorInfo() {
        }

        public CompetitorInfo(String name, String surname, int point, boolean position) {
            this.name = name;
            this.surname = surname;
            this.point = point;
            this.position = position;
        }

        public int getId() {
            return id;
        }

        public CompetitorInfo setId(int id) {
            this.id = id;
            return this;
        }

        public String getName() {
            return name;
        }

        public CompetitorInfo setName(String name) {
            this.name = name;
            return this;
        }

        public String getSurname() {
            return surname;
        }

        public CompetitorInfo setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public int getPoint() {
            return point;
        }

        public CompetitorInfo setPoint(int point) {
            this.point = point;
            return this;
        }

        public boolean isPosition() {
            return position;
        }

        public CompetitorInfo setPosition(boolean position) {
            this.position = position;
            return this;
        }

        @Override
        public String toString() {
            return "Competitor{" + "id=" + id + "\n name=" + name + "\n surname=" + surname + "\n point=" + point + "\n position=" + position + "\n}";
        }
    }

    public Competitor() {
    }

    public Competitor(CompetitorBase competitorBase, CompetitorInfo competitorInfo) {
        this.competitorBase = competitorBase;
        this.competitorInfo = competitorInfo;
    }

    public CompetitorBase getCompetitorBase() {
        return competitorBase;
    }

    public void setCompetitorBase(CompetitorBase competitorBase) {
        this.competitorBase = competitorBase;
    }

    public CompetitorInfo getCompetitorInfo() {
        return competitorInfo;
    }

    public void setCompetitorInfo(CompetitorInfo competitorInfo) {
        this.competitorInfo = competitorInfo;
    }

    @Override
    public String toString() {
        return "Competitor{" + "competitorBase=" + competitorBase + "\n competitorInfo=" + competitorInfo + "\n}";
    }

}
