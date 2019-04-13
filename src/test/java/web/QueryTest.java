package web;

import common.Util;
import junit.framework.TestCase;

import java.util.List;

public class QueryTest extends TestCase {

    public void testGetResult() {
        Util.setEnv("dev");

        List<List<String>> result = Query.getResult("南开大学");
        for (List<String> snippet : result) {
            for (String s : snippet)
                System.out.println(s);
            System.out.println();
        }
    }
}