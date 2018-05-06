package term_project;

public class ObjeStaff extends Obje {
    private String name, id, position;
    private int sale;

    public ObjeStaff(String name, String id, String position, int sale) {
        super(name, id);
        this.position = position;
        this.sale = sale;
    }

    public String getPosition() {
        return this.position;
    }

    public int getSale() {
        return this.sale;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }
}
