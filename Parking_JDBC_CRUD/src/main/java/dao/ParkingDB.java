package dao;

import model.ParkingCard;
import util.ConnectDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingDB {
    private Connection connection;

    public ParkingDB() {
        this.connection = ConnectDB.getConnection();
    }

    public List<ParkingCard> getAllparkingCards() {
        List<ParkingCard> list = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM parking_cards ORDER BY id");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ParkingCard parkingCard = new ParkingCard(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getDate(3), resultSet.getDate(4), resultSet.getInt(5),
                        resultSet.getDouble(6));
                list.add(parkingCard);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<ParkingCard> getParkingCardsByCarNumber(String number) {
        List<ParkingCard> list = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM parking_cards WHERE car_number=? ORDER BY id");
            preparedStatement.setString(1, number);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ParkingCard parkingCard = new ParkingCard(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getDate(3), resultSet.getDate(4), resultSet.getInt(5),
                        resultSet.getDouble(6));
                list.add(parkingCard);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Map<ParkingCard, String> getParkingCardsByOwnerId(int id) {
        Map<ParkingCard, String> map = new HashMap<>();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT " +
                            "parking_cards.id, TAB.name, parking_cards.car_number, parking_cards.start, " +
                            "parking_cards.finish, parking_cards.period, parking_cards.payCheck  " +
                            "FROM  parking_cards, (SELECT cars.car_number, owners.name  " +
                            "FROM cars INNER JOIN owners ON cars.owner_id = owners.id " +
                            "WHERE owners.id=?) AS TAB  " +
                            "WHERE parking_cards.car_number=TAB.car_number ORDER BY parking_cards.id");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ParkingCard parkingCard = new ParkingCard(resultSet.getInt(1), resultSet.getString(3),
                        resultSet.getDate(4), resultSet.getDate(5), resultSet.getInt(6),
                        resultSet.getDouble(7));
                map.put(parkingCard, resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    public ParkingCard getParkingCardById(int id) {
        ParkingCard parkingCard = null;
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM parking_cards WHERE id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                parkingCard = new ParkingCard(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getDate(3), resultSet.getDate(4), resultSet.getInt(5),
                        resultSet.getDouble(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return parkingCard;
    }

    public void addParkingcard(ParkingCard parkingCard) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO parking_cards (car_number, start, finish, period, paycheck) VALUES (?,?,?,?,?)");
            preparedStatement.setString(1, parkingCard.getCarNumber());
            preparedStatement.setDate(2, new Date(parkingCard.getStart().getTime()));
            preparedStatement.setDate(3, new Date(parkingCard.getFinish().getTime()));
            preparedStatement.setInt(4, parkingCard.getPeriod());
            preparedStatement.setDouble(5, parkingCard.getPayCheck());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editParkingCard(ParkingCard parkingCard) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE parking_cards SET car_number=?,start=?,finish=?,period=?,paycheck=?");
            preparedStatement.setString(1, parkingCard.getCarNumber());
            preparedStatement.setDate(2, new Date(parkingCard.getStart().getTime()));
            preparedStatement.setDate(3, new Date(parkingCard.getFinish().getTime()));
            preparedStatement.setInt(4, parkingCard.getPeriod());
            preparedStatement.setDouble(5, parkingCard.getPayCheck());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteParkingCardById(int id) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM parking_cards WHERE id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
