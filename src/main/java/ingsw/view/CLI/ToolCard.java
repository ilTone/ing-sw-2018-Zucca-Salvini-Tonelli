package ingsw.view.CLI;

import ingsw.model.ViewData;
import ingsw.view.ToolView;

public class ToolCard {


    public static void toolCard1(PlayGame playGame) {
        playGame.getClient().useToolCard(1,null);
        int selectedDie = playGame.selectDie();
        ToolView toolView = new ToolView();
        toolView.setStartRow1(0);
        toolView.setStartCol1(selectedDie);
        System.out.println("Choose operation:\n1 - Increase die's value\n2 - Decrease die's value");
        int operation = Main.validateIntegerInput(1,2);
        if (operation == 1) toolView.setDieModified(playGame.dpDieValue(selectedDie)+1);
        else toolView.setDieModified(playGame.dpDieValue(selectedDie)-1);
        if (toolView.getDieModified() < 1 || toolView.getDieModified() >6) System.out.println(ToString.printColored(ToString.ANSI_RED,"Forbidden operation"));
        else if (useToolCard(1, playGame, toolView)) playGame.setToolCardAlreadyUsed(true);
    }

    public static void toolCard2(PlayGame playGame) {
        ToolView toolView = new ToolView();
        String diePosition = Main.validateCoordinates(false);
        toolView.setStartRow1(Integer.parseInt(diePosition.substring(0,1))-1);
        toolView.setStartCol1(Integer.parseInt(String.valueOf(diePosition.charAt(2)))-1);
        playGame.getClient().takeWPDie(toolView.getStartRow1(), toolView.getStartCol1());
        diePosition = Main.validateCoordinates(true);
        toolView.setEndRow1(Integer.parseInt(diePosition.substring(0,1))-1);
        toolView.setEndCol1(Integer.parseInt(String.valueOf(diePosition.charAt(2)))-1);
        if (useToolCard(2, playGame, toolView)) {
            playGame.setToolCardAlreadyUsed(true);
            playGame.getClient().nullSelection();
        }
    }

    public static void toolCard3(PlayGame playGame) {
        ToolView toolView = new ToolView();
        String diePosition = Main.validateCoordinates(false);
        toolView.setStartRow1(Integer.parseInt(diePosition.substring(0,1))-1);
        toolView.setStartCol1(Integer.parseInt(String.valueOf(diePosition.charAt(2)))-1);
        playGame.getClient().takeWPDie(toolView.getStartRow1(), toolView.getStartCol1());
        diePosition = Main.validateCoordinates(true);
        toolView.setEndRow1(Integer.parseInt(diePosition.substring(0,1))-1);
        toolView.setEndCol1(Integer.parseInt(String.valueOf(diePosition.charAt(2)))-1);
        if (useToolCard(3, playGame, toolView)) {
            playGame.setToolCardAlreadyUsed(true);
            playGame.getClient().nullSelection();
        }
    }

    public static void toolCard4(PlayGame playGame) {
        ToolView toolView = new ToolView();
        toolView.setPhase(0);
        String diePosition = Main.validateCoordinates(false);
        toolView.setStartRow1(Integer.parseInt(diePosition.substring(0,1))-1);
        toolView.setStartCol1(Integer.parseInt(String.valueOf(diePosition.charAt(2)))-1);
        playGame.getClient().takeWPDie(toolView.getStartRow1(), toolView.getStartCol1());
        diePosition = Main.validateCoordinates(true);
        toolView.setEndRow1(Integer.parseInt(diePosition.substring(0,1))-1);
        toolView.setEndCol1(Integer.parseInt(String.valueOf(diePosition.charAt(2)))-1);
        if (!useToolCard(4, playGame, toolView)) return;
        else playGame.getClient().nullSelection();
        System.out.println("Choose operation:\n1 - Move another die\n2 - Stop using tool card");
        if (Main.validateIntegerInput(1,2) == 2) return;
        toolView = new ToolView();
        toolView.setPhase(1);
        diePosition = Main.validateCoordinates(false);
        toolView.setStartRow2(Integer.parseInt(diePosition.substring(0,1))-1);
        toolView.setStartCol2(Integer.parseInt(String.valueOf(diePosition.charAt(2)))-1);
        playGame.getClient().takeWPDie(toolView.getStartRow2(), toolView.getStartCol2());
        diePosition = Main.validateCoordinates(true);
        toolView.setEndRow2(Integer.parseInt(diePosition.substring(0,1))-1);
        toolView.setEndCol2(Integer.parseInt(String.valueOf(diePosition.charAt(2)))-1);
        if(useToolCard(4, playGame, toolView)) {
            playGame.setToolCardAlreadyUsed(true);
            playGame.getClient().nullSelection();
        }
    }

