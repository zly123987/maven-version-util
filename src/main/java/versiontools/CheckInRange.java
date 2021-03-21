package versiontools;


import org.apache.maven.artifact.versioning.*;

import java.util.*;

class SortVersions implements Comparator<Comparable>
{
    // Used for sorting in ascending order of versions
    public int compare(Comparable a, Comparable b)
    {
        return a.compareTo(b);
    }
}
public class CheckInRange {

    public boolean is_greater_than(String v1, String v2){
        ComparableVersion c1 = new ComparableVersion(v1);
        ComparableVersion c2 = new ComparableVersion(v2);
        if (c1.compareTo(c2) > 0){
            return true;
        }else{
            return false;
        }
    }
    public List<String> sort_versions(List<String> verlist){
        List<ComparableVersion> cvlist = new ArrayList<ComparableVersion>();
        for (String s: verlist){
            cvlist.add(new ComparableVersion(s));
        }
        Collections.sort(cvlist, new SortVersions());
        List<String> sorted_list = new ArrayList<String>();
        for (ComparableVersion cv : cvlist){
            sorted_list.add(cv.toString());
        }
        return sorted_list;
    }

    private void checkInvalidRange( String range )
    {
        try
        {
            VersionRange.createFromVersionSpec( range );
        }
        catch ( InvalidVersionSpecificationException expected )
        {
            System.out.println("Invalid range: " + range);
        }
    }

    public boolean check_in_range(String version, String versionrange){
        String tmp = versionrange.replaceAll("\\[|\\]|\\(|\\)|,", "");
        if (tmp.length()==versionrange.length()){
            if (version.equals(versionrange))
                return true;
            else
                return false;
        }
//        System.out.println(version + "   " +  versionrange);
        ComparableVersion c1 = new ComparableVersion(version);
        VersionRange vr = null;
        try {
            vr = VersionRange.createFromVersionSpec(versionrange);

        } catch (InvalidVersionSpecificationException e) {
            e.printStackTrace();
        }
        if (vr != null) {
            return vr.containsVersion(new DefaultArtifactVersion(version));
        }else{
            return false;
        }
    }
//    static public void testSnapshotSoftVersionCanContainSnapshot(String requiredVersionRange, String actualVersion)
//    {
//        VersionRange vr = null;
//
//        try {
//            vr = VersionRange.createFromVersionSpec( requiredVersionRange );
//            System.out.println(vr.containsVersion(new DefaultArtifactVersion(actualVersion)));
//        } catch (InvalidVersionSpecificationException e) {
//            e.printStackTrace();
//        }
//
//    }
//    public static void main(String[] args) {
//        System.out.println(new CheckRange().check_in_range("3.0.1", "[4.0.0,)"));
//        System.out.println(new CheckRange().check_in_range("3.0.1", "[3.0.0,)"));
//        System.out.println(new CheckRange().check_in_range("3.0.1", "[3.0.1,)"));
//        System.out.println(new CheckRange().check_in_range("3.0.1", "(3.0.1,)"));
//        System.out.println(new CheckRange().check_in_range("3.0.1", "[4,)"));
//        testSnapshotSoftVersionCanContainSnapshot( "2.0.5", "2.0.5" );
//        testSnapshotSoftVersionCanContainSnapshot( "2.0.4", "2.0.5" );
//        testSnapshotSoftVersionCanContainSnapshot( "[2.0.5]", "2.0.5" );
//        testSnapshotSoftVersionCanContainSnapshot( "[2.0.6,)", "2.0.5" );
//        testSnapshotSoftVersionCanContainSnapshot( "[2.0.6]", "2.0.5" );
//        testSnapshotSoftVersionCanContainSnapshot( "[2.0,2.1]", "2.0.5" );
//        testSnapshotSoftVersionCanContainSnapshot( "[2.0,2.0.3]", "2.0.5" );
//        testSnapshotSoftVersionCanContainSnapshot( "[2.0,2.0.5]", "2.0.5" );
//        testSnapshotSoftVersionCanContainSnapshot( "[2.0,2.0.5)", "2.0.5" );
//        System.out.println(new DefaultArtifactVersion("(3.0.0, 4.0.0)"));
//    }

}

