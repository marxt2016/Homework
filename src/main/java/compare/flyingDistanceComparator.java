package compare;

import model.Plane;

import java.util.Comparator;

/**
 * Created by Olga_Melnikova on 10/11/2016.
 */
public class flyingDistanceComparator implements Comparator<Plane>{
    @Override
    public int compare(Plane p1, Plane p2) {
        if (p1.getAverageFlyingDistance()<p2.getAverageFlyingDistance()){
            return -1;
        }
        if(p1.getAverageFlyingDistance()>p2.getAverageFlyingDistance()){
            return 1;
        }
        return 0;
    }
}
