package model;

public class Car {

    String carNumber;
    int OwnerId;

    public Car(String carNumber, int ownerId) {
        this.carNumber = carNumber;
        this.OwnerId = ownerId;

    }

    public Car() {
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public int getOwnerId() {
        return OwnerId;
    }

    public void setOwnerId(int ownerId) {
        OwnerId = ownerId;
    }
}
