import java.util.*;

class Field {
    private HashMap<String, String> state;

    public void setState(HashMap<String, String> state) {
        this.state = state;
    }

    public HashMap<String, String> getState() {
        return state;
    }
}

class GameLogic{

    private Field field = new Field();
    private Scanner scanner = new Scanner(System. in);

    public static final List<String> MOVES = Arrays.asList("ROCK", "PAPER", "SCISSORS");

    private final String initMsg = "Input your choice and press Enter: ";
    private final String tryAgain = "Wrong choice, try again... ";


    private void getInput(){
        HashMap<String, String> stateMoves = new HashMap<>();
        System.out.println(initMsg);

        String inputUser = scanner.nextLine();
        String usrMove = inputUser.toUpperCase();

        if(!validateInput(usrMove)) {
            System.out.println(tryAgain);
            getInput();
        }
        stateMoves.put("user", usrMove);

        String cmpMove = getComputerMove();
        stateMoves.put("computer", cmpMove);

        field.setState(stateMoves);
    };

    private String getComputerMove() {
        Random random = new Random();
        int idx = random.nextInt(MOVES.size());
        return MOVES.get(idx);
    };

    private boolean validateInput(String move){
        return MOVES.contains(move);
    };

    private int checkWinner(){
        HashMap<String, String> state = field.getState();

        if (state.get("user").equals(state.get("computer")))
            return 0;

        String user = state.get("user");
        if ("ROCK".equals(user)) {
            return state.get("computer").equals("SCISSORS") ? 1 : -1;
        } else if ("PAPE".equals(user)) {
            return state.get("computer").equals("PAPER") ? 1 : -1;
        } else if ("SCISSORS".equals(user)) {
            return state.get("computer").equals("PAPER") ? 1 : -1;
        }


        return 0;
    };

    private void showResults(int checker) {
        HashMap<String, String> state = field.getState();

        switch (checker) {
            case 0:
                System.out.println("Tie!");
                break;
            case 1:
                System.out.println(state.get("user") + " beats " + state.get("computer") + ". Win!");
                break;
            case -1:
                System.out.println(state.get("computer") + " beats " + state.get("user") + ". Lose.");
                break;
        }
    }

    private void playAgain() {
        System.out.print("Play again? [Y/N]");
        String userInput = scanner.nextLine();
        userInput = userInput.toLowerCase();
        if(userInput.charAt(0) == 'y') {
            runGame();
        }
    }

    public void runGame() {
        getInput();
        int result = checkWinner();
        showResults(result);
        playAgain();
    }


}


public class GameRunner {
    public static void main(String[] args) {
        System.out.println("Let's play to ROCK, PAPER, SCISSORS");
        GameLogic game = new GameLogic();
        game.runGame();
    }
}