    public static void toolCard5(PlayGame playGame) {
        ToolView toolView = new ToolView();
        int selectedDie = playGame.selectDie();
        toolView.setStartRow1(0);
        toolView.setStartCol1(selectedDie);
        System.out.println("Choose round:");
        int round = Main.validateIntegerInput(1,playGame.getClient().getRound());
        toolView.setRound(round);
        System.out.println("Round track:  (round" + round + ")");
        int i = 1;
        for(String die : playGame.getClient().updateView().getRoundTrack()){
            if(Integer.parseInt(die.substring(0,1)) == round){
                System.out.println(i + " - " +ToString.viewDieToString(die));
                i++;
            }
        }
        System.out.println("Choose die:");
        int die = Main.validateIntegerInput(1,i)-1;
        toolView.setEndRow1(0);
        toolView.setEndCol1(die);
        if(useToolCard(5, playGame, toolView)) {
            playGame.setToolCardAlreadyUsed(true);
            playGame.getClient().nullSelection();
        }
    }

    public static void toolCard6(PlayGame playGame) {
        ToolView toolView = new ToolView();
        int selectedDie = playGame.selectDie();
        toolView.setStartRow1(0);
        toolView.setStartCol1(selectedDie);
        if(useToolCard(6, playGame, toolView)) playGame.setToolCardAlreadyUsed(true);
    }

    public static void toolCard7(PlayGame playGame) {
        if(playGame.getClient().getClockwiseRound()) {
            System.out.println("Tool card not available");
            return;
        }
        ToolView toolView = new ToolView();
        ViewData viewData = playGame.getClient().updateView();
        int dieIndex = 0;
        for (String die: viewData.getDraftPoolDice()){
            if(!die.equals("die(0,WHITE)")) toolView.setListOfCoordinateY(Integer.toString(dieIndex));
            dieIndex ++;
        }
        if(useToolCard(7, playGame, toolView)) playGame.setToolCardAlreadyUsed(true);
    }

    public static void toolCard8(PlayGame playGame) {
        if(!playGame.getClient().getClockwiseRound() || !playGame.getClient().getInsertedDie()){
            System.out.println("Tool card not available");
            return;
        }
        if (useToolCard(8, playGame, null)) {
            playGame.setToolCardAlreadyUsed(true);
            playGame.getClient().setTool8Used(true);
        }
    }

    public static void toolCard9(PlayGame playGame) {
        ToolView toolView = new ToolView();
        int selectedDie = playGame.selectDie();
        toolView.setStartRow1(0);
        toolView.setStartCol1(selectedDie);
        String diePosition = Main.validateCoordinates(true);
        toolView.setEndRow1(Integer.parseInt(diePosition.substring(0,1))-1);
        toolView.setEndCol1(Integer.parseInt(String.valueOf(diePosition.charAt(2)))-1);
        if(useToolCard(9, playGame, toolView)) {
            playGame.setToolCardAlreadyUsed(true);
            playGame.getClient().nullSelection();
            playGame.getClient().setInsertedDie(true);
        }
    }

    public static void toolCard10(PlayGame playGame) {
        int selectedDie = playGame.selectDie();
        ToolView toolView = new ToolView();
        toolView.setStartRow1(0);
        toolView.setStartCol1(selectedDie);
        if(useToolCard(10, playGame, toolView)) playGame.setToolCardAlreadyUsed(true);
    }

