package api;

public class Location implements GeoLocation {
    private double x;
    private double y;
    private double z;

    public Location(double x ,double y , double z ){
        this.x = x ;
        this.y = y;
        this.z = z ;
    }
    public Location(Location other){
        this.x = other.x;
        this.y = other.y;
        this.z = other.z;
    }
    @Override
    public double x() {
        return x;
    }

    @Override
    public double y() {
        return y;
    }

    @Override
    public double z() {
        return z;
    }

    @Override
    public double distance(GeoLocation g) {
        double d = Math.pow(this.x - g.x(), 2) + Math.pow(this.y - g.y(), 2) + Math.pow(this.z - g.z(), 2);
        d = Math.sqrt(d);
        return d ;
    }
    public String toString(){
        return "(" + x + ", " + y + ", " + z + ")";
    }
}


