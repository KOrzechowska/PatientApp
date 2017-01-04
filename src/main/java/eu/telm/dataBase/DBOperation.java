package eu.telm.dataBase;

import eu.telm.models.Pacjent;
import eu.telm.models.RealizacjaBadania;
import eu.telm.models.SlownikOperacji;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by kasia on 10.12.16.
 */
public class DBOperation {
    DBConnection dbConnection;
    Statement statement;
    Connection connection;


    public DBOperation(){
        dbConnection = new DBConnection();
        statement = dbConnection.getStmt();
        connection = dbConnection.getConn();
    }

    public List<SlownikOperacji> getSlownikOperacji() throws SQLException {
        String sql;
        sql = "select nazwa, opis, typ from slownik_operacji;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //preparedStatement.setInt(1,patientId);
        ResultSet rs = preparedStatement.executeQuery();
        List<SlownikOperacji> slownikOperacji = new ArrayList<>();
        SlownikOperacji operacja;
        //STEP 5: Extract data from result set
        while (rs.next()) {
            //Retrieve by column name
            operacja = new SlownikOperacji();
            operacja.setNazwa(rs.getString("nazwa"));
            operacja.setOpis(rs.getString("opis"));
            operacja.setTyp(rs.getString("typ"));


            //Display values
            System.out.print("ID: " + rs.getString("typ"));
            slownikOperacji.add(operacja);
        }
        //STEP 6: Clean-up environment
        rs.close();
        statement.close();
        //connection.close();

        return slownikOperacji;
    }

    public List<Pacjent> getDailyReport(String date) throws SQLException, ParseException {
        String sql;
        sql = "select nazwa, imie, nazwisko, typ, opis from realizacje r join slownik_operacji s on r.operacja_id join pacjenci p on patient_id=p.id = s.id where r.data=?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,date);
        ResultSet rs = preparedStatement.executeQuery();
        List<Pacjent> pacjentList = new ArrayList<>();
        Pacjent pacjent;
        SlownikOperacji slownikOperacji;
        RealizacjaBadania realizacjaBadania;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        while (rs.next()) {
            //Retrieve by column name
            pacjent = new Pacjent();
            pacjent.setImie(rs.getString("imie"));
            pacjent.setNazwisko(rs.getString("nazwisko"));
            slownikOperacji = new SlownikOperacji();
            slownikOperacji.setNazwa(rs.getString("nazwa"));
            slownikOperacji.setOpis(rs.getString("opis"));
            slownikOperacji.setTyp(rs.getString("typ"));
            realizacjaBadania = new RealizacjaBadania();
            realizacjaBadania.setOperacja(slownikOperacji);
            realizacjaBadania.setDate(format.parse(date));
            pacjent.addBadanie(realizacjaBadania);
            //Display values
            System.out.print("ID: " + rs.getString("typ"));
            pacjentList.add(pacjent);
        }
        //STEP 6: Clean-up environment
        rs.close();
        statement.close();

        return pacjentList;

    }


}