    public static void toolCard11(PlayGame playGame) {
        int selectedDie = playGame.selectDie();
        ToolView toolView = new ToolView();
        toolView.setStartRow1(0);
        toolView.setStartCol1(selectedDie);
        toolView.setPhase(0);
        if(!useToolCard(11, playGame, toolView)) return;
        playGame.printSeparator();
        playGame.printDraftPool(playGame.getClient().updateView());
        playGame.printSeparator();
        toolView = new ToolView();
        System.out.println("Choose value for the new die:");
        toolView.setDieModified(Main.validateIntegerInput(1,6));
        toolView.setPhase(1);
        if(!useToolCard(11, playGame, toolView)) return;
        String diePosition = Main.validateCoordinates(true);
        toolView.setStartRow1(Integer.parseInt(diePosition.substring(0,1))-1);
        toolView.setStartCol1(Integer.parseInt(String.valueOf(diePosition.charAt(2)))-1);
        toolView.setPhase(2);
        if (useToolCard(11, playGame, toolView)) {
            playGame.setToolCardAlreadyUsed(true);
            playGame.getClient().nullSelection();
            playGame.getClient().setInsertedDie(true);
        }
    }

    public static void toolCard12(PlayGame playGame) {
        ToolView toolView = new ToolView();
        toolView.setPhase(0);
        System.out.println("Choose round:");
        int round = Main.validateIntegerInput(1,playGame.getClient().getRound());
        toolView.setRound(round);
        System.out.println("Round track:  (round" + round + ")");
        int i = 1;
        for(String die : playGame.getClient().updateView().getRoundTrack()){
            if(Integer.parseInt(die.substring(0,1)) == round){
                System.out.println(i + " - " +ToString.viewDieToString(die));
                i++;
            }
        }
        System.out.println("Choose die:");
        int die = Main.validateIntegerInput(1,i)-1;
        toolView.setStartRow1(0);
        toolView.setStartCol1(die);
        if(!useToolCard(12,playGame,toolView)) return;
        toolView = new ToolView();
        toolView.setPhase(1);
        String diePosition = Main.validateCoordinates(false);
        toolView.setStartRow1(Integer.parseInt(diePosition.substring(0,1))-1);
        toolView.setStartCol1(Integer.parseInt(String.valueOf(diePosition.charAt(2)))-1);
        diePosition = Main.validateCoordinates(true);
        toolView.setEndRow1(Integer.parseInt(diePosition.substring(0,1))-1);
        toolView.setEndCol1(Integer.parseInt(String.valueOf(diePosition.charAt(2)))-1);
        if (!useToolCard(12, playGame, toolView)) return;
        System.out.println("Choose operation:\n1 - Move another die\n2 - Stop using tool card");
        if (Main.validateIntegerInput(1,2) == 2) return;
        toolView = new ToolView();
        toolView.setPhase(2);
        diePosition = Main.validateCoordinates(false);
        toolView.setStartRow2(Integer.parseInt(diePosition.substring(0,1))-1);
        toolView.setStartCol2(Integer.parseInt(String.valueOf(diePosition.charAt(2)))-1);
        diePosition = Main.validateCoordinates(true);
        toolView.setEndRow2(Integer.parseInt(diePosition.substring(0,1))-1);
        toolView.setEndCol2(Integer.parseInt(String.valueOf(diePosition.charAt(2)))-1);
        if(useToolCard(12, playGame, toolView)) {
            playGame.setToolCardAlreadyUsed(true);
            playGame.getClient().nullSelection();
        }
    }

    private static boolean useToolCard(int toolCard, PlayGame playGame, ToolView toolView){
        if(playGame.getClient().useToolCard(toolCard,toolView)) {
            System.out.println("Tool card successfully used");
            return true;
        }
        else {
            System.out.println("Tool card not available");
            return false;
        }
    }

}
