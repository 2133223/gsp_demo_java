import gudusoft.gsqlparser.EDbVendor;
import junit.framework.TestCase;


public class JoinConverterTest extends TestCase {


    public static void testOracleSql1() {
        EDbVendor vendor = EDbVendor.dbvoracle;
        String sql = "INSERT INTO T SELECT e.employee_id,\n" +
                "       e.last_name,\n" +
                "       e.department_id\n" +
                "FROM   employees e,\n" +
                "       departments d\n" +
                "WHERE  e.department_id(+) = d.department_id\n";
        JoinConverter joinConverter = new JoinConverter(sql, vendor);
        assertTrue(joinConverter.convert() == 0);
    }


    public static void testOracleSql2() {
        EDbVendor vendor = EDbVendor.dbvoracle;
        String sql = "SELECT COUNT(1)\n" +
                "  FROM (SELECT PRODID\n" +
                "          FROM (SELECT T.ID PRODID, CD.ID CID\n" +
                "                  FROM PRODUCT          T,                      \n" +
                "                  CD            CD\n" +
                "                 WHERE T.ID=CD.ID)\n" +
                "         GROUP BY PRODID\n" +
                "        HAVING COUNT(*) > 1);\n";
        JoinConverter joinConverter = new JoinConverter(sql, vendor);
        assertTrue(joinConverter.convert() == 0);
    }


    public static void testOracleSql3() {
        EDbVendor vendor = EDbVendor.dbvoracle;
        String sql = "SELECT\n" +
                " *\n" +
                "FROM\n" +
                " ai_course a\n" +
                "WHERE\n" +
                " a.algorithmt(+) = (\n" +
                "  SELECT\n" +
                "   b.coverUrl\n" +
                "  FROM\n" +
                "   ai_course_dataset_relation b, ai_course a\n" +
                "  WHERE\n" +
                "   b.course_id = a.id\n" +
                "   and a.id=?\n" +
                " )";
        JoinConverter joinConverter = new JoinConverter(sql, vendor);
        assertTrue(joinConverter.convert() == 0);
    }


    public static void testOracleSql4() {
        EDbVendor vendor = EDbVendor.dbvoracle;
        String sql = "SELECT e.employee_id,\n" +
                "       e.last_name,\n" +
                "       e.department_id\n" +
                "FROM   employees e,\n" +
                "       departments d\n" +
                "WHERE  e.department_id = d.department_id \n";
        JoinConverter joinConverter = new JoinConverter(sql, vendor);
        assertTrue(joinConverter.convert() == 0);
    }

    public static void testOracleSql5() {
        EDbVendor vendor = EDbVendor.dbvoracle;
        String sql = "CREATE\n" +
                "OR REPLACE PROCEDURE myDemo01 IS aa number (10);\n" +
                "BEGIN\n" +
                "\tSELECT e.employee_id INTO aa FROM employees e, departments d WHERE e.department_id = d.department_id;\n" +
                "\tdbms_output.put_line (aa);\n" +
                "END myDemo01;";
        JoinConverter joinConverter = new JoinConverter(sql, vendor);
        assertTrue(joinConverter.convert() == 0);
    }

    public static void testOracleSql6() {
        EDbVendor vendor = EDbVendor.dbvoracle;
        String sql = "SELECT\n" +
                " *\n" +
                "FROM\n" +
                " ai_course a\n" +
                "WHERE\n" +
                " a.algorithmt(+) = (\n" +
                "  SELECT\n" +
                "   b.coverUrl\n" +
                "  FROM\n" +
                "   ai_course_dataset_relation b, ai_course a\n" +
                "  WHERE\n" +
                "   b.course_id(+) = a.id\n" +
                "   and \n" +
                "   a.id=?\n" +
                " )\n";
        JoinConverter joinConverter = new JoinConverter(sql, vendor);
        assertTrue(joinConverter.convert() == 0);
    }

    public static void testSqlServerSql1() {
        EDbVendor vendor = EDbVendor.dbvmssql;
        String sql = "SELECT  *\n" +
                "FROM    TableA AS A, TableB AS b\n" +
                "WHERE   A.ColA *= B.ColB;";
        JoinConverter joinConverter = new JoinConverter(sql, vendor);
        assertTrue(joinConverter.convert() == 0);
    }

    public static void testSqlServerSql2() {
        EDbVendor vendor = EDbVendor.dbvmssql;
        String sql = "SELECT m.*, \n" +
                "       altname.last_name  last_name_student, \n" +
                "       altname.first_name first_name_student, \n" +
                "       ccu.date_joined, \n" +
                "       ccu.last_login, \n" +
                "       ccu.photo_id, \n" +
                "       ccu.last_updated \n" +
                "FROM   summit.mstr m, \n" +
                "       summit.alt_name altname, \n" +
                "       smmtccon.ccn_user ccu \n" +
                "WHERE  m.id =?\n" +
                "       AND m.id *= altname.id \n" +
                "       AND m.id =* ccu.id \n" +
                "       AND altname.grad_name_ind *= ?\n";
        JoinConverter joinConverter = new JoinConverter(sql, vendor);
        assertTrue(joinConverter.convert() == 0);
    }

    public static void testSqlServerSql3() {
        EDbVendor vendor = EDbVendor.dbvmssql;
        String sql = "if (exists (select * from sys.objects where name = 'GetUser')) drop proc GetUser  \n" +
                "go\n" +
                "create proc GetUser  \n" +
                "@Id int \n" +
                "as \n" +
                "set nocount on;  \n" +
                "begin \n" +
                "\tSELECT e.employee_id,\n" +
                "\t       e.last_name,\n" +
                "\t       e.department_id\n" +
                "\tFROM   employees e,\n" +
                "\t       departments d\n" +
                "\tWHERE  e.department_id *= d.department_id and e.Id=@Id;\n" +
                "end;\n";
        JoinConverter joinConverter = new JoinConverter(sql, vendor);
        assertTrue(joinConverter.convert() == 0);
    }

    public static void testSqlServerSql4() {
        EDbVendor vendor = EDbVendor.dbvmssql;
        String sql = "SELECT\n" +
                " *\n" +
                "FROM\n" +
                " ai_course a\n" +
                "WHERE\n" +
                " a.algorithmt(+) = (\n" +
                "  SELECT\n" +
                "   b.coverUrl\n" +
                "  FROM\n" +
                "   ai_course_dataset_relation b, ai_course a\n" +
                "  WHERE\n" +
                "   b.course_id *= a.id\n" +
                "   and \n" +
                "   a.id=?\n" +
                " )\n";
        JoinConverter joinConverter = new JoinConverter(sql, vendor);
        assertTrue(joinConverter.convert() == 0);
    }

    public static void testSqlServerSql5() {
        EDbVendor vendor = EDbVendor.dbvmssql;
        String sql = "INSERT INTO T select  \n" +
                "    b.Amount\n" +
                "from \n" +
                "    TableA a, TableB b\n" +
                "where \n" +
                "    a.inv_no *= b.inv_no";
        JoinConverter joinConverter = new JoinConverter(sql, vendor);
        assertTrue(joinConverter.convert() == 0);
    }

}