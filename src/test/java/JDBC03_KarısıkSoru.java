import com.mysql.cj.protocol.Resultset;

import java.sql.*;

public class JDBC03_KarısıkSoru {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?serverTimezone=UTC", "root", "1234");
        Statement st = con.createStatement();
        /*=========================================================
         soru1-Cocuklar tablosunu siliniz.
        ============================================================= */
        //String dropQuery ="drop table puanlar";
       // System.out.println(st.execute(dropQuery));
        String dropQuery ="drop table cocuklar";
        System.out.println(st.execute(dropQuery));


        /*=========================================================
         soru2-Cocuklar tablosunu create ediniz.
        ============================================================= */
        String createTable = "CREATE TABLE cocuklar" + "(id INT," +
                "isim Varchar(25)," + "grade INT)";
        System.out.println(st.execute(createTable));


        /*=========================================================
         soru3-Cocuklar tablosuna   (123,'Ali can,69 )  bilgilerini insert  ediniz.
        ============================================================= */
        String insertQuery = "insert into cocuklar values (123,'Ali Can',69)";
        System.out.println(st.executeUpdate(insertQuery));


        /*=========================================================
         soru4-Cocuklar tablosuna bilgileri insert ediniz.(124,'Merve Gül',65)
                                                          (125, 'Kemal Yasa',93)
                                                          (126, 'Rümeysa Aydın',87)
        ============================================================= */
         String [] insertQuery2 = {"insert into cocuklar values (124,'Merve Gül',65)" , "insert into cocuklar values (125, 'Kemal Yasa',93)" ,
                 "insert into cocuklar values (126, 'Rümeysa Aydın',87)"};

        for (String each:insertQuery2) {
            st.addBatch(each);}
            st.executeBatch();
        System.out.println("Satırlar eklendi");


        /*=========================================================
         soru5-Cocuklar tablosunu  listeleyiniz.
        ============================================================= */
        String selectQuery = "select * from cocuklar ";
        ResultSet data = st.executeQuery(selectQuery);
        while(data.next()){
            System.out.println(data.getInt(1)+" "+data.getString(2)+" "+data.getInt(3));}


         /*=========================================================
         soru6-Cocuklar tablosunda grade 80 den büyük olanları  listeleyiniz.
        ============================================================= */
         String selectQuery2 = "select * from cocuklar where grade>80";
         ResultSet data2 = st.executeQuery(selectQuery2);
         while(data2.next()){
             System.out.println(data2.getInt(1)+" "+data2.getString(2)+" "+data2.getInt(3));}


         /*=========================================================
         soru7-Cocuklar tablosunda ismi Ali can olan kişinin ismini veli cem olarak update ediniz.
        ============================================================= */
          String updateQuery = "update cocuklar  set isim ='Veli Cem'  where isim = 'Ali Can'";
          int sayı=st.executeUpdate(updateQuery);
        System.out.println(sayı + " adet satır etkilendi");


         /*=========================================================
         soru8-Cocuklar tablosunda id si 125 ten büyük olanları  siliniz.
        ============================================================= */
        String deleteQuery ="delete from cocuklar where id>125";
        int adet=st.executeUpdate(deleteQuery);
        System.out.println(adet + " adet satır etkilendi");


        /*=========================================================
         soru9-Cocuklar tablosunun son halini  listeleyiniz.
        ============================================================= */
        String selectQuery3 = "select * from cocuklar ";
        ResultSet data3 = st.executeQuery(selectQuery);
        while(data3.next()){
            System.out.println(data3.getInt(1)+" "+data3.getString(2)+" "+data3.getInt(3));}
        con.close();
        st.close();
        data.close();
        data2.close();
        data3.close();

    }}


