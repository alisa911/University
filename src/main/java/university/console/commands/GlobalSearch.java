package university.console.commands;

import org.hibernate.Session;
import university.console.Command;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static university.util.HibernateUtil.beginSession;

public class GlobalSearch implements Command {

    private static final String NAME = "Global search by {template}.";
    private static final String REGEX = "^Global search by (.*).";
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
        String template = matcher.group(1).toLowerCase();
        Session session = beginSession();

        List<?> result = session.createSQLQuery
                ("select lectors.name " +
                        "from lectors " +
                        "where lower(name) like :template")
                .setParameter("template", "%" + template + "%").list();
        return result.toString().replaceAll("[\\[\\]]", "");
    }
}
