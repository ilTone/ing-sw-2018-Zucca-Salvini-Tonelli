package ingsw;

public class Tool1 implements ToolStrategy {
    private String title;
    private String comment;

    public Tool1(){
        this.title ="Pinza Sgrossatrice";
        this.comment = "Dopo aver scelto un dado,\n" + "aumenta o dominuisci il valore\n" + "del dado scelto di 1\n" + "Non puoi cambiare\n" + "un 6 in 1 o un 1 in 6"
    }
    @Override
    public void doOp(){
        //System.out.println("Sono la carta 1");
        if(1 <= DraftPool.takeDie(n).getNum() && DraftPool.takeDie(n).getNum()<=5)
            DraftPool.takeDie(n).setNum(getNum()+1);
        else
            System.out.println("Non puoi usare questa carta su questo dado");
    }
}
