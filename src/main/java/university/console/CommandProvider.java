package university.console;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import university.console.commands.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommandProvider {

    public static List<Command> createCommandList(){
        List<Command> commandList = new ArrayList<>();
        commandList.add(new HeadOfDepartment());
        commandList.add(new DepartmentStatistic());
        commandList.add(new AverageSalaryForDepartment());
        commandList.add(new AmountEmployeesForDepartment());
        commandList.add(new GlobalSearch());

        return commandList;
    }

    public static String getCommandsShortList(){
        String commandList = createCommandList().stream()
                .map(Command::getName)
                .map(s -> new StringBuilder(s).insert(0,"-").toString())
                .collect(Collectors.joining("\n"));

        String end = "\n----For finished use \"end\"----";

        return new StringBuilder(commandList).insert(commandList.length(),end).toString();
    }

}
