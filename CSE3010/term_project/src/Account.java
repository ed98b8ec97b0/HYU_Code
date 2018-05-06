public class Account {
    private String sname;
    private String sid;
    private String sclass;

    public Account() {
        this.sname = ""; this.sid = ""; this.sclass = "";
    }

    public void setAccount(String sname, String sid, String sclass) {
        this.sname = sname; this.sid = sid; this.sclass = sclass;
    }

    public String getSname() {
        return this.sname;
    }

    public String getSclass() {
        return this.sclass;
    }
}
