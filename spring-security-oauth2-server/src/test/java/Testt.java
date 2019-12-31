import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2019/12/26 10:14
 */
public class Testt {

    /*public static void main(String[] args) {
        List<String> list = Stream.of("abc", "dfg", "erwe", "sdfs").collect(Collectors.toList());
        for (String str : list) {
            System.out.println(str);
        }
    }*/

    public static void main(String[] args) {
        TreeSet<String> set = new TreeSet<>();
        set.add("qwe");
        set.add("sfs");
        set.add("dfg");
        set.add("3ghf54");

        for (String str : set) {
            System.out.println(str);
        }

        Comparator comparator = set.comparator();

        System.out.println(111);

//        Map<String, String> map = new TreeMap<>();
//        map.put("123", "234");
//        map.put("ert", "cvs");
//        for (Map.Entry<String, String> entry : map.entrySet()) {
//
//        }
    }
}
