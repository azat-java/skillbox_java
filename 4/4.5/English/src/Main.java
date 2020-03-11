import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String text = "Harbour Air, which has a fleet of some 40 commuter floatplanes serving the coastal regions around Vancouver, Victoria and Seattle, was the first airline in North America to become carbon-neutral through offsets in 2007. A one-acre green roof on their new Victoria airline terminal followed. Then in 2017, 50 solar panels and four beehives housing 10,000 honeybees were added, but for McDougall, a Tesla owner with an interest in disruptive technology, the big goal was to electrify the fleet.\n" +
                "\n" +
                "McDougall searched for alternative motor options for a couple of years and had put the plan on the backburner when Ganzarski first approached him in February 2019. “He said, ‘We’ve got a motor we want to get certified and we want to fly it before the end of the year,’” McDougall recalls.\n" +
                "\n" +
                "The two companies found their environmental values and teams were a good match and quickly formed a partnership. Eleven months later, the modest Canadian airline got what McDougall refers to as their “e-plane” off the ground, pulling ahead of other electric flight projects, including those by big-name companies Airbus, Boeing and Rolls-Royce.";

        String[] words = text.trim().split("\\s+");
        for (int i = 0; i < words.length; i++) {
            System.out.println(words[i].replaceAll("\\p{Punct}", ""));
        }
    }
}
