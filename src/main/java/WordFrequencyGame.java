import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    private final String DELIMITER = "\\s+";
    private final String ADD_ONE_WHEN_SPLITTED_INPUT_STR_LENGTH_IS_ONE = " 1";
    private final String NEW_LINE = "\n";
    private final String CALCULATE_ERROR = "Calculate Error";

    public String getResult(String inputStr) {


        if (inputStr.split(DELIMITER).length == 1) {
            return inputStr + ADD_ONE_WHEN_SPLITTED_INPUT_STR_LENGTH_IS_ONE;
        } else {
            try {
                List<Input> inputList = Arrays.stream(inputStr.split(DELIMITER))
                        .map(input -> new Input(input, 1))
                        .collect(Collectors.toList());

                Map<String, List<Input>> stringListInputMap = getListMap(inputList);

                inputList = stringListInputMap.entrySet().stream()
                        .map(stringListEntry -> new Input(stringListEntry.getKey(), stringListEntry.getValue().size()))
                        .collect(Collectors.toList());

                inputList.sort(Comparator.comparingInt(Input::getWordCount).reversed());
                return inputList.stream()
                        .map(input -> String.format("%s %d", input.getInputString(), input.getWordCount()))
                        .collect(Collectors.joining(NEW_LINE));

            } catch (Exception e) {
                return CALCULATE_ERROR;
            }
        }
    }

    private Map<String, List<Input>> getListMap(List<Input> inputList) {
        return inputList.stream()
                .collect(Collectors.groupingBy(Input::getInputString));
    }

}
