package utils;

import routes.Route;

import java.util.ArrayList;

public class Stock {

    private final int starting[] = {0, 1, 0, 2, 2, 3, 3, 1};
    private final int destination[] = {1, 0, 2, 0, 3, 2, 1, 3};

    private ArrayList<Integer> companies[];

    public Stock() {

        int x_split = Settings.COMP_X_INITIAL / 4;
        int y_split = Settings.COMP_Y_INITIAL / 4;

        int split[] = new int[]{x_split, y_split};

        companies = new ArrayList[2];
        for (int i = 0; i < companies.length; i++) {
            companies[i] = new ArrayList<>();

            for (int j = 0; j < 4; j++) {
                companies[i].add(split[i]);
            }
        }

    }

    public ArrayList<Integer>[] getCompanies() {
        return companies;
    }

    public void serve(Customer customer){

        Route route = customer.getRoute();
        int company = customer.getCompany();

        int stock = this.companies[company].get(starting[route.getId()]);
        if (stock <= 0) {
            customer.forceFail();
        }else{
            this.companies[company].set(starting[route.getId()], stock - 1);
        }

    }

    public void replenish(Customer customer) {
        if (customer.failed()) {
            return;
        }

        Route route = customer.getRoute();

        int stock = this.companies[customer.getCompany()].get(destination[route.getId()]);
        this.companies[customer.getCompany()].set(destination[route.getId()], stock + 1);

    }

    public void display() {

    }
}
