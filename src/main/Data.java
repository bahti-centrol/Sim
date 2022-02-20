package main;

import panels.GraphPanel;
import utils.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static utils.Chance.chance;
import static utils.Chance.flip;

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

    public List<Customer> getArchives() {
        return archives;
    }

    public void manhattan() {
        if (chance(Settings.MANHATTAN_SPAWN_RATE)) {

            Routes.Types direction = flip() ? Routes.Types.MANHATTAN_TO_QUEENS : Routes.Types.MANHATTAN_TO_JONES;
            Customer customer = new Customer(direction, company(), ++total);

            this.stock.serve(customer);

            this.customers.add(customer);
            manhattanSpawn++;

            Console.print("Customer ",total," created. Towards ", direction);
        }
    }

    public void queens() {
        if (chance(Settings.QUEENS_SPAWN_RATE)) {

            Routes.Types direction = flip() ? Routes.Types.QUEENS_TO_MANHATTAN : Routes.Types.QUEENS_TO_STONY;
            Customer customer = new Customer(direction, company(), ++total);

            this.stock.serve(customer);

            this.customers.add(customer);
            queensSpawn++;

            Console.print("Customer ",total," created. Towards ", direction);
        }
    }

    public void jones_beach() {
        if (chance(Settings.JONES_BEACH_SPAWN_RATE)) {

            Routes.Types direction = flip() ? Routes.Types.JONES_TO_STONY : Routes.Types.JONES_TO_MANHATTAN;
            Customer customer = new Customer(direction, company(), ++total);

            this.stock.serve(customer);

            this.customers.add(customer);
            jonesSpawn++;

            Console.print("Customer ",total," created. Towards ", direction);
        }
    }

    public void stony_brook() {
        if (chance(Settings.STONY_BROOK_SPAWN_RATE)) {

            Routes.Types direction = flip() ? Routes.Types.STONY_TO_JONES : Routes.Types.STONY_TO_QUEENS;
            Customer customer = new Customer(direction, company(), ++total);

            this.stock.serve(customer);

            this.customers.add(customer);
            stonySpawn++;

            Console.print("Customer ",total," created. Towards ", direction);
        }
    }

    private Routes.Company company() {
        if (chance(Settings.PREFERENCE)) {
            chosenX++;
            return Routes.Company.COMPANY_X;
        }
        else {
            chosenY++;
            return Routes.Company.COMPANY_Y;
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

                Settings.addRating(customer.getCompany().val, customer.getRating());

                this.archives.add(customer);
                itr.remove();

                Console.print("Customer ", customer.getId(), " has completed mission");
            }
            else {
                customer.triggerBreakdown();
            }
        }

    }
}
