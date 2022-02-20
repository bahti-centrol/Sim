package utils;

public class Stock {

    private int manhattan[];
    private int queens[];
    private int jones[];
    private int stony[];

    public Stock() {

        int x_split = Settings.COMP_X_INITIAL / 4;
        int y_split = Settings.COMP_Y_INITIAL / 4;

        manhattan = new int[]{x_split, y_split};
        queens = new int[]{x_split, y_split};
        jones = new int[]{x_split, y_split};
        stony = new int[]{x_split, y_split};
    }

    public void serve(Customer customer){

        Routes.Types direction = customer.getDirection();

        if (direction == Routes.Types.MANHATTAN_TO_QUEENS || direction == Routes.Types.MANHATTAN_TO_JONES) {
            if (this.manhattan[customer.getCompany().val] <= 0) {
                customer.forceFail();
            }else{
                this.manhattan[customer.getCompany().val]--;
            }
        }
        if (direction == Routes.Types.QUEENS_TO_MANHATTAN || direction == Routes.Types.QUEENS_TO_STONY) {
            if (this.queens[customer.getCompany().val] <= 0) {
                customer.forceFail();
            }else{
                this.queens[customer.getCompany().val]--;
            }
        }
        if (direction == Routes.Types.JONES_TO_STONY || direction == Routes.Types.JONES_TO_MANHATTAN) {
            if (this.jones[customer.getCompany().val] <= 0) {
                customer.forceFail();
            }else{
                this.jones[customer.getCompany().val]--;
            }
        }
        if (direction == Routes.Types.STONY_TO_QUEENS || direction == Routes.Types.STONY_TO_JONES) {
            if (this.stony[customer.getCompany().val] <= 0) {
                customer.forceFail();
            }else{
                this.stony[customer.getCompany().val]--;
            }
        }

    }

    public void replenish(Customer customer) {
        if (customer.failed()) {
            return;
        }

        int company = customer.getCompany().val;

        switch (customer.getDirection()) {
            case QUEENS_TO_MANHATTAN:
            case JONES_TO_MANHATTAN:
                this.manhattan[company]++;
                break;
            case STONY_TO_QUEENS:
            case MANHATTAN_TO_QUEENS:
                this.queens[company]++;
                break;
            case JONES_TO_STONY:
            case QUEENS_TO_STONY:
                this.stony[company]++;
                break;
            case MANHATTAN_TO_JONES:
            case STONY_TO_JONES:
                this.jones[company]++;
                break;
        }
    }

    public void display() {
        Console.print("manhattan: X=",manhattan[0], " Y=",manhattan[1]);
        Console.print("queens: X=",queens[0], " Y=",queens[1]);
        Console.print("jones: X=",jones[0], " Y=",jones[1]);
        Console.print("stony: X=",stony[0], " Y=",stony[1]);
    }
}
