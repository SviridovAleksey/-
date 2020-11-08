 abstract class Participant implements iAct{

    private String name;
    private int limit;
    private String kind;


    Participant(){
    this.name = "Неизвестно";
    this.limit = 0;
    this.kind = "Неизвестно";
    }

    protected String getName () {
        return name;
    }

    protected int getLimit(){
        return limit;
    }

    protected String getKind(){
        return kind;
    }

    protected void setName (String name) {
        this.name = name;
    }

    protected void setLimit(int limit){
        this.limit = limit;
    }

    protected void setKind(String kind) {
        this.kind = kind;
    }

}
