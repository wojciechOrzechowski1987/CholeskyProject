public class Arc {

    private Apex start;
    private Apex end;

    public Arc() {
    }

    public Arc(Apex start, Apex end) {
        this.start = start;
        this.end = end;
    }

    public Apex getStart() {
        return start;
    }

    public void setStart(Apex start) {
        this.start = start;
    }

    public Apex getEnd() {
        return end;
    }

    public void setEnd(Apex end) {
        this.end = end;
    }

}
