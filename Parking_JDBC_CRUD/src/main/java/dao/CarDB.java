package dao;

import model.Car;
import util.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDB {
    private Connection connection;

    public CarDB() {
        this.connection = ConnectDB.getConnection();
    }

    public List<Car> getAllCars() {
        List<Car> list = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM cars");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Car car = new Car(resultSet.getString(1), resultSet.getInt(2));
                list.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Car> getCarsByOwnerId(){
        List<Car> list = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM cars WHERE owner_id=?");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Car car = new Car(resultSet.getString(1), resultSet.getInt(2));
                list.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Car getCarByNumber(String number){
        Car car = new Car();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM cars WHERE car_number=?");
            preparedStatement.setString(1,number);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                car.setCarNumber(resultSet.getString(1));
                car.setOwnerId(resultSet.getInt(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return car;
    }

    public void addCar(Car car){
        try {
            PreparedStatement preparedStatement= connection
                    .prepareStatement("INSERT INTO cars (car_number, owner_id) VALUES (?,?)");
            preparedStatement.setString(1,car.getCarNumber());
            preparedStatement.setInt(2,car.getOwnerId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editCar(Car car){
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE cars SET owner_id=?");
            preparedStatement.setInt(1,car.getOwnerId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCarByNumber(String number){
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM cars WHERE car_number=?");
            preparedStatement.setString(1,number);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
