
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import ru.ikbo1018.app.models.account.Account;
import ru.ikbo1018.app.models.account.User;
import ru.ikbo1018.app.validators.AccountValidator;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"file:src/main/resources/springServicesConfiguration.xml",
"file:src/main/resources/springWebConfiguration.xml",
"file:src/main/resources/springDataConfiguration.xml"}
)
public class TestCases {

    @Test
    public void testCase1_1_1() {
        Account account = new User();
        AccountValidator validator = new AccountValidator();
        Errors errors = new BindException(account, "account");
        validator.validate(account, errors);
        Assert.assertEquals(4, errors.getErrorCount());
    }

    @Test
    public void testCase1_1_2() {
        Account account = new User();
        account.setFirstName("123");
        account.setLastName("abc");
        account.setEmail("adassa@mail.ru");
        account.setPassword("   adsda");
        AccountValidator validator = new AccountValidator();
        Errors errors = new BindException(account, "account");
        validator.validate(account, errors);
        Assert.assertEquals(2, errors.getErrorCount());
        account.setFirstName("normal");
        account.setPassword("qwerty");
        errors = new BindException(account, "account");
        validator.validate(account, errors);
        Assert.assertEquals(0, errors.getErrorCount());
    }

}
