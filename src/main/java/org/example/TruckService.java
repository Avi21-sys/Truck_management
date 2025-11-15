package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TruckService {
    public void addTruck(Trucks trucks){
        String query = "insert into trucks(name,model,capacity,driver_name) values(?,?,?,?)";
        try{
            Connection connection = ConnectionDetails.connection();
            PreparedStatement prepareStatement= connection.prepareStatement(query);
            prepareStatement.setString(1, trucks.getName());
            prepareStatement.setString(2, trucks.getModel());
            prepareStatement.setInt(3, trucks.getCapacity());
            prepareStatement.setString(4, trucks.getDriver_name());

            int update = prepareStatement.executeUpdate();
            System.out.println("Row inserted: "+update);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public Trucks getTruckById(int id){
        String query = "select * from trucks where id=?";
        Trucks trucks =new Trucks();
        try{
            Connection connection = ConnectionDetails.connection();
            PreparedStatement preparedStatement =connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                trucks.setId(resultSet.getInt("id"));
                trucks.setName(resultSet.getString("name"));
                trucks.setModel(resultSet.getString("model"));
                trucks.setCapacity(resultSet.getInt("capacity"));
                trucks.setDriver_name(resultSet.getString("driver_name"));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return trucks;
    }

    public void updateTruck(Trucks trucks){
        String query ="update trucks set name=?, model=?, capacity=?, driver_name=? where id=?";
        try{
            Connection connection = ConnectionDetails.connection();
            PreparedStatement preparedStatement =connection.prepareStatement(query);
            preparedStatement.setString(1, trucks.getName());
            preparedStatement.setString(2, trucks.getModel());
            preparedStatement.setInt(3, trucks.getCapacity());
            preparedStatement.setString(4, trucks.getDriver_name());
            preparedStatement.setInt(5, trucks.getId());

            int update = preparedStatement.executeUpdate();
            System.out.println("Row updated: "+update);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Trucks> getAllTrucks(){
        String query = "select * from trucks";
        List<Trucks> trucksList = new ArrayList<>();
        try{
            Connection connection = ConnectionDetails.connection();
            Statement statement =connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                Trucks trucks=new Trucks();
                trucks.setId(resultSet.getInt("id"));
                trucks.setName(resultSet.getString("name"));
                trucks.setModel(resultSet.getString("model"));
                trucks.setCapacity(resultSet.getInt("capacity"));
                trucks.setDriver_name(resultSet.getString("driver_name"));
                trucksList.add(trucks);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return trucksList;
    }

    public void deleteTruck(int id){
        String query= "delete from trucks where id=?";
        try{
            Connection connection = ConnectionDetails.connection();
            PreparedStatement preparedStatement =connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            int deletion = preparedStatement.executeUpdate();
            System.out.println("Row deleted: "+deletion);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
