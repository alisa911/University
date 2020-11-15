package university.console.commands;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import university.console.Command;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static university.util.HibernateUtil.beginSession;

@RequiredArgsConstructor
public class DepartmentStatistic implements Command {

    private static final String NAME = "Show {department_name} statistics.";
    private static final String REGEX = "^Show (.*) statistics.";
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

        List<?> degrees = session.createSQLQuery
                ("select degrees.name\n" +
                        "from degrees").list();

        Map<String, BigInteger> mapDegrees = degrees.stream()
                .collect(Collectors.toMap(Object::toString,
                        degree -> countLectorsByDegree(departmentName, degree.toString(), session)));

        return mapDegrees.keySet().stream()
                .map(key -> key + "-" + mapDegrees.get(key))
                .collect(Collectors.joining("\n"));
    }

    private BigInteger countLectorsByDegree(String departmentName, String degree, Session session) {
        return (BigInteger) session.createSQLQuery
                ("select count(degrees.name)\n" +
                        "from departments_lectors\n" +
                        "join departments on departments.id=department_id\n" +
                        "join lectors on lectors.id=lector_id\n" +
                        "join degrees on degrees.id=lectors.degree_id\n" +
                        "where departments.name=:name and degrees.name=:degree")
                .setParameter("name", departmentName)
                .setParameter("degree", degree)
                .uniqueResult();
    }
}
