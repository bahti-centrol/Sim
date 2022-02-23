package utils;

import routes.Route;

import static utils.Chance.*;

public class Customer {

    private Route route;
    private int company;

    private int started;
    private int duration;

    private final double initialPref;

    private int id;

    private boolean fail;
    private boolean breakdown;
    private int brokeTime;

    private int score;

    public Customer(Route route, int id, int company) {
        this.route = route;
        this.id = id;

        this.company = company;

        this.fail = false;
        this.breakdown = false;

        this.duration = range(route.getExpected());
        this.started = Settings.EVENT_COUNTER;
        this.initialPref = Settings.PREFERENCE;

        Console.print("i am ",id," with company ",company);

        triggerBreakdown();
    }

    public int getStarted() {
        return started;
    }

    public int getRating() {
         return score;
    }

    public int getCompany() {
        return company;
    }

    public Route getRoute() {
        return route;
    }

    public void forceFail() {
        this.fail = true;
    }

    public int rateRide(){

        if (this.fail)
            return this.score = random(0, 1);

        if (this.breakdown)
            return this.score = (int) (10 * ((this.brokeTime - this.started) / (this.duration * 1.0)));

        return this.score = random(8, 10);
    }

    public void triggerBreakdown() {

        double quality = company == 0 ? Settings.COMP_X_QUALITY : Settings.COMP_Y_QUALITY;

        if (chance(1.0-quality)) {
            Console.print("C",id,"'s scooter is expected to break down");

            this.breakdown = true;
            this.brokeTime = random(1, this.duration) + Settings.EVENT_COUNTER;
            Console.print("c",id," time ",brokeTime);
        }

    }

    public boolean completed() {
        return this.fail || (this.breakdown && Settings.EVENT_COUNTER >= this.brokeTime)
                         || Settings.EVENT_COUNTER >= this.started + this.duration;
    }

    public boolean failed(){
        return this.fail;
    }

    public int getDuration() {
        return this.duration;
    }

    public int getId() {
        return this.id;
    }

    public String csv() {

        StringBuilder builder = new StringBuilder();

        builder.append(id).append(',').append(initialPref).append(',').append(score).append(',').append(company)
                .append(',').append(route.getStart()).append(',').append(route.getEnd()).append(',')
                .append(started).append(',').append(started+duration).append(',').append(this.fail).append(',')
                .append(this.breakdown).append(',').append(brokeTime);

        return builder.toString();
    }

    public String toString() {
        return "cid=" + id + ", tL=" + duration + " dir=" + route.getId() + " c=" + company + " " + this.fail + " " + this.breakdown + " " + score;
    }
}
