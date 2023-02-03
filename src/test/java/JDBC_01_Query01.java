import java.sql.*;

public class JDBC_01_Query01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //1-İlgili driver'ı yüklemeliyiz.Mysql kullandığımızı bildiriyoruz.
        //Driver'i bulamama ihtimaline karşı forname methodu için  ClassNotFoundException
        //method signaturemize axception olarak fırlatmamızı istiyor.
       Class.forName("com.mysql.cj.jdbc.Driver");
        //2- Bağlantıyı oluşturmak için username ve password girmeliyiz.
        //3- getconnection ifadesinin altı kırmızı çiziliyor.Üzerine gelip sqlexception
        //fırlatırız.
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?serverTimezone=UTC", "root", "1234");

       //3-SQl query leri için bir statement objesi oluşturup javada kendimize sql sorgusu yapabilmek için
        // bir alan oluşturacağız.
        Statement st = con.createStatement();

        //4-SQL Query'lerimizi çalıştırabiliriz.
        ResultSet veri = st.executeQuery("SELECT * FROM personel");

       //5-Sonuçları görmek için iteration ile set içindeki elemanları while
        //döngüsüyle yazırırız.
        while (veri.next()){
            System.out.println(veri.getInt(1)+" "+ veri.getString(2)+
                    " "+veri.getString(3)+" "+veri.getInt(4)+" "+veri.getString(5));
        }
      //6- oluşturulan bağlantıları kapatıyoruz.
        con.close();
        st.close();
        veri.close();

    }
}
