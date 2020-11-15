package university.console.commands;

import org.hibernate.Session;
import university.console.Command;

import java.math.BigDecimal;
import java.util.Formatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static university.util.HibernateUtil.beginSession;

public class AverageSalaryForDepartment implements Command {

    private static final String NAME = "Show the average salary for the department {department_name}.";
    private static final String REGEX = "^Show the average salary for the department (.*).";
    private Matcher matcher;

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public boolean isMatch(String text) {
        Pattern pattern = Pattern.compile(REGEX);
        matcher = pattern.matcher(text);
        return matcher.find();
    }

    @Override
    public String getResult(String text) {
        String departmentName = matcher.group(1);
        Session session = beginSession();

        BigDecimal salary = (BigDecimal) session.createSQLQuery
                ("select avg(lectors.salary)\n" +
                        "from departments_lectors\n" +
                        "join departments on departments.id=department_id\n" +
                        "join lectors on lectors.id=lector_id\n" +
                        "where departments.name=:name")
                .setParameter("name", departmentName)
                .uniqueResult();
        return new Formatter().format("The average salary of %s is %s", departmentName, salary).toString();
    }
}
