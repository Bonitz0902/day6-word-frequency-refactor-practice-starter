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
                Map<String, List<Input>> stringListInputMap = Arrays.stream(inputStr.split(DELIMITER))
                        .map(input -> new Input(input, 1))
                        .collect(Collectors.groupingBy(Input::getInputString));

                return stringListInputMap.entrySet().stream()
                        .map(entry -> new Input(entry.getKey(), entry.getValue().size()))
                        .sorted(Comparator.comparingInt(Input::getWordCount).reversed())
                        .map(input -> String.format("%s %d", input.getInputString(), input.getWordCount()))
                        .collect(Collectors.joining(NEW_LINE));

            } catch (Exception e) {
                return CALCULATE_ERROR;
            }
        }
    }

}
