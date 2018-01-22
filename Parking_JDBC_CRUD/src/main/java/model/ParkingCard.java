package model;

import java.util.Date;

public class ParkingCard {
    int id;
    Date start;
    Date finish;
    double payCheck;

    public ParkingCard(int id, Date start, Date finish, double payCheck) {
        this.id = id;
        this.start = start;
        this.finish = finish;
        this.payCheck = payCheck;
    }

    public ParkingCard() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getFinish() {
        return finish;
    }

    public void setFinish(Date finish) {
        this.finish = finish;
    }

    public double getPayCheck() {
        return payCheck;
    }

    public void setPayCheck(double payCheck) {
        this.payCheck = payCheck;
    }
}
