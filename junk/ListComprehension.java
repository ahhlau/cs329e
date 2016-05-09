package cheng;

import java.util.*;
import java.util.stream.Collectors;

public class ListComprehension {
    public static void main(String[] args) {
        ArrayList<List<Object>> emp = new ArrayList<>();
        emp.add(Arrays.asList(1, "MARTIN", "CARMEN", "MARTINCU", "3-MAR-90", "", "PRESIDENT", 4500, 0.0, 50, 0));
        emp.add(Arrays.asList(10, "JACKSON", "MARTA", "JACKSOMT", "27-FEB-91", "", "WAREHOUSE MANAGER", 1507, 0.0, 45, 2));
        emp.add(Arrays.asList(11, "HENDERSON", "COLIN", "HENDERCS", "14-MAY-90", "", "SALES REPRESENTATIVE", 1400, 10.0, 31, 3));
        emp.add(Arrays.asList(12, "GILSON", "SAM", "GILSONSJ", "18-JAN-92", "", "SALES REPRESENTATIVE", 1490, 12.5, 32, 3));
        emp.add(Arrays.asList(13, "SANDERS", "JASON", "SANDERJK", "18-FEB-91", "", "SALES REPRESENTATIVE", 1515, 10.0, 33, 3));
        emp.add(Arrays.asList(14, "DAMERON", "ANDRE", "DAMEROAP", "9-OCT-91", "", "SALES REPRESENTATIVE", 1450, 17.5, 35, 3));
        emp.add(Arrays.asList(15, "HARDWICK", "ELAINE", "HARDWIEM", "7-FEB-92", "", "STOCK CLERK", 1400, 0.0, 41, 6));
        emp.add(Arrays.asList(16, "BROWN", "GEORGE", "BROWNGW", "8-MAR-90", "", "STOCK CLERK", 940, 0.0, 41, 6));
        emp.add(Arrays.asList(17, "WASHINGTON", "THOMAS", "WASHINTL", "9-FEB-91", "", "STOCK CLERK", 1200, 0.0, 42, 7));
        emp.add(Arrays.asList(18, "PATTERSON", "DONALD", "PATTERDV", "6-AUG-91", "", "STOCK CLERK", 795, 0.0, 42, 7));
        emp.add(Arrays.asList(19, "BELL", "ALEXANDER", "BELLAG", "26-MAY-91", "", "STOCK CLERK", 850, 0.0, 43, 8));
        emp.add(Arrays.asList(2, "SMITH", "DORIS", "SMITHDJ", "8-MAR-90", "", "VP, OPERATIONS", 2450, 0.0, 41, 1));
        emp.add(Arrays.asList(20, "GANTOS", "EDDIE", "GANTOSEJ", "30-NOV-90", "", "STOCK CLERK", 800, 0.0, 44, 9));
        emp.add(Arrays.asList(21, "STEPHENSON", "BLAINE", "STEPHEBS", "17-MAR-91", "", "STOCK CLERK", 860, 0.0, 45, 10));
        emp.add(Arrays.asList(22, "CHESTER", "EDDIE", "CHESTEEK", "30-NOV-90", "", "STOCK CLERK", 800, 0.0, 44, 9));
        emp.add(Arrays.asList(23, "PEARL", "ROGER", "PEARLRG", "17-OCT-90", "", "STOCK CLERK", 795, 0.0, 34, 9));
        emp.add(Arrays.asList(24, "DANCER", "BONNIE", "DANCERBW", "17-MAR-91", "", "STOCK CLERK", 860, 0.0, 45, 7));
        emp.add(Arrays.asList(25, "SCHMITT", "SANDRA", "SCHMITSS", "9-MAY-91", "", "STOCK CLERK", 1100, 0.0, 45, 8));
        emp.add(Arrays.asList(3, "NORTON", "MICHAEL", "NORTONMA", "17-JUN-91", "", "VP, SALES", 2400, 0.0, 31, 1));
        emp.add(Arrays.asList(4, "QUENTIN", "MARK", "QUENTIML", "7-APR-90", "", "VP, FINANCE", 2450, 0.0, 10, 1));
        emp.add(Arrays.asList(5, "ROPER", "JOSEPH", "ROPERJM", "4-MAR-90", "", "VP, ADMINISTRATION", 2550, 0.0, 50, 1));
        emp.add(Arrays.asList(6, "BROWN", "MOLLY", "BROWNMR", "18-JAN-91", "", "WAREHOUSE MANAGER", 1600, 0.0, 41, 2));
        emp.add(Arrays.asList(7, "HAWKINS", "ROBERTA", "HAWKINRT", "14-MAY-90", "", "WAREHOUSE MANAGER", 1650, 0.0, 42, 2));
        emp.add(Arrays.asList(8, "BURNS", "BEN", "BURNSBA", "7-APR-90", "", "WAREHOUSE MANAGER", 1500, 0.0, 43, 2));
        emp.add(Arrays.asList(9, "CATSKILL", "ANTOINETTE", "CATSKIAW", "9-FEB-92", "", "WAREHOUSE MANAGER", 1700, 0.0, 44, 2));

        System.out.println("Select * from s_emp;");
        emp.stream()
                .forEach(e -> {System.out.println(e);});

        System.out.println();
        System.out.println("Select LAST_NAME where SALARY>1500 from s_emp;");
        emp.stream()
                .filter(e ->  (int) e.get(7) > 1500)
                .forEach(e -> {System.out.print("[");System.out.print(e.get(1));System.out.println("]");});

        System.out.println();
        System.out.println("select LAST_NAME, FIRST_NAME from s_emp ORDER by SALARY");
        emp.stream()
                .sorted((a1, a2) -> (int) a1.get(7) - (int)a2.get(7))
                .forEach(e -> {System.out.print("[");System.out.print(e.get(1)); System.out.print(", ");System.out
                        .print(e.get(2));System.out.println("]");});
                //.forEach(a -> {System.out.print(a.get(1)); System.out.print(" "); System.out.println(a.get(2));});

        System.out.println();
        System.out.println("UPDATE s_emp SET SALARY = SALARY + 1000 where COMMISSION>0; select LAST_NAME, " +
                "FIRST_NAME, SALARY from s_emp ORDER by SALARY where COMMISSION>0");
        emp.stream()
                .filter(e -> (double) e.get(8) > 0)
                .sorted((e1, e2) -> (int) e1.get(7) - (int)e2.get(7))
                .map( e -> {e.set(7, (int) e.get(7)+1000); return e;})
                .forEach(e -> {System.out.print("[");System.out.print(e.get(1)); System.out.print(", "); System.out
                        .print(e
                        .get(2));System
                        .out.print(", "); System.out.print(e.get(7));System.out.println("]");});

        System.out.println();
        System.out.println("select TITLE sum SALARY from s_emp group by TITLE");
        emp.stream()
                .collect(Collectors
                        .groupingBy(e -> (String) e.get(6),
                        Collectors
                                .summingInt(e -> (int) e.get(7))))
                .forEach((a,b) -> {System.out.print("[");System.out.print(a); System.out.print(", "); System.out
                        .print(b);System.out.println("]");
                });
    }
}