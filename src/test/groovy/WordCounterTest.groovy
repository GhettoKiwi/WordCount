import org.example.model.WordCounter;
import spock.lang.Specification;

import java.util.*;

class WordCounterTest extends Specification {

    void "Basic word count - With special characters and cases."() {
        given:
        String testLines ="Lorem SIT ipsum dolor sit amet, IPsuM loREM.\n" +
        "siT IPsuM, SIt! DolOR?"

        when:
        TreeMap testMap = WordCounter.count(testLines);

        then:
        testMap == [
            amet: 1,
            dolor: 2,
            ipsum: 3,
            lorem: 2,
            sit: 4
        ]
    }
    void "Basic word count - Empty"() {
        given:
        String emptyLines =""

        when:
        TreeMap testMap = WordCounter.count(emptyLines);

        then:
        testMap == null
    }

    void "Getting excluded word count"() {
        given:
        TreeMap<String, Integer> countedWords = [amet: 2, dolor: 25, ipsum: 29, lorem: 1, sit: 7]
        String[] exclusions = ["sit", "dolor"];

        when:
        TreeMap testMap = WordCounter.getExclusionCounts(countedWords, exclusions)
        then:
        testMap == [ dolor: 25, sit: 7]
    }

    void "Getting excluded word count - No Exclusions"() {
        given:
        TreeMap<String, Integer> countedWords = [amet: 2, dolor: 25, ipsum: 29, lorem: 1, sit: 7]
        String[] exclusions = [];

        when:
        TreeMap testMap = WordCounter.getExclusionCounts(countedWords, exclusions)
        then:
        testMap == null
    }

    void "Getting included word counts"() {
        given:
        TreeMap<String, Integer> countedWords = [amet: 2, dolor: 25, ipsum: 29, lorem: 1, sit: 7]
        String[] exclusions = ["sit", "dolor"];

        when:
        TreeMap testMap = WordCounter.removeExclusionCounts(countedWords, exclusions)
        then:
        testMap == [ amet: 2, ipsum: 29, lorem: 1 ]
    }

    void "Getting included word counts - No words"() {
        given:
        String[] exclusions = ["sit", "dolor"];
        TreeMap<String, Integer> countedWords = []

        when:
        TreeMap testMap = WordCounter.removeExclusionCounts(countedWords, exclusions)
        then:
        testMap == null
    }
}
