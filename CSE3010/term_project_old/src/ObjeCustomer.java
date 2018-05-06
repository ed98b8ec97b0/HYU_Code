package term_project;

public class ObjeCustomer extends Obje {
    private String name, id, birth, phone, level;
    private int sale;

    public ObjeCustomer(String name, String id, String birth, String phone, String level, int sale) {
        super(name, id);
        this.birth = birth;
        this.phone = phone;
        this.level = level;
        this.sale = sale;
    }

    public String getBirth() {
        return this.birth;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getLevel() {
        return this.level;
    }

    public int getSale() {
        return this.sale;
    }
}
