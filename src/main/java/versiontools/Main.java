package versiontools;
import java.util.*;

public class Main {
    public static void main(String[] args){
        String operation =args[0];
        String ret = "";

        CheckInRange checkRange = new CheckInRange();
        if (operation.equals("check_in_range")){
            String vers = args[1];
            String r = args[2];
            String []versions = vers.split("\\|");
            for (String v: versions){
//                System.out.println(v);
                if (checkRange.check_in_range(v, r)){
                    if (ret.compareTo("") == 0){
                        ret += v;
                    }else{
                        ret += "|" + v;
                    }
                }
            }
             System.out.println(ret);

        }
        else if (operation.equals("sort")) {
            String vers = args [1];
//            System.out.println(vers);
            String []versions = vers.split("\\|");
            List<String> verlist = Arrays.asList(versions);
            List<String> sorted_vers = checkRange.sort_versions(verlist);
            for (String v : sorted_vers){
                if (ret.compareTo("") == 0){
                    ret += v;
                }else{
                    ret += "|" + v;
                }
            }
            System.out.println(ret);
        }
//        return ret;

    }
}
