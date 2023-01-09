import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author k 2022/12/19 16:06
 */
public class SpringApp {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        Object person = context.getBean("person");
        System.out.println(person);
    }
}
