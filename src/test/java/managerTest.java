import authorization.ManagerClient;
import org.junit.Test;

import static org.junit.Assert.*;

public class managerTest {
    /**
     * //@FIXME изменить с сервлетами связь
     *
     * @see ManagerClient
     */

    public static class managertest {
        @Test
        public void authentication() {
            assertTrue(ManagerClient.apiAuthZ("test", "test"));
        }

        @Test
        public void checkLogin() {
            assertFalse(ManagerClient.apiReg("test", "test"));
        }
    }
}
