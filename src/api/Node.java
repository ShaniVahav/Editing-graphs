package api;

public class Node implements NodeData {
    public  static  int id = 0 ;
    private int key ;
    GeoLocation  location ;
    private int tag ;
    private String info ;


    public Node(Node other){
        this.key = other.key;
        this.location = new Location((Location) other.location);
        this.tag = other.tag;
        this.info = other.info+"";
    }
////
    public Node(Location location, int tag){
        this.key = id;
        this.location = new Location(location);
        this.tag = 0;
        this.info = "("+ this.key + ", " + this.location.toString() +", " + this.tag + ")";
        id++;
    }
    @Override
    public int getKey() {
       return this.key;
    }

    @Override
    public GeoLocation getLocation() {
        return this.location;
    }

    @Override
    public void setLocation(GeoLocation p) {
             this.location = p;
    }

    @Override
    public String getInfo() {
      return info ;
    }

    @Override
    public void setInfo(String s) {
     this.info = s ;
    }

    @Override
    public int getTag() {
        return 0;
    }

    @Override
    public void setTag(int t) {
     this.tag = tag ;
    }

}
