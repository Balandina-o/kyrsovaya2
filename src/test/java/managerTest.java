import authorization.ManagerClient;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class managerTest {
    /**
     * //@FIXME изменить с сервлетами связь
     *
     * @see ManagerClient
     */
@Before
public void init(){
    //System.out.println(Files.createFile(Path.of("CHECK_DIR")).toAbsolutePath());
    // Path path = Path.of("CHECK_DIR");
    //System.out.println(path.toAbsolutePath());
}
    @Test
    public void authentication() {
        String temp = "Вы вошли USER";
        var x =ManagerClient.apiAuthZ("test", "test");
        String str = String.format("%s %s",x.get(0),x.get(1));
        assertEquals(temp,str);
    }
    @Test
    public void authAdm(){
        String temp = "Вы вошли ADMIN";
        var x = ManagerClient.apiAuthZ("admin","admin");
        String str = String.format("%s %s",x.get(0),x.get(1));
        assertEquals(temp,str);
    }
    @Test
    public void checkLogin() {
        String temp = "Логин занят";
        assertEquals(temp, ManagerClient.apiReg("test", "test"));
    }
}
