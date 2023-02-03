import java.sql.*;

public class JDBC02_Query2 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?serverTimezone=UTC", "root", "1234");
        Statement st = con.createStatement();
      /*  -------------------------------------------------------------
      Soru 1-Id si 1 den büyük firmaların ismini ve iletişim ismini isim ters sıralı yazdırın.

       */

        System.out.println("====================================================================================");

        String selectquery="SELECT isim , iletisim_isim " +
                "FROM firmalar " + "WHERE id>1 " + "ORDER BY isim desc ";
        ResultSet data = st.executeQuery( selectquery);

        while(data.next()){
            System.out.println(data.getString("isim")+" "+data.getString("iletisim_isim"));}


            System.out.println("================================================================================");

          /*
            Soru 2-İletisim isminde 'li' içeren firmaların id lerini ve ismini id sıralı yazdırın.

          */
            String select2="Select id , isim " + "from firmalar " + "where iletisim_isim like '%li%' " +
                    "order by id ";
            ResultSet data2 = st.executeQuery(select2);
            while(data2.next()){
                System.out.println(data2.getInt("id")+" "+data2.getString("isim"));}

        // NOT1 : Sorgulama icin get ile istenirse sütun (field) ismini yazabilecegimiz gibi sutun index
        // (field olusturulma sirasina gore) yazilabilir.

        // NOT2 : Sorgumuzda SELECT'ten sonra sadece belli fieldlari dondurmesini istiyorsak
        // get ile cagirdigimiz field indexleri sorguda belirttigimiz sirayla ifade etmemiz gerekiyor


        con.close();
        st.close();
        data.close();
        data2.close();



}}
