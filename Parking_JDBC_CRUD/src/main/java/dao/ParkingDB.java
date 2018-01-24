package dao;

import model.ParkingCard;
import util.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                    .prepareStatement("SELECT * FROM parking_cards");
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
                    .prepareStatement("SELECT * FROM parking_cards WHERE car_number=?");
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
                    .prepareStatement("SELECT parking_cards.id, TAB.name, parking_cards.car_number, parking_cards.start, parking_cards.finish, parking_cards.period, parking_cards.payCheck  FROM  parking_cards, (SELECT cars.car_number, owners.name  FROM cars INNER JOIN owners ON cars.owner_id = owners.id WHERE owners.id=?) AS TAB  WHERE parking_cards.car_number=TAB.car_number");
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
}
