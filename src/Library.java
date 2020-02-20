public class Library {
    private int id;
    private Bock[] bocks;
    private int timeToSignUp;
    private int scansPerDay;

    public Library(int id, Bock[] bocks, int timeToSignUp, int scansPerDay) {
        this.id = id;
        this.bocks = bocks;
        this.timeToSignUp = timeToSignUp;
        this.scansPerDay = scansPerDay;
    }
    public Library(){}
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bock[] getBocks() {
        return bocks;
    }

    public void setBocks(Bock[] bocks) {
        this.bocks = bocks;
    }

    public int getTimeToSignUp() {
        return timeToSignUp;
    }

    public void setTimeToSignUp(int timeToSignUp) {
        this.timeToSignUp = timeToSignUp;
    }

    public int getScansPerDay() {
        return scansPerDay;
    }

    public void setScansPerDay(int scansPerDay) {
        this.scansPerDay = scansPerDay;
    }
}
