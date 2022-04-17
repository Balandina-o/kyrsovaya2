import authorization.ManagerClient;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class managerTest {
    /**
     * //@FIXME изменить с сервлетами связь
     *
     * @see ManagerClient
     */

    @Test
    public void authentication() {
        String temp = "Вы вошли";
        assertEquals(temp, ManagerClient.apiAuthZ("test", "test"));
    }

    @Test
    public void checkLogin() {
        String temp = "Логин занят";
        assertEquals(temp, ManagerClient.apiReg("test", "test"));
    }
}
