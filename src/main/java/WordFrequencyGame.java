import java.util.*;

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
                String[] arr = inputStr.split(DELIMITER);
                //List<String> listOfInputString = Arrays.stream(inputStr.split())
                List<Input> inputList = new ArrayList<>();
                for (String s : arr) {
                    Input input = new Input(s, 1);
                    inputList.add(input);
                }

                //get the map for the next step of sizing the same word
                Map<String, List<Input>> map = getListMap(inputList);

                List<Input> list = new ArrayList<>();
                for (Map.Entry<String, List<Input>> entry : map.entrySet()) {
                    Input input = new Input(entry.getKey(), entry.getValue().size());
                    list.add(input);
                }
                inputList = list;

                inputList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

                StringJoiner joiner = new StringJoiner(NEW_LINE);
                for (Input w : inputList) {
                    String s = w.getValue() + SPACE_STRING + w.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {


                return CALCULATE_ERROR;
            }
        }
    }


    private Map<String, List<Input>> getListMap(List<Input> inputList) {
        Map<String, List<Input>> map = new HashMap<>();
        for (Input input : inputList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(input.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(input);
                map.put(input.getValue(), arr);
            } else {
                map.get(input.getValue()).add(input);
            }
        }


        return map;
    }


}
