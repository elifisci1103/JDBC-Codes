import java.sql.*;

public class JDBC02_Execute_ExecuteUpdate {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        /*
 	A) CREATE TABLE, DROP TABLE, ALTER TABLE gibi DDL ifadeleri icin sonuc kümesi (ResultSet)
 	   dondurmeyen metotlar kullanilmalidir. Bunun icin JDBC'de 2 alternatif bulunmaktadir.

 	    1) execute() metodu boolean döndürür
 	    2) executeUpdate() metodu. int değer döndürür

 	B) - execute() metodu her tur SQL ifadesiyle kullanibilen genel bir komuttur.
 	   - execute(), Boolean bir deger dondurur. DDL islemlerinde false dondururken,
 	     DML islemlerinde true deger dondurur.
 	   - Ozellikle, hangi tip SQL ifadesine hangi methodun uygun olduğunun bilinemediği
 	     durumlarda tercih edilmektedir.

 	C) - executeUpdate() metodu ise INSERT, Update gibi DML islemlerinde yaygin kullanilir.
 	   - bu islemlerde islemden etkilenen satir sayisini dondurur.
 	   - Ayrıca, DDL islemlerinde de kullanilabilir ve bu islemlerde 0 dondurur.*/

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?serverTimezone=UTC", "root", "1234");
        Statement st = con.createStatement();

      /*  -------------------------------------------------------------
      Soru 1-cucuklar tablosunu siliniz.
       _________________________________________________________________*/

        String dropQuery = "Drop table cucuklar";
        System.out.println(st.execute(dropQuery));

        /*=======================================================================
          ORNEK2: isciler adinda bir tablo olusturunuz id int,
          birim VARCHAR(10), maas int
	    ========================================================================*/
       String createTable = "CREATE TABLE isciler" + "(id INT," +
              "birim Varchar(10)," + "maas INT)";
               System.out.println(st.execute(createTable));

        if(!st.execute(createTable)){
        System.out.println("İsciler tablosu oluşturuldu.");}

        /*=========================================================================
        ORNEK3:İsciler tablosuna yeni bir kayıt ekleyelim.(80,'ARGE',4000)
        ============================================================================ */

        String insertData = "insert into isciler values(80,'ARGE',4000)";
        System.out.println("İslemden etkilenen satır sayısı:"+ st.executeUpdate(insertData));

        /*=========================================================================
        ORNEK3:İsciler tablosuna birden fazla  kayıt ekleyelim.(70,'HR',5000)
                                                               (60,'LAB',3000)
                                                               (50,'ARGE',4000)

        ============================================================================ */
        String [] queries ={"insert into values(70,'HR',5000)","insert into values(60,'LAB',3000)","insert into values(50,'ARGE',4000)"};
        int count=0;

        for(String each : queries){
        count += st.executeUpdate(each);}

        System.out.println(count + "satır eklendi");

        // Ayri ayri sorgular ile veritabanina tekrar tekrar ulasmak islemlerin
        // yavas yapilmasina yol acar. 10000 tane veri kaydi yapildigi dusunuldugunde
        // bu kotu bir yaklasimdir.
        // System.out.println("=============== 2. Yontem ==============");

        // 2.YONTEM (addBatch ve executeBatch() metotlari ile)
        // ----------------------------------------------------
        // addBatch metodu ile SQL ifadeleri gruplandirilabilir ve executeBatch()
        // metodu ile veritabanina bir kere gonderilebilir.
        // executeBatch() metodu bir int [] dizi dondurur. Bu dizi her bir ifade sonucunda
        // etkilenen satir sayisini gosterir.

        String [] queries2 = {"INSERT INTO isciler VALUES(10, 'TEKNIK', 3000)",
                             "INSERT INTO isciler VALUES(20, 'KANTIN', 2000)",
                              "INSERT INTO isciler VALUES(30, 'ARGE', 5000)"};

            for(String each : queries2){
             st.addBatch(each);} //Herbir sql komutunu alıp torbaya atıyor.
             st.executeBatch();  //O torbadakileri götürüp Database işliyor.
              System.out.println("Satırlar eklendi");

          /*======================================================================
          İsciler tablosunu görüntüleyin.
           =======================================================================*/
         System.out.println("==============İsciler Tablosu===============");
         String SelectQuery="Select * from isciler";
         ResultSet iscilertablosu = st.executeQuery(SelectQuery);
          while(iscilertablosu.next()){
        System.out.println(iscilertablosu.getInt(1)+ " " +iscilertablosu.getString(2)+ " " + iscilertablosu.getInt(3));}

          /*=======================================================================
           ORNEK6: isciler tablosundaki maasi 5000'den az olan iscilerin maasina
            %10 zam yapiniz
           ========================================================================*/

          String updateQuery="update isciler set maas=maas*1.10 where maas<5000";
          int satir = st.executeUpdate(updateQuery);
          System.out.println(satir+"satır güncellendi");

          /*======================================================================
         İsciler tablosunun son halini görüntüleyin.
          =======================================================================*/

         System.out.println("==============İsciler Tablosu===============");
         String SelectQuery2="Select * from isciler";
         ResultSet iscilertablosu2 = st.executeQuery(SelectQuery2);
         while(iscilertablosu2.next()){
             System.out.println(iscilertablosu2.getInt(1)+ " " +iscilertablosu2.getString(2)+ " " + iscilertablosu2.getInt(3));}

         /*=======================================================================
         ORNEK8: Isciler tablosundan birimi 'ARGE' olan iscileri siliniz.
          ========================================================================*/

        String deleteQuery ="Delete from isciler where birim='ARGE' ";
        int silinen = st.executeUpdate(deleteQuery);
        System.out.println(silinen + "satır silindi");

        st.close();
        con.close();
        iscilertablosu.close();
        iscilertablosu2.close();



    }}



