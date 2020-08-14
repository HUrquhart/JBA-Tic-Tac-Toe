import java.util.*;
public class Main {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] fields = new char[][]{{' ', ' ', ' '},{' ', ' ', ' '},{' ', ' ', ' '}};

        // Create Gameloop here
        boolean x = true;
        boolean state = true;
        while(state == true){
            Main.printBoard(fields);
            state = Main.determineGameState(fields);
            if(state){
                int pturn = (x)? 1: 2;
                x = Main.getPlayerMove(fields, scanner, pturn);
            }
        }
        scanner.close();
    }

    public static boolean getPlayerMove(char[][] fields,Scanner scanner, int player) {
        // ask the user of their turn
        
        while(true){
            System.out.print("Enter the coordinates : ");
            try{
                String[] l = scanner.nextLine().split(" ");
                int x = Integer.parseInt(l[0]);
                int y = Integer.parseInt(l[1]);
                if (fields[y - 1][x - 1] == 'X' || fields[y - 1][x - 1] == 'O') {
                    System.out.println("This cell is occupied! Choose another one!");
                }else{
                        // swap depending on turn
                        switch(player){
                        case 1:
                            fields[y - 1][x - 1] = 'X'; 
                            return false;
                        case 2:
                            fields[y - 1][x - 1] = 'O'; 
                            return true;
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Coordinates should be from 1 to 3!");
            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println("You should enter numbers!"); 
            }
        }
    }
    
    public static void printBoard(char[][] f){
        System.out.println("---------");
        System.out.printf("| %c %c %c |\n", f[2][0],f[2][1],f[2][2]);
        System.out.printf("| %c %c %c |\n", f[1][0],f[1][1],f[1][2]);
        System.out.printf("| %c %c %c |\n", f[0][0],f[0][1],f[0][2]);
        System.out.println("---------");
    }
    
    // determine the game state
    public static boolean determineGameState(char[][] f){
        int [] checks = new int[] {
            // rows
            f[2][0] + f[2][1] + f[2][2],
            f[1][0] + f[1][1] + f[1][2],
            f[0][0] + f[0][1] + f[0][2],
            // columns
            f[0][0] + f[1][0] + f[2][0],
            f[0][1] + f[1][1] + f[2][1],
            f[0][2] + f[1][2] + f[2][2],
            // diags
            f[0][0] + f[1][1] + f[2][2], 
            f[2][0] + f[1][1] + f[0][2]
        };
        
        // determine the difference by counting
        int countO = 0;
        int countX = 0;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                char ch =  f[i][j];
                if(ch == 'X'){countX++;}
                if(ch == 'O'){countO++;}
            }
        }

        
        if(countO - countX > 1 || countX - countO > 1){// is the field difference impossible?
            System.out.println("Impossible");
            return false;
        }else{
            // determine the State
            // check for duplicate wins
            for(int r: checks){
                if (r == ((int)'X')*3) {
                    System.out.println("X wins");
                    return false;
                }
                if (r == ((int)'O')*3) {
                    System.out.println("O wins");
                    return false;
                }
                if (countX + countO == 9) {
                    System.out.println("Draw");
                    return false;
                }
            }
            
        }
        return true;
    }
}