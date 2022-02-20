package utils;

import java.util.Set;

import static utils.Chance.*;

public class Customer {

    private Routes.Types direction;
    private Routes.Company company;

    private int started;
    private int duration;

    private double intialPref;

    private int id;

    private boolean fail;
    private boolean breakdown;
    private int brokeTime;

    private int score;

    public Customer(Routes.Types direction, Routes.Company company, int id) {
        this.direction = direction;
        this.company = company;
        this.id = id;

        this.fail = false;
        this.breakdown = false;

        this.duration = range(Routes.durations[direction.val]);
        this.started = Settings.EVENT_COUNTER;
        this.intialPref = Settings.PREFERENCE;
    }

    public int getRating() {
         return score;
    }

    public Routes.Company getCompany() {
        return company;
    }

    public Routes.Types getDirection() {
        return direction;
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

        double quality = company == Routes.Company.COMPANY_X ? Settings.COMP_X_QUALITY : Settings.COMP_Y_QUALITY;

        if (chance((1.0-quality) / this.duration)) {
            Console.print("C",id,"'s scooter broke down... bad");
            this.breakdown = true;
            this.brokeTime = Settings.EVENT_COUNTER;
        }

    }

    public boolean completed() {
        return this.fail || this.breakdown || Settings.EVENT_COUNTER >= this.started + this.duration;
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
        builder.append(id).append(',').append(intialPref).append(',').append(score).append(',').append(company)
                .append(',').append(direction.start).append(',').append(direction.end).append(',')
                .append(started).append(',').append(started+duration).append(',').append(this.fail).append(',')
                .append(this.breakdown).append(',').append(brokeTime);

        return builder.toString();
    }

    public String toString() {
        return "cid=" + id + ", tL=" + duration + " dir=" + direction.val + " c=" + company.val + " " + this.fail + " " + this.breakdown + " " + score;
    }
}
