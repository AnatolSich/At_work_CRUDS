package model;

public class Car {

    String carNumber;
    int OwnerId;
    int parkingCard;

    public Car(String carNumber, int ownerId,int parkingCard) {
        this.carNumber = carNumber;
        this.OwnerId = ownerId;
        this.parkingCard = parkingCard;
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

    public int getParkingCard() {
        return parkingCard;
    }

    public void setParkingCard(int parkingCard) {
        this.parkingCard = parkingCard;
    }
}
