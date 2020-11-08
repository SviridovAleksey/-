class Let {

    private int size;
    private String nameLet;

    Let(){
        this.size = 0;
        this.nameLet = "Неизвестно";
    }
    protected String getNameLet () {
        return nameLet;
    }
    protected int getSize () {
        return size;
    }
    protected void setNameLet(String nameLet){
        this.nameLet = nameLet;
    }
    protected void setSize(int size){
        this.size = size;
    }

    protected boolean overcoming(int limit){

        if (limit >= size) { System.out.println(" + преодолевает "
                + nameLet + " длинной " + size + " метра(ов)"); return true; }

        else { System.out.println(" - не может преодолеть "
                + nameLet + " длинной " + size + " и заканчивает гонку "); return false; }
    }

}

