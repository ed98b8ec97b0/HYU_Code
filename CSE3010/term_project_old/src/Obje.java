package term_project;

public class Obje {
    private String name, id;

    public Obje(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public String getId() {
        return this.id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }
}
