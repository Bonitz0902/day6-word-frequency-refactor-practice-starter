import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    private String DELIMITER = "\\s+";
    private String ADD_ONE_WHEN_SPLITTED_INPUT_STR_LENGTH_IS_ONE = " 1";
    private String SPACE_STRING = " ";
    private String NEW_LINE = "\n";
    private String CALCULATE_ERROR = "Calculate Error";

    public String getResult(String inputStr) {


        if (inputStr.split(DELIMITER).length == 1) {
            return inputStr + ADD_ONE_WHEN_SPLITTED_INPUT_STR_LENGTH_IS_ONE;
        } else {

            try {

                //split the input string with 1 to n pieces of spaces
                //String[] arr = inputStr.split(DELIMITER);
                List<String> listOfSplittedInputString = Arrays.stream(inputStr.split(DELIMITER))
                        .collect(Collectors.toList());
                //List<String> listOfInputString = Arrays.stream(inputStr.split())
               /* List<Input> inputList = new ArrayList<>();
                for (String s : arr) {
                    Input input = new Input(s, 1);
                    inputList.add(input);
                }*/

                List<Input> inputList = listOfSplittedInputString.stream()
                        .map(input -> new Input(input, 1))
                        .collect(Collectors.toList());

                //get the map for the next step of sizing the same word
                Map<String, List<Input>> stringListInputMap = getListMap(inputList);

                /*List<Input> list = new ArrayList<>();
                for (Map.Entry<String, List<Input>> entry : stringListInputMap.entrySet()) {
                    Input input = new Input(entry.getKey(), entry.getValue().size());
                    list.add(input);
                }*/

                inputList = stringListInputMap.entrySet().stream()
                        .map(stringListEntry -> new Input(stringListEntry.getKey(), stringListEntry.getValue().size()))
                        .collect(Collectors.toList());
                //inputList = list;

                //inputList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());
                inputList.sort(Comparator.comparingInt(Input::getWordCount).reversed());


                /*for (Input w : inputList) {
                    String s = w.getValue() + SPACE_STRING + w.getWordCount();
                    inputStringJoiner.add(s);
                }*/
                return inputList.stream()
                        .map(input -> String.format("%s %d", input.getValue(), input.getWordCount()))
                        .collect(Collectors.joining(NEW_LINE));
                //return inputStringJoiner.toString();
            } catch (Exception e) {


                return CALCULATE_ERROR;
            }
        }
    }


    private Map<String, List<Input>> getListMap(List<Input> inputList) {
        return inputList.stream()
                .collect(Collectors.groupingBy(Input::getValue));
    }


}
