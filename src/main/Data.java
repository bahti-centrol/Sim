package main;

import panels.GraphPanel;
import routes.Direction;
import routes.Route;
import utils.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static utils.Chance.*;

public class Data {


    private int total;

    private int manhattanSpawn;
    private int queensSpawn;
    private int jonesSpawn;
    private int stonySpawn;

    private int chosenX;
    private int chosenY;

    private Stock stock;

    private ArrayList<Customer> customers;

    private ArrayList<Customer> archives;

    public Data() {
        this.customers = new ArrayList<Customer>();
        this.archives = new ArrayList<Customer>();

        this.manhattanSpawn = 0;
        this.total = 0;

        this.stock = new Stock();
    }

    public List<Customer> getCustomers() { return customers; }

    public List<Customer> getArchives() {
        return archives;
    }

    public void manhattan() {
        if (chance(Settings.MANHATTAN_SPAWN_RATE)) {

            Route route = flip() ? Direction.MANHATTAN_TO_QUEENS : Direction.MANHATTAN_TO_JONES;
            Customer customer = new Customer(route, ++total, zeroOrOne());

            this.stock.serve(customer);

            this.customers.add(customer);
            manhattanSpawn++;

            Console.print("Customer ",total," created. Towards ", route);
        }
    }

    public void queens() {
        if (chance(Settings.QUEENS_SPAWN_RATE)) {

            Route route = flip() ? Direction.QUEENS_TO_MANHATTAN : Direction.QUEENS_TO_STONY;
            Customer customer = new Customer(route, ++total, zeroOrOne());

            this.stock.serve(customer);

            this.customers.add(customer);
            queensSpawn++;

            Console.print("Customer ",total," created. Towards ", route);
        }
    }

    public void jones_beach() {
        if (chance(Settings.JONES_BEACH_SPAWN_RATE)) {

            Route route = flip() ? Direction.JONES_TO_STONY : Direction.JONES_TO_MANHATTAN;
            Customer customer = new Customer(route, ++total, zeroOrOne());

            this.stock.serve(customer);

            this.customers.add(customer);
            jonesSpawn++;

            Console.print("Customer ",total," created. Towards ", route);
        }
    }

    public void stony_brook() {
        if (chance(Settings.STONY_BROOK_SPAWN_RATE)) {

            Route route = flip() ? Direction.STONY_TO_JONES : Direction.STONY_TO_QUEENS;
            Customer customer = new Customer(route, ++total, zeroOrOne());

            this.stock.serve(customer);

            this.customers.add(customer);
            stonySpawn++;

            Console.print("Customer ",total," created. Towards ", route);
        }
    }

    public boolean overflow() {
        return this.customers.size() > 0;
    }

    public void display() {

        for (Customer c : archives) {
            Console.print(c.toString());
        }

        Console.print("customers, ", customers.size(), " archives ", archives.size());
        Console.print("CompanyX ",chosenX," CompanyY ",chosenY);
        this.stock.display();
    }

    public void compute() {
        Iterator<Customer> itr = customers.iterator();
        while (itr.hasNext()) {
            Customer customer = itr.next();
            if (customer.completed()) {

                this.stock.replenish(customer);
                customer.rateRide();

                Settings.addRating(customer.getCompany(), customer.getRating());

                this.archives.add(customer);
                itr.remove();

                Console.print("Customer ", customer.getId(), " has completed mission");
            }
        }

    }

    public Stock getStocks() {
        return this.stock;
    }
}
