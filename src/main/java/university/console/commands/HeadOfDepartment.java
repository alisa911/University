package university.console.commands;

import org.hibernate.Session;
import university.console.Command;

import java.util.Formatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static university.util.HibernateUtil.beginSession;

public class HeadOfDepartment implements Command {

    private static final String NAME = "Who is head of department {department_name}";
    private static final String REGEX = "^Who is head of department (.*)$";
    private Matcher matcher;

    @Override
    public String getName() {
        return NAME;
    }

    public boolean isMatch(String text) {
        Pattern pattern = Pattern.compile(REGEX);
        matcher = pattern.matcher(text);
        return matcher.find();
    }

    public String getResult(String text) {
        String departmentName = matcher.group(1);
        Session session = beginSession();

        String head = (String) session.createSQLQuery
                ("select lectors.name\n" +
                        "from departments\n" +
                        "left join lectors on lectors.id=head_id\n" +
                        "where departments.name=:name")
                .setParameter("name", departmentName)
                .uniqueResult();
        return new Formatter().format("Head of %s department is %s", departmentName, head).toString();
    }
}
