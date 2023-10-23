import org.example.controller.Controller;
import spock.lang.Specification;

import java.util.TreeMap;

class ControllerTest extends Specification {

    void "Sort of treemap based on first character."() {
        given:
        TreeMap<String, Integer> testMap = [
            amet: 1,
            dolor: 2,
            dala: 1,
            ipsum: 3,
            ital: 2,
            lorem: 2,
            sit: 4
        ]
        Controller controller = new Controller();
        when:
        TreeMap<Character, String> sortedMap = controller.sortWordsOnCharacter(testMap);

        then:
        sortedMap.size() == 5
        sortedMap.get('a' as char) == "amet 1\n"
        sortedMap.get('d' as char) == "dala 1\ndolor 2\n"
        sortedMap.get('i' as char) == "ipsum 3\nital 2\n"
        sortedMap.get('l' as char) == "lorem 2\n"
        sortedMap.get('s' as char) == "sit 4\n"
        sortedMap.get('p' as char) == null
    }
}
