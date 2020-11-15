package university;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import university.console.Command;
import university.util.HibernateUtil;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import static university.console.CommandProvider.createCommandList;
import static university.console.CommandProvider.getCommandsShortList;

@RequiredArgsConstructor
public class UniversityApplication {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Logger logger = Logger.getLogger(UniversityApplication.class.getName());
    private static final List<Command> commandList = createCommandList();

    public static void main(String[] args) {
        disableWarning();
        Session session = HibernateUtil.getSessionFactory().openSession();
        logger.info("Hello!");
        logger.info("You can use commands:");
        logger.info(getCommandsShortList());
        logger.info("To show this help message type \"Show commands\"");
        run();
        session.close();
        System.exit(1);
    }

    public static void run() {
        while (scanner.hasNext()) {
            String text = scanner.nextLine();
            if (text.equals("end")) {
                scanner.close();
                break;
            } else if (text.equals("Show commands")) {
                logger.info(getCommandsShortList());
            } else {

                commandList.stream()
                        .filter(command -> command.isMatch(text))
                        .findFirst().map(command -> {
                    logger.info(command.getResult(text));
                    return command;
                }).orElseGet(() -> {
                    logger.info("Wrong command!");
                    return null;
                });
            }
        }
    }

    private static void disableWarning() {
        System.err.close();
        System.setErr(System.out);
    }
}
