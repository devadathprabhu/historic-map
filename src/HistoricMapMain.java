public class HistoricMapMain {
    public static void main(String[] args){
        HistoricMap<String, String> historicMap = new HistoricMapImpl<>();
        historicMap.put("a", "a0");
        historicMap.snapshot();
        historicMap.put("b", "b1");
        historicMap.put("a", "a1");
        historicMap.snapshot();
        historicMap.put("b", "b2.0");
        historicMap.put("b", "b2.1");
        historicMap.snapshot();
        historicMap.put("b", "b3");

        System.out.println("a at snapshot 0: " + historicMap.getAt("a", 0));
        System.out.println("a at snapshot 1: " + historicMap.getAt("a", 1));
        System.out.println("a at snapshot 2: " + historicMap.getAt("a", 2));
        System.out.println("a at snapshot 3: " + historicMap.getAt("a", 3));
        System.out.println("b at snapshot 0: " + historicMap.getAt("b", 0));
        System.out.println("b at snapshot 2: " + historicMap.getAt("b", 2));
        System.out.println("b at snapshot 3: " + historicMap.getAt("b", 3));
        System.out.println("c at snapshot 2: " + historicMap.getAt("c", 2));
        System.out.println("c at snapshot 100: " + historicMap.getAt("c", 100));

        /**
         * Output:
         * a at snapshot 0: a0
         * a at snapshot 1: a1
         * a at snapshot 2: a1
         * a at snapshot 3: a1
         * b at snapshot 0: null
         * b at snapshot 2: b2.1
         * b at snapshot 3: b3
         * c at snapshot 2: null
         * c at snapshot 100: null
         */
    }
}
