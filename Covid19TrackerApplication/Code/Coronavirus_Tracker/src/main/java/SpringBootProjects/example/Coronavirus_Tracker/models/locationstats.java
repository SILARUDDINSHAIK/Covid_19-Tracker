package SpringBootProjects.example.Coronavirus_Tracker.models;

public class locationstats
{
    private  String State;
    private  String Country;
    private int NewlyRecordedCases;
    private int latestTotalCases;
    private int diffFromPrevDay;



    public void setState(String state) {
        State = state;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public void setNewlyRecordedCases(int newlyRecordedCases) {
        NewlyRecordedCases = newlyRecordedCases;
    }

    public void setLatestTotalCases(int latestTotalCases) {
        this.latestTotalCases = latestTotalCases;
    }

    public void setDiffFromPrevDay(int diffFromPrevDay) {
        this.diffFromPrevDay = diffFromPrevDay;
    }

    public String getState() {
        return State;
    }

    public String getCountry() {
        return Country;
    }

    public int getNewlyRecordedCases() {
        return NewlyRecordedCases;
    }

    public int getLatestTotalCases() {
        return latestTotalCases;
    }

    public int getDiffFromPrevDay() {
        return diffFromPrevDay;
    }

    @Override
    public String toString() {
        return "locationstats{" +
                "State='" + State + '\'' +
                ", Country='" + Country + '\'' +
                ", NewlyRecordedCases=" + NewlyRecordedCases +
                ", latestTotalCases=" + latestTotalCases +
                ", diffFromPrevDay=" + diffFromPrevDay +
                '}';
    }

}
