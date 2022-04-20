import authorization.ManagerClient;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import java.nio.file.Path;

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
    @Ignore
    public void authentication() {
        String temp = "Вы вошли";
        assertEquals(temp, ManagerClient.apiAuthZ("test", "test"));
    }

    @Ignore
    public void checkLogin() {
        String temp = "Логин занят";
        assertEquals(temp, ManagerClient.apiReg("test", "test"));
    }
}
